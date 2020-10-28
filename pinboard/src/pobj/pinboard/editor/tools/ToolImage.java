package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolImage  implements Tool{

	private double currentX;
	private double currentY;
	private File filename;
	public ToolImage(File filename) {
		this.filename=filename;
	}
	
	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		currentX=e.getX();
		currentY=e.getY();
		
		Command c=new CommandAdd(i,new ClipImage(currentX, currentY,filename ));
		i.getUndoStack().addCommand(c);
		c.execute();
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		i.getBoard().draw(gc);
		gc.drawImage(new Image("file://"+filename.getAbsolutePath()), currentX,currentY);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Image tool";
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		currentX=e.getX();
		currentY=e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		currentX=e.getX();
		currentY=e.getY();
	}

	@Override
	public void setCurrentColor(Color c) {
		// TODO Auto-generated method stub
		
	}

}
