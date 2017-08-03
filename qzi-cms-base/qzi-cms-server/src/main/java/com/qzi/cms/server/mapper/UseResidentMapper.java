/* 
 * 文件名：UseResidentMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseResidentPo;
import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 住户信息DAO
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
public interface UseResidentMapper  extends BaseMapper<UseResidentPo>{

	/**
	 * @param rwoBounds
	 * @param criteria
	 * @param id
	 * @return
	 */
	public List<UseResidentVo> findAll(RowBounds rwoBounds,@Param("criteria") String criteria,@Param("uid") String id);


	/**
	 * @param criteria
	 * @param id
	 * @return
	 */
	public long findCount(@Param("criteria") String criteria,@Param("uid") String id);


	/**
	 * @param mobile
	 * @param communityId
	 * @return
	 */
	@Select("select count(1)>0 from use_resident where mobile=#{mobile}")
	public boolean existsMobile(@Param("mobile") String mobile);


	/**
	 * @param loginName
	 * @return
	 */
	@Select("select * from use_resident where mobile=#{mobile}")
	public UseResidentPo findMobile(@Param("mobile") String loginName);


	/**
	 * 新住户查询
	 * @param rwoBounds
	 * @param criteria
	 * @return
	 */
	public List<UseResidentVo> findAllByCriteria(RowBounds rwoBounds,@Param("criteria") String criteria);


	/**
	 *  新住户分页查询
	 * @param criteria
	 * @return
	 */
	public long findCountByCriteria(@Param("criteria") String criteria);


	/**
	 * @param messageVo
	 * @return
	 */
	public List<String> findResident(@Param("vo") UseMessageVo messageVo);


	/**
	 * @param mobile
	 * @param password
	 */
	@Select("update use_resident set password=#{password},salt=#{salt} where mobile=#{mobile}")
	public void updatePwd(@Param("mobile") String mobile,@Param("password") String password,@Param("salt") String salt);

}
