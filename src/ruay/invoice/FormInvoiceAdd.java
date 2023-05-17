package ruay.invoice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.model.AdminGoodsModel;
import ruay.model.CombinGroupModel;
import ruay.model.GoodGroupOptionView;
import ruay.model.InvoiceDetailModel;
import ruay.model.InvoiceModel;
import ruay.model.OptionGoodModel;
import ruay.model.SupplierGoodsModel;
import ruay.model.SupplierModel;

public class FormInvoiceAdd extends JPanel implements ActionListener {
	GuiMainRuay mainMenu;
	JComboBox id_supTxt;
	JTextField totalTxt;
	JComboBox id_goodTxt;
//	JComboBox id_goodsup;

	JPanel panelAddInvoice;
	JPanel panelAlert;

	JButton addBtn;
	JButton addBtn2;
	JButton okBtn;
	JButton yesBtn;
	JButton noBtn;
	JButton clearBtn;

	int row;

	private static final String cancelString = "Cancel";
	private static final String okString = "OK";
	private static final String yesString = "YES";
	private static final String noString = "NO";
	private static final String clearString = "ยกเลิก";
//	CalendarWindow calendarWindow;

	int number;
	int id_combingood;
	int id_sup;
	int goodsupId;
	int id;
	double amount = 0;

	JFormattedTextField textField = new JFormattedTextField();

	public FormInvoiceAdd(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formInvoice.FORMWIDTH;
		int FORMHEIGHT = mainMenu.menuSystem.formInvoice.FORMHEIGHT;
		setOpaque(false);
		setLayout(null);
		panelAddInvoice = new JPanel();
		panelAlert = new JPanel();
		// set layout manager to manual

		// set layout pane2
		add(panelAddInvoice);
		panelAddInvoice.setBackground(new Color(226, 224, 224));
		panelAddInvoice.setBounds((FORMWIDTH - 380) / 2, 30, 380, 200);
		panelAddInvoice.setLayout(new BorderLayout());
		panelAddInvoice.setLayout(null);
		panelAddInvoice.setOpaque(false);
		// pane2.setVisible(true);


		add(panelAlert);
		panelAlert.setBackground(new Color(226, 224, 224));
		panelAlert.setBounds((FORMWIDTH - 380) / 2, 60, 380, 100);
		panelAlert.setLayout(new BorderLayout());
		panelAlert.setVisible(false);

		// create labels
		JLabel addinvoiceLabel = new JLabel("เพิ่มรายการสั่งซื้อกับผู้ผลิต");
		JLabel invoiceLabel = new JLabel("Enter the invoice code");
		JLabel id_supLabel = new JLabel("ผู้ผลิต:");
		JLabel id_goodLabel = new JLabel("สินค้าผู้ผลิต:");
		JLabel priceLabel = new JLabel("จำนวนที่ต้องการ:");
		JLabel DateLabel = new JLabel("วันเที่:");
		JLabel id_invLabel = new JLabel("Enter ID Invoice:");
//		JLabel id_goodGroupLabel = new JLabel("เลือกกลุ่มสินค้า:");
		JLabel StringCheck = new JLabel("ต้องการเพิ่มสินค้าต่อหรือไม่ ?");

		addinvoiceLabel.setFont(CustomFont.THboldFont(18));
		id_supLabel.setFont(CustomFont.THFont(17));
		id_goodLabel.setFont(CustomFont.THFont(17));
		priceLabel.setFont(CustomFont.THFont(17));
		DateLabel.setFont(CustomFont.THFont(17));
//		id_goodGroupLabel.setFont(CustomFont.THFont(17));
		StringCheck.setFont(CustomFont.THboldFont(18));



		StringCheck.setHorizontalAlignment(JLabel.CENTER);
		StringCheck.setForeground(Color.BLACK);

		// create buttons
		addBtn = new JButton(mainMenu.ADD_STR);
		okBtn = new JButton(okString);
		yesBtn = new JButton(yesString);
		noBtn = new JButton(noString);
		addBtn2 = new JButton(mainMenu.ADD_STR);
		clearBtn = new JButton(clearString);

		addBtn.setFont(CustomFont.THboldFont(18));
		addBtn2.setFont(CustomFont.THboldFont(18));
		clearBtn.setFont(CustomFont.THboldFont(18));
		// setVasibel = false
		yesBtn.setVisible(false);
		noBtn.setVisible(false);
		addBtn2.setVisible(false);
		clearBtn.setVisible(false);

		addBtn.setFocusable(false);
		okBtn.setFocusable(false);
		yesBtn.setFocusable(false);
		noBtn.setFocusable(false);
		addBtn2.setFocusable(false);
		clearBtn.setFocusable(false);


		// create texts
		Vector<SupplierModel> sup = mainMenu.DAOSUP.viewSupplier();
		id_supTxt = new JComboBox(sup);
		totalTxt = new JTextField(6);
		id_goodTxt = new JComboBox();
//		id_goodsup = new JComboBox();	

		
		id_supTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SupplierModel selectSup = (SupplierModel) id_supTxt.getSelectedItem();
					if (selectSup != null) {
						Vector<SupplierGoodsModel> supgoods = mainMenu.DAOSUPGOOD
								.searchSupGoodList(selectSup.getSupId());
						id_sup = selectSup.getSupId();
						id_goodTxt.removeAllItems();
						for (SupplierGoodsModel supgood : supgoods) {
							id_goodTxt.addItem(supgood.getCombin());
						}
					}

				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}

			}
		});
		id_supTxt.setFont(CustomFont.THboldFont(17));
		id_goodTxt.setFont(CustomFont.THboldFont(17));
