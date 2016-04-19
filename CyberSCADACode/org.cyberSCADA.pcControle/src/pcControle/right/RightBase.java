package pcControle.right;
import java.util.Vector;

import pcControle.varAuto.*;

public class RightBase {

	Vector<Right> rights;
	
	public RightBase(Vector<Role> roles){
		Right right;
		rights = new Vector<Right>();
		
		//initialisation des droits
		for(Role role: roles){
			if(role.getName().equals("admin")){
				
			}
			if(role.getName().equals("operateur")){
				// Les droits en ecriture
				right = new Right(VariableAuto.actionneurChuteHaut, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.actionneurChuteBas, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.presenceTubeBalle, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.moteurBalle, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.running, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.remplissage, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.tournerPlateau, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.boucher, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.actionPinces, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.presenceTubeBouchons, true, role);
				rights.addElement(right);
				right = new Right(VariableAuto.capteurBouchons, true, role);
				rights.addElement(right);
				// les droits en lecture
				right = new Right(VariableAuto.actionneurChuteHaut, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.actionneurChuteBas, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.presenceTubeBalle, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.moteurBalle, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.running, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.remplissage, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.tournerPlateau, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.boucher, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.actionPinces, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.presenceTubeBouchons, false, role);
				rights.addElement(right);
				right = new Right(VariableAuto.capteurBouchons, false, role);
				rights.addElement(right);
			}
		}
	}
	
	public boolean orderIsOK(VariableAuto idVar, boolean mode, Role role){
		
		for(Right right : rights){
			if(right.isOk(idVar, mode, role)) return true;
		}
		return false;
	}

	public Vector<Right> getRights(Role role){
		Vector<Right> rightsR = new Vector<Right>();
		for(Right e: rights){
		if(e.getRole() == role) rightsR.add(e);
		}
		return rightsR;
	}
	
	public String toString(){
		String retour = "";
		for(Right e: rights){
			retour += e+ "\n";
		}
		return retour;
	}
}
