package pobj.pinboard.editor.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.editor.EditorInterface;

public abstract class AbstractTool implements Tool{
	/**
	 * baseX et baseY sont les coordonnées du point fixé par la souris 
	 * une fois qu'elle aura cliqué pour la première fois sur la fenêtre
	 */
	private double baseX;
	private double baseY;
	/**
	 * currentX et currentY sont les coordonnées du point sur lequel est posée la souris 
	 * lors du drag ou du release
	 */
	private double currentX;
	private double currentY;

	/**
	 * currentColor enables us to change the colors with the palet
	 */
	private Color currentColor=Color.BLACK;
	//getters
	public double CurrentX() {
		return currentX;
	}
	public double CurrentY() {
		return currentY;
	}
	public double BaseX() {
		return baseX;
	}
	public double BaseY() {
		return baseY;
	}
	public Color getCurrentColor() {
		return currentColor;
	}
	
	//setters
	public void setCurrentX(double c) {
		currentX=c;
	}
	public void setCurrentY(double c) {
		currentY=c;
	}
	public void setCurrentColor(Color c) {
		currentColor=c;
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		baseX=e.getX();
		baseY=e.getY();
		currentX=baseX;
		currentY=baseY;
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		currentX=e.getX();
		currentY=e.getY();

		
	}
}
