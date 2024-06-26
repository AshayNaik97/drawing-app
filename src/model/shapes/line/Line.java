package model.shapes.line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.shapes.Shape;
import model.shapes.point.Point;

import java.awt.BasicStroke;

public class Line extends Shape{

	/**
	 * 
	 */
	private Point pointStart;
	private Point pointEnd;

	public Line() {

	}

	public Line(Point pointStart, Point pointEnd) {
		this.pointStart = pointStart;
		this.pointEnd = pointEnd;
	}
	
	public Line(Point pointStart, Point pointEnd, Long groupNumber) {
		this.pointStart = pointStart;
		this.pointEnd = pointEnd;
		this.groupNum = groupNumber;
	}

	public Line(Point pointStart, Point pointEnd, Color color) {
		this(pointStart,pointEnd);
		setColor(color);
	}
	
	public Line(Point pointStart, Point pointEnd, Long groupNumber, Color color) {
		this(pointStart,pointEnd,groupNumber);
		setColor(color);
	}

	public Line(Point pointStart, Point pointEnd, int strokeSize, Color color) {
		this(pointStart,pointEnd);
		setColor(color);
		this.strokeSize=strokeSize;
	}

	public double length() {
		return pointStart.distance(pointEnd);
	}

	@Override
	public String toString() {
		return String.format("Line(startX=%d,startY=%d,endX=%d,endY=%d,ss=%d,color=[%d-%d-%d],selected=%b)", pointStart.getX(),
				pointStart.getY(), pointEnd.getX(), pointEnd.getY(), strokeSize, getColor().getRed(), getColor().getGreen(),
				getColor().getBlue(),isSelected());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line castedObj = (Line) obj;
			return pointStart.equals(castedObj.getPointStart()) && pointEnd.equals(castedObj.getPointEnd());
		}
		return false;
	}

	@Override
	public int compareTo(Shape shape) {
		if (shape instanceof Line) {
			Line castedShape = (Line) shape;
			return (int) this.length() - (int) castedShape.length();
		}
		return 0;
	}

	public void moveFor(int x, int y) {
		pointStart.setX(pointStart.getX() + x);
		pointStart.setY(pointStart.getY() + y);
		pointEnd.setX(pointEnd.getX() + x);
		pointEnd.setY(pointEnd.getY() + y);
	}

	@Override
	public void draw(Graphics g) {
		// paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		g2.setStroke(new BasicStroke(strokeSize));
		g2.drawLine(pointStart.getX(), pointStart.getY(), pointEnd.getX(), pointEnd.getY());
		if (isSelected())
			selected(g2);

	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getColor());
		g2d.setStroke(new BasicStroke(strokeSize));

		double dx = pointEnd.getX() - pointStart.getX();
		double dy = pointEnd.getY() - pointStart.getY();
		double angle = Math.atan2(dy,dx);

		int arcSize = 8;

		int x1 = pointStart.getX();
		int y1 = pointStart.getY();
		int x2 = pointEnd.getX();
		int y2 = pointEnd.getY();
		g2d.fillArc(x1 - arcSize/2, y1 - arcSize/2, arcSize, arcSize, (int) Math.toDegrees(angle) - 90, 180);
		g2d.fillArc(x2 - arcSize/2, y2 - arcSize/2, arcSize, arcSize, (int) Math.toDegrees(angle) + 90, 180);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		pointStart.selected(g);
		pointEnd.selected(g);
		lineCenter().selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		Point testPt = new Point(x, y);
		return (testPt.distance(pointStart) + testPt.distance(pointEnd) - length()) <= 0.5;
	}
	
	public Point lineCenter() {
		int centerX = (pointStart.getX() + pointEnd.getX()) / 2;
		int centerY = (pointStart.getY() + pointEnd.getY()) / 2;
		return new Point(centerX, centerY);
	}

	
	public Point getPointStart() {
		return pointStart;
	}

	public void setPointStart(Point pointStart) {
		this.pointStart = pointStart;
	}

	public Point getPointEnd() {
		return pointEnd;
	}

	public void setPointEnd(Point pointEnd) {
		this.pointEnd = pointEnd;
	}

	public Line clone() {
		Line l=new Line(pointStart.clone(),pointEnd.clone(),this.groupNum,this.getColor());
		l.setSelected(isSelected());
		return l;
	}

}
