package com.adv.service;

import java.util.List;

import com.adv.models.Project;
import com.adv.utils.DataWrapper;

public interface ProjectService {
	DataWrapper<Project> add(String name);
	
	DataWrapper<Void> delete(Long projectId);
	
	DataWrapper<List<Project>> getProjectList();
	
	DataWrapper<Project> getById(Long projectId);

}
