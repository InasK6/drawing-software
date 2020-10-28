package pobj.pinboard.document;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private List<Clip> contents=new LinkedList<Clip>();
	public Board() {
		
	}
	public List<Clip> getContents(){
		return contents;
	}
	/**
	 * ajout d'un élément graphique dans la liste de contenus de la planche
	 * @param clip l'élément graphique
	 */
	public void addClip(Clip clip) {
		contents.add(clip);
	}
	/**
	 * ajout d'une liste d'éléments graphiques dans la liste de contenus de la planche
	 * @param clip liste d'éléments graphiques
	 */
	public void addClip(List<Clip> clip) {
		 contents.addAll( clip);
	}
	/**
	 * supression d'un élément graphique dans la liste de contenus de la planche
	 * @param clip l'élément graphique
	 */
	public void removeClip(Clip clip) {
		contents.remove(clip);
	}
	/**
	 * supression d'une liste d'éléments graphiques dans la liste de contenus de la planche
	 * @param clip liste d'éléments graphiques
	 */
	public void removeClip(List<Clip> clip) {
		 contents.removeAll(clip);
	}
	
	public void draw(GraphicsContext gc) {
		double width=gc.getCanvas().getWidth();
		double height=gc.getCanvas().getHeight();
		Color c=Color.WHITE;
		gc.setFill(c);
		gc.fillRect(0.0, 0.0, width, height);
		for(Clip Elem: contents) {
			Elem.draw(gc);
		}
		
		
	}
	
}
