/* 
 * 文件名：ResidentController.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月18日
 * 版本号：v1.0
*/
package com.qzi.cms.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzi.cms.common.annotation.SystemControllerLog;
import com.qzi.cms.common.enums.RespCodeEnum;
import com.qzi.cms.common.exception.CommException;
import com.qzi.cms.common.resp.Paging;
import com.qzi.cms.common.resp.RespBody;
import com.qzi.cms.common.util.LogUtils;
import com.qzi.cms.common.vo.UseResidentRoomVo;
import com.qzi.cms.common.vo.UseResidentVo;
import com.qzi.cms.server.service.web.ResidentService;

/**
 * 住户信息控制器
 * @author qsy
 * @version v1.0
 * @date 2017年7月18日
 */
@RestController
@RequestMapping("/resident")
public class ResidentController {
	@Resource
	private ResidentService residentService;
	
	@GetMapping("/findCommunitys")
	public RespBody findCommunitys(){
		RespBody respBody = new RespBody();
		try {
			//查找数据并返回
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "获取用户小区信息成功",residentService.findCommunitys());
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "获取用户小区信息异常");
			LogUtils.error("获取用户小区信息异常！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findAll")
	public RespBody findAll(Paging paging,String criteria){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户数据成功", residentService.findAll(paging,criteria));
			//保存分页对象
			paging.setTotalCount(residentService.findCount(criteria));
			respBody.setPage(paging);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住户数据失败");
			LogUtils.error("查找所有住户数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/add")
	@SystemControllerLog(description="新增住户信息")
	public RespBody add(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			if(residentService.existsMobile(residentVo)){
				//已存在
				respBody.add(RespCodeEnum.ERROR.getCode(), "手机号已存在");
			}else{
				//不存在
				residentService.add(residentVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户数据保存成功");
			}
			
		} catch (CommException ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "云之讯调用异常");
			LogUtils.error("云之讯调用异常！",ex);
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户据保存失败");
			LogUtils.error("住户据保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/update")
	@SystemControllerLog(description="修改住户")
	public RespBody update(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			residentService.update(residentVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户保存成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户保存失败");
			LogUtils.error("住户保存失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delete")
	@SystemControllerLog(description="删除住户")
	public RespBody delete(@RequestBody UseResidentVo residentVo){
		RespBody respBody = new RespBody();
		try {
			residentService.delete(residentVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "住户删除成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "住户删除失败");
			LogUtils.error("住户删除失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findBuildings")
	public RespBody findBuildings(String communityId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找楼栋数据成功", residentService.findBuildings(communityId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找楼栋数据失败");
			LogUtils.error("查找楼栋数据失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findUnits")
	public RespBody findUnits(String buildingId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找单元数据成功", residentService.findUnits(buildingId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找单元数据失败");
			LogUtils.error("查找单元数据失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findRooms")
	public RespBody findRooms(String buildingId,String unitNo){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找房间数据成功", residentService.findRooms(buildingId,unitNo));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找房间数据失败");
			LogUtils.error("查找房间数据失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/addRelation")
	@SystemControllerLog(description="保存住户房间关系")
	public RespBody addRelation(@RequestBody UseResidentRoomVo residentRoomVo){
		RespBody respBody = new RespBody();
		try {
			if(residentService.existsRelation(residentRoomVo)){
				//已存在
				respBody.add(RespCodeEnum.ERROR.getCode(), "用户已绑定了此房间");
			}else{
				//不存在
				residentService.addRelation(residentRoomVo);
				respBody.add(RespCodeEnum.SUCCESS.getCode(), "保存住户房间关系成功");
			}
			
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "保存住户房间关系失败");
			LogUtils.error("保存住户房间关系失败！",ex);
		}
		return respBody;
	}
	
	@PostMapping("/delRelation")
	@SystemControllerLog(description="删除住户房间关系")
	public RespBody delRelation(@RequestBody UseResidentRoomVo residentRoomVo){
		RespBody respBody = new RespBody();
		try {
			residentService.delRelation(residentRoomVo);
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "删除住户房间关系成功");
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "删除住户房间关系失败");
			LogUtils.error("删除住户房间关系失败！",ex);
		}
		return respBody;
	}
	
	@GetMapping("/findResidentRooms")
	public RespBody findResidentRooms(String residentId){
		RespBody respBody = new RespBody();
		try {
			//保存返回数据
			respBody.add(RespCodeEnum.SUCCESS.getCode(), "查找所有住户数据成功", residentService.findResidentRooms(residentId));
		} catch (Exception ex) {
			respBody.add(RespCodeEnum.ERROR.getCode(), "查找所有住户数据失败");
			LogUtils.error("查找所有住户数据失败！",ex);
		}
		return respBody;
	}
	
}
