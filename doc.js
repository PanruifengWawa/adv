/**
	* @api
	* @apiName websocket-api
	* @apiGroup websocket
	* @apiDescription websocket通信
	*
	*
	* @apiParamExample {json} Request-Example:
	* ws://10.60.45.44:8089/adv/terminal?mac=abc123 //终端路由，mac地址作为终端mac地址
	* ws://10.60.45.44:8089/adv/admin               //总控端路由
	* 
	* ##websocket消息格式如下，其中code是指令，data为消息内容
	* ##参与角色：终端、总控端、服务器
	* {
	* 	"code": 0,
	* 	"data": xxxx 
	* }
	* 
	* ##消息-100：心跳消息
	* ##发送方：服务器
	* ##发送时间：每120秒
	* ##接收方：终端
	* ##接收时间：循环接收
	* {
	* 	"code": -100,
	* 	"data": null
	* }
	* 
	* ##消息1：某个终端当前页面的信息转发
	* ##发送方：终端
	* ##发送时间：终端刚刚连接websocket的时候和终端发生页面跳转的时候
	* ##接收方：总控端
	* ##接收时间：当服务器接收到终端的跳转页面信息的时候
	* ##备注：当总控端刚刚连入websocket，希望获取所有终端的信息时，请调用api/terminal/getTerminalList
	* {
	* 	"code": 1,
	* 	"data": {
	* 		"mac": "xxxx",
	* 		"page": 1  // 0-未知页面，1-轮播页面，2-投票页面，3-抢答页面，4-微信墙页面，5-图片展示页面
	* 	} 
	* }
	* 
	* 
	* ##消息2：总控端页面控制
	* ##发送方：总控端
	* ##发送时间：总控端选择并进行页面控制时
	* ##接收方：被总控端选中的终端
	* ##接收时间：任何时间
	* ##备注：记得跳转之后向服务器发送消息1
	* {
	* 	"code": 2,
	* 	"data": {
	* 		"macs": ["xxxx","xxx"], 
	* 		"page": 1, // 0-未知页面，1-轮播页面，2-投票页面，3-抢答页面，4-微信墙页面
	* 		"projectId": 1 //如果是轮播页面，则是轮播项目的id；如果是投票页面，则是投票项目的id；如果是其他页面，则忽略
	* 	}
	* }
	* 
	* 
	* ##消息3：终端名称修改通知
	* ##发送方：服务器
	* ##发送时间：当总控端调用api/terminal/update接口时
	* ##接收方：被修改名称的终端
	* ##接收时间：任何时间
	* {
	* 	"code": 3,
	* 	"data": {
	* 		"mac": "123",
	* 		"name": "aa"
	* 	}
	* }
	* 
	* 
	* ##消息4：投票大屏幕信息更新通知
	* ##发送方：服务器
	* ##发送时间：当终端调用api/vote/{voteId}/voteItem/vote接口时，即发起投票的时候
	* ##接收方：总控端、所有终端(因为服务器不知哪个终端是投票大屏幕，所以群发，不是投票大屏幕的终端忽略本消息)
	* ##接收时间：任何时间
	* {
	* 	"code": 4,
	* 	"data": 1 //voteId,如果投票大屏幕播放的是该投票项目，则刷新选票
	* }
	* 
	* 
	* ##消息5：开始抢答
	* ##发送方：总控端
	* ##发送时间：点击开始抢答的时候
	* ##接收方：所有终端、总控端
	* ##接收时间：任何时间
	* ##备注：开始抢答的时候，之前终端抢答的信息都会被清空,即默认自动调用调用api/answer/refresh
	* {
	* 	"code": 5,
	* 	"data": null
	* }
	* 
	* 
	* ##消息6：结束抢答
	* ##发送方：总控端
	* ##发送时间：点击结束抢答的时候
	* ##接收方：所有终端、总控端
	* ##接收时间：任何时间
	* ##备注：结束抢答后，任何终端点击抢答都不会被记录
	* {
	* 	"code": 6,
	* 	"data": null
	* }
	* 
	* 
	* ##消息7：抢答
	* ##发送方：终端
	* ##发送时间：点击抢答抢答的时候
	* ##接收方：服务器
	* {
	* 	"code": 7,
	* 	"data": null
	* } 
	* 
	* 
	* ##消息8：抢答结果广播
	* ##发送方：服务器
	* ##发送时间：调用api/answer/getAnswerResult接口时
	* ##接收方：所有终端、总控端
	* ##接收时间：任何时间
	* {
	* 	"code": 8,
	* 	"data": {
	* 		"mac": "123",
	* 		"name": "aa"
	* 	}
	* }
	* 
	* 
	* ##消息9：弹幕消息
	* ##发送方：服务器
	* ##发送时间：不需要审核弹幕，当调用api/wxwall/publish接口；需要审核弹幕，当调用api/wxwall/message/success
	* ##接收方：所有终端、总控端
	* ##接收时间：任何时间
	* {
	* 	"code": 9,
	* 	"data": [{
	* 		"id": 1,
	*		"mac": "abc123",
    *		"terminalName": "111",
    *		"state": 1,
    *		"content": "哈哈"
	* 	}]
	* }
	* 
	* 
	* 
	* 
	* 
	* 
	* 
	**/