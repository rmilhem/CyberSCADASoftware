package pcControle.right;
import java.util.Hashtable;

import pcControle.varAuto.VariableAuto;

public class Right {

	
	private VariableAuto nomVar;
	private boolean mode; // 0: lecture ; 1: ecriture
	private Role role;
	
	public Right(VariableAuto nomVar, boolean mode, Role role) {

		this.nomVar = nomVar;
		this.mode = mode;
		this.role = role;
	}
	
	public boolean isOk(VariableAuto nomVar, boolean mode){
		return this.nomVar == nomVar && this.mode == mode;
	
	}

	public Role getRole(){
		
		return role;
	}
	public boolean getMode(){
		return mode;
	}
	public VariableAuto getNomVar(){
		
		return nomVar;
	}
	public String toString(){
		return nomVar.toString();
	}
}
