package pcControle.right;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

import pcControle.data.AutomLecture;
import pcControle.data.AutomX;
import pcControle.varAuto.*;;

public class Monitor {
	private Hashtable<String, User> users = new Hashtable<String, User>();
	private Scanner sc;
	private User currentUser;
	RightBase rightBase;
	AutomX automate;
	
	boolean isOk = false;
	/** Le policier. Rq: pensez à ne pas mettre les codes en clair dans le code **/
	public Monitor() {
		
		this.automate = new AutomX();
		isOk = automate.isUpdated();
		sc = new Scanner(System.in);
		
		//création des roles
		Role roleAdmin = new Role("admin");
		Role roleOperateur = new Role("operateur");
		
		// création des rights et attribution des rights aux roles
		Vector<Role> roles = new Vector<Role>();
		roles.add(roleAdmin);
		roles.add(roleOperateur);
		rightBase = new RightBase(roles);
		
		
		//création des utilisateurs et attribution des roles aux users
		users= new Hashtable<String, User>();
		User u = new User("planchan", "42");
		u.addRole(roleAdmin);
		users.put("planchan", u);
		u= new User("tomezaju", "*");
		u.addRole(roleOperateur);
		users.put("tomezaju", u);

	}
	public boolean isOk(){
		return isOk;
	}
	public boolean identification(String id, String password) {
		if(users.containsKey(id)){
			if(password.equals(users.get(id).getPassword())){
				currentUser = users.get(id);
				return true;
			}
			
		}
		return false;
	}
	
	public RightBase getRightBase(){
		return rightBase;
	}
	public User getUser(){
		return currentUser;
	}
	public AutomLecture getAutomate(){
		return automate;
	}
	public void setVar(VariableAuto nomVar, int val){
		// si on a le droit
		if(rightBase.orderIsOK(nomVar, true, currentUser.getRole()))
		automate.setVar(nomVar, val);
	}

}
