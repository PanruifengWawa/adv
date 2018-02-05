package com.adv.service.impl;

import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.service.WSService;
import com.adv.utils.DataWrapper;
import com.adv.websocket.TerminalServer;

@Service
public class WSServiceImpl implements WSService {

	@Override
	public DataWrapper<Void> breakByMac(String mac) {
		// TODO Auto-generated method stub
		if (mac == null || mac.equals("")) {
			throw new MyException("参数缺失");
		}
		boolean flag = TerminalServer.closeByMac(mac);
		if (!flag) {
			throw new MyException("该设备未连接");
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

}
