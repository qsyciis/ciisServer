/* 
 * 文件名：UseBuildingMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月4日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseBuildingPo;
import com.qzi.cms.common.vo.UseBuildingVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 楼栋DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月4日
 */
public interface UseBuildingMapper extends BaseMapper<UseBuildingPo>{

	/**
	 * @param residentId
	 * @param rwoBounds 
	 * @return
	 */
	@Select("select * from use_building where residentId=#{residentId} order by buildingNo")
	public List<UseBuildingVo> findBuilding(@Param("residentId") String residentId, RowBounds rwoBounds);

	/**
	 * @param residentId
	 * @return
	 */
	@Select("select count(1) from use_building where residentId=#{residentId}")
	public long findCount(@Param("residentId") String residentId);

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT * from use_building where residentId=#{residentId} and state='10' ORDER BY buildingNo")
	public List<UseBuildingPo> findByResidentId(@Param("residentId") String id);
	
}
