package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.IBuildingJDBC;

public class BuildingJDBC implements IBuildingJDBC {
	public static final String jdbc_driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String user = "root";
	public static final String pass = "123456";
	
	public String getDistrictById(Long districtId) {
		String districtName = null;
		Connection conn = null; 
		Statement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();
			String queryGetDistrict = "select name from district where id = "+districtId+"";
			rs = stmt.executeQuery(queryGetDistrict);
			while(rs.next()) {
				districtName = rs.getString("name");
			}
		}catch (Exception e) {
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
		return districtName;
			
		
	}

	@Override
	public List<BuildingEntity> findListBuilding(HashMap<String, Object> mapBuilding) {
		Connection conn = null; 
		Statement stmt = null;
		ResultSet rs = null;
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();
			StringBuilder sql1 = new StringBuilder("select b.name as name"
					+ ",b.street as street,b.ward as ward,district.id as districtid,b.servicefee as servicefee,b.brokeragefee as brokeragefee"
					+ ",b.managername as namemanager,b.managerphone as phonemanager,b.createddate as createDate"
					+ ",b.numberofbasement as numberofbasement,b.floorarea as floorarea" + ",b.rentprice as rentprice");
			StringBuilder sql2 = new StringBuilder(" from building as b inner join district on district.id=b.districtid ");
			StringBuilder sql3 = new StringBuilder(" where 1=1");

			if (mapBuilding.get("name") != null && mapBuilding.get("name") != "") {
				sql3.append(" and b.name like '%" + mapBuilding.get("name") + "%'");
			}
			
			if (mapBuilding.get("numberOfBasement")!= null) {
				sql3.append(" and b.numberofbasement =" + mapBuilding.get("numberOfBasement") + "");
			}
			
			if (mapBuilding.get("costRentFrom")!= null) {
				sql3.append(" and b.rentprice >= '" + mapBuilding.get("costRentFrom")+ "'");
			}
			if (mapBuilding.get("costRentTo") != null) {
				sql3.append(" and b.rentprice <= '" + mapBuilding.get("costRentTo") + "'");
			}
			if (mapBuilding.get("street") != null && mapBuilding.get("street")  != "") {
				sql3.append(" and b.street like '%" + mapBuilding.get("street")  + "%'");
			}
			if (mapBuilding.get("ward") != null && mapBuilding.get("ward") != "") {
				sql3.append(" and b.ward like '%" + mapBuilding.get("ward")  + "%'");
			}
			if (mapBuilding.get("managerName") != null && mapBuilding.get("managerName") != "") {
				sql3.append(" and b.managername like '%" + mapBuilding.get("managerName") + "%'");
			}
			
			if (mapBuilding.get("managerPhone") != null && mapBuilding.get("managerPhone")  != "") {
				sql3.append(" and b.managerphone like '%" + mapBuilding.get("managerPhone")  + "%'");
			}
			if (mapBuilding.get("areaRentFrom") != null || mapBuilding.get("areaRentTo") != null) {
				sql3.append(" and EXISTS (SELECT rentarea.value FROM rentarea WHERE 1 =1");
				if (mapBuilding.get("areaRentFrom") != null) {
					sql3.append(" and rentarea.value >= " + mapBuilding.get("areaRentFrom") + "");
				}
				if (mapBuilding.get("areaRentTo") != null) {
					sql3.append(" and rentarea.value <= " + mapBuilding.get("areaRentTo") + "");
				}
				sql3.append(")");
			}
			if (mapBuilding.get("district") != null && mapBuilding.get("district")!="") {
				sql3.append(" and district.code = '" + mapBuilding.get("district") + "'");
			}
	
			String[] types = (String[]) mapBuilding.get("buildingTypes");
			
			if (types!=null) {
				int i = 0;
				sql1.append(",renttype.name as category");
				sql2.append(" inner join buildingrenttype on b.id=buildingrenttype.buildingid\r\n"
						+ "inner join renttype on buildingrenttype.renttypeid=renttype.id");
				sql3.append(" and(");
				for (String item :types) {
					types[i] = " renttype.code = '" + item + "'";
					i++;
				}
				String newSql = String.join(" OR ", types);
				sql3.append(newSql);
				sql3.append(")");
			}

			if (mapBuilding.get("staffId") != null) {
				sql2.append(" inner join assignmentbuilding on assignmentbuilding.buildingid=b.id ");
				sql3.append(" and assignmentbuilding.staffid = " + mapBuilding.get("staffId") + "");
			}
			sql3.append(" group by b.id");
			String sql = (sql1.toString() + sql2.toString() + sql3.toString());
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setFloorArea(rs.getInt("floorarea"));
				buildingEntity.setRentPrice(rs.getInt("rentprice"));
				buildingEntity.setDistrict(getDistrictById(rs.getLong("districtid")));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setNameManager(rs.getString("namemanager"));
				buildingEntity.setPhoneManager(rs.getString("phonemanager"));
				buildingEntity.setBrokerageFee(rs.getString("brokeragefee"));
				buildingEntity.setServiceFee(rs.getString("servicefee"));
				buildingEntity.setCreatedDate(rs.getDate("createDate"));
				buildingEntities.add(buildingEntity);
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
		return buildingEntities;
	}
	

}
