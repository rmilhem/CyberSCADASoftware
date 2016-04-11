package compilateur.grafcet;

public class Controller {

	private Grafcet g;
	
	public Controller(Grafcet g){
		this.g= g;
	}
	
	public void next(){
		g.next();
	}
}
