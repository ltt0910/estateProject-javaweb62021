package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class BuildingEntity{
	
    private String name;
    private Integer numberOfBasement;
    private Integer floorArea;
    private Integer areaRent;
	private String street;
	private String ward;
	private String district;
	private Integer costRent;
	private String buildingRentType;
	private String nameManager;
	private String phoneManager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getCostRent() {
		return costRent;
	}

	public void setCostRent(Integer costRent) {
		this.costRent = costRent;
	}

	public String getBuildingRentType() {
		return buildingRentType;
	}

	public void setBuildingRentType(String buildingRentType) {
		this.buildingRentType = buildingRentType;
	}

	public String getNameManager() {
		return nameManager;
	}

	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

	public String getPhoneManager() {
		return phoneManager;
	}

	public void setPhoneManager(String phoneManager) {
		this.phoneManager = phoneManager;
	}

	public Integer getAreaRent() {
		return areaRent;
	}

	public void setAreaRent(Integer areaRent) {
		this.areaRent = areaRent;
	}
    
    
}
