package pobj.pinboard.document;

import java.util.LinkedList;
import java.util.List;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup  extends AbstractClip  implements Composite{

	private List<Clip> clips =new LinkedList<Clip>();
	/**
	 * Constructeur avec liste d'éléments initialement vide
	 */
	public ClipGroup() {
		super (Double.MAX_VALUE, Double.MAX_VALUE,0.0,0.0,Color.BLACK);
	}
	
	/**
	 * Constructeurs avec arguments nécessaires pour le constructeur père
	 */
	public ClipGroup(int i, int j, int k, int l, Color c) {
		super (i,j, k, l,c);
	}
	/**
	 * Constructeur avec liste de clips
	 */
	public ClipGroup(List<Clip> clips) {
		super (Double.MAX_VALUE, Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Color.BLACK);
		
		this.clips=clips;
		calculate();
	}
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		for (Clip c: clips) {
			c.draw(ctx);
		}
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		List<Clip> list=new LinkedList<>();
		for(Clip c: clips) {
			list.add(c.copy());
		}
		return new ClipGroup(list);
	}

	@Override
	public List<Clip> getClips() {
		// TODO Auto-generated method stub
		return clips;
	}

	@Override
	public void addClip(Clip toAdd) {
		// TODO Auto-generated method stub
		if( clips.contains(toAdd)) {
			return ;
		}
		clips.add(toAdd);
		if(toAdd.getTop()<this.getTop()) {
			this.setTop(toAdd.getTop());
		}
		if(toAdd.getBottom()>this.getBottom()) {
			this.setBottom(toAdd.getBottom());
		}
		if(toAdd.getLeft()<this.getLeft()) {
			this.setLeft(toAdd.getLeft());
		}
		if(toAdd.getRight()>this.getRight()) {
			this.setRight(toAdd.getRight());
		}
	}

	/**
	 * Calculate attributes values
	 */
	
	private void calculate() {
		this.setBottom(0.0);
		this.setRight(0.0);
		this.setLeft(Double.MAX_VALUE);
		this.setTop(Double.MAX_VALUE);
		//System.out.println(" -- Left"+getLeft()+" -- Top"+getTop()+" -- Right"+getRight()+"bottom"+getBottom());
		for (Clip c:clips) {
			if(c.getTop()<this.getTop()) {
				this.setTop(c.getTop());
			}
			if(c.getBottom()>this.getBottom()) {
				this.setBottom(c.getBottom());
			}
			if(c.getLeft()<this.getLeft()) {
				this.setLeft(c.getLeft());
			}
			if(c.getRight()>this.getRight()) {
				this.setRight(c.getRight());
			}
		}
		//System.out.println(" -- Left"+getLeft()+" -- Top"+getTop()+" -- Right"+getRight()+"bottom"+getBottom());
		
	}
	@Override
	public void removeClip(Clip toRemove) {
		// TODO Auto-generated method stub
		clips.remove(toRemove);
		//look for new values 
		calculate();
	}
	
	@Override
	public void move(double x, double y) {
		super.move(x, y);
		for(Clip c: clips) {
			c.move(x, y);
		}
	}
	@Override
	public void setGeometry(double left, double top, double right, double bottom){
		double h=bottom-top;
		double l=right-left;
		double currentH=this.getBottom()-this.getTop();
		double currentL=this.getRight()-this.getLeft();
		if(h!=currentH || l!=currentL) {
			try{
				throw new Exception(" taille non concordantes, changement de dimensions non pris en compte");
			}catch (Exception e) {
				System.out.println("Attention changement de taille non pris en compte !!!!");
			}
		}
		else {
			super.setGeometry(left, top, right, bottom);
		}
	}
}
