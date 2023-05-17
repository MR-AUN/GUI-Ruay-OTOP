package ruay.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import ruay.db.ConnectionDB;
import ruay.model.CategorieViewModel;
import ruay.model.CategoriesModel;
import ruay.model.CustomerModel;
import ruay.model.RoleModel;

public class CategorieDAO {
	public Vector<CategoriesModel> viewCategorie() {
		System.out.println("View Categorie");
		Vector<CategoriesModel> cateList = null;
		
		try {
			String viewSql = "SELECT * FROM categories";
			System.out.println("selectSql:" + viewSql);
			
			cateList = new Vector<CategoriesModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int cate_id = rs.getInt("id");
					String name = rs.getString("categoriename");
					
					CategoriesModel cate = new CategoriesModel(cate_id, name);
					cateList.addElement(cate);
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
		return cateList;
	}	
	
	public Vector<CategorieViewModel> viewCategorieAll() {
		System.out.println("View Categorie All");
		Vector<CategorieViewModel> cateAllList = null;
		
		try {
			String viewSql = "SELECT cate.id,cate.categoriename,sub.sup_name AS items,sub.id AS sub_id "
					+ "FROM categories cate JOIN supcategories sub ON sub.categories_id = cate.id  GROUP BY cate.id,items "
					+ "UNION "
					+ "SELECT id,categoriename,NULL,NULL FROM categories;";
			System.out.println("selectSql:" + viewSql);
			
			cateAllList = new Vector<CategorieViewModel>();

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int cate_id = rs.getInt("id");
					String name = rs.getString("categoriename");
					String sub_name = rs.getString("items");
					int sub_id = rs.getInt("sub_id");
					
					CategorieViewModel cateall = new CategorieViewModel(cate_id, name,sub_name,sub_id);
					cateAllList.addElement(cateall);
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
		return cateAllList;
	}	
	
	public CategorieViewModel searchCategorieAll(int idCate,int idSup) {
		System.out.println("View Categorie All");
		CategorieViewModel cates = null;
		
		try {
			String viewSql = "SELECT \r\n"
					+ "	cate.id,\r\n"
					+ "	cate.categoriename,\r\n"
					+ "	sub.sup_name AS items,\r\n"
					+ "	sub.id AS sub_id\r\n"
					+ "FROM categories cate  JOIN supcategories sub ON sub.categories_id = cate.id WHERE sub.id = "+idSup+" AND cate.id = "+idCate+" GROUP BY cate.id,items \r\n"
					+ "UNION \r\n"
					+ "SELECT id,categoriename,NULL,NULL FROM categories WHERE categories.id = "+idCate+";";
			System.out.println("selectSql:" + viewSql);
			

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(viewSql);
				while (rs.next()) {
					int cate_id = rs.getInt("id");
					String name = rs.getString("categoriename");
					String sub_name = rs.getString("items");
					int sub_id = rs.getInt("sub_id");
					
					cates = new CategorieViewModel(cate_id, name,sub_name,sub_id);
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
		return cates;
	}	
	
	public void addCategorie(String cate) {
		System.out.println("Add Categorie");
		try {
			String insertSql = "INSERT INTO `categories` (`id`, `categoriename`) VALUES (NULL, '"+cate+"'); ";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql);
				stmnt.close();
				con.close();
				System.out.println("Categorie Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
