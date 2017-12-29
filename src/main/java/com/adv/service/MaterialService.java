package com.adv.service;

import com.adv.models.Material;
import com.adv.utils.DataWrapper;

public interface MaterialService {
	DataWrapper<Material> add(Material material, Long projectId);
	
	DataWrapper<Void> delete(Long materialId);
	
	DataWrapper<Material> updateTime(Integer time, Long materialId);

}
