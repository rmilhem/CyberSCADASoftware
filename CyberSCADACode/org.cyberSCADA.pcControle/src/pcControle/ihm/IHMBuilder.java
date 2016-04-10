package pcControle.ihm;

import java.util.HashMap;

import javax.management.monitor.Monitor;

import javafx.scene.Node;
import javafx.scene.control.Button;
import pcControle.right.RightBase;
import pcControle.right.User;
import pcControle.varAuto.VariableAuto;

public class IHMBuilder {
	RightBase rightBase;
	User currentUser;
	
	public IHMBuilder(RightBase rightBase, User currentUser) {
		this.rightBase = rightBase;
		this.currentUser = currentUser;
	}

	public void build(HashMap<Node, VariableAuto> widgets) {
		boolean mode;
		for(Node widget: widgets.keySet()){
		mode = (widget instanceof Button);	
		// si il n'y a pas de droit pour l'actuel utilisateur, on n'affiche pas le widget
		if(!rightBase.orderIsOK(widgets.get(widget), mode,
				currentUser.getRole())){
			widget.setVisible(false);
		}
		}
	}
	
	
}
