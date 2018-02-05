package com.adv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.Terminal;
import com.adv.repository.TerminalRepository;
import com.adv.service.TerminalService;
import com.adv.utils.DataWrapper;
import com.adv.utils.JSONUtil;
import com.adv.utils.WSMessage;
import com.adv.websocket.TerminalServer;

@Service
public class TerminalServiceImpl implements TerminalService {
	
	@Autowired
	TerminalRepository terminalRepository;

	@Override
	public DataWrapper<List<Terminal>> getTerminalList() {
		DataWrapper<List<Terminal>> dataWrapper = new DataWrapper<List<Terminal>>();
		Sort sort = new Sort(Direction.ASC, "id");

		List<Terminal> list = terminalRepository.findAll(sort);
		for(Terminal terminal: list) {
			TerminalServer.getTerminalState(terminal);
		}
		
		
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
		
		Terminal terminal = terminalRepository.findByMac(mac);
		if (terminal != null) {
			return true;
		}
		terminal = new Terminal();
		terminal.setMac(mac);
		terminal.setName(name);
		terminal.setSrc("");
		terminal.setId(null);
		try {
			terminalRepository.save(terminal);
		} catch (Exception e) {
			return false;
		}

		return true;
	}



	@Override
	public DataWrapper<Terminal> update(String mac, String name, String src) {
		Terminal terminal = terminalRepository.findByMac(mac);
		if (terminal == null) {
			throw new MyException("终端不存在");
		}
		if (name != null && !name.equals("")) {
			terminal.setName(name);
		}
		if (src != null && !src.equals("")) {
			terminal.setSrc(src);
		}
		try {
			terminalRepository.save(terminal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		
		try {
			WSMessage<Terminal> wsMessage = new WSMessage<Terminal>();
			wsMessage.setCode(3);
			wsMessage.setData(terminal);
			TerminalServer.sendMessageToOne(mac, JSONUtil.obj2Json(wsMessage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataWrapper<Terminal> dataWrapper = new DataWrapper<Terminal>();
		dataWrapper.setData(terminal);
		return dataWrapper;
	}



	@Override
	public DataWrapper<Terminal> getByMac(String mac) {
		// TODO Auto-generated method stub
		Terminal terminal = terminalRepository.findByMac(mac);
		DataWrapper<Terminal> dataWrapper = new DataWrapper<Terminal>();
		dataWrapper.setData(terminal);
		
		return dataWrapper;
	}
	
	

}
