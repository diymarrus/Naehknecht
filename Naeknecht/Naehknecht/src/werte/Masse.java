package werte;

import java.util.HashMap;

public class Masse {

	private static Masse masse = new Masse();
	private double körpergröße = 177.1;
	private double rückenlänge = 40.4;
	private double brustumfang = 94.5; 
	private double taille = 76.5;
	private double brustpunktWeite = 18.8;
	private double brustpunktTiefe = 28.2;
	private double schulterweiteHinten = 42;
	private double schulterweiteVorn = 38.9;
	private double hosenlänge = 108;
	private double hüftweite = 92.5;
	
	private HashMap<String,Double> werte = new HashMap<String,Double>();

	public Masse getInstance(){
		return masse;
	}


	public HashMap<String,Double> alleWerte(){
		werte.put("Körpergröße",körpergröße);
		werte.put("Rückenlänge",rückenlänge);
		werte.put("Brustumfang", brustumfang);
		werte.put("Taille", taille);
		werte.put("BrustpunktWeite", brustpunktWeite);
		werte.put("BrustpunktTiefe", brustpunktWeite);
		werte.put("schulterweiteHinten", schulterweiteHinten);
		werte.put("SchulterweiteVorn", schulterweiteVorn);
		
		return werte;
	}
	
	
	
	
	public double getKörpergröße() {
		return körpergröße;
	}
	public void setKörpergröße(double körpergröße) {
		this.körpergröße = körpergröße;
	}
	public double getRückenlänge() {
		return rückenlänge;
	}
	public void setRückenlänge(double rückenlänge) {
		this.rückenlänge = rückenlänge;
	}
	public double getBrustumfang() {
		return brustumfang;
	}
	public void setBrustumfang(double brustumfang) {
		this.brustumfang = brustumfang;
	}

	public double getTaille() {
		return taille;
	}
	public void setTaille(double taille) {
		this.taille = taille;
	}

	public double getBrustpunktWeite() {
		return brustpunktWeite;
	}
	public void setBrustpunktWeite(double brustpunktWeite) {
		this.brustpunktWeite = brustpunktWeite;
	}
	public double getBrustpunktTiefe() {
		return brustpunktTiefe;
	}
	public void setBrustpunktTiefe(double brustpunktTiefe) {
		this.brustpunktTiefe = brustpunktTiefe;
	}
	public double getSchulterweiteHinten() {
		return schulterweiteHinten;
	}
	public void setSchulterweiteHinten(double schulterweiteHinten) {
		this.schulterweiteHinten = schulterweiteHinten;
	}
	public double getSchulterweiteVorn() {
		return schulterweiteVorn;
	}
	public void setSchulterweiteVorn(double schulterweiteVorn) {
		this.schulterweiteVorn = schulterweiteVorn;
	}


	public double getHosenlänge() {
		return hosenlänge;
	}


	public void setHosenlänge(double hosenlänge) {
		this.hosenlänge = hosenlänge;
	}


	public double getHüftweite() {
		return hüftweite;
	}


	public void setHüftweite(double hüftweite) {
		this.hüftweite = hüftweite;
	}
}