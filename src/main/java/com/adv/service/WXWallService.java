package com.adv.service;

import java.util.List;

import com.adv.models.BgImage;
import com.adv.models.Message;
import com.adv.utils.DataWrapper;

public interface WXWallService {
	
	DataWrapper<Void> updateBGImage(String imgSrc);
	
	DataWrapper<Void> modifyCheckState(Integer state);
	
	DataWrapper<Message> publish(Message message);
	
	DataWrapper<List<Message>> getMessageList(Integer state);
	
	DataWrapper<List<Message>> successMessage(Long[] messageIds);

	DataWrapper<BgImage> getBGImage();
	
	DataWrapper<Void> deleteMessage(Long[] messageIds);
}
