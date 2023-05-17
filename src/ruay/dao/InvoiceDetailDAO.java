package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.AdminGoodsModel;
import ruay.model.CombinGroupModel;
import ruay.model.InvoiceDetailModel;
import ruay.model.InvoiceDetailViewModel;
import ruay.model.InvoiceGoodViewModel;
import ruay.model.InvoiceModel;
import ruay.model.OptionGoodModel;

public class InvoiceDetailDAO {
	public void addInvoiceDetail(InvoiceDetailModel invde) {
		try {
			String insertSql = "INSERT INTO `invoice_detail` (`id`, `invdetail_status`, `invdetail_Amount`, `invdetail_qua`, `inv_id`, `combin_group_id`) VALUES (NULL, '"+invde.getInvDeStatus()+"', '"+invde.getInvDeAmount()+"', '"+invde.getInvDeQuantity()+"', '"+invde.getInvId()+"', "+invde.getCombinId()+")";
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
	
	public static double searchTotalDet( int id) throws SQLException {
		double amount = 0;
		try {
			String sql = "SELECT SUM(invoice_detail.invdetail_Amount) AS total "
					+ "FROM invoice_detail "
					+ "WHERE invoice_detail.inv_id = "+id;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();
			
			System.out.println(sql);

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					 amount = rs.getDouble("TOTAL");
					
				}
				stmnt.close();
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return amount;
	}
	
	
	
	public static int searchDBstatus(int id) throws SQLException {
		int status =  -1  ;
		try {
			String sql = "SELECT invdetail_status FROM invoice_detail WHERE invoice_detail.inv_id =" + id;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					status = rs.getInt("invdetail_status");
				}
				stmnt.close();
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}
	
	
	public static ArrayList<InvoiceGoodViewModel> searchDBviewInv(int id) throws SQLException {
		ArrayList<InvoiceGoodViewModel>  invList = new ArrayList<InvoiceGoodViewModel>() ;
		
		try {
			String sql = "SELECT * FROM invoice_detail "
					+ "INNER JOIN goods ON invoice_detail.good_id = goods.id "
					+ "WHERE invoice_detail.inv_id = "+id;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					int id_good = rs.getInt("good_id");
					String name = rs.getString("goodname");
					double amount = rs.getDouble("invdetail_Amount");
					int quantity = rs.getInt("invdetail_qua");
					InvoiceGoodViewModel invoice = new InvoiceGoodViewModel( id_good, name, amount, quantity);
					invList.add(invoice);
				}
				stmnt.close();
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return invList;
	}
	
	public static ArrayList<InvoiceDetailViewModel> searchDBviewInvList(int id) throws SQLException {
		ArrayList<InvoiceDetailViewModel>  invList = new ArrayList<InvoiceDetailViewModel>() ;
		
		try {
			String sql = "SELECT * FROM invoice_detail  "
					+ "WHERE invoice_detail.inv_id = "+id;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					int invdetailId = rs.getInt("id");
					int invId = rs.getInt("inv_id");
					InvoiceModel inv = new InvoiceDAO().searchDBviewIdInv(invId);
					int invdetail_status = rs.getInt("invdetail_status");
					double invdetail_Amount = rs.getDouble("invdetail_Amount");
					int invdetail_qua = rs.getInt("invdetail_qua");
					int combin_group_id = rs.getInt("combin_group_id");
					CombinGroupModel combin = new CombinGroupDAO().searchCustomer(combin_group_id);
					InvoiceDetailViewModel invoice = new InvoiceDetailViewModel(invdetailId, inv, invdetail_status, combin, invdetail_Amount, invdetail_qua);
					invList.add(invoice);
				}
				stmnt.close();
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return invList;
	}
	
	public void updateInvDetailStatus(int id) {
		try {
			String sql = "UPDATE `invoice_detail` SET `invdetail_status` = " + 1
			+ " WHERE `invoice_detail`.`inv_id` =" + id;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
				con.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void updateInvDetailStatusOut(int id) {
		try {
			String sql = "UPDATE `invoice_detail` SET `invdetail_status` = " + 2
			+ " WHERE `invoice_detail`.`inv_id` =" + id;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
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
