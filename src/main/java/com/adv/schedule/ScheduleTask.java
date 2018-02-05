package com.adv.schedule;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adv.utils.JSONUtil;
import com.adv.utils.WSMessage;
import com.adv.websocket.TerminalServer;

@Component
public class ScheduleTask {
	@Scheduled(fixedRate=1000 * 120)
	public void sendRateCode() {
		try {
			WSMessage<List<Void>> wsMessage = new WSMessage<List<Void>>();
			wsMessage.setCode(-100);
			String message = JSONUtil.obj2Json(wsMessage);
			TerminalServer.sendMessageToAll(message);
			System.out.println("send message" + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
