package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Observable;
import views.SelectionView;

public abstract class Shape extends Observable implements Comparable<Shape>, Serializable,Cloneable {

	/**
	 * 
	 */
	private Color color=Color.BLACK;
	private boolean selected=false;
	public Long groupNum=0L;
	public int strokeSize=0;
	// private SelectionView selView = new SelectionView();
	// protected boolean fill = selView.getBtnfill().isSelected();
	
	public Shape(){

	}
	
	public Shape(Color color) {
		this.color=color;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void selected(Graphics g);
	
	public abstract boolean contains(int x, int y);
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers();
	}

	public int getStrokeSize() {
		return strokeSize;
	}

	public void setStrokeSize(int strokeSize) {
		this.strokeSize = strokeSize;
	}
	
	
}
