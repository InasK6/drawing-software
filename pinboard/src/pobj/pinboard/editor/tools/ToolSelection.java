package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool {
	
	/**
	 * Coordonnées de l'appui 
	 */
	private double x;
	private double y;
	
	public ToolSelection() {
		
	}
	
	/**
	 * if shift is pressed, then we can multiple select
	 */
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		y=e.getY();
		if(e.isShiftDown()) {
			
			i.getSelection().toogleSelect(i.getBoard(), x, y);
		}
		else {
			i.getSelection().select(i.getBoard(), x, y);
		}
	}
	/**
	 * move all the selected clips at the same time
	 */
	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		 double cx=e.getX();
		 double cy=e.getY();
		
		for( Clip c: i.getSelection().getContents()) {
			
			Command cmd=new CommandMove(i, c, cx-x,cy-y);
			i.getUndoStack().addCommand(cmd);
			cmd.execute();
			
		}
		setXY(cx, cy);
		
	}
	/**
	 * in this case, doesn't do anything, the clips have been drawn before on drag
	 */
	@Override
	public void release(EditorInterface i, MouseEvent e) {
	}
	/**
	 * drawsClips present on board, and blue rectangles around selected clips
	 */
	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		i.getBoard().draw(gc);
		i.getSelection().drawFeedBack(gc);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Selection Tool";
	}

	@Override
	public void setCurrentColor(Color c) {
		// TODO Auto-generated method stub
		
	}
	public void setXY(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	

}
