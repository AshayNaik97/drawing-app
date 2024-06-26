package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.Serializable;


import model.DrawingModel;
import model.frame.DrawingFrame;
import model.shapes.Command;
import model.shapes.Shape;
import model.shapes.circle.AddCircle;
import model.shapes.circle.Circle;
import model.shapes.circle.RemoveCircle;
import model.shapes.circle.UpdateCircle;
import model.shapes.line.AddLine;
import model.shapes.line.Line;
import model.shapes.line.RemoveLine;
import model.shapes.line.UpdateLine;
import model.shapes.point.AddPoint;
import model.shapes.point.Point;
import model.shapes.point.UpdatePoint;
import model.shapes.rectangle.AddRectangle;
import model.shapes.rectangle.Rectangle;
import model.shapes.rectangle.RemoveRectangle;
import model.shapes.rectangle.UpdateRectangle;
import model.shapes.square.AddSquare;
import model.shapes.square.RemoveSquare;
import model.shapes.square.Square;
import model.shapes.square.UpdateSquare;

public class DrawingController implements Serializable {
	/**
	 * 
	 */
	private DrawingModel model;
	private DrawingFrame frame;
	private Point start;
	private Square startSquare;
	private Line drag;
	private boolean draw = false;
	private int count=0;

	private boolean isDraged = false;
	private Point prevStop;
	private Line prevLine = null;
	private Square prevSquare = null;
	private Circle prevCircle = null;
	private Rectangle prevRectangle =null;
	private Point updatedStart = null;
	private Point stop;

