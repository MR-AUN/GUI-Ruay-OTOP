package ruay.admingoods;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.management.modelmbean.ModelMBean;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ruay.GuiMainRuay;
import ruay.component.util.CustomFont;
import ruay.dao.AdminGoodsDAO;
import ruay.model.AdminGoodsModel;


public class FormAdminGoodView extends JPanel {
	
	
	
	// ScrollPane for Table
	JScrollPane scrollPane;
	GuiMainRuay mainMenu ; 
	
	public FormAdminGoodView(GuiMainRuay mainMenu) {
		this.mainMenu = mainMenu;
		int FORMWIDTH = mainMenu.menuSystem.formAdminGood.FORMWIDTH ;
		int FORMHEIGHT = mainMenu.menuSystem.formAdminGood.FORMHEIGHT ;
		setOpaque(false);
		setLayout(null);
		JLabel viewGoodsLabel = new JLabel("รายการสินค้าทั้งหมด");
		viewGoodsLabel.setFont(CustomFont.THboldFont(18));
		add(viewGoodsLabel);

		// set sizes and positions for labels
		Dimension size = viewGoodsLabel.getPreferredSize();
		viewGoodsLabel.setBounds((FORMWIDTH - size.width) / 2, 5, size.width, size.height);

		scrollPane = new JScrollPane();
		scrollPane.setBounds((((FORMWIDTH ) - size.width) / 2 )-(((FORMWIDTH - 200) - size.width) / 2 ) , 30, FORMWIDTH - 200, FORMHEIGHT - 80);
		add(scrollPane);
		
		
		
		
		viewGood();
	}
	
	

	
	public void viewGood() {
		try {
			Vector<AdminGoodsModel> goods = mainMenu.DAOGOOD.viewGoods();

			// Table
			JTable table = new JTable(new MyTableModel(goods));
			JTableHeader tableHeader = table.getTableHeader();
			table.setFont(CustomFont.THFont(17));
			tableHeader.setFont(CustomFont.THboldFont(18));
			table.setFillsViewportHeight(false);
			table.setDefaultRenderer(JLabel.class, new MyRenderer());
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
			table.setRowHeight(150);
			scrollPane.setViewportView(table);


			setVisible(true);
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid data.");
		} catch (Exception ex) {
			System.err.println("Error! " + ex.getMessage());
		} 

	}
	
	 class MyTableModel extends AbstractTableModel {
	        private String[] columnNames = {"รหัสสินค้า",
	                                        "รูปภาพสินค้า",
	                                        "ชื่อสินค้า",
	                                        "รายละรายสินค้า"};
	        private Object[][] data ;
	        

	        public int getColumnCount() {
	            return columnNames.length;
	        }

	        public MyTableModel( ) {
			}
	        public MyTableModel( Vector<AdminGoodsModel> goods) {
				super();
				try {
					this.data = new Object[goods.size()][4];
					for (int i = 0;i < goods.size(); i++ ) {
						JLabel img = new JLabel();
						AdminGoodsModel good = goods.get(i);
						File theFile = new File("show_product.png");
						FileOutputStream output = new FileOutputStream(theFile);
						byte buffty[] = new byte[1024];
						InputStream images  = (InputStream) good.getGoodImg();
//						System.out.println("read"+read);
						while(images.read(buffty) > 0) {
							output.write(buffty);
						}
						String path = theFile.getAbsolutePath();
						img.setIcon(ResizeImage(path));
						data[i][0] = good.getGoodId();
						data[i][1] = img;
						data[i][2] = good.getGoodName();
						data[i][3] = good.getGoodDescriptio();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			public int getRowCount() {
	            return data.length;
	        }

	        public String getColumnName(int col) {
	            return columnNames[col];
	        }

	        public Object getValueAt(int row, int col) {
	            return data[row][col];
	        }

	        /*
	         * JTable uses this method to determine the default renderer/
	         * editor for each cell.  If we didn't implement this method,
	         * then the last column would contain text ("true"/"false"),
	         * rather than a check box.
	         */
	        public Class getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }

	        public boolean isCellEditable(int row, int col) {
	            //Note that the data/cell address is constant,
	            //no matter where the cell appears onscreen.
	            if (col < 1) {
	                return false;
	            } else {
	                return true;
	            }
	        }


	        private void printDebugData() {
	            int numRows = getRowCount();
	            int numCols = getColumnCount();

	            for (int i=0; i < numRows; i++) {
	                System.out.print("    row " + i + ":");
	                for (int j=0; j < numCols; j++) {
	                    System.out.print("  " + data[i][j]);
	                }
	                System.out.println();
	            }
	            System.out.println("--------------------------");
	        }
	    }
	 public ImageIcon ResizeImage(String imgpath) {
			ImageIcon myIcon = new ImageIcon(imgpath);
			Image imge = myIcon.getImage();
			Image newImage = imge.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
			ImageIcon image = new ImageIcon(newImage);
			return image;

		}

}
