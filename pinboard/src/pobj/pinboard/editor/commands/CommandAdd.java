package pobj.pinboard.editor.commands;

import java.util.LinkedList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command{

	private EditorInterface editor;
	private List<Clip> list=new LinkedList<Clip>();
	
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		super();
		this.editor = editor;
		list.add(toAdd);
	}
	
	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		super();
		this.editor = editor;
		list.addAll(toAdd);
	}

	@Override
	public void execute() {
		for(Clip c:list) {
			editor.getBoard().addClip(c);
		}
	}

	@Override
	public void undo() {
		for(Clip c:list) {
			editor.getBoard().removeClip(c);
		}
		editor.getSelection().clear();
		
	}

}
