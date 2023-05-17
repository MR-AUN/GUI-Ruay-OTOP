package ruay.admingoods;

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
import ruay.dao.AdminGoodsDAO;

public class FormAdminGoodDelete extends JPanel implements ActionListener{
	
	JTextField idTxt;

	private static final String deleteString = "ลบ";
	GuiMainRuay mainMenu ;
	public FormAdminGoodDelete(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formAdminGood.FORMWIDTH ;
		setOpaque(false);
		setLayout(null);

		// create labels
		JLabel deleteGoodLabel = new JLabel("ลบสินค้า");
		JLabel idLabel = new JLabel("กรอกรหัสสินค้าที่ต้องการลบ:");

		// create buttons
		JButton deleteBtn = new JButton(deleteString);
		
		deleteGoodLabel.setFont(CustomFont.THboldFont(18));
		idLabel.setFont(CustomFont.THboldFont(17));
		deleteBtn.setFont(CustomFont.THboldFont(18));

		// create texts
		idTxt = new JTextField(14);
		idTxt.setFont(CustomFont.THboldFont(17));

		// create control buttons.
		deleteBtn.addActionListener(this);

		// add labels
		add(deleteGoodLabel);
		add(idLabel);

		// add text fields
		add(idTxt);

		// add control buttons
		add(deleteBtn);

		// set sizes and positions for labels
		int labelH = 5 ;
		Dimension size = deleteGoodLabel.getPreferredSize();
		deleteGoodLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
		labelH += 30 ;
		size = idLabel.getPreferredSize();
		idLabel.setBounds((FORMWIDTH ) / 2 - 70, labelH, size.width, size.height);

		// set sizes and positions for labels
		size = idTxt.getPreferredSize();
		idTxt.setBounds((FORMWIDTH ) / 2 -70, labelH + 25, size.width, size.height);

		// set sizes and positions for controls buttons
		labelH += 65 ;
		size = deleteBtn.getPreferredSize();
		deleteBtn.setBounds((FORMWIDTH  - size.width )/2 + 80, labelH, size.width, size.height);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		// user presses "Add"
		if (actionCommand.equals(deleteString)) {
			System.out.println("actionCommand:" + deleteString);
			try {
				// retrieve values from text fields.
				long id = Long.parseLong(idTxt.getText());
				mainMenu.DAOGOOD.deleteGoods(id);
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
