package de.umpanet.naehen.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Geometrics {
	float cm = (72 / 2.54f) / 4 ; //50%
	Graphics2D g;
	Geometrics geo;

	public void setg2(Graphics2D g2) {
		
		this.g = g2;
		// TODO Auto-generated constructor stub
	}


	public Geometrics getInstance(){
		return geo;
	}
	public Point2D punktAufLine(Point2D startPunkt, Point2D endPunkt, double distance){
		double winkel =  Math.asin( startPunkt.distance(new Point2D.Double(startPunkt.getX(),endPunkt.getY()))
				/(endPunkt.distance(startPunkt)));
		Arc2D hilfswinkel = new Arc2D.Double(Arc2D.PIE);
		hilfswinkel .setArcByCenter(endPunkt.getX(),
				endPunkt.getY(),
				distance * cm,
				0,
				-winkel* (180 / Math.PI),
				Arc2D.PIE);
		return hilfswinkel.getEndPoint();

	}
	public void mark(Graphics2D g2, Point2D Punkt) {
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
	
	public void mark(Graphics2D g2, Point2D Punkt, Color color) {
		Color prevColor = g2.getColor();
		g2.setColor(color);
	    mark(g2, Punkt);
	    g2.setColor(prevColor);

	}
	
	public void mark(Graphics2D g2, double x , double y) {
		g2.setColor(Color.red);
		Point2D Punkt = new Point2D.Double(x, y);
		//mark it with a +
	    Line2D BP1 = new Line2D.Double(
	    		new Point2D.Double(Punkt.getX() - 0.5 * cm, Punkt.getY()), 
	    		new Point2D.Double(Punkt.getX() + 0.5 * cm, Punkt.getY()));
	    Line2D BP2 = new Line2D.Double(
	    		new Point2D.Double(Punkt.getX(), Punkt.getY() - 0.5 * cm), 
	    		new Point2D.Double(Punkt.getX(), Punkt.getY() + 0.5 * cm));
	    g2.draw(BP1);
	    g2.draw(BP2);
	    g2.setColor(Color.black);

	}
	
	public void mark(Graphics2D g2, double x , double y, Color color) {
		Color prevColor = g2.getColor();
		g2.setColor(color);
	    mark(g2,x, y);
	    g2.setColor(prevColor);

	}
	
	
	public void drawRotate(Graphics2D g2, double x, double y, int angle, String text) 
	{    
	    g2.translate((float)x,(float)y);
	    g2.rotate(Math.toRadians(angle));
	    g2.drawString(text,0,0);
	    g2.rotate(-Math.toRadians(angle));
	    g2.translate(-(float)x,-(float)y);
	}    
	
	
/*	public Iterator<Point2D> intersection(Line2D line, Arc2D arc){
		ArrayList<Point2D> intersections = new ArrayList<Point2D>();

		
		if((line.getX1() < arc.getMinX() && line.getX2() < arc.getMinX())
				||(line.getY1() < arc.getMinY() && line.getY2() < arc.getMinY()) 
				||(line.getX1() > arc.getMaxX() && line.getX2() < arc.getMaxX())
				||(line.getY1() > arc.getMaxY() && line.getY2() > arc.getMaxY())){
			//Keine Überschneidung
			return null;
		}

		final double[] coords = new double[6];
		for (PathIterator arcIter = arc.getPathIterator(null); !arcIter.isDone(); arcIter.next()) {
			
		}
		
		
		return intersections.iterator();
		
	}*/
	
	
	public void markrotate(Graphics2D g2, FancyLine line, String string) {
		Point2D middle = line.pointAtDistance(line.getP1(), line.length()/2);
		AffineTransform orig = g2.getTransform();
		g2.translate(middle.getX(), middle.getY());
		g2.rotate(Math.atan(line.steigung()));
		 middle = line.pointAtDistance(line.getP1(), line.length()/2);
		
		g2.drawString(string,-2,-2);
	

		g2.setTransform(orig);
		//g2.translate(middle.getX(), middle.getY());
	}
	
}
