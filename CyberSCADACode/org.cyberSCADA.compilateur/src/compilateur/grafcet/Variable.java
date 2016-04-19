package compilateur.grafcet;

import java.util.Hashtable;

public class Variable {
	
	public Hashtable<String, Bool> condition = new Hashtable<String, Bool>();
	
	public void addVariable(String name, boolean value){
		condition.put(name, new Bool(value));
	}
	
	public Variable(){
		
	}
	
}
