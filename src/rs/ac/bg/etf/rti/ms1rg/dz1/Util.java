package rs.ac.bg.etf.rti.ms1rg.dz1;

public class Util {

	public static Boolean insideSquare(float x, float y, float lfx, float lfy, float w, float h) {
		return x >= lfx && x <= lfx + w && y >= lfy && y <= lfy+h;
	}
	
	public static Boolean uCigli(float x, float y, Cigla c) {
		return insideSquare(x, y, c.x, c.y, c.SIRINA, c.VISINA);
	}
	
	public static Boolean uPloci(float x, float y, Ploca p) {
		return insideSquare(x, y, p.getX() - p.SIRINA/2, p.getY(), p.SIRINA, p.VISINA);
	}
}
