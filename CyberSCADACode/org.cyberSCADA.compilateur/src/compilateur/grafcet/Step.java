package compilateur.grafcet;

public class Step extends Composant{
	
	public boolean initial = false;
	
	public Step(String name){
		this.name = name;
	}
	
	public void setInitial(boolean b){
		initial = b;
	}
	
	public boolean isInitial(){
		return initial;
	}
	
	
	
	
}
