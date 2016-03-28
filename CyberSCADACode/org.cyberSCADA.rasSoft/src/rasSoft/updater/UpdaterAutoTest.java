package rasSoft.updater;

import net.wimpi.modbus.ModbusCoupler;
import rasSoft.principale.ThreadPrincipal;
import rasSoft.test.AutomPhy;

public class UpdaterAutoTest implements UpdaterAuto {
	AutomPhy automPhy;

	public UpdaterAutoTest(int numAutomate, boolean automateActif) {
		automPhy = new AutomPhy(numAutomate,automateActif);

	}

	@Override
	public void miseAJourAutomatePhy(int numAutomate, ThreadPrincipal threadPrincipal) {
		if(automPhy.getNum() == 1){
			for(int i =0; i<6; i++){	
				automPhy.setCoil(i, ModbusCoupler.getReference().getProcessImage().getDigitalOut(i+1).isSet());	
				// cas particulier
				if(true);
				}
			automPhy.setReg(0,ModbusCoupler.getReference().getProcessImage().getRegister(0).getValue());
		}
	// on remet le flag à false 
		ModbusCoupler.getReference().getProcessImage().getDigitalOut(0).set(false);
	}

	@Override
	
	// simulation avec une classe physique virtuel
	public void miseAJourAutomateV(int numAutomate, ThreadPrincipal threadPrincipal) {
		boolean coilTemp;
		int regTemp;
		boolean[] retour;

		// mise à jour de l'automate physique virtuel
		automPhy.nextStep();

		if (automPhy.getNum() == 1) {
			// lecture des variables de l'automate physique virtuel
			// lecture des coils
			for (int i = 0; i < 3; i++) {
				retour = automPhy.getCoil(i);
				if (retour[0]) {
					coilTemp = retour[1];
					// écriture dans automate virtuel
					ModbusCoupler.getReference().getProcessImage().getDigitalOut(i + 1).set(coilTemp);
				}
			}
			// lecture des registres
			regTemp = automPhy.getReg(0);
			// écriture dans automate virtuel
			if (regTemp != -1)
				ModbusCoupler.getReference().getProcessImage().getRegister(0).setValue(regTemp);
		}

	}

}
