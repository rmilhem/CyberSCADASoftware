
package rasFire;

import java.io.IOException;
import java.util.Scanner;

public class MainFire {

	public static void main(String arg[]) {

		
		final int port = 3333;
		String msgRecuServeur = "";
		String msgRecuRap = "";
		Firewall firewall = new Firewall();
		ComPcControle com = new ComPcControle(port);
		Scanner keyBoard = new Scanner(System.in);
		
		boolean repUtil = false;

	while(State.getReference().isRunning()){
		com.start();
		while (State.getReference().isEnConnectionIHM()) {
			System.out.println("Lancer la connection avec les rapsberrys ?");
			msgRecuServeur = com.getMessage();
			repUtil = keyBoard.nextBoolean();
			while (repUtil && State.getReference().isEnConnectionIHM()) {

				pause(100);
			
				if (!firewall.filtrer(msgRecuServeur)) {
					msgRecuRap = firewall.convModbus(msgRecuServeur);
					//System.out.println("message non filtré : "+msgRecuServeur+" : " +msgRecuRap);
					com.sendMessage(msgRecuRap);
				} else {
					 //System.out.println("Message venant du PC-Control FILTRE");
					com.sendMessage("");
				}
				msgRecuServeur = com.getMessage();
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
