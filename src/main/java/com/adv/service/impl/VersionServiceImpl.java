package com.adv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.Version;
import com.adv.repository.VersionRepository;
import com.adv.service.VersionService;
import com.adv.utils.DataWrapper;

@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	VersionRepository versionRepository;

	@Override
	public DataWrapper<List<Version>> getVersionList() {
		// TODO Auto-generated method stub
		DataWrapper<List<Version>> dataWrapper = new DataWrapper<List<Version>>();
		List<Version> list = versionRepository.findAll();
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Version> add(Version version) {
		if (version.getVesionId() == null || version.getVesionId().equals("")
				|| version.getSrc() == null || version.getSrc().equals("")
				|| version.getAppName() == null || version.getAppName().equals("")) {
			throw new MyException("参数缺少");
		}
		
		
		version.setId(null);
		
		try {
			versionRepository.save(version);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Version> dataWrapper = new DataWrapper<Version>();
		dataWrapper.setData(version);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(Long id) {
		// TODO Auto-generated method stub
		Version version = versionRepository.findOne(id);
		if (version == null) {
			throw new MyException("版本不存在");
		}
		
		try {
			versionRepository.delete(version);
		} catch (Exception e) {
			throw new MyException("数据库错误");
		}
		 DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Version> getNewestVersion() {
		List<Version> versions = versionRepository.findAllByOrderByIdDesc();
		Version version = null;
		if (versions != null && versions.size() > 0) {
			version = versions.get(0);
		}
		DataWrapper<Version>  dataWrapper = new DataWrapper<Version>();
		dataWrapper.setData(version);
		return dataWrapper;
	}

}
