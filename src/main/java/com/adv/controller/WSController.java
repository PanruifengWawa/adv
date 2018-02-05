package com.adv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.service.WSService;
import com.adv.utils.DataWrapper;

@Controller
@RequestMapping(value="/api/ws")
public class WSController {

	@Autowired
	WSService wsService;
	
	/**
	* @api {GET} api/ws/break 断开某个终端
	* @apiName websocket-break
	* @apiGroup websocket
	*
	* @apiParam {Long} mac * mac地址（必须）
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
	@RequestMapping(value="break",method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Void> breakByMac(
			@RequestParam(value="mac", required=true) String mac
			) {

		return wsService.breakByMac(mac);
	}
}
