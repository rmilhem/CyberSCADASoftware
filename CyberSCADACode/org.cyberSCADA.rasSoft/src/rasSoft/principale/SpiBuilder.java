package rasSoft.principale;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;

public class SpiBuilder {

	public void build(SimpleProcessImage spi){
		int numAutomate = 1;
		// on rajoute le flag
				//false la modifification de l'automate a été prise en compte
				// true l'automate a été modifié, et la modif n'a pas été prise en compte
				spi.addDigitalOut(new SimpleDigitalOut(false));

				if(numAutomate == 1){

					// presence tube remplissage 1
					spi.addDigitalOut(new SimpleDigitalOut(false));

					// Actionneur chute
					// haut :2
					spi.addDigitalOut(new SimpleDigitalOut(false));
					// bas : 3
					spi.addDigitalOut(new SimpleDigitalOut(false));

					// Moteur Balle 4
					spi.addDigitalOut(new SimpleDigitalOut(false));

					// remplirTube 5
					spi.addDigitalOut(new SimpleDigitalOut(false));

					// Running 6
					spi.addDigitalOut(new SimpleDigitalOut(false));

					// Tourner Plateau 7
					spi.addDigitalOut(new SimpleDigitalOut(false));

				} if (numAutomate == 2) {
					
					// presence bouchon 1
					spi.addDigitalOut(new SimpleDigitalOut(false));

					// presence tube bouchage 2
					spi.addDigitalOut(new SimpleDigitalOut(false));

				} if (numAutomate == 3) {

					// presence tube pinces 1
					spi.addDigitalOut(new SimpleDigitalOut(false));

				

					// est ce utile ?
					spi.addRegister(new SimpleRegister(0));
				}
				  //3. Set the image on the coupler
				  // Attention ici ModbusCoupler cr�e une copie de spi !!!
				  // on utilisera donc par la suite ModbusCoupler.getReference().getProcessImage() plut�t que spi
				  ModbusCoupler.getReference().setProcessImage(spi);
				  ModbusCoupler.getReference().setMaster(false);
				  //ModbusCoupler.getReference().setUnitID(15);
	}
}
