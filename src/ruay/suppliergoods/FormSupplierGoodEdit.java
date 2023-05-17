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


public class FormSupplierGoodEdit extends JPanel implements ActionListener {

	JTextField idTxt;
	JComboBox id_supTxt;
	JTextField priceTxt;
	JComboBox id_goodTxt;
//	JComboBox id_goodsup;
	
	private static final String editString = "แก้ไข";
	private static final String searchString = "ค้นหา";
	GuiMainRuay mainMenu;
	public FormSupplierGoodEdit(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formSupplierGoods.FORMWIDTH ;
		setOpaque(false);
		setLayout(null);
		// create labels
		JLabel editSupgoodLabel = new JLabel("แก้ไขรายการจำหน่ายสินค้า");

		// JLabel idLabel = new JLabel("ID:");
		JLabel idLabel = new JLabel("รหัส:");
		JLabel id_supLabel = new JLabel("เลือกผู้ผลิต:");
		JLabel id_goodLabel = new JLabel("เลือกสินค้า:");
//		JLabel id_goodGroupLabel = new JLabel("เลือกกลุ่มสินค้า:");
		JLabel priceLabel = new JLabel("จำนวนราคา:");
		// create buttons
		JButton editBtn = new JButton(editString);
		JButton searchBtn = new JButton(searchString);
		
		
		editSupgoodLabel.setFont(CustomFont.THboldFont(18));
		idLabel.setFont(CustomFont.THFont(17));
		id_supLabel.setFont(CustomFont.THFont(17));
		id_goodLabel.setFont(CustomFont.THFont(17));
		priceLabel.setFont(CustomFont.THFont(17));
//		id_goodGroupLabel.setFont(CustomFont.THFont(17));
		editBtn.setFont(CustomFont.THboldFont(18));
		searchBtn.setFont(CustomFont.THboldFont(18));

		// create texts
		idTxt = new JTextField(5);
		Vector<SupplierModel> supplier = mainMenu.DAOSUP.viewSupplier();
		id_supTxt = new JComboBox(supplier);
		id_supTxt.setSelectedItem(null);
		priceTxt = new JTextField(10);
		Vector<CombinGroupModel> combin = mainMenu.DAOCOMBIN.viewCombinGroup(); 
		id_goodTxt = new JComboBox(combin);
		id_goodTxt.setSelectedItem(null);;
//		id_goodsup = new JComboBox();
		
		id_supTxt.setFont(CustomFont.THboldFont(17));
		id_goodTxt.setFont(CustomFont.THboldFont(17));
//		id_goodsup.setFont(CustomFont.THboldFont(17));
		// create control buttons.
		editBtn.addActionListener(this);
		searchBtn.addActionListener(this);

		// edit labels
		add(idLabel);
		add(editSupgoodLabel);
		add(id_supLabel);
		add(id_goodLabel);
//		add(id_goodGroupLabel);
		add(priceLabel);

		// add text fields
		add(idTxt);
		add(id_supTxt);
		add(priceTxt);
		add(id_goodTxt);
//		add(id_goodsup);
		// add control buttons
		add(editBtn);
		add(searchBtn);

		
		int labelH = 5;
		Dimension size = editSupgoodLabel.getPreferredSize();
		int labelW = (FORMWIDTH - size.width) / 2;
		editSupgoodLabel.setBounds(labelW, labelH, size.width, size.height);
		// size = idLabel.getPreferredSize();
		// idLabel.setBounds(10, 20, size.width, size.height);
		labelH += 35;
		labelW = (FORMWIDTH) / 2 - 70;
		size = idLabel.getPreferredSize();
		idLabel.setBounds(labelW, labelH, size.width, size.height);
		labelW += (size.width+20);
		size = idTxt.getPreferredSize();
		idTxt.setBounds(labelW, labelH, size.width, size.height);
		labelW += size.width + 20 ;
		size = searchBtn.getPreferredSize();
		searchBtn.setBounds(labelW , labelH -5, size.width, size.height);
		labelW = (FORMWIDTH) / 2 - 70;
		labelH += 35 ;
		size = id_supLabel.getPreferredSize();
		id_supLabel.setBounds(labelW, labelH, size.width, size.height);
		size = id_supTxt.getPreferredSize();
		id_supTxt.setBounds(labelW, labelH + 25, size.width, size.height);
		size = id_goodLabel.getPreferredSize();
		labelH += 60;
		id_goodLabel.setBounds(labelW, labelH, size.width, size.height);
		size = id_goodTxt.getPreferredSize();
		id_goodTxt.setBounds(labelW, labelH + 25, size.width, size.height);
//		labelH += 60;
//		size = id_goodGroupLabel.getPreferredSize();
//		id_goodGroupLabel.setBounds(labelW, labelH, size.width, size.height);
//		size = id_goodsup.getPreferredSize();
//		id_goodsup.setBounds(labelW, labelH + 25, 130, size.height);
		labelH += 60;
		size = priceLabel.getPreferredSize();
		priceLabel.setBounds(labelW, labelH, size.width, size.height);
		size = priceTxt.getPreferredSize();
		priceTxt.setBounds(labelW, labelH+25, size.width, size.height);
		labelH += 55 ;
		size = editBtn.getPreferredSize();
		editBtn.setBounds(labelW+ size.width, labelH, size.width, size.height);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// user presses "Edit"
		if (actionCommand.equals(searchString)) {
			try {
				// retrieve values from text fields.
				long id = Long.parseLong(idTxt.getText());
				
				SupplierGoodsModel supgood = mainMenu.DAOSUPGOOD.searchSupGood(id);
				if (supgood != null) {
					id_supTxt.getModel().setSelectedItem(supgood.getSup());
					id_goodTxt.getModel().setSelectedItem(supgood.getCombin());
					priceTxt.setText(String.valueOf(supgood.getSupgoodPrice()));
					
				} else {
					// reset text fields
					idTxt.setText(null);
					id_supTxt.setSelectedItem(null);
					id_goodTxt.setSelectedItem(null);
					priceTxt.setText(null);
					
				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
				idTxt.setText(null);
				
			}catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}
		if (actionCommand.equals(editString)) {
			try {
				// retrieve values from text fields.
				
				
				int id = Integer.parseInt(idTxt.getText());
				SupplierModel sup = (SupplierModel) id_supTxt.getSelectedItem();
				CombinGroupModel good = (CombinGroupModel) id_goodTxt.getSelectedItem();
				int price = Integer.parseInt(priceTxt.getText());
				SupplierGoodsModel supgood = new SupplierGoodsModel(id, sup, good, price);
				mainMenu.DAOSUPGOOD.editSupGood(supgood);
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}
	}

}
