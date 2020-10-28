package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip{

	
	/**
	 * 
	 * @param left coordonnée gauche du rectangle
	 * @param right coordonnée droite du rectangle
	 * @param top   coordonnée haute du rectangle
	 * @param bottom  coordonnée basse du rectangle
	 * @param color   couleur du rectangle
	 */
	public ClipRect(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);

	}

	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		double left=getLeft();
		double right=getRight();
		double bottom=getBottom();
		double top=getTop();
		ctx.setFill(getColor());
		ctx.setStroke(Color.BLACK);
		ctx.strokeRect(left, top, right-left, bottom-top);
		ctx.fillRect(left, top, right-left, bottom-top);
	}

	@Override
	public Clip copy() {
		double left=getLeft();
		double right=getRight();
		double bottom=getBottom();
		double top=getTop();
		Color color=getColor();
		// TODO Auto-generated method stub
		return new ClipRect(left, top, right, bottom, color);
	}

}
