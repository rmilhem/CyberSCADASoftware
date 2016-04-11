package compilateur.grafcet;

public class Step extends Composant{
	
	
	public Step(String name){
		this.name = name;
	}
	
	public void run(){
		active = true;
		System.out.println("start step");
	}
	
	
	public void setActive(boolean b){
		active = b;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void setInitial(boolean b){
		initial = b;
	}
	
	public boolean isInitial(){
		return initial;
	}
	
	
	
	
	
	
}
