package rasSoft.updater;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import rasSoft.principale.ThreadPrincipal;

public class UpdaterAutoReel implements UpdaterAuto {

	public UpdaterAutoReel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	// A simplifier (avec des sous fonctions), créer des fonctions génériques
	// dans grafcet pour modifier les variables
	public void miseAJourAutomatePhy(int numAutomate, ThreadPrincipal threadPrincipal) {

		if (numAutomate == 1) {

			// l'actionneur haut
			threadPrincipal.getGrafcet()
					.setActionneurHaut(ModbusCoupler.getReference().getProcessImage().getDigitalOut(2).isSet());
			// l'actionneur bas
			threadPrincipal.getGrafcet()
					.setActionneurBas(ModbusCoupler.getReference().getProcessImage().getDigitalOut(3).isSet());
			// Moteur des balles
			threadPrincipal.getGrafcet()
					.setMoteurBalle(ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).isSet());
			// remplir tube
			threadPrincipal.getGrafcet()
					.setDepart_remplissage(ModbusCoupler.getReference().getProcessImage().getDigitalOut(6).isSet());
			// tourner plateau
			threadPrincipal.getGrafcet()
					.setRotation_moteur(ModbusCoupler.getReference().getProcessImage().getDigitalOut(7).isSet());

			// si l'automate doit commencer à tourner, et qu'il n'est pas en
			// train de tourner
			if (ModbusCoupler.getReference().getProcessImage().getDigitalOut(5).isSet()) {
				// Gestion du fonctionnement l'automate
				// (synchronisation des "3" Automates )
				threadPrincipal.start();

			}
			// si l'automate doit s'arrêter, et qu'il est en train de tourner
			if (!ModbusCoupler.getReference().getProcessImage().getDigitalOut(5).isSet() && threadPrincipal.isAlive()) {
				// TODO : Changer la façon dont on arrête ce thread
				threadPrincipal.stop();
				threadPrincipal.getGrafcet().stop();
			}
		}
		// on remet le flag à false
		ModbusCoupler.getReference().getProcessImage().getDigitalOut(0).set(false);
	}

	@Override
	public void miseAJourAutomateV(int numAutomate, ThreadPrincipal threadPrincipal) {
		boolean coilTemp;
		int regTemp;
		boolean[] retour;

		// MEET IN THE MIDDLE !!!
		// mode réel
		if (numAutomate == 1) {
			// capteur de presence du tube
			ModbusCoupler.getReference().getProcessImage().getDigitalOut(1).set(threadPrincipal.getGrafcet().isPresence_tube_remplissage());
			// Actionneur haut
			ModbusCoupler.getReference().getProcessImage().getDigitalOut(2).set(threadPrincipal.getGrafcet().isActionneurHaut());
			// Actionneur bas
			ModbusCoupler.getReference().getProcessImage().getDigitalOut(3).set(threadPrincipal.getGrafcet().isActionneurBas());
			// Moteur Balle
			ModbusCoupler.getReference().getProcessImage().getDigitalOut(4).set(threadPrincipal.getGrafcet().isMoteurBalle());
			// RemplirTube
			ModbusCoupler.getReference().getProcessImage().getDigitalOut(6).set(threadPrincipal.getGrafcet().isRemplissage());
			// Tourner Plateau
			ModbusCoupler.getReference().getProcessImage().getDigitalOut(7).set(threadPrincipal.getGrafcet().isRotation_moteur());
			// Running


		}

	}

}
