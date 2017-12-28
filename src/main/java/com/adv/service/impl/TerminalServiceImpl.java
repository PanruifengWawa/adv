package com.adv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.Terminal;
import com.adv.repository.TerminalRepository;
import com.adv.service.TerminalService;
import com.adv.utils.DataWrapper;
import com.adv.websocket.TerminalServer;

@Service
public class TerminalServiceImpl implements TerminalService {
	
	@Autowired
	TerminalRepository terminalRepository;

	@Override
	public DataWrapper<List<Terminal>> getTerminalList() {
		DataWrapper<List<Terminal>> dataWrapper = new DataWrapper<List<Terminal>>();
		List<Terminal> list = terminalRepository.findAll();
		dataWrapper.setData(list);
		return dataWrapper;
	}



	@Override
	public DataWrapper<Void> delete(String mac) {
		Terminal terminal = terminalRepository.findByMac(mac);
		if (terminal == null) {
			throw new MyException("终端不存在");
		}
		
		try {
			terminalRepository.delete(terminal);
			TerminalServer.removeTerminal(mac);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}
	
	
	
	@Override
	public boolean save(String mac, String name) {
		
		Terminal terminal = new Terminal();
		terminal.setMac(mac);
		terminal.setName(name);
		try {
			terminalRepository.save(terminal);
		} catch (Exception e) {
			return false;
		}

		return true;
	}



	@Override
	public DataWrapper<Terminal> update(String mac, String name) {
		Terminal terminal = terminalRepository.findByMac(mac);
		if (terminal == null) {
			throw new MyException("终端不存在");
		}
		terminal.setName(name);
		try {
			terminalRepository.save(terminal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		DataWrapper<Terminal> dataWrapper = new DataWrapper<Terminal>();
		dataWrapper.setData(terminal);
		return dataWrapper;
	}
	
	

}
