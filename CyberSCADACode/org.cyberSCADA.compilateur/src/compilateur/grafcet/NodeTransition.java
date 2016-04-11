package compilateur.grafcet;

public class NodeTransition extends NodeComposant{

	
	public Transition transi;
	
	public NodeTransition(Transition transi){
		this.transi = transi;
		nextTransi = null;
		prevTransi = null;
		nextStep = null;
		prevStep = null;
	}
	
	
	public Transition getTransi(){
		return transi;
	}
	
	public NodeTransition getPrevTransition(){
		return prevTransi;
	}
	
	public NodeTransition getNextTransition(){
		return nextTransi;
	}
	
	public NodeStep getPrevStep(){
		return prevStep;
	}
	
	public NodeStep getNextStep(){
		return nextStep;
	}
	
	public void setPrevTransition(NodeTransition prev){
		this.prevTransi = prev;
	}
	
	public void setNextTransition(NodeTransition next){
		this.nextTransi = next;
	}
	
	public void setPrevStep(NodeStep prev){
		this.prevStep = prev;
	}
	
	public void setNextStep(NodeStep next){
		this.nextStep = next;
	}
	
	public String toString(){
		return transi.name;
	}
}
