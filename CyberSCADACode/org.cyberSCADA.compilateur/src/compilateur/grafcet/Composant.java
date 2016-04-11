package compilateur.grafcet;

public abstract class Composant extends Thread {
	
	public String name = "";
	protected boolean active = false;
	protected Controller c;
	public boolean initial = false;

}
