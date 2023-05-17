package ruay.admingoods;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.component.util.RoundedPanel;

public class FormAdminGood extends JPanel implements ActionListener {

	JScrollPane scrollPaneAdd;
	JScrollPane scrollPaneEdit;

	GuiMainRuay mainMenu;
	
	FormAdminGoodAdd formAdminGoodAdd ;
	FormAdminGoodEdit formAdminGoodEdit;
	FormAdminGoodDelete formAdminGoodDelete;
	FormAdminGoodView formAdminGoodView ;
	FormAdminGoodCategories formAdminGoodCategories;
	FormAdminGoodCombin formAdminGoodCombin;

	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnView;
	JButton btnCate ;
	JButton btnCom ;

	JPanel FormPanel;
	JPanel formMenuSystem;
	public static  int FORMWIDTH = 0;
	public static  int FORMHEIGHT = 0;
	
	int check = 1 ;

	public FormAdminGood(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		setOpaque(false);
		setLayout(null);
		JLabel lbHeader = new JLabel("แบบฟอร์มสินค้า");
		lbHeader.setFont(CustomFont.THboldFont(20));
		lbHeader.setBounds((mainMenu.WIDTH - lbHeader.getPreferredSize().width) / 2, 10,
				lbHeader.getPreferredSize().width, lbHeader.getPreferredSize().height);
		add(lbHeader);

		btnAdd = new JButton(mainMenu.ADD_STR );
		btnEdit = new JButton(mainMenu.EDIT_STR);
		btnDelete = new JButton(mainMenu.DELETE_STR);
		btnView = new JButton(mainMenu.VIEW_STR );
		btnCate = new JButton(mainMenu.CATE_STR);
		btnCom = new JButton(mainMenu.COM_STR);
		
		btnAdd.setFont(CustomFont.THboldFont(18));
		btnEdit.setFont(CustomFont.THboldFont(18));
		btnDelete.setFont(CustomFont.THboldFont(18));
		btnView.setFont(CustomFont.THboldFont(18));
		btnCate.setFont(CustomFont.THboldFont(18));
		btnCom.setFont(CustomFont.THboldFont(18));
		// menu add delete search
		formMenuSystem = new RoundedPanel(15, Color.white);
		formMenuSystem.setOpaque(false);
		formMenuSystem.add(btnAdd);
		formMenuSystem.add(btnCom);
		formMenuSystem.add(btnCate);
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
		btnCate.addActionListener(this);
		btnCom.addActionListener(this);

		FormPanel = new JPanel(new CardLayout());
		FormPanel.setOpaque(false);
		FORMWIDTH = mainMenu.WIDTH - 50;
		FORMHEIGHT = (mainMenu.HEIGHT - mainMenu.HEIGHTNAVBER) -(90+50) ;
		FormPanel.setBounds((mainMenu.WIDTH - FORMWIDTH) /2 - 5 , 90,FORMWIDTH , FORMHEIGHT);
		add(FormPanel);
		

		
		
	}
	
	public void formMenu() {
		if(check == 1) {
			formAdminGoodAdd = new FormAdminGoodAdd(mainMenu);
			formAdminGoodEdit = new FormAdminGoodEdit(mainMenu);
			formAdminGoodDelete = new FormAdminGoodDelete(mainMenu);
			formAdminGoodView = new FormAdminGoodView(mainMenu);
			formAdminGoodCategories = new FormAdminGoodCategories(mainMenu);
			formAdminGoodCombin = new FormAdminGoodCombin(mainMenu);
			
			scrollPaneAdd = new JScrollPane(formAdminGoodAdd);
			scrollPaneAdd.getViewport().setOpaque(false);
			scrollPaneEdit = new JScrollPane(formAdminGoodEdit);
			scrollPaneEdit.getViewport().setOpaque(false);
			FormPanel.add(formAdminGoodView, mainMenu.VIEW_STR );
			FormPanel.add(scrollPaneAdd, mainMenu.ADD_STR );
			FormPanel.add(formAdminGoodCategories,mainMenu.CATE_STR);
			FormPanel.add(formAdminGoodCombin,mainMenu.COM_STR);
			FormPanel.add(scrollPaneEdit, mainMenu.EDIT_STR );
			FormPanel.add(formAdminGoodDelete, mainMenu.DELETE_STR );
			check ++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals(mainMenu.ADD_STR )) {
			FormPanel.remove(scrollPaneAdd);
			scrollPaneAdd = new JScrollPane();
			scrollPaneAdd.getViewport().setOpaque(false);
			formAdminGoodAdd = new FormAdminGoodAdd(mainMenu);
			scrollPaneAdd.setViewportView(formAdminGoodAdd);
			FormPanel.add(scrollPaneAdd, mainMenu.ADD_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.ADD_STR );
		}else if (command.equals(mainMenu.CATE_STR))  {
			FormPanel.remove(formAdminGoodCategories);
			formAdminGoodCategories = new FormAdminGoodCategories(mainMenu);
			FormPanel.add(formAdminGoodCategories, mainMenu.CATE_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.CATE_STR );
		}else if (command.equals(mainMenu.COM_STR))  {
			FormPanel.remove(formAdminGoodCombin);
			formAdminGoodCombin = new FormAdminGoodCombin(mainMenu);
			FormPanel.add(formAdminGoodCombin,mainMenu.COM_STR);
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.COM_STR );
		}  else if(command.equals(mainMenu.EDIT_STR )) {
			FormPanel.remove(scrollPaneEdit);
			scrollPaneEdit = new JScrollPane();
			scrollPaneEdit.getViewport().setOpaque(false);
			formAdminGoodEdit = new FormAdminGoodEdit(mainMenu);
			scrollPaneEdit.setViewportView(formAdminGoodEdit);
			FormPanel.add(scrollPaneEdit, mainMenu.EDIT_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.EDIT_STR );
		}else if(command.equals( mainMenu.DELETE_STR )) {
			FormPanel.remove(formAdminGoodDelete);
			formAdminGoodDelete = new FormAdminGoodDelete(mainMenu);
			FormPanel.add(formAdminGoodDelete, mainMenu.DELETE_STR );
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.DELETE_STR );
		}else if (command.equals(mainMenu.VIEW_STR )) {
			FormPanel.remove(formAdminGoodView);
			formAdminGoodView = new FormAdminGoodView(mainMenu);
			FormPanel.add(formAdminGoodView, mainMenu.VIEW_STR );
			
			
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel,mainMenu.VIEW_STR );
		}
		
	}
}
