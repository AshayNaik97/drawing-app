package shapes.rectangle;


import model.DrawingModel;
import shapes.Command;

public class RemoveRectangle implements Command{

	private DrawingModel model;
	private Rectangle rectangle;
	public Long groupNum=0L;
	public RemoveRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.remove(rectangle);
	}

	@Override
	public void unexecute() {
		model.add(rectangle);
	}
	
	@Override
	public Long getGroupNum(){
		return groupNum;
	}	

}
