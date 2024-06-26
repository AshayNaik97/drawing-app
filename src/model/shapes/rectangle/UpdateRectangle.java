package model.shapes.rectangle;


import model.shapes.Command;

public class UpdateRectangle implements Command{

	private static final long serialVersionUID = -7464460081210689736L;
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState;
	public UpdateRectangle(Rectangle originalState, Rectangle newState) {
		this.originalState = originalState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState=originalState.clone();
		originalState.setUpperLeft(newState.getUpperLeft().clone());
		originalState.setWidth(newState.getWidth());
		originalState.setSideLength(newState.getSideLength());
		originalState.setColor(newState.getColor());
		originalState.setInnerColor(newState.getInnerColor());
		originalState.setSelected(newState.isSelected());
		originalState.setFill(newState.getFill());
	}

	@Override
	public void unexecute() {
		originalState.setUpperLeft(oldState.getUpperLeft().clone());
		originalState.setWidth(oldState.getWidth());
		originalState.setSideLength(oldState.getSideLength());
		originalState.setColor(oldState.getColor());
		originalState.setInnerColor(oldState.getInnerColor());
		originalState.setSelected(oldState.isSelected());
		originalState.setFill(oldState.getFill());
	}

	@Override
	public Long getGroupNum(){
		return 0L;
	}

}
