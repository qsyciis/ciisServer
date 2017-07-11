/* 
 * 文件名：ResidentAreaService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月28日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.enums.StateEnum;
import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.po.UseResidentAreaPo;
import com.qzi.cms.common.po.UseResidentUserPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.AdminVo;
import com.qzi.cms.common.vo.ResidentAreaAdminVo;
import com.qzi.cms.common.vo.SysCityVo;
import com.qzi.cms.common.vo.UseResidentAreaVo;
import com.qzi.cms.server.mapper.SysCityMapper;
import com.qzi.cms.server.mapper.SysUserMapper;
import com.qzi.cms.server.mapper.UseBuildingMapper;
import com.qzi.cms.server.mapper.UseResidentAreaMapper;
import com.qzi.cms.server.mapper.UseResidentUserMapper;
import com.qzi.cms.server.service.web.ResidentAreaService;

/**
 * 住宅小区业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年6月28日
 */
@Service
public class ResidentAreaServiceImpl implements ResidentAreaService {
	@Resource
	private UseResidentAreaMapper residentAreaMapper;
	@Resource
	private SysCityMapper cityMapper;
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private UseResidentUserMapper residentUserMapper;
	@Resource
	private UseBuildingMapper buildMapper;

	@Override
	public List<UseResidentAreaVo> findAll(Paging paging) {
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return residentAreaMapper.findAll(rwoBounds);
	}

	@Override
	public long findCount() {
		return residentAreaMapper.findCount();
	}

	@Override
	public void add(UseResidentAreaVo residentAreaVo) throws Exception {
		//保存小区信息
		UseResidentAreaPo raPo = YBBeanUtils.copyProperties(residentAreaVo, UseResidentAreaPo.class);
		raPo.setId(ToolUtils.getUUID());
		raPo.setResidentNo(getResidentNo());
		raPo.setCreateTime(new Date());
		residentAreaMapper.insert(raPo);
		//保存楼栋信息
		for(int n=1;n<=residentAreaVo.getBuildingNum();n++){
			UseBuildingPo buildPo = new UseBuildingPo();
			buildPo.setId(ToolUtils.getUUID());
			buildPo.setBuildingName(n+"栋");
			buildPo.setBuildingNo(String.format("%02d", n));
			buildPo.setResidentId(raPo.getId());
			buildPo.setState(StateEnum.NORMAL.getCode());
			buildMapper.insert(buildPo);
		}
	}

	@Override
	public void update(UseResidentAreaVo residentAreaVo) throws Exception {
		UseResidentAreaPo raPo = YBBeanUtils.copyProperties(residentAreaVo, UseResidentAreaPo.class);
		residentAreaMapper.updateByPrimaryKey(raPo);
	}
	
	/**
	 * 获取小区编号
	 * @return 6位字符编号
	 */
	private String getResidentNo(){
		String no = residentAreaMapper.findMaxResidentNo();
		int tno=1;
		if(StringUtils.isNotEmpty(no)){
			tno = Integer.parseInt(no)+1;
		}
		return String.format("%06d", tno);
	}

	@Override
	public List<SysCityVo> findCitys(String parentCode) {
		return cityMapper.findCitys(parentCode);
	}

	@Override
	public List<ResidentAreaAdminVo> findAdmin(String residentId) {
		return userMapper.findAdmin(residentId);
	}

	@Override
	public void add(AdminVo adminVo) {
		//删除当前小区关联的数据
		residentUserMapper.deleteForRid(adminVo.getResidentId());
		//保存当前小区关系的数据
		for(String userId:adminVo.getUserIds()){
			UseResidentUserPo uruPo = new UseResidentUserPo();
			uruPo.setResidentId(adminVo.getResidentId());
			uruPo.setUserId(userId);
			residentUserMapper.insert(uruPo);
		}
	}

}
