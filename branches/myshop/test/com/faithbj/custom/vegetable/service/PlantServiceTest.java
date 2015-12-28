package com.faithbj.custom.vegetable.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import com.faithbj.BaseSpringTest;
import com.faithbj.custom.dao.pagination.PaginationInfo;
import com.faithbj.custom.vegetable.entity.Plant;

public class PlantServiceTest extends BaseSpringTest
{
	@Resource
	protected PlantService plantService = null;
	
	public void testCRUD() throws SQLException
	{
		Plant plant = new Plant();
		plant.setId("id1");
		plant.setName("name1");
		
		this.plantService.insertEntity(plant);
		
		plant = this.plantService.selectEntityById(plant);
		
		assertEquals("name1", plant.getName());
		
		plant.setName("name2");
		this.plantService.updateEntity(plant);
		
		plant = this.plantService.selectEntityById(plant);

		assertEquals("name2", plant.getName());
		
		this.plantService.deleteEntity(plant);
		
		plant = this.plantService.selectEntityById(plant);
		
		assertNull(plant);
	}
	
	public void testSelectByCond() throws SQLException
	{
		List<Plant> plantList = this.plantService.selectEntityListByCond(null, new PaginationInfo(3, 2));
		System.out.println(plantList.size());
		for (Plant plant : plantList)
		{
			System.out.println(plant.getId() + ", " + plant.getName());
		}
	}
}
