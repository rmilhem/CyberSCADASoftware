package compilateur.grafcet;

import java.util.Hashtable;

public class Grafcet extends Thread {
	
	private NodeStep firstStep;
	private NodeStep tmpStep[];
	private NodeTransition firstTransi;
	private NodeTransition tmpTransi[];
	private static Variable variable;
	private boolean running = true;
	private static int nbMax = 1;
	private static Hashtable<String, NodeComposant> collection;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String st[];String tr[];String c[];
		Grafcet g = new Grafcet("step1");
		String c2[], c3[];
		
		st = new String[1];
		st[0] = "step1";
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



	public Grafcet(String s){
		collection = new Hashtable<String, NodeComposant>();
		firstStep = new NodeStep(s);
		collection.put(s, firstStep);
		firstStep.setInitial(true);
		variable = new Variable();
	
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

	public void run(){
		firstStep.setActive(true);
		//firstTransi.setActive(true);;
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

	/******************** Méthode d'ajout d'étape / transition***********************/
	public void addStep(String prev[], String name[]){
		int c = 0;
		if(prev == null){
			firstStep = new NodeStep(name[0]);
			firstStep.setInitial(true);
			collection.put(name[0], firstStep);
		}
		else{
			//debut ET ou suite normale
			if(prev.length == 1){
				tmpStep = new NodeStep[name.length];
				for(String s : name){
					tmpStep[c] = new NodeStep(s, 0);
					tmpTransi = new NodeTransition[1];
					tmpTransi[0] = getNodeTransition(prev[0]);
					
					tmpStep[c].setPrevTransition(tmpTransi);
					collection.put(s, tmpStep[c]);
					c++;
				}
				tmpTransi[0].setNextStep(tmpStep);
			}
			//fin OU
			else if(name.length == 1){
				tmpStep = new NodeStep[1];
				tmpStep[0] = new NodeStep(name[0], 0);
				collection.put(name[0], tmpStep[0]);
				tmpTransi = new NodeTransition[prev.length];
				for(String s : prev){
					tmpTransi[c] = getNodeTransition(s);
					tmpTransi[c].setNextStep(tmpStep);
					c++;
				}
				tmpStep[0].setPrevTransition(tmpTransi);
			}
			else{
				
			}
		}
	}
	public void addStep(String prev, String name){
		if(prev == null){
			firstStep = new NodeStep(name);
			firstStep.setInitial(true);
			collection.put(name, firstStep);
		}
		else{
			//suite normale
			tmpStep = new NodeStep[1];
			tmpStep[0] = new NodeStep(name, 0);
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = getNodeTransition(prev);

			tmpStep[0].setPrevTransition(tmpTransi);
			collection.put(name, tmpStep[0]);
			tmpTransi[0].setNextStep(tmpStep);
		}
	}
	public void addTransition(String prev[], String name[], String[] ... cond ){
		int c = 0;
		if(prev == null){
			firstStep = new NodeStep(name[0]);
			firstStep.setInitial(true);
			collection.put(name[0], firstStep);
		}
		else{
			//debut OU ou suite normale
			if(prev.length == 1){
				tmpTransi = new NodeTransition[name.length];
				for(String s : name){
					tmpTransi[c] = new NodeTransition(s);
					tmpStep = new NodeStep[1];
					tmpStep[0] = getNodeStep(prev[0]);
					
					tmpTransi[c].setPrevStep(tmpStep);
					collection.put(s, tmpTransi[c]);
					c++;
				}
				if(tmpStep[0] == null)
					System.out.println("probleme");
				tmpStep[0].setNextTransition(tmpTransi);
			}
			//fin ET
			else if(name.length == 1){
				tmpTransi = new NodeTransition[1];
				tmpTransi[0] = new NodeTransition(name[0]);
				collection.put(name[0], tmpTransi[0]);
				tmpStep = new NodeStep[prev.length];
				for(String s : prev){
					tmpStep[c] = getNodeStep(s);
					tmpStep[c].setNextTransition(tmpTransi);
					c++;
				}
				tmpTransi[0].setPrevStep(tmpStep);
			}
			else{
				
			}
		}
		
		c = 0;
		int d = 0;
		//pour chaque transition
		for(String s[] : cond){
			//pour chaque condition
			Bool b[] = new Bool[s.length];
			for(String ss : s){
				if(ss.contains(".x")){
					String p = ss.substring(0, ss.length()-2);
					b[d] = getNodeStep(p).getActive();
				}
				else{
					b[d] = variable.condition.get(ss);
				}
				d++;
			}
			d = 0;
			getNodeTransition(name[c]).getTransition().setCondition(new Bool(b));
			c++;
		}
	}
	public void addTransition(String prev, String name, String ... cond ){
		if(prev == null){
			firstStep = new NodeStep(name);
			firstStep.setInitial(true);
			collection.put(name, firstStep);
		}
		else{
			//suite normale
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = new NodeTransition(name);
			tmpStep = new NodeStep[1];
			tmpStep[0] = getNodeStep(prev);

			tmpTransi[0].setPrevStep(tmpStep);
			collection.put(name, tmpTransi[0]);
			
			tmpStep[0].setNextTransition(tmpTransi);
		}

		int d = 0;
			Bool b[] = new Bool[cond.length];
			//pour chaque condition
			for(String ss : cond){
				if(ss.contains(".x")){
					String p = ss.substring(0, ss.length()-2);
					b[d] = getNodeStep(p).getActive();
				}
				else{
					b[d] = variable.condition.get(ss);
				}
				d++;
			}
			getNodeTransition(name).getTransition().setCondition(new Bool(b));
	}
	public void addFinalTransition(String prev[], String name[], String[] ... cond){
		int c = 0;
		//a fusionner
		//suite normale
		if(prev.length == 1 && name.length == 1){
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = new NodeTransition(name[0]);
			tmpStep = new NodeStep[1];
			tmpStep[0] = getNodeStep(prev[0]);

			tmpTransi[0].setPrevStep(tmpStep);
			collection.put(name[0], tmpTransi[c]);
			tmpStep[0].setNextTransition(tmpTransi);
			
			tmpStep = new NodeStep[1];
			tmpStep[0] = firstStep;
			tmpTransi[0].setNextStep(tmpStep);
			firstStep.setPrevTransition(tmpTransi);
		}
		//fin ET
		else if(name.length == 1){
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = new NodeTransition(name[0]);
			collection.put(name[0], tmpTransi[0]);
			for(String s : prev){
				tmpStep = new NodeStep[1];
				tmpStep[c] = getNodeStep(s);
				tmpStep[c].setNextTransition(tmpTransi);
				c++;
			}
			tmpTransi[0].setPrevStep(tmpStep);
			
			tmpStep = new NodeStep[1];
			tmpTransi = new NodeTransition[1];
			tmpStep[0] = firstStep;
			tmpTransi[0].setNextStep(tmpStep);
			firstStep.setNextTransition(tmpTransi);
		}
		else{

		}
		
		c = 0;
		int d = 0;
		//pour chaque transition
		for(String s[] : cond){
			//pour chaque condition
			Bool b[] = new Bool[s.length];
			for(String ss : s){
				if(ss.contains(".x")){
					String p = ss.substring(0, ss.length()-2);
					b[d] = getNodeStep(p).getActive();
				}
				else{
					b[d] = variable.condition.get(ss);
				}
				d++;
			}
			d = 0;
			getNodeTransition(name[c]).getTransition().setCondition(new Bool(b));
			c++;
		}
	}
	public void addFinalTransition(String prev, String name, String ... cond){
		//suite normale
		tmpTransi = new NodeTransition[1];
		tmpTransi[0] = new NodeTransition(name);
		tmpStep = new NodeStep[1];
		tmpStep[0] = getNodeStep(prev);

		tmpTransi[0].setPrevStep(tmpStep);
		collection.put(name, tmpTransi[0]);

		tmpStep[0].setNextTransition(tmpTransi);
		
		tmpStep = new NodeStep[1];
		tmpStep[0] = firstStep;
		tmpTransi[0].setNextStep(tmpStep);
		tmpStep[0].setPrevTransition(tmpTransi);

		int d = 0;
		Bool b[] = new Bool[cond.length];
			//pour chaque condition
			for(String ss : cond){
				if(ss.contains(".x")){
					String p = ss.substring(0, ss.length()-2);
					b[d] = getNodeStep(p).getActive();
				}
				else{
					b[d] = variable.condition.get(ss);
				}
				d++;
			}
			getNodeTransition(name).getTransition().setCondition(new Bool(b));
	}

	/******************** Méthode get ***********************/
	public NodeStep getNodeStep(String name){
		return (NodeStep) collection.get(name);
	}
	public NodeTransition getNodeTransition(String name){
		return (NodeTransition) collection.get(name);
	}

	/******************** Méthode first ***********************/
	public void setFirstStep(NodeStep first){
		this.firstStep = first;
	}
	public NodeStep getFirstStep(){
		return firstStep;
	}
	public void setFirstTransi(NodeTransition first){
		this.firstTransi = first;
	}
	public NodeTransition getFirstTransi(){
		return firstTransi;
	}

	/******************** Méthode d'affichage ***********************/
	public String toString(){
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
	}

}
