package compilateur.grafcet;

public class Bool {
	
	public boolean bool;
	
	public Bool(boolean b, boolean ... bb){
		bool = b;
		
		for(boolean i : bb){
			bool = bool && i;
		}
	}
	
	public void set(boolean b){
		bool = b;
	}
	public boolean get(){
		return bool;
	}
	
	public String toString(){
		String s = "";
		s += bool;
		return s;
	}

}
