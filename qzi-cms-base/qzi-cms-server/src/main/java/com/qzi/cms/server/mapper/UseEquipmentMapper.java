/* 
 * 文件名：UseEquipmentMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseEquipmentPo;
import com.qzi.cms.common.vo.UseEquipmentVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 设备管理DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月27日
 */
public interface UseEquipmentMapper extends BaseMapper<UseEquipmentPo>{

	/**
	 * @param rwoBounds
	 * @param criteria
	 * @param id
	 * @return
	 */
	public List<UseEquipmentVo> findAll(RowBounds rwoBounds,@Param("criteria") String criteria,@Param("uid") String userId);

	/**
	 * @param criteria
	 * @param id
	 * @return
	 */
	public long findCount(@Param("criteria") String criteria,@Param("uid") String userId);

	/**
	 * @param communityId
	 * @param equipmentType
	 * @return
	 */
	@Select("SELECT MAX(equipmentId) from use_equipment where communityId=#{cid} and equipmentType=#{etype}")
	public String findByCondition(@Param("cid")String communityId,@Param("etype") String equipmentType);

	/**
	 * @param communityId
	 * @param equipmentType
	 * @param buildingId
	 * @param unitName
	 * @return
	 */
	@Select("SELECT MAX(equipmentId) from use_equipment where communityId=#{cid} and equipmentType=#{etype} and buildingId=#{bid} and unitName=#{uno}")
	public String findByExample(@Param("cid") String communityId,@Param("etype") String equipmentType,@Param("bid") String buildingId,@Param("uno") int unitName);

}
