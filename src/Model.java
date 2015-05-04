import java.awt.Color;
import java.util.ArrayList;


public class Model implements Subject {

	//this class is model class and originator class
	
	Player1 player1 ;
	Player2 player2 ;
	
	private int player1Score=0 , player2Score=0 ;
	
	Caretaker player1Stack1 = new Caretaker();
	Caretaker player1Stack2 = new Caretaker();
	Caretaker player2Stack1 = new Caretaker();
	Caretaker player2Stack2 = new Caretaker();
	
	private Color shapeColor ;
	
	public Model(){
		player1 = new Player1();
		player2 = new Player2();
		this.player1Stack1 = player1.player1Stack1 ;
		this.player1Stack2 = player1.player1Stack2 ;
		this.player2Stack1 = player2.player2Stack1 ;
		this.player2Stack2 = player2.player2Stack2 ;
	}
	
	public void register(Observer o) {
		
	}

	public void unregister(Observer o) {
		
	}
	
	public void notifyObserver() {
	
		player1.update(player1Score);
		player2.update(player2Score);
	}
	
	public void setColor (Color color){
		shapeColor = color ;
	}
	
	public void saveColor(int playerNum){
		
	}
	
	public Color restorColor(Memento shape){
		return shape.getShape().color;
	}
	
//	public boolean has3MatchColors(int playerNum){
//		Color[] top3Colors = new Color[3];
//		
//		if(playerNum==1){
//			if(player1Stack1.colors.size() < 3)
//				return false ;
//			
//			for(int i=0 ; i<3 ; i++){
//				top3Colors[i] = player1Stack.getShape(i).getShape().color;
//			}
//			return top3Colors[0] == top3Colors[1] && top3Colors[1] == top3Colors[2] ;
//		}
//		
//		else if(playerNum==2){
//			if(player2Stack.colors.size() < 3)
//				return false ;
//			
//			for(int i=0 ; i<3 ; i++){
//				top3Colors[i] = player2Stack.getShape(i).getShape().color;
//			}
//			return top3Colors[0] == top3Colors[1] && top3Colors[1] == top3Colors[2] ;
//		}
//		
//		return false ;
//	}
	
	public void vanishShapes(int playerNum){
		if(playerNum==1){
			for(int i=0 ; i<3 ; i++)
				player1Stack1.removeShape(0);
			
			player1Score++;
		}
		
		else if(playerNum==2){
			for(int i=0 ; i<3 ; i++)
				player2Stack1.removeShape(0);
		
			player2Score++;
		}
		
		this.notifyObserver();
	}
	
	
	
}
