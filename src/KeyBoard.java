import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyBoard implements KeyListener {

	private View view;

	public KeyBoard(View view) {
		this.view = view;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("fffffffffff");
//		for (int j=0 ; j<view.balls.size() ; j++)
		{
			switch (e.getKeyCode()) {
			case 39:
				System.out.println("dhffhjfk");
				return;
			case 37:
				System.out.println("dfngbdofnkk");
				return;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
