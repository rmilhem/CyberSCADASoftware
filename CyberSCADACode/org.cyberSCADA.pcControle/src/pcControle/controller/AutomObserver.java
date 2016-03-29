package pcControle.controller;

import java.util.Observable;
import java.util.Observer;


// Ne sert qu'à mettre la vue à jour.
public class AutomObserver implements Observer
{
	
	ScadaController scada;


	public AutomObserver(ScadaController scada){
		
		this.scada=scada;
	}
	
	@Override
	public void update(Observable o, Object obj)
	{
		//autom=(AutomX) o; // inutile non ? autom n'est pas une copie, c'est la variable
		// mise à jour de l'interface
		scada.refreshAll();
		

	}
	
}
