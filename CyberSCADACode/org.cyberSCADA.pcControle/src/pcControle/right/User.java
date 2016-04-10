package pcControle.right;
import java.util.Vector;

public class User {
	
	private String login;
	private String password;
	private Vector<Role> roleBase;
	
	public User(String id, String password) {
	
		this.login = id;
		this.password = password;
		roleBase = new Vector<Role>();
	}


	public Vector< Role> getRoles() {
		return roleBase;
	}
	
	public String getLogin() {
		return this.login;
	}
	public String getPassword(){
		return password;
	}
	
	public void addRole(Role ro) {
		roleBase.add(ro);
	}
	
	public void delRole(Role ro) {
		roleBase.remove(ro);
	}
	public Role getRole(){
		return roleBase.get(0);
	}
}
