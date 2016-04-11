package compilateur.grafcet;

public class Controller {

	private Grafcet g;
	
	public Controller(Grafcet g){
		this.g= g;
	}
	
	public void next(){
		g.next();
	}
	
	public boolean getState(String name){
		return g.getNodeStep(name).getStep().isActive();
	}
}
