package ruay.component;

import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ruay.GuiMainRuay;
import ruay.admingoods.FormAdminGood;
import ruay.component.util.CustomFont;
import ruay.component.util.JPanelGradient;
import ruay.customer.FormCustomer;
import ruay.invoice.FormInvoice;
import ruay.supplieradmin.FormSupplierAdmin;
import ruay.suppliergoods.FormSupplierGood;

public class MainMenu extends JFrame implements ActionListener {

	public final int BTNWIDTH = 140;
	private JPanelGradient gradient;
	private JPanelGradient navber;

	private JButton btnAdGoods;
	private JButton btnSpAd;
	private JButton btnSpGoodAd;
	private JButton btnCt;
	private JButton btnIn;

	public FormAdminGood formAdminGood;
	public FormSupplierAdmin formSupAdmin;
	public FormSupplierGood formSupplierGoods;
	public FormCustomer formCustomer;
	public static FormInvoice formInvoice;

	JPanel mainPanel;

	GuiMainRuay Guimain;

	String checkBtn = "";
	JButton BtnOld;

	public void hover(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btn.setForeground(Color.WHITE);
				btn.setBorderPainted(true);
				btn.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
			}

			public void mouseExited(MouseEvent evt) {
				String command = ((AbstractButton) evt.getSource()).getText();
				if (!checkBtn.equals(command)) {
					btn.setForeground(new Color(199, 209, 204));
					btn.setBorderPainted(false);
				} else {
					BtnOld = btn;
				}

			}

		});
	}

	public MainMenu(GuiMainRuay Guimain) {
		this.Guimain = Guimain;
		// setLayout(new GridLayout(5,1));
		// size JFrame and position window
		setResizable(false);
		setSize(Guimain.WIDTH, Guimain.HEIGHT);
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenDim.width - Guimain.WIDTH) / 2, (screenDim.height - Guimain.HEIGHT) / 2);

		// label logo project
		ImageIcon iconLogo = new ImageIcon("img\\logo.png");
		Image image = iconLogo.getImage();
		Image newimg = image.getScaledInstance(120, 50, Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(newimg);
		JLabel project = new JLabel();
		project.setIcon(iconLogo);

		// button
		btnAdGoods = new JButton(Guimain.GOOD_STR);
		btnSpAd = new JButton(Guimain.SUP_STR);
		btnSpGoodAd = new JButton(Guimain.SUBGOOD_STR);
		btnCt = new JButton(Guimain.CUS_STR);
		btnIn = new JButton(Guimain.INVOICE_STR);

		/// btnStyle
		btnAdGoods.setFocusable(false);
		btnAdGoods.setFont(CustomFont.THboldFont(18));
		btnAdGoods.setForeground(new Color(199, 209, 204));
		btnAdGoods.setContentAreaFilled(false);
		btnAdGoods.setBorderPainted(false);
		hover(btnAdGoods);

		btnSpAd.setFocusable(false);
		btnSpAd.setFont(CustomFont.THboldFont(18));
		btnSpAd.setForeground(new Color(199, 209, 204));
		btnSpAd.setContentAreaFilled(false);
		btnSpAd.setBorderPainted(false);
		hover(btnSpAd);

		btnSpGoodAd.setFocusable(false);
		btnSpGoodAd.setFont(CustomFont.THboldFont(18));
		btnSpGoodAd.setForeground(new Color(199, 209, 204));
		btnSpGoodAd.setContentAreaFilled(false);
		btnSpGoodAd.setBorderPainted(false);
		hover(btnSpGoodAd);

		btnCt.setFocusable(false);
		btnCt.setFont(CustomFont.THboldFont(18));
		btnCt.setForeground(new Color(199, 209, 204));
		btnCt.setContentAreaFilled(false);
		btnCt.setBorderPainted(false);
		hover(btnCt);

		btnIn.setFocusable(false);
		btnIn.setFont(CustomFont.THboldFont(18));
		btnIn.setForeground(new Color(199, 209, 204));
		btnIn.setContentAreaFilled(false);
		btnIn.setBorderPainted(false);
		hover(btnIn);

		getContentPane().setLayout(null);

		// navber
		navber = new JPanelGradient(new Color(0,0,0), new Color(65, 65, 65));
		navber.setBounds(0, 0, Guimain.WIDTH, Guimain.HEIGHTNAVBER);
		navber.setLayout(null);
		getContentPane().add(navber);
		// add label and button in panel
		navber.add(project);
		navber.add(btnAdGoods);
		navber.add(btnSpAd);
		navber.add(btnSpGoodAd);
		navber.add(btnCt);
		navber.add(btnIn);

		// size JLabel project
		Dimension size = project.getPreferredSize();
		project.setBounds(10, size.height / 2 - 10, size.width, size.height);

		// button setBounds
		int xl = Guimain.WIDTH - (BTNWIDTH + 10);
		int hs = size.height + 30;
		btnIn.setBounds(xl, 0, BTNWIDTH, hs);
		xl -= BTNWIDTH;
		btnCt.setBounds(xl, 0, BTNWIDTH, hs);
		xl -= BTNWIDTH;
		btnSpGoodAd.setBounds(xl, 0, BTNWIDTH, hs);
		xl -= BTNWIDTH;
		btnSpAd.setBounds(xl, 0, BTNWIDTH, hs);
		xl -= BTNWIDTH;
		btnAdGoods.setBounds(xl, 0, BTNWIDTH, hs);

		// button Action Listener
		btnAdGoods.addActionListener(this);
		btnSpAd.addActionListener(this);
		btnSpGoodAd.addActionListener(this);
		btnCt.addActionListener(this);
		btnIn.addActionListener(this);

		JPanel formdefault = new JPanel();
		formdefault.setOpaque(false);
		formAdminGood = new FormAdminGood(Guimain);
		formSupAdmin = new FormSupplierAdmin(Guimain);
		formSupplierGoods = new FormSupplierGood(Guimain);
		formCustomer = new FormCustomer(Guimain);
		formInvoice = new FormInvoice(Guimain);

		// menu
		mainPanel = new JPanel(new CardLayout());
		mainPanel.setOpaque(false);
		// set background JFrame
		gradient = new JPanelGradient(new Color(219, 214, 214), new Color(219, 214, 214));
		gradient.setBounds(0, 0, Guimain.WIDTH, Guimain.HEIGHT);
		gradient.setLayout(null);
		getContentPane().add(gradient);
		gradient.add(mainPanel);

		size = gradient.getPreferredSize();
		mainPanel.setBounds(0, hs, size.width, size.height - hs);

		mainPanel.add(formdefault, "");
		mainPanel.add(formAdminGood, Guimain.GOOD_STR);
		mainPanel.add(formSupAdmin, Guimain.SUP_STR);
		mainPanel.add(formSupplierGoods, Guimain.SUBGOOD_STR);
		mainPanel.add(formCustomer, Guimain.CUS_STR);
		mainPanel.add(formInvoice, Guimain.INVOICE_STR);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setUndecorated(true);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String command = evt.getActionCommand();
		if (command.equals(Guimain.GOOD_STR)) {
			formAdminGood.formMenu();
			CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
			cardLayout.show(mainPanel, Guimain.GOOD_STR);
		} else if (command.equals(Guimain.SUP_STR)) {
			formSupAdmin.formMenu();
			CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
			cardLayout.show(mainPanel, Guimain.SUP_STR);
		} else if (command.equals(Guimain.SUBGOOD_STR)) {
			formSupplierGoods.formMenu();
			CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
			cardLayout.show(mainPanel, Guimain.SUBGOOD_STR);
		} else if (command.equals(Guimain.CUS_STR)) {
			formCustomer.formMenu();
			CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
			cardLayout.show(mainPanel, Guimain.CUS_STR);
		} else if (command.equals(Guimain.INVOICE_STR)) {
			CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
			cardLayout.show(mainPanel, Guimain.INVOICE_STR);
		}

		if (!checkBtn.equals(command) && checkBtn != "") {
			BtnOld.setForeground(new Color(199, 209, 204));
			BtnOld.setBorderPainted(false);
		}
		checkBtn = command;

	}
}
