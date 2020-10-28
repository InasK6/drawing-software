package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {

	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.setFill(getColor());
		ctx.setStroke(getColor());
		
		double left=getLeft();
		double right=getRight();
		double bottom=getBottom();
		double top=getTop();
		ctx.fillOval(left, top, right-left, bottom-top);
	}

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		double cx=(getLeft()+getRight())/2;
		double cy=(getTop()+getBottom())/2;
		double rx=(getLeft()-getRight())/2;
		double ry=(getTop()-getBottom())/2;
		return ((x-cx)/rx)*((x-cx)/rx)+((y-cy)/ry)*((y-cy)/ry)<=1;
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return new ClipEllipse(getLeft(),getTop(), getRight(), getBottom(), getColor());
	}
	
}
