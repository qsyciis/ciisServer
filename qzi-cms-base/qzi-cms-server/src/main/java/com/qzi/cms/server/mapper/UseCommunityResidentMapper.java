/* 
 * 文件名：UseCommunityResidentMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月1日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qzi.cms.common.po.UseCommunityResidentPo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 住户小区DAO
 * @author qsy
 * @version v1.0
 * @date 2017年8月1日
 */
public interface UseCommunityResidentMapper extends BaseMapper<UseCommunityResidentPo>{

	/**
	 * @param residentId
	 * @param communityId
	 * @return
	 */
	@Select("select count(1)>0 from use_community_resident where residentId=#{rid} and communityId=#{cid}")
	public boolean existsCR(@Param("rid") String residentId,@Param("cid") String communityId);

	/**
	 * @param id
	 * @param communityId
	 */
	@Select("delete from use_community_resident where residentId=#{rid} and communityId=#{cid}")
	public void deleteByCriteria(@Param("rid") String residentId,@Param("cid") String communityId);

}
