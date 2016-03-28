
package rasFire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainFire {

	public static void main(String arg[]) {

		boolean enConnection = true;
		boolean firewallRunning = true;
		final int port = 3333;
		String msgRecuServeur = "";
		String msgRecuRap = "";
		

		Firewall firewall = new Firewall();
		ComPcControle com = new ComPcControle(port);

		while (firewallRunning) {

			com.start();
			System.out.println("Un Pc-Control s'est connecté");
			while (enConnection) {

				pause(100);
				try {
					msgRecuServeur = com.getMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					enConnection = false;
					System.out.println("Un Pc-Control s'est déconnecté");
				}
				if (msgRecuServeur != null && !firewall.filtrer(msgRecuServeur)) {
					// System.out.println("message non filtré");
					msgRecuRap = firewall.convModbus(msgRecuServeur);
					com.sendMessage(msgRecuRap);
				} else {
					// System.out.println("Message venant du PC-Control
					// FILTRE");
				}
			
			
			}
			com.closeConnection();
		}

		

	}

	private static void pause(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