//		id_goodsup.setFont(CustomFont.THboldFont(17));
		// create control buttons.
		addBtn.addActionListener(this);
		okBtn.addActionListener(this);
		id_supTxt.addActionListener(this);
		yesBtn.addActionListener(this);
		noBtn.addActionListener(this);
		addBtn2.addActionListener(this);
		clearBtn.addActionListener(this);

		// add labels
		add(addinvoiceLabel);
		// pane.add(idLabel);
		panelAddInvoice.add(id_supLabel);
		panelAddInvoice.add(id_goodLabel);
		panelAddInvoice.add(priceLabel);
		panelAddInvoice.add(DateLabel);
//		panelAddInvoice.add(id_goodGroupLabel);


		panelAlert.add(StringCheck);

		// add text fields
		panelAddInvoice.add(id_supTxt);
		panelAddInvoice.add(textField);
		panelAddInvoice.add(totalTxt);
		panelAddInvoice.add(id_goodTxt);
//		panelAddInvoice.add(id_goodsup);

		// add control buttons
		add(addBtn);
		add(yesBtn);
		add(noBtn);
		add(addBtn2);
		add(clearBtn);

		// set sizes and positions for labels
		Dimension size = addinvoiceLabel.getPreferredSize();
		addinvoiceLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);
		// size = idLabel.getPreferredSize();
		// idLabel.setBounds(10, 20, size.width, size.height);
		size = id_supLabel.getPreferredSize();
		id_supLabel.setBounds(130, 30, size.width, size.height);
		size = id_goodLabel.getPreferredSize();
		id_goodLabel.setBounds(130, 70, size.width, size.height);
//		size = id_goodGroupLabel.getPreferredSize();
//		id_goodGroupLabel.setBounds(130, 110, size.width, size.height);
		size = priceLabel.getPreferredSize();
		priceLabel.setBounds(130, 110, size.width, size.height);
		size = DateLabel.getPreferredSize();
		DateLabel.setBounds(130, 150, size.width, size.height);
		size = invoiceLabel.getPreferredSize();
		invoiceLabel.setBounds(130, 5, size.width, size.height);
		size = id_invLabel.getPreferredSize();
		id_invLabel.setBounds(50, 50, size.width, size.height);

		// set sizes and positions for labels
		size = id_supTxt.getPreferredSize();
		id_supTxt.setBounds(210, 30, size.width, size.height);

		
		size = totalTxt.getPreferredSize();
		totalTxt.setBounds(210, 110, size.width, size.height);
		size = id_goodTxt.getPreferredSize();
		id_goodTxt.setBounds(210, 70, 170, size.height);
