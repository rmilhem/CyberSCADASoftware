package rasSoft.gestFonctAuto;

public class Thread4 extends Threads{
	
	public Thread4(Grafcet g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(!g.isTransi_S511x());
		g.setFin4(true);
		this.interrupt();
	}

}