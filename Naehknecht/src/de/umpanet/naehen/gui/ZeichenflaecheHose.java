package de.umpanet.naehen.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import de.umpanet.naehen.werte.Masse;

public class ZeichenflaecheHose extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7132609802018647774L;

	float cm = (72 / 2.54f) / 2 ; //50%
	
	Masse m = new Masse();
	double abstand = 70;
	double zugabe = 6; //cm
	
	
	@Override
	  public void paint(Graphics g) {
		
		super.paint(g);
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setStroke(new BasicStroke(2));
	    double breite = (m.getHüftweite()+ 7) / 2;
	    double breiteRückenteil = breite / 2 + 1.25;
	    double breiteVorderteil = breite / 2-1.25;
	    //Zeichne Grundfläche
	    Rectangle2D rückenteil = new Rectangle2D.Double(abstand, abstand, breiteRückenteil * cm, m.getHosenlänge()*cm);
	    g2.draw(rückenteil);
	    
	    Rectangle2D vorderteil = new Rectangle2D.Double(abstand+(breiteRückenteil * cm) + 15 * cm, abstand, breiteVorderteil * cm, m.getHosenlänge()*cm);
	    g2.draw(vorderteil);
	    
	    Line2D obereHüftlinie = new Line2D.Double(abstand, abstand + 10 * cm , vorderteil.getMaxX(), abstand + 10 * cm);
	    g2.draw(obereHüftlinie);
	    
	    Line2D hüftlinie = new Line2D.Double(abstand, abstand + 20 * cm , vorderteil.getMaxX(), abstand + 20 * cm);
	    g2.draw(hüftlinie);
	    
	    Line2D schrittlinie = new Line2D.Double(abstand, abstand + 27* cm , vorderteil.getMaxX(), abstand + 27 * cm);
	    g2.draw(schrittlinie);
	    
	    Line2D knielinie = new Line2D.Double(abstand, abstand + 27* cm , vorderteil.getMaxX(), abstand + 27 * cm);
	    g2.draw(knielinie);
	    
	    Point2D schrittPunkt = new Point2D.Double(rückenteil.getMaxX() + 10 * cm, schrittlinie.getY1());

	    Line2D SP2 = new Line2D.Double(
	    		new Point2D.Double(schrittPunkt.getX(), schrittPunkt.getY() - 0.5 * cm), 
	    		new Point2D.Double(schrittPunkt.getX(), schrittPunkt.getY() + 0.5 * cm));

	    g2.draw(SP2);
	    
	    double vordereMitteX = schrittPunkt.distance(new Point2D.Double(vorderteil.getMaxX(), schrittPunkt.getY())) /2;
	    Line2D vordereMitte = new Line2D.Double(schrittPunkt.getX() +vordereMitteX, abstand, schrittPunkt.getX() +vordereMitteX, vorderteil.getMaxY());
	    g2.draw(vordereMitte);
	    
	    double hintereMitteX = schrittPunkt.distance(new Point2D.Double(rückenteil.getMinX(), schrittPunkt.getY()))/2;
	    Line2D hintereMitte = new Line2D.Double(abstand +hintereMitteX - (1.5 * cm), abstand, abstand +hintereMitteX- (1.5 * cm), rückenteil.getMaxY());
	    g2.draw(hintereMitte);
		
	}

}