//		size = id_goodsup.getPreferredSize();
//		id_goodsup.setBounds(210, 110, 130, size.height);

		// set sizes and positions for controls buttons
		size = addBtn.getPreferredSize();
		addBtn.setBounds((FORMWIDTH - size.width) / 2, 240, size.width, size.height);
		size = okBtn.getPreferredSize();
		okBtn.setBounds(270, 47, size.width, size.height);
		size = yesBtn.getPreferredSize();
		yesBtn.setBounds((FORMWIDTH - size.width) / 2 - 150, 170, size.width, size.height);
		size = noBtn.getPreferredSize();
		noBtn.setBounds((FORMWIDTH - size.width) / 2 + 150, 170, size.width, size.height);

		size = addBtn2.getPreferredSize();
		addBtn2.setBounds(FORMWIDTH / 2 - size.width - 10, 240, size.width, size.height);
		size = clearBtn.getPreferredSize();
		clearBtn.setBounds(FORMWIDTH / 2 + 10, 240, size.width, size.height);

		LocalDate myDate = LocalDate.now();
		textField.setValue(myDate);
		textField.setBounds(210, 150, 100, 20);

		id_goodTxt.setSelectedItem(null);
		id_supTxt.setSelectedItem(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (e.getSource() == addBtn) {
			try {
				// retrieve values from text fields.
				SupplierModel supplier = (SupplierModel) id_supTxt.getSelectedItem();
				if(supplier != null) {
					id_sup = supplier.getSupId();
				}
				number = Integer.parseInt(totalTxt.getText());
				CombinGroupModel combin = (CombinGroupModel) id_goodTxt.getSelectedItem();
				if(combin != null) {
					id_combingood =  combin.getCombinId();
				}
				
				String date = textField.getText();
				// create student object
				// create sql command
				InvoiceModel inv = new InvoiceModel(id_sup, date, 0);
				id = mainMenu.DAOINV.addInvoiceGetId(inv);

				double total = 0;
				total = mainMenu.DAOINV.searchDBinvDet(id, id_sup, number,id_combingood);
				if (total != 0) {
					amount = total;
				}
				InvoiceDetailModel invDe = new InvoiceDetailModel(total, id, 0, id_combingood, number);
				mainMenu.DAOINVDE.addInvoiceDetail(invDe);
				panelAddInvoice.setVisible(false);
				addBtn.setVisible(false);
				panelAlert.setVisible(true);
				yesBtn.setVisible(true);
				noBtn.setVisible(true);
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
				ex.getStackTrace();
			}
			// user presses "Cancel"
		} else if (actionCommand.equals(cancelString)) {
			setVisible(false);
		}
		if (actionCommand.equals(yesString)) {
			totalTxt.setText(null);
			id_goodTxt.setSelectedItem(null);
			id_supTxt.setEditable(false);
			id_supTxt.setEnabled(false);
			textField.setEditable(false);
			panelAlert.setVisible(false);
			yesBtn.setVisible(false);
			noBtn.setVisible(false);
			panelAddInvoice.setVisible(true);
			addBtn2.setVisible(true);
			clearBtn.setVisible(true);

		} else if (actionCommand.equals(noString)) {
			try {

				// execute sql command
				double total = 0;
				total = mainMenu.DAOINVDE.searchTotalDet(id);
				if (total != 0) {
					amount = total;
				}
				mainMenu.DAOINV.updateById(amount, id);
				id_goodTxt.removeAllItems();
				id_supTxt.setSelectedItem(null);
				totalTxt.setText(null);
				id_goodTxt.setSelectedItem(null);
				panelAlert.setVisible(false);
				yesBtn.setVisible(false);
				noBtn.setVisible(false);
				panelAddInvoice.setVisible(true);
				addBtn.setVisible(true);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		}
		if (e.getSource() == addBtn2) {
			try {
				number = Integer.parseInt(totalTxt.getText());
				CombinGroupModel combin = (CombinGroupModel) id_goodTxt.getSelectedItem();
				if(combin != null) {
					id_combingood =  combin.getCombinId();
				}
				// execute sql command
				double totals = 0;
				totals = mainMenu.DAOINV.searchDBinvDet(id, id_sup, number,id_combingood);
				if (totals != 0) {
					amount = totals;
				}
				// create sql command
				InvoiceDetailModel invDe = new InvoiceDetailModel(amount, id, 0, id_combingood, number);
				mainMenu.DAOINVDE.addInvoiceDetail(invDe);
				id_goodTxt.setSelectedItem(null);
				totalTxt.setText(null);
			} catch (Exception ex) {
				// TODO: handle exception
			}

		} else if (actionCommand.equals(clearString)) {
			System.out.println("actionCommand:" + clearString);
			try {
				double total = 0;
				total = mainMenu.DAOINVDE.searchTotalDet(id);
				if (total != 0) {
					amount = total;
				}
				// execute sql command
				mainMenu.DAOINV.updateById(amount, id);
//				id_goodsup.removeAllItems();
				id_supTxt.setEditable(true);
				id_supTxt.setEnabled(true);
				textField.setEditable(true);
				id_supTxt.setSelectedItem(null);
				totalTxt.setText(null);
				id_goodTxt.setSelectedItem(null);
				addBtn2.setVisible(false);
				clearBtn.setVisible(false);
				addBtn.setVisible(true);
			} catch (Exception ex) {
				// TODO: handle exception
			}

		}
	}

}
