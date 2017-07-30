/* 
 * 文件名：BannerServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月29日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.po.UseBannerPo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.UseBannerVo;
import com.qzi.cms.server.mapper.UseBannerMapper;
import com.qzi.cms.server.service.web.BannerService;

/**
 * 手机广告轮播图业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月29日
 */
@Service
public class BannerServiceImpl implements BannerService {
	@Resource
	private UseBannerMapper bannerMapper;

	@Override
	public List<UseBannerVo> findAll(Paging paging) throws Exception {
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return bannerMapper.findAll(rwoBounds);
	}

	@Override
	public void add(UseBannerVo bannerVo) throws Exception {
		UseBannerPo bannerPo = YBBeanUtils.copyProperties(bannerVo, UseBannerPo.class);
		bannerPo.setId(ToolUtils.getUUID());
		bannerPo.setCreateTime(new Date());
		System.out.println(bannerPo.getImg().length()+"========================");
		bannerMapper.insert(bannerPo);
	}

	@Override
	public void update(UseBannerVo bannerVo) throws Exception {
		UseBannerPo bannerPo = YBBeanUtils.copyProperties(bannerVo, UseBannerPo.class);
		bannerMapper.updateByPrimaryKey(bannerPo);
	}

	@Override
	public void delete(UseBannerVo bannerVo) {
		bannerMapper.deleteByPrimaryKey(bannerVo.getId());
	}

	@Override
	public long findCount() {
		return bannerMapper.findCount();
	}

}
