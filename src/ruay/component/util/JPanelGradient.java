package ruay.component.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class JPanelGradient extends JPanel {
	Color color1;
	Color color2;
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point point1 = new Point(20, 20);
        Point point2 = new Point(
                getWidth() -10, 
                getHeight() -10 );
        final GradientPaint gp = new GradientPaint(
                point1,  color1,
                point2, color2,
                true);

        // we need a Graphics2D to use GradientPaint.
        // If this is Swing, it should be one..
        final Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(gp);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
	

	public JPanelGradient(Color color1,Color color2) {
		this.color1 = color1;
		this.color2 = color2;
	}
}
