package ruay.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.AdminGoodsModel;
import ruay.model.CustomerModel;
import ruay.model.GroupGoodModel;

public class GroupGoodDAO {
	public int addGroupId(GroupGoodModel group) {
		System.out.println("Add group");
		try {
			String insertSql = "INSERT INTO `goods_group` (`id`, `good_id`, `groupname`) VALUES (NULL, '"+group.getGoodId()+"', '"+group.getGroupName()+"'); \n";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				int affectedRows = stmnt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);

		        if (affectedRows == 0) {
		            throw new SQLException("Creating user failed, no rows affected.");
		        }

		        try (ResultSet generatedKeys = stmnt.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                int idGroup= generatedKeys.getInt(1);
		            	stmnt.close();
		                con.close();
		                return idGroup;
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
	
	public void editGroupName(GroupGoodModel group) {
		System.out.println("Edit Group");
		try {
			String updateSql = "UPDATE `goods_group` SET  `groupname` = '"+group.getGroupName()+"' WHERE `goods_group`.`id` = "+group.getGroupId();
			System.out.println("updateSql:" + updateSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.executeUpdate(updateSql);
				stmnt.close();
				con.close();
				System.out.println("Group Updated successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Vector<GroupGoodModel> viewGroupByGood(int goodId) {
		System.out.println("View Goods");
		Vector<GroupGoodModel> groupList = null;
		
		try {
			String viewSql = "SELECT * FROM goods_group WHERE good_id = "+goodId;
			System.out.println("selectSql:" + viewSql);
			
			groupList = new Vector<GroupGoodModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int id = rs.getInt("id");
					int good_Id = rs.getInt("good_id");
					String groupname = rs.getString("groupname");
					GroupGoodModel group = new GroupGoodModel(id, goodId, groupname);
					groupList.add(group);
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
		return groupList;
	}	
}
