package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
	public List<BuildingEntity> findAll(HashMap<String, Object> map) {
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
					+ ",b.street as street,b.ward as ward,district.name as district"
					+ ",b.managername as namemanager,b.managerphone as phonemanager"
					+ ",b.numberofbasement as numberofbasement,b.floorarea as floorarea" + ",b.rentprice as rentprice");
			StringBuilder sql2 = new StringBuilder(" from building as b inner join district on district.id=b.districtid ");
			StringBuilder sql3 = new StringBuilder(" where 1=1");

			if (map.get("name") != null && map.get("name") != "") {
				sql3.append(" and b.name like '%" + map.get("name") + "%'");
			}
			
			if (map.get("numberofbasement")!= null) {
				sql3.append(" and b.numberofbasement =" + map.get("numberofbasement") + "");
			}
			
			if (map.get("costRentFrom")!= null) {
				sql3.append(" and b.rentprice >= '" + map.get("costRentFrom")+ "'");
			}
			if (map.get("costRentTo") != null) {
				sql3.append(" and b.rentprice <= '" + map.get("costRentTo") + "'");
			}
			if (map.get("street") != null && map.get("street")  != "") {
				sql3.append(" and b.street like '%" + map.get("street")  + "%'");
			}
			if (map.get("ward") != null && map.get("ward") != "") {
				sql3.append(" and b.ward like '%" + map.get("ward")  + "%'");
			}
			if (map.get("managerName") != null && map.get("managerName") != "") {
				sql3.append(" and b.managername like '%" + map.get("managerName") + "%'");
			}
			
			if (map.get("managerPhone") != null && map.get("managerPhone")  != "") {
				sql3.append(" and b.managerphone like '%" + map.get("managerPhone")  + "%'");
			}
			if (map.get("areaRentFrom") != null || map.get("areaRentTo") != null) {
				sql3.append(" and EXISTS (SELECT rentarea.value FROM rentarea WHERE 1 =1");
				if (map.get("areaRentFrom") != null) {
					sql3.append(" and rentarea.value >= " + map.get("areaRentFrom") + "");
				}
				if (map.get("areaRentTo") != null) {
					sql3.append(" and rentarea.value <= " + map.get("areaRentTo") + "");
				}
				sql3.append(")");
			}
			if (map.get("district") != null && map.get("district")!="") {
				sql3.append(" and district.code = '" + map.get("district") + "'");
			}
	
			String[] types = (String[]) map.get("buildingTypes");
			
			if (types!=null) {
				int i = 0;
				sql1.append(",renttype.name as category");
				sql2.append(" left join buildingrenttype on b.id=buildingrenttype.buildingid\r\n"
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

			if (map.get("staffId") != null) {
				sql2.append(" left join assignmentbuilding on assignmentbuilding.buildingid=b.id ");
				sql3.append(" and assignmentbuilding.staffid = " + map.get("staffId") + "");
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
				buildingEntity.setDistrict(rs.getString("district"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setNameManager(rs.getString("namemanager"));
				buildingEntity.setPhoneManager(rs.getString("phonemanager"));
				if(types!=null) {
					for (int i = 0; i < types.length; i++) {
						if (types[i] != null) {
							buildingEntity.setBuildingRentType(rs.getString("category"));
						}
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
