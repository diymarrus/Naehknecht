/**
 * 
 */
package de.umpanet.naehen.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.lang.Double;

/**
 * @author marrus
 *
 */
public class FancyLine extends Line2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	double steigung;
	double sx;
	double gx;
	double sy;
	double gy;
	private Graphics2D g;
	private double epsilon = 0.1;
	
	public FancyLine() {
		super();
		setSG();
	}
	public FancyLine(double x1, double y1, double x2, double y2) {
		
		super(round(x1), round(y1), round(x2), round(y2));
		setSG();
	}
	public FancyLine( Point2D p1, Point2D p2) {
		
		super(new Point2D.Double(round(p1.getX()), round(p1.getY())),
				new Point2D.Double(round(p2.getX()), round(p2.getY())));
		setSG();
	}
	public FancyLine( Point2D p1, Point2D p2, double length) {
		super();
		steigung = (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
		double x =p1.getX()- Math.cos(Math.atan(steigung)) *length;
		
	
		double y = steigung *(x-p1.getX()) + p1.getY();
		System.out.println("new Endpoint " + x + " , " + y + " für " + length );
		
		super.setLine(p1, new Point2D.Double(x,y));
		
		setSG();
		
	}
	private void setSG(){
			steigung = (this.y2-this.y1)/(this.x2-this.x1);

		if(this.x1 < this.x2){
			this.sx = this.x1;
			this.gx = this.x2;
		}else{
			this.sx = this.x2;
			this.gx = this.x1;
		}
		
		if(this.y1 < this.y2){
			this.sy = this.y1;
			this.gy = this.y2;
		}else{
			this.sy = this.y2;
			this.gy = this.y1;
		}
	}
	
	/**
	 * 
	 * @param x
	 * @return the point of the line for a given x value if it is on the line. Otherwise returns null;
	 */
	public Point2D getY(double x){
		
		if(x>= this.sx && x<= gx){
			return new Point2D.Double(x, (this.y2-this.y1)/(this.x2-this.x1)*(x-this.x1)+this.y1);		
		}else{
		//	System.out.println("x " + x + " sx " + sx + " gx "+ gx);
			//System.out.println("x " + x + " x1 " + x1 + " x2 "+ x2);
			return null;
		}
		
		
	}
	
	/**
	 * 
	 * @param y
	 * @return the point of the line for a given y value
	 */
	public Point2D getX(double y){
		if(y<=this.gy && y>=this.sy){
			return new Point2D.Double((this.x2-this.x1)* (y-this.y1)/(this.y2-this.y1)+this.x1,y);
		}else{
			return null;
		}
	}
	
	public double steigung(){
		return steigung;
	}
	public Point2D pointAtDistance(Point2D startingPoint, double distance, Graphics2D g){
	
		
		if(this.x1-this.x2 == 0){
			//parallel to y-axis
			y1 = startingPoint.getY()+Math.round(distance);
			y2 = startingPoint.getY()-Math.round(distance);
			if(y1 <= gy && y1 >=sy ){
				return new Point2D.Double(startingPoint.getX(), y1);
			}else if(y2 <= gy && y2 >=sy ){
		
				return new Point2D.Double(startingPoint.getX(), y2);
			}else{
				//TODO make nice exception
				System.out.println("Point not in rnage " );
				System.out.println("y1 " + y1 + " gy " + gy + " sy " + sy);
				System.out.println("y2 " + y2 + " gy " + gy + " sy " + sy);
				return null;
			}
		}
		if(!includes(startingPoint)){
			//TODO make nice exception
			System.out.println("Strating Point is not on Line");
			return null;
		}
		Arc2D hilfswinkel = new Arc2D.Double(Arc2D.PIE);
		hilfswinkel .setArcByCenter(startingPoint.getX(),
				startingPoint.getY(),
				distance,
				0,
				360,
				Arc2D.PIE);
		g.setColor(Color.pink);
		g.draw(hilfswinkel);
		ArrayList<Point2D> pts = this.intersectionPt(hilfswinkel);
		//System.out.println("pts "  + pts.size());
		if(pts == null){
			System.out.println("No Intersection point found");
			return null;
		}
		if(pts.size() == 1){
			return pts.get(0);
		}
	//	double winkel =  Math.asin(this.getP1().distance(new Point2D.Double( this.getP1().getX(), this.getP2().getY()))
	//			/( this.getP2().distance(this.getP1())));
		double winkel = Math.atan(this.steigung);
		System.out.println( "Winkel" + winkel);
		//System.out.println(steigung);
		hilfswinkel = new Arc2D.Double(Arc2D.PIE);
		hilfswinkel .setArcByCenter(startingPoint.getX(),
				startingPoint.getY(),
				distance,
				0,
				-winkel* (180 / Math.PI),
				Arc2D.PIE);
		g.setColor(Color.BLUE);
		g.draw(hilfswinkel);
		g.setColor(Color.black);
		return hilfswinkel.getEndPoint();
		
	}
	public Point2D pointAtDistance(Point2D startingPoint, double distance){
		
		
		if(this.x1-this.x2 == 0){
			//parallel to y-axis
			y1 = startingPoint.getY()+Math.round(distance);
			y2 = startingPoint.getY()-Math.round(distance);
			if(y1 <= gy && y1 >=sy ){
				return new Point2D.Double(startingPoint.getX(), y1);
			}else if(y2 <= gy && y2 >=sy ){
		
				return new Point2D.Double(startingPoint.getX(), y2);
			}else{
				//TODO make nice exception
				System.out.println("Point not in range " );
				System.out.println("y1 " + y1 + " gy " + gy + " sy " + sy);
				System.out.println("y2 " + y2 + " gy " + gy + " sy " + sy);
				return null;
			}
		}
		if(!includes(startingPoint)){
			//TODO make nice exception
			System.out.println("Strating Point is not on Line");
			return null;
		}
		Arc2D hilfswinkel = new Arc2D.Double(Arc2D.PIE);
		hilfswinkel .setArcByCenter(startingPoint.getX(),
				startingPoint.getY(),
				distance,
				0,
				360,
				Arc2D.PIE);
		
		ArrayList<Point2D> pts = this.intersectionPt(hilfswinkel);
		if(pts == null){
			System.out.println("No Intersection point found");
			return null;
		}
//		System.out.println("pts "  + pts.size());
		if(pts.size() == 1){
			return pts.get(0);
		}
	//	double winkel =  Math.asin(this.getP1().distance(new Point2D.Double( this.getP1().getX(), this.getP2().getY()))
	//			/( this.getP2().distance(this.getP1())));
		double winkel = Math.atan(this.steigung);
		//System.out.println(winkel);
		//System.out.println(steigung);
		hilfswinkel = new Arc2D.Double(Arc2D.PIE);
		hilfswinkel .setArcByCenter(startingPoint.getX(),
				startingPoint.getY(),
				distance,
				0,
				-winkel* (180 / Math.PI),
				Arc2D.PIE);
		
		return hilfswinkel.getEndPoint();

	}
	
	
	public Point2D intersectionPt(Line2D line){
		if(this.intersectsLine(line)){
			//gleichsetzen der Geraden gleichungen
			 final double x = ((this.x2 - this.x1)*(line.getX1()*line.getY2() - line.getX2()*line.getY1()) - (line.getX2() - line.getX1())*(this.x1*this.y2 - this.x2*this.y1)
		                ) /((this.x1 - this.x2)*(line.getY1() - line.getY2()) - (this.y1 - this.y2)*(line.getX1() - line.getX2())		                );
		    final double y = ((line.getY1() - line.getY2())*(this.x1*this.y2 - this.x2*this.y1) - (this.y1 - this.y2)*(line.getX1()*line.getY2() - line.getX2()*line.getY1())
		                ) /((this.x1 - this.x2)*(line.getY1()- line.getY2()) - (this.y1 - this.y2)*(line.getX1() - line.getX2()));

		        return new Point2D.Double(x, y);
		}
		return null;
		
		
	}
	public ArrayList<Point2D> intersectionPt(Arc2D arc){
		if((this.getX1() < arc.getMinX() && this.getX2() < arc.getMinX())
				||(this.getY1() < arc.getMinY() && this.getY2() < arc.getMinY()) 
				||(this.getX1() > arc.getMaxX() && this.getX2() < arc.getMaxX())
				||(this.getY1() > arc.getMaxY() && this.getY2() > arc.getMaxY())){
			//line and arc do not intersect
			System.out.println("Line an arc do not intersect");
			return null;
		}
		ArrayList<Point2D> intersectionPoints = new ArrayList<Point2D> ();
		double cx = arc.getCenterX();
		double cy = arc.getCenterY();
		double r = arc.getEndPoint().distance(cx, cy);

		double c=this.x2*this.y1-this.x1*this.y2;
		double a=this.y1-this.y2;
		double b = this.x2-this.x1;
		double d= c-a*cx-b*cy;
		//System.out.println("cx " +cx + " cy " + cy + " a*cx " + a*cx + " b*cy " + b*cy);
		//System.out.println("a " + a + " b " + b + " c " + c + " d " + d);
		
		//Find intersection points for line an full circle

		double x1 = ((cx+((a*d + b* Math.sqrt(r*r*(a*a+b*b)-d*d))/(a*a+b*b)))*100.0)/100.0; // zwei nachkommastellen
		double x2 = ((cx+((a*d - b* Math.sqrt(r*r*(a*a+b*b)-d*d))/(a*a+b*b)) )* 100.0)/100.0;
		
		double y1 =(( cy + ((b*d-a*Math.sqrt(r*r*(a*a+b*b)-d*d))/(a*a+b*b)))*100.0)/100.00;
		double y2 = ((cy + ((b*d+a*Math.sqrt(r*r*(a*a+b*b)-d*d))/(a*a+b*b)))*100.0)/100.00;
//		System.out.println("x1 " + x1 + " y1 " + y1 );
//		System.out.println("x2 " + x2 + " y2 " + y2);
		
		Rectangle2D rec = arc.getBounds2D();
		//System.out.println("rec minX " + rec.getMinX() + " maxX " + rec.getMaxX());
		//System.out.println("arc minY " + rec.getMinY() + " maxY " + rec.getMaxY());
		//System.out.println();
		
//		System.out.println((x1 >=  rec.getMinX()) + " " + (x1 <= rec.getMaxX() )
//				+ " " + (y1 >= rec.getMinY()) + " " + (y1 <= rec.getMaxY())
//				+ " " +includes(x1,y1) + " x " + x1 + " y " + y1);
		
		//TODO epislon umgebung
		if(x1 >=  rec.getMinX() && x1 <= rec.getMaxX() 
				&& y1 >= rec.getMinY() && y1 <= rec.getMaxY()
				&& includes(x1,y1)){
			
			//intersection Point is on arc
			intersectionPoints.add(new Point2D.Double(x1, y1));
		}
		
		if(x2 >=  rec.getMinX() && x2 <= rec.getMaxX() 
				&& y2 >= rec.getMinY() && y2 <= rec.getMaxY()
				&& includes(x2,y2)){
			//intersection Point is on arc
			intersectionPoints.add(new Point2D.Double(x2, y2));
		}
		
		return intersectionPoints;
		
		
	}
	/**
	 * 
	 * @param pt
	 * @return true if the point pt is on the line
	 */
	public boolean includes(Point2D pt){
		if(x1-x2 == 0){
			//parallel to y-axis
			double y = pt.getY();
			return pt.getX() == x1 && (y<=this.gy && y>=this.sy);
		}
		double h = getY(pt.getX()).getY()-pt.getY();
		return Math.abs(h) < epsilon ;
		
	}
	
	public boolean includes(double x,double y){
		if(x1-x2 == 0){
			//parallel to y-axis
			//System.out.println("y: " + y + " gy " + gy + " sy " + sy);
			return x == x1&& (y<=this.gy && y>=this.sy);
		}
		if(getY(x) != null){
		//System.out.println("y: " + y + " gety " + getY(x).getY());
		return Math.abs(getY(x).getY()-y) < epsilon;
		
		}
		//ystem.out.println("NULL");
		return false;
	}
	
	public void lengthenToY(double y){
		if(y1 < y2){
			this.x2 = (this.x2-this.x1)* (y-this.y1)/(this.y2-this.y1)+this.x1;
			this.y2 = y;
		}else{
			this.x1 =(this.x2-this.x1)* (y-this.y1)/(this.y2-this.y1)+this.x1;
			this.y1 = y;
		}
	}
	public double length(){
		return round(getP1().distance(getP2()));
	}
	public void setg(Graphics2D g){
		this.g = g;
	}
	
	private static double round(double a){
		return Math.round(10.0 *a)/10.0;
	}
}
