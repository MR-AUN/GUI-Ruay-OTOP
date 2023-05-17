package ruay.supplieradmin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.SupplierDAO;



public class FormSupplierAdminDelete extends JPanel implements ActionListener {
	JTextField idTxt;

	private static final String deleteString = "ลบ";
	
	GuiMainRuay mainMenu ;
	public FormSupplierAdminDelete(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupAdmin.FORMWIDTH ;
		setOpaque(false);
		setLayout(null);
		// create labels
		JLabel deleteSupplierLabel = new JLabel("ลบรายชื่อผู้ผลิต");
		JLabel idLabel = new JLabel("กรอกรหัสผู้ผลิตที่ต้องการลบ:");

		// create buttons
		JButton deleteBtn = new JButton(deleteString);
		
		deleteSupplierLabel.setFont(CustomFont.THboldFont(18));
		idLabel.setFont(CustomFont.THFont(17));
		
		deleteBtn.setFont(CustomFont.THboldFont(18));

		// create texts
		idTxt = new JTextField(10);

		// create control buttons.
		deleteBtn.addActionListener(this);

		// add labels
		add(deleteSupplierLabel);
		add(idLabel);

		// add text fields
		add(idTxt);

		// add control buttons
		add(deleteBtn);

		int labelH = 30; 
		int labelW = (FORMWIDTH) / 2 - 150 ;
		// set sizes and positions for labels
		Dimension size = deleteSupplierLabel.getPreferredSize();
		deleteSupplierLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);
		size = idLabel.getPreferredSize();
		idLabel.setBounds(labelW, labelH, size.width, size.height);

		// set sizes and positions for labels
		labelH += 30 ;
		size = idTxt.getPreferredSize();
		idTxt.setBounds(labelW, labelH, size.width, size.height);
		labelW += size.width + 10 ;
		// set sizes and positions for controls buttons
		size = deleteBtn.getPreferredSize();
		deleteBtn.setBounds(labelW, labelH - 5, size.width, size.height);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// user presses "Add"
		if (actionCommand.equals(deleteString)) {
			try {
				// retrieve values from text fields.
				long id = Long.parseLong(idTxt.getText());
				mainMenu.DAOSUP.deleteSupplier(id);
				// reset text fields
				idTxt.setText(null);
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
			// user presses "Cancel"
		}
	}

}
