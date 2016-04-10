package pcControle.ihm;
	


import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pcControle.controller.LoginController;
import pcControle.controller.ScadaController;
import pcControle.right.Monitor;


public class IHM_Pingpong extends Application {
	private static Stage stage; // pourquoi static ? (julien)
	private static Monitor monitor; // pourquoi obligé static ? (julien)
	private static IHMBuilder ihmBuilder; 
	
	@Override
	public void start(Stage primaryStage) {
		monitor = new Monitor();
		
		try {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gotoMain()
	{
		System.out.println("Login OK !!");
		try
		{
			ScadaController Scada = (ScadaController) replaceSceneContent ("ScadaView.fxml");
			Scada.init(monitor.getAutomate(), monitor);
			Scada.setApp(this);	
			ihmBuilder = new IHMBuilder(monitor.getRightBase(), monitor.getUser());
			ihmBuilder.build(Scada.getWidgets());
		}
		catch (Exception ex)
		{
			Logger.getLogger(IHM_Pingpong.class.getName()).log(Level.SEVERE, null, ex);
		}
	}	
	public void gotoLogin()
	{
		  try {
	            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
	            login.setApp(this);
	        } catch (Exception ex) {
	            Logger.getLogger(IHM_Pingpong.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	// Si login est bon, charger le ScadaView
	public void userLogin(String account, String password) 
	{
	
		if(monitor.identification(account, password))
		{
			gotoMain();
		}
	}
	
	public void userQuit() //quitter et retourner a la page de login
	{
		gotoLogin();
	}

	public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        //Stage stage = new Stage();
        InputStream in = IHM_Pingpong.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(IHM_Pingpong.class.getResource(fxml));
        AnchorPane page=null;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        //Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(page);
        if("Login.fxml".equals(fxml))
        {
        	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        	stage.setTitle("Login");
        }
        else if ("ScadaView.fxml".equals(fxml))
        {
        	scene.getStylesheets().add(getClass().getResource("Scada.css").toExternalForm());
        	stage.setTitle("Automate Pingpong");
        }
        //stage.setTitle("Scada");
        stage.setScene(scene);
        stage.sizeToScene();
        //stage.show();
        return (Initializable) loader.getController();
    }
	
	public static void main(String[] args) {
		launch(args);
		
	}
}