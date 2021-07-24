package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.IBuildingJDBC;

public class BuildingJDBC implements IBuildingJDBC {
	public static final String jdbc_driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String user = "root";
	public static final String pass = "123456";
	@Override
	public List<BuildingEntity> findAll(BuildingDTO searchBuilding) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> result = new ArrayList<>();
		try {
			int a = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();
			StringBuilder sql1 = new StringBuilder("select b.name as name"
					+ ",b.street as street,b.ward as ward,b.managername as namemanager,b.managerphone as phonemanager"
					+ ",b.numberofbasement as numberofbasement,b.floorarea as floorarea" + ",b.rentprice as rentprice");
			StringBuilder sql2 = new StringBuilder(" from building as b");
			StringBuilder sql3 = new StringBuilder(" where 1=1");

			if (searchBuilding.getName() != null && searchBuilding.getName() != "") {
				sql3.append(" and b.name like '%" + searchBuilding.getName() + "%'");
			}
			
			if (searchBuilding.getNumberOfBasement() != null) {
				sql3.append(" and b.numberofbasement =" + searchBuilding.getNumberOfBasement() + "");
			}
			if (searchBuilding.getFloorArea() != null) {
				sql3.append(" and b.floorarea =" + searchBuilding.getFloorArea() + "");
			}
			
			if (searchBuilding.getCostRentFrom() != null) {
				sql3.append(" and b.rentprice >= '" + searchBuilding.getCostRentFrom() + "'");
			}
			if (searchBuilding.getCostRentTo() != null) {
				sql3.append(" and b.rentprice <= '" + searchBuilding.getCostRentTo() + "'");
			}
			if (searchBuilding.getStreet() != null && searchBuilding.getStreet() != "") {
				sql3.append(" and b.street like '%" + searchBuilding.getStreet() + "%'");
			}
			if (searchBuilding.getWard() != null && searchBuilding.getWard() != "") {
				sql3.append(" and b.ward like '%" + searchBuilding.getWard() + "%'");
			}
			if (searchBuilding.getManagerName() != null && searchBuilding.getManagerName() != "") {
				sql3.append(" and b.managername like '%" + searchBuilding.getManagerName() + "%'");
			}
			
			if (searchBuilding.getManagerPhone() != null && searchBuilding.getManagerPhone() != "") {
				sql3.append(" and b.managerphone like '%" + searchBuilding.getManagerPhone() + "%'");
			}
			if (searchBuilding.getAreaRentFrom() != null || searchBuilding.getAreaRentTo() != null) {
				sql3.append(" and EXISTS (SELECT rentarea.value FROM rentarea WHERE 1 =1");
				if (searchBuilding.getAreaRentFrom() != null) {
					sql3.append(" and rentarea.value >= " + searchBuilding.getAreaRentFrom() + "");
				}
				if (searchBuilding.getAreaRentTo() != null) {
					sql3.append(" and rentarea.value <= " + searchBuilding.getAreaRentTo() + "");
				}
				sql3.append(")");
			}
			if (searchBuilding.getDistrict() != null && searchBuilding.getDistrict()!="") {
				sql1.append(" ,district.name as district");
				sql2.append(" inner join district on district.id=b.districtid");
				sql3.append(" and district.code = '" + searchBuilding.getDistrict() + "'");
			}
			
			if (searchBuilding.getCostRentFrom() != null) {
				sql3.append(" and b.rentprice >= '" + searchBuilding.getCostRentFrom() + "'");
			}
			if (searchBuilding.getCostRentTo() != null) {
				sql3.append(" and b.rentprice <= '" + searchBuilding.getCostRentTo() + "'");
			}

			if (searchBuilding.getBuildingTypes().length > 0) {
				int i = 0;
				sql1.append(",renttype.name as category");
				sql2.append(" inner join buildingrenttype on b.id=buildingrenttype.buildingid\r\n"
						+ "inner join renttype on buildingrenttype.renttypeid=renttype.id");
				sql3.append(" and(");
				for (String item : searchBuilding.getBuildingTypes()) {
					searchBuilding.getBuildingTypes()[i] = " renttype.code = '" + item + "'";
					i++;
				}
				String newSql = String.join(" OR ", searchBuilding.getBuildingTypes());
				sql3.append(newSql);
				sql3.append(")");
			}

			if (searchBuilding.getStaffId() != null) {
				sql2.append(" inner join assignmentbuilding on assignmentbuilding.buildingid=b.id ");
				sql3.append(" and assignmentbuilding.staffid = " + searchBuilding.getStaffId() + "");
			}
			sql3.append(" group by b.id");
			String sql = (sql1.toString() + sql2.toString() + sql3.toString());
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setFloorArea(rs.getInt("floorarea"));
				buildingEntity.setCostRent(rs.getInt("rentprice"));
				buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
				if(searchBuilding.getDistrict()!="") {
					buildingEntity.setDistrict(rs.getString("district"));
				}
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setNameManager(rs.getString("namemanager"));
				buildingEntity.setPhoneManager(rs.getString("phonemanager"));
				for (int i = 0; i < searchBuilding.getBuildingTypes().length; i++) {
					if (searchBuilding.getBuildingTypes()[i] != null) {
						buildingEntity.setBuildingRentType(rs.getString("category"));
					}
				}
				result.add(buildingEntity);
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
		return result;
	}

}
