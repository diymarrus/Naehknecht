package de.umpanet.naehen.werte;

import java.util.HashMap;

public class Masse {

	private static Masse masse = new Masse();
	private double körpergröße = 177.1;
	private double rückenlänge = 40.4;
	private double vorderelänge = 45.8;
	private double brustumfang = 94.5; 
	private double unterbrustumfang;
	
	private double taillenumfang = 76.5;
	private double hüfttiefe;
	
	private double brustpunktWeite = 18.8;
	private double brustpunktTiefe = 28.2;
	private double schulterweiteHinten = 42;
	private double schulterweiteVorn = 38.9;
	private double hosenlänge = 108;
	private double hüftweite = 92.5;
	private double armDurchmesser = 10.8;
	
	private double schulterBreite = 14.0;
	private double halsUmfang = 37.2;
	private double halslochbreite = ((halsUmfang/6 +1));
	
	private HashMap<String,Double> werte = new HashMap<String,Double>();

	public Masse getInstance(){
		return masse;
	}


	public HashMap<String,Double> alleWerte(){
		werte.put("Körpergröße",körpergröße);
		werte.put("Rückenlänge",rückenlänge);
		werte.put("Brustumfang", brustumfang);
		werte.put("Taille", taillenumfang);
		werte.put("BrustpunktWeite", brustpunktWeite);
		werte.put("BrustpunktTiefe", brustpunktWeite);
		werte.put("schulterweiteHinten", schulterweiteHinten);
		werte.put("SchulterweiteVorn", schulterweiteVorn);
		werte.put("ArmDurchmesser", armDurchmesser);
		
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
		return taillenumfang;
	}
	public void setTaille(double taille) {
		this.taillenumfang = taille;
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




	public void setArmDurchmesser(double armDurchmesser) {
		this.armDurchmesser = armDurchmesser;
	}


	public double getHüftweite() {
		return hüftweite;
	}


	public void setHüftweite(double hüftweite) {
		this.hüftweite = hüftweite;
	}


	public double getSchulterBreite() {
		return schulterBreite;
	}


	public void setSchulterBreite(double schulterBreite) {
		this.schulterBreite = schulterBreite;
	}


	public double getHalsUmfang() {
		return halsUmfang;
	}


	public void setHalsUmfang(double halsUmfang) {
		this.halsUmfang = halsUmfang;
	}


	public double getVorderelänge() {
		return vorderelänge;
	}


	public void setVorderelänge(double vorderelänge) {
		this.vorderelänge = vorderelänge;
	}


	public double getHalslochbreite() {
		return halslochbreite;
	}


	public void setHalslochbreite(double halslochbreite) {
		this.halslochbreite = halslochbreite;
	}
	
	public double getArmlochTiefe(){
		if(this.brustumfang>=120){
			return this.brustumfang/10 +10.5;
		}
		return this.brustumfang/10 +11;
	}
	
	public double getArmDurchmesser(){
		if(this.brustumfang<=89){
			return this.brustumfang/8-1.5;
		}else if(this.brustumfang <= 99){
			return this.brustumfang/8-1;
		}else if(this.brustumfang <= 109){
			return this.brustumfang/8-0.5;
		}else if(this.brustumfang <= 119){
			return this.brustumfang/8;
		}else{
			return this.brustumfang/8+0.5;
		}
	}
	
	public double getArmtiefe(){
		return (getBrustumfang()/10)+11;
	}
}