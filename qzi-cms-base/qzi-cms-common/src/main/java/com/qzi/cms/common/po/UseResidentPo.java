/* 
 * 文件名：UseResidentPo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 住户信息Po类
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
@Table(name="use_resident")
public class UseResidentPo {
	/**
	 * 主键编号
	 */
	@Id
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 密码盐
	 */
	private String salt;
	/**
	 * 是否业主
	 */
	private String owner;
	/**
	 * 小区编号
	 */
	private String communityId;
	/**
	 * 第三方clientid
	 */
	private String clientNumber;
	/**
	 * 第三方密码
	 */
	private String clientPwd;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 开门密码
	 */
	private String openPwd;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the communityId
	 */
	public String getCommunityId() {
		return communityId;
	}
	/**
	 * @param communityId the communityId to set
	 */
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	/**
	 * @return the clientNumber
	 */
	public String getClientNumber() {
		return clientNumber;
	}
	/**
	 * @param clientNumber the clientNumber to set
	 */
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	/**
	 * @return the openPwd
	 */
	public String getOpenPwd() {
		return openPwd;
	}
	/**
	 * @param openPwd the openPwd to set
	 */
	public void setOpenPwd(String openPwd) {
		this.openPwd = openPwd;
	}
	/**
	 * @return the clientPwd
	 */
	public String getClientPwd() {
		return clientPwd;
	}
	/**
	 * @param clientPwd the clientPwd to set
	 */
	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
}
