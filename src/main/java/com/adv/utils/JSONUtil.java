package com.adv.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	
	public static String obj2Json(Object obj) {
		String json =null;
		try {
			ObjectMapper mapper = new ObjectMapper();  
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
