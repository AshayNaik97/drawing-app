package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import controller.position.BringToBackCommand;
import controller.position.BringToFrontCommand;
import controller.position.ToBackCommand;
import controller.position.ToFrontCommand;
import model.DrawingModel;
import model.frame.DrawingFrame;
import model.shapes.Command;
import model.shapes.Shape;
import model.shapes.circle.Circle;
import model.shapes.circle.RemoveCircle;
import model.shapes.circle.UpdateCircle;
import model.shapes.line.Line;
import model.shapes.line.RemoveLine;
import model.shapes.line.UpdateLine;
import model.shapes.point.Point;
import model.shapes.point.RemovePoint;
import model.shapes.point.UpdatePoint;
import model.shapes.rectangle.Rectangle;
import model.shapes.rectangle.RemoveRectangle;
import model.shapes.rectangle.UpdateRectangle;
import model.shapes.square.RemoveSquare;
import model.shapes.square.Square;
import model.shapes.square.UpdateSquare;
import view.dialogsView.DialogCircle;
import view.dialogsView.DialogLine;
import view.dialogsView.DialogPoint;
import view.dialogsView.DialogRectangle;
import view.dialogsView.DialogSquare;

public class ToolsController implements Serializable {
	private DrawingModel model;
	private DrawingFrame frame;
	private int selection = 1;
	private final Color btnColor = new Color(204, 0, 204);
	private Color inner = Color.WHITE, outer = Color.BLACK;
	private boolean enterSelecting = false;
	private Logger globalLogger = Logger.getLogger("global");

