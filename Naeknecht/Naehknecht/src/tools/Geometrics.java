package tools;

import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

public class Geometrics {
	float cm = (72 / 2.54f) / 2 ; //50%

	public Point2D punktAufLine(Point2D startPunkt, Point2D endPunkt){
		double winkel =  Math.asin( startPunkt.distance(new Point2D.Double(startPunkt.getX(),endPunkt.getY()))
				/(endPunkt.distance(startPunkt)));
		Arc2D hilfswinkel = new Arc2D.Double(Arc2D.PIE);
		hilfswinkel .setArcByCenter(endPunkt.getX(),
				endPunkt.getY(),
				5 * cm,
				0,
				-winkel* (180 / Math.PI),
				Arc2D.PIE);
		return hilfswinkel.getEndPoint();

	}
}
