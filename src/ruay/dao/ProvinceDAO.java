package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.ProvinceModel;

public class ProvinceDAO {
	public Vector<ProvinceModel> viewProvince() {
		System.out.println("View Province");
		Vector<ProvinceModel> provinceList = null;
		
		try {
			String viewSql = "SELECT * FROM provinces";
			System.out.println("selectSql:" + viewSql);
			
			provinceList = new Vector<ProvinceModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int pro_id = rs.getInt("province_id");
					String name = rs.getString("province_name");
					String code = rs.getString("province_code");
					int geo_id = rs.getInt("geo_id");
					
					ProvinceModel province = new ProvinceModel(pro_id, code,name,geo_id);
					provinceList.addElement(province);
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
		return provinceList;
	}	
}
