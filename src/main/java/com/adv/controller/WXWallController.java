package com.adv.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.models.BgImage;
import com.adv.models.Message;
import com.adv.parameters.Parameters;
import com.adv.service.WXWallService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/wxwall")
public class WXWallController {
	
	@Autowired
	WXWallService wXWallService;
	
	
	
	/**
	* @api {post} api/wxwall/updateBGImage 修改微信墙背景图片
	* @apiName wxwall-updateBGImage
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙页面”
	*
	* @apiParam {String} imgSrc * 微信墙背景图片（必须）
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="updateBGImage",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> updateBGImage(
			@RequestParam(value="imgSrc", required=true) String imgSrc
			) {

		return wXWallService.updateBGImage(imgSrc);
	}
	
	
	/**
	* @api {get} api/wxwall/getBGImage 获取微信墙背景图片
	* @apiName wxwall-getBGImage
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙页面”
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "微信墙背景图片",
    *			"type": 0,  //请根据type来分
    *			"imgSrc": "l2333"
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="getBGImage",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<BgImage> getBGImage(
			) {

		return wXWallService.getBGImage();
	}
	
	
	
	
	
	
	
	/**
	* @api {post} api/wxwall/modifyCheckState 设置“是否审核”
	* @apiName wxwall-modifyCheckState
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙管理页面”
	*
	* @apiParam {Integer} state * 是否审核（必须，0-需要审核，1-不审核，默认为0）
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="modifyCheckState",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> modifyCheckState(
			@RequestParam(value="state", required=true) Integer state
			) {

		return wXWallService.modifyCheckState(state);
	}
	
	
	
	/**
	* @api {get} api/wxwall/getCheckState 获取“是否审核”
	* @apiName wxwall-getCheckState
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙管理页面”
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": 1,
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="getCheckState",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Integer> getCheckState(
			) {
		DataWrapper<Integer> dataWrapper = new DataWrapper<Integer>();
		dataWrapper.setData(Parameters.wxWallMessageState);
		return dataWrapper;
	}
	
	
	
	/**
	* @api {post} api/wxwall/publish 发弹幕
	* @apiName wxwall-publish
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙页面”
	*
	* @apiParam {String} mac * 终端的mac（必须）
	* @apiParam {String} content * 弹幕内容（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 7,
    *			"mac": "abc123",
    *			"terminalName": "111",
    *			"state": 0,
    *			"content": "哈哈3"
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="publish",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Message> publish(
			@ModelAttribute Message message
			) {

		return wXWallService.publish(message);
	}
	
	
	/**
	* @api {get} api/wxwall/getMessageList 获取弹幕列表
	* @apiName wxwall-getMessageList
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙页面”
	*
	* @apiParam {Integer} state * 是否审核（非必须，0-需要审核，1-不审核，过滤参数，如果想获取所有的弹幕，则置空；如果想获取待审核的弹幕，则传0）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": [{
    *			"id": 1,
    *			"mac": "abc123",
    *			"terminalName": "111",
    *			"state": 1,
    *			"content": "哈哈"
    *		}],
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="getMessageList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Message>> getMessageList(
			@RequestParam(value="state", required=false) Integer state
			) {

		return wXWallService.getMessageList(state);
	}
	
	
	
	/**
	* @api {get} api/wxwall/message/success 批量审核弹幕
	* @apiName wxwall-check
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙页面”，请用json格式请求
	*
	* @apiParamExample {json} Request-Example:
	* [1, 2, 3, 4]
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"mac": "abc123",
    *			"terminalName": "111",
    *			"state": 1,
    *			"content": "哈哈"
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="message/success",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Message>> successMessage(
			@RequestBody Long[] messageIds
			) {

		return wXWallService.successMessage(messageIds);
	}
	
	
	
	
	/**
	* @api {post} api/wxwall/message/delete 批量删除弹幕
	* @apiName wxwall-delete
	* @apiGroup wxwall
	* @apiDescription 用于“微信墙页面”，请用json格式请求
	*
	* @apiParamExample {json} Request-Example:
	* [1, 2, 3, 4]
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="message/delete",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> delete(
			@RequestBody Long[] messageIds
			) {

		return wXWallService.deleteMessage(messageIds);
	}

}
