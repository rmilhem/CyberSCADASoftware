package pcControle.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import pcControle.data.AutomLecture;
import pcControle.ihm.IHM_Pingpong;
import pcControle.right.Monitor;
import pcControle.varAuto.VariableAuto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ScadaController implements Initializable{


	AutomLecture automLecture;
	AutomObserver observer;
	Monitor monitor;
	
	HashMap<Node, VariableAuto> widgets;
	@FXML private Label Accumulateur;
	// automate bouchons
	@FXML private Circle capteurTubeBouchonsC;
	@FXML private Circle stockBouchonsC;
	// automate bales
	@FXML private Circle acHautC;
	@FXML private Circle acBasC;
	@FXML private Circle acBallesC;
	@FXML private Circle enMarcheC;
	@FXML private Circle enRotationC;
	@FXML private Circle capteurTubeBallesC;
	// automate pince tube
	@FXML private Circle tubePlateauC; // n'apparait nul part
	@FXML private Circle stockTubesC;
	
	@FXML private Button marche;
	@FXML private Button acHautB;
	@FXML private Button acBasB;
	@FXML private Button acBallesB;
	@FXML private Button remplir;
	@FXML private Button tournerPlateau;	
	@FXML private Button acPinces;
	@FXML private Button quitter;
	@FXML private Button boucher;
	

	
	
	private IHM_Pingpong application;
	IHM_Pingpong IHM = new IHM_Pingpong();
	//private int accumulator=0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
	public void init(AutomLecture autom, Monitor monitor, int lang) throws IOException
	{
		setLang(lang);
		automLecture = autom;
		this.monitor = monitor;
		observer = new AutomObserver(this);
		autom.addObserver(observer);
		widgets = new HashMap<Node, VariableAuto>();
		
		fillWidgetsArray(widgets);
		// construction de l'IHM
		
		
		
	}
	
	// Passage un peu chiant mais nécessaire pour l'implémentation
	// de IHM Builder. La suite se fait plus facilement.
	// Cette table d'association pourra servir d'ailleurs à minimiser le
	// nombre de fonction set ci-dessous. 
	private void fillWidgetsArray(HashMap<Node, VariableAuto> widgets) {
		// On a Ommis: accumulateur, back, tubeEnStock, tubeSurPlateau
		widgets.put(acPinces, VariableAuto.actionPinces);
		widgets.put(tournerPlateau, VariableAuto.tournerPlateau);
		widgets.put(acBasC, VariableAuto.actionneurChuteBas);
		widgets.put(acBasB, VariableAuto.actionneurChuteBas);
		widgets.put(acHautC, VariableAuto.actionneurChuteHaut);
		widgets.put(acHautB, VariableAuto.actionneurChuteHaut);
		widgets.put(boucher, VariableAuto.boucher);
		widgets.put(capteurTubeBallesC, VariableAuto.presenceTubeBalle);
		widgets.put(enMarcheC, VariableAuto.running);
		widgets.put(enRotationC, VariableAuto.tournerPlateau);
		widgets.put(acBallesC, VariableAuto.moteurBalle);
		widgets.put(acBallesB, VariableAuto.moteurBalle);
		widgets.put(capteurTubeBouchonsC, VariableAuto.presenceTubeBouchons);
		widgets.put(remplir, VariableAuto.remplissage);
		widgets.put(marche, VariableAuto.running);
		widgets.put(stockBouchonsC, VariableAuto.capteurBouchons);
	
		
		
	}
	public HashMap<Node, VariableAuto> getWidgets(){
		return widgets;
	}
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
		setOnOff(VariableAuto.actionPinces);
	}
	public void boucher()
	{
		setOnOff(VariableAuto.boucher);
	}
	public void refreshAll()
	{
		//----------------------------- automate bales-------------------------------------
		setColorCircle(automLecture.getVar(VariableAuto.presenceTubeBalle), capteurTubeBallesC);
		setColorCircle(automLecture.getVar(VariableAuto.actionneurChuteHaut), acHautC);
		setColorCircle(automLecture.getVar(VariableAuto.actionneurChuteBas), acBasC);
		setColorCircle(automLecture.getVar(VariableAuto.moteurBalle), acBallesC);
		setColorCircle(automLecture.getVar(VariableAuto.running), enMarcheC);
		setColorCircle(automLecture.getVar(VariableAuto.tournerPlateau), enRotationC);
		setColorCircle(automLecture.getVar(VariableAuto.presenceTubeBouchons), capteurTubeBouchonsC);
		setColorCircle(automLecture.getVar(VariableAuto.capteurBouchons), stockBouchonsC);
		setColorCircle(automLecture.getVar(VariableAuto.stock_tube), stockTubesC);

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

	
	@FXML public void Boucher(ActionEvent event){
		boucher();
		
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
		System.out.println("prevenir werner !");
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
	@FXML private Label titre; @FXML private Label autBouchons; @FXML private Label autBalles; @FXML private Label autTubes;
	@FXML private Label accL; @FXML private Label capteurTubeBouchonsL; @FXML private Label stockBouchonsL; @FXML private Label err;
	@FXML private Label capteurTubeBallesL; @FXML private Label acHautL; @FXML private Label acBasL; @FXML private Label acBallesL;
	@FXML private Label enRotationL; @FXML private Label enMarcheL; @FXML private Label stockTubesL; @FXML private Label tubePlateauL;
	@FXML private Label oui; @FXML private Label non;
	private void setLang(int lang) throws IOException {
		BufferedReader fichier=null;
		String[] trad=new String[23];
        String ligne;
        try{
        	fichier = new BufferedReader(new FileReader(new File("src/pcControle/controller/trad.txt")));
        }
        catch(FileNotFoundException exc){
        	System.out.println("Erreur d'ouverture");
        }
		ligne=fichier.readLine();
		int i=0;
		while (ligne!=null){
			String[] l=ligne.split("\t");
			trad[i]=l[lang];
			ligne=fichier.readLine();
			i++;
		}
		fichier.close();
		/* Automate Bouchons*/
		autBouchons.setText(trad[0]);
		capteurTubeBouchonsL.setText(trad[1]);
		stockBouchonsL.setText(trad[2]);
		boucher.setText(trad[3]);
		/* Automate Balles*/
		autBalles.setText(trad[4]);
		capteurTubeBallesL.setText(trad[1]);
		acHautL.setText(trad[5]);
		acBasL.setText(trad[6]);
		acBallesL.setText(trad[7]);
		acHautB.setText(trad[5]);
		acBasB.setText(trad[6]);
		acBallesB.setText(trad[7]);
		remplir.setText(trad[8]);
		/*Automate Tubes*/
		tubePlateauL.setText(trad[9]);
		stockTubesL.setText(trad[10]);
		autTubes.setText(trad[11]);
		acPinces.setText(trad[12]);
		/*General*/
		titre.setText(trad[13]);
		enRotationL.setText(trad[14]);
		marche.setText(trad[15]);
		enMarcheL.setText(trad[16]);
		tournerPlateau.setText(trad[17]);
		quitter.setText(trad[18]);
		accL.setText(trad[19]);
		oui.setText(trad[20]);
		non.setText(trad[21]);
		err.setText(trad[22]);
	}
}