	private Long groupNumber=0L;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent arg0) {

		if (!frame.getToolsController().isEnterSelecting()) {
			if (frame.getToolsController().getSelection() == 1) {

				Point p = new Point(arg0.getX(), arg0.getY(), frame.getToolsController().getOuter());
				AddPoint cmdAddPoint = new AddPoint(model, p);
				cmdAddPoint.execute();
				frame.getToolsController().LogCommand(cmdAddPoint, true, p, null);
				frame.getToolsController().addUndo(cmdAddPoint,
						frame.getToolsController().transCmd(cmdAddPoint, true, p, null));
				frame.getView().repaint();
				frame.getToolsController().updateButtons();
			}
		} else {
			for (int i = model.getAll().size() - 1; i >= 0; i--) {
				Shape s = model.get(i);
				if (s.contains(arg0.getX(), arg0.getY())) {
					doCommandUpdateSelected(s, !s.isSelected());
					break;
				}
			}

			frame.getView().repaint();
			frame.getToolsController().updateButtons();
		}
	}

	public void doCommandUpdateSelected(Shape s, boolean state) {

		Command cmd = null;
		if (s instanceof Point) {
			Point p = (Point) s;
			Point c = p.clone();

			c.setSelected(state);
			cmd = new UpdatePoint(p, c);
			frame.getToolsController().LogCommand(cmd, true, p, c);
			frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, p, c));

		} else if (s instanceof Line) {
			Line p = (Line) s;
			Line c = p.clone();
			c.setSelected(state);
			cmd = new UpdateLine(p, c);
			frame.getToolsController().LogCommand(cmd, true, p, c);
			frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, p, c));

		} else if (s instanceof Rectangle) {
			Rectangle p = (Rectangle) s;
			Rectangle c = p.clone();
			c.setSelected(state);
			cmd = new UpdateRectangle(p, c);
			frame.getToolsController().LogCommand(cmd, true, p, c);
			frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, p, c));
		} else if (s instanceof Square) {
			Square p = (Square) s;
			Square c = p.clone();
			c.setSelected(state);
			cmd = new UpdateSquare(p, c);
			frame.getToolsController().LogCommand(cmd, true, p, c);
			frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, p, c));

		} else if (s instanceof Circle) {
			Circle p = (Circle) s;
			Circle c = p.clone();
			c.setSelected(state);
			cmd = new UpdateCircle(p, c);
			frame.getToolsController().LogCommand(cmd, true, p, c);
			frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, p, c));
		}


		cmd.execute();
	}

	public void moveLines(MouseEvent arg0) {

		stop = null;
		drag = null;
		// startSquare = null;
		Color inner = frame.getToolsController().getInner();
		Color outer = frame.getToolsController().getOuter();
		Point curEndpPoint = new Point(arg0.getX(), arg0.getY());
		if (start != null) {
			if (frame.getToolsController().getSelection() == 2) {
				Line l = new Line(start, new Point(arg0.getX(), arg0.getY(), outer), outer);
				l.setStrokeSize(frame.getBrush().getStrokeSize());
				if (l.length() > 3) {
					if (prevLine != null && prevStop != curEndpPoint) {
						RemoveLine cmd = new RemoveLine(model, prevLine);
						cmd.execute();
					}
					AddLine cmd = new AddLine(model, l);
					cmd.execute();
					prevLine = l;
					prevStop = curEndpPoint;
					frame.getView().repaint();
				}
			} 
			else if (frame.getToolsController().getSelection() == 3) {
				System.out.println(start.getX() + " " + start.getY() + " " + curEndpPoint.getX() + " " + curEndpPoint.getY());
				int distance = Math.min(Math.abs(start.getX() - curEndpPoint.getX()), Math.abs(start.getY() - curEndpPoint.getY()));
				if (start.getX() < curEndpPoint.getX() && start.getY() > curEndpPoint.getY()) {
					updatedStart = new Point(start.getX(), start.getY()-distance);
				}
				else if (start.getX() < curEndpPoint.getX() && start.getY() < curEndpPoint.getY()) {
					updatedStart = start;
				}
				else if(curEndpPoint.getX()<start.getX() && curEndpPoint.getY()<start.getY()){
					updatedStart = new Point(start.getX()-distance, start.getY()-distance);
				}
				 else {
					updatedStart = new Point(start.getX()-distance, start.getY());

				}
				System.out.println(updatedStart.getX() + " " +updatedStart.getY() + " "+ distance);
				Square s = new Square(updatedStart, distance, frame.getToolsController().fillCommand(),outer, inner);
				s.setStrokeSize(frame.getBrush().getStrokeSize());
				if (s.surfaceArea() > 3) {
					if (prevSquare != null && prevStop != curEndpPoint) {
						RemoveSquare cmd = new RemoveSquare(model, prevSquare);
						cmd.execute();
					}
					prevSquare = s;
					prevStop=curEndpPoint;
					AddSquare cmd1 = new AddSquare(model, s);
					cmd1.execute();
					frame.getView().repaint();
				}
			}

			else if (frame.getToolsController().getSelection() == 4) {
				if (start.getX() < curEndpPoint.getX() && start.getY() > curEndpPoint.getY()) {
					updatedStart = new Point(start.getX(), curEndpPoint.getY());
				}
				else if (start.getX() < curEndpPoint.getX() && start.getY() < curEndpPoint.getY()) {
					updatedStart = start;
				}
				else if(curEndpPoint.getX()<start.getX() && curEndpPoint.getY()<start.getY()){
					updatedStart = curEndpPoint;
				}
				 else {
					updatedStart = new Point(curEndpPoint.getX(), start.getY());

				}
				Rectangle r = new Rectangle(updatedStart, Math.abs(start.getY() - curEndpPoint.getY()),
						Math.abs(start.getX() - curEndpPoint.getX()), frame.getToolsController().fillCommand(),outer, inner);
				r.setStrokeSize(frame.getBrush().getStrokeSize());
				if (r.surfaceArea() > 3) {
					if(prevRectangle !=null && curEndpPoint!=prevStop){
						RemoveRectangle cmd = new RemoveRectangle(model , prevRectangle);
						cmd .execute();
					}
					prevRectangle=r;
					prevStop=curEndpPoint;
					AddRectangle cmd = new AddRectangle(model, r);
					cmd.execute();
					frame.getView().repaint();
				}
			}

			else if (frame.getToolsController().getSelection() == 5) {
				Circle c = new Circle(start, (int) start.distance(new Point(arg0.getX(), arg0.getY(), outer)),frame.getToolsController().fillCommand(), outer,
						inner);
				c.setStrokeSize(frame.getBrush().getStrokeSize());
				// c.addObserver(observer);
				if (c.getRadius() > 3) {
					if (prevCircle != null && curEndpPoint != prevStop) {
						RemoveCircle cmd = new RemoveCircle(model, prevCircle);
						cmd.execute();
					}
					AddCircle cmd = new AddCircle(model, c);
					cmd.execute();
					prevCircle = c;
					prevStop = curEndpPoint;
					frame.getView().repaint();
				}
			}


		}
	}

	public void mousePressed(MouseEvent arg0) {

		System.out.println("mouse pressed");
		isDraged = true;
		if (frame.getToolsController().getSelection() > 1 && !frame.getToolsController().isEnterSelecting()) {
			if (start == null) {
				start = new Point(arg0.getX(), arg0.getY(), frame.getToolsController().getOuter());
				startSquare = new Square(new Point(start.getX() - 3, start.getY() - 3), 6);

				groupNumber++;
				// model.add(startSquare);
				// model.add(start);
				draw = true;
				frame.getView().repaint();
			}

		}
		// if(frame.getToolsController().getSelection()>6){
		// if(start==null) {
		// start=new
		// Point(arg0.getX(),arg0.getY(),frame.getToolsController().getOuter());
		// startSquare=new Square(new Point(start.getX()-3,start.getY()-3),6);
		// model.add(startSquare);
		// model.add(start);
		// draw=true;
		// frame.getView().repaint();
		// }
		// }
	}

	public void mouseReleased(MouseEvent arg0) {

		System.out.println("mouse released ");
		isDraged = false;
		draw = false;

		model.remove(start);
		// model.remove(startSquare);
		model.remove(stop);
		model.remove(drag);
		stop = null;
		drag = null;
		startSquare = null;
		Color inner = frame.getToolsController().getInner();
		Color outer = frame.getToolsController().getOuter();
		if (start != null) {
			if (frame.getToolsController().getSelection() == 2) {
				
				Line l = new Line(start, new Point(arg0.getX(), arg0.getY(), outer), groupNumber, outer);

				l.setStrokeSize(frame.getBrush().getStrokeSize());
				// l.addObserver(observer);
			
				if (l.length() > 3) {
					RemoveLine cmd1 = new RemoveLine(model, prevLine);
					cmd1.groupNum = groupNumber;
					cmd1.execute(); 
					AddLine cmd = new AddLine(model, l);
					cmd.groupNum = groupNumber;
					cmd.execute();
					frame.getToolsController().LogCommand(cmd, true, l, null);
					frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, l, null));
				}
			}
			if (frame.getToolsController().getSelection() == 3) {
				Point end = new Point(arg0.getX(), arg0.getY(), Color.RED);

				int distance = Math.min(Math.abs(start.getX() - end.getX()), Math.abs(start.getY() - end.getY()));
				
				Square s = new Square(updatedStart, distance, frame.getToolsController().fillCommand(), outer, inner,groupNumber);
				s.setStrokeSize(frame.getBrush().getStrokeSize());
				// s.addObserver(observer);
				if (s.surfaceArea() > 3) {
					RemoveSquare cmd1 = new RemoveSquare(model, prevSquare);
					cmd1.groupNum = groupNumber;
					cmd1.execute();
					AddSquare cmd = new AddSquare(model, s);
					cmd.groupNum = groupNumber;
					cmd.execute();
					frame.getToolsController().LogCommand(cmd, true, s, null);
					frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, s, null));
				}
			}

			if (frame.getToolsController().getSelection() == 4) {
				Point end = new Point(arg0.getX(), arg0.getY(), Color.RED);

				// directionAssitant(start, end);
				
				Rectangle r = new Rectangle(updatedStart, Math.abs(start.getY() - end.getY()),
						Math.abs(start.getX() - end.getX()),frame.getToolsController().fillCommand(), outer, inner,groupNumber);
				r.setStrokeSize(frame.getBrush().getStrokeSize());
				// r.addObserver(observer);
				if (r.surfaceArea() > 3) {
					RemoveRectangle cmd1 = new RemoveRectangle(model, prevRectangle);
					cmd1.groupNum = groupNumber;
					cmd1.execute();
					AddRectangle cmd = new AddRectangle(model, r);
					cmd.groupNum = groupNumber;
					frame.getToolsController().LogCommand(cmd, true, r, null);
					cmd.execute();
					frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, r, null));
				}
			}

			if (frame.getToolsController().getSelection() == 5) {
				
				Circle c = new Circle(start, (int) start.distance(new Point(arg0.getX(), arg0.getY(), outer)),frame.getToolsController().fillCommand(), outer,
						inner,groupNumber);
				c.setStrokeSize(frame.getBrush().getStrokeSize());

				if (c.getRadius() > 3) {
					RemoveCircle cmd1 = new RemoveCircle(model, prevCircle);
					cmd1.groupNum = groupNumber;
					cmd1.execute();
					AddCircle cmd = new AddCircle(model, c);
					cmd.groupNum = groupNumber;
					frame.getToolsController().LogCommand(cmd, true, c, null);
					cmd.execute();
					frame.getToolsController().addUndo(cmd, frame.getToolsController().transCmd(cmd, true, c, null));
				}
			}

			prevCircle=null;
			prevLine=null;
			prevRectangle=null;
			prevSquare=null;

			start = null;
			frame.getView().repaint();
			frame.getToolsController().updateButtons();
			// frame.getMenuController().setSave();
		}
	}


	public void drawBrush(MouseEvent arg0) {
		// System.out.println("controller/DrawingController/DrawBrush");
		if (draw) {
			stop = new Point(arg0.getX(), arg0.getY());
			// ShapeObserver observer = new ShapeObserver(model, frame);
			// Color inner = frame.getToolsController().getInner();
			Color outer = frame.getToolsController().getOuter();
			if(frame.getToolsController().getSelection()==8) outer = Color.WHITE;

			if (start != null && (Math.abs(start.getX()-stop.getX())>=20 || Math.abs(start.getY()-stop.getY())>=20 || count > 6)) {
				count = 0;
				if (frame.getToolsController().getSelection() > 6) {
					
					Line l = new Line(start, new Point(arg0.getX(), arg0.getY(), outer), groupNumber, outer);
					l.setStrokeSize(frame.getBrush().getStrokeSize());
					// l.addObserver(observer);
					if (l.length() > 0) {
						RemoveLine cmd1 = new RemoveLine(model, prevLine);
						cmd1.groupNum = groupNumber;
						cmd1.execute(); 
						AddLine cmd = new AddLine(model, l);
						cmd.groupNum = groupNumber;
						cmd.execute();
						frame.getToolsController().LogCommand(cmd, true, l, null);
						frame.getToolsController().addUndo(cmd,
								frame.getToolsController().transCmd(cmd, true, l, null));
					}
				}
				start = stop;
				frame.getView().repaint();
				frame.getToolsController().updateButtons();
			}else count++;
		}
	}

}
