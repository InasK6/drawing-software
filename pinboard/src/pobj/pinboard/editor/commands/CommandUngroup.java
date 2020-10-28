package pobj.pinboard.editor.commands;

import java.util.LinkedList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command{
	private EditorInterface editor;
	private List<Clip> ungrouped=new LinkedList<Clip>();
	private List<Clip> elements=new LinkedList<Clip>();
	
	private ClipGroup clip;
	public CommandUngroup(EditorInterface i, ClipGroup clip) {
		editor=i;
		this.clip=clip;
	}

	@Override
	public void execute() {
		

				ungrouped.add(clip);
				editor.getBoard().removeClip(clip);
				for(Clip fils: clip.getClips()) {
					elements.add(fils);
					editor.getBoard().addClip(fils);
				}

		
	}

	@Override
	public void undo() {
		for(Clip c: ungrouped) {
			editor.getBoard().addClip(c);
		}
		for(Clip c: elements) {
			editor.getBoard().removeClip(c);
		}
		editor.getSelection().clear();
		
	}
}
