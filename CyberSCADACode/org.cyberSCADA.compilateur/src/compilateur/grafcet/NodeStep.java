package compilateur.grafcet;


public class NodeStep extends NodeComposant{

	private Step step;
	
	protected NodeTransition nextTransi[];
	protected NodeTransition prevTransi[];
	
	public NodeStep(String name){
		step = new Step(name);
		
		nextTransi = null;
		prevTransi = null;
		
		this.start();
	}
	
	/**
	 * A step is always running
	 * but it is waiting for the condition only when it is "active"
	 * At the end, it disactivate its next transitions
	 */
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
	
	public void setPrevTransition(NodeTransition prev[]){
		this.prevTransi = prev;
	}
	
	public void setNextTransition(NodeTransition next[]){
		this.nextTransi = next;
	}
	
	public String toString(){
		return step.getName();
	}
}
