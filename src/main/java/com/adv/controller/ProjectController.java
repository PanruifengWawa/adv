package com.adv.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.models.Project;
import com.adv.service.ProjectService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	
	/**
	* @api {post} api/project/add 添加轮播项目
	* @apiName project-add
	* @apiGroup project
	* @apiDescription 用于“轮播页面”
	*
	* @apiParam {String} name * 项目名称（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "aa"
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
	public DataWrapper<Project> add(
			@RequestParam(value="name", required=true) String name
			) {

		return projectService.add(name);
	}
	
	/**
	* @api {delete} api/project/delete 删除轮播项目
	* @apiName project-delete
	* @apiGroup project
	* @apiDescription 用于“轮播页面”
	*
	* @apiParam {Long} projectId * 项目id（必须）
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
			@RequestParam(value="projectId", required=true) Long projectId
			) {

		return projectService.delete(projectId);
	}
	
	
	
	/**
	* @api {get} api/project/getProjectList 获取轮播项目列表
	* @apiName project-getProjectList
	* @apiGroup project
	* @apiDescription 用于“轮播页面”
	*
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": [
    *			{
    *				"id": 9,
    *				"name": "轮播7",
    *				"materials": [
    *					{
    *						"id": 9,
    *						"name": "name",
    *						"type": 1,
    *						"time": 1,
    *						"src": "1",
    *						"projectId": 9
    *					}
    *				]
    *			},
    *			{
    *				"id": 9,
    *				"name": "轮播7",
    *				"materials": []
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
	*  		"data": "xxx",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="getProjectList",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Project>> getProjectList(
			) {

		return projectService.getProjectList();
	}
	
	
	/**
	* @api {get} api/project/getById 根据id获取某个轮播项目
	* @apiName project-getById
	* @apiGroup project
	* @apiDescription 用于“轮播页面”
	* @apiParam {Long} projectId * 项目id（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 9,
    *			"name": "轮播7",
    *			"materials": [
    *				{
    *					"id": 9,
    *					"name": "name",
    *					"type": 1,
    *					"time": 1,
    *					"src": "1",
    *					"projectId": 9
    *				}
    *			]
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
	*  		"data": "xxx",
	*  		"numberPerPage": 0,
	*  		"currentPage": 0,
	*  		"totalNumber": 0,
	*  		"totalPage": 0
	*	}
	**/
	@RequestMapping(value="getById",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Project> getById(
			@RequestParam(value="projectId", required=true) Long projectId
			) {

		return projectService.getById(projectId);
	}

}
