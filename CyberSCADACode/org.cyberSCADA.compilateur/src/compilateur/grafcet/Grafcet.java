package compilateur.grafcet;

public class Grafcet extends Thread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		variable = new Variable();
		
		Grafcet g = new Grafcet(new Step("step1"));
		controller = new Controller(g);
		
		variable.condition.put("s_2_2.x", new Bool(false));
		variable.condition.put("index", new Bool(false));
		variable.condition.put("auto", new Bool(false));
		
		g.addTransition(new Transition(controller, "tr1"));		
		g.getNodeTransition("tr1").getTransi().setCondition((Bool)variable.condition.get("s_2_2.x"));
		
		g.addStep(new Step("step2"));
		g.addTransition(new Transition(controller, "tr2"));
		g.addStep(new Step("step3"));
		g.addTransition(new Transition(controller, "tr3"));
		g.addFinalStep(new Step("step4"));
		g.addFinalTransition(new Transition(controller, "tr4"));
		
	
		NodeStep tt = g.getNodeStep("step4");
		if(tt != null){System.out.println("tr1 ok");}
		System.out.println(g.getNodeTransition("tr1").getTransi());
		
		//System.out.println(g.getLastTransition());
		//g.start();
		
		
	}
	
	private NodeStep firstStep;
	private NodeStep tmpStep;
	private NodeTransition firstTransi;
	private NodeTransition tmpTransi;
	private NodeStep currentStep;
	private NodeTransition currentTransition;
	private static Variable variable;
	private static Controller controller;
	private boolean running = true;
	
	
	public Grafcet(Step n){
		
		firstStep = new NodeStep(n);
		firstStep.getStep().setInitial(true);
		
	}
	
	public void run(){
		firstStep.getStep().start();
		currentStep = firstStep;
		firstTransi.getTransi().start();
		currentTransition = firstTransi;
		while(running){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			((Bool)variable.condition.get("s_2_2.x")).set(true);
		}
	}
	
	public void next(){
		currentStep = currentStep.getNextStep();
		currentTransition = currentTransition.getNextTransition();
		System.out.println("current : "+currentStep+" | "+currentTransition);
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
			firstTransi.getTransi().setInitial(true);
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
		
		do {
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
		}while(tmpStep != getLastStep() && b);
		return null;
	}
	public NodeTransition getNodeTransition(String name){
		tmpTransi = firstTransi;
		boolean b = true;
				
		do {
			if(tmpTransi.getTransi().name.equals(name)){
				return tmpTransi;
			}
			else{
				tmpTransi = tmpTransi.getNextTransition();
				if(tmpTransi.getTransi().isInitial()){
					b = false;
				}
				else if(tmpTransi.getTransi().name.equals(name)){
					return tmpTransi;
				}
			}
		}while(tmpTransi != getLastTransition() && b);
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
		boolean b = true;
		while(tmp.getNextTransition() != null && b)
		{
			tmp = tmp.getNextTransition();
			if(tmp == firstTransi){
				b = false;
				tmp = tmp.getPrevTransition();
			}
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
