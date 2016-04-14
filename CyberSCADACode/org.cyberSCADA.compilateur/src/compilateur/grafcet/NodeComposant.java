package compilateur.grafcet;

public abstract class NodeComposant extends Thread {

	protected NodeTransition nextTransi[];
	protected NodeTransition prevTransi[];
	protected NodeStep nextStep[];
	protected NodeStep prevStep[];
	
	public Bool active = new Bool(false);
	
	public boolean initial = false;
	
	public int id;
	
	public void setInitial(boolean b){
		initial = b;
	}
	
	public boolean isInitial(){
		return initial;
	}
	
	public Bool getActive(){
		return active;
	}
	
	public void setActive(boolean b){
		active.set(b);
	}

	public boolean isActive(){
		return active.get();
	}
	
	public int getID(){
		return this.id;
	}
	
	
	
}
