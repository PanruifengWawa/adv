package com.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.models.Terminal;
import com.adv.service.TerminalService;
import com.adv.utils.DataWrapper;


@Controller
@RequestMapping(value="/api/terminal")
public class TerminalController {
	
	@Autowired
	TerminalService terminalService;
	
	
	
	/**
	* @api {get} api/terminal/getTerminalList 获取所有终端列表
	* @apiName terminal-getTerminalList
	* @apiGroup terminal
	* @apiDescription 用于“终端管理”页面
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,//状态只有0和1,0-成功，1-失败
    *		"data": [
    *			{
    *				"mac": "123", //终端mac地址
    *				"name": "aa" //终端名称
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
	@RequestMapping(value="getTerminalList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Terminal>> getTerminalList() {

		return terminalService.getTerminalList();
	}
	
	
	/**
	* @api {post} api/terminal/update 修改终端名称
	* @apiName terminal-update
	* @apiGroup terminal
	* @apiDescription 用于“终端管理”页面
	*
	* @apiParam {String} mac * 设备的mac地址（必须）
	* @apiParam {String} name * 设备的新名称（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": [
    *			{
    *				"mac": "123",
    *				"name": "aa"
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
	*  		"data": "终端不存在",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="update",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Terminal> update(
			@RequestParam(value="mac", required=true) String mac,
			@RequestParam(value="name", required=true) String name
			
			) {
		return terminalService.update(mac, name);
	}
	
	
	/**
	* @api {delete} api/terminal/delete 删除终端
	* @apiName terminal-delete
	* @apiGroup terminal
	* @apiDescription 用于“终端管理”页面。注：删除时，如果这个终端的websocket是连接状态，则会断开这个连接
	* @apiParam {String} mac * 设备的mac地址（必须）
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
			@RequestParam(value="mac", required=true) String mac
			) {

		return terminalService.delete(mac);
	}
	
	

}
