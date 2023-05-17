package ruay.suppliergoods;

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
import ruay.dao.SupplierGoodsDAO;


public class FormSupplierGoodDelete extends JPanel implements ActionListener {
	JTextField idTxt;

	private static final String deleteString = "ลบ";
	
	GuiMainRuay mainMenu;
	public FormSupplierGoodDelete(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupplierGoods.FORMWIDTH ;
		setOpaque(false);
		setLayout(null);
		// create labels
		JLabel deleteStudentLabel = new JLabel("ลบรายการจำหน่ายสินค้า");
		JLabel idLabel = new JLabel("กรอกรหัสรายการจำหน่ายสินค้าที่ต้องการลบ:");

		// create buttons
		JButton deleteBtn = new JButton(deleteString);
		
		deleteStudentLabel.setFont(CustomFont.THboldFont(18));
		idLabel.setFont(CustomFont.THFont(17));
		deleteBtn.setFont(CustomFont.THboldFont(18));
		// create texts
		idTxt = new JTextField(10);

		// create control buttons.
		deleteBtn.addActionListener(this);

		// add labels
		add(deleteStudentLabel);
		add(idLabel);

		// add text fields
		add(idTxt);

		// add control buttons
		add(deleteBtn);

		// set sizes and positions for labels
		Dimension size = deleteStudentLabel.getPreferredSize();
		deleteStudentLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);
		size = idLabel.getPreferredSize();
		idLabel.setBounds((FORMWIDTH ) / 2 - 70, 50, size.width, size.height);

		// set sizes and positions for labels
		size = idTxt.getPreferredSize();
		idTxt.setBounds((FORMWIDTH ) / 2 - 70, 80, size.width, size.height);

		// set sizes and positions for controls buttons
		size = deleteBtn.getPreferredSize();
		deleteBtn.setBounds((FORMWIDTH  - size.width )/2 + 80, 120, size.width, size.height);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// user presses "Add"
		if (actionCommand.equals(deleteString)) {
			try {
				// retrieve values from text fields.
				long id = Long.parseLong(idTxt.getText());
				mainMenu.DAOSUPGOOD.deleteSupGood(id);
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
