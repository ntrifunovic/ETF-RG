package rs.ac.bg.etf.rti.ms1rg.dz1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.StyledEditorKit.BoldAction;

public class Igra extends JPanel implements Runnable {
	private final static Color POZADINA = new Color(10, 10, 10);
	
	final static int SIRINA = 800;
	final static int VISINA = 600;
	
	final static float POCETAK = VISINA*0.1f;
	final static float KRAJ = VISINA*0.5f;
	
	final static float BR_HORIZONTALNIH_CIGLI = 6;
	final static float BR_VERTIKALNIH_CIGLI = 3;
	
	static int score = 0;
	static long starttime = 0;
	static long time = 0;
	
	static Igra igra = new Igra();
	
	public Igra() {
	    addMouseListener(Lopta.lopta);
	    addMouseMotionListener(Ploca.ploca);
	}

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Dimension d = getSize();
        g2d.setPaint(POZADINA);
        g2d.fillRect(0, 0, d.width, d.height);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Helvetica", Font.BOLD, 25));
        g2d.drawString("Time: " + String.format("%.2f", (float)(time - starttime)/1000f) + " | Score: " + score, 30, 35);
        
        Ploca.ploca.draw(g2d);
        Lopta.lopta.draw(g2d);
        
        for(Cigla cigla:Cigla.cigle) {
        	cigla.draw(g2d);
        }
        
    }

	public void run() {
		long  starttime;

		while(true) {
			starttime = System.currentTimeMillis();
			
			try {
				repaint();
				starttime += 40;
				Thread.sleep(Math.max(0, starttime-System.currentTimeMillis()));
			} catch (InterruptedException e) {
				break;
			}
		}
	}		
		
    public static void main(String s[]) {
		igra.setOpaque(true);
		igra.setPreferredSize(new Dimension(SIRINA, VISINA));
	    
		JFrame frame = new JFrame("Igra - RG DZ1 [C]");
		
		frame.addWindowListener(new WindowAdapter() {
	        	public void windowClosing(WindowEvent e) {System.exit(0);}
		});
	    		
		frame.getContentPane().add(igra);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		Thread t = new Thread(igra);
		t.start();
    }
}
