package com.adv.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.models.BgImage;
import com.adv.models.Terminal;
import com.adv.service.AnswerService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/answer")
public class AnswerController {
	
	@Autowired
	AnswerService answerService;
	
	
	/**
	* @api {post} api/answer/updateBGImage 修改抢答背景图片
	* @apiName answer-updateBGImage
	* @apiGroup answer
	* @apiDescription 用于“抢答管理页面”
	*
	* @apiParam {String} answerBigBgImageSrc * 抢答大屏幕背景图片（非必须）
	* @apiParam {String} answerBgImageSrc * 抢答终端背景图片（非必须）
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
			@RequestParam(value="answerBigBgImageSrc", required=false) String answerBigBgImageSrc,
			@RequestParam(value="answerBgImageSrc", required=false) String answerBgImageSrc
			
			) {
		return answerService.updateBGImage(answerBigBgImageSrc, answerBgImageSrc);
	}
	
	
	
	/**
	* @api {get} api/answer/getBGImage 获取抢答背景图片
	* @apiName answer-getBGImage
	* @apiGroup answer
	* @apiDescription 用于“抢答管理页面”
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": [
    *			{
    *				"id": 3,
    *				"name": "抢答终端背景图片",
    *				"type": 2,  //请根据type来分
    *				"imgSrc": "l2333"
    *			},
    *			{
    *				"id": 2,
    *				"name": "抢答大屏幕背景图片",
    *				"type": 1,
 	*				"imgSrc": "lllaaa"
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
	*  		"data": "参数为空",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="getBGImage",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<BgImage>> getBGImage(
			) {
		return answerService.getBGImage();
	}
	
	
	
	/**
	* @api {post} api/answer/refresh 刷新抢答
	* @apiName answer-refresh
	* @apiGroup answer
	* @apiDescription 用于“抢答管理页面”
	*
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
	@RequestMapping(value="refresh",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> refresh(
			) {
		return answerService.refresh();
	}
	
	
	/**
	* @api {post} api/answer/getAnswerResult 获取抢答结果
	* @apiName answer-getAnswerResult
	* @apiGroup answer
	* @apiDescription 用于“抢答管理”页面
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *				"mac": "123",
    *				"name": "aa"
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
	@RequestMapping(value="getAnswerResult",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Terminal> getAnswerResult(
			) {
		return answerService.getAnswerResult();
	}
	
	
	

}
