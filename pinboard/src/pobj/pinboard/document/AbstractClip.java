package pobj.pinboard.document;

//import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip{
	private double left;
	private double right;
	private double top;
	private double bottom;
	private Color color;
	
	/**
	 * 
	 * @param left coordonnée gauche du rectangle
	 * @param right coordonnée droite du rectangle
	 * @param top   coordonnée haute du rectangle
	 * @param bottom  coordonnée basse du rectangle
	 * @param color   couleur du rectangle
	 */
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		super();
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.color = color;
	}

	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return bottom;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		left+=x;
		right+=x;
		top+=y;
		bottom+=y;
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return x>=left && x<=right && y>=top && y<=bottom;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		color = c;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	/**
	 * @param left the left to set
	 */
	protected void setLeft(double left) {
		this.left = left;
	}

	/**
	 * @param right the right to set
	 */
	protected void setRight(double right) {
		this.right = right;
	}

	/**
	 * @param top the top to set
	 */
	protected void setTop(double top) {
		this.top = top;
	}

	/**
	 * @param bottom the bottom to set
	 */
	protected void setBottom(double bottom) {
		this.bottom = bottom;
	}

	

}
