package ruay;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import ruay.component.MainMenu;
import ruay.dao.AdminGoodsDAO;
import ruay.dao.CategorieDAO;
import ruay.dao.CategorieSupDAO;
import ruay.dao.CombinGroupDAO;
import ruay.dao.CustomerDAO;
import ruay.dao.GroupGoodDAO;
import ruay.dao.InvoiceDAO;
import ruay.dao.InvoiceDetailDAO;
import ruay.dao.OptionGoodDAO;
import ruay.dao.ProvinceDAO;
import ruay.dao.RoleDAO;
import ruay.dao.SupplierDAO;
import ruay.dao.SupplierGoodsDAO;
import ruay.model.CombinGroupModel;

public class GuiMainRuay {
	public  MainMenu menuSystem  ;

	public final int WIDTH = 1000;
	public final int HEIGHT = 560;
	public final int HEIGHTNAVBER = 80;

	public AdminGoodsDAO DAOGOOD;
	public SupplierDAO DAOSUP ;
	public SupplierGoodsDAO DAOSUPGOOD ;
	public CustomerDAO DAOCUS ;
	public InvoiceDAO DAOINV ;
	public InvoiceDetailDAO DAOINVDE;
	public RoleDAO DAOROLE ;
	public CategorieDAO DAOCATE;
	public ProvinceDAO DAOPROVINCE ;
	public GroupGoodDAO DAOGROUP;
	public OptionGoodDAO DAOOPTION;
	public CategorieSupDAO DAOCATESUP;
	public CombinGroupDAO DAOCOMBIN;
	
	public final static String GOOD_STR = "จัดการสินค้า";
	public final static String SUP_STR = "ผู้ผลิต";
	public final static String SUBGOOD_STR = "การจัดจำหน่ายสินค้า";
	public final static String CUS_STR = "จัดการบัญชีผู้ใช้";
	public final static String INVOICE_STR = "ใบแจ้งหนี้";
	public final static String ORDER_STR = "รายการสั่งซื้อกับผู้ผลิต";
	public final static String VIEW_IN_STR = "ยืนยันใบเสร็จรายการสั่งซื้อ";
	public final static String EXIT_STR = "Exit";

	public final static String ADD_STR = "เพิ่ม";
	public final static String DELETE_STR = "ลบ";
	public final static String EDIT_STR = "แก้ไข";
	public final static String VIEW_STR = "ดูข้อมูลทั้งหมด";
	public final static String CATE_STR = "หมวกหมู่สินค้า" ;
	public final static String COM_STR = "จัดกลุ่มสินค้าสินค้า" ;

	GuiMainRuay() {
		
		DAOGOOD = new AdminGoodsDAO();
		DAOSUP = new SupplierDAO();
		DAOSUPGOOD = new SupplierGoodsDAO();
		DAOCUS = new CustomerDAO();
		DAOINV = new InvoiceDAO();
		DAOINVDE = new InvoiceDetailDAO();
		DAOROLE = new RoleDAO();
		DAOCATE = new CategorieDAO();
		DAOPROVINCE = new ProvinceDAO();
		DAOGROUP = new GroupGoodDAO();
		DAOOPTION = new OptionGoodDAO();
		DAOCATESUP = new CategorieSupDAO();
		DAOCOMBIN = new CombinGroupDAO();
		menuSystem = new MainMenu(this);
		menuSystem.setVisible(true);
	}

	public static void main(String[] args) {
		
		new GuiMainRuay();
	}
}
