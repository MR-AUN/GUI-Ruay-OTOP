package ruay.invoice;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.component.util.RoundedPanel;

public class FormInvoice extends JPanel implements ActionListener {
	GuiMainRuay mainMenu;
	JPanel FormPanel;
	JPanel formMenuSystem;
	FormInvoiceAdd formInvoiceAdd;
	FormInvoiceAccept formInvoiceAccept;
	JButton btnOrder;
	JButton btnReceipt;
	
	public static int FORMWIDTH = 0;
	public static int FORMHEIGHT = 0;

	public FormInvoice(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		setOpaque(false);
		setLayout(null);
		JLabel lbHeader = new JLabel("แบบฟอร์มใบแจ้งหนี");
		lbHeader.setBounds((mainMenu.WIDTH - lbHeader.getPreferredSize().width) / 2, 10,
				lbHeader.getPreferredSize().width, lbHeader.getPreferredSize().height);
		lbHeader.setFont(CustomFont.THboldFont(20));
		add(lbHeader);
		btnOrder = new JButton(mainMenu.ORDER_STR);
		btnReceipt = new JButton(mainMenu.VIEW_IN_STR);
		
		btnOrder.setFont(CustomFont.THboldFont(18));
		btnReceipt.setFont(CustomFont.THboldFont(18));
		// menu add delete search
		formMenuSystem = new RoundedPanel(15, Color.white);
		formMenuSystem.setOpaque(false);
		formMenuSystem.add(btnOrder);
		formMenuSystem.add(btnReceipt);
		formMenuSystem.setBounds((mainMenu.WIDTH - formMenuSystem.getPreferredSize().width) / 2 - 20, 30,
				formMenuSystem.getPreferredSize().width + 20, formMenuSystem.getPreferredSize().height);
		add(formMenuSystem);
		
		btnOrder.addActionListener(this);
		btnReceipt.addActionListener(this);
		
		FormPanel = new JPanel(new CardLayout());
		FormPanel.setOpaque(false);
		int formH =80;
		FORMWIDTH = mainMenu.WIDTH - 50;
		FORMHEIGHT = (mainMenu.HEIGHT - mainMenu.HEIGHTNAVBER) -(formH+50) ;
		FormPanel.setBounds((mainMenu.WIDTH - FORMWIDTH) /2 - 5 , formH,FORMWIDTH , FORMHEIGHT);
		add(FormPanel);
		
		formInvoiceAdd = new FormInvoiceAdd(mainMenu);
		formInvoiceAccept = new FormInvoiceAccept(mainMenu);
		
		FormPanel.add(formInvoiceAccept,mainMenu.VIEW_IN_STR);
		FormPanel.add(formInvoiceAdd,mainMenu.ORDER_STR);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals(mainMenu.ORDER_STR)) {
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.ORDER_STR);
		}else if (command.equals(mainMenu.VIEW_IN_STR)) {
			CardLayout cardLayout = (CardLayout) FormPanel.getLayout();
			cardLayout.show(FormPanel, mainMenu.VIEW_IN_STR);
		}
	}

}
