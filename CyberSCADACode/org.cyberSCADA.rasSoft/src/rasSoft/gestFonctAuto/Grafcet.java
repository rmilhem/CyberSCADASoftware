package rasSoft.gestFonctAuto;
import rasSoft.principale.MasterModbus;

public class Grafcet {

	 /* D�claration des �tapes et les transitions
	 */

	boolean init;
	boolean aru;
	boolean stop;
	MasterModbus m;
	/*----------------------------------------*/
	
	boolean fin1 = false;
	boolean fin2 = false;
	boolean fin3 = false;
	boolean fin4 = false;
	
	boolean fonctionnement = false;

	/*---------------------------------------*/


	public Grafcet(){

		init=true; //etape d'initialisation
		m = new MasterModbus("172.20.25.41:502");
	}

	public void start(){
		//forcer les �tapes initiales
		initialisation();
		coordination();
	}

	public void initialisation(){
			while(!isDcy());
			setRotation_moteur(true);
			while(!isTransi_index_plateau());
			setRotation_moteur(false);
			fonctionnement = true;
	}

	public void coordination(){
			while(!fonctionnement);

			//------------------------------//
			while(!(!isPresence_tube() || !isRotation_moteur()));
			setDepart_transfert(true);
			setDepart_transfert(false);//on donne une impulsion sur depart_transfert pour lancer le grafcet de transfert
			while(!isTransi_S511x());
			
			//-------------------------------//
			setRotation_moteur(true);
			try {
				Thread.sleep(5340);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setRotation_moteur(false);

			//-------------------------------//

			Thread1 t1 = new Thread1(this);
			Thread2 t2 = new Thread2(this);

			t1.start();
			t2.start();
			
			while(!(fin1 == true && fin2 == true)){System.out.println("thread1&2");}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(m.ReadCoil("9","1","1").equals("1")){
				
				Thread3 t3 = new Thread3(this);
				Thread4 t4 = new Thread4(this);
				
				setFin3(false);
				setFin4(false);
				
				setDepart_remplissage(true);
				setDepart_remplissage(false);//on donne une impulsion sur depart_transfert pour lancer le grafcet de transfert
				setDepart_bouchage(true);
				setDepart_bouchage(false);
				setDepart_transfert(true);
				setDepart_transfert(false);
				t3.start();
				t4.start();
				
				while(!(isAttente_Tube() == true && fin3 == true && fin4 == true)){System.out.println("attente");}
			}
			System.out.println("fin");
	}

	//Get et set

	public boolean isTransi_index_plateau() {
		if(m.ReadCoil("10","1","1").equals("1")){
			return true;
		}
		else
			return false;
	}
	
	public boolean isAttente_Tube() {
		if(m.ReadCoil("19","1","1").equals("1")){
			return true;
		}
		else
			return false;
	}

	public boolean isTransi_S511x() {
		if(m.ReadCoil("2","1","1").equals("1")){
			return true;
		}
		else
			return false;
	}
	
	public  boolean isTransi_S410x() {
		if(m.ReadCoil("6","1","1").equals("1")){
			return true;
		}
		else
			return false;
	}

	public boolean isTransi_S36x() {
		if(m.ReadCoil("15","1","1").equals("1")){
			return true;
		}
		else
			return false;
	}

	public boolean isPresence_tube() {
		if(m.ReadCoil("9","1","1").equals("1")){
			return true;
		}
		else
			return false;
	}

	public boolean isRotation_moteur() {
		if(m.ReadCoil("12","1","1").equals("1")){
			return true;
		}
		else
			return false;
	}
	public boolean isRemplissage() {
		if(m.ReadCoil("101", "1", "1").equals("1")){
			return true;
		}
		else return false;
	}

	public void setRotation_moteur(boolean rotation_moteur) {
		m.WriteCoil("102", rotation_moteur,"1");
	}

	public void setDepart_transfert(boolean depart_transfert) {
		m.WriteCoil("100", depart_transfert, "1");
	}

	
	public boolean isPresence_tube_remplissage() {
		if(m.ReadCoil("17","1","1").equals("1")){
			return true;
		}
		else
		{
			return false;
		}
	}

	public void stop(){
	m.WriteCoil("102", false, "1");
	}
	public void setDepart_remplissage(boolean depart_remplissage) {
		m.WriteCoil("101", depart_remplissage, "1");
	}
	
	public void setDepart_bouchage(boolean depart_bouchage) {
		m.WriteCoil("104", depart_bouchage, "1");
	}

	public boolean isDcy() {
		if(m.ReadCoil("18","1","1").equals("1")){
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setFin1(boolean b)
	{
		this.fin1 = b;
	}
	public void setFin2(boolean b)
	{
		this.fin2 = b;
	}
	public void setFin3(boolean b)
	{
		this.fin3 = b;
	}
	public void setFin4(boolean b)
	{
		this.fin4 = b;
	}

	public static void main (String args[]){
		Grafcet g = new Grafcet();
		g.start();
	}

	public boolean isActionneurHaut() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setActionneurHaut(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public boolean isActionneurBas() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setActionneurBas(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public boolean isMoteurBalle() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setMoteurBalle(boolean b) {
		// TODO Auto-generated method stub
		
	}



}
