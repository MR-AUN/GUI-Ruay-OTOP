package ruay.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.relation.Role;
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


public class FormCustomerAdd extends JPanel implements ActionListener{
	
	
	JTextField nameTxt;
	JTextField usernameTxt;
	JTextField e_mail;
	JTextField lastnameTxt;
	JTextField passwordTxt;
	JComboBox roleBox ;

	JPanel pane2;
	JLabel text;
	GuiMainRuay mainMenu ;
	private static final String addString = "เพิ่ม";
	public static boolean setValidEmailAddress(String email) {
		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(email);
		return matcher.find();
	}
	public FormCustomerAdd(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formCustomer.FORMWIDTH ;
		setOpaque(false);
		setLayout(null);
		pane2 = new JPanel();
		text = new JLabel();
		add(pane2);
		pane2.setBackground(new Color(249, 51, 51));
		
		pane2.setLayout(new BorderLayout());
		pane2.setVisible(false);
		pane2.add(text);
		text.setHorizontalAlignment(JLabel.CENTER);

		// Create pane as container
		// set layout manager to manual

		// create labels
		JLabel addCustomerLabel = new JLabel("เพิ่มรายชื่อ");
		addCustomerLabel.setFont(CustomFont.THboldFont(18));
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
		JButton addBtn = new JButton(addString);
		addBtn.setFont(CustomFont.THboldFont(18));

		// create texts
		// idTxt = new JTextField(10);
		nameTxt = new JTextField(13);
		lastnameTxt = new JTextField(13);
		usernameTxt = new JTextField(13);
		e_mail = new JTextField(28);
		passwordTxt = new JTextField(13);
		Vector<RoleModel> roleList = mainMenu.DAOROLE.viewRole();
		roleBox = new JComboBox(roleList) ;
		roleBox.setFont(CustomFont.THboldFont(18));
		roleBox.setEditable(true);
		
		nameTxt.setFont(CustomFont.THFont(17));
		lastnameTxt.setFont(CustomFont.THFont(17));

		// create control buttons.
		addBtn.addActionListener(this);
		
		roleBox.addActionListener(this);

		// add labels
		add(addCustomerLabel);
		// pane.add(idLabel);
		add(nameLabel);
		add(usernameLabel);
		add(roleLabel);
		add(e_mailLabel);
		add(lastnameLabel);
		add(passLabel);

		// add text fields
		// pane.add(idTxt);
		add(nameTxt);
		add(usernameTxt);
		add(e_mail);
		add(lastnameTxt);
		add(passwordTxt);
		add(roleBox);

		// add control buttons
		add(addBtn);

		// set sizes and positions for labels
		int labelH = 5; 
		int labelW = 0 ;
		Dimension size = addCustomerLabel.getPreferredSize();
		addCustomerLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
		// size = idLabel.getPreferredSize();
		// idLabel.setBounds(10, 20, size.width, size.height);
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
		pane2.setBounds((FORMWIDTH - 250) / 2 + 300, labelH + 8, 280, 40);
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

		labelH += 30 ;
		// set sizes and positions for controls buttons
		size = addBtn.getPreferredSize();
		addBtn.setBounds((FORMWIDTH  - size.width ) / 2 + 120, labelH, size.width, size.height);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals(addString)) {
			try {
				String name = nameTxt.getText();
				String username = usernameTxt.getText();
				String email = e_mail.getText();
				String user = lastnameTxt.getText();
				String pass = passwordTxt.getText();
				RoleModel role = (RoleModel) roleBox.getSelectedItem();
				int roleId = role.getRole_id();

				boolean check = setValidEmailAddress(email);
				if (name.equals("") || username.equals("")  || email.equals("") || user.equals("")||pass.equals("")) {
					String error = "ข้อมูลไม่ครบถ้วน กรุณากรอกใหม่อีกครั้ง!!!";
					text.setText(error);
					text.setFont(CustomFont.THboldFont(18));
					text.setForeground(Color.WHITE);
					pane2.setVisible(true);
				} else if (check) {
					pane2.setVisible(false);
					// create student object
					CustomerModel cus = new CustomerModel(name, user, username, email, pass, roleId);
					
					mainMenu.DAOCUS.addCustomer(cus);
					nameTxt.setText(null);
					usernameTxt.setText(null);
					e_mail.setText(null);
					lastnameTxt.setText(null);
					passwordTxt.setText(null);
				} else {
					String error = "ข้อมูลอีเมลไม่ถูกต้อง!!!";
					text.setText(error);
					text.setFont(CustomFont.THboldFont(18));
					text.setForeground(Color.WHITE);
					pane2.setVisible(true);
				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
				ex.getStackTrace();
			}
			// user presses "Cancel"
		}
		
		if(actionCommand.equals("comboBoxChanged")) {
			System.out.println();
			RoleModel item = (RoleModel)roleBox.getSelectedItem();
	        System.out.println( item.getRole_id() + " : " + item.getRole_name() );
		}
	}

}
