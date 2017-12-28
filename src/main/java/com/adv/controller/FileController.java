package com.adv.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.adv.service.FileService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/file")
public class FileController {
	
	@Autowired
	FileService fileService;

	/**
	* @api {post} api/file/material/upload 上传素材
	* @apiName file-material-upload
	* @apiGroup file
	* @apiDescription 用于“轮播管理”页面
	*
	* @apiParam {file} file * 素材文件（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
    *		"status": 0,
    *		"data": "material/45a45b58180dbc99cc6fcafd9c6c2dd3.png", //这是相对路径,前面需要加上htpp://ip:host/adv
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
	@RequestMapping(value="material/upload",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<String> update(
			@RequestParam(value="file", required=true) MultipartFile file,
			 HttpServletRequest request
			
			) {
		return fileService.uploadFile(file, "material", request);
	}
}
