package rasFire.rule;

import rasFire.varAuto.VariableAuto;

public class Rule {

	private boolean mode;
	private VariableAuto id_var;
	
	public Rule(boolean md, VariableAuto var) {
	
		mode = md;
		id_var = var;
	}
	
	public boolean isCorrect(boolean md, VariableAuto var) {
		
		return (md == this.mode && var == this.id_var);
	}
}
