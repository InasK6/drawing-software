package pobj.pinboard.editor;



import java.beans.EventHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.document.Composite;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface, ClipboardListener {
	private Board board = new Board();
	private Tool tool;
	private File filename;
	private Selection selection=new Selection();
	private MenuItem paste;
	private CommandStack commandStack=new CommandStack();
	/**initialisation de la couleur courante de dessin à noir
	 * elle est modifiée lors du choix d'une couleur de la palette
	 */
	private Color colorNow=Color.BLACK;
	public EditorWindow(Stage stage) {
		
		Canvas canvas = new Canvas(800, 600);
		VBox vbox = new VBox();
		
		
		//Barre de Menu
		
		Menu file=new Menu("File");
		//peupler les menus déroulants
		MenuItem n=new MenuItem("new window");
		MenuItem c=new MenuItem("close current window");
		file.getItems().addAll(n,c);
		
		Menu edit=new Menu("Edit");
		MenuItem copy=new MenuItem("copy");
		paste=new MenuItem("paste");
		clipboardChanged();
		MenuItem del=new MenuItem("delete");
		
		//Option de group 
		MenuItem group=new MenuItem("group");
		MenuItem ungroup=new MenuItem("ungroup");
		// actions associées
		group.setOnAction(e->group());
		ungroup.setOnAction(e->ungroup());
		
		MenuItem undo=new MenuItem("Undo");
		MenuItem redo=new MenuItem("Redo");
		
		edit.getItems().addAll(copy, paste, del, group, ungroup, undo, redo );
		
		
		Menu tools=new Menu("Tools");
		//Ajout des menus dans la barre des menus
		MenuBar mb=new MenuBar(file, edit, tools);
		
		//Ajout de handlers aux clics sur les menuItem
		n.setOnAction(e-> new EditorWindow(new Stage()));
		c.setOnAction(e-> {Clipboard.getInstance().removeListener(this);stage.close(); });
		copy.setOnAction(e->{ if(!selection.getContents().isEmpty()) {
								Clipboard.getInstance().copyToClipboard(selection.getContents());
								clipboardChanged();
							}
		});

		
		
		//Ajout de la barre de menu dans Vbox
		vbox.getChildren().add(mb);
		
		//Barre d'outils
		
		Button box=new Button("Box");
		Button ellipse=new Button("Ellipse");
		Button img=new Button("Img...");
		Button select=new Button("Selection");
		ToolBar tb=new ToolBar(box, ellipse, img, select);
		
		
		//ajout de la barre dans vbox
		vbox.getChildren().add(tb);
		
		//ajout d'une barre de couleurs

		Rectangle b=new Rectangle(40, 25, Color.BLACK);
		Rectangle wh=new Rectangle(40, 25, Color.WHITE);
		Rectangle r=new Rectangle(40, 25, Color.RED);
		Rectangle gr=new Rectangle(40, 25, Color.GREEN);
		Rectangle bl=new Rectangle(40, 25, Color.BLUE);
		
		
		// Mise des couleurs à l'interieur de boutons
		Button black=new Button(null,b);
		Button white=new Button(null,wh);
		Button red=new Button(null,r);
		Button green=new Button(null,gr);
		Button blue=new Button(null,bl);
		

		
		
		ToolBar colorb=new ToolBar(black, white, red, green , blue);
		
		vbox.getChildren().add(colorb);
		
		//ajout de la zone de dessin dans vbox
		vbox.getChildren().add(canvas);
		
		
		GraphicsContext gc=canvas.getGraphicsContext2D();
		
		//action associée à chaque choix de couleur
		actionColor(black, Color.BLACK, gc);
		actionColor(white, Color.WHITE, gc);
		actionColor(red, Color.RED, gc);
		actionColor(green, Color.GREEN, gc);
		actionColor(blue, Color.BLUE, gc);
		
		//Ajout de handler sur Canva
		canvas.setOnMousePressed(
				(e)-> {press(e); draw(gc);}
				
		);
		canvas.setOnMouseDragged((e)-> {drag(e); draw(gc);});
		canvas.setOnMouseReleased((e)-> {release(e); draw(gc);} );
		undo.setOnAction(e->{commandStack.undo();draw(gc);});
		redo.setOnAction(e->{commandStack.redo(); draw(gc);});
		
		// coller des éléments
		paste.setOnAction(e->{ board.addClip(Clipboard.getInstance().copyFromClipboard()); draw(gc);});
		//Supression d'éléments
		del.setOnAction(e->{
			List<Clip> l=selection.getContents();
			
			board.removeClip(l);
			selection.clear();
			clipboardChanged();
			draw(gc);
			
		});
		
		Label l=new Label("choose a tool");
		//ajout d'action lors de clics sur ces boutons
		box.setOnAction(e->{ 
			tool=new ToolRect();
			tool.setCurrentColor(colorNow);
			l.textProperty().set(tool.getName());
			
				
		});
		ellipse.setOnAction(e-> {
			tool=new ToolEllipse();
			tool.setCurrentColor(colorNow);
			l.textProperty().set(tool.getName());
			
		});
		img.setOnAction(
				e->{
				FileChooser fc=new FileChooser();
				File selectedFile = fc.showOpenDialog(stage);
				 if (selectedFile != null) {
					 filename=selectedFile;
					 tool=new ToolImage(filename);
				 }
				
				l.textProperty().set(tool.getName());	
				
		});
		
		select.setOnAction(e-> {tool=new ToolSelection();
								l.textProperty().set(tool.getName());});
		
		//Barre de statut
		Separator s=new Separator();
		vbox.getChildren().add(s);
		
		// Label
		
		vbox.getChildren().add(l);
		
		stage.setScene(new Scene(vbox));
		stage.setTitle("PinBoard - <untitled>");
		
		Clipboard.getInstance().addListener(this);
		

		stage.show();
	}
	public Board getBoard() {
		return board;
	}
	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return selection;
	}
	@Override
	public CommandStack getUndoStack() {
		
		return commandStack;
	}
	
	public void press( MouseEvent e) {
		tool.press(this, e);
	}
	public void drag(MouseEvent e) {
		tool.drag(this, e);
	}
	public void release( MouseEvent e) {
		tool.release(this, e);
	}
	
	/**
	 * Méthode à appeler après chaque action qui modifie l'affichage
	 */
	public void draw(GraphicsContext gc) {
		tool.drawFeedback(this, gc);
	}
	@Override
	public void clipboardChanged() {
		
		paste.setDisable(Clipboard.getInstance().isEmpty());

	}
	
	/**
	 * Méthode appelée après avoir sélectionné des éléments et avoir choisi une nouvelle couleur
	 */
	private void changeColorSelected() {
		for(Clip i: selection.getContents()) {
			i.setColor(colorNow);
		}
	}
	/**
	 * spécifie l'action associée aux boutons de couleur
	 * @param b référence vers le bouton
	 * @param c couleur associée au bouton
	 */
	private void actionColor(Button b, Color c, GraphicsContext gc) {
		b.setOnAction(e-> {
			
			
			colorNow=c;
			changeColorSelected();
			draw (gc);
			selection.clear();
			tool.setCurrentColor(colorNow);
		});
	}
	/**
	 * Actions effectuées pour grouper des éléments présents sur le tableau
	 */
	private void group() {
		new CommandGroup(this, selection.getContents()).execute();
	}
	/**
	 * séparer un groupe en plusieurs éléments
	 * On peut séléctionner plusieurs groupes au mêmes temps et tous les séparer
	 */
	private void ungroup() {
		for( Clip c: selection.getContents()) {
			// on applique des actions seulement aux Clip qui représentent des groupes
			// les autres sont ignorés
			if(c instanceof ClipGroup) {
				ClipGroup cg=(ClipGroup) c;
				new CommandUngroup(this, cg).execute();
			}
		}
		selection.clear();
		
	}
}
