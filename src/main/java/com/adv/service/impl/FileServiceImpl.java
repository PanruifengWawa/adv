package com.adv.service.impl;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.adv.exceptions.MyException;
import com.adv.service.FileService;
import com.adv.utils.DataWrapper;
import com.adv.utils.MD5Util;



@Service
public class FileServiceImpl implements FileService {

	@Override
	public DataWrapper<String> uploadFile(MultipartFile file, String dirName, HttpServletRequest request) {
		
		 
		if (file == null) {
			throw new MyException("文件为空");
		}
		
		
		String filePath = request.getSession().getServletContext().getRealPath("/") + dirName;
        String fileName = MD5Util.getMD5String(file.getOriginalFilename() + new Date() + UUID.randomUUID().toString()).replace(".","")
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(filePath + "/" + fileName);
        try {
        	 File fileDir = new File(filePath);
             if (!fileDir.exists()) {
                 fileDir.mkdirs();
             }
        	FileCopyUtils.copy(file.getBytes(), new File(filePath + "/" + fileName) );
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("保存失败");
		}
        
		
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setData(dirName + "/" + fileName);
		return dataWrapper;
		
	
	}

}
