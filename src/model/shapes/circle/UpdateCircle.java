package model.shapes.circle;


import model.shapes.Command;

public class UpdateCircle implements Command {

	private static final long serialVersionUID = 8264407501688477834L;
	private Circle oldState;
	private Circle newState;
	private Circle originalState;
	public UpdateCircle(Circle originalState, Circle newState) {
		this.originalState = originalState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState=originalState.clone();
		originalState.setCenter(newState.getCenter().clone());
		originalState.setRadius(newState.getRadius());
		originalState.setColor(newState.getColor());
		originalState.setInnerColor(newState.getInnerColor());
		originalState.setSelected(newState.isSelected());
		originalState.setFill(newState.getFill());
	}

	@Override
	public void unexecute() {
		originalState.setCenter(oldState.getCenter().clone());
		originalState.setRadius(oldState.getRadius());
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
