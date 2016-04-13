package compilateur.grafcet;

public class NodeTransition extends NodeComposant{


	public Transition transi;

	public int wide;

	public NodeTransition(String name, int w, int ... id){
		this.transi = new Transition(name);
		wide = w;
		nextTransi = null;
		prevTransi = null;
		nextStep = null;
		prevStep = null;
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
				System.out.println("start transi : "+transi.name);
				while(!transi.isTrue()){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("fin transi : "+transi.name);
				active.set(false);
				end = true;

				if(nextTransi.length > wide){
					for(NodeTransition t : nextTransi){
						t.setActive(true);;
					}
				}
				else if(nextTransi.length == wide){
					nextTransi[id].setActive(true);;
				}
				else{
					if(id == 0){
						boolean b[] = new boolean[wide];
						boolean bbb = false;
						int c = 0;
						while(!bbb){
							c = 0;
							for(NodeTransition t : nextTransi[0].getPrevTransition()){
								b[c] = t.end;
								c++;
							}
							for(boolean bb : b){
								bbb = bbb | bb;
							}
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						nextTransi[0].setActive(true);;
					}
				}
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
