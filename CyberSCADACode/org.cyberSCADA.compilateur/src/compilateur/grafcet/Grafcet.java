package compilateur.grafcet;

public class Grafcet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private NodeStep first;
	
	
	public Grafcet(Step n){
		
		first = new NodeStep(n);
		
	}
	
	
	public void add(Step step){
		NodeStep node = new NodeStep(step);
		NodeTransition tmp = getLastTransi();
		tmp.setNext(node);
		node.setPrev(tmp);
	}	
	
	public NodeStep getLast(){
		NodeStep tmp = first;
		while(tmp.getNext() != null)
		{
			tmp = tmp.getNext();
		}
		return tmp;
	}
	public NodeTransition getLastTransi(){
		NodeTransition tmp = first;
		while(tmp.getNext() != null)
		{
			tmp = tmp.getNext();
		}
		return tmp;
	}
	
	public void setFirst(NodeStep first){
		this.first = first;
	}
	
	public NodeStep getFirst(){
		return first;
	}

}
