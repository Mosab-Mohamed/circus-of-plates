import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

class Plate extends Shape {

        int randXLoc;
        int y = 0;
        int randomDelayedStart;
        boolean draw = false;
        boolean out = false;
        Image image ;
        int INCREMENT =5 ; 
        Color color ;
        private List<Color> colors;

        public Plate(int randXLoc, int randomDelayedStart, Image image,Color color) {
            this.randXLoc = randXLoc;
            this.randomDelayedStart = randomDelayedStart;
            this.image = image;
            this.color = color ;
        }

        public void drawShape(Graphics g,Player1 p1,Player2 p2) {
			
			 if (draw && !out) {
				 	if(p1.haveBall(this)){
				 		out = true ;
				 		g.drawImage(image, this.randXLoc, this.y, null );
				 		return ;
				 	}
				 	
				 	else if(p2.haveBall(this)){
				 		out = true ;
				 		g.drawImage(image, this.randXLoc, this.y, null );
				 		return ;
				 	}
				 	
				 	g.drawImage(image, this.randXLoc, this.y, null );
		     }
        }

        public void move() {
            if (draw && !out) {
            	y += INCREMENT;
            	if(y>=600){
            		Random random = new Random();
            		draw = false ;
            		y=0;
            		randXLoc = random.nextInt(950);
            		randomDelayedStart = random.nextInt(300);
            		try{
	            		Image greenBall = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\greenBall.png"));
	        			Image blueBall = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\blueBall.png"));
	        	        Image redBall = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\redBall.png"));
	        	        Image[] images = {greenBall , redBall , blueBall };
	        	        int ballIndex = random.nextInt(images.length);
	        	        this.image = images[ ballIndex ];
	        	        colors = createColorList();
	        	        this.color = colors.get(ballIndex);
	        	        
            		}
            		catch(Exception e){
            			
            		}
            	}
            }
        }

        private List<Color> createColorList() {
            List<Color> list = new ArrayList<>();
            list.add(Color.GREEN);
            list.add(Color.RED);
            list.add(Color.BLUE);
            return list;
        }
        
        public void decreaseDelay() {
            if (randomDelayedStart <= 0 && !out) {
                draw = true;
            } 
            
            else {
                randomDelayedStart -= 1;
            }
        }

    }