	public ToolsController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		frame.getSelViews().getBtnInner().setBackground(inner);
		frame.getSelViews().getBtnOutter().setBackground(outer);
		changeButtonCollor();
	}

	public void pointSelected(ActionEvent e) {
		selection = 1;
		changeButtonCollor();
	}

	public void lineSelected(ActionEvent e) {
		selection = 2;
		changeButtonCollor();
	}

	public void squareSelected(ActionEvent e) {
		selection = 3;
		changeButtonCollor();
	}

	public void rectangleSelected(ActionEvent e) {
		selection = 4;
		changeButtonCollor();
	}

	public void circleSelected(ActionEvent e) {

		selection=5;
		changeButtonCollor();
	}
	public void brushSelected(ActionEvent e) {
		selection=7;
		changeButtonCollor();
	}
	public void eraserSelected(ActionEvent e) {
		selection=8;
		changeButtonCollor();
	}

	public void changeButtonCollor() {
		// frame.getSViews().getBtnPoint().setBackground(null);
		frame.getSViews().getBtnLine().setBackground(null);
		frame.getSViews().getBtnLine().setForeground(null);
		frame.getSViews().getBtnSquare().setBackground(null);
		frame.getSViews().getBtnSquare().setForeground(null);
		frame.getSViews().getBtnRectangle().setBackground(null);
		frame.getSViews().getBtnRectangle().setForeground(null);
		frame.getSViews().getBtnCircle().setBackground(null);
		frame.getSViews().getBtnCircle().setForeground(null);
		frame.getSViews().getBtnBrush().setBackground(null);
		frame.getSViews().getBtnBrush().setForeground(null);
		frame.getSViews().getBtnEraser().setBackground(null);
		frame.getSViews().getBtnEraser().setForeground(null);
		// if(selection==1) {

		// 	frame.getSViews().getBtnPoint().setBackground(btnColor);
		// } 
	 	if (selection == 2) {
			frame.getSViews().getBtnLine().setBackground(btnColor);
			frame.getSViews().getBtnLine().setForeground(Color.WHITE);
		} else if (selection == 3) {
			frame.getSViews().getBtnSquare().setBackground(btnColor);
			frame.getSViews().getBtnSquare().setForeground(Color.WHITE);
		} else if (selection == 4) {
			frame.getSViews().getBtnRectangle().setBackground(btnColor);
			frame.getSViews().getBtnRectangle().setForeground(Color.WHITE);
		} else if (selection == 5) {
			frame.getSViews().getBtnCircle().setBackground(btnColor);
			frame.getSViews().getBtnCircle().setForeground(Color.WHITE);
		}else if(selection==7){
			frame.getSViews().getBtnBrush().setBackground(btnColor);
			frame.getSViews().getBtnBrush().setForeground(Color.WHITE);
		}else if(selection==8){
			frame.getSViews().getBtnEraser().setBackground(btnColor);
			frame.getSViews().getBtnEraser().setForeground(Color.WHITE);
		}
	}

	// updateButtons();
	public void updateButtons() {
		System.out.println("update buttons");
		if (frame.getMenuController().getUndoStack().size() == 0)
			frame.getSelViews().getBtnUndo().setEnabled(false);
		else
			frame.getSelViews().getBtnUndo().setEnabled(true);
		if (frame.getMenuController().getRedoStack().size() == 0)
			frame.getSelViews().getBtnRedo().setEnabled(false);
		else
			frame.getSelViews().getBtnRedo().setEnabled(true);
		int count=0;
		for(Shape s:model.getAll()) {
			if(s.isSelected())count++;
		}
		if(count==0) {
			if(frame.isActModify()) {
				frame.getSelViews().getBtnModify().setEnabled(false);
				frame.setActModify(false);
			}
			if(frame.isActDelete()) {
				frame.getSelViews().getBtnDelete().setEnabled(false);
				frame.setActDelete(false);
			}
			
			if(frame.isActPosition()) {
				frame.getPosView().getBtnBringtoback().setEnabled(false);
				frame.getPosView().getBtnToBack().setEnabled(false);
				frame.getPosView().getBtnToFront().setEnabled(false);
				frame.getPosView().getBtnBringToFront().setEnabled(false);
				frame.setActPosition(false);
			}
			
		}else if(count==1) {
			if(!frame.isActModify()) {
				frame.getSelViews().getBtnModify().setEnabled(true);
				frame.setActModify(true);
			}
			if(!frame.isActDelete()) {
				frame.getSelViews().getBtnDelete().setEnabled(true);
				frame.setActDelete(true);
			}
			if(!frame.isActPosition()) {
				frame.getPosView().getBtnBringtoback().setEnabled(true);
				frame.getPosView().getBtnToBack().setEnabled(true);
				frame.getPosView().getBtnToFront().setEnabled(true);
				frame.getPosView().getBtnBringToFront().setEnabled(true);
				frame.setActPosition(true);
			}
		}else if(count>1) {
			if(frame.isActModify()) {
				frame.getSelViews().getBtnModify().setEnabled(false);
				frame.setActModify(false);
			}
			if(!frame.isActDelete()) {
				frame.getSelViews().getBtnDelete().setEnabled(true);
				frame.setActDelete(true);
			}
			if(frame.isActPosition()) {
				frame.getPosView().getBtnBringtoback().setEnabled(false);
				frame.getPosView().getBtnToBack().setEnabled(false);
				frame.getPosView().getBtnToFront().setEnabled(false);
				frame.getPosView().getBtnBringToFront().setEnabled(false);
				frame.setActPosition(false);
			}
		}
	}

	public void undoCommand() {
		if (frame.getMenuController().getUndoStack().size() != 0) {
			do {
				Command old = frame.getMenuController().getUndoStack().peek();
				doUndo();
				if (!isRemoveCommand(old))
					break;
			} while (!frame.getMenuController().getUndoStack().isEmpty()
					&& isRemoveCommand(frame.getMenuController().getUndoStack().peek()));
			ActivateSel();
			frame.getView().repaint();
			updateButtons();
		} else
			JOptionPane.showMessageDialog(null, "List is empty", "Undo command:", JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean fillCommand(){
		System.out.println("fill");
		return frame.getSelViews().getBtnfill().isSelected();
	}

	private void ActivateSel() {
		for (Shape s : model.getAll()) {
			if (s.isSelected()) {
				if (!frame.getToolsController().isEnterSelecting())
					frame.getToolsController().selectShape(null);
				break;
			}
		}
	}

	private boolean isRemoveCommand(Command cmd) {
		if (cmd instanceof RemovePoint || cmd instanceof RemoveLine || cmd instanceof RemoveSquare
				|| cmd instanceof RemoveRectangle || cmd instanceof RemoveCircle)
			return true;
		else
			return false;
	}

	public void redoCommand() {
		if (frame.getMenuController().getRedoStack().size() != 0) {

			do {
				Command old = frame.getMenuController().getRedoStack().peek();
				doRedo();
				if (!isRemoveCommand(old))
					break;
			} while (!frame.getMenuController().getRedoStack().isEmpty()
					&& isRemoveCommand(frame.getMenuController().getRedoStack().peek()));

			ActivateSel();
			frame.getView().repaint();
			updateButtons();
		} else
			JOptionPane.showMessageDialog(null, "List is empty", "Redo command:", JOptionPane.INFORMATION_MESSAGE);
	}

	public int getSelection() {
		return selection;
	}

	public void setSelection(int selection) {
		this.selection = selection;
	}

	public void changeInnerColor() {
		Color temp = JColorChooser.showDialog(null, "choose collor", inner);
		if (temp != null) {
			inner = temp;
			frame.getSelViews().getBtnInner().setBackground(inner);
		}
	}

	public void changeOuterColor() {
		Color temp = JColorChooser.showDialog(null, "choose collor", outer);
		if (temp != null) {
			outer = temp;
			frame.getSelViews().getBtnOutter().setBackground(outer);
		}
	}

	public Color getInner() {
		return inner;
	}

	public void setInner(Color inner) {
		this.inner = inner;
	}

	public Color getOuter() {
		return outer;
	}

	public void setOuter(Color outer) {
		this.outer = outer;
	}

	public void selectShape(ActionEvent e) {
		if (enterSelecting) {
			enterSelecting = false;
			frame.getSelViews().getBtnSelect().setBackground(null);
			for (int i = model.getAll().size() - 1; i >= 0; i--)
				if (model.get(i).isSelected())
					frame.getController().doCommandUpdateSelected(model.get(i), false);
			frame.getView().repaint();
		} else {
			enterSelecting = true;
			frame.getSelViews().getBtnSelect().setBackground(btnColor);
		}
	}

	public boolean isEnterSelecting() {
		return enterSelecting;
	}

	public void setEnterSelecting(boolean enterSelecting) {
		this.enterSelecting = enterSelecting;
	}

	public void deleteShapes(ActionEvent e) {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to delete this shapes?", "Warning",
				JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION)
			for (int i = model.getAll().size() - 1; i >= 0; i--) {
				Shape s = model.get(i);
				Command cmd;
				if (s.isSelected()) {
					if (s instanceof Point) {
						cmd = new RemovePoint(model, (Point) s);
					} else if (s instanceof Line) {
						cmd = new RemoveLine(model, (Line) s);
					} else if (s instanceof Rectangle) {
						cmd = new RemoveRectangle(model, (Rectangle) s);
					} else if (s instanceof Square) {
						cmd = new RemoveSquare(model, (Square) s);
					} else if (s instanceof Circle) {
						cmd = new RemoveCircle(model, (Circle) s);
					}
					else
						continue;
					LogCommand(cmd, true, s, null);
					cmd.execute();
					addUndo(cmd, transCmd(cmd, true, s, null));

				}
			}
		updateButtons();
		frame.getView().repaint();
	}

	public void LogCommand(Command cmd, boolean state, Shape shape1, Shape shape2) {
		String text = transCmd(cmd, state, shape1, shape2);
		globalLogger.info(text);
	}

	public String transCmd(Command cmd, boolean state, Shape shape1, Shape shape2) {
		StringBuilder sb = new StringBuilder();
		String exec, creation;
		if (state)
			exec = "execute";
		else
			exec = "unexecute";
		if (shape2 != null)
			creation = shape1.toString() + "_to_" + shape2.toString();
		else
			creation = shape1.toString();
		sb.append(cmd.getClass().getSimpleName())
				.append("_")
				.append(exec)
				.append("_")
				.append(creation);
		return sb.toString();
	}

	public void modifyShape(ActionEvent e) {
		Shape selected = new Point();
		Command cmd = null;
		for (int i = model.getAll().size() - 1; i >= 0; i--) {
			if (model.get(i).isSelected()) {
				selected = model.get(i);
				break;
			}
		}
		if (selected instanceof Point) {
			DialogPoint view = new DialogPoint();
			view.setPoint((Point) selected);
			view.setVisible(true);
			if (view.isConfirm()) {
				cmd = new UpdatePoint((Point) selected, view.getPoint());
				LogCommand(cmd, true, selected, view.getPoint());
				addUndo(cmd, transCmd(cmd, true, selected, view.getPoint()));
			}
		} else if (selected instanceof Line) {
			DialogLine view = new DialogLine();
			view.setLine((Line) selected);
			view.setVisible(true);
			if (view.isConfirm()) {
				cmd = new UpdateLine((Line) selected, view.getLine());
				LogCommand(cmd, true, selected, view.getLine());
				addUndo(cmd, transCmd(cmd, true, selected, view.getLine()));
			}
		} else if (selected instanceof Rectangle) {
			DialogRectangle view = new DialogRectangle();
			view.setRectangle((Rectangle) selected);
			view.setVisible(true);
			if (view.isConfirm()) {
				cmd = new UpdateRectangle((Rectangle) selected, view.getRectangle());
				LogCommand(cmd, true, selected, view.getRectangle());
				addUndo(cmd, transCmd(cmd, true, selected, view.getRectangle()));
			}
		} else if (selected instanceof Square) {
			DialogSquare view = new DialogSquare();
			view.setSquare((Square) selected);
			view.setVisible(true);
			if (view.isConfirm()) {
				cmd = new UpdateSquare((Square) selected, view.getSquare());
				LogCommand(cmd, true, selected, view.getSquare());
				addUndo(cmd, transCmd(cmd, true, selected, view.getSquare()));
			}
		} else if (selected instanceof Circle) {
			DialogCircle view = new DialogCircle();
			view.setCircle((Circle) selected);
			view.setVisible(true);
			if (view.isConfirm()) {
				cmd = new UpdateCircle((Circle) selected, view.getCircle());
				LogCommand(cmd, true, selected, view.getCircle());
				addUndo(cmd, transCmd(cmd, true, selected, view.getCircle()));
			}
		}
		cmd.execute();
		updateButtons();
		frame.getView().repaint();
	}

	public void bringToFront(ActionEvent e) {
		Shape selected = new Point();
		for (int i = model.getAll().size() - 1; i >= 0; i--) {
			if (model.get(i).isSelected()) {
				selected = model.get(i);
				break;
			}
		}
		if (model.getAll().indexOf(selected) != model.getAll().size() - 1) {
			BringToFrontCommand cmd = new BringToFrontCommand(model, selected);
			LogCommand(cmd, true, selected, null);
			cmd.execute();
			addUndo(cmd, transCmd(cmd, true, selected, null));
			updateButtons();
			frame.getView().repaint();
		} else
			JOptionPane.showMessageDialog(null, "Shape is already on the front.");
	}

	public void toFront(ActionEvent e) {
		Shape selected = new Point();
		for (int i = model.getAll().size() - 1; i >= 0; i--) {
			if (model.get(i).isSelected()) {
				selected = model.get(i);
				break;
			}
		}
		if (model.getAll().indexOf(selected) != model.getAll().size() - 1) {
			ToFrontCommand cmd = new ToFrontCommand(model, selected);
			LogCommand(cmd, true, selected, null);
			cmd.execute();
			addUndo(cmd, transCmd(cmd, true, selected, null));
			updateButtons();
			frame.getView().repaint();
		} else
			JOptionPane.showMessageDialog(null, "Shape is already on the front.");
	}

	public void toBack(ActionEvent e) {
		Shape selected = new Point();
		for (int i = model.getAll().size() - 1; i >= 0; i--) {
			if (model.get(i).isSelected()) {
				selected = model.get(i);
				break;
			}
		}
		if (model.getAll().indexOf(selected) != 0) {
			ToBackCommand cmd = new ToBackCommand(model, selected);
			LogCommand(cmd, true, selected, null);
			cmd.execute();
			addUndo(cmd, transCmd(cmd, true, selected, null));
			updateButtons();
			frame.getView().repaint();
		} else
			JOptionPane.showMessageDialog(null, "Shape is already back.");
	}

	public void addUndo(Command cmd, String text) {
		frame.getMenuController().getUndoStack().push(cmd);
		frame.getMenuController().getRedoStack().clear();
		frame.getMenuController().getUndoStackLog().push(text);
		frame.getMenuController().getRedoStackLog().clear();
	}

	public void doUndo() {
		Long gn = frame.getMenuController().getUndoStack().peek().getGroupNum();
		frame.getMenuController().getUndoStack().peek().unexecute();
		frame.getMenuController().getRedoStack().push(frame.getMenuController().getUndoStack().pop());

		globalLogger.info(frame.getMenuController().getUndoStackLog().peek().replace("_execute_", "_unexecute_"));
		frame.getMenuController().getRedoStackLog().push(frame.getMenuController().getUndoStackLog().pop());

		while(!frame.getMenuController().getUndoStack().empty() && gn == frame.getMenuController().getUndoStack().peek().getGroupNum()){
			frame.getMenuController().getUndoStack().peek().unexecute();
			frame.getMenuController().getRedoStack().push(frame.getMenuController().getUndoStack().pop());
	
			globalLogger.info(frame.getMenuController().getUndoStackLog().peek().replace("_execute_", "_unexecute_"));
			frame.getMenuController().getRedoStackLog().push(frame.getMenuController().getUndoStackLog().pop());	
		}
	}

	public void doRedo() {
		Long gn = frame.getMenuController().getRedoStack().peek().getGroupNum();
		frame.getMenuController().getRedoStack().peek().execute();
		frame.getMenuController().getUndoStack().push(frame.getMenuController().getRedoStack().pop());

		globalLogger.info(frame.getMenuController().getRedoStackLog().peek().replace("_unexecute_", "_execute_"));
		frame.getMenuController().getUndoStackLog().push(frame.getMenuController().getRedoStackLog().pop());

		while(!frame.getMenuController().getRedoStack().empty() && gn == frame.getMenuController().getRedoStack().peek().getGroupNum()){
			frame.getMenuController().getRedoStack().peek().execute();
			frame.getMenuController().getUndoStack().push(frame.getMenuController().getRedoStack().pop());
	
			globalLogger.info(frame.getMenuController().getRedoStackLog().peek().replace("_unexecute_", "_execute_"));
			frame.getMenuController().getUndoStackLog().push(frame.getMenuController().getRedoStackLog().pop());	
		}
	}

	public void bringToBack(ActionEvent e) {
		Shape selected = new Point();
		for (int i = model.getAll().size() - 1; i >= 0; i--) {
			if (model.get(i).isSelected()) {
				selected = model.get(i);
				break;
			}
		}
		if (model.getAll().indexOf(selected) != 0) {
			BringToBackCommand cmd = new BringToBackCommand(model, selected);
			LogCommand(cmd, true, selected, null);
			cmd.execute();
			addUndo(cmd, transCmd(cmd, true, selected, null));
			updateButtons();
			frame.getView().repaint();
		} else
			JOptionPane.showMessageDialog(null, "Shape is already back.");
	}

}
