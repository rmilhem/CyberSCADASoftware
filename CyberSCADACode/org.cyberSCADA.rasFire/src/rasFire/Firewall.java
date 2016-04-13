package rasFire;

import rasFire.modbus.ConvertisseurModbus;
import rasFire.rule.RuleBase;
import rasFire.varAuto.VariableAuto;

public class Firewall {

	RuleBase ruleBase;
	ConvertisseurModbus convertisseurModbus;

	public Firewall() {
		
		ruleBase = new RuleBase();
		convertisseurModbus =  new ConvertisseurModbus();
		// configuration des régles
		// les droits en écriture
		ruleBase.addRule(true, VariableAuto.actionneurChuteHaut);
		ruleBase.addRule(true, VariableAuto.actionneurChuteBas);
		ruleBase.addRule(true, VariableAuto.moteurBalle);
		ruleBase.addRule(true, VariableAuto.running);
		ruleBase.addRule(true, VariableAuto.remplissage);
		ruleBase.addRule(true, VariableAuto.tournerPlateau);
		// les droits en lecture
		ruleBase.addRule(false, VariableAuto.presenceTubeBalle);
		ruleBase.addRule(false, VariableAuto.actionneurChuteHaut);
		ruleBase.addRule(false, VariableAuto.actionneurChuteBas);
		ruleBase.addRule(false, VariableAuto.moteurBalle);
		ruleBase.addRule(false, VariableAuto.capteurBouchons);
		ruleBase.addRule(false, VariableAuto.stock_tube);
		ruleBase.addRule(false, VariableAuto.presenceTubeBouchons);
		ruleBase.addRule(false, VariableAuto.running);
		ruleBase.addRule(false, VariableAuto.remplissage);
		ruleBase.addRule(false, VariableAuto.tournerPlateau);
		ruleBase.addRule(false, VariableAuto.bouchonner);
		ruleBase.addRule(false, VariableAuto.actionPinces);
	}

	// renvoie si le message a été filtré ou non
	public boolean filtrer(String msg) {

		if(msg != null){
		String modeTemp = msg.split(" ")[0];
		boolean mode = false;
		if (modeTemp.equals("0"))
			mode = false;
		else if (modeTemp.equals("1")) {
			mode = true;
		} else
			modeTemp = "invalide";
		String variable = msg.split(" ")[1];
		// System.out.println("Firewall: variable recupérée de PC Controle "
		// +variable);
		if (!modeTemp.equals("invalide")) {
			boolean filtre = !ruleBase.isCorrect(mode, VariableAuto.valueOf(variable));
			return filtre;
		} else
			return true;}
		else return false;
	}

	public String convModbus(String msg){
		return convertisseurModbus.convModbus(msg);
	}

}
