package de.umpanet.naehen.tools;

import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class FancyArc extends Arc2D.Double {

	public FancyArc() {
		// TODO Auto-generated constructor stub
	}

	public FancyArc(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public FancyArc(Rectangle2D ellipseBounds, double start, double extent,
			int type) {
		super(ellipseBounds, start, extent, type);
		// TODO Auto-generated constructor stub
	}

	public FancyArc(double x, double y, double w, double h, double start,
			double extent, int type) {
		super(x, y, w, h, start, extent, type);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Point2D> intersectionPt(Arc2D arc){
		ArrayList<Point2D> intersectionPoints = new ArrayList<Point2D> ();
		return intersectionPoints;
	}

}
