
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class View extends JPanel implements ActionListener{
	
    private static final int D_HEIGHT = 700;
    private static final int D_WIDTH = 1300;
    private static final int INCREMENT = 5;
    private List<Plate> balls;
    private List<Color> colors;
    private Timer timer = null;
    
    public Player1 p1 ;
    public Player2 p2 ;
    
    JTextPane player1Score ;
    JTextPane player2Score ;
    JTextPane time ;
    
    
    public View(Player1 p1, Player2 p2) {
    	
    	SimpleAttributeSet green = new SimpleAttributeSet();
    	StyleConstants.setFontFamily(green, "Courier New Italic");
    	StyleConstants.setForeground(green, Color.GREEN);
    	StyleConstants.setFontSize(green, 18);
    	
    	this.p1 = p1 ;
    	this.p2 = p2 ;
        colors = createColorList();
        balls = createShapeList();
        
        this.setLayout(null);
        player1Score = new JTextPane();
        player2Score = new JTextPane();
        time = new JTextPane();
        
        Stopwatch s = new Stopwatch();
         
        player1Score.setText(p1.getScore());
        player2Score.setText(p2.getScore());
        time.setText(Double.toString(s.elapsedTime()));
        
        player1Score.setEditable(false);
        player2Score.setEditable(false);
        
       
        
        this.add(player1Score );
        this.add(player2Score );
        this.add(time );
        
        player1Score.setSize(65, 65);
        player1Score.setLocation(1065,295);
        
        try
        {
            player1Score.getDocument().insertString(0, "green text with Courier font", green);
        }
        catch(Exception e){
        	
        }
        
        time.setSize(65, 65);
        time.setLocation(1065,100);
        
        player2Score.setSize(65, 65);
        player2Score.setLocation(1065,440);
        
        timer = new Timer(65, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Plate shape : balls) {
                    shape.move();
                    shape.decreaseDelay();
                    repaint();
                    player1Score.setText(p1.getScore());
                    player2Score.setText(p2.getScore());
                    time.setText(Double.toString(s.elapsedTime()));
                }
            }
        });
        
        timer.start();
        
//        timer.start();
        
        this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3 && p1.rod2Position.x < 1000-60){
					p1.clown1Position.x += 10 ;
					p1.rod1Position.x += 10 ;
					p1.rod2Position.x += 10 ;
					repaint();
				}
				
				if(e.getButton()==MouseEvent.BUTTON1 && p1.rod1Position.x > 0){
					p1.clown1Position.x -= 10 ;
					p1.rod1Position.x -= 10 ;
					p1.rod2Position.x -= 10 ;
					repaint();
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});


        this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {}
			
			
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT && p2.rod2Position.x < 1000-60){
					p2.clown2Position.x += 10 ;
					p2.rod1Position.x += 10 ;
					p2.rod2Position.x += 10 ;
					repaint();
				}	
				
				 if(e.getKeyCode()==KeyEvent.VK_LEFT && p2.rod1Position.x > 0){
					p2.clown2Position.x -= 10 ;
					p2.rod1Position.x -= 10 ;
					p2.rod2Position.x -= 10 ;
					repaint();
				}
				
			}
		});
        
        this.setFocusable(true);
        
    }
        
  
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
		Image background ;
		try {
			background = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\background3.png") );
			g.drawImage(background, 0, 0, null);
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
        p1.draw(g);
        p2.draw(g);
        for (Plate shape : balls) {
        	shape.drawShape(g,p1,p2);
		}
        
        if(Double.parseDouble(time.getText()) >=60){
        	timer.stop();
        	Image p1Won , p2Won , draw ;
        	this.remove(player1Score);
        	this.remove(player2Score);
        	this.remove(time);
        	
        	try{
        		p1Won = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\p1won.png") );
        		p2Won = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\p2won.png") );
        		draw = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\draw.png") );
        		if(Integer.parseInt(player1Score.getText()) > Integer.parseInt(player2Score.getText()))
            		g.drawImage(p1Won, 0,0, null);
        		
        		else if(Integer.parseInt(player2Score.getText()) > Integer.parseInt(player1Score.getText()))
            		g.drawImage(p2Won, 0,0, null);
        		
        		else if(Integer.parseInt(player1Score.getText()) == Integer.parseInt(player2Score.getText()))
            		g.drawImage(draw, 0,0, null);
        	}
        	catch (Exception e){
        		
        	}
        	
        	return ;
        }
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(D_WIDTH, D_HEIGHT);
    }

    private List<Color> createColorList() {
        List<Color> list = new ArrayList<>();
        list.add(Color.GREEN);
        list.add(Color.RED);
        list.add(Color.BLUE);
        return list;
    }

    private List<Plate> createShapeList() {
        List<Plate> list = new ArrayList<Plate>();
        Image redBall ;
        Image blueBall ;
        Image greenBall ;
        try {
			greenBall = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\greenBall.png"));
			blueBall = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\blueBall.png"));
	        redBall = ImageIO.read(new File("F:\\workspace\\Circus of Plates\\redBall.png"));
	        Image[] images = {greenBall , redBall , blueBall };
	        
	        Random random = new Random();
	        for (int i = 0; i < 30; i++) {
	            int randXLoc = random.nextInt(950);
	            int randomDelayedStart = random.nextInt(300);
	            int ballIndex = random.nextInt(images.length);
	            list.add(new Plate(randXLoc, randomDelayedStart, images[ ballIndex ],colors.get(ballIndex)));
	        }
		} 
        
        catch (IOException e) {
			e.printStackTrace();
		}
        

        return list;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}