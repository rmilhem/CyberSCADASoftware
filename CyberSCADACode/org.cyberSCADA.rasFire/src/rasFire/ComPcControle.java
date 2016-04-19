package rasFire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ComPcControle {
	//Objets liés à la communication avec le PC_controle
			ServerSocket s;
			Socket soc;
			BufferedReader reader;
			PrintWriter writer; 
			int port;
			
	public ComPcControle(int port){
		
		this.port = port;
		try {
			s = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void start(){
		try {
			System.out.println("En attente d'une connection avec une IHM");
		    soc = s.accept();
		    System.out.println("Une IHM est connectée");
		    State.getReference().setEnConnectionIHM(true);
			  // Un BufferedReader permet de lire par ligne.
	        reader = new BufferedReader(
	                               new InputStreamReader(soc.getInputStream())
	                              );

	        // Un PrintWriter possède toutes les opérations print classiques.
	        // En mode auto-flush, le tampon est vidé (flush) à l'appel de println.
	        writer = new PrintWriter(
	                             new BufferedWriter(
	                                new OutputStreamWriter(soc.getOutputStream())), 
	                             true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getMessage(){
		
		String messageRecu ="";
			try {
				messageRecu = reader.readLine();
				if(messageRecu == null){
					State.getReference().setEnConnectionIHM(false);
					
				}
				return messageRecu;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Connection avec le PC interrompu");
				State.getReference().setEnConnectionIHM(false);
	
			}
			return messageRecu;
	}
	
	
	public void sendMessage(String msgRecuRap){
		if(msgRecuRap!= null){
			writer.println(msgRecuRap);
			
		}
	}

	public void closeConnection(){
		
		try {
			reader.close();
			writer.close();
			soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
