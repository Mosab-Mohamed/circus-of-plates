import java.awt.Color;
import java.awt.Image;


public class Memento {

	private Plate shape ;
	
	public Memento (Plate shape){
		this.shape = shape ;
	}
	
	public Plate getShape(){
		return shape ;
	}
}
