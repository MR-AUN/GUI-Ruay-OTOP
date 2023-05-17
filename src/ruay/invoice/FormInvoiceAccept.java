package ruay.invoice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.model.InvoiceDetailViewModel;
import ruay.model.InvoiceGoodViewModel;

public class FormInvoiceAccept extends JPanel implements ActionListener{
	GuiMainRuay mainMenu;
	JTextField idTxt;
	JTextField id_supTxt;
	JTextField amountTxt;
	JTextField id_goodTxt;
	JTextField totalTxt;

	JPanel panelData;
	JLabel imgLable;

	JButton cancelBtn;
	JButton acceptBtn;

	private static final int iconWidth = 20;
	private static final int iconHeight = 20;

	private static final int buttonWidth = 100;
	private static final int buttonHeight = 25;

	private static final String searchString = "ค้นหา";

	private static final String acceptString = "ยอมรับ";
	private static final String cancelString = "ยกเลิก";

	int id;
	int row;
	int number;
	int IDgood;
	ArrayList<InvoiceDetailViewModel> invList ;
	
	JScrollPane scrollPane;
	
	public void setHoverImage(JButton Btn, ImageIcon icon) {
		Btn.addMouseListener(new java.awt.event.MouseAdapter() {
			ImageIcon icons = icon;

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Image image = icon.getImage();
				Image newimg = image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
				icons = new ImageIcon(newimg);
				Btn.setIcon(icons);
				Btn.setHorizontalAlignment(JButton.CENTER);
				Btn.setHorizontalTextPosition(JButton.LEFT);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Btn.setBackground(Color.WHITE);
				Btn.setIcon(null);
			}
		});
	}

	public FormInvoiceAccept(GuiMainRuay mainMenu) {
		setOpaque(false);
		setLayout(null);
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formInvoice.FORMWIDTH ;
		
		panelData = new JPanel();
		imgLable = new JLabel();
		this.add(panelData);
		panelData.setBackground(new Color(226, 224, 224));
		panelData.setBounds((FORMWIDTH - 380) /2, 80, 380, 150);
		panelData.setLayout(new BorderLayout());
		panelData.setVisible(false);
		panelData.setOpaque(false);

		this.add(imgLable);
		imgLable.setBounds((FORMWIDTH - 380) /2, 60, 380, 200);
		imgLable.setLayout(new BorderLayout());
		imgLable.setVisible(false);

		panelData.setLayout(null);
		imgLable.setLayout(null);


		ImageIcon iconError = new ImageIcon("img\\error\\404.png");
		Image image = iconError.getImage();
		Image newimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		iconError = new ImageIcon(newimg);
		imgLable.setIcon(iconError);
		imgLable.setHorizontalAlignment(JLabel.CENTER);

		// create labels
		JLabel searchStudentLabel = new JLabel("ยืนยันใบเสร็จรายการสั่งซื้อ");
		JLabel idLabel = new JLabel("ค้นหารหัสใบเสร็จ:");
		
		searchStudentLabel.setFont(CustomFont.THboldFont(18));
		idLabel.setFont(CustomFont.THFont(17));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 360, 100);
		panelData.add(scrollPane);

		// Table
		JTable table = new JTable();
		scrollPane.setViewportView(table);

		// create buttons
		JButton searchBtn = new JButton(searchString);
		cancelBtn = new JButton(cancelString);
		acceptBtn = new JButton(acceptString);
		
		searchBtn.setFont(CustomFont.THboldFont(18));
		cancelBtn.setFont(CustomFont.THboldFont(18));
		acceptBtn.setFont(CustomFont.THboldFont(18));

		cancelBtn.setVisible(false);
		acceptBtn.setVisible(false);

		// create texts
		idTxt = new JTextField(10);

		// create control buttons.
		searchBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		acceptBtn.addActionListener(this);

		cancelBtn.setFocusable(false);
		acceptBtn.setFocusable(false);
		searchBtn.setFocusable(false);

		cancelBtn.setBackground(Color.WHITE);
		acceptBtn.setBackground(Color.white);

		cancelBtn.setBorder(new LineBorder(Color.black, 2));
		acceptBtn.setBorder(new LineBorder(Color.black, 2));

		cancelBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		acceptBtn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

		ImageIcon iconcancel = new ImageIcon("img\\icon\\not.png");
		setHoverImage(cancelBtn, iconcancel);
		ImageIcon iconaccept = new ImageIcon("img\\icon\\accept.png");
		setHoverImage(acceptBtn, iconaccept);

		// search labels
		add(searchStudentLabel);
		add(idLabel);


		// add text fields
		add(idTxt);


		// add control buttons
		add(searchBtn);
		add(cancelBtn);
		add(acceptBtn);

		// set sizes and positions for labels
		Dimension size = searchStudentLabel.getPreferredSize();
		searchStudentLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);
		size = idLabel.getPreferredSize();
		idLabel.setBounds((FORMWIDTH - size.width) /2 - 100, 30, size.width, size.height);


		// set sizes and positions for labels
		size = idTxt.getPreferredSize();
		idTxt.setBounds((FORMWIDTH - size.width) /2  + 40, 30, size.width, size.height);


		// set sizes and positions for controls buttons
		size = searchBtn.getPreferredSize();
		searchBtn.setBounds((FORMWIDTH - size.width) / 2 + 150, 27, size.width, size.height);
		size = cancelBtn.getPreferredSize();
		cancelBtn.setBounds((FORMWIDTH - size.width) / 2 + 70 , 250, size.width, size.height);
		size = acceptBtn.getPreferredSize();
		acceptBtn.setBounds((FORMWIDTH - size.width) / 2  -70, 250, size.width, size.height);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals(searchString)) {
			try {
				imgLable.setVisible(false);
				// retrieve values from text fields.
				id = Integer.parseInt(idTxt.getText());
				
				int status = mainMenu.DAOINVDE.searchDBstatus(id);
				if (status != -1) {
					// reset text fields
					if (status == 0) {
						try {
							System.err.println("NO");
							invList = mainMenu.DAOINVDE.searchDBviewInvList(id);
							System.err.println("OK outsize");
							// Table
							JTable table = new JTable();
							JTableHeader tableHeader = table.getTableHeader();
							table.setFont(CustomFont.THFont(17));
							tableHeader.setFont(CustomFont.THboldFont(18));
							scrollPane.setViewportView(table);

							// Model for Table
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.addColumn("ชื่อสินค้า");
							model.addColumn("จำนวน");
							model.addColumn("ราคารวม");
							

							row = 0;
							// Data Row
							for (int i = 0; i < invList.size() ; i++) {
								System.err.println("OK insize");
								InvoiceDetailViewModel inv = invList.get(i);
								model.addRow(new Object[0]);
								model.setValueAt(inv.getCombin(), row, 0);
								IDgood = inv.getCombin().getGood().getGoodId();
								model.setValueAt(inv.getInvDeQuantity(), row, 1);
								model.setValueAt(inv.getInvDeAmount(), row, 2);
								number = inv.getInvDeQuantity();
								++row;
							}

						}catch (Exception ex) {
							// TODO: handle exception
							ex.printStackTrace();
						}

						panelData.setVisible(true);
						cancelBtn.setVisible(true);
						acceptBtn.setVisible(true);
					} else {
						idTxt.setText(null);
						panelData.setVisible(false);
						cancelBtn.setVisible(false);
						acceptBtn.setVisible(false);
					}
				} else {
					panelData.setVisible(false);
					cancelBtn.setVisible(false);
					acceptBtn.setVisible(false);
					// reset text fields
					idTxt.setText(null);

					imgLable.setVisible(true);
				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
				idTxt.setText(null);
				imgLable.setVisible(true);
			} catch (SQLException ex) {
				System.err.println("Error! Database error.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
			// user presses "Cancel"
		} else if (actionCommand.equals(acceptString))

		{
			try {

				int status = mainMenu.DAOINVDE.searchDBstatus(id);
				if (status != -1) {
					// reset text fields
					if (status == 0) {
						try {

							// Data Row
							for (int i = 0; i < invList.size() ; i++) {
								InvoiceDetailViewModel inv = invList.get(i);
								mainMenu.DAOGOOD.updateGoodsFormInvDetail(inv);
							}

						} catch (Exception ex) {

						}
					}
				}
				mainMenu.DAOINVDE.updateInvDetailStatus(id);

				// reset text fields
				panelData.setVisible(false);
				cancelBtn.setVisible(false);
				acceptBtn.setVisible(false);

				idTxt.setText(null);
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (SQLException ex) {
				System.err.println("Error! Database error.");
				System.err.println("Error! " + ex.getMessage());
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		} else if (actionCommand.equals(cancelString)) {
			try {
				// sql2
				mainMenu.DAOINVDE.updateInvDetailStatusOut(id);
				panelData.setVisible(false);
				cancelBtn.setVisible(false);
				acceptBtn.setVisible(false);
				idTxt.setText(null);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		} 
	}
	
}
