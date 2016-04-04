package rasSoft.principale;
import java.util.Random;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;
import rasSoft.test.AutomPhy;
import rasSoft.updater.UpdaterAuto;
import rasSoft.updater.UpdaterAutoReel;
import rasSoft.updater.UpdaterAutoTest;

public class Manager {
	AutomPhy automPhy;
	int numAutomate;
	UpdaterAuto updater;
	ThreadPrincipal threadPrincipal;
	
	public Manager(int numAutomate, boolean virtuel){
		this.numAutomate = numAutomate;
		if(virtuel){
			updater = new UpdaterAutoTest(numAutomate, true);
		}
		else{
			threadPrincipal = new ThreadPrincipal();
			updater = new UpdaterAutoReel();
		}
		SpiBuilder spiBuilder = new SpiBuilder();
		// l'automate virtuel du serveur 
		SimpleProcessImage spi = new SimpleProcessImage();	
		
		spiBuilder.build(spi);
		
		automPhy = updater.getAutomPhy();
		
	}

	public void update(){
		
		
		boolean flag = ModbusCoupler.getReference().getProcessImage().getDigitalOut(0).isSet();
		// si le flag n'est pas mis, tous les chgts venant du pc-control ont été pris en compte.
		if(!flag){
			updater.miseAJourAutomateV(numAutomate, threadPrincipal);
			
		}
		// sinon on doit mettre à jour l'automate physique
		else{
			
			updater.miseAJourAutomatePhy(numAutomate, threadPrincipal);
			updater.miseAJourAutomateV(numAutomate, threadPrincipal);			
		}
		
	}
	
	public void display(){
		// affichage de l'automate physique
		System.out.println(automPhy);
		
		// affichage de l'automate virtuel 
		System.out.println("----------------Automate virtuel (debut)-------------------");
		if(numAutomate == 1){
			System.out.println("Capteur presence: "+  ModbusCoupler.getReference().getProcessImage().getDigitalOut(1).isSet());
			System.out.println("Actionneur chute Haut: "+  ModbusCoupler.getReference().getProcessImage().getDigitalOut(2).isSet());
			System.out.println("Actionneur chute Bas: "+  ModbusCoupler.getReference().getProcessImage().getDigitalOut(3).isSet());
			System.out.println("Moteur Balle: "+  ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).isSet());
			System.out.println("Running : "+  ModbusCoupler.getReference().getProcessImage().getDigitalOut(6).isSet());
			System.out.println("Turning : "+  ModbusCoupler.getReference().getProcessImage().getDigitalOut(7).isSet());
			//System.out.println("registre (inutilisé): "+  ModbusCoupler.getReference().getProcessImage().getRegister(0).getValue());
			
		}
		System.out.println("---------------Automate virtuel (fin) -------------------");
	}
}
