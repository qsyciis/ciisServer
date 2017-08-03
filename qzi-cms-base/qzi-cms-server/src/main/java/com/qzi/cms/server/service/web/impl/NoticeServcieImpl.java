/* 
 * 文件名：NoticeServcieImpl.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年8月2日
 * 版本号：v1.0
*/
package com.qzi.cms.server.service.web.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.qzi.cms.common.po.UseNoticePo;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.util.ToolUtils;
import com.qzi.cms.common.util.YBBeanUtils;
import com.qzi.cms.common.vo.UseNoticeVo;
import com.qzi.cms.server.mapper.UseNoticeMapper;
import com.qzi.cms.server.service.web.NoticeServcie;

/**
 * 公告业务层实现类
 * @author qsy
 * @version v1.0
 * @date 2017年8月2日
 */
@Service
public class NoticeServcieImpl implements NoticeServcie {
	@Resource
	private UseNoticeMapper noticeMapper;

	@Override
	public List<UseNoticeVo> findAll(Paging paging) throws Exception {
		RowBounds rwoBounds = new RowBounds(paging.getPageNumber(),paging.getPageSize());
		return noticeMapper.findAll(rwoBounds);
	}

	@Override
	public void add(UseNoticeVo noticeVo) throws Exception {
		//将vo转换成Po
		UseNoticePo noticePo = YBBeanUtils.copyProperties(noticeVo, UseNoticePo.class);
		noticePo.setId(ToolUtils.getUUID());
		noticePo.setCreateTime(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(noticeVo.getEndTime());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		noticePo.setEndTime(calendar.getTime());
		//调用dao保存数据
		noticeMapper.insert(noticePo);
	}

	@Override
	public void update(UseNoticeVo noticeVo) throws Exception {
		//将vo转换成Po
		UseNoticePo noticePo = YBBeanUtils.copyProperties(noticeVo, UseNoticePo.class);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(noticeVo.getEndTime());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		noticePo.setEndTime(calendar.getTime());
		//调用dao修改数据
		noticeMapper.updateByPrimaryKey(noticePo);
	}

	@Override
	public void delete(UseNoticeVo noticeVo) {
		noticeMapper.deleteByPrimaryKey(noticeVo.getId());
	}

	@Override
	public long findCount() {
		return noticeMapper.findCount();
	}

}
