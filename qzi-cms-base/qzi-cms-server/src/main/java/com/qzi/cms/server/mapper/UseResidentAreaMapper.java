/* 
 * 文件名：UseResidentAreaMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月27日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseResidentAreaPo;
import com.qzi.cms.common.vo.TreeVo;
import com.qzi.cms.common.vo.UseResidentAreaVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 住宅小区DAO接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月27日
 */
public interface UseResidentAreaMapper extends BaseMapper<UseResidentAreaPo>{

	/**
	 * @param rwoBounds
	 * @return
	 */
	@Select("select * from use_resident_area order by createTime desc")
	public List<UseResidentAreaVo> findAll(RowBounds rwoBounds);

	/**
	 * @return
	 */
	@Select("select count(1) from use_resident_area")
	public long findCount();

	/**
	 * @return
	 */
	@Select("select max(residentNo) from use_resident_area")
	public String findMaxResidentNo();

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT ura.id id,ura.residentName value from use_resident_area ura,use_resident_user uru where ura.id = uru.residentId and uru.userId=#{userId} and ura.state='10'")
	public List<TreeVo> findTree(@Param("userId") String id);
	
}
