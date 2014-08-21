package rs.ac.bg.etf.rti.ms1rg.dz1;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.*;


public class Lopta implements Runnable, MouseListener {
	private Color boja = new Color(50, 50, 90);
	private float x, y;
	private float vx, vy;
	private final static float PRECNIK = 25;
	
	private final static Ellipse2D OBLIK = new Ellipse2D.Float(-PRECNIK/2, -PRECNIK/2, PRECNIK, PRECNIK);
	
	Boolean free = false;
	
	Thread t = new Thread(this);
	
	private Lopta() {
		
	}
	
	float getX() {
		if(free)
			return x;
		return Ploca.ploca.getX();
	}
	
	float getY() {
		if(free)
			return y;
		return Ploca.ploca.getY() - PRECNIK/2;
	}
	
	static Lopta lopta = new Lopta();
	
	
	//MouseListener implementation
	public void mouseClicked(MouseEvent e) {
		if(free)
			return;
		
		Random rand = new Random();
		
		vy = -rand.nextFloat();
		vx = (rand.nextBoolean() ? 1 : -1) * (float)Math.sqrt(1 - vy*vy);
		
		x = getX();
		y = getY();
		
		free = true;
		Igra.starttime = System.currentTimeMillis();
		t.start();
	}
	
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}

	public void draw(Graphics2D g2D) {
		g2D.setPaint(boja);
		
		AffineTransform oldTransform = g2D.getTransform();

		g2D.translate(getX(), getY());
		
		g2D.fill(OBLIK);
		
		g2D.setTransform(oldTransform);
	}
	
	public void run() {
		while(true) {
			try {
				Igra.time = System.currentTimeMillis(); 
				
				Thread.sleep(Math.max(0, 10 - (System.currentTimeMillis() - Igra.time)));
				
				Iterator<Cigla> i = Cigla.cigle.iterator();
				while (i.hasNext()) {
				   Cigla c = i.next();
				   
				   if(Util.uCigli(x + vx + PRECNIK/2, y + vy, c) || Util.uCigli(x + vx - PRECNIK/2, y + vy, c)) {
					   vx *= -1;
					   i.remove();
					   Igra.score++;
					   break;
				   } 
				   
				   if(Util.uCigli(x + vx, y + vy + PRECNIK/2, c) || Util.uCigli(x + vx, y + vy - PRECNIK/2, c)) {
					   vy *= -1;
					   i.remove();
					   Igra.score++;
					   break;
				   }
				}
				
				if(Util.uPloci(x + vx + PRECNIK/2, y + vy, Ploca.ploca) || Util.uPloci(x + vx - PRECNIK/2, y + vy, Ploca.ploca))
					vx *= -1;
				   
			   	if(Util.uPloci(x + vx, y + vy + PRECNIK/2, Ploca.ploca))
					vy *= -1;
				
				if(x + vx - PRECNIK/2 <= 0 || x + vx + PRECNIK/2  >= Igra.SIRINA)
					vx *= -1;
				
				if(y + vy - PRECNIK/2 <= 0)
					vy *= -1;
				
				if(y + vy - PRECNIK/2 > Igra.VISINA) {
					return;
				}
					
				
				x += vx;
				y += vy;
				
				Igra.igra.repaint();
				
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
}
