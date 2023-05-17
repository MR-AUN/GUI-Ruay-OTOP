package ruay.component.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont {
	 public static Font THFont(float size) {
	        Font TH = null;

	        try {
	            TH = Font.createFont(Font.TRUETYPE_FONT, new File("THSarabun.ttf")).deriveFont(size);
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("THSarabun.ttf")));
	            return TH;
	        } catch (FontFormatException | IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        return TH;

	    }
	 public static Font THboldFont(float size) {
	        Font TH = null;

	        try {
	            TH = Font.createFont(Font.TRUETYPE_FONT, new File("THSarabun Bold.ttf")).deriveFont(size);
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("THSarabun Bold.ttf")));
	            return TH;
	        } catch (FontFormatException | IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        return TH;

	    }
}
