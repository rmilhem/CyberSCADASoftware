package compilateur.ihm;

import java.util.Observable;
import java.util.Observer;

import compilateur.grafcet.NodeComposant;
import compilateur.grafcet.NodeStep;
import compilateur.grafcet.NodeTransition;

public class GrafcetObserver implements Observer{

	private IHMBuilder ihm;
	
	public GrafcetObserver(IHMBuilder ihm) {
		this.ihm = ihm;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof NodeStep){
			ihm.setActive((NodeComposant)arg0, (boolean)arg1);
		}
		else if(arg0 instanceof NodeTransition){
			ihm.setTrueFalse((NodeComposant)arg0, (boolean)arg1);
		}
		
	}

}
