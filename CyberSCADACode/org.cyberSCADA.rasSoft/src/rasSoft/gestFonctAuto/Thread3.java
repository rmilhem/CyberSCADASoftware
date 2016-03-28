package rasSoft.gestFonctAuto;

public class Thread3 extends Threads{
	
	public Thread3(Grafcet g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(!g.isTransi_S410x());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!g.isRotation_moteur());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.setFin3(true);
		this.interrupt();
	}
}