package model.shapes.circle;


import model.DrawingModel;
import model.shapes.Command;

public class AddCircle implements Command{

	private DrawingModel model;
	private Circle circle;
	public Long groupNum=0L;
	public AddCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.add(circle);
	}

	@Override
	public void unexecute() {
		model.remove(circle);
	}

	@Override
	public Long getGroupNum(){
		return groupNum;
	}

}
