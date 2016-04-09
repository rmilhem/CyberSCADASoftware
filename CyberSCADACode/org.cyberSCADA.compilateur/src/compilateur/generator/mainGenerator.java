package compilateur.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class mainGenerator {
		
	static Document document = null;
	static Element racine;
	static String source = "E:\\Users\\WERNER\\git\\CyberSCADASoftware\\CyberSCADACode\\org.cyberSCADA.compilateur\\test.xml";
	static String out = "E:\\Users\\WERNER\\git\\CyberSCADASoftware\\CyberSCADACode\\org.cyberSCADA.compilateur\\out.java";
	
	static FileWriter writer;
	static Data data;
	static Tri tri;
	
	static String code ="";

	public static void main(String[] args) throws IOException {
		
		int i = 0, num = 0, c = 0;
		int nbTotal = 0, t[];
		
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
		
		
		
		code += data.imports();
		code += data.main();
		
		
		List grafcet = racine.getChild("FEFExchangeFile").getChildren("SFCProgram");
		Iterator it = grafcet.iterator();
		
		//while(i.hasNext())
		   //{
		      Element courant = (Element)it.next();
		      
		      code += data.comNom(courant.getChild("identProgram").getAttribute("name").getValue());
		  // }
		      		
		
		List stepList = courant.getChild("chartSource").getChild("networkSFC").getChildren("step");
		Iterator iStep = stepList.iterator();
		LinkedList <Element> listStep;
		t = new int[stepList.size()];
		while(iStep.hasNext()){
			
			Element step = (Element)iStep.next();
			t[c] = Integer.parseInt(step.getChild("objPostion").getAttributeValue("posY"));
			c ++;
			t = tri.sort(t);
		}
		
		
		iStep = stepList.iterator();
		while(bool != true && iStep.hasNext()){
			Element step = (Element)iStep.next();
			
			if(step.getAttributeValue("stepType").equals("initialStep")){
				code += data.step(step.getAttributeValue("stepName"), String.valueOf(num));
				code += data.stepIsActive(step.getAttributeValue("stepName"), "true");
				num ++;
				nbTotal ++;
				bool = true;
			}
		}
		iStep = stepList.iterator();
		c = 0;
		while(iStep.hasNext()){
			Element step = (Element)iStep.next();
			
			//listStep.add(c, step.getChild("objPosition").getAttributeValue("posY"))){
			code += data.step(step.getAttributeValue("stepName"), String.valueOf(num));
			
			code += data.stepIsActive(step.getAttributeValue("stepName"), "false");
			num ++;
			nbTotal ++;
			//}
		}
		
		List transiList = courant.getChild("chartSource").getChild("networkSFC").getChildren("transition");
		Iterator iTransi = transiList.iterator();
		
		while(iTransi.hasNext()){
			Element transi = (Element)iTransi.next();
			
			code += data.transition(transi.getChild("objPosition").getAttributeValue("posY"));
			
			nbTotal ++;
		}
		
		while(i < nbTotal){
			i++;
		}
		
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
