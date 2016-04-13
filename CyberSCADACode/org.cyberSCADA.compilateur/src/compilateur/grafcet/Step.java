package compilateur.grafcet;

public class Step extends Composant{
	
	
	public Step(String name, int ... id){
		this.name = name;
		if(id.length == 1)
			this.id = id[0];
		else
			this.id = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	
}
