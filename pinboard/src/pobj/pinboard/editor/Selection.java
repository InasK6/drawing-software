package pobj.pinboard.editor;

import pobj.pinboard.document.Clip;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import pobj.pinboard.document.Board;
public class Selection {
	/**
	 * list of selected graphic elements
	 */
	private List<Clip> selected=new LinkedList<Clip>();
	
	/**
	 * simple selection
	 * @param board
	 * @param x
	 * @param y
	 */
	public Selection() {
		
	}
	public void select(Board board, double x, double y) {
		selected.clear();
		for(Clip c: board.getContents()) {
			if(c.isSelected(x, y)) {
				selected.add(c);
				return;
			}
		}
	}
	/**
	 * multiple selection
	 * @param board
	 * @param x
	 * @param y
	 */
	public void toogleSelect(Board board, double x, double y) {
		for(Clip c: board.getContents()) {
			if(c.isSelected(x, y)) {
				if(selected.contains(c)) {
					selected.remove(c);
				}
				else {
					selected.add(c);
				}
				return ;
			}
		}
	}
	
	public void clear() {
		selected.clear();
	}
	public List<Clip> getContents(){
		return selected;
	}
	public void drawFeedBack(GraphicsContext ctx) {
		ctx.setStroke(Color.BLUE);
		for(Clip c: selected) {
			double bottom=c.getBottom();
			double top=c.getTop();
			double left=c.getLeft();
			double right=c.getRight();
			ctx.strokeRect(left, top, right-left , bottom-top);
		}
	}
}
