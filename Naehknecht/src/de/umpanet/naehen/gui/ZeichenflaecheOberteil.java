package de.umpanet.naehen.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.Iterator;

import cib.util.geo.Geo2D;

import javax.swing.JPanel;

import de.umpanet.naehen.tools.FancyLine;
import de.umpanet.naehen.tools.Geometrics;
import de.umpanet.naehen.werte.Masse;

public class ZeichenflaecheOberteil  extends JPanel {
	  /**
	 * 
	 */
	float cm = (72 / 2.54f) / 2 ; //50%
	
	Masse m = new Masse();
	double abstand = 5 *cm;
	double zugabe = 6; //cm
	Geometrics geo = new Geometrics();
    double breite = m.getBrustumfang()/2+zugabe;
    Point2D rechtsOben = new Point2D.Double(abstand+breite *cm, abstand);
    Point2D linksOben = new Point2D.Double(abstand, abstand);
    Rectangle2D fläche = new Rectangle2D.Double(abstand, abstand, breite * cm, m.getVorderelänge()*cm);
    Line2D seitenaht = new Line2D.Double(
    		new Point2D.Double(abstand+ (breite/2) *cm, abstand), 
    		new Point2D.Double(abstand+ (breite/2)*cm, abstand + m.getRückenlänge() *cm));
    Point2D brustPunkt = new Point2D.Double(rechtsOben.getX() - ((m.getBrustpunktWeite() /2)* cm), rechtsOben.getY() + m.getBrustpunktTiefe()* cm);
    Graphics2D g2;
	
	Point2D start = new Point2D.Double(abstand,abstand);
	
	Line2D armausschnitt;
	private static final long serialVersionUID = 1L;

