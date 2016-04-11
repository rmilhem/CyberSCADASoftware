
package rasFire;

import java.io.IOException;

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
					msgRecuRap = firewall.convModbus(msgRecuServeur);
					//System.out.println("message non filtré : "+msgRecuServeur+" : " +msgRecuRap);
					com.sendMessage(msgRecuRap);
				} else {
					 //System.out.println("Message venant du PC-Control FILTRE");
					com.sendMessage("");
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
