package com.adv.exceptions.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adv.exceptions.MyException;
import com.adv.utils.DataWrapper;



@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(RuntimeException ex) {
		ex.printStackTrace();
        return getReturns(1, ex.getMessage());
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MyException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(MyException ex) {  
		return getReturns(1, ex.getMessage());
    }
	

	public DataWrapper<String> getReturns(int status, String message) {
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setStatus(status);
		dataWrapper.setData(message);
        return dataWrapper;
	}

}
