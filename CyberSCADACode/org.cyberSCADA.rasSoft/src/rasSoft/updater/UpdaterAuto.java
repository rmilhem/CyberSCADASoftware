package rasSoft.updater;

import rasSoft.principale.ThreadPrincipal;
import rasSoft.test.AutomPhy;

public interface UpdaterAuto {
	
	
	public void miseAJourAutomatePhy(int numAutomate, ThreadPrincipal threadPrincipal);
	
	// lecture des variables de l'automate physique
		// écriture dans automate virtuel
	public void miseAJourAutomateV(int numAutomate, ThreadPrincipal threadPrincipal);
	
	public AutomPhy getAutomPhy();
}
