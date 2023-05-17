package ruay.supplieradmin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ruay.GuiMainRuay;
import ruay.admingoods.FormAdminGoodAdd;
import ruay.component.util.CustomFont;
import ruay.component.util.RoundedPanel;

public class FormSupplierAdmin extends JPanel implements ActionListener {
	
	GuiMainRuay mainMenu;
	
	FormSupplierAdminAdd formSupplierAdminAdd;
	FormSupplierAdminEdit formSupplierAdminEdit;
	FormSupplierAdminDelete formSupplierAdminDelete;
	FormSupplierAdminView formSupplierAdminView;
	
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnView;
	
	public static  int FORMWIDTH = 0;
	public static  int FORMHEIGHT = 0;
	
	JPanel FormPanel;
	JPanel formMenuSystem;
	int check = 1 ;
	public FormSupplierAdmin(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		setOpaque(false);
		setLayout(null);
		JLabel lbHeader = new JLabel("แบบฟอร์มผู้ผลิต");
		lbHeader.setBounds(((mainMenu.WIDTH-lbHeader.getPreferredSize().width)/2)+25, 10, 
				lbHeader.getPreferredSize().width, lbHeader.getPreferredSize().height);
		add(lbHeader);
		
		btnAdd = new JButton(mainMenu.ADD_STR );
		btnEdit = new JButton(mainMenu.EDIT_STR );
		btnDelete = new JButton(mainMenu.DELETE_STR );
		btnView = new JButton(mainMenu.VIEW_STR );
		
		lbHeader.setFont(CustomFont.THboldFont(20));
		btnAdd.setFont(CustomFont.THboldFont(18));
		btnEdit.setFont(CustomFont.THboldFont(18));
		btnDelete.setFont(CustomFont.THboldFont(18));
		btnView.setFont(CustomFont.THboldFont(18));
		
		formMenuSystem = new RoundedPanel(15, Color.white);
		formMenuSystem.setOpaque(false);
		formMenuSystem.add(btnAdd);
		formMenuSystem.add(btnEdit);
		formMenuSystem.add(btnDelete);
		formMenuSystem.add(btnView);
		formMenuSystem.setBounds((mainMenu.WIDTH - formMenuSystem.getPreferredSize().width) / 2 - 20, 35,
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
		if (check == 1 ) {
			formSupplierAdminAdd = new FormSupplierAdminAdd(mainMenu);
			formSupplierAdminEdit = new FormSupplierAdminEdit(mainMenu);
			formSupplierAdminDelete = new FormSupplierAdminDelete(mainMenu);
			formSupplierAdminView = new FormSupplierAdminView(mainMenu);
			
			FormPanel.add(formSupplierAdminView,mainMenu.VIEW_STR );
			FormPanel.add(formSupplierAdminAdd,mainMenu.ADD_STR);
			FormPanel.add(formSupplierAdminEdit,mainMenu.EDIT_STR );
			FormPanel.add(formSupplierAdminDelete,mainMenu.DELETE_STR );
			check++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals(mainMenu.ADD_STR )) {
			FormPanel.remove(formSupplierAdminAdd);
			formSupplierAdminAdd = new FormSupplierAdminAdd(mainMenu);
			FormPanel.add(formSupplierAdminAdd, mainMenu.ADD_STR);
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.ADD_STR );
			
		}else if(command.equals(mainMenu.EDIT_STR )) {
			FormPanel.remove(formSupplierAdminEdit);
			formSupplierAdminEdit = new FormSupplierAdminEdit(mainMenu);
			FormPanel.add(formSupplierAdminEdit,mainMenu.EDIT_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.EDIT_STR );
		}else if (command.equals(mainMenu.DELETE_STR )) {
			FormPanel.remove(formSupplierAdminDelete);
			formSupplierAdminDelete = new FormSupplierAdminDelete(mainMenu);
			FormPanel.add(formSupplierAdminDelete,mainMenu.DELETE_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.DELETE_STR );
		}else if (command.equals(mainMenu.VIEW_STR )) {
			FormPanel.remove(formSupplierAdminView);
			formSupplierAdminView = new FormSupplierAdminView(mainMenu);
			FormPanel.add(formSupplierAdminView,mainMenu.VIEW_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.VIEW_STR );
		}
		
	}
}
