package com.laptrinhjavaweb.enums;

public  enum BuildingTypesEnum {

        TANG_TRET("Tầng trệt"),
        NGUYEN_CAN("Nguyên căn"),
        NOI_THAT("Nội thất");

        private final String buildingTypes;
        BuildingTypesEnum(String buildingTypes) {
            this.buildingTypes = buildingTypes;
        }

        public String getBuildingTypes() {
            return buildingTypes;
        }
}
