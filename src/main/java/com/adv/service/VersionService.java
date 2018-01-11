package com.adv.service;

import java.util.List;

import com.adv.models.Version;
import com.adv.utils.DataWrapper;

public interface VersionService {
	DataWrapper<List<Version>> getVersionList();
	
	DataWrapper<Version> add(Version version);
	
	DataWrapper<Void> delete(Long id);
	
	DataWrapper<Version> getNewestVersion();
}
