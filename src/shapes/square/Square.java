package shapes.square;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import shapes.Movable;
import shapes.Shape;
import shapes.SurfaceShape;
import shapes.line.Line;
import shapes.point.Point;

public class Square extends SurfaceShape implements Movable{
	/**
	 * 
	 */
	protected  Point upperLeft;
	protected  int sideLength;
	protected boolean fill;
	public Square() {
		
	}
	public Square(Point upperLeft,int sideLength) {
		this.upperLeft=upperLeft;
		this.sideLength=sideLength;
	}
	
	public Square(Point upperLeft, int sideLength, Color outer) {
		this(upperLeft, sideLength);
		setColor(outer);
	}
	
	public Square(Point upperLeft, int sideLength,boolean fill, Color outer, Color inner) {
		this(upperLeft, sideLength, outer);
		setfill(fill);
		setInnerColor(inner);
	}

	public Square(Point upperLeft, int sideLength,boolean fill, Color outer, Color inner, Long groupNumber) {
		this(upperLeft, sideLength, outer);
		this.groupNum = groupNumber;
		setfill(fill);
		setInnerColor(inner);
	}

	public Square(Point upperLeft, int sideLength,boolean fill, int strokeSize, Color outer, Color inner) {
		this(upperLeft, sideLength, outer);
		setfill(fill);
		setInnerColor(inner);
		this.strokeSize=strokeSize;
	}
	
	public Line diagonal() {
		return new Line(upperLeft, new Point(upperLeft.getX() + sideLength, upperLeft.getY() + sideLength));
	}
	
	public Point center() {
		return diagonal().lineCenter();
	}
	
	public int surfaceArea() {
		return sideLength * sideLength;
	}

	public int volume() {
		return 4 * sideLength;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square castedObj = (Square) obj;
			return upperLeft.equals(castedObj.getUpperLeft()) && sideLength == castedObj.getSideLength();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("Square(UpperX=%d,UpperY=%d,a=%d,f=%b,ss=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)",
				upperLeft.getX(), upperLeft.getY(), sideLength, fill, strokeSize, getColor().getRed(), getColor().getGreen(),
				getColor().getBlue(), getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());
	}

	@Override
	public int compareTo(Shape o) {
		if (o instanceof Square) {
			Square castedObj = (Square) o;
			return surfaceArea() - castedObj.surfaceArea();
		}
		return 0;
	}
	

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(upperLeft.getX() + 1, upperLeft.getY() + 1, sideLength - 1, sideLength - 1);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		g2.setStroke(new BasicStroke(strokeSize));
		g.setColor(getColor());
		g2.drawRect(upperLeft.getX(), upperLeft.getY(), sideLength, sideLength);
		if(fill){
			fill(g);
		}
		if (isSelected())
			selected(g);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(upperLeft, new Point(upperLeft.getX() + sideLength, upperLeft.getY())).selected(g);
		new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + sideLength)).selected(g);
		new Line(new Point(upperLeft.getX() + sideLength, upperLeft.getY()), diagonal().getPointEnd()).selected(g);
		new Line(new Point(upperLeft.getX(), upperLeft.getY() + sideLength), diagonal().getPointEnd()).selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		return upperLeft.getX() <= x && x <= (upperLeft.getX() + sideLength) && upperLeft.getY() <= y
				&& y <= (upperLeft.getY() + sideLength);
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeft.setX(x);
		upperLeft.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		upperLeft.setX(upperLeft.getX() + x);
		upperLeft.setY(upperLeft.getY() + y);
	}


	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getSideLength() {
		return sideLength;
	}
	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
	
	public boolean getfill() {
		return this.fill;
	}
	public void setfill(boolean fill) {
		this.fill = fill;
	}
	public Square clone() {
		Square s=new Square(upperLeft.clone(),
				sideLength,fill, this.getColor(), this.getInnerColor());
		s.setSelected(isSelected());
		return s;
	}
}
