package model.shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape{
	/**
	 * 
	 */

	private Color innerColor = Color.WHITE;
	// protected boolean fill = super.fill;
	
	public abstract void fill(Graphics g);

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}
