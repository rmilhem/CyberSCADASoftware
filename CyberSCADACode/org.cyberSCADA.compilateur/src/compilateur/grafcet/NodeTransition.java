package compilateur.grafcet;

public class NodeTransition {

	private NodeTransition next;
	private NodeTransition prev;
	private Transition transi;
	
	public NodeTransition(Transition transi){
		this.transi = transi;
		next = null;
		prev = null;
	}
	
	
	public Transition getTransi(){
		return transi;
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
