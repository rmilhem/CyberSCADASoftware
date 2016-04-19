package pcControle.controller;

import pcControle.ihm.IHM_Pingpong;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable
{
	public IHM_Pingpong application;
	IHM_Pingpong IHM = new IHM_Pingpong();
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}
	@FXML private Button effacer;
	@FXML private Button connexion;
	@FXML private PasswordField mdp;
	@FXML private TextField id;
	@FXML private Label idL;
	@FXML private Label mdpL;
	@FXML private Label titre;
	
	public void setApp(IHM_Pingpong application)
	{
		this.application = application;
	}
	@FXML public void loginEvent(ActionEvent event)
	{
		System.out.println(id.getText()+mdp.getText());
		IHM.userLogin(id.getText(), mdp.getText());
	}
	@FXML public void clearEvent(ActionEvent event)
	{
		id.setText(null);
		mdp.setText(null);
	}
	@FXML public void langFr(ActionEvent event)
	{
		effacer.setText("Effacer");
		connexion.setText("Connexion");
		idL.setText("Identifiant");
		mdpL.setText("Mot de Passe");
		titre.setText("Bienvenue");
		IHM.setLang(0);
	}
	@FXML public void langEn(ActionEvent event)
	{
		effacer.setText("Clear");
		connexion.setText("Log in");
		idL.setText("Login");
		mdpL.setText("Password");
		titre.setText("Welcome");
		IHM.setLang(1);
	}
	
}
