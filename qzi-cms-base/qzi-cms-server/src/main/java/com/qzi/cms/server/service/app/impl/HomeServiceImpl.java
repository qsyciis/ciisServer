/* 
 * 文件名：HomeServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月30日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qzi.cms.server.mapper.UseBannerMapper;
import com.qzi.cms.server.service.app.HomeService;

/**
 * 首页业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月30日
 */
@Service
public class HomeServiceImpl implements HomeService {
	@Resource
	private UseBannerMapper bannerMapper;

	@Override
	public List<String> findBanners() {
		return bannerMapper.findBanners();
	}

}
