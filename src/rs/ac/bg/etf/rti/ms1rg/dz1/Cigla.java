package rs.ac.bg.etf.rti.ms1rg.dz1;

import java.awt.*;
import java.awt.geom.*;
import java.util.LinkedList;
import java.util.Random;

public class Cigla {
	private Color boja;
	float x, y;
	final static float VISINA = (Igra.KRAJ - Igra.POCETAK)/Igra.BR_VERTIKALNIH_CIGLI;
	final static float SIRINA = Igra.SIRINA/Igra.BR_HORIZONTALNIH_CIGLI;
	
	private final static Rectangle2D PRAVOUGAONIK = new Rectangle2D.Float(0, 0, SIRINA, VISINA);
	
	static LinkedList<Cigla> cigle;
	
	static  {
		cigle = new LinkedList<Cigla>();

		Random rand = new Random();
		
		for(float y = Igra.POCETAK; y < Igra.KRAJ; y+=VISINA)
			for(float x = 0; x < Igra.SIRINA; x+=SIRINA) {
				
				float r = rand.nextFloat();
				float g = rand.nextFloat();
				float b = rand.nextFloat();
				cigle.push(new Cigla(new Color(r, g, b), x, y));
			}
	}
	
	private Cigla(Color boja, float x, float y) {
		this.boja = boja;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2D) {
		g2D.setPaint(boja);
		
		AffineTransform oldTransform = g2D.getTransform();

		g2D.translate(x, y);
		
		g2D.fill(PRAVOUGAONIK);
		
		g2D.setTransform(oldTransform);
	}
	
}
