/* 
 * 文件名：ResidentAreaService.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年6月28日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web;

import java.util.List;

import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.vo.AdminVo;
import com.qzi.cms.common.vo.ResidentAreaAdminVo;
import com.qzi.cms.common.vo.SysCityVo;
import com.qzi.cms.common.vo.UseResidentAreaVo;

/**
 * 住宅小区业务层接口
 * @author qsy
 * @version v1.0
 * @date 2017年6月28日
 */
public interface ResidentAreaService {

	/**
	 * 查找所有数据
	 * @param paging
	 * @return
	 */
	public List<UseResidentAreaVo> findAll(Paging paging);

	/**
	 * 查找总记录数
	 * @return
	 */
	public long findCount();

	/**
	 * 保存
	 * @param residentAreaVo
	 * @throws Exception 
	 */
	public void add(UseResidentAreaVo residentAreaVo) throws Exception;

	/**
	 * 修改
	 * @param residentAreaVo
	 * @throws Exception 
	 */
	public void update(UseResidentAreaVo residentAreaVo) throws Exception;

	/**
	 * 查找中国城市
	 * @param parentCode
	 * @return
	 */
	public List<SysCityVo> findCitys(String parentCode);

	/**
	 * @param residentId
	 * @return
	 */
	public List<ResidentAreaAdminVo> findAdmin(String residentId);

	/**
	 * @param adminVo
	 */
	public void add(AdminVo adminVo);

}
