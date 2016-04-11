package compilateur.grafcet;

public class Transition extends Composant{
	
	public Bool condition;
	
	public Transition(Controller c, String name){
		this.name = name;
		this.c = c;
	}
	
	public void setCondition(Bool b){
		condition = b;
		System.out.println("condition ok");
	}
	
	public Bool getCondition(){
		return condition;
	}
	
	public void setInitial(boolean b){
		initial = b;
	}
	
	public boolean isInitial(){
		return initial;
	}
	
	public void run(){
		System.out.println("start transi : "+name);
		while(!condition.get()/*!c.getState("step1")*/){
			System.out.println("condition : : : "+condition);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		c.next();
		this.interrupt();
	}

}
