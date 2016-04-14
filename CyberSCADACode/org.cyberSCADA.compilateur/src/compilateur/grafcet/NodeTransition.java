package compilateur.grafcet;

public class NodeTransition extends NodeComposant{


	public Transition transi;

	public int wide;

	public NodeTransition(String name){
		this.transi = new Transition(name);
		nextTransi = null;
		prevTransi = null;
		nextStep = null;
		prevStep = null;
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
						System.out.println(p.getStep().getName()+" set true");
					}
					for(NodeStep p : prevStep){
						p.setActive(false);
						System.out.println(p.getStep().getName()+" set false");
					}
				}
				active.set(false);
				System.out.println("fin transi : "+transi.name);
			}
		}
	}
	
	public Transition getTransition(){
		return transi;
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
		return transi.name;
	}
}
