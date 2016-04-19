package compilateur.grafcet;

import java.util.Observable;

public abstract class NodeComposant extends Observable implements Runnable{
	
	public Bool active = new Bool(false);
	
	public boolean initial = false;
	public boolean affiche = false;
	public int x = 0;
	public int y = 0;
	
	public int id;
	
	public abstract String getNom();
	
	public void setInitial(boolean b){
		initial = b;
	}
	
	public boolean isInitial(){
		return initial;
	}
	
	public boolean isAffiche(){
		return affiche;
	}
	
	public void setAffiche(boolean b){
		affiche = b;
	}
	
	public Bool getActive(){
		return active;
	}
	
	public void setActive(boolean b){
		active.set(b);
	}

	public boolean isActive(){
		return active.get();
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
}
