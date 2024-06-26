package model.shapes.line;


import model.shapes.Command;

public class UpdateLine implements Command{
	private Line oldState;
	private Line newState;
	private Line originalState;
	public UpdateLine(Line originalState,Line newState) {
		this.originalState=originalState;
		this.newState=newState;
	}
	@Override
	public void execute() {
		oldState=originalState.clone();
		originalState.setPointStart(newState.getPointStart().clone());
		originalState.setPointEnd(newState.getPointEnd().clone());
		originalState.setColor(newState.getColor());
		originalState.setSelected(newState.isSelected());
	}

	@Override
	public void unexecute() {
		originalState.setPointStart(oldState.getPointStart().clone());
		originalState.setPointEnd(oldState.getPointEnd().clone());
		originalState.setColor(oldState.getColor());
		originalState.setSelected(oldState.isSelected());
	}

	@Override
	public Long getGroupNum(){
		return 0L;
	}

}
