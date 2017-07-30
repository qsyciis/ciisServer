/* 
 * 文件名：YzsClientUtils.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月25日
 * 版本号：v1.0
*/
package com.qzi.cms.common.util;

import com.qzi.cms.common.vo.ClientVo;

/**
 * 云之讯通讯
 * @author qsy
 * @version v1.0
 * @date 2017年7月25日
 */
public class YzsClientUtils {
	private String sid;
	private String token;
	private String appid;
	private String url;
	
	/**
	 * 创建帐号
	 * @param client
	 * @return
	 * @throws Exception
	 */
	public String createClient(ClientVo client) throws Exception{
		String rstr = null;
		String timestamp = DateUtils.formatDateTime(DateUtils.DATE_TIME_FORMAT4);
		String reqUrl = url.concat("/2015-06-30")
		.concat("/Accounts/")
		.concat(sid)
		.concat("/Clients")
		.concat("?sig=")
		.concat(CryptUtils.getSignature(sid, token, timestamp));
		client.setAppId(appid);
		String param = "{\"client\":"+ToolUtils.toJson(client)+"}";
		String auth= CryptUtils.base64Encoder(sid+":"+timestamp);
		rstr = HttpUtils.sendPostJson(reqUrl,param,auth);
		return rstr;
	}
	
	/**
	 * 注销帐号
	 * @param client
	 * @return
	 * @throws Exception
	 */
	public String deleteClient(ClientVo client) throws Exception{
		String rstr = null;
		String timestamp = DateUtils.formatDateTime(DateUtils.DATE_TIME_FORMAT4);
		String reqUrl = url.concat("/2015-06-30")
		.concat("/Accounts/")
		.concat(sid)
		.concat("/dropClient")
		.concat("?sig=")
		.concat(CryptUtils.getSignature(sid, token, timestamp));
		client.setAppId(appid);
		String param = "{\"client\":"+ToolUtils.toJson(client)+"}";
		String auth= CryptUtils.base64Encoder(sid+":"+timestamp);
		rstr = HttpUtils.sendPostJson(reqUrl,param,auth);
		return rstr;
	}
	
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
}