	@Override
	  public void paint(Graphics g) {
	    
	    super.paint(g);
	    g2 = (Graphics2D)g;
	    g2.setStroke(new BasicStroke(2));

	    

	    
	    //Zeichne Grundfläche
	    g2.draw(fläche);
	    
	    g2.draw(seitenaht);
	    this.setPreferredSize(new Dimension((int) fläche.getWidth(),(int) fläche.getHeight()));
	    
	    zeichneHilfslinien();
	    
	    //Armausschnitttiefe
	    //TODO mach das Maß abhängig
	    
	    
	    //Halsauschnitt vorne
	    //rechtsOben.setLocation(brustPunkt.getX(),(fläche.getMinY()+m.getRückenlänge()*cm) - (m.getVorderelänge()-m.getHalslochbreite()));
	   
	    Point2D halsSchulter = new Point2D.Double(rechtsOben.getX() - (m.getHalslochbreite() *cm), rechtsOben.getY());
	    Point2D halsVorne = new Point2D.Double(rechtsOben.getX(), rechtsOben.getY() + ((m.getHalsUmfang()/6 +1.5) *cm));
	    Point2D halsRefVorne = new Point2D.Double(halsSchulter.getX(), halsVorne.getY());
	    
	    QuadCurve2D halsauschnittVorne = new QuadCurve2D.Double(halsSchulter.getX(), halsSchulter.getY(), 
	    										halsRefVorne.getX(), halsRefVorne.getY(),
	    										halsVorne.getX(), halsVorne.getY());
	    g2.draw(halsauschnittVorne);
	    
	    //Halsauschnitt hinten
	    
	    Point2D halsSchulterHinten = new Point2D.Double(linksOben.getX() +(m.getHalslochbreite() *cm), linksOben.getY() -2*cm);
	    Point2D halsHinten = new Point2D.Double(linksOben.getX(), linksOben.getY() + 2*cm);
	    Point2D halsRefHinten = new Point2D.Double(halsSchulterHinten.getX(), halsHinten.getY());
	    
	    QuadCurve2D halsauschnittRücken = new QuadCurve2D.Double(halsSchulterHinten.getX(), halsSchulterHinten.getY(), 
	    										halsRefHinten.getX(), halsRefHinten.getY(),
	    										halsHinten.getX(), halsHinten.getY());
	    g2.draw(halsauschnittRücken);



	    
	    //Schulterpunkt hinten
	    Point2D schulterpuntkHinten =  new Point2D.Double(linksOben.getX() + ((m.getSchulterweiteHinten() /2)* cm), rechtsOben.getY() + 4* cm);
	    Line2D schulternahtHinten = new Line2D.Double(
	    		schulterpuntkHinten, 
	    		halsSchulterHinten);
	    g2.draw(schulternahtHinten);
	    
	    //Schulterabnäher hinten
	
	    double winkel =  Math.asin( schulterpuntkHinten.distance(new Point2D.Double(schulterpuntkHinten.getX(),halsSchulterHinten.getY()))
	    		/(halsSchulterHinten.distance(schulterpuntkHinten)));
	    Arc2D hilfswinkel = new Arc2D.Double(Arc2D.PIE);
	    Arc2D hilfswinkel2 = new Arc2D.Double(Arc2D.PIE);
	    Arc2D hilfswinkel3 = new Arc2D.Double(Arc2D.PIE);

		hilfswinkel.setArcByCenter(halsSchulterHinten.getX(),
				halsSchulterHinten.getY(),
              5 * cm,
              0,
              -winkel* (180 / Math.PI),
              Arc2D.PIE);
		
		
		hilfswinkel2.setArcByCenter(halsSchulterHinten.getX(),
				halsSchulterHinten.getY(),
              6.5 * cm,
              0,
              -winkel* (180 / Math.PI),
              Arc2D.PIE);
		hilfswinkel3.setArcByCenter(halsSchulterHinten.getX(),
				halsSchulterHinten.getY(),
              5.75 * cm,
              0,
              -winkel* (180 / Math.PI),
              Arc2D.PIE);
		
	    Point2D schulterabnäher1 = hilfswinkel.getEndPoint();
	    Point2D schulterabnäher2 = hilfswinkel2.getEndPoint();
	    Point2D schulterabnäher3 = hilfswinkel3.getEndPoint();
	    
	    Arc2D hilfswinkel4 = new Arc2D.Double(Arc2D.PIE);
	    hilfswinkel4.setArcByCenter(schulterabnäher3.getX(),
	    		schulterabnäher3.getY(),
              8 * cm,
              -winkel* (180 / Math.PI),
              -90,
              Arc2D.PIE);
	    
	    Point2D schulterabnäherSpitze = hilfswinkel4.getEndPoint();
	    
	    Line2D schulterabnäherL1 = new Line2D.Double(
	    		schulterabnäher1, 
	    		schulterabnäherSpitze);
	    Line2D schulterabnäherL2 = new Line2D.Double(
	    		schulterabnäher2, 
	    		schulterabnäherSpitze);
	    g2.draw(schulterabnäherL1);
	    g2.draw(schulterabnäherL2);
	    
	    //Schulterabnäher vorn
	    Point2D schulterTeilPunkt = new Point2D.Double(brustPunkt.getX(),halsSchulter.getY());
	    Point2D vordererArmLinienPunkt = new Point2D.Double(seitenaht.getX1() + m.getArmDurchmesser()/3 *cm , armausschnitt.getY1() );
	    Point2D vordererArmPunkt = new Point2D.Double(seitenaht.getX1() + m.getArmDurchmesser()/3 *cm , armausschnitt.getY1() - m.getArmDurchmesser()/4 );
	    
	    double dist = schulterpuntkHinten.distance(new Point2D.Double(schulterpuntkHinten.getX(),armausschnitt.getY1()));
	    double degree = (m.getBrustumfang()/20 * cm *180) / (Math.PI * dist);

	    hilfswinkel.setArcByCenter(vordererArmLinienPunkt.getX(),
	    		vordererArmLinienPunkt.getY(),
              dist,
              90,
             degree,
              Arc2D.PIE);
	   
	    Point2D schulterPunkt = hilfswinkel.getEndPoint();
	    mark(g2, schulterPunkt);
	    hilfswinkel.setArcByCenter(brustPunkt.getX(),
	    		brustPunkt.getY(),
              brustPunkt.distance(schulterTeilPunkt),
              90,
             90,
              Arc2D.PIE);
	    hilfswinkel2.setArcByCenter(schulterPunkt.getX(),
	    		schulterPunkt.getY(),
	              m.getSchulterBreite()*cm,
	              90,
	             -90,
	              Arc2D.PIE);
	    g2.draw(hilfswinkel);
	    //g2.draw(hilfswinkel2);
	    /*
	    Iterator< Point2D> iter = Geo2D.intersection(hilfswinkel, hilfswinkel2);
	    Point2D p1 = schulterPunkt; //TODO do this nice
	    if(iter.hasNext()){
	    	p1 = iter.next();
	    	if(iter.hasNext()){
		    	p1 = iter.next();
		    	System.out.println("P2 " + p1.getX() + " " + p1.getY());
	    	}
	    }
	    
	    Line2D vSchulterNaht = new Line2D.Double(p1, schulterPunkt);
	    
	    
	    dist = halsSchulter.distance(schulterTeilPunkt);
	    hilfswinkel.setArcByCenter(p1.getX(),
	    		p1.getY(),
	              dist+5, //TODO ???? +5
	             - 90,
	             360,
	              Arc2D.PIE);
	    
	   // g2.draw(vSchulterNaht);
	    g2.draw(hilfswinkel);
	    
	    iter = Geo2D.intersection(vSchulterNaht, hilfswinkel);
	    if(iter.hasNext()){
	    	p1 = iter.next();
	    	System.out.println("P " + p1.getX() + " " + p1.getY());
	    	if(iter.hasNext()){
		    	p1 = iter.next();
		    	System.out.println("P2 " + p1.getX() + " " + p1.getY());
	    	}
	    }
	    vSchulterNaht = new Line2D.Double(p1, schulterPunkt);
	    g2.draw(vSchulterNaht);
	    
	    Line2D abnäher1 = new Line2D.Double(p1, brustPunkt);
	    
	    g2.draw(abnäher1);
	    
	    
	    //Point2D abnäher2end = geo.punktAufLine(schulterTeilPunkt, brustPunkt, p1.distance(brustPunkt));
	    Point2D abnäher2end = new Point2D.Double(brustPunkt.getX(), brustPunkt.getY() -  p1.distance(brustPunkt));
	    Line2D abnäher2 = new Line2D.Double(abnäher2end, brustPunkt );
	    g2.draw(abnäher2);
	    
	   
	    Line2D schulterNahtTeil = new Line2D.Double(abnäher2end, halsSchulter);
	    g2.draw(schulterNahtTeil);
	    //g2.draw(hilfswinkel);
	    
	    
	    
	    //	    //Schulterpunkt vorne
//	    Point2D schulterpuntkVorn =  new Point2D.Double(rechtsOben.getX() - ((m.getSchulterweiteVorn() /2)* cm), rechtsOben.getY() + 4.5* cm);
//	    Line2D schulternahtVorn = new Line2D.Double(
//	    		schulterpuntkVorn, 
//	    		halsSchulter);
//	    g2.draw(schulternahtVorn); */

	    g2.setColor(Color.red);
	    
	    FancyLine tryl = new FancyLine(1*cm, 9*cm,20*cm, -10*cm);
	    Arc2D arc = new Arc2D.Double(Arc2D.PIE);
	    arc.setArcByCenter(4*cm, 4*cm, 5*cm,0,90, Arc2D.OPEN);
	    
	    ArrayList<Point2D> list = tryl.intersectionPt(arc);

	    //System.out.println(list.size() + "x " + list.get(0).getX() + " y " + list.get(0).getY());
	    //System.out.println("x " + list.get(1).getX() + " y " + list.get(1).getY());
	    
	    g2.draw(tryl);
	    g2.draw(arc);
	
	}

