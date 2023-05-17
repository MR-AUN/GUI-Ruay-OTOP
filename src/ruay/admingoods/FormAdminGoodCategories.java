package ruay.admingoods;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.model.CategoriesModel;

public class FormAdminGoodCategories extends JPanel implements ActionListener {
	
	JTextField CatetNameBox ;
	JComboBox CateSupBox ;

	GuiMainRuay mainMenu;

	public FormAdminGoodCategories(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formAdminGood.FORMWIDTH;
		setOpaque(false);
		setLayout(null);

		JLabel CategorietLabel = new JLabel("เพิ่มหมวกหมู่สินค้า");
		JLabel CategorietName = new JLabel("ช่ื่อหมวกหมู่:");
		JLabel CategorietSup = new JLabel("เป็นหมวกหมู่ย่อยของ:");

		// create buttons
		JButton addBtn = new JButton(mainMenu.ADD_STR);
		
		CatetNameBox = new JTextField(20);
		
		Vector<CategoriesModel> cateList = mainMenu.DAOCATE.viewCategorie();
		CategoriesModel data = new CategoriesModel(0, "ไม่มีหมวกหมู่หลัก");
		cateList.addElement(data);
		CateSupBox = new JComboBox(cateList);
		CateSupBox.setSelectedItem(data);
		
		CatetNameBox.setFont(CustomFont.THboldFont(16));
		CateSupBox.setFont(CustomFont.THboldFont(16));

		CategorietLabel.setFont(CustomFont.THboldFont(18));
		CategorietName.setFont(CustomFont.THFont(18));
		CategorietSup.setFont(CustomFont.THFont(18));

		addBtn.setFont(CustomFont.THboldFont(18));

		addBtn.addActionListener(this);
		CateSupBox.addActionListener(this);

		add(CategorietLabel);
		add(CategorietName);
		add(CategorietSup);
		
		add(CatetNameBox);
		add(CateSupBox);

		add(addBtn);

		// set sizes and positions for labels
		int labelH = 5;
		int labelW = 0;
		Dimension size = CategorietLabel.getPreferredSize();
		CategorietLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
		
		size = CategorietName.getPreferredSize();
		labelH += 30 ;
		labelW = (FORMWIDTH ) / 2  - 150 ;
		CategorietName.setBounds(labelW, labelH, size.width, size.height);
		size = CatetNameBox.getPreferredSize();
		size.width = (size.width < 250 ? 250: size.width);
		CatetNameBox.setBounds(labelW, labelH+25, size.width, size.height);
		labelH+= 55 ;
		size = CategorietSup.getPreferredSize();
		CategorietSup.setBounds(labelW, labelH, size.width, size.height);
		size = CateSupBox.getPreferredSize();
		size.width = (size.width < 250 ? 250: size.width);
		CateSupBox.setBounds(labelW, labelH+25, size.width, size.height);
		labelH += 60 ;
		size = addBtn.getPreferredSize();
		addBtn.setBounds((FORMWIDTH  - size.width) /2  , labelH, size.width, size.height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		// user presses "Add"
		if(actionCommand.equals(mainMenu.ADD_STR)) {
			String cateName = CatetNameBox.getText();
			CategoriesModel item = (CategoriesModel) CateSupBox.getSelectedItem();
			int cateId = item.getCateId();
			if(cateId != 0) {
				mainMenu.DAOCATESUP.addCategorieSup(cateId, cateName);
			}else {
				mainMenu.DAOCATE.addCategorie(cateName);
			}
		}
	}

}
