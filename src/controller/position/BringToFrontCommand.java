package controller.position;


import java.util.Collections;

import model.DrawingModel;
import model.shapes.Command;
import model.shapes.Shape;

public class BringToFrontCommand implements Command {

	private static final long serialVersionUID = -5639011122262095118L;
	private DrawingModel model;
	private Shape shape;
	private int oldState;
	public BringToFrontCommand(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	@Override
	public void execute() {
		oldState=model.getAll().indexOf(shape);
		Collections.swap(model.getAll(), model.getAll().size()-1, oldState);
	}

	@Override
	public void unexecute() {
		Collections.swap(model.getAll(), model.getAll().size()-1, oldState);
	}

	@Override
	public Long getGroupNum(){
		return 0L;
	}
}
