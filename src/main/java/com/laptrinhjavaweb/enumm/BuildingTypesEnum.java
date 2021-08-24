package com.laptrinhjavaweb.enumm;

public  enum BuildingTypesEnum {

        tang_tret("Tầng trệt"),
        nguyen_can("Nguyên căn"),
        noi_that("Nội thất");

        private final String buildingTypes;
        BuildingTypesEnum(String buildingTypes) {
            this.buildingTypes = buildingTypes;
        }

        public String getBuildingTypes() {
            return buildingTypes;
        }
}
