package com.adv.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.adv.utils.DataWrapper;

public interface FileService {
	
	DataWrapper<String> uploadFile(MultipartFile file, String dirName, HttpServletRequest request);

}
