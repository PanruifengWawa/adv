package com.adv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.models.Material;
import com.adv.service.MaterialService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/project")
public class ProjectMaterialController {
	
	@Autowired
	MaterialService materialService;
	
	
	
	/**
	* @api {post} api/project/{projectId}/material/add 添加素材
	* @apiName material-add
	* @apiGroup project-material
	* @apiDescription 用于“轮播页面”
	*
	* @apiParam {Long} projectId * 所属项目id（必须，在路径中）
	* @apiParam {String} name * 素材名称（必须）
	* @apiParam {int} type * 素材类型（必须,0-图片，1-gif，2-视频）
	* @apiParam {int} time * 素材时长（必须）
	* @apiParam {String} src * 文件路径（必须，先调用“上传素材”接口，获取相对路径，相对路径的值即是这里的值）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "素材1",
    *			"type": 0,
    *			"time": 20,
    *			"src": "xxxx",
    *			"projectId": 11
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
	@RequestMapping(value="{projectId}/material/add",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Material> add(
			@PathVariable Long projectId,
			@ModelAttribute Material material
			) {

		return materialService.add(material, projectId);
	}
	
	
	/**
	* @api {delete} api/project/{projectId}/material/delete 删除素材
	* @apiName material-delete
	* @apiGroup project-material
	* @apiDescription 用于“轮播页面”
	*
	* @apiParam {Long} projectId * 所属项目id（必须，在路径中）
	* @apiParam {Long} materialId * 素材id（必须）
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
	@RequestMapping(value="{projectId}/material/delete",method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> delete(
			@RequestParam(value="materialId", required=true) Long materialId
			) {

		return materialService.delete(materialId);
	}
	
	
	
	/**
	* @api {post} api/project/{projectId}/material/updateTime 修改素材时长
	* @apiName material-updateTime
	* @apiGroup project-material
	* @apiDescription 用于“轮播页面”
	*
	* @apiParam {Long} projectId * 所属项目id（必须，在路径中）
	* @apiParam {Long} materialId * 素材id（必须）
	* @apiParam {int} time * 素材时长（必须）
	* 
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": {
    *			"id": 1,
    *			"name": "素材1",
    *			"type": 0,
    *			"time": 20,
    *			"src": "xxxx",
    *			"projectId": 11
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
	@RequestMapping(value="{projectId}/material/updateTime",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Material> update(
			@RequestParam(value="materialId", required=true) Long materialId,
			@RequestParam(value="time", required=true) Integer time
			) {

		return materialService.updateTime(time, materialId);
	}

}


