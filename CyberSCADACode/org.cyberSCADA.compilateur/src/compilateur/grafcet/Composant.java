package compilateur.grafcet;

public abstract class Composant extends Thread {
	
	public String name = "";
	public Bool active = new Bool(false);
	protected Controller c;
	public boolean initial = false;

}
