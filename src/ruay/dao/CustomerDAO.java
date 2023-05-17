package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ruay.db.ConnectionDB;
import ruay.model.CustomerModel;
import ruay.model.SupplierModel;

public class CustomerDAO {
	public void addCustomer(CustomerModel cus) {
		System.out.println("Add Customer");
		try {
			String insertSql = "INSERT INTO `customer` (`id`, `firstname`, `e_mail`, `username`, `password`, `lastname`, `role_id`) VALUES (NULL, '"+cus.getCusName()+"', '"+cus.getCusEmail()+"', '"+cus.getCusUsername()+"', '"+cus.getCusPass()+"', '"+cus.getCusLast()+"', '"+cus.getRoleId()+"')";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql);
				stmnt.close();
				con.close();
				System.out.println("Customer Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void editCustomer(CustomerModel cus) {
		System.out.println("Edit Customer");
		try {
			String updateSql = "UPDATE `customer` SET `firstname` = '"+cus.getCusName()+"', `e_mail` = '"+cus.getCusEmail()+"', `username` = '"+cus.getCusUsername()+"', `password` = '"+cus.getCusPass()+"', `lastname` = '"+cus.getCusLast()+"', `role_id` = '"+cus.getRoleId()+"' WHERE `customer`.`id` = "+cus.getCusId();
			System.out.println("updateSql:" + updateSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.executeUpdate(updateSql);
				stmnt.close();
				con.close();
				System.out.println("Customer Updated successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void deleteCustomer(long cus_id) {
		System.out.println("Delete Customer");
		try {
			String deleteSql = "DELETE FROM `customer` WHERE `customer`.`id` = "+cus_id;
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
	public CustomerModel searchCustomer(long cus_id) {
		System.out.println("Search Customer");
		CustomerModel cus = null;
		try {
			String searchSql = "SELECT * FROM `customer` WHERE `customer`.`id` = "+cus_id;
			System.out.println("selectSql:" + searchSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				if (rs.next()) {
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					String username = rs.getString("username");
					String pass = rs.getString("password");
					String e_mail = rs.getString("e_mail");
					int role = rs.getInt("role_id");
					cus = new CustomerModel(firstname, lastname, username, e_mail, pass, role);
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
		return cus;
	}
	
	public ArrayList<CustomerModel> viewCustomer() {
		System.out.println("View Customer");
		ArrayList<CustomerModel> cusList = null;
		
		try {
			String viewSql = "SELECT * FROM customer";
			System.out.println("selectSql:" + viewSql);
			
			cusList = new ArrayList<CustomerModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int cus_id = rs.getInt("id");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					String username = rs.getString("username");
					String pass = rs.getString("password");
					String e_mail = rs.getString("e_mail");
					int role = rs.getInt("role_id");
					CustomerModel cus = new CustomerModel(cus_id, firstname, lastname, username, e_mail, pass, role);
					cusList.add(cus);
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
		return cusList;
	}	
}
