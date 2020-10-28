package pobj.pinboard.editor.commands;

/**
 * 
 * @author inas
 * construire une commande n'exécute pas l'action
 */
public interface Command {
	public void execute();
	public void undo();
}
