package ruay.admingoods;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyRenderer extends JLabel implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		try {
			if(column == 1) {
//				System.out.print("MyRender Test true");
				
				JLabel reulst = (JLabel) value;
				return reulst;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return this;
	}

	
}
