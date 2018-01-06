package com.adv.service;

import java.util.List;

import com.adv.models.BgImage;
import com.adv.models.Terminal;
import com.adv.utils.DataWrapper;

public interface AnswerService {
	
	DataWrapper<Void> updateBGImage(String answerBigBgImageSrc, String answerBgImageSrc);
	
	DataWrapper<List<BgImage>> getBGImage();
	
	DataWrapper<Void> refresh();
	
	DataWrapper<Terminal> getAnswerResult();

}
