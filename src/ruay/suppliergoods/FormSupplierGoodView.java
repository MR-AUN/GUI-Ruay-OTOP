package ruay.suppliergoods;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.SupplierGoodsDAO;
import ruay.model.AdminGoodsModel;
import ruay.model.GroupGoodModel;
import ruay.model.SupplierGoodsModel;

public class FormSupplierGoodView extends JPanel  {
	// ScrollPane for Table
	JScrollPane scrollPane;
	GuiMainRuay mainMenu;
	public FormSupplierGoodView(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupplierGoods.FORMWIDTH ;
		int FORMHEIGHT = mainMenu.menuSystem.formSupplierGoods.FORMHEIGHT ;
		setOpaque(false);
		setLayout(null);
		JLabel viewStudentLabel = new JLabel("การจัดจำหน่ายสินค้าทั้งหมด");
		viewStudentLabel.setFont(CustomFont.THboldFont(18));
		add(viewStudentLabel);

		// set sizes and positions for labels
		Dimension size = viewStudentLabel.getPreferredSize();
		viewStudentLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);

		scrollPane = new JScrollPane();
		scrollPane.setBounds((((FORMWIDTH ) - size.width) / 2 )-(((FORMWIDTH - 200) - size.width) / 2 ) , 30, FORMWIDTH - 200, FORMHEIGHT - 80);
		add(scrollPane);
		
		
		
		// Table
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		viewSupGood() ;
	}
	
	public void viewSupGood() {
		try {
			Vector<SupplierGoodsModel> supGoods = mainMenu.DAOSUPGOOD.viewSupGood();

			// Table
			JTable table = new JTable();
			JTableHeader tableHeader = table.getTableHeader();
			table.setFont(CustomFont.THFont(17));
			tableHeader.setFont(CustomFont.THboldFont(18));
			scrollPane.setViewportView(table);

			// Model for Table
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addColumn("ไอดี");
			model.addColumn("ชื่อผู้ผลิต");
			model.addColumn("ชื่อสินค้า");
			model.addColumn("ราคา");


			int row = 0;
			// Data Row
			for (int i = 0;i < supGoods.size(); i++ ) {
				SupplierGoodsModel supgood = supGoods.get(i);
				model.addRow(new Object[0]);
				model.setValueAt(supgood.getSupgoodId(), row, 0);
				model.setValueAt(supgood.getSup().getSupName()+" "+supgood.getSup().getSupLast(), row, 1);
				model.setValueAt(supgood.getCombin().toString(), row, 2);
				model.setValueAt(supgood.getSupgoodPrice(), row, 3);
				++row;
			}

			setVisible(true);
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid data.");
		} catch (Exception ex) {
			System.err.println("Error! " + ex.getMessage());
		} 

	}
	
}
