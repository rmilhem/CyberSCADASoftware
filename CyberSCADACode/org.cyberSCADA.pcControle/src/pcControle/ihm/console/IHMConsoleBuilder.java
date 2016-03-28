package pcControle.ihm.console;
import java.util.Vector;

import pcControle.right.Right;
import pcControle.right.RightBase;
import pcControle.right.Role;

public class IHMConsoleBuilder {
	RightBase rightBase;
	
	public IHMConsoleBuilder(RightBase rightBase){
		this.rightBase = rightBase;

	}
	
	
	public void build(IHMConsole ihm, Vector<Role> rolesUser){
		// Pour l'instant gestion d'un role par utilisateur
		// ajout des choix pour tous
		ihm.addChoixMP("Lecture variables");
		ihm.addChoixMP("Edition variables");
		Vector<Right> rights = rightBase.getRights(rolesUser.get(0));
		
		String menuLecture = "";
		String menuEcriture= "";
		
		
		for(Right e: rights){
		if(!e.getMode()){
			ihm.addChoixLecture(e.getNomVar());
		}
		if(e.getMode()){
			ihm.addChoixEcriture(e.getNomVar());
		}

		}
		
		if(rolesUser.get(0).getName().equals("admin")){
		ihm.addChoixMP("Parametres systeme");
		}
	}
	
}
