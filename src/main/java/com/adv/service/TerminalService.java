package com.adv.service;

import java.util.List;


import com.adv.models.Terminal;
import com.adv.utils.DataWrapper;

public interface TerminalService {
	
	DataWrapper<List<Terminal>> getTerminalList();
	
	DataWrapper<Void> delete(String mac);
	DataWrapper<Terminal> update(String mac, String name);
	
	boolean save(String mac, String name);
}
