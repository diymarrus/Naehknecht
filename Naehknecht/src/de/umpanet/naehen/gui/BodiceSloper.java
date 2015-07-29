package de.umpanet.naehen.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.QuadCurve2D;

import javax.swing.JPanel;

import de.umpanet.naehen.tools.FancyLine;
import de.umpanet.naehen.tools.Geometrics;
import de.umpanet.naehen.werte.ConstructionMeasuerments;

public class BodiceSloper extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics2D g2;
	double cm = Math.round(100.0* (72 / 2.54f) / 4)/100.0 ;//50%
	private double dist = 5*cm ;
	 
	
	
	//leftUpper corner
	private Point2D refLU = new Point2D.Double(dist, dist);
	//rightUpper corner
	private Point2D refRU;
	private int fitclass;
	private double waistAllowance;
	private double hipAllowance;
	private ConstructionMeasuerments m;
	private FancyLine baseline;
	private FancyLine seamline;
	private FancyLine shoulderline;
	private float paintwidth = 70;
	private FancyLine bustline;
	private FancyLine waistline;
	private FancyLine hipline;
	private java.awt.geom.Path2D.Double centerBack;
	private Geometrics geo = new Geometrics();
	private Double waistlinePoint;
	private Point2D bustlinePoint;
	private Double hiplinePoint;
	private Point2D armlineBustline;
	private FancyLine armlineBack;
	private FancyLine sideSeamBack;
	private FancyLine sideSeamFront;
	private FancyLine armlineFront;
	private FancyLine centerFront;
	private FancyLine bustDartLine;
	private FancyLine shoulderBack;
	
	public BodiceSloper(ConstructionMeasuerments m, int fitclass) {
		
		this.fitclass = fitclass;
		addAllowence(m);
		this.m = m;
		this.setPreferredSize(new Dimension((int) (paintwidth*cm), (int) (dist+m.getNeckToWaistCenterBack()+m.getWaistToButtockHeight()+20*cm) ));
		refRU = new Point2D.Double(paintwidth * cm, dist);
		System.out.println(refRU);
	

	}
	
	@Override
	  public void paint(Graphics g) {
	    
	    super.paint(g);
	    g2 = (Graphics2D)g;
	    g2.setStroke(new BasicStroke(1));
	    geo.setg2(g2);
	    
	    paintReferenceLines();
	    paintBasics();
	    
	}
	private void paintReferenceLines() {
		
		   g2.setColor(Color.BLUE);
		   
		   Point2D seamPointR = new Point2D.Double(refRU.getX(), refRU.getY()+m.getNeckToWaistCenterBack()+m.getWaistToButtockHeight()+10*cm);
		   Point2D seamPointL = new Point2D.Double(dist, refRU.getY()+m.getNeckToWaistCenterBack()+m.getWaistToButtockHeight()+10*cm);
		   //baseline
		   this.baseline = new FancyLine(refRU, seamPointR);
		   g2.draw(baseline);
		   geo.markrotate(g2, this.baseline,"Baseline");
		 
		   this.seamline = new FancyLine(seamPointL, seamPointR);
		   g2.draw(seamline);
		   g2.drawString("Seamline",(int) seamline.getX1()+2, (int) seamline.getY1()-5);
		   
		   this.shoulderline = new FancyLine(refLU, refRU);
		   g2.draw(shoulderline);
		   g2.drawString("Shoulderline",(int) shoulderline.getX1()+2, (int) shoulderline.getY1()-5);
		   Point2D refbustR = baseline.pointAtDistance(refRU, m.getArmholeDepth());
		   Point2D refbustL = new Point2D.Double(dist, refbustR.getY());
		  
		   this.bustline = new FancyLine (refbustL, refbustR);
		   g2.draw(bustline);
		   g2.drawString("Bustline",(int) bustline.getX1()+2, (int) bustline.getY1()-5);
		   Point2D refwaistR = baseline.pointAtDistance(refRU, m.getNeckToWaistCenterBack());
		   Point2D refwaistL = new Point2D.Double(dist, refwaistR.getY());		   
		   
		   this.waistline = new FancyLine(refwaistL,refwaistR);
		   g2.draw(waistline);
		   g2.drawString("Waistline",(int) waistline.getX1()+2, (int) waistline.getY1()-5);
		   double hipY = refwaistR.getY()+m.getWaistToButtockHeight();
		   
		   this.hipline = new FancyLine(dist, hipY , baseline.getX1(), hipY);	   
		   g2.draw(hipline);
		   g2.drawString("Hipline",(int) hipline.getX1()+2, (int)hipline.getY1()-5);
		   
		   
		   g2.setColor(Color.BLACK);
		   this.waistlinePoint = new Point2D.Double(baseline.getX1()-2*cm, waistline.getY1());
		   FancyLine upperCenterBack = new FancyLine(refRU, this.waistlinePoint );
		   
		   FancyLine lowerCenterBack = new FancyLine(this.waistlinePoint , new Point2D.Double(baseline.getX1()-2*cm, seamline.getY2()));
		   
		   this.centerBack = new Path2D.Double();
		   this.centerBack.append(upperCenterBack, true);
		   this.centerBack.append(lowerCenterBack, true);
		   geo.markrotate(g2, upperCenterBack,"CenterBack");
		   g2.draw(centerBack);
		   
		   this.bustlinePoint = upperCenterBack.intersectionPt(bustline);
		   geo.mark(g2,this.bustlinePoint, Color.cyan);
		   this.hiplinePoint = new Point2D.Double(baseline.getX1()-2*cm, hipY);
		   geo.mark(g2, this.hiplinePoint, Color.green);
	}
	
	private void paintBasics(){
		// paint amrline in backsloper
		this.armlineBustline = this.bustline.pointAtDistance(bustlinePoint, this.m.getAcrossBackWidth());
		geo.mark(g2, this.armlineBustline, Color.red);
		
		this.armlineBack = new FancyLine(this.armlineBustline.getX(), this.shoulderline.getY1(), this.armlineBustline.getX(), this.hipline.getY1());
		g2.draw(armlineBack);
		
		//paint sideseam 2/3 armdiameter width from armline
		Point2D sideSeamBust = new Point2D.Double(this.armlineBustline.getX() - (this.m.getArmDiameter()*2/3), this.bustline.getY1());
		this.sideSeamBack = new FancyLine(sideSeamBust, new Point2D.Double(sideSeamBust.getX(), this.seamline.getY1()));
		g2.draw(sideSeamBack);
		
		//paint front side seam 8cm from back side seam.
		Point2D sideSeamBustF = new Point2D.Double(sideSeamBack.x1-8*cm, this.bustline.getY1());
		this.sideSeamFront = new FancyLine(sideSeamBustF, new Point2D.Double(sideSeamBack.x2-8*cm, sideSeamBack.y2));
		g2.draw(sideSeamFront);
		
		//paint front armline 1/3 diameter from sideseam
		double armlineX = sideSeamBustF.getX()-this.m.getArmDiameter()/3;
		this.armlineFront = new FancyLine(armlineX, 0, armlineX, this.waistline.getY1());
		g2.draw(armlineFront);
		
		//paint center front
		double centerfrontX = armlineX - this.m.getBustWidth();
		this.centerFront = new FancyLine(centerfrontX, 0 , centerfrontX, this.seamline.getY1());
		g2.draw(this.centerFront);
		
		if((armlineX - centerfrontX) + (this.sideSeamBack.getX1() - this.bustlinePoint.getX() ) != this.m.getBustChestGirth()/2){
			System.out.println( "Brustweite stimmt nicht " + ((armlineX - centerfrontX) + (this.bustlinePoint.getX() - this.sideSeamBack.getX1() ))  / cm + "  " + this.m.getBustChestGirth()/(2) / cm);
		}
		//paint bust dart line
		Point2D bustlFront = new Point2D.Double(centerfrontX,this.bustline.getY1());
		double bustDartX =  this.bustline.pointAtDistance(bustlFront, this.m.getBustPointWidth()).getX();
		this.bustDartLine = new FancyLine(bustDartX,0,bustDartX, this.seamline.getY1());
		g2.draw(bustDartLine);
		
		//Neckline Point
		double neckHoleWidth = this.m.getNeckHoleDepth()/6 + 1*cm;
		Point2D sNlPRef = this.shoulderline.pointAtDistance(refRU, this.m.getNeckHoleDepth());
		Point2D sNlP = new Point2D.Double(sNlPRef.getX(), sNlPRef.getY() - 2*cm);
		geo.mark(g2, sNlP, Color.GREEN);
		
		
		//back shoulder
		QuadCurve2D necklineback = new QuadCurve2D.Double(sNlP.getX(), sNlP.getY(), sNlPRef.getX(), sNlPRef.getY(), this.refRU.getX(), this.refRU.getY());
		g2.draw(necklineback);
		
		Point2D hP = new Point2D.Double(this.armlineBack.getX1(), this.shoulderline.getY1() + 1.5 *cm);
		shoulderBack = new FancyLine(sNlP, hP, this.m.getShoulderWidth() + 1*cm);
		System.out.println(this.m.getShoulderWidth() / cm + " " );
		g2.draw(shoulderBack);
		
		
	}
	
