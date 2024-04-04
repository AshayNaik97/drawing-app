package model;

import java.io.Serializable;
import java.util.ArrayList;
import shapes.Shape;

public class DrawingModel implements Serializable{
	/**
	 * 
	 */
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	private ArrayList<Long> group =new ArrayList<Long>();
	
	public ArrayList<Shape> getAll() {
		return shapes;
	}

	public Shape get(int i) {
		return shapes.get(i);
	}

	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void remove(Shape s) {
		shapes.remove(s);
	}

	public ArrayList<Long> getAllGroup() {
		return group;
	}

	public Long getGroup(int i) {
		return group.get(i);
	}

	public void addGroup(Long s) {
		group.add(s);
	}
	
	public void removeGroup(Long s) {
		group.remove(s);
	}

}
