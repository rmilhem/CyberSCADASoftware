package compilateur.grafcet;

public class NodeStep {

	private NodeTransition nextTransi;
	private NodeTransition prevTransi;
	private NodeStep nextStep;
	private NodeStep prevStep;
	private Step step;
	
	public NodeStep(Step step){
		this.step = step;
		nextStep = null;
		prevStep = null;
		nextTransi = null;
		prevTransi = null;
	}
	
	
	public Step getStep(){
		return step;
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
		return step.name;
	}
}
