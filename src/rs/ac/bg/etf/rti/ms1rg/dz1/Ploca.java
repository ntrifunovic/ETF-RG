package rs.ac.bg.etf.rti.ms1rg.dz1;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Ploca implements MouseMotionListener {
	private final static Color boja = new Color(20, 100, 100);
	
	final static float VISINA = 10;
	final static float SIRINA = 100;

	private final static RoundRectangle2D OBLIK = new RoundRectangle2D.Float(-SIRINA/2, 0, SIRINA, VISINA, 3f, 3f);
	
	private float x;
	
	float getX() {
		return x;
	}
	
	float getY() {
		return Igra.VISINA-VISINA;
	}
	
	private Ploca() {
	
	}
	
	static final Ploca ploca = new Ploca();
	
	//MouseMotionListener implementation
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
	}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void draw(Graphics2D g2D) {
		g2D.setPaint(boja);
		
		AffineTransform oldTransform = g2D.getTransform();
	
		g2D.translate(getX(), getY());
		
		g2D.fill(OBLIK);
		
		g2D.setTransform(oldTransform);
	}
}
