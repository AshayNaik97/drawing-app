package shapes.square;


import model.DrawingModel;
import shapes.Command;

public class RemoveSquare implements Command {

	private DrawingModel model;
	private Square square;
	public Long groupNum=0L;
	public RemoveSquare(DrawingModel model, Square square) {
		this.model = model;
		this.square = square;
	}
	@Override
	public void execute() {
		model.remove(square);
	}

	@Override
	public void unexecute() {
		model.add(square);
	}

	@Override
	public Long getGroupNum(){
		return groupNum;
	}

}
