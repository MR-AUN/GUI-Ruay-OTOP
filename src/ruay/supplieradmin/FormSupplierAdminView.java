package ruay.supplieradmin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.SupplierDAO;
import ruay.model.AdminGoodsModel;
import ruay.model.SupplierModel;

public class FormSupplierAdminView extends JPanel  {

	// ScrollPane for Table
	JScrollPane scrollPane;
	GuiMainRuay mainMenu;
	public FormSupplierAdminView(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupAdmin.FORMWIDTH ;
		int FORMHEIGHT = mainMenu.menuSystem.formSupAdmin.FORMHEIGHT ;
		setOpaque(false);
		setLayout(null);
		JLabel viewSupplierLabel = new JLabel("รายชื่อผู้ผลิต");
		viewSupplierLabel.setFont(CustomFont.THboldFont(17));
		add(viewSupplierLabel);

		// set sizes and positions for labels
		Dimension size = viewSupplierLabel.getPreferredSize();
		viewSupplierLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);

		scrollPane = new JScrollPane();
		scrollPane.setBounds((((FORMWIDTH ) - size.width) / 2 )-(((FORMWIDTH - 200) - size.width) / 2 ) , 30, FORMWIDTH - 200, FORMHEIGHT - 80);
		add(scrollPane);
		
		
		
		// Table
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		
		viewSupplier();
	}

	public void viewSupplier() {
		try {
			Vector<SupplierModel> sups = mainMenu.DAOSUP.viewSupplier();

			// Table
			JTable table = new JTable();
			JTableHeader tableHeader = table.getTableHeader();
//			table.getModel()
			table.setFont(CustomFont.THFont(17));
			tableHeader.setFont(CustomFont.THboldFont(18));
			scrollPane.setViewportView(table);

			// Model for Table
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addColumn("รหัสผู้ผลิต");
			model.addColumn("ชื่อต้น");
			model.addColumn("นามสกุล");
			model.addColumn("ที่อยู่");
			model.addColumn("เบอร์โทร");

			int row = 0;
			// Data Row
			for (int i = 0;i < sups.size(); i++ ) {
				SupplierModel sup = sups.get(i);
				model.addRow(new Object[0]);
				model.setValueAt(sup.getSupId(), row, 0);
				model.setValueAt(sup.getSupName(), row, 1);
				model.setValueAt(sup.getSupLast(), row, 2);
				model.setValueAt(sup.getSupAddress(), row, 3);
				model.setValueAt(sup.getSupPhone(), row, 4);
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
