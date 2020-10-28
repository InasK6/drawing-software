package pobj.pinboard.editor.tools.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.test.ToolTest.MockEditor;




public class ToolImageTest extends ToolTest {
	private EditorInterface editor = new MockEditor();
	//private File file=new File("/home/inas/Images/Hello_606x341.jpg");
	private File file=new File("Hello_606x341.jpg");
	private Tool tool = new ToolImage(file);

	@Test
	public void testCreate() {
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 100, 200, false));
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 300, 400, false));
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 300, 400, false));
		List<Clip> list = editor.getBoard().getContents();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipImage);
		ClipImage r = (ClipImage) list.get(0);
		assertEquals(300., r.getLeft(), 0.);
		assertEquals(400., r.getTop(), 0.);
		/*assertEquals(300., r.getRight(), 0.);
		assertEquals(400., r.getBottom(), 0.);*/
	}
}
