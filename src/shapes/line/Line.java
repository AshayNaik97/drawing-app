package shapes.line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import shapes.Shape;
import shapes.point.Point;

public class Line extends Shape{

	/**
	 * 
	 */
	private Point pointStart;
	private Point pointEnd;
	private int strokeSize=0;

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

	public double length() {
		return pointStart.distance(pointEnd);
	}

	@Override
	public String toString() {
		return String.format("Line(startX=%d,startY=%d,endX=%d,endY=%d,color=[%d-%d-%d],selected=%b)", pointStart.getX(),
				pointStart.getY(), pointEnd.getX(), pointEnd.getY(), getColor().getRed(), getColor().getGreen(),
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
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		g2.setStroke(new BasicStroke(strokeSize));
		g2.drawLine(pointStart.getX(), pointStart.getY(), pointEnd.getX(), pointEnd.getY());
		if (isSelected())
			selected(g2);

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

	public int getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(int strokeSize) {
		this.strokeSize = strokeSize;
	}

	public Line clone() {
		Line l=new Line(pointStart.clone(),pointEnd.clone(),this.groupNum,this.getColor());
		l.setSelected(isSelected());
		return l;
	}

}
