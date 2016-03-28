package pcControle.data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;

import pcControle.varAuto.VariableAuto;

public class Com {
	AutomX automate;
	Socket soc; 
	BufferedReader reader;
	PrintWriter writer;
	
	String firewallIp;
	int port;
	
	boolean socketConnecte;
	
	public Com(String firewallIp, int port, AutomX automate) {
		this.automate = automate;
		this.firewallIp = firewallIp;
		this.port = port;
		socketConnecte = true;
	}
	
	public boolean connect(){
		try {
			 soc = new Socket(firewallIp, port);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Firewall unreachable");
			socketConnecte =false;
		}
		if(socketConnecte){
		// Un BufferedReader permet de lire par ligne.
		try {
			reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Un PrintWriter possède toutes les opérations print classiques.
		// En mode auto-flush, le tampon est vidé (flush) à l'appel de println.
		try {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return socketConnecte;
	}
	
	public void updateAutomate(){
		// ouverture d'une socket avec le firewall

		
		String msgRecu = "";
		String[] msgRecus;

		while (socketConnecte) {

			msgRecu = "";
		
			// on récupére les variables de l'automate pour les mettres à
			// jour ensuite
			Hashtable<VariableAuto, Integer> variables = automate.getVariables();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// pour chaque variable
			for (VariableAuto var : variables.keySet()) {
				// demander de lecture
				
				 sendOrder(false, var, -1);
				// Traitement de la réponse
				try {
					
					msgRecu = reader.readLine();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					socketConnecte = false;
				}
				
				// lecture de la réponse (est on sur que c'est bien la réponse que nous attendions ?)
				// justement non 
				if (msgRecu != null && !msgRecu.equals("")) {
					msgRecus = msgRecu.split(" ");
					// mise à jour de la variable
					variables.put(VariableAuto.valueOf(msgRecus[0]), Integer.parseInt(msgRecus[1]));
				}
			}
			automate.aChange();
		}
		System.out.println("Connection perdu");
		
	}
	public synchronized void sendOrder(boolean ecriture, VariableAuto nomVar, int val){
		// construction de la demande
		// -1 valeur par défaut pour la lecture
		String msgDemande = "";
		if(!ecriture) msgDemande += "0";
		else msgDemande += "1";
		msgDemande += " "+ nomVar+" "+ val;
		writer.println(msgDemande);
	}
	
	public void finalize() throws IOException{
		reader.close();
		writer.close();
		soc.close();
	}
}
