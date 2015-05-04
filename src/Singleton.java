
public class Singleton {

	private Singleton (){
		
	}
	
	private static Singleton firstSingleton = null ;
	
	public Singleton getInstance(){
		
		if(firstSingleton==null)
			firstSingleton = new Singleton();
		
		return firstSingleton;
	}
}
