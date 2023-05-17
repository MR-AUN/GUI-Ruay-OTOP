package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;

import ruay.db.ConnectionDB;
import ruay.model.AdminGoodsModel;
import ruay.model.CombinGroupModel;
import ruay.model.GoodGroupOptionView;
import ruay.model.GroupGoodModel;
import ruay.model.OptionGoodModel;
import ruay.model.SupplierGoodsModel;
import ruay.model.SupplierModel;

public class SupplierGoodsDAO {
	public void addSupGood(SupplierGoodsModel sup_good) {
		System.out.println("Add Supplier Goods");
		try {
			String insertSql = "INSERT INTO `suppliergoods` (`id`, `supplier_id`, `suppliergood_price`, `combin_group_id`) VALUES (NULL, '"+sup_good.getSup().getSupId()+"', '"+sup_good.getSupgoodPrice()+"', "+sup_good.getCombin().getCombinId()+")";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql);
				stmnt.close();
				con.close();
				System.out.println("Supplier Goods Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void editSupGood(SupplierGoodsModel sup_good) {
		System.out.println(sup_good);
		try {
			String updateSql = "UPDATE `suppliergoods` SET `supplier_id` = '"+sup_good.getSup().getSupId()+"', `suppliergood_price` = '"+sup_good.getSupgoodPrice()+"', `combin_group_id` = '"+sup_good.getCombin().getCombinId()+"' WHERE `suppliergoods`.`id` = "+sup_good.getSupgoodId();
			System.out.println("updateSql:" + updateSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.executeUpdate(updateSql);
				stmnt.close();
				con.close();
				System.out.println("Supplier Goods Updated successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void deleteSupGood(long sup_goodId) {
		System.out.println("Delete Supplier Goods");
		try {
			String deleteSql = "DELETE FROM `suppliergoods` WHERE `suppliergoods`.`id` = "+sup_goodId;
			System.out.println("deleteSql:" + deleteSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.executeUpdate(deleteSql);
				stmnt.close();
				con.close();
				System.out.println("Supplier Goods Deleted successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public SupplierGoodsModel searchSupGood(long sup_goodId) {
		System.out.println("Search Supplier Goods");
		SupplierGoodsModel sup_good = null;
		try {
			String searchSql = "SELECT * FROM `suppliergoods` WHERE `suppliergoods`.`id` = "+sup_goodId;
			System.out.println("selectSql:" + searchSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				if (rs.next()) {
					int id = rs.getInt("id");
					int supplier_id  = rs.getInt("supplier_id");
					double suppliergood_price = rs.getDouble("suppliergood_price");
					int combin_group_id  = rs.getInt("combin_group_id");
					SupplierModel sup = new SupplierDAO().searchSupplier(supplier_id);
					CombinGroupModel combin = new CombinGroupDAO().searchCustomer(combin_group_id);
					sup_good = new SupplierGoodsModel(id, sup, combin, suppliergood_price);
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
		return sup_good;
	}
	
	public Vector<SupplierGoodsModel> viewSupGood() {
		System.out.println("View Supplier Goods");
		Vector<SupplierGoodsModel> sup_goodList = null;
		
		try {
			String viewSql = "SELECT * FROM suppliergoods";
			
			sup_goodList = new Vector<SupplierGoodsModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int id = rs.getInt("id");
					int supplier_id  = rs.getInt("supplier_id");
					double suppliergood_price = rs.getDouble("suppliergood_price");
					int combin_group_id  = rs.getInt("combin_group_id");
					SupplierModel sup = new SupplierDAO().searchSupplier(supplier_id);
					CombinGroupModel combin = new CombinGroupDAO().searchCustomer(combin_group_id);
					SupplierGoodsModel sup_good = new SupplierGoodsModel(id, sup, combin, suppliergood_price);
					sup_goodList.add(sup_good);
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
		return sup_goodList;
	}	
	
	public Vector<SupplierGoodsModel> searchSupGoodList(int sup_id) {
		System.out.println("View Supplier Goods");
		Vector<SupplierGoodsModel> sup_goodList = null;
		
		try {
			String viewSql = "SELECT * FROM suppliergoods WHERE suppliergoods.supplier_id = "+sup_id+" ";
			
			sup_goodList = new Vector<SupplierGoodsModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int id = rs.getInt("id");
					int supplier_id  = rs.getInt("supplier_id");
					double suppliergood_price = rs.getDouble("suppliergood_price");
					int combin_group_id  = rs.getInt("combin_group_id");
					SupplierModel sup = new SupplierDAO().searchSupplier(supplier_id);
					CombinGroupModel combin = new CombinGroupDAO().searchCustomer(combin_group_id);
					SupplierGoodsModel sup_good = new SupplierGoodsModel(id, sup, combin, suppliergood_price);
					sup_goodList.add(sup_good);
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
		return sup_goodList;
	}	
	
//	public Vector<SupplierGoodsModel> searchSupGoodList(int sup_id) {
//		System.out.println("View Supplier Goods");
//		Vector<SupplierGoodsModel> sup_goodList = null;
//		
//		try {
//			String viewSql = "SELECT * FROM suppliergoods WHERE suppliergoods.supplier_id = "+sup_id;
//			
//			sup_goodList = new Vector<SupplierGoodsModel>();
//
//			ConnectionDB connDB = new ConnectionDB();
//			Connection con = connDB.getConnection();
//
//			Statement stmnt = null;
//			if (con != null) {
//				stmnt = con.createStatement();
//				ResultSet rs = stmnt.executeQuery(viewSql);
//				while (rs.next()) {
//					int id = rs.getInt("id");
//					int supplierId = rs.getInt("supplier_id");
//					int good_id = rs.getInt("good_id");
//					Integer option_id = rs.getInt("option_id");
//					double suppliergood_price = rs.getDouble("suppliergood_price");
//					SupplierModel supplier = new SupplierDAO().searchSupplier(supplierId);
//					AdminGoodsModel good = new AdminGoodsDAO().searchGoods(good_id);
//					OptionGoodModel option = null;
//					if(option_id != null || option_id != 0) {
//						option = new OptionGoodDAO().searchOption(option_id);
//					}
//					SupplierGoodsModel sup_good = new SupplierGoodsModel(id,supplier, good, option, suppliergood_price);
//					sup_goodList.add(sup_good);
//				}
//				stmnt.close();
//				con.close();
//				System.out.println("Searched successfully.");
//			}
//		} catch (ClassNotFoundException ex) {
//			ex.printStackTrace();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		return sup_goodList;
//	}	
	
	public Vector<GoodGroupOptionView> viewByIdoption(int good_id) {
		System.out.println("View Supplier Goods");
		Vector<GoodGroupOptionView> sup_goodList = null;
		
		try {
			String viewSql = "SELECT *,op.id optionId FROM goods_option op\r\n"
					+ "JOIN goods_group gr ON gr.id = op.good_group\r\n"
					+ "WHERE gr.good_id = "+good_id	;
			
			sup_goodList = new Vector<GoodGroupOptionView>();

			System.out.println(viewSql);
			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int groupId = rs.getInt("id");
					int goodId = rs.getInt("good_id");
					String groupname = rs.getString("groupname");
					GroupGoodModel group = new GroupGoodModel(groupId, goodId, groupname);
					int optionId = rs.getInt("optionId");
					int groupOp = rs.getInt("good_group");
					String optionName = rs.getString("optionname");
					double optionPrice = rs.getDouble("optionprice");
					OptionGoodModel option = new OptionGoodModel(optionId, groupOp, optionName, optionPrice);
					GoodGroupOptionView groupOption = new GoodGroupOptionView(group, option);
					sup_goodList.add(groupOption);
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
		return sup_goodList;
	}	
	
	public Vector<GoodGroupOptionView> viewByIdSupOption(int good_id , int supId) {
		System.out.println("View Supplier Goods");
		Vector<GoodGroupOptionView> sup_goodList = null;
		
		try {
			String viewSql = "SELECT *,op.id optionId,gr.id groupId FROM suppliergoods sup\r\n"
					+ "JOIN goods_option op ON op.id = sup.option_id\r\n"
					+ "JOIN goods_group gr ON gr.id = op.good_group\r\n"
					+ "WHERE  sup.supplier_id = "+supId+" AND sup.good_id = "+good_id	;
			
			sup_goodList = new Vector<GoodGroupOptionView>();

			System.out.println(viewSql);
			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int groupId = rs.getInt("groupId");
					int goodId = rs.getInt("good_id");
					String groupname = rs.getString("groupname");
					GroupGoodModel group = new GroupGoodModel(groupId, goodId, groupname);
					int optionId = rs.getInt("optionId");
					int groupOp = rs.getInt("good_group");
					String optionName = rs.getString("optionname");
					double optionPrice = rs.getDouble("optionprice");
					OptionGoodModel option = new OptionGoodModel(optionId, groupOp, optionName, optionPrice);
					GoodGroupOptionView groupOption = new GoodGroupOptionView(group, option);
					sup_goodList.add(groupOption);
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
		return sup_goodList;
	}	
	
	
	public GoodGroupOptionView searchByIdoption(int option_id) {
		System.out.println("View Supplier Goods");
		GoodGroupOptionView sup_good = null;
		
		try {
			String viewSql ="SELECT *,op.id optionId FROM goods_option op\r\n"
					+ "	JOIN goods_group gr ON gr.id = op.good_group\r\n"
					+ "	WHERE op.id = "+option_id		;
			

			System.out.println(viewSql);
			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int groupId = rs.getInt("id");
					int goodId = rs.getInt("good_id");
					String groupname = rs.getString("groupname");
					GroupGoodModel group = new GroupGoodModel(groupId, goodId, groupname);
					int optionId = rs.getInt("optionId");
					int groupOp = rs.getInt("good_group");
					String optionName = rs.getString("optionname");
					double optionPrice = rs.getDouble("optionprice");
					OptionGoodModel option = new OptionGoodModel(optionId, groupOp, optionName, optionPrice);
					sup_good = new GoodGroupOptionView(group, option);
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
		return sup_good;
	}	
	
	
	
	
	
}
