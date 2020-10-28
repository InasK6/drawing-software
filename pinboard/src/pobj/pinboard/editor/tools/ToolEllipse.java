package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse extends AbstractTool implements Tool{



	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				setCurrentX(e.getX());
				setCurrentY(e.getY());
				double minX=Math.min(BaseX(), CurrentX());
				double maxX=Math.max(BaseX(), CurrentX());
				double maxY=Math.max(BaseY(), CurrentY());
				double minY=Math.min(BaseY(), CurrentY());
				
				Command c=new CommandAdd(i,new ClipEllipse(minX, minY, maxX, maxY, getCurrentColor()));
				i.getUndoStack().addCommand(c);
				c.execute();
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
i.getBoard().draw(gc);
		
		gc.setStroke(getCurrentColor());
		double minX=Math.min(BaseX(), CurrentX());
		double maxX=Math.max(BaseX(), CurrentX());
		double maxY=Math.max(BaseY(), CurrentY());
		double minY=Math.min(BaseY(), CurrentY());
		gc.strokeOval(minX, minY, maxX-minX, maxY-minY);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Filled Ellipse Tool";
	}
	


}
