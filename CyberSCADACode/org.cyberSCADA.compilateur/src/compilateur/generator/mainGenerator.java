package compilateur.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import compilateur.grafcet.Grafcet;

public class mainGenerator {

	static Document document = null;
	static Element racine;
	static String source = "C:\\Users\\Werner\\Documents\\test.xml";
	static String out = "C:\\Users\\Werner\\git\\CyberSCADASoftware\\CyberSCADACode\\org.cyberSCADA.compilateur\\src\\compilateur\\grafcet\\out.java";

	static FileWriter writer;
	static Data data;
	static Tri tri;

	static String code ="";

	static Vector<String> tamponEtat = new Vector<String>();
	static Vector<String> tamponLadder = new Vector<String>();

	public static void main(String[] args) throws IOException {

		int i = 0, num = 0, c = 0;
		int nbTotal = 0, tStep[], tTransi[];

		data = new Data();
		tri = new Tri();

		boolean bool = false;
		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			//On crée un nouveau document JDOM avec en argument le fichier XML
			//Le parsing est terminé ;)
			document = sxb.build(new File(source));
		}
		catch(Exception e){}

		if(document == null){System.out.println("ok");}

		//On initialise un nouvel élément racine avec l'élément racine du document.
		racine = document.getRootElement();



		writer = new FileWriter(out);


		code += data.setPackage();
		code += data.imports();
		code += data.classe("Out");
		code += data.varInstance();
		code += data.main();
		code += data.initVariable();

		List varList = racine.getChild("dataBlock").getChildren("variables");
		Iterator iVar = varList.iterator();
		while(iVar.hasNext()){
			Element var = (Element)iVar.next();
			code += data.ajoutVariable(var.getAttributeValue("name"));
		}

		List grafcet = racine.getChildren("SFCProgram");
		Iterator it = grafcet.iterator();

		while(it.hasNext()){
			code += "\n\n/**********************************************************************/\n\n";

			Element courant = (Element)it.next();
			
			String nomGrafcet = courant.getChild("identProgram").getAttribute("name").getValue();

			code += data.comNom(nomGrafcet);
			code += data.varGrafcet(nomGrafcet);
			code += data.initGrafcet(nomGrafcet);


			/*******************************Step*******************************/

			List stepList = courant.getChild("chartSource").getChild("networkSFC").getChildren("step");
			Iterator iStep = stepList.iterator();
			LinkedList <Element> listStep;
			tStep = new int[stepList.size()];
			c = 0;
			while(iStep.hasNext()){

				Element step = (Element)iStep.next();
				tStep[c] = Integer.parseInt(step.getChild("objPosition").getAttributeValue("posY"));
				c ++;

			}
			tStep = tri.sort(tStep);

			iStep = stepList.iterator();
			boolean b = false;
			for(int j : tStep){
				b = false;
				iStep = stepList.iterator();
				while(!b && iStep.hasNext()){
					Element step = (Element)iStep.next();
					if(Integer.valueOf(step.getChild("objPosition").getAttributeValue("posY")) == j){
						b = true;
						code += data.step(nomGrafcet, step.getAttributeValue("stepName"));
					}
				}
			}

			/*******************************Transition*******************************/
			
			List transiList = courant.getChild("chartSource").getChild("networkSFC").getChildren("transition");
			Iterator iTransi = transiList.iterator();

			LinkedList <Element> listTransi;
			tTransi = new int[transiList.size()];
			c = 0;
			while(iTransi.hasNext()){

				Element transi = (Element)iTransi.next();
				tTransi[c] = Integer.parseInt(transi.getChild("objPosition").getAttributeValue("posY"));
				c ++;

			}
			tTransi = tri.sort(tTransi);

			iTransi = transiList.iterator();
			c = 0;
			for(int j : tTransi){
				b = false;
				iTransi = transiList.iterator();
				while(!b && iTransi.hasNext()){
					Element transi = (Element)iTransi.next();
					if(Integer.valueOf(transi.getChild("objPosition").getAttributeValue("posY")) == j){
						b = true;
						if(transi.getChild("transitionCondition").getChild("variableName") != null){
							String name = transi.getChild("transitionCondition").getChildText("variableName");
							System.out.println(name);
							if(name.contains(".x")){
								code += data.transition(nomGrafcet, name);
								tamponEtat.addElement(name);
							}
							else {
								code += data.transition(nomGrafcet, name, name);
							}

							c++;
						}
						else if(transi.getChild("transitionCondition").getChild("sectionName") != null){
							String name = transi.getChild("transitionCondition").getChildText("sectionName");
							System.out.println(name);
							
							code += data.transition(nomGrafcet, name);

							c++;
						}
					}
				}
			}
			for(String s : tamponEtat){
				code += data.addCondition(nomGrafcet, s, s);
			}
			
			tamponEtat = new Vector<String>();
			
			/*******************************Ladder*******************************/
			
			
			//code += data.start();
		}
		code += data.accoladeFin();
		code += data.accoladeFin();
		writer.write(code);
		writer.close();

		//Méthode définie dans la partie 3.2. de cet article
		afficheALL();
	}

	static void afficheALL()
	{
		//On crée une List contenant tous les noeuds "etudiant" de l'Element racine
		List listEtudiants = racine.getChildren("FEFExchangeFile");

		//On crée un Iterator sur notre liste
		Iterator i = listEtudiants.iterator();
		while(i.hasNext())
		{
			//On recrée l'Element courant à chaque tour de boucle afin de
			//pouvoir utiliser les méthodes propres aux Element comme :
			//sélectionner un nœud fils, modifier du texte, etc...
			Element courant = (Element)i.next();
			//On affiche le nom de l’élément courant
			System.out.println(courant.getChild("IOConf").getChild("PLC").getAttributeValue("autoRun"));
		}
	}


}
