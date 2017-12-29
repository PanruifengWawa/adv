package com.adv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.Project;
import com.adv.repository.ProjectRepository;
import com.adv.service.ProjectService;
import com.adv.utils.DataWrapper;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public DataWrapper<Project> add(String name) {
		if (name == null || name.equals("")) {
			throw new MyException("参数为空");
		}
		
		Project project = new Project();
		project.setId(null);
		project.setName(name);
		try {
			projectRepository.save(project);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		DataWrapper<Project> dataWrapper = new DataWrapper<Project>();
		dataWrapper.setData(project);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(Long projectId) {
		
		try {
			projectRepository.deleteByProjectId(projectId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Project>> getProjectList() {
		DataWrapper<List<Project>> dataWrapper = new DataWrapper<List<Project>>();
		List<Project> list = projectRepository.findAll();
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Project> getById(Long projectId) {
		
		DataWrapper<Project> dataWrapper = new DataWrapper<Project>();
		Project project = projectRepository.findOne(projectId);
		dataWrapper.setData(project);
		return dataWrapper;
	}

}
