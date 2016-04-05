package rasSoft.principale;
import java.util.Scanner;


public class Main {

	
	
	public static void main(String args[]){
		Manager manager;
		Scanner sc = new Scanner(System.in);
		SlaveModbus slave;
		int flag;
		long date1;
		
		boolean virtuel;
		int numeroAutomate;
		
		System.out.println("Go ?");
		boolean running = sc.nextBoolean();
		// Démarrage du serveur
		if(running){
			System.out.println("Quel automate cet raspberry doit gérer (quel numero) ?");
			numeroAutomate = sc.nextInt();
			slave = new SlaveModbus(Integer.valueOf("444"+numeroAutomate));
			System.out.println("Voulez-vous fonctionner en mode virtuel pour un test (true) ou réel(false) ?");
			virtuel = sc.nextBoolean();
			manager = new Manager(numeroAutomate, virtuel);
		
		
			
			date1 = System.currentTimeMillis();
		while(running){
			
			// Pause de 10 ms
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			manager.update();
			
			// affichage de l'état de l'automate physique et de l'automate virtuel
			
			if(System.currentTimeMillis() - date1> 1000){
				manager.display();
				date1 = System.currentTimeMillis();
			}
			
			
		}
		
		}
		
	}
}
