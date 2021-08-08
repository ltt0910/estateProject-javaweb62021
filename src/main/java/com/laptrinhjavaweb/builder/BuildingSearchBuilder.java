package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	
	private String name;
	private String district;
	private String buildingArea;
	private String street;
	private String ward;
	private Integer numberOfBasement;
	private String[] buildingTypes = new String[] {};
	private Integer costRentFrom;
	private Integer costRentTo;
	private Integer areaRentFrom;
	private Integer areaRentTo;
	private Long staffId;
	private String managerName;
	private String managerPhone;
	
	public String getName() {
		return name;
	}

	public String getDistrict() {
		return district;
	}
	
	public String getBuildingArea() {
		return buildingArea;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	
	public String getStreet() {
		return street;
	}

	public String getWard() {
		return ward;
	}
	
	public String[] getBuildingTypes() {
		return buildingTypes;
	}
	
	public Integer getCostRentFrom() {
		return costRentFrom;
	}

	public Integer getCostRentTo() {
		return costRentTo;
	}

	public Integer getAreaRentFrom() {
		return areaRentFrom;
	}

	public Integer getAreaRentTo() {
		return areaRentTo;
	}
	
	public Long getStaffId() {
		return staffId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public void setBuildingTypes(String[] buildingTypes) {
		this.buildingTypes = buildingTypes;
	}

	public void setCostRentFrom(Integer costRentFrom) {
		this.costRentFrom = costRentFrom;
	}

	public void setCostRentTo(Integer costRentTo) {
		this.costRentTo = costRentTo;
	}

	public void setAreaRentFrom(Integer areaRentFrom) {
		this.areaRentFrom = areaRentFrom;
	}

	public void setAreaRentTo(Integer areaRentTo) {
		this.areaRentTo = areaRentTo;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	
	
}
