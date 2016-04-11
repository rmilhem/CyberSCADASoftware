package compilateur.grafcet;

public class Step extends Composant{
	
	
	public Step(String name){
		this.name = name;
	}
	
	public void run(){
		active.set(true);
		System.out.println("start step");
	}
	
	public String getNom(){
		return this.name;
	}
	
	
	public void setActive(boolean b){
		active.set(true);
	}
	
	public boolean isActive(){
		return active.get();
	}
	
	public Bool getActive(){
		return active;
	}
	
	public void setInitial(boolean b){
		initial = b;
	}
	
	public boolean isInitial(){
		return initial;
	}
	
	
	
	
	
	
}
