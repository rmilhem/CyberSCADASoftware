package pcControle.controller;

import java.net.URL;
import java.util.ResourceBundle;

import pcControle.data.AutomLecture;
import pcControle.data.AutomX;
import pcControle.ihm.IHM_Pingpong;
import pcControle.right.Monitor;
import pcControle.varAuto.VariableAuto;
/*import controller.Automate_Bales.etat_capteur_bales_rempli;
import controller.Automate_Bales.etat_capteur_tube;
import controller.Automate_Bouchons.etat_presence_tube;
import controller.Automate_Bouchons.etat_stock_bouchon;
import controller.Automate_Pince_Tube.etat_tube_en_stock;
import controller.Automate_Pince_Tube.etat_tube_sur_plateau;*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ScadaController implements Initializable{

	AutomLecture automLecture;
	AutomObserver observer;
	Monitor monitor;
	
	
	private IHM_Pingpong application;
	IHM_Pingpong IHM = new IHM_Pingpong();
	private int accumulator=0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

	}
	public void init(AutomLecture autom, Monitor monitor)
	{
		automLecture = autom;
		this.monitor = monitor;
		observer = new AutomObserver(this);
		autom.addObserver(observer);
	}
	@FXML private Label Accumulateur;
	// automate bouchons
	@FXML private Circle presence_tube;
	@FXML private Circle stock_bouchon;
	// automate bales
	@FXML private Circle actionneurHaut;
	@FXML private Circle actionneurBas;
	@FXML private Circle moteurBalle;
	@FXML private Circle isRunning;
	@FXML private Circle isTurning;
	@FXML private Circle capteur_tube;
	// automate pince tube
	@FXML private Circle tube_sur_plateau;
	@FXML private Circle tube_en_stock;
	
	
	private void setStartAutomate(){
		setOnOff(VariableAuto.running);
	}
	private void setActionneurHaut(){
		setOnOff(VariableAuto.actionneurChuteHaut);
	}
	private void setActionneurBas(){
		setOnOff(VariableAuto.actionneurChuteBas);
	}
	private void setMoteurBalle(){
		setOnOff(VariableAuto.moteurBalle);
	}
	public void setRemplirTube(){
		setOnOff(VariableAuto.remplissage);
	}
	public void tournePlateau()		
	{
		setOnOff(VariableAuto.tournerPlateau);
	}
	public void actionPinceTube()	
	{
		setOnOff(VariableAuto.bouchonner);
	}
	public void bouchonner()
	{
		setOnOff(VariableAuto.actionPinces);
	}
	public void refreshAll()
	{
		
		//----------------------------- automate bales-------------------------------------
		setColorCircle(automLecture.getVar(VariableAuto.capteurPresence), capteur_tube);
		setColorCircle(automLecture.getVar(VariableAuto.actionneurChuteHaut), actionneurHaut);
		setColorCircle(automLecture.getVar(VariableAuto.actionneurChuteBas), actionneurBas);
		setColorCircle(automLecture.getVar(VariableAuto.moteurBalle), moteurBalle);
		setColorCircle(automLecture.getVar(VariableAuto.running), isRunning);
		setColorCircle(automLecture.getVar(VariableAuto.tournerPlateau), isTurning);
		
	}
	
	private void setOnOff(VariableAuto var){
		if(automLecture.getVar(var)==0 || automLecture.getVar(var)==-1){
			monitor.setVar(var, 1);
		
		}
		else
			monitor.setVar(var, 0);
	}
	
	private void setColorCircle(int val, Circle circle){
		if (val==0)
			circle.setFill(Color.YELLOW);
		else if (val==1)
			circle.setFill(Color.BLUE );
		else 
			circle.setFill(Color.RED);	
	}
	
	public void setApp(IHM_Pingpong application)
	{
		this.application = application;
	}

	// ------------------------------------ SIMULATEUR ------------------------------------
	@FXML private Button startAutomateB;
	@FXML private Button Actionneur_Haut;
	@FXML private Button Actionneur_Bas;
	@FXML private Button moteurBalleB;
	@FXML private Button remplirTubeB;
	@FXML private Button MisEnPlaceTube;
	@FXML private Button FaireTournerPlateau;	
	@FXML private Button ActionPinceTube;
	@FXML private Button back;
	//@FXML private Button startAutomate;
	@FXML private Button bouchonner;
	
	@FXML public void Bouchonner(ActionEvent event){
		bouchonner();
	}
	
	@FXML public void backEvent(ActionEvent event)	
	{
		IHM.userQuit();
	}
	@FXML public void startAutomate(ActionEvent event)
	{
		setStartAutomate();
	}
	@FXML public void actionneurHaut(ActionEvent event)
	{
		// si l'automate n'est pas en train de tourner
		if(automLecture.getVar(VariableAuto.running)==0 || automLecture.getVar(VariableAuto.running)==-1)
		setActionneurHaut();
		else System.out.println("l'automate est en train de tourner ! Arrêtez le d'abord");
	}
	@FXML public void actionneurBas(ActionEvent event)
	{
		if(automLecture.getVar(VariableAuto.running)==0 || automLecture.getVar(VariableAuto.running)==-1)
		setActionneurBas();
		else System.out.println("l'automate est en train de tourner ! Arrêtez le d'abord");
	}
	@FXML public void moteurBalle(ActionEvent event)
	{
		if(automLecture.getVar(VariableAuto.running)==0 || automLecture.getVar(VariableAuto.running)==-1)
		setMoteurBalle();
		else System.out.println("l'automate est en train de tourner ! Arrêtez le d'abord");
	}
	@FXML public void remplirTube(ActionEvent event)
	{
		if(automLecture.getVar(VariableAuto.running)==0 || automLecture.getVar(VariableAuto.running)==-1)
			setRemplirTube();
		else System.out.println("l'automate est en train de tourner ! Arrêtez le d'abord");
	}
	@FXML public void MettreTube(ActionEvent event)
	{
		if(automLecture.getVar(VariableAuto.running)==0 || automLecture.getVar(VariableAuto.running)==-1)
		mettreEnStockTube();
		else System.out.println("l'automate est en train de tourner ! Arrêtez le d'abord");
	}
	@FXML public void TournerPlateau(ActionEvent event)
	{
		if(automLecture.getVar(VariableAuto.running)==0 || automLecture.getVar(VariableAuto.running)==-1)
		tournePlateau();
		else System.out.println("l'automate est en train de tourner ! Arrêtez le d'abord");
	}
	@FXML public void acPinceTube(ActionEvent event)
	{
		if(automLecture.getVar(VariableAuto.running)==0 || automLecture.getVar(VariableAuto.running)==-1)
		actionPinceTube();
		else System.out.println("l'automate est en train de tourner ! Arrêtez le d'abord");
	}
	
}
