package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.Composite;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command {
	private EditorInterface editor;
	private List<Clip> togroup;
	private Composite newC;
	public CommandGroup(EditorInterface e, List<Clip > list) {
		editor=e;
		togroup=list;
	}
	@Override
	public void execute() {
		newC=new ClipGroup();
		for(Clip c: togroup) {
		//for(Clip c: editor.getSelection().getContents()) {
			//on retire les éléments sélectionnés de la planche
			editor.getBoard().removeClip(c);
			//on les ajoute à un nouvel objet groupe
			newC.addClip(c);
			
		}
		// ajout du groupe à la planche
		editor.getBoard().addClip(newC);
		//on retire la référence vers les objets séléctionnés de la séléction
		editor.getSelection().clear();
		
	}

	@Override
	public void undo() {
		if(newC!=null) {
			editor.getBoard().removeClip(newC);
			for(Clip fils: newC.getClips()) {
				editor.getBoard().addClip(fils);
			}
		}
		editor.getSelection().clear();
	}

}
