package com.adv.parameters;

import java.util.HashMap;
import java.util.Map;

public class Parameters {
	//微信墙消息默认状态，0-未审核，1-已审核
	public static Integer wxWallMessageState = 0;
	public static Integer wxWallMessageStateFail = 0;
	public static Integer wxWallMessageStateSuccess = 1;
	
	
	public static Integer wxWallBgImageType = 0; //微信墙背景图片
	public static Integer answerBigBgImageType = 1;//抢答大屏幕背景图片
	public static Integer answerBgImageType = 2;//抢答终端背景图片
	
	
	public static Integer canAnswer = 0;//开始抢答
	
	
	//mac-time,抢答存
	public static Map<String, Long> answer = new HashMap<>();

}
