package ruay.admingoods;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.model.AdminGoodsModel;
import ruay.model.CategoriesModel;
import ruay.model.CombinGroupModel;
import ruay.model.GoodGroupOptionView;
import ruay.model.GroupGoodModel;
import ruay.model.OptionGoodModel;

public class FormAdminGoodCombin extends JPanel implements ActionListener {
	
//	JTextField CombintPriceBox ;
	JTextField CombintStockBox ;
	JComboBox CombinGood ;
	
	Vector<GroupGoodModel> group = null;
	Vector<OptionGoodModel> optionList = null;
	GuiMainRuay mainMenu;
	
	int labelHS ;
	int labelWS ;


	public FormAdminGoodCombin(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formAdminGood.FORMWIDTH;
		setOpaque(false);
		setLayout(null);
		

		JLabel CombinLabel = new JLabel("จัดกลุ่มสินค้าสินค้า");
		JLabel CombinPriceLabel = new JLabel("ราคา:");
		JLabel CombinStockLabel = new JLabel("จำนวนสินค้า");
		JLabel CombinGoodLabel = new JLabel("สินค้า:");

		// create buttons
		JButton addBtn = new JButton(mainMenu.ADD_STR);
		
//		CombintPriceBox = new JTextField(10);
		CombintStockBox = new JTextField(10);
		
		Vector<AdminGoodsModel> good = mainMenu.DAOGOOD.viewGoods();
		CombinGood = new JComboBox(good);
		CombinGood.setSelectedItem(null);
		JPanel myGridLayoutUsingJPanel = new JPanel(new GridLayout(0, 6,10,10));
		myGridLayoutUsingJPanel.setOpaque(false);
		
		CombintStockBox.setFont(CustomFont.THboldFont(16));
//		CombintPriceBox.setFont(CustomFont.THboldFont(16));
		CombinGood.setFont(CustomFont.THboldFont(16));

		CombinLabel.setFont(CustomFont.THboldFont(18));
		CombinStockLabel.setFont(CustomFont.THFont(18));
		CombinPriceLabel.setFont(CustomFont.THFont(18));
		CombinGoodLabel.setFont(CustomFont.THFont(18));
		
		addBtn.setFont(CustomFont.THboldFont(18));

		addBtn.addActionListener(this);
		CombinGood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myGridLayoutUsingJPanel.removeAll();
				AdminGoodsModel goods =  (AdminGoodsModel) CombinGood.getSelectedItem();
				 group = mainMenu.DAOGROUP.viewGroupByGood(goods.getGoodId());
				 optionList = new Vector<OptionGoodModel>();
				JComboBox[] combos = new JComboBox[group.size()]; // MAX_... is a constant
				for (int i = 0; i < combos.length; i++) {
				  JLabel GroupName = new JLabel(group.get(i).getGroupName());
				  GroupName.setFont(CustomFont.THboldFont(18));
				  myGridLayoutUsingJPanel.add(GroupName);
				  Vector<OptionGoodModel> option = mainMenu.DAOOPTION.viewOptionpByGroup(group.get(i).getGroupId());
				  OptionGoodModel optionNew = new OptionGoodModel(group.get(i).getGroupId());
				  optionList.add(optionNew);
				  combos[i] = new JComboBox(option);
				  combos[i].addActionListener(new ButtonListener());
				  combos[i].setFont(CustomFont.THboldFont(18));
				  combos[i].setSelectedItem(null);
				  myGridLayoutUsingJPanel.add(combos[i]);
				}
				Dimension size = myGridLayoutUsingJPanel.getPreferredSize();
				myGridLayoutUsingJPanel.setBounds(labelWS, labelHS, size.width, size.height);
				myGridLayoutUsingJPanel.updateUI();
			}
		});

		add(CombinLabel);
		add(CombinPriceLabel);
		add(CombinGoodLabel);
		add(CombinStockLabel);
		
		add(CombintStockBox);
//		add(CombintPriceBox);
		add(CombinGood);

		add(addBtn);
		
		add(myGridLayoutUsingJPanel);

		// set sizes and positions for labels
		int labelH = 5;
		int labelW = 0;
		Dimension size = CombinLabel.getPreferredSize();
		CombinLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
		
		
		labelH += 30 ;
		labelW = (FORMWIDTH ) / 2  - 110 ;
		size = CombinGoodLabel.getPreferredSize();
		CombinGoodLabel.setBounds(labelW, labelH, size.width, size.height);
		size = CombinGood.getPreferredSize();
		CombinGood.setBounds(labelW, labelH+25, size.width, size.height);
		labelH+= 55 ;
//		size = CombinPriceLabel.getPreferredSize();
//		CombinPriceLabel.setBounds(labelW, labelH, size.width, size.height);
//		size = CombintPriceBox.getPreferredSize();
//		CombintPriceBox.setBounds(labelW, labelH+25, size.width, size.height);
//		labelW += size.width + 20 ;
		size = CombinStockLabel.getPreferredSize();
		CombinStockLabel.setBounds(labelW, labelH, size.width, size.height);
		size = CombintStockBox.getPreferredSize();
		CombintStockBox.setBounds(labelW, labelH+25, size.width, size.height);
		labelW = (FORMWIDTH ) / 2  - 110 ;
		labelH += 70;
		size = myGridLayoutUsingJPanel.getPreferredSize();
		myGridLayoutUsingJPanel.setBounds(labelW, labelH, size.width, size.height);
		labelHS = labelH ;
		labelWS = labelW ;
		labelH += 100 + 20 ;
		size = addBtn.getPreferredSize();
		addBtn.setBounds((FORMWIDTH  - size.width) /2  , labelH, size.width, size.height);
	}
	
	public int getIndexOption(int optionId) {
		int index = -1 ;
		int count = 0 ;
		for(OptionGoodModel option : optionList) {
			if(option.getGroupId() == optionId) {
				index = count ;
				return index;
			}
			count++;
		}
		return index;
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox jcmbType = (JComboBox) e.getSource();
			OptionGoodModel cmbType = (OptionGoodModel) jcmbType.getSelectedItem();
			if(cmbType != null) {
				int index = getIndexOption(cmbType.getGroupId());
				optionList.set(index, cmbType);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		// user presses "Add"
		if(actionCommand.equals(mainMenu.ADD_STR)) {
			AdminGoodsModel good = (AdminGoodsModel) CombinGood.getSelectedItem() ;
//			String priceStr = CombintPriceBox.getText();
			String stockStr = CombintStockBox.getText();
			if(!group.isEmpty()) {
				
				
				try {
					double price = 0 ;
					int stock = Integer.parseInt(stockStr);
					boolean checkStr = true ;
					for (OptionGoodModel option : optionList) {
						System.out.println("true:"+option);
						if(option.getOptionName() == null) {
							checkStr = false;
							break;
						}
					}
					if(checkStr) {
						String CombinName = good.getGoodName() ;
						for (int i = 0 ; i < optionList.size();i++) {
							price += optionList.get(i).getOptionPrice();
							CombinName += "-"+group.get(i).getGroupName()+" "+optionList.get(i).getOptionName();
						}
						System.out.println(CombinName);
						CombinGroupModel combin = new CombinGroupModel(CombinName, price, stock, good);
						mainMenu.DAOCOMBIN.addCombinGroup(combin);
					}
				}catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}else {
				try {
					double price = good.getGoodPrice();
					int stock = Integer.parseInt(stockStr);
					CombinGroupModel combin = new CombinGroupModel(price, stock, good);
					mainMenu.DAOCOMBIN.addCombinGroup(combin);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}

}
