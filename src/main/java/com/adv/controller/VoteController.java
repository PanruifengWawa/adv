package com.adv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.models.Vote;
import com.adv.service.VoteService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/vote")
public class VoteController {
	
	@Autowired
	VoteService voteService;

	
	/**
	* @api {get} api/vote/getVoteList 获取投票项目列表
	* @apiName vote-getVoteList
	* @apiGroup vote
	* @apiDescription 用于“投票页面”
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": [
    *			{
    *				"id": 1,
    *				"name": "aa",
    *				"allCanVoteNumber": 10,
    *				"eachItemCanVoteNumber": 10,
    *				"voteItems": []
    *			},
    *			{
    *				"id": 1,
    *				"name": "aa",
    *				"allCanVoteNumber": 10,
    *				"eachItemCanVoteNumber": 10,
    *				"voteItems": [{
    *					"id": 1,
    *					"name": "2号",
    *					"imgSrc": "aaa",
    *					"result": 0,
    *					"voteId": 2
    *				}]
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
	@RequestMapping(value="getVoteList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Vote>> getVoteList() {

		return voteService.getVoteList();
	}
	
	
	/**
	* @api {get} api/vote/getById 获取某个投票项目
	* @apiName vote-getById
	* @apiGroup vote
	* @apiDescription 用于“投票页面”
	* @apiParam {Long} voteId * 投票项目id（必须）
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *				"id": 1,
    *				"name": "aa",
    *				"allCanVoteNumber": 10,
    *				"eachItemCanVoteNumber": 10,
    *				"voteItems": [{
    *					"id": 1,
    *					"name": "2号",
    *					"imgSrc": "aaa",
    *					"result": 0,
    *					"voteId": 2
    *				}]
    *			
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
	@RequestMapping(value="getById",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Vote> getById(
			@RequestParam(value="voteId", required=true) Long voteId
			) {

		return voteService.getById(voteId);
	}
	
	
	/**
	* @api {post} api/vote/add 添加投票项目
	* @apiName vote-add
	* @apiGroup vote
	* @apiDescription 用于“投票页面”
	*
	* @apiParam {String} name * 投票项目名称（必须）
	* @apiParam {Integer} allCanVoteNumber * 终端可投总票数（必须）
	* @apiParam {Integer} eachItemCanVoteNumber * 终端每项最多可投票数（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "aa",
    *			"allCanVoteNumber": 10,
    *			"eachItemCanVoteNumber": 10
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
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Vote> add(
			@ModelAttribute Vote vote
			) {

		return voteService.add(vote);
	}
	
	
	/**
	* @api {delete} api/vote/delete 删除投票项目
	* @apiName vote-delete
	* @apiGroup vote
	* @apiDescription 用于“投票页面”
	*
	* @apiParam {Long} voteId * 投票项目id（必须）
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
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> delete(
			@RequestParam(value="voteId", required=true) Long voteId
			) {

		return voteService.delete(voteId);
	}
	
	
	
	
	
	/**
	* @api {post} api/vote/update 修改投票项目
	* @apiName vote-update
	* @apiGroup vote
	* @apiDescription 用于“投票页面”
	*
	* @apiParam {Long} voteId * 投票项目id（必须）
	* @apiParam {Integer} allCanVoteNumber * 终端可投总票数（非必须）
	* @apiParam {Integer} eachItemCanVoteNumber * 终端每项最多可投票数（非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "aa",
    *			"allCanVoteNumber": 10,
    *			"eachItemCanVoteNumber": 10
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
	@RequestMapping(value="update",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Vote> update(
			@RequestParam(value="voteId", required=true) Long voteId,
			@ModelAttribute Vote vote
			) {

		return voteService.update(vote, voteId);
	}
}
