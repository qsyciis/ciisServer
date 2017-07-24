/* 
 * 文件名：UseResidentRoomPo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月20日
 * 版本号：v1.0
*/
package com.qzi.cms.common.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 住户房间Po类
 * @author qsy
 * @version v1.0
 * @date 2017年7月20日
 */
@Table(name="use_resident_room")
public class UseResidentRoomPo {
	/**
	 * 住户编号
	 */
	@Id
	private String residentId;
	/**
	 * 房间编号
	 */
	@Id
	private String roomId;
	/**
	 * @return the residentId
	 */
	public String getResidentId() {
		return residentId;
	}
	/**
	 * @param residentId the residentId to set
	 */
	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}
	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
}
