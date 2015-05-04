import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class Player2 extends JPanel implements Observer {

	private int score=0 ;
	
	Point clown2Position = new Point();
	Point rod1Position = new Point();
	Point rod2Position = new Point();
	Image clown ;
	
	Rectangle2D.Double rod1 ;
	Rectangle2D.Double rod2 ;
	
	Caretaker player2Stack1 = new Caretaker();
	Caretaker player2Stack2 = new Caretaker();
	
	public Player2(){
		 clown2Position.setLocation(5*1300/8-100,450);
		 rod1Position.setLocation(5*1300/8-120,528);
	     rod2Position.setLocation(5*1300/8+45,582);
	    
     	
	     try {
				clown = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\clown.png"));
			} 
	        
	        catch (IOException e) {
				e.getMessage();
			}
	}
	
	public void update(int score) {
		this.score = score ;
	}
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(clown, clown2Position.x , clown2Position.y , null);
		rod1 = new Rectangle2D.Double(rod1Position.x,rod1Position.y,60,15);
	   	rod2 = new Rectangle2D.Double(rod2Position.x,rod2Position.y,60,15);
		
	    g2d.fill(rod1);
        g2d.fill(rod2);
        
        if(S1has3MatchColors()){
        	for(int i=0 ; i<3 ; i++){
        		Plate shape = player2Stack1.getShape(player2Stack1.size()-1).getShape() ;
    			shape.out = false ;
    			shape.y = 600;
    			shape.move();
    			player2Stack1.removeShape(player2Stack1.size()-1);
    			
    		}
        	score++;
        }
        
        if(S2has3MatchColors()){
        	for(int i=0 ; i<3 ; i++){
        		Plate shape = player2Stack2.getShape(player2Stack2.size()-1).getShape() ;
    			shape.out = false ;
    			Random random = new Random();
        		shape.draw = false ;
        		shape.y=0;
        		shape.randXLoc = random.nextInt(1300);
        		shape.randomDelayedStart = random.nextInt(100);
    			player2Stack2.removeShape(player2Stack2.size()-1);
    		}
        	score++;
        }
        
        for(int i=0 ; i<player2Stack1.size() ; i++){
        	Memento shape = player2Stack1.getShape(i) ;
        	Plate ball = shape.getShape() ;
        	ball.randXLoc = (int)( rod1Position.x+rod1.getWidth()/2 - ball.image.getWidth(null)/2 ) ;
        	ball.y = (int) rod1Position.y - ball.image.getHeight(null)- ball.image.getHeight(null)*i;
        	g.drawImage(ball.image, ball.randXLoc, ball.y, null) ;
        }
        
        for(int i=0 ; i<player2Stack2.size() ; i++){
        	Memento shape = player2Stack2.getShape(i) ;
        	Plate ball = shape.getShape() ;
        	ball.randXLoc = (int)( rod2Position.x+rod2.getWidth()/2 - ball.image.getWidth(null)/2 ) ;
        	ball.y = (int) rod2Position.y - ball.image.getHeight(null) - ball.image.getHeight(null)*i;
        	g.drawImage(ball.image, ball.randXLoc, ball.y, null) ;
        }
	}
	
	public boolean haveBall(Plate shape){
		Image image = shape.image ;
		int x = shape.randXLoc ;
		int y = shape.y ;
		
		Rectangle2D.Double ball = new Rectangle2D.Double(shape.randXLoc,shape.y,image.getWidth(null),image.getHeight(null));
		Area areaS = new Area(ball);
		Area area1 ;
		Area area2 ;
		if(player2Stack1.size()==0){
			area1 = new Area(rod1);
		}
		else{
			Plate plate  = player2Stack1.getShape(player2Stack1.size()-1).getShape() ;
			int newX = plate.randXLoc ;
			int newY = plate.y ;
			Rectangle2D.Double newBall1 = new Rectangle2D.Double(newX,newY,image.getWidth(null),image.getHeight(null));
			area1 = new Area( newBall1);
		}
		
		if(player2Stack2.size()==0){
			area2 = new Area(rod2);
		}
		else{
			Plate plate  = player2Stack2.getShape(player2Stack2.size()-1).getShape() ;
			int newX = plate.randXLoc ;
			int newY = plate.y ;
			Rectangle2D.Double newBall1 = new Rectangle2D.Double(newX,newY,image.getWidth(null),image.getHeight(null));
			area2 = new Area( newBall1);
		}
		
		area1.intersect(areaS);
		area2.intersect(areaS);
		
		if(!area1.isEmpty()){
			shape.randXLoc = (int)( rod1Position.x+rod1.getWidth()/2 - image.getWidth(null)/2) ;
        	shape.y = (int) rod1Position.y - image.getHeight(null) - image.getHeight(null)*player2Stack1.size();
			player2Stack1.addShape(new Memento(shape));
			return true ;
		}
		
		if(!area2.isEmpty()){
			shape.randXLoc = (int)( rod2Position.x+rod2.getWidth()/2 - image.getWidth(null)/2) ;
        	shape.y = (int) rod2Position.y - image.getHeight(null) - image.getHeight(null)*player2Stack2.size();
			player2Stack2.addShape(new Memento(shape));
			return true ;
		} 
		
		return false ;
	}
	
	
	public boolean S1has3MatchColors(){
		Color[] top3Colors = new Color[3];
		if(player2Stack1.colors.size() < 3)
			return false ;
		
		int k= 0 ;
		for(int i=player2Stack1.size()-1 ; k<3 ; i--){
			top3Colors[k] = player2Stack1.getShape(i).getShape().color;
			k++ ;
		}
		return top3Colors[0] == top3Colors[1] && top3Colors[1] == top3Colors[2] ;
		
	
	}
	
	public boolean S2has3MatchColors(){
		Color[] top3Colors = new Color[3];
		if(player2Stack2.colors.size() < 3)
			return false ;
		
		int k= 0 ;
		for(int i=player2Stack2.size()-1 ; k<3 ; i--){
			top3Colors[k] = player2Stack2.getShape(i).getShape().color;
			k++ ;
		}
		return top3Colors[0] == top3Colors[1] && top3Colors[1] == top3Colors[2] ;
		
	
	}
	
	public String getScore(){
		return Integer.toString(score);
	}
}
