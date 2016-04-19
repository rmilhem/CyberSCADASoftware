package compilateur.ihm;

import java.util.Enumeration;
import java.util.Hashtable;

import compilateur.grafcet.NodeComposant;
import compilateur.grafcet.NodeStep;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class IHMBuilder{

	private int ref = 70;
	private Hashtable<String, NodeComposant> collection;
	private Hashtable<String, Integer> table;
	private Group root;
	private GrafcetObserver observer;

	public IHMBuilder(Hashtable<String, NodeComposant> c, Group root) {
		collection = c;
		this.root = root;
		observer = new GrafcetObserver(this);
		table = new Hashtable<String, Integer>();
	}

	public void build(){
		Enumeration<String> en = collection.keys();
		int index = 0;
		while(en.hasMoreElements()){
			NodeComposant tmp = collection.get(en.nextElement());
			tmp.addObserver(observer);
			if(tmp instanceof NodeStep){
				Rectangle rect = new Rectangle();
				rect.setX(ref*tmp.getX());
				rect.setY(ref*tmp.getY());
				rect.setWidth(ref);
				rect.setHeight(ref);
				rect.setFill(Color.WHITE);
				rect.setStroke(Color.BLACK);
				rect.setId(tmp.getNom());
				root.getChildren().add(rect);
				table.put(tmp.getNom(), index);
				index++;
				Label label = new Label(tmp.getNom());
				label.setLayoutX(ref*tmp.getX());
				label.setLayoutY(ref*tmp.getY());
				label.setPrefHeight(ref);
				label.setPrefWidth(ref);
				label.setAlignment(Pos.CENTER);
				//label.setId("label:"+tmp.getNom());
				root.getChildren().add(label);
				//table.put("label:"+tmp.getNom(), index);
				index++;
			}
			else{
				Rectangle rect = new Rectangle();
				rect.setX(ref*tmp.getX()+ref/2-2);
				rect.setY(ref*tmp.getY());
				rect.setWidth(5);
				rect.setHeight(ref);
				rect.setFill(Color.BLACK);
				rect.setStroke(Color.BLACK);
				rect.setId(tmp.getNom());
				root.getChildren().add(rect);
				table.put(tmp.getNom(), index);
				index++;
				Label label = new Label(tmp.getNom());
				label.setLayoutX(ref*tmp.getX()+ref/2);
				label.setLayoutY(ref*tmp.getY());
				label.setPrefHeight(ref);
				label.setPrefWidth(ref);
				label.setAlignment(Pos.CENTER);
				label.setId("label:"+tmp.getNom());
				root.getChildren().add(label);
				table.put("label:"+tmp.getNom(), index);
				index++;
			}
			
			tmp.setAffiche(true);
		}
	}
	
	public void setActive(NodeComposant n, boolean b){
		Rectangle r = (Rectangle) root.getChildren().get(table.get(n.getNom()));
		if(b){
			r.setFill(Color.BLUE);
		}
		else{
			r.setFill(Color.WHITE);
		}
	}
	public void setTrueFalse(NodeComposant n, boolean b){
		Label l = (Label) root.getChildren().get(table.get("label:"+n.getNom()));
		if(b){
			l.setTextFill(Color.GREEN);
		}
		else{
			l.setTextFill(Color.RED);
		}
	}
}
