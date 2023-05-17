package ruay.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ruay.db.ConnectionDB;

public class CategorieSupDAO {
	public void addCategorieSup(int id,String cate) {
		System.out.println("Add Categorie Sup");
		try {
			String insertSql = "INSERT INTO `supcategories` (`id`, `categories_id`, `sup_name`) VALUES (NULL, '"+id+"', '"+cate+"'); ";
			System.out.println("insertSql:" + insertSql);

			ConnectionDB connDB = new ConnectionDB();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(insertSql);
				stmnt.close();
				con.close();
				System.out.println("Categorie Sup Add successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
