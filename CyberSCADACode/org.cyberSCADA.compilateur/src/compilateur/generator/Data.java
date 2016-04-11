package compilateur.generator;

import compilateur.grafcet.Bool;

public class Data {
	
	private String s;
	
	public Data(){
		
	}
	
	
	public String accoladeFin(){
		s = "}\n";
		return s;
	}
	
	public String setPackage(){
		s = "package compilateur.grafcet;\n\n";
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
	
	public String grafcet(){
		s = "public static Grafcet g;\n";
		return s;
	}
	
	public String initGrafcet(){
		s = "g = new Grafcet();\n";
		return s;
	}
	
	public String classe(String a){
		s = "public class "+a+"{\n";
		return s;
	}
	
	public String step(String nom){
		s = "g.addStep(\""+nom+"\");\n";
		return s;
	}
	
	public String transition(String nom, String var){
		s = "g.addTransition(\""+nom+"\","+var+");\n";
		return s;
	}
	
	public String ajoutVariable(String name){
		s = "g.addVariable(\""+name+"\", false);\n";
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
	
	public String start(){
		s = "g.start();\n";
		return s;
	}

}
