package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.AdminGoodsModel;
import ruay.model.SupplierModel;

public class SupplierDAO {
	public void addSupper(SupplierModel sup) {
		System.out.println("Add Supplier");
		try {
			String insertSql = "INSERT INTO `supplier` (`id`, `sup_firstname`, `sup_address`, `sup_phone`, `sup_lastname`) VALUES (NULL, '"+sup.getSupName()+"', '"+sup.getSupAddress()+"', '"+sup.getSupPhone()+"', '"+sup.getSupLast()+"')";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql);
				stmnt.close();
				con.close();
				System.out.println("Supplier Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void editSupplier(SupplierModel sup) {
		System.out.println("Edit Supplier");
		try {
			String updateSql = "UPDATE `supplier` SET `sup_firstname` = '"+sup.getSupName()+"', `sup_address` = '"+sup.getSupAddress()+"', `sup_phone` = '"+sup.getSupPhone()+"', `sup_lastname` = '"+sup.getSupLast()+"' WHERE `supplier`.`id` = "+sup.getSupId();
			System.out.println("updateSql:" + updateSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.executeUpdate(updateSql);
				stmnt.close();
				con.close();
				System.out.println("Goods Updated successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteSupplier(long sup_id) {
		System.out.println("Delete Supplier");
		try {
			String deleteSql = "DELETE FROM `supplier` WHERE `supplier`.`id` = "+sup_id;
			System.out.println("deleteSql:" + deleteSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.executeUpdate(deleteSql);
				stmnt.close();
				con.close();
				System.out.println("Deleted successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public SupplierModel searchSupplier(int sup_id) {
		System.out.println("Search Supplier");
		SupplierModel sup = null;
		try {
			String searchSql = "SELECT * FROM `supplier` WHERE `supplier`.`id` = "+sup_id;
			System.out.println("selectSql:" + searchSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("sup_firstname");
					String last = rs.getString("sup_lastname");
					String address = rs.getString("sup_address");
					String phone = rs.getString("sup_phone");
					sup = new SupplierModel(id,name, last, address, phone);
				}
				stmnt.close();
				con.close();
				System.out.println("Searched successfully.");
			}
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid plate");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return sup;
	}
	
	public Vector<SupplierModel> viewSupplier() {
		System.out.println("View Supplier");
		Vector<SupplierModel> suplierList = null;
		
		try {
			String viewSql = "SELECT * FROM supplier";
			System.out.println("selectSql:" + viewSql);
			
			suplierList = new Vector<SupplierModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int sup_id = rs.getInt("id");
					String name = rs.getString("sup_firstname");
					String last = rs.getString("sup_lastname");
					String address = rs.getString("sup_address");
					String phone = rs.getString("sup_phone");
					SupplierModel sup = new SupplierModel(sup_id, name, last, address, phone);
					suplierList.add(sup);
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
		return suplierList;
	}	
}
