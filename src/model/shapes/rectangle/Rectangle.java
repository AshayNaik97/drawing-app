package model.shapes.rectangle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.shapes.line.Line;
import model.shapes.point.Point;
import model.shapes.square.Square;

import java.awt.BasicStroke;

public class Rectangle extends Square{

	/**
	 * 
	 */
	private int width;
	private boolean fill;
	public Rectangle(Point upperLeft, int height, int width) {
		super(upperLeft, height);
		this.width = width;
	}

	public Rectangle(Point upperLeft, int height, int width, Color outerColor) {
		this(upperLeft, height, width);
		setColor(outerColor);
	}

	public Rectangle(Point upperLeft, int height, int width, boolean fill, Color outerColor, Color innerColor) {
		this(upperLeft, height, width, outerColor);
		setFill(fill);
		setInnerColor(innerColor);
	}

	public Rectangle(Point upperLeft, int height, int width, boolean fill, Color outerColor, Color innerColor, Long groupNumber) {
		this(upperLeft, height, width, outerColor);
		this.groupNum = groupNumber;
		setFill(fill);
		setInnerColor(innerColor);
	}

	public Rectangle(Point upperLeft, int height, int width, boolean fill, int strokeSize, Color outerColor, Color innerColor) {
		this(upperLeft, height, width, outerColor);
		setFill(fill);
		setInnerColor(innerColor);
		this.strokeSize=strokeSize;
	}

	@Override
	public Line diagonal() {
		return new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY() + sideLength));
	}

	@Override
	public Point center() {
		return diagonal().lineCenter();
	}

	@Override
	public int surfaceArea() {
		return width * sideLength;
	}

	@Override
	public int volume() {
		return 2 * (width + sideLength);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle castedObj = (Rectangle) obj;
			return upperLeft.equals(castedObj.getUpperLeft()) && width == castedObj.getWidth()
					&& sideLength == castedObj.getSideLength();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format(
				"Rectangle(UpperX=%d,UpperY=%d,height=%d,width=%d,f=%b,ss=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)",
				upperLeft.getX(), upperLeft.getY(), sideLength, width,fill, strokeSize, getColor().getRed(), getColor().getGreen(),
				getColor().getBlue(), getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());
	}

	@Override
	public boolean contains(int x, int y) {
		return upperLeft.getX() <= x && x <= (upperLeft.getX() + width) && upperLeft.getY() <= y
				&& y <= (upperLeft.getY() + sideLength);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY())).selected(g);
		new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + sideLength)).selected(g);
		new Line(new Point(upperLeft.getX() + width, upperLeft.getY()), diagonal().getPointEnd()).selected(g);
		new Line(new Point(upperLeft.getX(), upperLeft.getY() + sideLength), diagonal().getPointEnd()).selected(g);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(upperLeft.getX() + 1, upperLeft.getY() + 1, width - 1, sideLength - 1);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		g2.setStroke(new BasicStroke(strokeSize));
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, sideLength);
		if(fill==true){
			fill(g);
		}
		if (isSelected())
			selected(g);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean getFill(){
		return this.fill;
	}

	public void setFill(boolean fill){
		this.fill = fill;
	}
	
	public Rectangle clone() {
		Rectangle r=new Rectangle(upperLeft.clone(), 
				this.getSideLength(), this.width,this.fill, this.getColor(), this.getInnerColor());
		r.setSelected(this.isSelected());
		return r;
	}

}
