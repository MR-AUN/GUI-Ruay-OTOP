package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.InvoiceModel;

public class InvoiceDAO {
	public void addInvoice(InvoiceModel inv) {
		try {
			String insertSql = "INSERT INTO INVOICE VALUES (NULL," + inv.getSupId() + ",'" + inv.getInvDate() + "',"
					+ inv.getInvTotal() + ")";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql);
				stmnt.close();
				con.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public int addInvoiceGetId(InvoiceModel inv) {
		try {
			String insertSql = "INSERT INTO `invoice` (`id`, `inv_date`, `inv_total`, `sup_id`) VALUES (NULL, '"+inv.getInvDate()+"', "+inv.getInvTotal()+", "+inv.getSupId()+")";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql, Statement.RETURN_GENERATED_KEYS);
				try (ResultSet generatedKeys = stmnt.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                int idInv= generatedKeys.getInt(1);
		            	stmnt.close();
		                con.close();
		                return idInv;
		            }
		            else {
		            	stmnt.close();
		            	con.close();
		                throw new SQLException("Creating user failed, no ID obtained.");
		            }
		        }
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	
	public  InvoiceModel searchDBviewIdInv(int id) throws SQLException {
		String sql = "SELECT * FROM invoice WHERE invoice.id = "+id;
		InvoiceModel inv = null;
		try {

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					int id_inv = rs.getInt("ID");
					int id_sup = rs.getInt("id_sup");
					String date = rs.getString("date");
					double total = rs.getDouble("inv_total");
					inv = new InvoiceModel(id_inv, id_sup, date,total);
				}
				stmnt.close();
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return inv;
	}
	
	public static double searchDBinvDet(long id, long id_sup ,int number,int combinID) throws SQLException {
		
		double amount = 0 ;
		try {
			String sql = "SELECT suppliergoods.suppliergood_price*"+number+" AS amount FROM `invoice` AS inv INNER JOIN supplier ON inv.sup_id = supplier.id INNER JOIN suppliergoods on supplier.id = suppliergoods.supplier_id WHERE inv.id ="+id+" AND suppliergoods.supplier_id ="+id_sup+" AND suppliergoods.combin_group_id = "+combinID;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();
			System.out.println(sql);
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					 amount = rs.getDouble("AMOUNT");
				}
				stmnt.close();
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return amount;
	}
	
	
	public void updateById(double amount , int id) {
		try {
			String sqls = "UPDATE `invoice` SET inv_total= " + amount + " WHERE id =" + id;
			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();
			
			System.out.println(sqls);
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sqls);
				stmnt.close();
				con.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
