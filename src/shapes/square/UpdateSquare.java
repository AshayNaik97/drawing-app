package shapes.square;


import shapes.Command;

public class UpdateSquare implements Command{

	private Square oldState;
	private Square newState;
	private Square originalState;
	public UpdateSquare(Square originalState, Square newState) {
		this.originalState = originalState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		oldState=originalState.clone();
		originalState.setUpperLeft(newState.getUpperLeft().clone());
		originalState.setSideLength(newState.getSideLength());
		originalState.setColor(newState.getColor());
		originalState.setInnerColor(newState.getInnerColor());
		originalState.setSelected(newState.isSelected());
		originalState.setfill(newState.getfill());

	}

	@Override
	public void unexecute() {
		originalState.setUpperLeft(oldState.getUpperLeft().clone());
		originalState.setSideLength(oldState.getSideLength());
		originalState.setColor(oldState.getColor());
		originalState.setInnerColor(oldState.getInnerColor());
		originalState.setSelected(oldState.isSelected());
		originalState.setfill(oldState.getfill());
	}

	@Override
	public Long getGroupNum(){
		return 0L;
	}

}
