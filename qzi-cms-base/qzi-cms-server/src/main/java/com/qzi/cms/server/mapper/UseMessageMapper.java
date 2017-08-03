/* 
 * 文件名：UseMessageMapper.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.qzi.cms.common.po.UseMessagePo;
import com.qzi.cms.common.vo.UseMessageVo;
import com.qzi.cms.server.base.BaseMapper;

/**
 * 消息DAO
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
public interface UseMessageMapper extends BaseMapper<UseMessagePo>{

	/**
	 * @param rwoBounds
	 * @return
	 */
	@Select("select * from use_message order by createTime")
	public List<UseMessageVo> findAll(RowBounds rwoBounds);

	/**
	 * @return
	 */
	@Select("select count(1) from use_message")
	public long findCount();

}
