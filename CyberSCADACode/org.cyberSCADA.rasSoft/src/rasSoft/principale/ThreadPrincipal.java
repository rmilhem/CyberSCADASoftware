package rasSoft.principale;
import rasSoft.gestFonctAuto.*;

public class ThreadPrincipal extends Thread {

	Grafcet graph;
	public ThreadPrincipal() {
		graph = new Grafcet();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		graph.start();
	}

	public Grafcet getGrafcet(){
		return graph;
	}
}
