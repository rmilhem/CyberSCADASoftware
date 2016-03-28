package rasFire.rule;

import java.util.Vector;

import rasFire.varAuto.VariableAuto;

public class RuleBase {
	private Vector<Rule> rb;
	
	public RuleBase(){
		rb = new Vector<Rule>();
	}
	
	
	public void addRule(boolean md, VariableAuto var) {
		Rule r = new Rule(md, var);
		rb.addElement(r);
	}
	
	public boolean isCorrect(boolean md, VariableAuto var) {
		for (Rule r: rb) {
			if (r.isCorrect(md, var)) {
				return true;
			}
		}
		return false;
	}
}
