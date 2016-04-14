package compilateur.grafcet;

import java.util.Enumeration;
import java.util.Hashtable;

public class Grafcet extends Thread{
	
	protected Variable variable;
	private GrafcetMethods gm;
	protected Hashtable<String, NodeComposant> collection;
	protected boolean running = true;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String st[];String tr[];String c[];
		Grafcet g = new Grafcet();
		
		g.addStep(null, "step1");
		
		String c2[], c3[];
		
		st = new String[1];
		st[0] = "step1";//st[1] = "step2";st[2] = "step3";
		tr = new String[3];
		tr[0] = "tr11";tr[1] = "tr12";tr[2] = "tr13";
		c = new String[1];c[0] = "test1";
		c2 = new String[1];c2[0] = "test2";
		c3 = new String[1];c3[0] = "test3";
		g.addTransition(st, tr, c, c2, c3);	

		st = new String[3];
		st[0] = "s21";st[1] = "s22";st[2] = "s23";
		g.addStep("tr11", st[0]);
		g.addStep("tr12", st[1]);
		g.addStep("tr13", st[2]);
		
		g.addTransition("s21", "tr21", "test4");
		g.addTransition("s22", "tr22", "test5");
		g.addTransition("s23", "tr23", "test6");
		
		tr = new String[3];
		tr[0] = "tr21";tr[1] = "tr22";tr[2] = "tr23";
		st = new String[1];
		st[0] = "s3";
		g.addStep(tr, st);
		
		g.addFinalTransition("s3", "tr3", "test7");
		g.start();
	}
	
	public void run(){
		//Launch the first step of the grafcet
		Enumeration<String> en = collection.keys();
		while(en.hasMoreElements()){
			NodeComposant tmp = collection.get(en.nextElement());
			if(tmp.isInitial()){
				tmp.setActive(true);
				break;
			}
		}
		while(running){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			variable.condition.get("test1").set(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			//variable.condition.get("test6").set(true);
			variable.condition.get("test3").set(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			variable.condition.get("test6").set(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			variable.condition.get("test4").set(true);
			variable.condition.get("test5").set(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			variable.condition.get("test7").set(true);

		}
	}

	public Grafcet(){
		collection = new Hashtable<String, NodeComposant>();
		//firstStep = new NodeStep(s);
		//collection.put(s, firstStep);
		//firstStep.setInitial(true);
		variable = new Variable();
		gm = new GrafcetMethods(collection, variable);
	
		variable.condition.put("test1", new Bool(false));
		variable.condition.put("test2", new Bool(false));
		variable.condition.put("test3", new Bool(false));
		variable.condition.put("test4", new Bool(false));
		variable.condition.put("test5", new Bool(false));
		variable.condition.put("test6", new Bool(false));
		variable.condition.put("test7", new Bool(false));
	}
	public Grafcet(Variable v){
		if(v != null)
			variable = v;
		else{//mode test
			variable = new Variable();
			variable.condition.put("s_2_2.x", new Bool(false));
			variable.condition.put("index", new Bool(false));
			variable.condition.put("auto", new Bool(false));
		}
	}

	
	
	/******************** Méthode d'ajout d'étape***********************/
	/**
	 * Add steps "name" at transitions "prev"
	 * "prev" = 1 and "name" > 1 = Divergence ET
	 * "prev" > 1 and "name" = 1 = Convergence OU
	 * @param prev
	 * @param name
	 */
	public void addStep(String prev[], String name[]){
		try {
			gm.addS(prev, name);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Add step "name" at transition "prev"
	 * for a grafcet with one branch
	 * @param prev
	 * @param name
	 */
	public void addStep(String prev, String name){
		try {
			gm.addS(prev, name);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/******************** Méthode d'ajout de transition***********************/
	/**
	 * Add transitions "name" at steps "prev" with the conditions "cond"
	 * "prev" = 1 and "name" > 1 = Divergence OU
	 * "prev" > 1 and "name" = 1 = Convergence ET
	 * ex : addTransition(prev[], name[], cond1[], cond2[])
	 * add "cond1" to the first transition of "name" with a logical AND between the values of "cond1"
	 * @param prev
	 * @param name
	 * @param cond
	 */
	public void addTransition(String prev[], String name[], String[] ... cond ){
		try {
			gm.addT(prev, name, cond);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *  Add transition "name" at step "prev" with the condition "cond"
	 * for a grafcet with one branch
	 * ex : addTransition(prev[], name[], cond[])
	 * add "cond" to the transition "name" with a logical AND between the values of "cond"
	 * @param prev
	 * @param name
	 * @param cond
	 */
	public void addTransition(String prev, String name, String ... cond ){
		try {
			gm.addT(prev, name, cond);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Add the final transitions "name" at steps "prev" with the conditions "cond"
	 * "prev" = 1 and "name" > 1 = Divergence OU
	 * "prev" > 1 and "name" = 1 = Convergence ET
	 * ex : addFinalTransition(prev[], name[], cond1[], cond2[])
	 * add "cond1" to the first transition of "name" with a logical AND between the values of "cond1"
	 * and connect transitions with the initial step of the grafcet to make a loop
	 * @param prev
	 * @param name
	 * @param cond
	 */
	public void addFinalTransition(String prev[], String name[], String[] ... cond){
		try {
			gm.addFT(prev, name, cond);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Add transition "name" at step "prev" with the condition "cond"
	 * for a grafcet with one branch
	 * ex : addTransition(prev[], name[], cond[])
	 * add "cond" to the transition "name" with a logical AND between the values of "cond"
	 * and connect transition with the initial step of the grafcet to make a loop
	 * @param prev
	 * @param name
	 * @param cond
	 */
	public void addFinalTransition(String prev, String name, String ... cond){
		try {
			gm.addFT(prev, name, cond);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/******************** Méthode get ***********************/
	public NodeStep getNodeStep(String name){
		try {
			return gm.getNS(name);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public NodeTransition getNodeTransition(String name){
		try {
			return gm.getNT(name);
		} catch (SyntaxGrafcetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/******************** Méthode first ***********************/
	public void setFirstStep(NodeStep first){
		gm.setFirstStep(first);
	}
	public NodeStep getFirstStep(){
		return gm.getFirstStep();
	}
	public void setFirstTransi(NodeTransition first){
		gm.setFirstTransi(first);
	}
	public NodeTransition getFirstTransi(){
		return gm.getFirstTransi();
	}

	/******************** Méthode d'affichage ***********************/
	/*public String toString(){
		NodeStep tmp[] = new NodeStep[nbMax];
		tmp[0] = firstStep;
		NodeTransition tmp2[] = new NodeTransition[nbMax];
		tmp2[0] = firstTransi;

		String s = "";
		do
		{
			for(NodeStep p : tmp){
				if(p != null)
					s += p.getStep().getName()+"  |  ";
			}
			s +="\n";
			tmp = tmp[0].getNextStep();
			for(NodeTransition t : tmp2){
				if(t != null)
					s += t.getTransition().getName()+"{"+t.getTransition().getCondition()+"}"+t.id+"  |  ";
			}
			s +="\n";
			tmp2 = tmp2[0].getNextTransition();
			
			if(tmp != null && tmp[0].getNextStep() == null){
				for(NodeStep p : tmp){
					if(p != null)
						s += p.getStep().getName()+"  |  ";
				}
				s +="\n";
				if(tmp2 != null){
					for(NodeTransition t : tmp2){
						if(t != null)
							s += t.getTransition().getName()+"  |  ";
					}
					s +="\n";
				}
			}
		}while(tmp[0].isInitial() != true && tmp[0].getNextStep() != null);

		return s;
	}*/

}
