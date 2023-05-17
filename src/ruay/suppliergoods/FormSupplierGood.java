package ruay.suppliergoods;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ruay.GuiMainRuay;
import ruay.admingoods.FormAdminGoodAdd;
import ruay.component.util.CustomFont;
import ruay.component.util.RoundedPanel;

public class FormSupplierGood extends JPanel implements ActionListener{
	GuiMainRuay mainMenu;
	
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnView;

	FormSupplierGoodAdd formSupplierGoodAdd;
	FormSupplierGoodEdit formSupplierGoodEdit;
	FormSupplierGoodDelete formSupplierGoodDelete;
	FormSupplierGoodView formSupplierGoodView;
	
	JPanel FormPanel;
	JPanel formMenuSystem;
	public static  int FORMWIDTH = 0;
	public static  int FORMHEIGHT = 0;
	int check = 1;
	public FormSupplierGood(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		setOpaque(false);
		setLayout(null);
		JLabel lbHeader = new JLabel("แบบฟอร์การจัดจำหน่ายสินค้า");
		lbHeader.setBounds(((mainMenu.WIDTH - lbHeader.getPreferredSize().width) / 2 )+ 40, 10,
				lbHeader.getPreferredSize().width, lbHeader.getPreferredSize().height);
		add(lbHeader);
		lbHeader.setFont(CustomFont.THboldFont(20));

		btnAdd = new JButton(mainMenu.ADD_STR );
		btnEdit = new JButton(mainMenu.EDIT_STR);
		btnDelete = new JButton(mainMenu.DELETE_STR );
		btnView = new JButton(mainMenu.VIEW_STR );
		
		btnAdd.setFont(CustomFont.THboldFont(18));
		btnEdit.setFont(CustomFont.THboldFont(18));
		btnDelete.setFont(CustomFont.THboldFont(18));
		btnView.setFont(CustomFont.THboldFont(18));

		// menu add delete search
		formMenuSystem = new RoundedPanel(15, Color.white);
		formMenuSystem.setOpaque(false);
		formMenuSystem.add(btnAdd);
		formMenuSystem.add(btnEdit);
		formMenuSystem.add(btnDelete);
		formMenuSystem.add(btnView);
		formMenuSystem.setBounds((mainMenu.WIDTH - formMenuSystem.getPreferredSize().width) / 2 , 35,
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
			formSupplierGoodAdd = new FormSupplierGoodAdd(mainMenu);
			formSupplierGoodEdit = new FormSupplierGoodEdit(mainMenu);
			formSupplierGoodDelete = new FormSupplierGoodDelete(mainMenu);
			formSupplierGoodView = new FormSupplierGoodView(mainMenu);
			
			FormPanel.add(formSupplierGoodView, mainMenu.VIEW_STR );
			FormPanel.add(formSupplierGoodAdd, mainMenu.ADD_STR );
			FormPanel.add(formSupplierGoodEdit, mainMenu.EDIT_STR );
			FormPanel.add(formSupplierGoodDelete, mainMenu.DELETE_STR);
			check++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals(mainMenu.ADD_STR )) {
			FormPanel.remove(formSupplierGoodAdd);
			formSupplierGoodAdd = new FormSupplierGoodAdd(mainMenu);
			FormPanel.add(formSupplierGoodAdd, mainMenu.ADD_STR );
			
//			formSupplierGoodAdd.viewBox();
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.ADD_STR );
		}else if (command.equals(mainMenu.EDIT_STR )) {
			FormPanel.remove(formSupplierGoodEdit);
			formSupplierGoodEdit = new FormSupplierGoodEdit(mainMenu);
			FormPanel.add(formSupplierGoodEdit, mainMenu.EDIT_STR);
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.EDIT_STR );
		}else if (command.equals(mainMenu.DELETE_STR )) {
			FormPanel.remove(formSupplierGoodDelete);
			formSupplierGoodDelete = new FormSupplierGoodDelete(mainMenu);
			FormPanel.add(formSupplierGoodDelete, mainMenu.DELETE_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.DELETE_STR );
		}else if (command.equals(mainMenu.VIEW_STR )) {
			FormPanel.remove(formSupplierGoodView);
			formSupplierGoodView = new FormSupplierGoodView(mainMenu);
			FormPanel.add(formSupplierGoodView, mainMenu.VIEW_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.VIEW_STR );
		}
	}
}
