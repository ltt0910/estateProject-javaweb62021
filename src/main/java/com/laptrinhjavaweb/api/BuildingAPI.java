package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	
	private IBuildingService buildingService = new BuildingService();
	
	@GetMapping
	public List<BuildingDTO> findBy(@RequestParam(value = "name",required = false) String name,
									@RequestParam(value = "numberofbasement",required = false)Integer numberofbasement,
									@RequestParam(value = "district",required = false)String district,
									@RequestParam(value = "areaRentFrom",required = false)Integer areaRentFrom,
									@RequestParam(value = "areaRentTo",required = false)Integer areaRentTo,
									@RequestParam(value = "types",required = false)String [] types,
									@RequestParam(value = "costRentFrom",required = false)Integer costRentFrom,
									@RequestParam(value = "costRentTo",required = false)Integer costRentTo,
									@RequestParam(value = "ward",required = false)String ward,
									@RequestParam(value = "street",required = false)String street,
									@RequestParam(value = "managerName",required = false)String managerName,
									@RequestParam(value = "managerPhone",required = false)String managerPhone,
									@RequestParam(value = "staffId",required = false)Long staffId){
		BuildingDTO searchBuilding = new BuildingDTO();
		searchBuilding.setName(name);
		searchBuilding.setNumberOfBasement(numberofbasement);
		searchBuilding.setAreaRentFrom(areaRentFrom);
		searchBuilding.setAreaRentTo(areaRentTo);
		searchBuilding.setCostRentFrom(costRentFrom);
		searchBuilding.setCostRentTo(costRentTo);
		searchBuilding.setDistrict(district);
		searchBuilding.setBuildingTypes(types);
		searchBuilding.setWard(ward);
		searchBuilding.setStreet(street);
		searchBuilding.setManagerName(managerName);
		searchBuilding.setManagerPhone(managerPhone);
		searchBuilding.setStaffId(staffId);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		result = buildingService.findAll(searchBuilding);
		return result;
	}
	

}
