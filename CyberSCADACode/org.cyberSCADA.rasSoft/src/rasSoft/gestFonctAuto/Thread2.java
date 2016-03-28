package rasSoft.gestFonctAuto;

public class Thread2 extends Threads{

	public Thread2(Grafcet g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(!g.isPresence_tube_remplissage());
		g.setDepart_remplissage(true);
		g.setDepart_remplissage(false);
		while(!g.isTransi_S511x());
		while(!g.isTransi_S36x());
		try {
			Thread.sleep(5340);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.setFin2(true);
		this.interrupt();
	}

}
