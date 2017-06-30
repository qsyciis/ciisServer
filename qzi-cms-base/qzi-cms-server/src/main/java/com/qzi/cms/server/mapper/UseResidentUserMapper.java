/* 
 * 文件名：UseResidentUserMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月29日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.UseResidentUserPo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 住宅小区用户关系DAO
 * @author qsy
 * @version v1.0
 * @date 2017年6月29日
 */
public interface UseResidentUserMapper extends BaseMapper<UseResidentUserPo>{

	/**
	 * 删除数据
	 * @param residentId 住宅小区编号
	 */
	@Select("delete from use_resident_user where residentId = #{rid}")
	public void deleteForRid(@Param("rid") String residentId);

}
