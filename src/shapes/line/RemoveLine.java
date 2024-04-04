package shapes.line;


import model.DrawingModel;
import shapes.Command;

public class RemoveLine implements Command {
	private DrawingModel model;
	private Line line;
	public RemoveLine(DrawingModel model,Line line) {
		this.model=model;
		this.line=line;
	}
	@Override
	public void execute() {
		model.remove(line);
	}

	@Override
	public void unexecute() {
		model.add(line);
	}

}
