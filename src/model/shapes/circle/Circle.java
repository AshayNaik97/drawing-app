package model.shapes.circle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.shapes.Movable;
import model.shapes.Shape;
import model.shapes.SurfaceShape;
import model.shapes.line.Line;
import model.shapes.point.Point;

import java.awt.BasicStroke;
public class Circle extends SurfaceShape implements Movable  {

	/**
	 * 
	 */

	private Point center;
	private int radius;
	private boolean fill=false;

	public Circle() {
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color color) {
		this(center, radius);
		setColor(color);
	}

	public Circle(Point center, int radius, boolean fill, Color outerColor, Color innerColor) {
		this(center, radius, outerColor);
		setInnerColor(innerColor);
		setFill(fill);
	}

	public Circle(Point center, int radius, boolean fill, Color outerColor, Color innerColor, Long groupNumber) {
		this(center, radius, outerColor);
		this.groupNum = groupNumber;
		setInnerColor(innerColor);

		setFill(fill);
	}

	public Circle(Point center, int radius, boolean fill, int strokeSize, Color outerColor, Color innerColor) {
		this(center,radius,fill,outerColor,innerColor);
		this.strokeSize=strokeSize;
	}

	public double surfaceArea() {
		return Math.pow(radius, 2) * Math.PI;
	}

	public double volume() {
		return 2 * radius * Math.PI;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle castedObj = (Circle) obj;
			return center.equals(castedObj.getCenter()) && radius == castedObj.getRadius();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Circle(X=%d,Y=%d,r=%d,f=%b,ss=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)", center.getX(),
				center.getY(), radius, fill, strokeSize, getColor().getRed(), getColor().getGreen(), getColor().getBlue(),
				getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());
	}

	@Override
	public int compareTo(Shape o) {
		if (o instanceof Circle) {
			Circle castedObj = (Circle) o;
			return radius - castedObj.getRadius();
		}
		return 0;
	}

	@Override
	public void moveTo(int x, int y) {
		center.setX(x);
		center.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		center.setX(center.getX() + x);
		center.setY(center.getY() + y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(center.getX() - radius + 1, center.getY() - radius + 1, 2 * radius - 2, radius * 2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(getColor());
		g2.setStroke(new BasicStroke(strokeSize));
		g.drawOval(center.getX() - radius, center.getY() - radius, 2 * radius, radius * 2);
		// System.out.println(super.fill);
		if(fill==true){
			fill(g);
		}
		if (isSelected())
			selected(g);
	}

	@Override
	public void selected(Graphics g) {
		new Line(new Point(center.getX(), center.getY() - radius), new Point(center.getX(), center.getY() + radius)).selected(g);
		new Line(new Point(center.getX() - radius, center.getY()), new Point(center.getX() + radius, center.getY())).selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		Point testPt = new Point(x, y);
		return testPt.distance(center) <= radius;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean getFill(){
		return this.fill;
	}

	public void setFill(boolean fill){
		this.fill = fill;
	}
	
	public Circle clone() {
		Circle c=new Circle(center.clone(),
				this.radius,this.fill, this.getColor(), this.getInnerColor());
		c.setSelected(isSelected());
		return c;
	}  

}
