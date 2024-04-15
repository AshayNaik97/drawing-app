package shapes.circle;


import model.DrawingModel;
import shapes.Command;

public class RemoveCircle implements Command {

	private static final long serialVersionUID = 2718465074191333778L;
	private DrawingModel model;
	private Circle circle;
	public Long groupNum=0L;
	public RemoveCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}
	@Override
	public void execute() {
		model.remove(circle);
	}

	@Override
	public void unexecute() {
		model.add(circle);
	}

	@Override
	public Long getGroupNum(){
		return groupNum;
	}

}
