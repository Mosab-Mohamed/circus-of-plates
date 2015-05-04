import java.awt.Color;
import java.util.ArrayList;


public class Caretaker {

	ArrayList<Memento> colors = new ArrayList<Memento>();
	
	public void addShape(Memento m){
		colors.add(m);
	}
	
	public Memento getShape(int index){
		return colors.get(index);
	}
	
	public void removeShape(int index){
		colors.remove(index);
	}
	
	public int size(){
		if(colors.isEmpty())
			return 0 ;
		return colors.size();
	}
}
