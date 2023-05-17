package ruay.customer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.CustomerDAO;
import ruay.model.CustomerModel;
import ruay.model.RoleModel;
import ruay.model.SupplierGoodsModel;

public class FormCustomerView extends JPanel {
	JScrollPane scrollPane;
	GuiMainRuay mainMenu ;
	public FormCustomerView(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formCustomer.FORMWIDTH ;
		int FORMHEIGHT = mainMenu.menuSystem.formCustomer.FORMHEIGHT ;
		setOpaque(false);
		setLayout(null);
		JLabel viewStudentLabel = new JLabel("รายชื่อลูกค้าทั้งหมด");
		viewStudentLabel.setFont(CustomFont.THboldFont(20));
		add(viewStudentLabel);

		// set sizes and positions for labels
		Dimension size = viewStudentLabel.getPreferredSize();
		viewStudentLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);

		scrollPane = new JScrollPane();
		scrollPane.setBounds((((FORMWIDTH ) - size.width) / 2 )-(((FORMWIDTH - 200) - size.width) / 2 ) , 30, FORMWIDTH - 200, FORMHEIGHT - 80);
		add(scrollPane);
		
		
		
		
		viewCustomer() ;
	}
	
	public void viewCustomer() {
		try {
			ArrayList<CustomerModel> cuslist = mainMenu.DAOCUS.viewCustomer();
			

			// Table
			JTable table = new JTable();
			JTableHeader tableHeader = table.getTableHeader();
			table.setFont(CustomFont.THFont(17));
			tableHeader.setFont(CustomFont.THboldFont(18));
			scrollPane.setViewportView(table);

			// Model for Table
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addColumn("รหัส");
			model.addColumn("ชื่อต้น");
			model.addColumn("นามสกุล");
			model.addColumn("อีเมล์");
			model.addColumn("ชื่อผู้ใช้");
			model.addColumn("รหัสผ่าน");
			model.addColumn("สิทธ์");


			int row = 0;
			// Data Row
			for (int i = 0;i < cuslist.size(); i++ ) {
				CustomerModel cus = cuslist.get(i);
				RoleModel role = mainMenu.DAOROLE.searchRole(cus.getRoleId());
				model.addRow(new Object[0]);
				model.setValueAt(cus.getCusId(), row, 0);
				model.setValueAt(cus.getCusName(), row, 1);
				model.setValueAt(cus.getCusLast(), row, 2);
				model.setValueAt(cus.getCusEmail(), row, 3);
				model.setValueAt(cus.getCusUsername(), row, 4);
				model.setValueAt(cus.getCusPass(), row, 5);
				model.setValueAt(role.getRole_name(), row, 6);
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
