package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage implements Clip{
	private double left;
	private double top;
	private File filename;
	private Image img;
	public ClipImage(double left, double top, File filename) {
		this.left=left;
		this.top=top;
		this.filename=filename;
		img=new Image("file://"+filename.getAbsolutePath());
	}
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.drawImage(img, left,top);
		
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
		double height=img.getHeight();
		return top+height;
	}
	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		double width=img.getWidth();
		return left+width;
	}
	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		this.left=left;
		this.top=top;
		
		
	}
	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		left+=x;
		top+=y;
	}
	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return x>=left && x<=left+img.getWidth() && y>=top && y<=top+img.getHeight();
	}
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return new ClipImage( left,  top,  filename);
	}
}
