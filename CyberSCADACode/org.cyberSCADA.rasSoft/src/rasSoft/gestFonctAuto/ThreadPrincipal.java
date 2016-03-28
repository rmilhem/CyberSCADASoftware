package rasSoft.gestFonctAuto;

public class ThreadPrincipal extends Threads {

	
	public ThreadPrincipal(Grafcet g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		g.start();
	}

}
