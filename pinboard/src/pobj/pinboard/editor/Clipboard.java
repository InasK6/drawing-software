package pobj.pinboard.editor;
import java.util.LinkedList;
import java.util.List;

import pobj.pinboard.document.Clip;
public class Clipboard {
	private static Clipboard cb;
	private List<Clip> copied=new LinkedList<Clip>();
	
	private List<ClipboardListener> cblisteners=new LinkedList<>();
	private Clipboard() {
		
	}
	public void copyToClipboard(List<Clip> clips) {
		//copied.addAll(clips);
		copied.clear();
		for(Clip c: clips) {
			copied.add(c.copy());
		}
		notifyListeners();
	}
	public List<Clip> copyFromClipboard(){
		List<Clip> result=new LinkedList<>();
		for(Clip c:copied) {
			result.add(c.copy());
		}
		return result;
	}
	
	private void notifyListeners() {
		for (ClipboardListener c: cblisteners) {
			c.clipboardChanged();
		}
	}
	/**
	 * removes all copied from presse-papier
	 * informs board of its state's change
	 */
	public void clear() {
		copied.clear();
		notifyListeners();
	}
		
	public boolean isEmpty() {
		return copied.isEmpty();
	}
	/**
	 * DP Singleton function, creates the instance on its first use
	 */
	public static Clipboard getInstance() {
		if(cb==null) {
			cb=new Clipboard();
		}
		return cb;
	}
	public void addListener(ClipboardListener listener) {
		cblisteners.add(listener);
	}
	public void removeListener(ClipboardListener listener) {
		cblisteners.remove(listener);
	}
}
