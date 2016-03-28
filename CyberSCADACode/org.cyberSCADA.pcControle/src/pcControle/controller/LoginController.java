package pcControle.controller;

import pcControle.ihm.IHM_Pingpong;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable
{
	private IHM_Pingpong application;
	IHM_Pingpong IHM = new IHM_Pingpong();
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}
	@FXML private Button Clear;
	@FXML private Button Login;
	@FXML private PasswordField password;
	@FXML private TextField account;
	public void setApp(IHM_Pingpong application)
	{
		this.application = application;
	}
	@FXML public void loginEvent(ActionEvent event)
	{
		System.out.println(account.getText()+password.getText());
		IHM.userLogin(account.getText(), password.getText());
	}
	@FXML public void clearEvent(ActionEvent event)
	{
		account.setText(null);
		password.setText(null);
	}
}
