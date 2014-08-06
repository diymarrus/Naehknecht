package gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import werte.Masse;

public class ZeichenflächeOberteil  extends JPanel {
	  /**
	 * 
	 */
	float cm = (72 / 2.54f) / 2 ; //50%
	
	Masse m = new Masse();
	double abstand = 70;
	double zugabe = 6; //cm
	
	Point2D start = new Point2D.Double(abstand,abstand);
	private static final long serialVersionUID = 1L;

	@Override
	  public void paint(Graphics g) {
	    
	    super.paint(g);
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setStroke(new BasicStroke(2));

	    
	    double breite = m.getBrustumfang()/2+zugabe;
	    Point2D rechtsOben = new Point2D.Double(abstand+breite *cm, abstand);
	    Point2D linksOben = new Point2D.Double(abstand, abstand);
	    
	    //Zeichne Grundfläche
	    Rectangle2D fläche = new Rectangle2D.Double(abstand, abstand, breite * cm, m.getRückenlänge()*cm);
	    g2.draw(fläche);
	    Line2D seitenaht = new Line2D.Double(
	    		new Point2D.Double(abstand+ (breite/2) *cm, abstand), 
	    		new Point2D.Double(abstand+ (breite/2)*cm, abstand + m.getRückenlänge() *cm));
	    g2.draw(seitenaht);
	    
	    //Armausschnitttiefe
	    //TODO mach das Maß abhängig
	    double armtiefe = (breite /2) + 1; //cm
	    Line2D armausschnitt = new Line2D.Double(
	    		new Point2D.Double(abstand, abstand +armtiefe *cm), 
	    		new Point2D.Double(abstand +breite * cm, abstand +armtiefe *cm));
	    g2.draw(armausschnitt);
	    
	    //Halsauschnitt vorne
	    Point2D halsSchulter = new Point2D.Double(rechtsOben.getX() - 7*cm, rechtsOben.getY());
	    Point2D halsVorne = new Point2D.Double(rechtsOben.getX(), rechtsOben.getY() + 7.5 *cm);
	    Point2D halsRefVorne = new Point2D.Double(halsSchulter.getX(), halsVorne.getY());
	    
	    QuadCurve2D halsauschnittVorne = new QuadCurve2D.Double(halsSchulter.getX(), halsSchulter.getY(), 
	    										halsRefVorne.getX(), halsRefVorne.getY(),
	    										halsVorne.getX(), halsVorne.getY());
	    g2.draw(halsauschnittVorne);
	    
	    //Halsauschnitt hinten
	    Point2D halsSchulterHinten = new Point2D.Double(linksOben.getX() +7.5 *cm, linksOben.getY());
	    Point2D halsHinten = new Point2D.Double(linksOben.getX(), linksOben.getY() + 2*cm);
	    Point2D halsRefHinten = new Point2D.Double(halsSchulterHinten.getX(), halsHinten.getY());
	    
	    QuadCurve2D halsauschnittRücken = new QuadCurve2D.Double(halsSchulterHinten.getX(), halsSchulterHinten.getY(), 
	    										halsRefHinten.getX(), halsRefHinten.getY(),
	    										halsHinten.getX(), halsHinten.getY());
	    g2.draw(halsauschnittRücken);

	    //Brustpunkt
	    Point2D brustPunkt = new Point2D.Double(rechtsOben.getX() - ((m.getBrustpunktWeite() /2)* cm), rechtsOben.getY() + m.getBrustpunktTiefe()* cm);
	    //mark it with a +
	    Line2D BP1 = new Line2D.Double(
	    		new Point2D.Double(brustPunkt.getX() - 0.5 * cm, brustPunkt.getY()), 
	    		new Point2D.Double(brustPunkt.getX() + 0.5 * cm, brustPunkt.getY()));
	    Line2D BP2 = new Line2D.Double(
	    		new Point2D.Double(brustPunkt.getX(), brustPunkt.getY() - 0.5 * cm), 
	    		new Point2D.Double(brustPunkt.getX(), brustPunkt.getY() + 0.5 * cm));
	    g2.draw(BP1);
	    g2.draw(BP2);
	
	    //Schulterpunkt vorne
	    Point2D schulterpuntkVorn =  new Point2D.Double(rechtsOben.getX() - ((m.getSchulterweiteVorn() /2)* cm), rechtsOben.getY() + 4.5* cm);
	    Line2D schulternahtVorn = new Line2D.Double(
	    		schulterpuntkVorn, 
	    		halsSchulter);
	    g2.draw(schulternahtVorn);
	    
	    //Schulterpunkt hinten
	    Point2D schulterpuntkHinten =  new Point2D.Double(linksOben.getX() + ((m.getSchulterweiteHinten() /2)* cm), rechtsOben.getY() + 4* cm);
	    Line2D schulternahtHinten = new Line2D.Double(
	    		schulterpuntkHinten, 
	    		halsSchulterHinten);
	    g2.draw(schulternahtHinten);
	    
	    //Schulterabnäher
	
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
	    
	    
	    
	  }
	
		
	}
