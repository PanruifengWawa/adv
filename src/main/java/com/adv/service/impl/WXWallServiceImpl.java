package com.adv.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.BgImage;
import com.adv.models.Message;
import com.adv.models.Terminal;
import com.adv.parameters.Parameters;
import com.adv.repository.BgImageRepository;
import com.adv.repository.MessageRepository;
import com.adv.repository.TerminalRepository;
import com.adv.service.WXWallService;
import com.adv.utils.DataWrapper;

@Service
public class WXWallServiceImpl implements WXWallService {
	
	@Autowired
	BgImageRepository bgImageRepository;
	
	@Autowired
	TerminalRepository terminalRepository;
	
	@Autowired
	MessageRepository messageRepository;

	@Override
	public DataWrapper<Void> updateBGImage(String imgSrc) {
		BgImage bgImage = bgImageRepository.findByType(Parameters.wxWallBgImageType);
		if (bgImage == null) {
			bgImage = new BgImage();
			bgImage.setId(null);
			bgImage.setName("微信墙背景图片");
			bgImage.setType(Parameters.wxWallBgImageType);
		}
		
		bgImage.setImgSrc(imgSrc);
		try {
			bgImageRepository.save(bgImage);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> modifyCheckState(Integer state) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if (state == null) {
			throw new MyException("缺少参数");
		}
		if (state == 0 || state == 1) {
			Parameters.wxWallMessageState = state;
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Message> publish(Message message) {
		if (message.getMac() == null || message.getMac().equals("") 
				|| message.getContent() == null || message.getContent().equals("")) {
			throw new MyException("缺少参数");
		}
		Terminal terminal = terminalRepository.findByMac(message.getMac());
		if (terminal == null) {
			throw new MyException("终端不存在");
		}
		message.setId(null);
		message.setTerminalName(terminal.getName());
		message.setState(Parameters.wxWallMessageState);
		
		try {
			messageRepository.save(message);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		DataWrapper<Message> dataWrapper = new DataWrapper<Message>();
		dataWrapper.setData(message);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Message>> getMessageList(Integer state) {
		List<Message> list = null;
		if (state != null) {
			list = messageRepository.findByStateOrderByIdDesc(state);
		} else {
			list = messageRepository.findAllByOrderByIdDesc();
		}
		DataWrapper<List<Message>> dataWrapper = new DataWrapper<List<Message>>();
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Message>> successMessage(Long[] messageIds) {
		List<Message> list = messageRepository.findByIdInAndState(Arrays.asList(messageIds), Parameters.wxWallMessageStateFail);
		for(Message message: list) {
			message.setState(Parameters.wxWallMessageStateSuccess);
		}
		try {
			messageRepository.save(list);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		
		DataWrapper<List<Message>> dataWrapper = new DataWrapper<List<Message>>();
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<BgImage> getBGImage() {
		// TODO Auto-generated method stub
		BgImage bgImage = bgImageRepository.findByType(Parameters.wxWallBgImageType);
		DataWrapper<BgImage> dataWrapper = new DataWrapper<BgImage>();
		dataWrapper.setData(bgImage);
		return dataWrapper;
	}

}
