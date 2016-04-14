package compilateur.grafcet;

public class Transition extends Composant{
	
	public Bool condition;
	
	public Transition(String name, int ... id){
		this.name = name;
		if(id.length == 1)
			this.id = id[0];
		else
			this.id = 0;
	}
	
	public void setCondition(Bool b){
		condition = b;
	}
	
	public Bool getCondition(){
		return condition;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isTrue(){
		boolean bb = true;
		for(Bool b : condition.b){
			bb = bb && b.get();
		}
		return bb;
	}

}
