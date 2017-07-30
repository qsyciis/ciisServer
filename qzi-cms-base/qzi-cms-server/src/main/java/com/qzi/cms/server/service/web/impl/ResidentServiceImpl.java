/* 
 * 文件名：ResidentServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.po.UseResidentRoomPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.CryptUtils;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.util.YzsClientUtils;
import com.qzi.cms.common.vo.ClientRespVo;
import com.qzi.cms.common.vo.ClientVo;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.mapper.UseBuildingMapper;
import com.qzi.cms.server.mapper.UseResidentMapper;
import com.qzi.cms.server.mapper.UseResidentRoomMapper;
import com.qzi.cms.server.mapper.UseRoomMapper;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.BuildingService;
import com.qzi.cms.server.service.web.ResidentService;

/**
 * 珠海业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
@Service
public class ResidentServiceImpl implements ResidentService {
	@Resource
	private CommonService commonService;
	@Resource
	private UseResidentMapper residentMapper;
	@Resource
	private BuildingService buildService;
	@Resource
	private UseBuildingMapper buildMapper;
	@Resource
	private UseRoomMapper roomMapper;
	@Resource
	private UseResidentRoomMapper residentRoomMapper;
	@Resource
	private YzsClientUtils clientUtils;
	
	@Override
	public List<UseResidentVo> findAll(Paging paging, String criteria) throws Exception {
		//读取用户信息
		SysUserVo userVo = commonService.findUser();
		//分页对象
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return residentMapper.findAll(rwoBounds,criteria,userVo.getId());
	}


	@Override
	public long findCount(String criteria) throws Exception {
		//读取用户信息
		SysUserVo userVo = commonService.findUser();
		return residentMapper.findCount(criteria,userVo.getId());
		
	}


	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(UseResidentVo residentVo) throws Exception {
		//保存数据
		UseResidentPo residentPo = YBBeanUtils.copyProperties(residentVo, UseResidentPo.class);
		residentPo.setId(ToolUtils.getUUID());
		String salt = ToolUtils.getUUID();
		residentPo.setSalt(salt);
		String loginPw = CryptUtils.hmacSHA1Encrypt(residentVo.getMobile(), salt);
		residentPo.setPassword(loginPw);
		residentPo.setCreateTime(new Date());
		residentMapper.insert(residentPo);
		//注册client账号
		ClientVo client = new ClientVo();
		client.setUserId(residentVo.getMobile());
		client.setMobile(residentVo.getMobile());
		ClientRespVo clientResp = ToolUtils.toObject(clientUtils.createClient(client),ClientRespVo.class);
		if(clientResp.getResp().getRespCode().equals("000000")){
			residentPo.setClientNumber(clientResp.getResp().getClient().getClientNumber());
			residentPo.setClientPwd(clientResp.getResp().getClient().getClientPwd());
			residentMapper.updateByPrimaryKey(residentPo);
		}else{
			throw new CommException("注册Client账号失败["+clientResp.getResp().getRespCode()+"]");
		}
	}

	@Override
	public void update(UseResidentVo residentVo) {
		UseResidentPo residentPo = residentMapper.selectByPrimaryKey(residentVo.getId());
		residentPo.setCommunityId(residentVo.getCommunityId());
		residentPo.setName(residentVo.getName());
		residentPo.setOwner(residentVo.getOwner());
		residentPo.setState(residentVo.getState());
		residentMapper.updateByPrimaryKey(residentPo);
	}

	@Override
	public List<TreeVo> findCommunitys() throws Exception {
		return buildService.findTree();
	}

	@Override
	public boolean existsMobile(UseResidentVo residentVo) {
		return residentMapper.existsMobile(residentVo.getMobile(),residentVo.getCommunityId());
	}

	@Override
	public List<OptionVo> findBuildings(String communityId) {
		return buildMapper.findBuildings(communityId);
	}


	@Override
	public List<OptionVo> findUnits(String buildingId) {
		List<OptionVo> ropts = new ArrayList<>();
		UseBuildingPo buildingPo = buildMapper.selectByPrimaryKey(buildingId);
		for(int u=1;u<=buildingPo.getUnitNumber();u++){
			ropts.add(new OptionVo(String.format("%02d", u), u+"单元"));
		}
		return ropts;
	}

	@Override
	public List<OptionVo> findRooms(String buildingId, String unitNo) {
		return roomMapper.findRooms(buildingId,unitNo);
	}
	
	@Override
	public List<UseRoomVo> findResidentRooms(String residentId) {
		return roomMapper.findResidentRooms(residentId);
	}

	@Override
	public boolean existsRelation(UseResidentRoomVo residentRoomVo) {
		return residentRoomMapper.existsRelation(residentRoomVo);
	}

	@Override
	public void addRelation(UseResidentRoomVo residentRoomVo) throws Exception {
		UseResidentRoomPo po = YBBeanUtils.copyProperties(residentRoomVo, UseResidentRoomPo.class);
		residentRoomMapper.insert(po);
	}

	@Override
	public void delRelation(UseResidentRoomVo residentRoomVo) throws Exception {
		UseResidentRoomPo po = YBBeanUtils.copyProperties(residentRoomVo, UseResidentRoomPo.class);
		residentRoomMapper.delete(po);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(UseResidentVo residentVo) throws Exception {
		//注销云之讯账户
		ClientVo client = new ClientVo();
		client.setUserId(residentVo.getMobile());
		clientUtils.deleteClient(client);
		//删除住户房间关系
		residentRoomMapper.deleteByResidentId(residentVo.getId());
		//删除住户信息
		residentMapper.deleteByPrimaryKey(residentVo.getId());
	}

}
