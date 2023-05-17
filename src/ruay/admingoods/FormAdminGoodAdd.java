package ruay.admingoods;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.attribute.AclEntry;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.AdminGoodsDAO;
import ruay.model.AdminGoodsModel;
import ruay.model.CategorieViewModel;
import ruay.model.GroupGoodModel;
import ruay.model.OptionGoodModel;
import ruay.model.ProvinceModel;
import ruay.model.RoleModel;
import ruay.model.itemGroup;


public class FormAdminGoodAdd extends JPanel implements ActionListener {
	JTextField idTxt;
	JTextField nameTxt;
	JComboBox provinceBox;
	JComboBox categorieBox;
	JTextArea descriptionTxt;
	JTextField priceTxt;
	JButton chooseImg;
	JButton addBtn;

	JPanel panelGroup;

	String s = null;

	JLabel img;

	private static final String addString = "เพิ่มรายการ";
	private static final String chooseString = "เลือกรูปภาพ";
	GuiMainRuay mainMenu;

	int panelWidth = 0;
	int panelHeight = 0;
	int panelxw = 0;
	int panelyh = 0;
	int labelH = 5;
	int labelW = 0;
	int FORMWIDTH = 0;
	int FORMHEIGHT = 0;
	ArrayList<ArrayList<itemGroup>> groupList;

