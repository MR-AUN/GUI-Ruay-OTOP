package ruay.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.AdminGoodsModel;
import ruay.model.CategorieViewModel;
import ruay.model.GroupGoodModel;
import ruay.model.OptionGoodModel;
import ruay.model.SupplierModel;
import ruay.model.itemGroup;

public class OptionGoodDAO {
	public void addOptionList(ArrayList<OptionGoodModel> options) {
		System.out.println("Add Option");
		try {
			String insertSql = "";
			
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				for (OptionGoodModel option : options) {
					insertSql = " INSERT INTO `goods_option` (`id`, `good_group`, `optionname`, `optionprice`) VALUES (NULL, "
							+ option.getGroupId() + ", '" + option.getOptionName() + "' , " + option.getOptionPrice() + ");  ";
					stmnt.execute(insertSql);
				}

				
				stmnt.close();
				con.close();
				System.out.println("Good Option Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void addOption(OptionGoodModel option) {
		System.out.println("Add Option");
		try {
			String insertSql = " INSERT INTO `goods_option` (`id`, `good_group`, `optionname`, `optionprice`) VALUES (NULL, "
					+ option.getGroupId() + ", '" + option.getOptionName() + "' , " + option.getOptionPrice() +");  ";

			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql);
				stmnt.close();
				con.close();
				System.out.println("Good Option Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void editOption(OptionGoodModel option) {
		System.out.println("edit Option");
		try {
			String editSql = " UPDATE `goods_option` SET `optionname` = '"+option.getOptionName()+"', `optionprice` = '"+option.getOptionPrice()+"' WHERE `goods_option`.`id` = "+option.getOptionId();

			System.out.println("UpdateSql:" + editSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(editSql);
				stmnt.close();
				con.close();
				System.out.println("Good Option Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public OptionGoodModel searchOption(int option_id) {
		System.out.println("Search Goods");
		OptionGoodModel option = null;
		try {
			String searchSql = "SELECT * FROM `goods_option` WHERE `goods_option`.`id` = " + option_id;
			System.out.println("selectSql:" + searchSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				if (rs.next()) {
					int groupId = rs.getInt("good_group");
					String optionName = rs.getString("optionname");
					double optionprice = rs.getDouble("optionprice");
					option = new OptionGoodModel(option_id,groupId,optionName, optionprice);
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
		return option;
	}


	public ArrayList<ArrayList<itemGroup>> searchOptionByGoodId(int good_id) {
		ArrayList<ArrayList<itemGroup>> optionall = null;

		try {
			String viewSql = "SELECT gr.id groupId, gr.groupname , op.* FROM goods_option op \r\n"
					+ "right JOIN goods_group gr ON gr.id = op.good_group\r\n" + "JOIN goods g ON gr.good_id = g.id\r\n"
					+ "WHERE g.id = " + good_id;
			System.out.println("selectSql:" + viewSql);

			optionall = new ArrayList<ArrayList<itemGroup>>();
			ArrayList<itemGroup> option = new ArrayList<itemGroup>() ;
			int oldID = -1;
			boolean check =false ;

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {

					int groupId = rs.getInt("groupId");
					String groupname = rs.getString("groupname");
					int optionId = rs.getInt("id");
					String optionName = rs.getString("optionname");
					double optionPrice = rs.getDouble("optionprice");
					if (oldID != groupId) {
						if(!option.isEmpty()) {
							optionall.add(option);
						}
						option = new ArrayList<itemGroup>();
						oldID = groupId;
					}
					itemGroup optiongroup = new itemGroup(groupId, groupname, optionId, optionName, optionPrice);
					option.add(optiongroup);
					
				}
				if(!option.isEmpty()) {
					if(option.get(0).getGroupId() == oldID) {
						optionall.add(option);
					}
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
		return optionall;
	}
	
	public void deleteOption(int option_id) {
		System.out.println("Delete Option");
		try {
			String deleteSql = "DELETE FROM `goods_option` WHERE `goods_option`.`id` = " + option_id;
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
	
	public Vector<OptionGoodModel> viewOptionpByGroup(int groupId) {
		Vector<OptionGoodModel> optionList = null;
		
		try {
			String viewSql = "SELECT * FROM goods_option WHERE good_group = "+groupId;
			System.out.println("selectSql:" + viewSql);
			
			optionList = new Vector<OptionGoodModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int id = rs.getInt("id");
					int group_Id = rs.getInt("good_group");
					String optionName = rs.getString("optionname");
					double optionprice = rs.getDouble("optionprice");
					OptionGoodModel option = new OptionGoodModel(id, groupId, optionName, optionprice);
					optionList.add(option);
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
		return optionList;
	}	
}
