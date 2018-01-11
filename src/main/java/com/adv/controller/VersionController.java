package com.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.models.Version;
import com.adv.service.VersionService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/version")
public class VersionController {
	
	@Autowired
	VersionService versionService;
	
	
	
	
	/**
	* @api {get} api/version/getVersionList 获取所有版本信息
	* @apiName version-getVersionList
	* @apiGroup version
	* @apiDescription 用于“版本”页面
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,//状态只有0和1,0-成功，1-失败
    *		"data": [
    *			{
    *					"id": 1,
    *					"vesionId": "2.0.0",
    *					"src": "xxx",
    *					"appName": "2.apk",
    *					"description": "dnkjadan"
    *			}
    *		],
    *		"numberPerPage": 0,
    *		"currentPage": 0,
    *		"totalNumber": 0,
    *		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"status": 1,
	*  		"data": "未知错误",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	*
	**/
	@RequestMapping(value="getVersionList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Version>> getVersionList() {

		return versionService.getVersionList();
	}
	
	/**
	* @api {post} api/version/add 添加版本
	* @apiName version-add
	* @apiGroup version
	* @apiDescription 用于“版本”页面
	*
	* @apiParam {String} vesionId * 字符串的版本号（必须）
	* @apiParam {String} appName * app的名称（必须，因为上传后的apk会被重命名，所以需要记录原来的名称）
	* @apiParam {String} src * 版本下载路径（必须）
	* @apiParam {String} description * 版本描述（非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *				"id": 1,
    *				"vesionId": "2.0.0",
    *				"src": "xxx",
    *				"appName": "2.apk",
    *				"description": "dnkjadan"
    *		},
    *		"numberPerPage": 0,
    *		"currentPage": 0,
    *		"totalNumber": 0,
    *		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"status": 1,
	*  		"data": "终端不存在",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Version> add(
			@ModelAttribute Version version
			
			) {

		return versionService.add(version);
	}
	
	
	/**
	* @api {delete} api/version/delete 删除版本
	* @apiName version-delete
	* @apiGroup version
	* 
	* @apiParam {Long} id * 版本id（必须,不是versionid）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": null,
    *		"numberPerPage": 0,
    *		"currentPage": 0,
    *		"totalNumber": 0,
    *		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"status": 1,
	*  		"data": "xxxx",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> delete(
			@RequestParam(value="id", required=true) Long id
			
			) {

		return versionService.delete(id);
	}
	
	
	
	/**
	* @api {get} api/version/getNewestVersion 获取最新的版本
	* @apiName version-getNewestVersion
	* @apiGroup version
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data":{
    *				"id": 1,
    *				"vesionId": "2.0.0",
    *				"src": "xxx",
    *				"appName": "2.apk",
    *				"description": "dnkjadan"
    *		}
    *		"numberPerPage": 0,
    *		"currentPage": 0,
    *		"totalNumber": 0,
    *		"totalPage": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"status": 1,
	*  		"data": "终端不存在",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="getNewestVersion",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Version> getNewestVersion(
			
			) {

		return versionService.getNewestVersion();
	}
	
	
	
	

}
