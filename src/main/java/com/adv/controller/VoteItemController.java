package com.adv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.adv.models.VoteItem;
import com.adv.models.VoteRecord;
import com.adv.service.VoteItemService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/vote")
public class VoteItemController {
	
	@Autowired
	VoteItemService voteItemService;
	
	
	/**
	* @api {post} api/vote/{voteId}/voteItem/add 添加投票选项
	* @apiName voteItem-add
	* @apiGroup vote-voteItem
	* @apiDescription 用于“投票页面”
	*
	* @apiParam {Long} voteId * 投票项目id（必须，路径参数）
	* @apiParam {String} name * 投票选项名称（必须）
	* @apiParam {String} imgSrc * 投票选项图片路径（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "aa",
    *			"imgSrc": "xxxx",
    *			"result": 0,
    *			"voteId": 10
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
	@RequestMapping(value="{voteId}/voteItem/add",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<VoteItem> add(
			@PathVariable Long voteId,
			@ModelAttribute VoteItem voteItem
			) {

		return voteItemService.add(voteItem, voteId);
	}
	
	
	
	
	/**
	* @api {post} api/vote/{voteId}/voteItem/update 修改投票选项
	* @apiName voteItem-update
	* @apiGroup vote-voteItem
	* @apiDescription 用于“投票页面”
	*
	* @apiParam {Long} voteId * 投票项目id（必须，路径参数）
	* @apiParam {Long} voteItemId * 投票选项id（必须）
	* @apiParam {String} name * 投票选项名称（非必须）
	* @apiParam {String} imgSrc * 投票选项图片路径（非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "aa",
    *			"imgSrc": "xxxx",
    *			"result": 0,
    *			"voteId": 10
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
	@RequestMapping(value="{voteId}/voteItem/update",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<VoteItem> update(
			@PathVariable Long voteId,
			@RequestParam(value="voteItemId", required=true) Long voteItemId,
			@ModelAttribute VoteItem voteItem
			) {

		return voteItemService.update(voteItem, voteItemId);
	}
	
	
	
	/**
	* @api {delete} api/vote/{voteId}/voteItem/delete 删除投票选项
	* @apiName voteItem-delete
	* @apiGroup vote-voteItem
	* @apiDescription 用于“投票页面”
	*
	* @apiParam {Long} voteId * 投票项目id（必须，路径参数）
	* @apiParam {Long} voteItemId * 投票选项id（必须）
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
	*  		"data": "xxx",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="{voteId}/voteItem/delete",method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> delete(
			@PathVariable Long voteId,
			@RequestParam(value="voteItemId", required=true) Long voteItemId
			) {

		return voteItemService.delete(voteItemId);
	}
	
	
	
	/**
	* @api {post} api/vote/{voteId}/voteItem/vote 投票
	* @apiName voteItem-vote
	* @apiGroup vote-voteItem
	* @apiDescription 用于“投票页面”,这个接口的请求用json格式请求
	*
	* @apiParam {Long} voteId * 投票项目id（必须，路径参数）
	* @apiParam {String} mac * 终端mac（必须）
	*
	*  @apiParamExample {json} Request-Example:
	* [
	*	{
	*		"voteItemId" : 2, //投票选项id
	*		"voteNumber" : 1 //票数
	*	},
	*	{
	*		"voteItemId" : 3,
	*		"voteNumber" : 2
	*	}
	* ]
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
	*  		"data": "xxx",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="{voteId}/voteItem/vote",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> vote(
			@PathVariable Long voteId,
			@RequestParam(value="mac", required=true) String mac,
			@RequestBody VoteRecord[] voteRecords
			) {

		return voteItemService.vote(mac, voteId, voteRecords);
	}
	
	/**
	* @api {post} api/vote/refresh 清空所有票数
	* @apiName vote-refresh
	* @apiGroup vote
	* @apiDescription 用于“投票页面”
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
	*  		"data": "xxx",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="/refresh",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> refreshAll(
			) {

		return voteItemService.refreshAll();
	}
	
	
	/**
	* @api {post} api/vote/{voteId}/refresh 清空某个投票项目下所有的投票选项的票数
	* @apiName vote-refreshVoteItem
	* @apiGroup vote
	* @apiDescription 用于“投票页面”
	* @apiParam {Long} voteId * 投票项目id（必须，路径参数）
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
	*  		"data": "xxx",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="{voteId}/refresh",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> refreshVoteItem(
			@PathVariable Long voteId
			) {

		return voteItemService.refreshVoteItem(voteId);
	}

}
