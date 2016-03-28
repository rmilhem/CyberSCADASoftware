package rasSoft.test;
import java.util.Random;

public class AutomPhy {
	private int numAut;
	private boolean[] coil;
	private int[] reg;
	boolean actif;
	public AutomPhy(int nbAut, boolean actif) {
		this.actif = actif;
		numAut = nbAut;
		reg = new int[5];
		for (int i = 0 ; i < 5 ; i++) {
			reg[i] = 0;
		}
		
		if (nbAut == 1 || nbAut == 2) {
			coil = new boolean[3];
				for (int i = 0 ; i < 3 ; i++) {
					coil[i] = true;
				}
		} else {
			coil = new boolean[2];
			coil[0] = true;
			coil[1] = true;
		}
	}
	
	public int getReg(int i) {
		if (numAut == 1 && i >= 0 && i < this.reg.length) {
			return this.reg[i];
		} else {
			return -1;
		}
	}
	
	public boolean[] getCoil(int i) {
		boolean[] res = new boolean[2];
		if ((this.numAut == 1 || numAut == 2) && i >= 0 && i < 3) {
			res[0] = true;
			res[1] = coil[i];
		} else if (this.numAut == 3 && i >= 0 && i < 2) {
			res[0] = true;
			res[1] = coil[i];
		} else {
			res[0] = false;
			res[1] = false;
		}
		return res;
	}
	
	public boolean setReg(int i, int val) {
		if (numAut == 1 && i >= 0 && i < this.reg.length) {
			reg[i] = val;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean setCoil(int i, boolean val) {
		if ((this.numAut == 1 || numAut == 2) && i >= 0 && i < 3) {
			coil[i] = val;
			return true;
		} else if (this.numAut == 3 && i >= 0 && i < 2) {
			coil[i] = val;
			return true;
		} else {
			return false;
		}
	}

	
	public boolean nextStep() {
		if(actif){
		Random alea = new Random();
		
		// m.a.j. des booleens
		boolean[] b = {true, false};
		if (numAut == 1 || numAut == 2) {
			for (int i = 0 ; i < 3 ; i++) {
				this.coil[i] = b[alea.nextInt(2)];
			}
		} else if (numAut == 3) {
			for (int i = 0 ; i < 2 ; i++) {
				this.coil[i] = b[alea.nextInt(2)];
			}
		} else {
			return false;
		}
		// m.a.j. du registre
		if (numAut == 1) {
			this.reg[0] = alea.nextInt(100);
		}
		}
		return true;
	}
	
	public String toString() {
		String res = "";
		res += "**************Automate Physique (debut)***************\n";
		res += "Aut. N°"+numAut+"\n";
		if (numAut == 1) {
			res += "Capteur presence : "+coil[0]+"\n";
			res += "Actionneur chute haut : "+coil[1]+"\n";
			res += "Actionneur chute bas : "+coil[2]+"\n";
			res += "(ancien)Nombre de balles : "+reg[0];
		} else if (numAut == 3) {
			res += "Capteur presence : "+coil[0]+"\n";
			res += "Actionneur ventouse : "+coil[1]+"\n";
			res += "Capteur presence bouchon : "+coil[2]+"\n";
		} else {
			res += "Actionneur pince : "+coil[0]+"\n";
			res += "Capteur presence : "+coil[1]+"\n";
		}
		res += "**************Automate Physique (fin)***************\n";;
		res += "\n";
		return res;
	}
	public int getNum(){
		return numAut;
	}
	public static void main (String args[]) {
		AutomPhy a = new AutomPhy(1, false);
		// System.out.println("All is alright 1");
		System.out.println(a);
		// System.out.println("All is alright 2");
		a.nextStep();
		//System.out.println("All is alright 3");
		System.out.println(a);
	}
}
