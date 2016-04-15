package compilateur.grafcet;

public class NodeTransition extends NodeComposant implements Runnable{

	private Transition transi;
	
	protected NodeStep nextStep[];
	protected NodeStep prevStep[];

	public NodeTransition(String name, int ... coord){
		this.transi = new Transition(name);
		nextStep = null;
		prevStep = null;
		if(coord.length == 2){
			x = coord[0];
			y = coord[1];
		}
	}
	/**
	 * A transition is always running
	 * but it is waiting for the condition only when it is "active"
	 * At the end, it activate its next steps and disactivate its previous steps
	 */
	public void run(){
		setChanged();
		notifyObservers(false);
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(transi.isTrue()){
				setChanged();
				notifyObservers(true);
			}
			else{
				setChanged();
				notifyObservers(false);
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
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return getTransition().getName();
	}
}
