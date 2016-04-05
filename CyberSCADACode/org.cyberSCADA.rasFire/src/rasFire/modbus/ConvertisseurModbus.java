package rasFire.modbus;

import rasFire.varAuto.VariableAuto;

public class ConvertisseurModbus {
	MasterModbus master;
	String adresseRasp1;
	String adresseRasp2;
	String adresseRasp3;
	
	public ConvertisseurModbus(){
		master = new MasterModbus();
		adresseRasp1 = "127.0.1.1:4441";
		adresseRasp2 = "127.0.1.1:4442";
		adresseRasp3 = "127.0.1.1:4443";
	}
	
	public String convModbus(String msg) {
		String[] split = msg.split(" ");
		String mode = split[0];
		String variable = split[1];
		String valeur = split[2];
		Boolean registre = false;
		String adresse = "";
		String reference = "";
		String reponse = "";
		
		String[] temp = getEquivalence(VariableAuto.valueOf(variable), registre);
		adresse = temp[0];
		reference = temp[1];
		
		/********************************** Partie à compléter pour gérer les Input **********************************/
		// un decalage de un avec les adresses réelles sur la raspberry, du au drapeau
		reference = String.valueOf((Integer.parseInt(reference) + 1));
		
		// si on souhaite lire une valeur
		if (mode.equals("0")) {
		reponse = lireValeur(registre, adresse, reference);
		}

		// si on souhaite écrire une valeur
		else if (mode.equals("1")) {
			ecrireValeur(valeur, registre, reference, adresse);
		}
		//conversion de la réponse
		reponse = convReponse(reponse, variable);
		return reponse;
	}

	/**
	 * 
	 * @param variable
	 * @return adresse et référence et change la valeur de registre
	 */
	private String[] getEquivalence(VariableAuto variable, Boolean registre) {
		// (à synthetiser) décalage de 1 a cause du flag
		String[] retour = new String[2];
		switch (variable) {
		case presenceTubeBalle:
			retour[0] = adresseRasp1;
			retour[1] = "0";
			break;
		case actionneurChuteHaut:
			retour[0] = adresseRasp1;
			retour[1] = "1";
			break;
		case actionneurChuteBas:
			retour[0] = adresseRasp1;
			retour[1] = "2";
			break;
		case moteurBalle:
			retour[0] = adresseRasp1;
			retour[1] = "3";
			break;
		case remplissage:
			retour[0] = adresseRasp1;
			retour[1] = "4";
			break;
		case running:
			retour[0] = adresseRasp1;
			retour[1] = "5";
			break;
		case tournerPlateau:
			retour[0] = adresseRasp1;
			retour[1] = "6";
			break;
		case bouchonner:
			retour[0] = adresseRasp2;
			retour[1] = "0";
			break;
		case capteurBouchons:
			retour[0] = adresseRasp2;
			retour[1] = "1";
			break;
		case presenceTubeBouchons:
			retour[0] = adresseRasp2;
			retour[1] = "2";
			break;
		case capteurPresence:
			retour[0] = adresseRasp3;
			retour[1] = "0";
			break;
		case actionPinces:
			retour[0] = adresseRasp3;
			retour[1] = "1";
			break;
		case stock_tube:
			retour[0] = adresseRasp3;
			retour[1] = "0";
			break;
		/*case "3":
			retour[0] = adresseRasp1;
			retour[1] = "3";
			registre = true;
			break;*/
		/*
		 * case "4": adresse = ; reference = ; break; case "5": adresse = ;
		 * reference = ; break; case "6": adresse = ; reference = ; break; case
		 * "7": adresse = ; reference = ; break; case "8": adresse = ; reference
		 * = ; break; case "9": adresse = ; reference = ; break;
		 */

		}
		return retour;
	}

	private String lireValeur(boolean registre, String adresse, String reference) {
		

			if (!registre && !adresse.equals("")){
				return master.ReadCoil(adresse, reference, "1");
			}
			if (registre && !adresse.equals(""))
				return  master.ReadRegister(adresse, reference, "1");

			return "";
		
	}
	private void ecrireValeur(String valeur, Boolean registre, String reference, String adresse){
		// conversion de la valeur
		boolean valeurBool = false;
					if (valeur.equals("0")) {
						valeurBool = false;
					} else if (valeur.equals("1")) {
						valeurBool = true;
					}
					// on met le flag à true
					master.WriteCoil(adresse, "0", true);
					if (!registre) {
						if (!adresse.equals(""))
							 master.WriteCoil(adresse, reference, valeurBool);
					} else {
						if (!adresse.equals("")) 
							 master.WriteRegister(adresse, reference, Integer.parseInt(valeur));
					}
	}
	
	private String convReponse(String reponse, String variable){
		// Ne marche que pour des valeurs <= 99
				if(!reponse.equals("")){
					int taille = reponse.length();
					if(reponse.substring(taille-2, taille-2).equals("0"))
					reponse = reponse.substring(taille-1, taille-1);
					else reponse = reponse.substring(taille-2, taille-1);
					//System.out.println(variable+" "+reponse);
					return (variable+" "+reponse);
				}
				return "";
	}
}
