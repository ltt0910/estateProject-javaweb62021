package com.laptrinhjavaweb.enumm;

public enum DistrictsEnum {

    Q1("Quận 1"),
    Q2("Quận 2"),
    Q3("Quận 3"),
    Q4("Quận 4");
    private final String districtValue;

    DistrictsEnum(String districtValue) {
        this.districtValue = districtValue;
    }

    public String getDistrictValue() {
        return districtValue;
    }
}