	public FormAdminGoodAdd(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		FORMHEIGHT = mainMenu.menuSystem.formAdminGood.FORMHEIGHT;
		FORMWIDTH = mainMenu.menuSystem.formAdminGood.FORMWIDTH;
		setOpaque(false);
		setLayout(null);
		// create labels
		JLabel goodsAddLabel = new JLabel("เพิ่มสินค้า");
		JLabel nameLabel = new JLabel("ชื่อสินค้า:");
		JLabel provinceLabel = new JLabel("จังหวัด:");
		JLabel descriptionLabel = new JLabel("รายละเอียดสินค้า:");
		JLabel priceLabel = new JLabel("ราคา:");
		JLabel categotieLabel = new JLabel("หมวกหมู่สินค้า:");
		JLabel imgLabel = new JLabel("ตัวอย่างรูปภาพ");
		chooseImg = new JButton(chooseString);
		chooseImg.setFont(CustomFont.THboldFont(18));

		Border blackline = BorderFactory.createLineBorder(Color.black);
		img = new JLabel();
		img.setBorder(blackline);

		// create buttons
		addBtn = new JButton(addString);

		groupList = new ArrayList<ArrayList<itemGroup>>();
		panelGroup = new JPanel();

		goodsAddLabel.setFont(CustomFont.THboldFont(18));
		nameLabel.setFont(CustomFont.THFont(17));
		provinceLabel.setFont(CustomFont.THFont(17));
		descriptionLabel.setFont(CustomFont.THFont(17));
		priceLabel.setFont(CustomFont.THFont(17));
		categotieLabel.setFont(CustomFont.THFont(17));
		imgLabel.setFont(CustomFont.THboldFont(18));
		// create texts
		// idTxt = new JTextField(10);
		nameTxt = new JTextField(13);
		descriptionTxt = new JTextArea(4, 28);
		priceTxt = new JTextField(8);

		Vector<ProvinceModel> provinceList = mainMenu.DAOPROVINCE.viewProvince();
		provinceBox = new JComboBox(provinceList);
		provinceBox.setFont(CustomFont.THboldFont(18));

		Vector<CategorieViewModel> cateAll = mainMenu.DAOCATE.viewCategorieAll();
		categorieBox = new JComboBox(cateAll);
		categorieBox.setFont(CustomFont.THboldFont(18));
		categorieBox.setSelectedItem(null);

		nameTxt.setFont(CustomFont.THFont(17));
		priceTxt.setFont(CustomFont.THFont(17));
		addBtn.setFont(CustomFont.THboldFont(18));

		// Border
		descriptionTxt.setBorder(BorderFactory.createEtchedBorder());
		descriptionTxt.setFont(CustomFont.THFont(17));
		// setLine
		descriptionTxt.setLineWrap(true);

		// create control buttons.
		addBtn.addActionListener(this);
		chooseImg.addActionListener(this);

		// Frame constraints

		GridBagConstraints frameConstraintGroup = new GridBagConstraints();

		JButton groupButton = new JButton("เพิ่มกลุ่มตัวเลือก");
		groupButton.addActionListener(new ButtonAddGroupListener());
		groupButton.setFont(CustomFont.THboldFont(18));
		add(groupButton, frameConstraintGroup);

		// Construct panel
		panelGroup.setLayout(new GridBagLayout());
		panelGroup.setOpaque(false);
		add(panelGroup, frameConstraintGroup);

		// add labels
		add(goodsAddLabel);
		add(nameLabel);
		add(provinceLabel);
		add(descriptionLabel);
		add(priceLabel);
		add(categotieLabel);
		add(imgLabel);

		add(img);

		// add text fields
		add(nameTxt);
		add(descriptionTxt);
		add(priceTxt);
		add(provinceBox);
		add(categorieBox);

		// add control buttons
		add(addBtn);
		add(chooseImg);

		// set sizes and positions for labels

		labelH = 5;
		labelW = 0;
		Dimension size = goodsAddLabel.getPreferredSize();
		goodsAddLabel.setBounds((FORMWIDTH - size.width) / 2, labelH, size.width, size.height);
		labelH += 30;
		labelW = (FORMWIDTH) / 2 - 150;
		size = nameLabel.getPreferredSize();
		nameLabel.setBounds(labelW, labelH, size.width, size.height);
		size = nameTxt.getPreferredSize();
		nameTxt.setBounds(labelW, labelH + 25, size.width, size.height);
		labelW += size.width + 20;
		size = priceLabel.getPreferredSize();
		priceLabel.setBounds(labelW, labelH, size.width, size.height);
		size = priceTxt.getPreferredSize();
		priceTxt.setBounds(labelW, labelH + 25, size.width, size.height);
		labelH += 55;
		labelW = (FORMWIDTH) / 2 - 150;
		size = provinceLabel.getPreferredSize();
		provinceLabel.setBounds(labelW, labelH, size.width, size.height);
		size = provinceBox.getPreferredSize();
		provinceBox.setBounds(labelW, labelH + 25, size.width, size.height);
		labelW += size.width + 20;
		size = categotieLabel.getPreferredSize();
		categotieLabel.setBounds(labelW, labelH, size.width, size.height);
		size = categorieBox.getPreferredSize();
		categorieBox.setBounds(labelW, labelH + 25, size.width, size.height);
		labelH += 65;
		size = imgLabel.getPreferredSize();
		labelW = (FORMWIDTH) / 2;
		imgLabel.setBounds(labelW - (size.width / 2), labelH, size.width, size.height);
		labelW -= 100;
		labelH += 30;
		img.setBounds(labelW, labelH, 200, 200);
		size = chooseImg.getPreferredSize();
		labelH += 220;
		chooseImg.setBounds(labelW + (size.width / 2), labelH, size.width, size.height);
		labelH += size.height + 20;
		labelW = (FORMWIDTH) / 2 - 150;
		size = descriptionLabel.getPreferredSize();
		descriptionLabel.setBounds(labelW, labelH, size.width, size.height);
		labelH += 25;
		size = descriptionTxt.getPreferredSize();
		descriptionTxt.setBounds(labelW, labelH, size.width, size.height);

		labelH += size.height + 30;
		size = groupButton.getPreferredSize();
		labelW = (FORMWIDTH) / 2 - (size.width / 2);
		groupButton.setBounds(labelW, labelH, size.width, size.height);
		labelH += size.height + 20;
		labelW = (FORMWIDTH) / 2 - (((FORMWIDTH - 120) / 2) - 10);
		panelWidth = labelW;
		panelHeight = labelH;
		panelxw = (FORMWIDTH - 120);
		panelyh = 150;
		panelGroup.setBounds(labelW, labelH, panelxw, panelyh);

		labelH += panelyh + 20;
		size = addBtn.getPreferredSize();
		labelW = (FORMWIDTH) / 2 - (size.width / 2);
		addBtn.setBounds(labelW, labelH, size.width, size.height);
		labelH += size.height;
		this.setPreferredSize(new Dimension(FORMWIDTH - 50, FORMHEIGHT + (labelH - FORMHEIGHT + 50)));

	}

	private void iterateOverJTextFields(Container container) {
		int key = 0;
		for (int i = 0; i < container.getComponents().length; i += 4) {
			Component[] component = container.getComponents();
			if (component[i + 1] instanceof JTextField) {
				groupList.get(key).get(0).setGroupName(((JTextField) container.getComponents()[i + 1]).getText());
				System.out.println("groupName: " + groupList.get(key).get(0).getGroupName());
			}
			key++;
		}

	}

