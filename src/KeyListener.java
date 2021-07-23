import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class KeyListener extends KeyAdapter
{
	private static HashSet<Integer> input = new HashSet<>(); 
	@Override
	public void keyPressed(KeyEvent e)
	{
		input.add(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		input.remove(e.getKeyCode());
	}
	public static boolean isKeyPressed(int keyCode)
	{
		return input.contains(keyCode);
	}
}