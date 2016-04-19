package compilateur.grafcet;

public class Transition extends Composant{
	
	private Bool condition;
	
	public Transition(String name){
		this.name = name;
	}
	
	public void setCondition(Bool b){
		condition = b;
	}
	
	public Bool getCondition(){
		return condition;
	}
	
	public boolean isTrue(){
		boolean bb = true;
		for(Bool b : condition.b){
			bb = bb && b.get();
		}
		return bb;
	}

}
