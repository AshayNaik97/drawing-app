package model.shapes.point;


import model.DrawingModel;
import model.shapes.Command;

public class RemovePoint implements Command {
	private static final long serialVersionUID = 4212765520295523052L;
	private DrawingModel model;
	private Point point;
	public Long groupNum=0L;
	

	public RemovePoint(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;
	}

	@Override
	public void execute() {
		model.remove(point);
	}

	@Override
	public void unexecute() {
		model.add(point);
	}

	@Override
	public Long getGroupNum(){
		return groupNum;
	}

}
