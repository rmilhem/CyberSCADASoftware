package compilateur.grafcet;

import java.util.*;

public class Out{
public static Variable variable;

public static void main(String args[]){

variable = new Variable();
variable.addVariable("pres_3_balles", false);
variable.addVariable("pres_tube_remplissage", false);
variable.addVariable("index_plateau", false);
variable.addVariable("Dcy", false);
variable.addVariable("Pap", false);
variable.addVariable("Arret", false);
variable.addVariable("ARU_NC", false);
variable.addVariable("ARU_NO", false);
variable.addVariable("Init", false);
variable.addVariable("Auto", false);
variable.addVariable("Manuel", false);
variable.addVariable("ElectroA_sup", false);
variable.addVariable("ElectroA_inf", false);
variable.addVariable("Moteur_Pap", false);
variable.addVariable("Moteur_brassage", false);
variable.addVariable("position_chargement", false);
variable.addVariable("position_dechargement", false);
variable.addVariable("position_basse", false);
variable.addVariable("position_haute", false);
variable.addVariable("pinces_ouvertes", false);
variable.addVariable("presence_tube_transfert", false);
variable.addVariable("position_prise_bouchon", false);
variable.addVariable("position_pose_bouchon", false);
variable.addVariable("venturi_position_haute", false);
variable.addVariable("venturi_position_basse", false);
variable.addVariable("presence_tube_bouchage", false);
variable.addVariable("presence_bouchon", false);
variable.addVariable("fermer_pinces", false);
variable.addVariable("ouvrir_pinces", false);
variable.addVariable("rotation_dechargement", false);
variable.addVariable("rotation_chargement", false);
variable.addVariable("monter_pinces", false);
variable.addVariable("voyant_vert", false);
variable.addVariable("voyant_rouge", false);
variable.addVariable("descendre_pinces", false);
variable.addVariable("rentrer_verin_bouchon", false);
variable.addVariable("sortir_verin_bouchon", false);
variable.addVariable("monter_venturi", false);
variable.addVariable("descendre_venturi", false);
variable.addVariable("venturi", false);
variable.addVariable("test", false);
variable.addVariable("fermer_pince", false);
variable.addVariable("test2", false);
variable.addVariable("ext_S_2_3_x", false);
variable.addVariable("ext_S_5_10_x", false);
variable.addVariable("ext_S_4_10_x", false);
variable.addVariable("ext_presence_tube_transfert", false);
variable.addVariable("ext_index_plateau", false);
variable.addVariable("ext_mode_auto", false);
variable.addVariable("ext_S_3_6_x", false);
variable.addVariable("ext_temps_vidange", false);
variable.addVariable("in_depart_transfert", false);
variable.addVariable("ext_presence_tube_remplissage", false);
variable.addVariable("ext_dcy", false);
variable.addVariable("in_depart_remplissage", false);
variable.addVariable("in_rotation_moteur", false);
variable.addVariable("in_depart_bouchage", false);
variable.addVariable("ext_attente_tube", false);
variable.addVariable("ext_ElectroA_inf", false);
variable.addVariable("ext_ElectroA_sup", false);
variable.addVariable("ext_Moteur_brassage", false);
variable.addVariable("ext_presence_bouchon", false);
variable.addVariable("ext_presence_tube_bouchage", false);
variable.addVariable("in_ElectroA_sup", false);
variable.addVariable("in_ElectroA_inf", false);
variable.addVariable("in_Moteur_brassage", false);


/**********************************************************************/

//initialisation_PO
Grafcet initialisation_PO;
initialisation_PO = new Grafcet(variable);

initialisation_PO.addStep("S_2_1");
initialisation_PO.addStep("S_2_2");
initialisation_PO.addStep("S_2_3");
initialisation_PO.addTransition("S_2_2.x");
initialisation_PO.addTransition("index_plateau","index_plateau");
initialisation_PO.addTransition("Auto","Auto");
initialisation_PO.getNodeTransition("S_2_2.x").getTransi().setCondition(initialisation_PO.getNodeStep("S_2_2").getStep().getActive());


/**********************************************************************/

//remplissage
Grafcet remplissage;
remplissage = new Grafcet(variable);

remplissage.addStep("S_3_1");
remplissage.addStep("S_3_2");
remplissage.addStep("S_3_3");
remplissage.addStep("S_3_4");
remplissage.addStep("S_3_5");
remplissage.addStep("S_3_6");
remplissage.addTransition("depart_remplissage");
remplissage.addTransition("balle1");
remplissage.addTransition("balle2");
remplissage.addTransition("balle3");
remplissage.addTransition("temps_vidange");
remplissage.addTransition("attente_tube");


/**********************************************************************/

//bouchage
Grafcet bouchage;
bouchage = new Grafcet(variable);

bouchage.addStep("S_4_2");
bouchage.addStep("S_4_3");
bouchage.addStep("S_4_4");
bouchage.addStep("S_4_5");
bouchage.addStep("S_4_6");
bouchage.addStep("S_4_7");
bouchage.addStep("S_4_8");
bouchage.addStep("S_4_9");
bouchage.addStep("S_4_10");
bouchage.addStep("S_4_1");
bouchage.addTransition("depart_bouchage");
bouchage.addTransition("venturi_pos_basse");
bouchage.addTransition("tempo_1");
bouchage.addTransition("venturi_pos_haute");
bouchage.addTransition("pos_pose_bouchon");
bouchage.addTransition("venturi_pos_basse_2");
bouchage.addTransition("tempo_2");
bouchage.addTransition("venturi_pos_haute_2");
bouchage.addTransition("pos_prise_bouchon");
bouchage.addTransition("fin_bouchage");


/**********************************************************************/

//transfert
Grafcet transfert;
transfert = new Grafcet(variable);

transfert.addStep("S_5_1");
transfert.addStep("S_5_2");
transfert.addStep("S_5_3");
transfert.addStep("S_5_4");
transfert.addStep("S_5_5");
transfert.addStep("S_5_6");
transfert.addStep("S_5_7");
transfert.addStep("S_5_8");
transfert.addStep("S_5_9");
transfert.addStep("S_5_10");
transfert.addStep("S_5_11");
transfert.addTransition("depart_transfert");
transfert.addTransition("CI_transfert");
transfert.addTransition("pos_chargement");
transfert.addTransition("pos_basse");
transfert.addTransition("tempo_3");
transfert.addTransition("pos_haute");
transfert.addTransition("pos_dechargement");
transfert.addTransition("pos_basse_2");
transfert.addTransition("pinc_ouvertes");
transfert.addTransition("pos_haute_2");
transfert.addTransition("fin_transfert");


/**********************************************************************/

//gestion_arret
Grafcet gestion_arret;
gestion_arret = new Grafcet(variable);

gestion_arret.addStep("S_1_1");
gestion_arret.addStep("S_1_2");
gestion_arret.addTransition("Dcy","Dcy");
gestion_arret.addTransition("Arret","Arret");
}
}
