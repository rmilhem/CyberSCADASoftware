package rasSoft.gestFonctAuto;

public abstract class Threads extends Thread{
	
	protected Grafcet g;

	public Threads(Grafcet g){
		super();
		this.g=g;
	}
	
	public abstract void run();
}