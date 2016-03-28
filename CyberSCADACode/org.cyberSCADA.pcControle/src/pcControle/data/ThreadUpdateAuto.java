package pcControle.data;
import java.io.IOException;

public class ThreadUpdateAuto extends Thread{

	AutomX automate;
	Com com;
	
	
	public ThreadUpdateAuto(AutomX automate, Com com){
		this.automate = automate;
		this.com = com;
	}
	
	public void run(){
		
	com.updateAutomate();
		
	}
	
	
}
