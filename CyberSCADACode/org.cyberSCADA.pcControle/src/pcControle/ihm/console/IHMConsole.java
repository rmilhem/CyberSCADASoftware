package pcControle.ihm.console;
import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

import pcControle.data.AutomLecture;
import pcControle.right.Monitor;
import pcControle.varAuto.VariableAuto;

public class IHMConsole {
	private Scanner sc;

	private Hashtable<Integer, String> tableMenuP;
	int i;
	private Hashtable<Integer, VariableAuto> tableEcriture;
	int j;
	private Hashtable<Integer, VariableAuto> tableLecture;
	int k;

	int currentMenu;
	int choix;
	int valEntree;

	private AutomLecture auto;
	private Monitor monitor;

	public IHMConsole() {
		sc = new Scanner(System.in);
		i = j = k = 1;
		currentMenu = 0;
		choix = 0;
		valEntree = -1;
		tableMenuP = new Hashtable<Integer, String>();
		tableEcriture = new Hashtable<Integer, VariableAuto>();
		tableLecture = new Hashtable<Integer, VariableAuto>();
	}

	public void addMonitor(Monitor monitor) {
		this.auto = monitor.getAutomate();
		this.monitor = monitor;
	}

	public void addChoixMP(String choix) {
		tableMenuP.put(i, choix);
		i++;
	}

	public void addChoixEcriture(VariableAuto choix) {
		tableEcriture.put(j, choix);
		j++;
	}

	public void addChoixLecture(VariableAuto choix) {
		tableLecture.put(k, choix);
		k++;
	}

	private String displayMenu(Hashtable<Integer, String> menu) {
		String contenu = "";
		for (Integer i : menu.keySet()) {
			contenu += i + "." + menu.get(i) + "\n";
		}
		return contenu;
	}
	private String displaySousMenu(Hashtable<Integer, VariableAuto> menu) {
		String contenu = "";
		for (Integer i : menu.keySet()) {
			contenu += i + "." + menu.get(i) + "\n";
		}
		return contenu;
	}

	public void display() {
		String contenu = "";
		if (currentMenu == 0) {
			contenu += "*** ECRAN DE CONTROLE ***\n";
			contenu += displayMenu(tableMenuP);
		} else if (currentMenu == 1 && choix == 0) {
			contenu += "*** Afficher variable ***\n";
			contenu += displaySousMenu(tableLecture);
		} else if (currentMenu == 2 && choix == 0) {
			contenu += "*** Ecriture de variable ***\n";
			contenu += displaySousMenu(tableEcriture);
		} else if (currentMenu == 1 && choix > 0) {

			contenu += auto.getVar(tableLecture.get(choix)) + "\n";

		} else if (currentMenu == 2 && choix > 0) {

			// si on a pas encore choisit la valeur de la variable
			if(valEntree ==-1){
			contenu += "Quelle valeur pour :" + tableEcriture.get(choix) + "?\n";
			}
			else{
				monitor.setVar(tableEcriture.get(choix), valEntree);
				choix =0;
				valEntree=-1;
				contenu += "*** Ecriture de variable ***\n";
				contenu += displaySousMenu(tableEcriture);
				
			}
		}

		contenu += "0.Retour\n";
		System.out.println(contenu);
	}

	public void waitOrder() {
		//System.out.println("Etat actuel des variables: CurrentMenu " + currentMenu+ " Choix "+ choix+" Valeur Entree "+ valEntree);
		System.out.print("Choix : ");
		String chx = sc.next();
		int valTemp;
		if (currentMenu == 0) {
			// on est sur le menu d'acceuil
			try {
				valTemp = Integer.parseInt(chx);
				currentMenu = valTemp;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Rentrez un chiffre !");
			}
				
		} else if (currentMenu == 1) {
			try {
				valTemp = Integer.parseInt(chx);
				// on vient d'arriver sur le sous menu
				if(choix ==0){
				choix = valTemp;
				if(valTemp ==0) currentMenu =0;
				}
				// on a lu la variable
				else if(choix >0){
					if(valTemp ==0) choix=0;
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Rentrez un chiffre !");
			}
				

		} else if (currentMenu == 2) {
			try {
				valTemp = Integer.parseInt(chx);
				// on vient d'arriver sur le sous menu
				if(choix ==0){
				choix = valTemp;
				if(valTemp ==0) currentMenu =0;
				}
				// on choisit la valeur de la variable
				
				else if(choix >0){
					valEntree = valTemp;
					
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Rentrez un chiffre !");
			}
		} else {
			System.out.println("Wrong");
		}

	}
}
