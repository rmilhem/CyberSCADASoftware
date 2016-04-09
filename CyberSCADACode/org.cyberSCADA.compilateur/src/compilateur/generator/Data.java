package compilateur.generator;

public class Data {
	
	private String s;
	
	public Data(){
		
	}
	
	
	public String accoladeFin(){
		s = "}\n";
		return s;
	}
	public String imports(){
		s= "import java.util.*;\n\n";
		return s;
	}
	
	public String main(){
		s = "public static void main(){\n\n";
		return s;
	}
	
	public String classe(String a){
		s = "public class "+a+"{\n";
		return s;
	}
	
	public String step(String nom, String num){
		s = "Step step_"+num+" = new Step(\""+nom+"\");\n";
		return s;
	}
	
	public String transition(String nom){
		s = "Transition transi_"+nom+" = new Transition(\"transi_"+nom+"\");\n";
		return s;
	}
	
	public String comNom(String a){
		s = "//"+a+"\n";
		return s;
	}
	
	public String stepIsActive(String nom, String valInit){
		s = nom+".isActive = "+valInit+";\n\n";
		return s;
	}

}
