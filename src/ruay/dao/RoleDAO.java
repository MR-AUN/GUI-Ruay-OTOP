package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.CustomerModel;
import ruay.model.RoleModel;

public class RoleDAO {
	public Vector<RoleModel> viewRole() {
		System.out.println("View Role");
		Vector<RoleModel> roleList = null;
		
		try {
			String viewSql = "SELECT * FROM roles";
			System.out.println("selectSql:" + viewSql);
			
			roleList = new Vector<RoleModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int cus_id = rs.getInt("id");
					String name = rs.getString("role_name");
					
					RoleModel role = new RoleModel(cus_id, name);
					roleList.addElement(role);
				}
				stmnt.close();
				con.close();
				System.out.println("Searched successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return roleList;
	}	
	
	public RoleModel searchRole(int role_id) {
		System.out.println("Search Role");
		RoleModel roleList = null;
		
		try {
			String viewSql = "SELECT * FROM roles WHERE roles.id = "+role_id;
			System.out.println("selectSql:" + viewSql);
			
			

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int cus_id = rs.getInt("id");
					String name = rs.getString("role_name");
					
					roleList = new RoleModel(cus_id, name);
				}
				stmnt.close();
				con.close();
				System.out.println("Searched successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return roleList;
	}	
}
