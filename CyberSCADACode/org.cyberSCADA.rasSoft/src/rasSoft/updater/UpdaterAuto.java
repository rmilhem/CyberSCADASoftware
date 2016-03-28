package rasSoft.updater;

import rasSoft.principale.ThreadPrincipal;

public interface UpdaterAuto {
	
	
	public void miseAJourAutomatePhy(int numAutomate, ThreadPrincipal threadPrincipal);
	
	// lecture des variables de l'automate physique
		// écriture dans automate virtuel
	public void miseAJourAutomateV(int numAutomate, ThreadPrincipal threadPrincipal);
}
