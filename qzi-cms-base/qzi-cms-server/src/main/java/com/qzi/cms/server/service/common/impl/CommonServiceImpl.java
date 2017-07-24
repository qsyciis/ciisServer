/* 
 * 文件名：CommonServiceImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.common.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qzi.cms.common.vo.SysUserVo;
import com.qzi.cms.server.service.common.CommonService;
import com.qzi.cms.server.service.web.UserService;

/**
 * 通用业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
@Service
public class CommonServiceImpl implements CommonService {
	@Resource
	private HttpServletRequest request;
	@Resource
	private UserService userService;

	@Override
	public SysUserVo findUser() throws Exception {
		String token = request.getHeader("token");
		//读取用户信息
		return userService.SysUserVo(token);
	}
	
	
}
