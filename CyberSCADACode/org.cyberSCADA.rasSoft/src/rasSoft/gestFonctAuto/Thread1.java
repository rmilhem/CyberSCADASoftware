package rasSoft.gestFonctAuto;

public class Thread1 extends Threads{
	
	public Thread1(Grafcet g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		g.setDepart_transfert(true);
		g.setDepart_transfert(false);//on donne une impulsion sur depart_transfert pour lancer le grafcet de transfert
		while(!g.isTransi_S511x());
		g.setFin1(true);
		this.interrupt();
	}

}