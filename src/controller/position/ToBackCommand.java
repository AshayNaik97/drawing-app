package controller.position;

import java.util.Collections;

import model.DrawingModel;
import model.shapes.Command;
import model.shapes.Shape;

public class ToBackCommand implements Command{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5425108811485215268L;
	private DrawingModel model;
	private Shape shape;
	private int oldState;
	public ToBackCommand(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	@Override
	public void execute() {
		oldState=model.getAll().indexOf(shape);
		Collections.swap(model.getAll(), oldState-1, oldState);
	}

	@Override
	public void unexecute() {
		Collections.swap(model.getAll(), oldState-1, oldState);
	}

	@Override
	public Long getGroupNum(){
		return 0L;
	}

}
