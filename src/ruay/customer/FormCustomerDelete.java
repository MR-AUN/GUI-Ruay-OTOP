package ruay.customer;

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
import ruay.dao.CustomerDAO;

public class FormCustomerDelete extends JPanel implements ActionListener {
	JTextField idTxt;
	private static final String deleteString = "ลบ";
	GuiMainRuay mainMenu ;
	public FormCustomerDelete(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formCustomer.FORMWIDTH ;
		setOpaque(false);
		setLayout(null);
		// create labels
		JLabel deleteCustomerLabel = new JLabel("ลบบัญชีผู้ใช้");
		deleteCustomerLabel.setFont(CustomFont.THboldFont(18));
		JLabel idLabel = new JLabel("กรอกรหัสบัญชีผู้ใช้ที่ต้องการลบ:");
		idLabel.setFont(CustomFont.THFont(18));

		// create buttons
		JButton deleteBtn = new JButton(deleteString);
		deleteBtn.setFont(CustomFont.THboldFont(18));

		// create texts
		idTxt = new JTextField(23);

		// create control buttons.
		deleteBtn.addActionListener(this);

		// add labels
		add(deleteCustomerLabel);
		add(idLabel);

		// add text fields
		add(idTxt);

		// add control buttons
		add(deleteBtn);

		int labelH = 30; 
		int labelW = (FORMWIDTH) / 2 - 150 ;
		// set sizes and positions for labels
		Dimension size = deleteCustomerLabel.getPreferredSize();
		deleteCustomerLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);
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
				mainMenu.DAOCUS.deleteCustomer(id);
				// reset text fields
				idTxt.setText(null);
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			}  catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
			// user presses "Cancel"
		}
	}

}
