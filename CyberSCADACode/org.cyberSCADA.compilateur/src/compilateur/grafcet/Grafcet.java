package compilateur.grafcet;

public class Grafcet extends Thread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String st[];String tr[];String c[];
		Grafcet g = new Grafcet("step1");

		tr = new String[1];
		tr[0] = "tr1";
		c = new String[1];
		c[0] = "step1.x";
		g.addTransition(tr, c);	
		//System.out.println(g.getFirstTransi());
		//g.getNodeTransition("tr1").getTransi().setCondition(variable.condition.get("s_2_2.x"));
		//System.out.println("cond : "+g.getNodeTransition("tr1").getTransi().getCondition());
		st = new String[3];
		st[0] = "s21";st[1] = "s22";st[2] = "s23";
		g.addStep(st);
		
		tr = new String[3];
		tr[0] = "tr21";tr[1] = "tr22";tr[2] = "tr23";
		c = new String[1];
		String c2[] = new String[1];
		String c3[] = new String[1];
		c[0] = "test1";c2[0] = "test2";c3[0] = "test3";
		g.addTransition(tr, c,c2,c3);
		
		st = new String[3];
		st[0] = "s31";st[1] = "s32";st[2] = "s33";
		g.addStep(st);
		
		tr = new String[3];
		tr[0] = "tr31";tr[1] = "tr32";tr[2] = "tr33";
		c = new String[1];
		c2 = new String[1];
		c3 = new String[1];
		c[0] = "test4";c2[0] = "test5";c3[0] = "test6";
		g.addTransition(tr, c,c2,c3);
		
		st = new String[1];
		st[0] = "s41";
		g.addFinalStep(st);
		
		tr = new String[1];
		tr[0] = "tr4";
		c = new String[1];
		c[0] = "test4";
		g.addFinalTransition(tr);
		
		/*g.getNodeTransition("tr31").getTransition().setCondition(new Bool(g.getNodeStep("s41").getActive().get()));
		g.getNodeTransition("tr32").getTransition().setCondition(new Bool(g.getNodeStep("s41").getActive().get()));
		g.getNodeTransition("tr33").getTransition().setCondition(new Bool(g.getNodeStep("s41").getActive().get()));*/

		g.getNodeTransition("tr4").getTransition().setCondition(variable.condition.get("test4"));

		/*NodeStep tt = g.getNodeStep("step4");
		if(tt != null){System.out.println("tr1 ok");}
		System.out.println(g.getNodeTransition("tr1").getTransi());*/
		
		//currentStep = new NodeStep[nbMax];
		
		//System.out.println(g);
		g.start();


	}

	private NodeStep firstStep;
	private NodeStep tmpStep[];
	private NodeTransition firstTransi;
	private NodeTransition tmpTransi[];
	private static Variable variable;
	private boolean running = true;
	private static int nbMax = 1;


	public Grafcet(String s){

		firstStep = new NodeStep(s);
		firstStep.setInitial(true);
		variable = new Variable();
		variable.condition.put("test1", new Bool(false));
		variable.condition.put("test2", new Bool(false));
		variable.condition.put("test3", new Bool(false));
		variable.condition.put("test4", new Bool(false));
		variable.condition.put("test5", new Bool(false));
		variable.condition.put("test6", new Bool(false));
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
		firstStep.start();
		firstTransi.setActive(true);;
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
			variable.condition.get("test6").set(true);
			variable.condition.get("test3").set(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			variable.condition.get("test2").set(true);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("10s");
			variable.condition.get("test4").set(true);
			variable.condition.get("test5").set(true);



		}
	}

	/******************** Méthode d'ajout d'étape / transition***********************/
	public void addStep(String name[]){
		if(firstStep == null){
			firstStep = new NodeStep(name[0]);
			firstStep.setInitial(true);
		}
		else{
			nbMax = name.length > nbMax ?  name.length : nbMax;
			NodeStep node[] = new NodeStep[nbMax];
			int c = 0;
			for(String s : name){
				node[c] = new NodeStep(s, c);
				node[c].setPrevStep(getLastStep());
				c++;
			}
			for(NodeStep t : getLastStep()){
				if(t != null){
					t.setNextStep(node);
				}
			}
		}
	}
	public void addFinalStep(String name[]){
		nbMax = name.length > nbMax ?  name.length : nbMax;
		NodeStep node[] = new NodeStep[nbMax];
		NodeStep nodeInit[] = new NodeStep[1];
		nodeInit[0] = firstStep;
		int c = 0;
		for(String s : name){
			node[c] = new NodeStep(s, c);
			node[c].setPrevStep(getLastStep());
			node[c].setNextStep(nodeInit);
			c++;
		}
		firstStep.setPrevStep(node);
		for(NodeStep t : getLastStep()){
			if(t != null){
				t.setNextStep(node);
			}
		}
	}
	public void addTransition(String name[], String[] ... cond ){
		Bool b;
		nbMax = name.length > nbMax ?  name.length : nbMax;
		NodeTransition transi[] = new NodeTransition[name.length];
		if(firstTransi == null){
			firstTransi = new NodeTransition(name[0], 1);
			transi[0] = firstTransi;
			transi[0].setInitial(true);
		}
		else{
			int c= 0;
			for(String s : name){
				transi[c] = new NodeTransition(s, name.length, c);
				transi[c].setPrevTransition(getLastTransition());
				c++;
			}
			for(NodeTransition t : getLastTransition()){
				if(t != null)
					t.setNextTransition(transi);
			}			
		}
		int c = 0;
		//pour chaque transition
		for(String s[] : cond){
			//pour chaque condition
			for(String ss : s){
				if(ss.contains(".x")){
					String p = ss.substring(0, ss.length()-2);
					b = getNodeStep(p).getActive();
				}
				else{
					b = variable.condition.get(ss);
				}
				transi[c].getTransition().setCondition(b);
			}
			c++;
		}
	}
	public void addFinalTransition(String name[]){
		nbMax = name.length > nbMax ?  name.length : nbMax;
		NodeTransition transi[] = new NodeTransition[name.length];
		NodeTransition transiInit[] = new NodeTransition[1];
		transiInit[0] = firstTransi;
		int c = 0;
		for(String s : name){
			transi[c] = new NodeTransition(s, name.length, c);
			transi[c].setPrevTransition(getLastTransition());
			transi[c].setNextTransition(transiInit);
			c++;
		}
		firstStep.setPrevTransition(transi);
		for(NodeTransition t : getLastTransition()){
			if(t != null){
				t.setNextTransition(transi);
			}
		}
	}

	/******************** Méthode get ***********************/
	public NodeStep getNodeStep(String name){
		tmpStep = new NodeStep[nbMax];
		tmpStep[0] = firstStep;

		do {
			for(NodeStep p : tmpStep){
				if(p != null && p.getStep().name.equals(name)){
					return p;
				}
			}
			tmpStep = tmpStep[0].getNextStep();
		}while(!tmpStep[0].isInitial() && tmpStep[0].getNextStep() != null);
		for(NodeStep p : tmpStep){
			if(p.getStep().name.equals(name)){
				return p;
			}
		}
		return null;
	}
	public NodeTransition getNodeTransition(String name){
		tmpTransi = new NodeTransition[nbMax];
		tmpTransi[0] =	firstTransi;

		do {
			for(NodeTransition t : tmpTransi){
				if(t != null && t.getTransition().name.equals(name)){
					return t;
				}
			}
			tmpTransi = tmpTransi[0].getNextTransition();
		}while(!tmpTransi[0].isInitial() && tmpTransi[0].getNextTransition() != null);
		for(NodeTransition t : tmpTransi){
			if(t.getTransition().name.equals(name)){
				return t;
			}
		}
		return null;
	}

	public NodeStep[] getLastStep(){
		tmpStep = new NodeStep[nbMax];
		tmpStep[0] = firstStep;
		boolean b = true;
		while(tmpStep[0].getNextStep() != null && b)
		{
			tmpStep = tmpStep[0].getNextStep();
			if(tmpStep[0].isInitial()){
				b = false;
				tmpStep = tmpStep[0].getPrevStep();
			}
		}
		return tmpStep;
	}
	public NodeTransition[] getLastTransition(){
		tmpTransi = new NodeTransition[nbMax];
		tmpTransi[0] = firstTransi;
		boolean b = true;
		while(tmpTransi[0].getNextTransition() != null && b)
		{
			tmpTransi = tmpTransi[0].getNextTransition();
			if(tmpTransi[0].isInitial()){
				b = false;
				tmpTransi = tmpTransi[0].getPrevTransition();
			}
		}
		return tmpTransi;
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
