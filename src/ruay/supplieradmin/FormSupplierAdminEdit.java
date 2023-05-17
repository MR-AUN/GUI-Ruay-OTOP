package ruay.supplieradmin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.SupplierDAO;
import ruay.model.SupplierModel;



public class FormSupplierAdminEdit extends JPanel implements ActionListener {

	JTextField idTxt;
	JTextField nameTxt;
	JTextField lastnameTxt;
	JTextArea addressTxt;
	JTextField phoneTxt;

	private static final String editString = "แก้ไข";
	private static final String searchString = "ค้นหา";
	
	GuiMainRuay mainMenu ;
	public FormSupplierAdminEdit(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupAdmin.FORMWIDTH ;
		setLayout(null);
		setOpaque(false);
		
		// create labels
		JLabel editSupplierLabel = new JLabel("แก้ไขข้อมูลผู้ผลิต");
		JLabel idLabel = new JLabel("รหัสผู้ผลิต:");
		JLabel nameLabel = new JLabel("ชื่อต้น:");
		JLabel lastnameLabel = new JLabel("นามสกุล:");
		JLabel addressLabel = new JLabel("ที่อยู่:");
		JLabel phoneLabel = new JLabel("เบอร์ติดต่อ:");

		// create buttons
		JButton editBtn = new JButton(editString);
		JButton searchBtn = new JButton(searchString);
		
		editSupplierLabel.setFont(CustomFont.THboldFont(18));
		idLabel.setFont(CustomFont.THFont(17));
		nameLabel.setFont(CustomFont.THFont(17));
		lastnameLabel.setFont(CustomFont.THFont(17));
		addressLabel.setFont(CustomFont.THFont(17));
		phoneLabel.setFont(CustomFont.THFont(17));

		editBtn.setFont(CustomFont.THboldFont(18));
		searchBtn.setFont(CustomFont.THboldFont(18));
		// create texts
		idTxt = new JTextField(10);
		nameTxt = new JTextField(13);
		addressTxt = new JTextArea(3, 28);
		phoneTxt = new JTextField(10);
		lastnameTxt = new JTextField(13);

		// Border
		addressTxt.setBorder(BorderFactory.createEtchedBorder());

		// setLine
		addressTxt.setLineWrap(true);
		
		nameTxt.setFont(CustomFont.THFont(17));
		lastnameTxt.setFont(CustomFont.THFont(17));
		addressTxt.setFont(CustomFont.THFont(17));

		// create control buttons.
		editBtn.addActionListener(this);
		searchBtn.addActionListener(this);

		// edit labels
		add(editSupplierLabel);
		add(idLabel);
		add(nameLabel);
		add(addressLabel);
		add(phoneLabel);
		add(lastnameLabel);

		// add text fields
		add(idTxt);
		add(nameTxt);
		add(addressTxt);
		add(lastnameTxt);
		add(phoneTxt);

		// add control buttons
		add(editBtn);
		add(searchBtn);

		// set sizes and positions for labels
		int labelH = 5 ;
		int btnSrarch ;
		int labelW = (FORMWIDTH ) / 2  - 150  ;
		Dimension size = editSupplierLabel.getPreferredSize();
		editSupplierLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
		labelH += 30 ;
		btnSrarch = labelH - 3 ;
		size = idLabel.getPreferredSize();
		idLabel.setBounds(labelW, labelH, size.width, size.height);
		labelW += size.width + 20 ;
		size = idTxt.getPreferredSize();
		idTxt.setBounds(labelW, labelH, size.width, size.height);
		labelH += 30 ;
		 labelW = (FORMWIDTH ) / 2  - 150  ;
		size = nameLabel.getPreferredSize();
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
		// set sizes and positions for controls buttons
		size = editBtn.getPreferredSize();
		editBtn.setBounds((FORMWIDTH - size.width)/2 + 120, labelH, size.width, size.height);
		size = searchBtn.getPreferredSize();
		searchBtn.setBounds((FORMWIDTH / 2) + 50, btnSrarch, size.width, size.height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// user presses "Edit"
		if (actionCommand.equals(searchString)) {
			try {
				// retrieve values from text fields.
				int id = Integer.parseInt(idTxt.getText());
				
				SupplierModel sup = mainMenu.DAOSUP.searchSupplier(id);
				if (sup != null) {
					// reset text fields
					nameTxt.setText(sup.getSupName());
					lastnameTxt.setText(sup.getSupLast());
					addressTxt.setText(sup.getSupAddress());
					phoneTxt.setText(sup.getSupPhone());
				} else {
					// reset text fields
					idTxt.setText(null);
					lastnameTxt.setText(null);
					nameTxt.setText(null);
					addressTxt.setText(null);
					phoneTxt.setText(null);
				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
				idTxt.setText(null);
			}catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
			// user presses "Cancel"
		}
		if (actionCommand.equals(editString) && !idTxt.getText().equals("")) {
			try {
				// retrieve values from text fields.
				int id = Integer.parseInt(idTxt.getText());
				String name = nameTxt.getText();
				String lastname = lastnameTxt.getText();
				String address = addressTxt.getText();
				String phone = phoneTxt.getText();
				// create student object
				SupplierModel sup = new SupplierModel(id, name, lastname, address, phone);
				// create sql command
				mainMenu.DAOSUP.editSupplier(sup);
				
				// reset text fields
				idTxt.setText(null);
				lastnameTxt.setText(null);
				nameTxt.setText(null);
				addressTxt.setText(null);
				phoneTxt.setText(null);
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
			// user presses "Cancel"
		}
	}

}
