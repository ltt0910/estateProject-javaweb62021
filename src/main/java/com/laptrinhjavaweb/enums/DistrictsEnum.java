package com.laptrinhjavaweb.enums;

public enum DistrictsEnum {

    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4");
    private final String districtValue;

    DistrictsEnum(String districtValue) {
        this.districtValue = districtValue;
    }

    public String getDistrictValue() {
        return districtValue;
    }
  public static String existDistrict(String districtCode){
      for (DistrictsEnum item:DistrictsEnum.values()) {
            if(item.toString().equals(districtCode)){
                return item.getDistrictValue();
            }
      }
      return null;
  }
}