	private void mark(Graphics2D g2, Point2D Punkt) {
		//mark it with a +
	    Line2D BP1 = new Line2D.Double(
	    		new Point2D.Double(Punkt.getX() - 0.5 * cm, Punkt.getY()), 
	    		new Point2D.Double(Punkt.getX() + 0.5 * cm, Punkt.getY()));
	    Line2D BP2 = new Line2D.Double(
	    		new Point2D.Double(Punkt.getX(), Punkt.getY() - 0.5 * cm), 
	    		new Point2D.Double(Punkt.getX(), Punkt.getY() + 0.5 * cm));
	    g2.draw(BP1);
	    g2.draw(BP2);
	}
	
	private void zeichneHilfslinien(){
	    //Zeichne Hilfslinien
	    //Brustpunkt
	    g2.setColor(Color.BLUE);
	    
	    mark(g2, brustPunkt);
	    Line2D brustabnäherLinie = new Line2D.Double(new Point2D.Double(brustPunkt.getX(), fläche.getMinY()), new Point2D.Double(brustPunkt.getX(), fläche.getMaxY()));
	    g2.draw(brustabnäherLinie);
	    
	    Line2D vordereArmLinie = new Line2D.Double(new Point2D.Double(seitenaht.getX1() + m.getArmDurchmesser()/3 *cm , fläche.getMinY()), new Point2D.Double(seitenaht.getX1() + m.getArmDurchmesser()/3 *cm , fläche.getMaxY()));
	    g2.draw(vordereArmLinie);
	    
	    
	    Line2D taillienLinie = new Line2D.Double(new Point2D.Double(fläche.getMinX(), fläche.getMinY()+m.getRückenlänge()*cm), new Point2D.Double(fläche.getMaxX(), fläche.getMinY()+m.getRückenlänge()*cm));
	    g2.draw(taillienLinie);
	    g2.drawString("Taille",(int) taillienLinie.getX1()+2, (int) taillienLinie.getY1()-5);
	    
	    double armtiefe = m.getArmlochTiefe();
	    armausschnitt = new Line2D.Double(
	    		new Point2D.Double(abstand, abstand +armtiefe *cm), 
	    		new Point2D.Double(abstand +breite * cm, abstand +armtiefe *cm));
	    g2.draw(armausschnitt);
	    g2.drawString("Armtiefe",(int) armausschnitt.getX1()+2, (int) armausschnitt.getY1()-5);
	    g2.setColor(Color.BLACK);
	}

	}
