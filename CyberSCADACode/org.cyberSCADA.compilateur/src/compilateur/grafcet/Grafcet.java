package compilateur.grafcet;

public class Grafcet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafcet g = new Grafcet(new Step("step1"));

		g.addTransition(new Transition("tr1"));
		g.addStep(new Step("step2"));
		g.addTransition(new Transition("tr2"));
		g.addStep(new Step("step3"));
		g.addTransition(new Transition("tr3"));
		g.addFinalStep(new Step("step4"));
		g.addFinalTransition(new Transition("tr4"));
		
		//System.out.println(g);
		
		System.out.println(g.getLastStep());
	}
	
	private NodeStep firstStep;
	private NodeStep tmpStep;
	private NodeTransition firstTransi;
	private NodeTransition tmpTransi;
	
	
	public Grafcet(Step n){
		
		firstStep = new NodeStep(n);
		firstStep.getStep().setInitial(true);
		
	}
	
	/******************** Méthode d'ajout ***********************/
	public void addStep(Step step){
		NodeStep node = new NodeStep(step);
		NodeStep tmp = getLastStep();
		tmp.setNextStep(node);
		node.setPrevStep(tmp);
	}
	public void addFinalStep(Step step){
		NodeStep node = new NodeStep(step);
		NodeStep tmp = getLastStep();
		tmp.setNextStep(node);
		node.setPrevStep(tmp);
		node.setNextStep(firstStep);
		firstStep.setPrevStep(node);
	}
	public void addTransition(Transition transition){
		if(firstTransi == null){
			firstTransi = new NodeTransition(transition);
		}
		else{
			NodeTransition transi = new NodeTransition(transition);
			NodeTransition tmp = getLastTransition();
			tmp.setNextTransition(transi);
			transi.setPrevTransition(tmp);
		}
	}
	public void addFinalTransition(Transition transition){
		if(firstTransi == null){}
		else{
			NodeTransition transi = new NodeTransition(transition);
			NodeTransition tmp = getLastTransition();
			tmp.setNextTransition(transi);
			transi.setPrevTransition(tmp);
			transi.setNextTransition(firstTransi);
			firstTransi.setPrevTransition(transi);
		}
	}	
	
	/******************** Méthode get ***********************/
	public NodeStep getNodeStep(String name){
		tmpStep = firstStep;
		boolean b = true;
		
		while(tmpStep != getLastStep() && b){
			if(tmpStep.getStep().name.equals(name)){
				return tmpStep;
			}
			else{
				tmpStep = tmpStep.getNextStep();
				if(tmpStep.getStep().isInitial()){
					b = false;
				}
				else if(tmpStep.getStep().name.equals(name)){
					return tmpStep;
				}
			}
		}
		return null;
	}
	
	public NodeStep getLastStep(){
		NodeStep tmp = firstStep;
		boolean b = true;
		while(tmp.getNextStep() != null && b)
		{
			tmp = tmp.getNextStep();
			if(tmp.getStep().isInitial()){
				b = false;
				tmp = tmp.getPrevStep();
			}
		}
		return tmp;
	}
	public NodeTransition getLastTransition(){
		NodeTransition tmp = firstTransi;
		while(tmp.getNextTransition() != null)
		{
			tmp = tmp.getNextTransition();
		}
		return tmp;
	}
	
	/******************** Méthode set ***********************/
	public void setFirstStep(NodeStep first){
		this.firstStep = first;
	}
	public NodeStep getFirstStep(){
		return firstStep;
	}
	
	/******************** Méthode d'affichage ***********************/
	public String toString(){
		NodeStep tmp = firstStep;
		NodeTransition tmp2 = firstTransi;
		boolean b = true;
		String s = "";
		do
		{
			s += tmp+"\n";
			tmp = tmp.getNextStep();
			s += tmp2+"\n";
			tmp2 = tmp2.getNextTransition();
			if(tmp != null && tmp.getNextStep() == null){
				s += tmp+"\n";
				if(tmp2 != null){
					s += tmp2+"\n";
				}
			}
		}while(tmp.getStep().isInitial() != true && tmp.getNextStep() != null);
		
		return s;
	}

}
