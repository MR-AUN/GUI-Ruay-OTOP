package ruay.customer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.component.util.RoundedPanel;
import ruay.suppliergoods.FormSupplierGoodAdd;
import ruay.suppliergoods.FormSupplierGoodDelete;
import ruay.suppliergoods.FormSupplierGoodEdit;
import ruay.suppliergoods.FormSupplierGoodView;

public class FormCustomer extends JPanel implements ActionListener{
GuiMainRuay mainMenu;
	
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnView;
	
	FormCustomerAdd formCustomerAdd;
	FormCustomerEdit formCustomerEdit;
	FormCustomerDelete formCustomerDelete;
	FormCustomerView formCustomerView;
	
	JPanel FormPanel;
	JPanel formMenuSystem;
	public static  int FORMWIDTH = 0;
	public static  int FORMHEIGHT = 0;
	int check = 1;
	
	public FormCustomer(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		setOpaque(false);
		setLayout(null);
		JLabel lbHeader = new JLabel("แบบฟอร์มบัญชี");
		lbHeader.setFont(CustomFont.THboldFont(20));
		lbHeader.setBounds((mainMenu.WIDTH - lbHeader.getPreferredSize().width) / 2, 10,
				lbHeader.getPreferredSize().width, lbHeader.getPreferredSize().height);
		add(lbHeader);

		btnAdd = new JButton(mainMenu.ADD_STR );
		btnAdd.setFont(CustomFont.THboldFont(18));
		btnEdit = new JButton(mainMenu.EDIT_STR );
		btnEdit.setFont(CustomFont.THboldFont(18));
		btnDelete = new JButton(mainMenu.DELETE_STR );
		btnDelete.setFont(CustomFont.THboldFont(18));
		btnView = new JButton(mainMenu.VIEW_STR );
		btnView.setFont(CustomFont.THboldFont(18));

		// menu add delete search
		formMenuSystem = new RoundedPanel(15, Color.white);
		formMenuSystem.setOpaque(false);
		formMenuSystem.add(btnAdd);
		formMenuSystem.add(btnEdit);
		formMenuSystem.add(btnDelete);
		formMenuSystem.add(btnView);
		formMenuSystem.setBounds((mainMenu.WIDTH - formMenuSystem.getPreferredSize().width) / 2 - 20, 40,
				formMenuSystem.getPreferredSize().width + 20, formMenuSystem.getPreferredSize().height);
		add(formMenuSystem);

		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnView.addActionListener(this);
		
		

		FormPanel = new JPanel(new CardLayout());
		FormPanel.setOpaque(false);
		int formH =80;
		FORMWIDTH = mainMenu.WIDTH - 50;
		FORMHEIGHT = (mainMenu.HEIGHT - mainMenu.HEIGHTNAVBER) -(formH+50) ;
		FormPanel.setBounds((mainMenu.WIDTH - FORMWIDTH) /2 - 5 , formH,FORMWIDTH , FORMHEIGHT);
		add(FormPanel);
		
		
	}
	public void formMenu() {
		if(check == 1) {
			formCustomerAdd = new FormCustomerAdd(mainMenu);
			formCustomerEdit = new FormCustomerEdit(mainMenu);
			formCustomerDelete = new FormCustomerDelete(mainMenu);
			formCustomerView = new FormCustomerView(mainMenu);
			
			FormPanel.add(formCustomerView, mainMenu.VIEW_STR );
			FormPanel.add(formCustomerAdd, mainMenu.ADD_STR );
			FormPanel.add(formCustomerEdit, mainMenu.EDIT_STR );
			FormPanel.add(formCustomerDelete, mainMenu.DELETE_STR );
			check++;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals(mainMenu.ADD_STR )) {
			FormPanel.remove(formCustomerAdd);
			formCustomerAdd = new FormCustomerAdd(mainMenu);
			FormPanel.add(formCustomerAdd, mainMenu.ADD_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.ADD_STR );
		}else if (command.equals(mainMenu.EDIT_STR )) {
			FormPanel.remove(formCustomerEdit);
			formCustomerEdit = new FormCustomerEdit(mainMenu);
			FormPanel.add(formCustomerEdit, mainMenu.EDIT_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.EDIT_STR );
		}else if (command.equals(mainMenu.DELETE_STR)) {
			FormPanel.remove(formCustomerDelete);
			formCustomerDelete = new FormCustomerDelete(mainMenu);
			FormPanel.add(formCustomerDelete, mainMenu.DELETE_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.DELETE_STR );
		}else if (command.equals(mainMenu.VIEW_STR )) {
			FormPanel.remove(formCustomerView);
			formCustomerView = new FormCustomerView(mainMenu);
			FormPanel.add(formCustomerView, mainMenu.VIEW_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.VIEW_STR );
		}
	}
}
