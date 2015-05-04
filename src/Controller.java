import java.util.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Controller extends JFrame {

	//controller and the class that implements subject
	
	
	Player1 player1 = new Player1() ;
	Player2 player2 = new Player2() ;
	
	int player1Score , player2Score ;
	
	public Controller(){
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                Model model = new Model();
                View view = new View(model.player1, model.player2);
//                addKeyListener(new KeyBoard(view));
                
                frame.add(view);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
		
	}
	
	 public static void main(String[] args) {
	     new Controller();   
	    }
	

}