private void addAllowence(ConstructionMeasuerments m) {
		//TODO Units
		//TODO move this into construction measurments
		//very tight cloth
		if (fitclass == 0) {
			//underwear
			return;
		}
		if(fitclass == 1) {
			//corsets
			m.setBustWidth(m.getBustWidth()+0.5);
			m.setAcrossBackWidth(m.getAcrossBackWidth()+0.15);
			m.setShoulderWidth(m.getShoulderWidth()+0.15);
			m.setArmDiameter(m.getArmDiameter()+0.3);
			m.setArmholeDepth(m.getArmholeDepth()+0.25);
			this.waistAllowance = 1;
			this.hipAllowance = 1;
			
		}
		else if(fitclass == 2) {
			//very fitted dresses
			m.setBustWidth(m.getBustWidth()+0.8);
			m.setAcrossBackWidth(m.getAcrossBackWidth()+0.4);
			m.setShoulderWidth(m.getShoulderWidth()+0.4);
			m.setArmDiameter(m.getArmDiameter()+0.7);
			m.setArmholeDepth(m.getArmholeDepth()+0.7);
			this.waistAllowance = 3;
			this.hipAllowance = 3;
		}
		//pretty tight cloth (3+4)
		else if(fitclass == 4) {
			//tight blouse or very tight jacket
			m.setBustWidth(m.getBustWidth()+1.4);
			m.setAcrossBackWidth(m.getAcrossBackWidth()+0.9);
			m.setShoulderWidth(m.getShoulderWidth()+0.9);
			m.setArmDiameter(m.getArmDiameter()+1.8);
			m.setArmholeDepth(m.getArmholeDepth()+1.5);
			this.waistAllowance = 6;
			this.hipAllowance = 6;
		}
		//tight cloth
		else if(fitclass == 5) {
			//medium loose dresses, tight jacket
			m.setBustWidth(m.getBustWidth()+1.5);
			m.setAcrossBackWidth(m.getAcrossBackWidth()+1.2);
			m.setShoulderWidth(m.getShoulderWidth()+1.2);
			m.setArmDiameter(m.getArmDiameter()+2.4);
			m.setArmholeDepth(m.getArmholeDepth()+1.7);
			this.waistAllowance = 10;
			this.hipAllowance = 7;
		}
		else if(fitclass == 6) {
			//loose dresses, medium loose jacket, tight coat
			m.setBustWidth(m.getBustWidth()+1.8);
			m.setAcrossBackWidth(m.getAcrossBackWidth()+1.4);
			m.setShoulderWidth(m.getShoulderWidth()+1.4);
			m.setArmDiameter(m.getArmDiameter()+2.9);
			m.setArmholeDepth(m.getArmholeDepth()+2.5);
			this.waistAllowance = 12;
			this.hipAllowance = 8;
		}
		else if(fitclass == 7) {
			//loose dresses, medium loose jacket, tight coat
			m.setBustWidth(m.getBustWidth()+2);
			m.setAcrossBackWidth(m.getAcrossBackWidth()+1.7);
			m.setShoulderWidth(m.getShoulderWidth()+1.7);
			m.setArmDiameter(m.getArmDiameter()+3.4);
			m.setArmholeDepth(m.getArmholeDepth()+3);
			this.waistAllowance = 14;
			this.hipAllowance = 10;
		}
		else if(fitclass == 8) {
			//medium tight coat
			m.setBustWidth(m.getBustWidth()+2);
			m.setAcrossBackWidth(m.getAcrossBackWidth()+2);
			m.setShoulderWidth(m.getShoulderWidth()+2);
			m.setArmDiameter(m.getArmDiameter()+4);
			m.setArmholeDepth(m.getArmholeDepth()+3.5);
			this.waistAllowance = 16;
			this.hipAllowance = 15;
		}
		else{
			//fitclass 3 is standard
			this.fitclass =3;
			//tight dress
			m.setBustWidth(m.getBustWidth()+(1.2*cm));
			m.setAcrossBackWidth(m.getAcrossBackWidth()+0.6*cm);
			m.setShoulderWidth(m.getShoulderWidth()+0.6*cm);
			m.setArmDiameter(m.getArmDiameter()+1.3*cm);
			m.setArmholeDepth(m.getArmholeDepth()+1.3*cm);
			this.waistAllowance = 5*cm;
			this.hipAllowance = 5*cm;
		}
		
	}
}
