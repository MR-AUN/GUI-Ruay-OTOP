package ruay.suppliergoods;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.SupplierGoodsDAO;
import ruay.model.AdminGoodsModel;
import ruay.model.CombinGroupModel;
import ruay.model.GoodGroupOptionView;
import ruay.model.OptionGoodModel;
import ruay.model.SupplierGoodsModel;
import ruay.model.SupplierModel;

public class FormSupplierGoodAdd extends JPanel implements ActionListener {
	JComboBox id_supTxt;
	JTextField priceTxt;
	JComboBox id_CombingoodTxt;
//	JComboBox id_goodsup;

	private static final String addString = "เพิ่ม";
	GuiMainRuay mainMenu;

	public FormSupplierGoodAdd(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupplierGoods.FORMWIDTH;
		setOpaque(false);
		setLayout(null);
		// create labels
		JLabel addSupplierLabel = new JLabel("เพิ่มรายการจำหน่ายสินค้า");
		// JLabel idLabel = new JLabel("ID:");
		JLabel id_supLabel = new JLabel("เลือกผู้ผลิต:");
		JLabel id_goodLabel = new JLabel("เลือกสินค้า:");
		JLabel priceLabel = new JLabel("จำนวนราคา:");

		// create buttons
		JButton addBtn = new JButton(addString);

		addSupplierLabel.setFont(CustomFont.THboldFont(18));
		id_supLabel.setFont(CustomFont.THFont(17));
		id_goodLabel.setFont(CustomFont.THFont(17));
		priceLabel.setFont(CustomFont.THFont(17));
		addBtn.setFont(CustomFont.THboldFont(18));
		// create texts
		// idTxt = new JTextField(10);
		Vector<SupplierModel> supplier = mainMenu.DAOSUP.viewSupplier();
		id_supTxt = new JComboBox(supplier);
		priceTxt = new JTextField(10);
		Vector<CombinGroupModel> combin = mainMenu.DAOCOMBIN.viewCombinGroup(); 
		id_CombingoodTxt = new JComboBox(combin);

		id_supTxt.setSelectedItem(null);
		id_CombingoodTxt.setSelectedItem(null);

		id_supTxt.setFont(CustomFont.THboldFont(17));
		id_CombingoodTxt.setFont(CustomFont.THboldFont(17));
//		id_goodsup = new JComboBox();
//		id_goodsup.setFont(CustomFont.THboldFont(17));
		// create control buttons.
		addBtn.addActionListener(this);
		
		

		// add labels
		add(addSupplierLabel);
		// pane.add(idLabel);
		add(id_supLabel);
		add(id_goodLabel);
		add(priceLabel);

		// add text fields
		// pane.add(idTxt);
		add(id_supTxt);

		add(priceTxt);
		add(id_CombingoodTxt);
//		add(id_goodsup);

		// add control buttons
		add(addBtn);

		// set sizes and positions for labels
		int labelH = 5;
		Dimension size = addSupplierLabel.getPreferredSize();
		int labelW = (FORMWIDTH - size.width) / 2;
		addSupplierLabel.setBounds(labelW, labelH, size.width, size.height);
		// size = idLabel.getPreferredSize();
		// idLabel.setBounds(10, 20, size.width, size.height);
		labelH += 35;
		labelW = (FORMWIDTH) / 2 - 70;
		size = id_supLabel.getPreferredSize();
		id_supLabel.setBounds(labelW, labelH, size.width, size.height);
		size = id_supTxt.getPreferredSize();
		id_supTxt.setBounds(labelW, labelH + 25, size.width, size.height);
		size = id_goodLabel.getPreferredSize();
		labelH += 60;
		id_goodLabel.setBounds(labelW, labelH, size.width, size.height);
		size = id_CombingoodTxt.getPreferredSize();
		id_CombingoodTxt.setBounds(labelW, labelH + 25, size.width, size.height);
//		labelH += 60;
//		size = id_goodGroupLabel.getPreferredSize();
//		id_goodGroupLabel.setBounds(labelW, labelH, size.width, size.height);
//		size = id_goodsup.getPreferredSize();
//		id_goodsup.setBounds(labelW, labelH + 25, 130, size.height);
		labelH += 60;
		size = priceLabel.getPreferredSize();
		priceLabel.setBounds(labelW, labelH, size.width, size.height);
//		// set sizes and positions for labels

		size = priceTxt.getPreferredSize();
		priceTxt.setBounds(labelW, labelH+25, size.width, size.height);
//		
//
//		// set sizes and positions for controls buttons
		labelH += 55;
		size = addBtn.getPreferredSize();
		addBtn.setBounds((FORMWIDTH - size.width) / 2 , labelH, size.width, size.height);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals(addString)) {
			try {
				SupplierModel sup = (SupplierModel) id_supTxt.getSelectedItem();
				CombinGroupModel Combingood = (CombinGroupModel) id_CombingoodTxt.getSelectedItem();
				int price = Integer.parseInt(priceTxt.getText());
				SupplierGoodsModel supgood = new SupplierGoodsModel(sup, Combingood, price);

				mainMenu.DAOSUPGOOD.addSupGood(supgood);
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
