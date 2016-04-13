package compilateur.grafcet;


public class NodeStep extends NodeComposant{

	private Step step;
	
	public NodeStep(String name, int ... id){
		step = new Step(name);
		
		nextStep = null;
		prevStep = null;
		nextTransi = null;
		prevTransi = null;
		if(id.length == 1)
			this.id = id[0];
		else
			this.id = 0;
	}
	
	public void run(){
		System.out.println("start step : "+step.name);
		
		active.set(true);
		this.interrupt();
	}
	
	
	public Bool getActive(){
		return active;
	}
	
	public boolean isActive(){
		return active.get();
	}
	
	public Step getStep(){
		return step;
	}
	
	public NodeTransition[] getPrevTransition(){
		return prevTransi;
	}
	
	public NodeTransition[] getNextTransition(){
		return nextTransi;
	}
	
	public NodeStep[] getPrevStep(){
		return prevStep;
	}
	
	public NodeStep[] getNextStep(){
		return nextStep;
	}
	
	public void setPrevTransition(NodeTransition prev[]){
		this.prevTransi = prev;
	}
	
	public void setNextTransition(NodeTransition next[]){
		this.nextTransi = next;
	}
	
	public void setPrevStep(NodeStep prev[]){
		this.prevStep = prev;
	}
	
	public void setNextStep(NodeStep next[]){
		this.nextStep = next;
	}
	
	public String toString(){
		return step.getName();
	}
}
