/* 
 * 文件名：UseRoomMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月6日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseRoomPo;
import com.qzi.cms.common.vo.OptionVo;
import com.qzi.cms.common.vo.UseRoomVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 房间DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月6日
 */
public interface UseRoomMapper extends BaseMapper<UseRoomPo>{

	/**
	 * @param buildingId
	 * @param unitId
	 * @param rwoBounds
	 * @return
	 */
	@Select("SELECT * from use_room where unitName=#{unitId} and buildingId = #{buildingId} ORDER BY roomNo")
	public List<UseRoomVo> findBuilding(@Param("buildingId") String buildingId, @Param("unitId") String unitId, RowBounds rwoBounds);

	/**
	 * @param buildingId
	 * @param unitId
	 * @return
	 */
	@Select("SELECT count(1) from use_room where unitName=#{unitId} and buildingId = #{buildingId}")
	public long findCount(@Param("buildingId") String buildingId, @Param("unitId") String unitId);

	/**
	 * @param buildingId
	 * @param unitNo
	 * @return
	 */
	@Select("SELECT id value,roomName name from use_room where unitName=#{unitNo} and buildingId = #{buildingId} ORDER BY roomNo")
	public List<OptionVo> findRooms(@Param("buildingId")String buildingId,@Param("unitNo") String unitNo);

	/**
	 * @param residentId
	 * @return
	 */
	@Select("SELECT ur.*,ub.buildingName from use_room ur,use_resident_room urr,use_building ub "
			+ "where ur.buildingId = ub.id and urr.roomId= ur.id and urr.residentId=#{residentId}")
	public List<UseRoomVo> findResidentRooms(@Param("residentId") String residentId);

}