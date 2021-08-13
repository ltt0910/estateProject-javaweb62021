package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.entity.DistrictEntity;

public interface IDistrictJDBC {
	DistrictEntity getDistrictById(Long districtId);
}
