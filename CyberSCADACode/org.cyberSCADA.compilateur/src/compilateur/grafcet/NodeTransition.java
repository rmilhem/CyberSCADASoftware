package compilateur.grafcet;

public class NodeTransition extends NodeComposant{

	private Transition transi;
	
	protected NodeStep nextStep[];
	protected NodeStep prevStep[];

	public NodeTransition(String name){
		this.transi = new Transition(name);
		nextStep = null;
		prevStep = null;
		this.start();
	}
	/**
	 * A transition is always running
	 * but it is waiting for the condition only when it is "active"
	 * At the end, it activate its next steps and disactivate its previous steps
	 */
	public void run(){
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(active.get()){
				System.out.println("start transi : "+transi.name);
				while(!transi.isTrue() && active.get()){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if(active.get()){
					for(NodeStep p : nextStep){
						p.setActive(true);
					}
					for(NodeStep p : prevStep){
						p.setActive(false);
					}
				}
				active.set(false);
				//System.out.println("fin transi : "+transi.name);
			}
		}
	}
	
	public Transition getTransition(){
		return transi;
	}

	public NodeStep[] getPrevStep(){
		return prevStep;
	}

	public NodeStep[] getNextStep(){
		return nextStep;
	}

	public void setPrevStep(NodeStep prev[]){
		this.prevStep = prev;
	}

	public void setNextStep(NodeStep next[]){
		this.nextStep = next;
	}

	public String toString(){
		return transi.getName();
	}
}
