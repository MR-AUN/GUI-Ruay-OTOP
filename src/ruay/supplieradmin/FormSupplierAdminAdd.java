package ruay.supplieradmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.SupplierDAO;
import ruay.model.SupplierModel;


public class FormSupplierAdminAdd extends JPanel implements ActionListener{

	JTextField nameTxt;
	JTextField lastnameTxt;
	JTextArea addressTxt;
	JTextField phoneTxt;

	
	GuiMainRuay mainMenu;
	private static final String addString = "เพิ่ม";
	
	public FormSupplierAdminAdd(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupAdmin.FORMWIDTH ;


		setLayout(null);
		setOpaque(false);
		// create labels
		JLabel addSupplierLabel = new JLabel("เพิ่มรายชื่อผู้ผลิต");
		// JLabel idLabel = new JLabel("ID:");
		JLabel nameLabel = new JLabel("ชื่อต้น:");
		JLabel lastnameLabel = new JLabel("นามสกุล:");
		JLabel addressLabel = new JLabel("ที่อยู่:");
		JLabel phoneLabel = new JLabel("เบอร์ติดต่อ:");

		// create buttons
		JButton addBtn = new JButton(addString);
		
		addSupplierLabel.setFont(CustomFont.THboldFont(18));
		nameLabel.setFont(CustomFont.THFont(17));
		lastnameLabel.setFont(CustomFont.THFont(17));
		addressLabel.setFont(CustomFont.THFont(17));
		phoneLabel.setFont(CustomFont.THFont(17));
		
		addBtn.setFont(CustomFont.THboldFont(18));
		
		// create texts
		// idTxt = new JTextField(10);
		nameTxt = new JTextField(13);
		addressTxt = new JTextArea(3, 28);
		phoneTxt = new JTextField(10);
		lastnameTxt = new JTextField(13);
		
		nameTxt.setFont(CustomFont.THFont(17));
		lastnameTxt.setFont(CustomFont.THFont(17));
		addressTxt.setFont(CustomFont.THFont(17));

		// Border
		addressTxt.setBorder(BorderFactory.createEtchedBorder());

		// setLine
		addressTxt.setLineWrap(true);

		// create control buttons.
		addBtn.addActionListener(this);

		// add labels
		add(addSupplierLabel);
		// pane.add(idLabel);
		add(nameLabel);
		add(addressLabel);
		add(phoneLabel);
		add(lastnameLabel);

		// add text fields
		// pane.add(idTxt);
		add(nameTxt);
		add(lastnameTxt);
		add(addressTxt);
		add(phoneTxt);

		// add control buttons
		add(addBtn);

		int labelH = 5 ;
		int labelW ;
		Dimension size = addSupplierLabel.getPreferredSize();
		addSupplierLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
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
		labelW = (FORMWIDTH ) / 2  - 150 ;
		size = addressLabel.getPreferredSize();
		addressLabel.setBounds(labelW, labelH, size.width, size.height);
		labelH += 25 ;
		size = addressTxt.getPreferredSize();
		addressTxt.setBounds(labelW, labelH, size.width, size.height);
		labelH += size.height + 20 ;;
		size = phoneLabel.getPreferredSize();
		phoneLabel.setBounds(labelW, labelH, size.width, size.height);
		labelW += size.width + 20 ;
		size = phoneTxt.getPreferredSize();
		phoneTxt.setBounds(labelW, labelH, size.width, size.height);
		
		labelH += 50 ;
		labelW = (FORMWIDTH   ) / 2;
		size = addBtn.getPreferredSize();
		addBtn.setBounds(labelW, labelH, size.width, size.height);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals(addString)) {
			System.out.println("actionCommand:" + addString);
			try {
				String name = nameTxt.getText();
				String lastname = lastnameTxt.getText();
				String address = addressTxt.getText();
				String phone = phoneTxt.getText();
				if (!name.equals("") || !address.equals("") || !phone.equals("") || !lastname.equals("")) {
					// create student object
					SupplierModel sup = new SupplierModel(name, lastname, address, phone);
					mainMenu.DAOSUP.addSupper(sup);
					
					nameTxt.setText(null);
					lastnameTxt.setText(null);
					addressTxt.setText(null);
					phoneTxt.setText(null);
				} 
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
				ex.getStackTrace();
			}
			// user presses "Cancel"
		} 
	}

}
