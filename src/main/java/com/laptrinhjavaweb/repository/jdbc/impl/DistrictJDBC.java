package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.jdbc.IDistrictJDBC;

public class DistrictJDBC implements IDistrictJDBC {
	public static final String jdbc_driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String user = "root";
	public static final String pass = "123456";

	@Override
	public DistrictEntity getDistrictById(Long districtId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String name = null;
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		DistrictEntity districtEntity = new DistrictEntity();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();
			String queryGetDistrict = "select code,name from district where id = '"+districtId+"'";
			rs = stmt.executeQuery(queryGetDistrict);
			while (rs.next()) {
				districtEntity.setName(rs.getString("name"));
				districtEntity.setCode(rs.getString("code"));
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.getMessage();
			}
		}
		return districtEntity;
	}


}
