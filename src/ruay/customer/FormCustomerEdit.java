package ruay.customer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.CustomerDAO;
import ruay.model.CustomerModel;
import ruay.model.RoleModel;



public class FormCustomerEdit extends JPanel implements ActionListener{
	
	JTextField idTxt;
	JTextField nameTxt;
	JTextField usernameTxt;
	JTextField e_mail;
	JTextField lastnameTxt;
	JTextField passwordTxt;
	JComboBox roleBox ;

	
	private static final String editString = "แก้ไข";
	private static final String searchString = "ค้นหา";
	GuiMainRuay mainMenu ;
	public FormCustomerEdit(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formCustomer.FORMWIDTH ;
		setOpaque(false);
		setLayout(null);
		
		JLabel editCustomerLabel = new JLabel("แก้ไขข้อมูล");
		editCustomerLabel.setFont(CustomFont.THboldFont(18));
		JLabel idLabel = new JLabel("รหัสผู้ใช้:");
		idLabel.setFont(CustomFont.THFont(16));
		JLabel nameLabel = new JLabel("ชื่อต้น:");
		nameLabel.setFont(CustomFont.THFont(16));
		JLabel lastnameLabel = new JLabel("นามสกุล:");
		lastnameLabel.setFont(CustomFont.THFont(16));
		JLabel usernameLabel = new JLabel("ชื่อผู้ใช้:");
		usernameLabel.setFont(CustomFont.THFont(16));
		JLabel roleLabel = new JLabel("สิทธิ์:");
		roleLabel.setFont(CustomFont.THFont(16));
		JLabel e_mailLabel = new JLabel("อีเมล์(E-Mail):");
		e_mailLabel.setFont(CustomFont.THFont(16));
		
		JLabel passLabel = new JLabel("รหัสผ่าน:");
		passLabel.setFont(CustomFont.THFont(16));


		// create buttons
		JButton editBtn = new JButton(editString);
		editBtn.setFont(CustomFont.THboldFont(18));
		JButton searchBtn = new JButton(searchString);
		searchBtn.setFont(CustomFont.THboldFont(18));

		// create texts
		idTxt = new JTextField(13);
		nameTxt = new JTextField(13);
		lastnameTxt = new JTextField(13);
		usernameTxt = new JTextField(13);
		e_mail = new JTextField(28);
		passwordTxt = new JTextField(13);
		Vector<RoleModel> roleList = mainMenu.DAOROLE.viewRole();
		roleBox = new JComboBox(roleList) ;
		roleBox.setFont(CustomFont.THboldFont(18));

		nameTxt.setFont(CustomFont.THFont(17));
		lastnameTxt.setFont(CustomFont.THFont(17));
		

		// create control buttons.
		editBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		
		roleBox.addActionListener(this);

		// edit labels
		add(editCustomerLabel);
		add(idLabel);
		add(nameLabel);
		add(usernameLabel);
		add(roleLabel);
		add(e_mailLabel);
		add(lastnameLabel);
		add(passLabel);

		
		add(nameTxt);
		add(idTxt);
		add(usernameTxt);
		add(e_mail);
		add(lastnameTxt);
		add(passwordTxt);
		add(roleBox);

		// add control buttons
		add(editBtn);
		add(searchBtn);

		int labelH = 5; 
		int labelW = 0 ;
		// set sizes and positions for labels
		Dimension size = editCustomerLabel.getPreferredSize();
		editCustomerLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
		
		labelH += 30 ;
		labelW = (FORMWIDTH ) / 2  - 150 ;
		size = idLabel.getPreferredSize();
		idLabel.setBounds(labelW, labelH, size.width, size.height);
		labelW += size.width + 20 ;
		size = idTxt.getPreferredSize();
		idTxt.setBounds(labelW , labelH, size.width, size.height);
		labelW += size.width + 30 ;
		size = searchBtn.getPreferredSize();
		searchBtn.setBounds(labelW, labelH - 4, size.width, size.height);
		
		labelH += 30 ;
		
		size = nameLabel.getPreferredSize();
		labelW = (FORMWIDTH ) / 2  - 150 ;
		nameLabel.setBounds(labelW, labelH, size.width, size.height);
		size = nameTxt.getPreferredSize();
		nameTxt.setBounds(labelW, labelH + 20, size.width, size.height);
		labelW += size.width + 20 ;
		size = lastnameLabel.getPreferredSize();
		lastnameLabel.setBounds(labelW, labelH, size.width, size.height);
		size = lastnameTxt.getPreferredSize();
		lastnameTxt.setBounds(labelW  , labelH+20, size.width, size.height);
		labelH += 50 ;
		size = e_mailLabel.getPreferredSize();
		labelW = (FORMWIDTH ) / 2  - 150 ;
		e_mailLabel.setBounds(labelW, labelH, size.width, size.height);
		size = e_mail.getPreferredSize();
		e_mail.setBounds(labelW , labelH + 20, size.width, size.height);
		labelH += 50 ;
		size = usernameLabel.getPreferredSize();
		usernameLabel.setBounds(labelW, labelH, size.width, size.height);
		size = usernameTxt.getPreferredSize();
		usernameTxt.setBounds(labelW, labelH+ 20, size.width, size.height);
		labelW += size.width + 20 ;
		size = passLabel.getPreferredSize();
		passLabel.setBounds(labelW, labelH, size.width, size.height);
		size = passwordTxt.getPreferredSize();
		passwordTxt.setBounds(labelW, labelH + 20, size.width, size.height);
		labelH += 50 ;
		labelW = (FORMWIDTH ) / 2  - 150 ;
		size = roleLabel.getPreferredSize();
		roleLabel.setBounds(labelW, labelH, size.width, size.height);
		labelW += size.width + 30 ;
		size = roleBox.getPreferredSize();
		roleBox.setBounds(labelW, labelH, size.width, size.height);
		// set sizes and positions for controls buttons
		labelH += 30 ;
		size = editBtn.getPreferredSize();
		editBtn.setBounds((FORMWIDTH  - size.width) /2  + 120, labelH, size.width, size.height);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals(searchString)) {
			try {
				// retrieve values from text fields.
				long id = Long.parseLong(idTxt.getText());
				CustomerModel cus = mainMenu.DAOCUS.searchCustomer(id);
				if (cus != null) {
					nameTxt.setText(cus.getCusName());
					usernameTxt.setText(cus.getCusUsername());
					e_mail.setText(cus.getCusEmail());
					lastnameTxt.setText(cus.getCusLast());
					passwordTxt.setText(cus.getCusPass());
					roleBox.setSelectedIndex(cus.getRoleId()-1);
				} else {
					// reset text fields
					idTxt.setText(null);
					nameTxt.setText(null);
					lastnameTxt.setText(null);
					e_mail.setText(null);
					usernameTxt.setText(null);
					passwordTxt.setText(null);

				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
				idTxt.setText(null);
			}catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}
		
		if (actionCommand.equals(editString) && !idTxt.getText().equals("")) {
			try {
				// retrieve values from text fields.
				int id = Integer.parseInt(idTxt.getText());
				String name = nameTxt.getText();
				String username = usernameTxt.getText();
				String email = e_mail.getText();
				String lastname = lastnameTxt.getText();
				String pass = passwordTxt.getText();
				RoleModel role = (RoleModel) roleBox.getSelectedItem();
				int roleId = role.getRole_id();
				
				CustomerModel cus = new CustomerModel(id, name, lastname, username, email, pass, roleId);
//				
				mainMenu.DAOCUS.editCustomer(cus);
				
				idTxt.setText(null);
				nameTxt.setText(null);
				lastnameTxt.setText(null);
				e_mail.setText(null);
				usernameTxt.setText(null);
				passwordTxt.setText(null);
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
			// user presses "Cancel"
		} 
	}
	
}
