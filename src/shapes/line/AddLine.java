package shapes.line;


import model.DrawingModel;
import shapes.Command;

public class AddLine implements Command {

	private DrawingModel model;
	private Line line;
	public AddLine(DrawingModel model,Line line) {
		this.model=model;
		this.line=line;
	}
	@Override
	public void execute() {
		model.add(line);
	}

	@Override
	public void unexecute() {
		model.remove(line);
	}

}
