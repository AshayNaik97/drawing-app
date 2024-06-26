package model.logging;


import java.awt.Color;

import controller.position.*;
import model.DrawingModel;
import model.frame.DrawingFrame;
import model.shapes.Command;
import model.shapes.Shape;
import model.shapes.circle.*;
import model.shapes.line.*;
import model.shapes.point.*;
import model.shapes.rectangle.*;
import model.shapes.square.*;

public class CommandTransfer {
	private DrawingModel model;
	private DrawingFrame frame;
	public CommandTransfer(DrawingModel model,DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	
	public Command toCommand(String line) {
		String className,doneIt,shape,shape2;
		try {
		String[] lineSplit=line.split("_");
   	 	className=lineSplit[0];
   	 	doneIt=lineSplit[1];
   	 	shape=lineSplit[2];
   	 	shape2="no";
   	 	if(lineSplit.length>3)
   	 	{
   	 		shape2=lineSplit[4];
   	 	}
		}catch (Exception e) {
			return null;
		}
   	 	
   	 	
   	 	
   		Command cmd=null;
   		if(!shape2.equals("no")) {
   			shape2=shape2.split("\\(")[1];
   			shape2 = shape2.substring(0, shape2.length() - 1);
   		}

   		Shape sp=null;
   		// ShapeObserver observer=new ShapeObserver(model, frame);
   	 	if(shape.split("\\(")[0].equals("Point")) {
   	 	shape = shape.substring(0, shape.length() - 1);
   	 		Point p=toPoint(shape);
   	 		sp=p;
   	 		int i=model.getAll().indexOf(p);
   			if(className.equals("AddPoint"))cmd=new AddPoint(model,p);
   			else if(className.equals("RemovePoint"))cmd=new RemovePoint(model,(Point)model.get(i));
   			else if(className.equals("UpdatePoint"))cmd=new UpdatePoint((Point)model.get(i),toPoint(shape2));
   	 		
   	 	}
   	 	
   	 	//Line
   	 	else if(shape.split("\\(")[0].equals("Line")) {
   	 	shape = shape.substring(0, shape.length() - 1);
   	 		Line l=toLine(shape);
   	 		sp=l;
   	 		int i=model.getAll().indexOf(l);
   			if(className.equals("AddLine"))cmd=new AddLine(model,l);
   			else if(className.equals("UpdateLine"))cmd=new UpdateLine((Line)model.get(i),toLine(shape2));
   			else if(className.equals("RemoveLine"))cmd=new RemoveLine(model,(Line)model.get(i));
   	 		}
   	 	
   	 	//Square
   	 	else if(shape.split("\\(")[0].equals("Square")) {
   	 	shape = shape.substring(0, shape.length() - 1);
   	 		Square s=toSquare(shape);
   	 		sp=s;
   	 		int i=model.getAll().indexOf(s);
   			if(className.equals("AddSquare"))cmd=new AddSquare(model,s);
   			else if(className.equals("RemoveSquare"))cmd=new RemoveSquare(model,(Square)model.get(i));
   			else if(className.equals("UpdateSquare"))cmd=new UpdateSquare((Square)model.get(i),toSquare(shape2));
   	 		}
   	 	
   	 	
   	 	else if(shape.split("\\(")[0].equals("Rectangle")) {
   	 	shape = shape.substring(0, shape.length() - 1);
   	 		Rectangle r=toRectangle(shape);
   	 		sp=r;
   	 		int i=model.getAll().indexOf(r);
   			if(className.equals("AddRectangle"))cmd=new AddRectangle(model,r);
   			else if(className.equals("UpdateRectangle"))cmd=new UpdateRectangle((Rectangle)model.get(i),toRectangle(shape2));
   			else if(className.equals("RemoveRectangle"))cmd=new RemoveRectangle(model,(Rectangle)model.get(i));
   	 		}
   	 	
   	 	else if(shape.split("\\(")[0].equals("Circle")) {
   	 	shape = shape.substring(0, shape.length() - 1);
   	 		Circle c=toCircle(shape);
   	 		sp=c;
   	 		int i=model.getAll().indexOf(c);
   			if(className.equals("AddCircle"))cmd=new AddCircle(model,c);
   			else if(className.equals("UpdateCircle"))cmd=new UpdateCircle((Circle)model.get(i),toCircle(shape2));
   			else if(className.equals("RemoveCircle"))cmd=new RemoveCircle(model,(Circle)model.get(i));
   			
   	 		}
   	 	
   	 	
   	 	if(cmd==null) {
   	 		if(className.equals("BringToBackCommand"))cmd=new BringToBackCommand(model,sp);
   	 		else if(className.equals("BringToFrontCommand"))cmd=new BringToFrontCommand(model,sp);
   	 		else if(className.equals("ToBackCommand"))cmd=new ToBackCommand(model,sp);
   	 		else if(className.equals("ToFrontCommand"))cmd=new ToFrontCommand(model,sp);
   	 	}
   	 	
   	 	if(cmd!=null)
   	 	{
   	 		// sp.addObserver(observer);
   	 		if(doneIt.equals("execute")) {
   	 			cmd.execute();
   	 			frame.getToolsController().addUndo(cmd,line);
   	 			frame.getLogView().getLogPane().setText(frame.getLogView().getLogPane().getText()+line+'\n');
   	 		}
   	 		else {
   	 			frame.getToolsController().doUndo();
   	 		}
   	 	frame.getToolsController().updateButtons();
   	 	}
   	 	
		return cmd;
	}
	
	private Color getColor(String tekst) {
		tekst=tekst.substring(1);
		tekst = tekst.substring(0, tekst.length() - 1);
		String[] flow=tekst.split("-");
		int red=Integer.parseInt(flow[0]);
		int green=Integer.parseInt(flow[1]);
		int blue=Integer.parseInt(flow[2]);
		Color col=new Color(red, green, blue);
		return col;
	}
	private Point toPoint(String tekst) {
		System.out.println(tekst);
		//x=%d,y=%d,color=[%d-%d-%d]x=%d,y=%d,color=[%d-%d-%d]
		String[] flow=tekst.split(",");
		int x=Integer.parseInt(flow[0].split("=")[1]);
		int y=Integer.parseInt(flow[1].split("=")[1]);
		Color color=getColor(flow[2].split("=")[1]);
		boolean sel=Boolean.parseBoolean(flow[3].split("=")[1]);
		Point p=new Point(x,y,color);
		int search=model.getAll().indexOf(p);
		if(search!=-1)p=(Point) model.get(search);
		p.setSelected(sel);
		return p;
	}
	
	private Line toLine(String tekst) {
		System.out.println(tekst);
		String[] flow=tekst.split(",");
		int startX=Integer.parseInt(flow[0].split("=")[1]);
		int startY=Integer.parseInt(flow[1].split("=")[1]);
		int endX=Integer.parseInt(flow[2].split("=")[1]);
		int endY=Integer.parseInt(flow[3].split("=")[1]);
		int ss = Integer.parseInt(flow[4].split("=")[1]);
		Color color=getColor(flow[5].split("=")[1]);
		boolean sel=Boolean.parseBoolean(flow[6].split("=")[1]);
		Line l=new Line(new Point(startX,startY),new Point(endX,endY),ss,color);
		l.setSelected(sel);
		return l;
	}
	
	private Square toSquare(String tekst) {
		System.out.println(tekst);
		String[] flow=tekst.split(",");
		//Square(UpperX=%d,UpperY=%d,a=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d])
		int upperX=Integer.parseInt(flow[0].split("=")[1]);
		int upperY=Integer.parseInt(flow[1].split("=")[1]);
		int width=Integer.parseInt(flow[2].split("=")[1]);
		boolean fill = Boolean.parseBoolean(flow[3].split("=")[1]);
		int ss = Integer.parseInt(flow[4].split("=")[1]);
		Color outerColor=getColor(flow[5].split("=")[1]);
		Color innerColor=getColor(flow[6].split("=")[1]);
		boolean sel=Boolean.parseBoolean(flow[7].split("=")[1]);
		Square s=new Square(new Point(upperX,upperY), width,fill, ss, outerColor, innerColor);
		s.setSelected(sel);
		return s;
	}
	
	private Rectangle toRectangle(String tekst) {
		System.out.println(tekst);
		String[] flow=tekst.split(",");
		int upperX=Integer.parseInt(flow[0].split("=")[1]);
		int upperY=Integer.parseInt(flow[1].split("=")[1]);
		int height=Integer.parseInt(flow[2].split("=")[1]);
		int width=Integer.parseInt(flow[3].split("=")[1]);
		boolean fill = Boolean.parseBoolean(flow[4].split("=")[1]);
		int ss = Integer.parseInt(flow[5].split("=")[1]);
		Color outerColor=getColor(flow[6].split("=")[1]);
		Color innerColor=getColor(flow[7].split("=")[1]);
		boolean sel=Boolean.parseBoolean(flow[8].split("=")[1]);
		Rectangle r=new Rectangle(new Point(upperX,upperY), height, width,fill, ss, outerColor, innerColor);
		r.setSelected(sel);
		return r;
	}
	
	private Circle toCircle(String tekst) {
		//Circle(X=%d,Y=%d,r=%d,f=%d,ss=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d])
		System.out.println(tekst);
		String[] flow=tekst.split(",");
		int crX=Integer.parseInt(flow[0].split("=")[1]);
		int crY=Integer.parseInt(flow[1].split("=")[1]);
		int r=Integer.parseInt(flow[2].split("=")[1]);
		boolean fill = Boolean.parseBoolean(flow[3].split("=")[1]);
		int ss = Integer.parseInt(flow[4].split("=")[1]);
		Color outerColor=getColor(flow[5].split("=")[1]);
		Color innerColor=getColor(flow[6].split("=")[1]);
		boolean sel=Boolean.parseBoolean(flow[7].split("=")[1]);
		Circle c=new Circle(new Point(crX,crY), r, fill, ss, outerColor, innerColor);
		c.setSelected(sel);
		return c;
	}
	

}