package com.adv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.BgImage;
import com.adv.parameters.Parameters;
import com.adv.repository.BgImageRepository;
import com.adv.service.AnswerService;
import com.adv.utils.DataWrapper;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	BgImageRepository bgImageRepository;

	@Override
	public DataWrapper<Void> updateBGImage(String answerBigBgImageSrc, String answerBgImageSrc) {
		
		if (answerBigBgImageSrc != null && !answerBigBgImageSrc.equals("")) {
			BgImage bigBgImage = bgImageRepository.findByType(Parameters.answerBigBgImageType);
			if (bigBgImage == null) {
				bigBgImage = new BgImage();
				bigBgImage.setId(null);
				bigBgImage.setName("抢答大屏幕背景图片");
				bigBgImage.setType(Parameters.answerBigBgImageType);
			}
			
			bigBgImage.setImgSrc(answerBigBgImageSrc);
			try {
				bgImageRepository.save(bigBgImage);
			} catch (Exception e) {
				throw new MyException("数据库错误");
			}
		}
		
		
		if (answerBgImageSrc != null && !answerBgImageSrc.equals("")) {
			BgImage bgImage = bgImageRepository.findByType(Parameters.answerBgImageType);
			if (bgImage == null) {
				bgImage = new BgImage();
				bgImage.setId(null);
				bgImage.setName("抢答终端背景图片");
				bgImage.setType(Parameters.answerBgImageType);
			}
			bgImage.setImgSrc(answerBgImageSrc);
			try {
				bgImageRepository.save(bgImage);
			} catch (Exception e) {
				throw new MyException("数据库错误");
			}
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<BgImage>> getBGImage() {
		// TODO Auto-generated method stub
		BgImage bigBgImage = bgImageRepository.findByType(Parameters.answerBigBgImageType);
		BgImage bgImage = bgImageRepository.findByType(Parameters.answerBgImageType);
		List<BgImage> list = new ArrayList<>();
		list.add(bgImage);
		list.add(bigBgImage);
		DataWrapper<List<BgImage>> dataWrapper = new DataWrapper<List<BgImage>>();
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
