package com.adv.utils;

import java.io.File;

public class FileUtil {
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if(file.exists()) 
			file.delete();
		return true;
	}

}
