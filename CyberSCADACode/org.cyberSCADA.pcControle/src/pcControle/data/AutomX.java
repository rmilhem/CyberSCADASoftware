package pcControle.data;
import java.util.Hashtable;

import pcControle.varAuto.VariableAuto;

public class AutomX extends AutomLecture{
	Com com;
	private Hashtable<VariableAuto, Integer> ht;
	// à migrer ailleurs peut être
	final String firewallIp = "127.0.1.1";
	final int port = 3333;
	
	// si com a réussi a ouvrir une connection, et donc si l'automate
	// est actuellement mis à jour
	
	boolean updated = false;
	
	public AutomX() {
		ht = new Hashtable<VariableAuto, Integer>();
		ht.put(VariableAuto.actionneurChuteBas, -1);
		ht.put(VariableAuto.actionneurChuteHaut, -1);
		ht.put(VariableAuto.presenceTubeBalle, -1);
		ht.put(VariableAuto.moteurBalle, -1);
		ht.put(VariableAuto.running, -1);
		ht.put(VariableAuto.remplissage, -1);
		ht.put(VariableAuto.tournerPlateau, -1);
		ht.put(VariableAuto.presenceTubeBouchons, -1);
		ht.put(VariableAuto.capteurBouchons, -1);
		ht.put(VariableAuto.actionPinces, -1);
		ht.put(VariableAuto.bouchonner, -1);
		ht.put(VariableAuto.stock_tube, -1);
		
		// création de la capacité de communication de l'automate
			com = new Com(firewallIp, port, this);
		
		ThreadUpdateAuto threadUpdate = new ThreadUpdateAuto(this, com);
		threadUpdate.start();
		updated = com.isSocketConnecte();
	}
	public boolean isUpdated(){
		return updated;
	}
	public void setVar(VariableAuto nomVar, int val) {
		 com.sendOrder(true, nomVar, val);
		 setChanged();
		notifyObservers();
	}
	public void aChange(){
		 setChanged();
		notifyObservers();
	}
	
	public Hashtable<VariableAuto, Integer> getVariables(){
		return ht;
	}
	
	public int getVar(VariableAuto nomVar){
		if(ht.containsKey(nomVar)) return ht.get(nomVar);
		return -1;
	}
	
	
}
