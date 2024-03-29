package compilateur.generator;

import java.util.Hashtable;

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
		s = "public static void main(String args[]){\n\n";
		return s;
	}
	
	public String varInstance(){
		s = "public static Variable variable;\n\n";
		return s;
	}
	
	public String initVariable(){
		s = "variable = new Variable();\n";
		return s;
	}
	public String varGrafcet(String name){
		s = "Grafcet "+name+";\n";
		return s;
	}
	public String initGrafcet(String name){
		s = name +" = new Grafcet(variable);\n\n";
		return s;
	}
	
	public String classe(String a){
		s = "public class "+a+"{\n";
		return s;
	}
	
	public String step(String nomGrafcet, String nom){
		s = nomGrafcet+".addStep(\""+nom+"\");\n";
		return s;
	}
	
	public String transition(String nomGrafcet, String nom){
		s = nomGrafcet+".addTransition(\""+nom+"\");\n";
		return s;
	}
	public String transition(String nomGrafcet, String nom, String var){
		s = nomGrafcet+".addTransition(\""+nom+"\",\""+var+"\");\n";
		return s;
	}
	public String addConditionEtat(String nomGrafcet, String nom, String var){
		String s =var.substring(0, var.length()-2);
		s = nomGrafcet+".getNodeTransition(\""+nom+"\").getTransi().setCondition("+nomGrafcet+".getNodeStep(\""+s+"\").getStep().getActive());\n";
		return s;
	}
	public String addConditionLadder(String nomGrafcet, String nom, String var[], Hashtable<String, String> hash){
		String cond = "";
		for(String i : var){
			if(i.contains(".x")){
				String ss =i.substring(0, i.length()-2);
				cond += (cond.equals("")) ? hash.get(ss).toString()+".getNodeStep(\""+ss+"\").getStep().getActive().get()" : ", "+hash.get(ss).toString()+".getNodeStep(\""+ss+"\").getStep().getActive().get()";
			}
			else{
				cond += (cond.equals("")) ? "variable.condition.get(\""+i+"\").get()" : ", variable.condition.get(\""+i+"\").get()";
				
			}
			
			s = nomGrafcet+".getNodeTransition(\""+nom+"\").getTransi().setCondition(new Bool("+cond+"));\n";
		}
		return s;
	}
	
	public String ajoutVariable(String name){
		s = "variable.addVariable(\""+name+"\", false);\n";
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
	
	public String start(String nomGrafcet){
		s = nomGrafcet+".start();\n";
		return s;
	}

}
