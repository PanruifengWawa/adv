package com.adv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.exceptions.MyException;
import com.adv.models.Material;
import com.adv.models.Project;
import com.adv.repository.MaterialRepository;
import com.adv.repository.ProjectRepository;
import com.adv.service.MaterialService;
import com.adv.utils.DataWrapper;

@Service
public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	MaterialRepository materialRepository;
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public DataWrapper<Material> add(Material material, Long projectId) {
		
		if (material.getName() == null || material.getName().equals("") 
				|| material.getType() == null 
				|| material.getTime() == null || material.getTime() < 0
				|| material.getSrc() == null || material.getSrc().equals("")) {
			throw new MyException("参数错误");
		}
		
		Project project = projectRepository.findOne(projectId);
		if (project == null) {
			throw new MyException("项目不存在");
		}
		material.setId(null);
		material.setProjectId(projectId);
		try {
			materialRepository.save(material);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		DataWrapper<Material> dataWrapper = new DataWrapper<Material>();
		dataWrapper.setData(material);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(Long materialId) {
		if (materialId == null) {
			throw new MyException("参数为空");
		}
		Material material = materialRepository.findOne(materialId);
		if (material == null) {
			throw new MyException("素材不存在");
		}
		try {
			materialRepository.delete(material);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<Material> updateTime(Integer time, Long materialId) {
		// TODO Auto-generated method stub
		if (materialId == null || time == null || time < 0) {
			throw new MyException("参数错误");
		}
		
		Material material = materialRepository.findOne(materialId);
		if (material == null) {
			throw new MyException("素材不存在");
		}
		material.setTime(time);
		
		try {
			materialRepository.save(material);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库错误");
		}
		
		DataWrapper<Material> dataWrapper = new DataWrapper<Material>();
		dataWrapper.setData(material);
		return dataWrapper;
	}

}
