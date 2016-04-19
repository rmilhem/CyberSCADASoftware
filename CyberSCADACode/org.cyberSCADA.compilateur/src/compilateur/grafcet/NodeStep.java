package compilateur.grafcet;


public class NodeStep extends NodeComposant{

	private Step step;
	
	protected NodeTransition nextTransi[];
	protected NodeTransition prevTransi[];
	
	public NodeStep(String name, int ... coord){
		step = new Step(name);
		
		nextTransi = null;
		prevTransi = null;
		
		if(coord.length == 2){
			x = coord[0];
			y = coord[1];
		}
	}
	
	/**
	 * A step is always running
	 * but it is waiting for the condition only when it is "active"
	 * At the end, it disactivate its next transitions
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
			setChanged();
			notifyObservers(false);
			if(active.get()){
				System.out.println("start step : "+step.name);
				setChanged();
				notifyObservers(true);
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

				setChanged();
				notifyObservers(false);
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

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return getStep().getName();
	}
}
