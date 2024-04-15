package shapes.rectangle;


import model.DrawingModel;
import shapes.Command;

public class AddRectangle implements Command{

	private static final long serialVersionUID = 7933382380315617476L;
	private DrawingModel model;
	private Rectangle rectangle;
	public Long groupNum=0L;
	public AddRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.add(rectangle);
	}

	@Override
	public void unexecute() {
		model.remove(rectangle);
	}

	@Override
	public Long getGroupNum(){
		return groupNum;
	}

}
