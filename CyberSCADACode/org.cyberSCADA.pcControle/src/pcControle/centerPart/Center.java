package pcControle.centerPart;
import java.util.Scanner;

import pcControle.ihm.console.IHMConsole;
import pcControle.ihm.console.IHMConsoleBuilder;
import pcControle.right.Monitor;

public class Center {
	Monitor monitor;
	Scanner sc;
	IHMConsoleBuilder ihmBuilder;
	IHMConsole ihm;
	
	public Center(){
		monitor = new Monitor();
		sc = new Scanner(System.in);
		ihm = new IHMConsole();
		ihmBuilder = new IHMConsoleBuilder(monitor.getRightBase());
				
	}

	
	public void identification() {
		System.out.println("*** IDENTIFICATION ***");
		String id = "";
		String password = "";
		boolean logedIn = false;
		
		while (!logedIn) {
			System.out.print("login : ");
			id = sc.next();
			System.out.print("password : ");
			password = sc.next();
			logedIn = monitor.identification(id, password);
			if(logedIn) System.out.println("Identification reussie.\n\n");
		}

	}
	
	public void startIHM(){
		ihm.addMonitor(monitor);
		ihmBuilder.build(ihm,  monitor.getUser().getRole());
	}
	public IHMConsole getIHM(){
		
		return ihm;
	}

	public Monitor getMonitor(){
		return monitor;
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Center center = new Center();
		if(center.getMonitor().isOk()){
		center.identification();
		center.startIHM();
		center.getIHM().display();
		
		while(center.getMonitor().isOk()){
	
			center.getIHM().waitOrder();
			center.getIHM().display();
			
		}
		System.out.println("Le programme s'est arrété de fonctionner");
		}
		
	}
}
