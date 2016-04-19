package compilateur.ihm;

import compilateur.grafcet.Grafcet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHM extends Application {

	private static IHMBuilder builder;
	private static Grafcet g;
	private static Group root;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		g = new Grafcet();
		g.build();
		g.start();
		root = new Group();

		builder = new IHMBuilder(g.getCollection(), root);
		builder.build();

		Application.launch(args); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		Scene scene = new Scene(root, 1500,900); 
		
		primaryStage.setTitle("Grafcet"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}

}
