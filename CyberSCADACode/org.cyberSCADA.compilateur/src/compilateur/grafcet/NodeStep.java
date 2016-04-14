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
		this.start();
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(active.get()){
				System.out.println("start step : "+step.name);
				
				for(NodeTransition t : nextTransi){
					t.setActive(true);
				}
				while(active.get()){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				for(NodeTransition t : nextTransi){
					t.setActive(false);
				}

				//System.out.println("fin step : "+step.name);
			}
		}
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
