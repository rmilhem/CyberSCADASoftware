package rasFire;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.ProcessImage;

public  class State {
	
 //class attributes
	private static State self;  //Singleton reference
	boolean enConnectionIHM = false;
	boolean running = true;
	
	static {
	    self = new State();
	  }//initializer

	 private State() {
		   
		  }

	
	public boolean isEnConnectionIHM() {
		return enConnectionIHM;
	}

	public void setEnConnectionIHM(boolean enConnectionIHM) {
		this.enConnectionIHM = enConnectionIHM;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static State getReference(){
		return self;
	}
	
	

}
