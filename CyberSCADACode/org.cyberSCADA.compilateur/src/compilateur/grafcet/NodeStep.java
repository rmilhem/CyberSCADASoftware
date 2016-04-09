package compilateur.grafcet;

public class NodeStep {

	private NodeTransition next;
	private NodeTransition prev;
	private Step step;
	
	public NodeStep(Step step){
		this.step = step;
		next = null;
		prev = null;
	}
	
	
	public Step getStep(){
		return step;
	}
	
	public NodeTransition getPrev(){
		return prev;
	}
	
	public NodeTransition getNext(){
		return next;
	}
	
	public void setPrev(NodeTransition prev){
		this.prev = prev;
	}
	
	public void setNext(NodeTransition next){
		this.next = next;
	}
}
