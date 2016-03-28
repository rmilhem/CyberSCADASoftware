package pcControle.data;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Observable;

import pcControle.varAuto.VariableAuto;

public class AutomX extends AutomLecture{
	Com com;
	private Hashtable<VariableAuto, Integer> ht;
	// à migrer ailleurs peut être
	final String firewallIp = "172.20.27.1";
	final int port = 3333;
	
	// si com a réussi a ouvrir une connection, et donc si l'automate
	// est actuellement mis à jour
	
	boolean updated = false;
	
	public AutomX() {
		ht = new Hashtable<VariableAuto, Integer>();
		ht.put(VariableAuto.actionneurChuteBas, -1);
		ht.put(VariableAuto.actionneurChuteHaut, -1);
		ht.put(VariableAuto.capteurPresence, -1);
		ht.put(VariableAuto.moteurBalle, -1);
		ht.put(VariableAuto.running, -1);
		ht.put(VariableAuto.remplissage, -1);
		ht.put(VariableAuto.tournerPlateau, -1);
		
		// création de la capacité de communication de l'automate
			com = new Com(firewallIp, port, this);
			updated= com.connect();
		ThreadUpdateAuto threadUpdate = new ThreadUpdateAuto(this, com);
		threadUpdate.start();
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
