package pobj.pinboard.editor.commands;



import java.util.LinkedList;
import java.util.List;

import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.tools.ToolSelection;

public class CommandMove implements Command{

	private EditorInterface i;
	//private MouseEvent e;
	//private ToolSelection tool;
	/**
	 * previous values of x, y of tool before applying execute action
	 */
	//private double x;
	//private double y;
	//private double cx;
	//private double cy;
	
	//private List<Clip> list=new LinkedList<>();
	
	
	private Clip clip;
	/**
	 * Déplacement selon x
	 */
	private double Dx;
	/**
	 * Déplacement selon y
	 */
	private double Dy;
	
	/*public CommandMove(EditorInterface i, MouseEvent e, ToolSelection tool) {
		super();
		this.i = i;
		this.e = e;
		this.tool = tool;
		this.x = tool.getX();
		this.y = tool.getY();
	}*/
	public CommandMove(EditorInterface i, Clip c, double Dx, double Dy ) {
		this.i = i;
		clip=c;
		this.Dx=Dx;
		this.Dy=Dy;
	}

	@Override
	public void execute() {
		//Double x=clip.getLeft();
		//Double y=clip.getTop();
		clip.move(Dx, Dy);
		
		

		//tool.setXY(cx, cy);

	}

	@Override
	public void undo() {
		clip.move(-Dx, -Dy);
		
		
		//tool.setXY(x, y);
	}

}
