package pobj.pinboard.editor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pobj.pinboard.editor.commands.Command;
public class CommandStack {
	private List<Command> undo=new ArrayList<>();
	private List<Command> redo=new ArrayList<>();
	
	public void addCommand( Command cmd) {
		undo.add(cmd);
		redo.clear();
	}
	/**
	 * when we undo a command, we keep a reference to it in redo List in order to be able to redo it later
	 */
	public void undo() {
		if(!isUndoEmpty()) {
			int size=undo.size();
			Command c=undo.get(size-1);
			undo.remove(size-1);
			c.undo();
			redo.add(c);
			
		}
		
		
	}
	public void redo() {
		if(!isRedoEmpty()) {
			int size=redo.size();
			Command c=redo.get(size-1);
			redo.remove(c);
			c.execute();
			undo.add(c);
			
		}
		
	}
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}
}