	class ButtonAddGroupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int key = 0;
			JPanel panelOption;
			try {
				iterateOverJTextFields(panelGroup);
				panelGroup.removeAll();
				GridBagConstraints textFieldConstraints = new GridBagConstraints();

				if (groupList.size() > 0) {
					panelyh += 100;
					panelGroup.setBounds(panelWidth, panelHeight, panelxw, panelyh);
				}
				key = 0;
				System.out.println(groupList.size());
				System.out.println(groupList);
				for (int i = 0; i <= (groupList.size() * 2); i += 2) {
					JLabel t = new JLabel("กรอกข้อมูลกลุ่ม:");
					t.setFont(CustomFont.THFont(17));

					textFieldConstraints.gridx = 0;
					textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
					textFieldConstraints.weightx = 6;
					textFieldConstraints.gridwidth = 1;
					textFieldConstraints.insets = new Insets(10, 15, 10, 15);
					textFieldConstraints.gridy = i;
					panelGroup.add(t, textFieldConstraints);

					JTextField g = new JTextField(20);
					g.setFont(CustomFont.THFont(17));
					try {
						g.setText(groupList.get(key).get(0).getGroupName() != null
								? groupList.get(key).get(0).getGroupName()
								: "");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					textFieldConstraints.gridx = 1;
					textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
					textFieldConstraints.weightx = 6;
					textFieldConstraints.gridwidth = 1;
					textFieldConstraints.insets = new Insets(10, 15, 10, 15);
					textFieldConstraints.gridy = i;
					panelGroup.add(g, textFieldConstraints);

					int width = 400;
					int height = 700;
					panelOption = new JPanel();
					JButton addButton = new JButton("เพิ่มตัวเลือก");
					try {
						addButton.addActionListener(
								new ButtonListener(width, height, panelOption, key, groupList.get(key)));
					} catch (IndexOutOfBoundsException ex) {
						// TODO: handle exception
						itemGroup test = new itemGroup();
						ArrayList<itemGroup> empty = new ArrayList<itemGroup>();
						empty.add(test);
						groupList.add(empty);
						addButton.addActionListener(
								new ButtonListener(width, height, panelOption, key, groupList.get(key)));
						break;

					} finally {

						addButton.setFont(CustomFont.THboldFont(18));
						textFieldConstraints.gridx = 2;
						textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
						textFieldConstraints.weightx = 6;
						textFieldConstraints.gridwidth = 1;
						textFieldConstraints.insets = new Insets(10, 15, 10, 15);
						textFieldConstraints.gridy = i;
						panelGroup.add(addButton, textFieldConstraints);

						// Construct panel

//						panelOption.setBorder(LineBorder.createBlackLineBorder());
						panelOption.setPreferredSize(new Dimension(width, height));
						textFieldConstraints.gridx = 0;
						textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
						textFieldConstraints.weightx = 0.5;
						textFieldConstraints.gridwidth = GridBagConstraints.REMAINDER;
						textFieldConstraints.insets = new Insets(10, 15, 10, 15);
						textFieldConstraints.gridy = i + 1;
						panelGroup.add(panelOption, textFieldConstraints);

						key++;
					}

				}

				panelGroup.updateUI();
				resize();
			} catch (IndexOutOfBoundsException ex) {
				// TODO: handle exception

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

	class ButtonListener implements ActionListener {
		int width;
		int height;
		JPanel panelOption;
		ArrayList<itemGroup> optionList;
		int key;

		public ButtonListener(int width, int height, JPanel panel, int key, ArrayList<itemGroup> optionList) {
			super();
			
			this.panelOption = panel;
			this.key = key;
			this.optionList = optionList;
			this.width = width;
			this.height = height + 300;
			
			System.out.println("new " + optionList);
			if (optionList.toString().equals("[null]")) {
				System.out.println("true");
				this.optionList = new ArrayList<itemGroup>();
				work();
			} else {
				work();
			}

		}

		private void iterateOverJTextFields(Container container) {

			itemGroup list = new itemGroup();
			optionList.removeAll(optionList);
			for (int i = 0; i < container.getComponents().length; i += 5) {
				Component[] component = container.getComponents();
				if (component[i + 1] instanceof JTextField && component[i + 3] instanceof JTextField) {
					list = new itemGroup();
					list.setOptionName(((JTextField) container.getComponents()[i + 1]).getText());
					list.setOptionPrice(
							Double.parseDouble(((JTextField) container.getComponents()[i + 3]).getText().isEmpty() ? "0"
									: ((JTextField) container.getComponents()[i + 3]).getText()));
					optionList.add(list);
				}

			}
//			System.out.println("Kay["+key+"] == sizeGroup: "+groupList.size());
			if (key < groupList.size()) {
				System.out.println("false");
				groupList.set(key, optionList);
			}

		}
		public void deleteDataOption(int index) {
			System.out.println("430[index] "+index);
			optionList.remove(index);
			work();
		}

		public void actionPerformed(ActionEvent arg0) {
			iterateOverJTextFields(panelOption);
			work();

		}

		public void work() {
			try {
				panelOption.setLayout(new GridBagLayout());
				panelOption.removeAll();
				GridBagConstraints textFieldConstraints = new GridBagConstraints();

				int rowCnt = 5, i, j;
				panelyh += 20;
				panelGroup.setBounds(panelWidth, panelHeight, panelxw, panelyh);
				this.height += 50;
				panelOption.setPreferredSize(new Dimension(width, height));

				for (i = 0; i < optionList.size() + 1; i++) {

					itemGroup list = null;
					if (i < optionList.size()) {
						list = optionList.get(i);
					}
					for (j = 0; j < rowCnt; j++) {
						JLabel t = new JLabel();
						t.setText((j == 0 ? "กรอกตัวเลือก:" : j == 2 ? "ราคาสินค้า:"  : ""));
						t.setFont(CustomFont.THFont(17));
						JTextField g = new JTextField(14);
						if (i < optionList.size()) {
							g.setText((j == 1 ? list.getOptionName()
									: j == 3 ? String.valueOf(list.getOptionPrice())
											 : ""));
						}
						g.setFont(CustomFont.THFont(17));
						textFieldConstraints.gridx = j;
						textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
						textFieldConstraints.weightx = 5;
						textFieldConstraints.gridwidth = 1;
						textFieldConstraints.insets = new Insets(10, 15, 10, 15);
						textFieldConstraints.gridy = i;
						if(j == rowCnt -1) {
							int k = i ;
							JButton b = new JButton("ลบ");
							b.setFont(CustomFont.THboldFont(18));
							b.addActionListener(new ActionListener() {
					            public void actionPerformed(ActionEvent e) {
					                // Insert code here
					            	deleteDataOption(k);
					            }
					        });
							panelOption.add(b, textFieldConstraints);
						}else {
							panelOption.add(j == 0 || j == 2 ? t : g, textFieldConstraints);
						}
					}
				}

				panelOption.updateUI();
				resize();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	public void resize() {
		labelH = panelyh + panelHeight + 20;
		Dimension size = addBtn.getPreferredSize();
		addBtn.setBounds(labelW, labelH, size.width, size.height);
		labelH += size.height;
		this.setPreferredSize(new Dimension(FORMWIDTH - 50, FORMHEIGHT + (labelH - FORMHEIGHT + 50)));
		this.revalidate();
	}

	public ImageIcon ResizeImage(String imgpath) {
		ImageIcon myIcon = new ImageIcon(imgpath);
		Image imge = myIcon.getImage();
		Image newImage = imge.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImage);
		return image;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		System.out.println(command);
		if (command.equals(addString)) {
			try {
				iterateOverJTextFields(panelGroup);
				String goodName = nameTxt.getText();
				String goodDescriptio = descriptionTxt.getText();
				Double goodPrice = !priceTxt.getText().equals("") ? Double.parseDouble(priceTxt.getText()) : null;
				ProvinceModel itemProvince = (ProvinceModel) provinceBox.getSelectedItem();
				Integer proviceId = itemProvince.getProviceId();
				CategorieViewModel itemCategorie = (CategorieViewModel) categorieBox.getSelectedItem();
				Integer cate_id = itemCategorie.getCateId();
				Integer sub_id = (itemCategorie.getSubId() == 0 ? null : itemCategorie.getSubId());
				InputStream is = new FileInputStream(s);
				AdminGoodsModel goods = new AdminGoodsModel(goodName, goodPrice, proviceId, cate_id, sub_id,
						is, goodDescriptio);
				int idGood = mainMenu.DAOGOOD.addGoodsId(goods);
				ArrayList<OptionGoodModel> optionList = new ArrayList<OptionGoodModel>();
				for (int i = 0; i < groupList.size(); i++) {
					String groupName = groupList.get(i).get(0).getGroupName();
					GroupGoodModel group = new GroupGoodModel(idGood, groupName);
					int idGroup = mainMenu.DAOGROUP.addGroupId(group);
					for (int j = 0; j < groupList.get(i).size(); j++) {
						String optionName = groupList.get(i).get(j).getOptionName();
						double optionPrice = groupList.get(i).get(j).getOptionPrice();
						OptionGoodModel option = new OptionGoodModel(idGroup, optionName, optionPrice);
						optionList.add(option);
					}
					mainMenu.DAOOPTION.addOptionList(optionList);
				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
				ex.getStackTrace();
			}
		} else if (command.equals(chooseString)) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "png", "gif");
			filechooser.addChoosableFileFilter(filter);
			int result = filechooser.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = filechooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath();
				img.setIcon(ResizeImage(path));
				s = path;
			}
		}
	}

}
