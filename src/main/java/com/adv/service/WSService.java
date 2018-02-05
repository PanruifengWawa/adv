package com.adv.service;

import com.adv.utils.DataWrapper;

public interface WSService {
	DataWrapper<Void> breakByMac(String mac);

}
