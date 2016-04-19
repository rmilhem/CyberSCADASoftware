package compilateur.grafcet;

import java.util.Hashtable;

public class GrafcetMethods {

	private Hashtable<String, NodeComposant> collection;
	private Variable variable;
	private NodeStep tmpStep[];
	private NodeTransition tmpTransi[];
	private NodeStep firstStep;
	private NodeTransition firstTransi;

	public GrafcetMethods(Hashtable<String, NodeComposant> c, Variable v) {
		this.collection = c;
		this.variable = v;
	}



	public void addS(String prev[], String name[], int[] ... coord) throws SyntaxGrafcetException{
		int c = 0;
		if(prev == null){
			firstStep = new NodeStep(name[0]);
			firstStep.setInitial(true);
			collection.put(name[0], firstStep);
			if(coord.length == 2){
				firstStep.setX(coord[0][0]);
				firstStep.setY(coord[0][1]);

			}
		}
		else{
			//Divergence ET or simple branch
			if(prev.length == 1){
				tmpStep = new NodeStep[name.length];
				for(String s : name){
					tmpStep[c] = new NodeStep(s);
					if(coord.length != 0){
						tmpStep[c].setX(coord[c][0]);
						tmpStep[c].setY(coord[c][1]);
					}
					tmpTransi = new NodeTransition[1];
					tmpTransi[0] = getNT(prev[0]);

					tmpStep[c].setPrevTransition(tmpTransi);
					collection.put(s, tmpStep[c]);
					c++;
				}
				tmpTransi[0].setNextStep(tmpStep);
			}
			//Convergence OU
			else if(name.length == 1){
				tmpStep = new NodeStep[1];
				tmpStep[0] = new NodeStep(name[0]);
				if(coord.length != 0){
					tmpStep[0].setX(coord[0][0]);
					tmpStep[0].setY(coord[0][1]);
				}
				collection.put(name[0], tmpStep[0]);
				tmpTransi = new NodeTransition[prev.length];
				for(String s : prev){
					tmpTransi[c] = getNT(s);
					tmpTransi[c].setNextStep(tmpStep);
					c++;
				}
				tmpStep[0].setPrevTransition(tmpTransi);
			}
			else{
				throw new SyntaxGrafcetException(0);
			}
		}
	}
	public void addS(String prev, String name, int ... coord) throws SyntaxGrafcetException{
		if(prev == null){
			firstStep = new NodeStep(name);
			firstStep.setInitial(true);
			collection.put(name, firstStep);
			if(coord.length == 2){
				firstStep.setX(coord[0]);
				firstStep.setY(coord[1]);

			}
		}
		else{
			//simple branch
			tmpStep = new NodeStep[1];
			tmpStep[0] = new NodeStep(name);
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = getNT(prev);

			tmpStep[0].setPrevTransition(tmpTransi);
			collection.put(name, tmpStep[0]);
			tmpTransi[0].setNextStep(tmpStep);

			if(coord.length == 2){
				tmpStep[0].setX(coord[0]);
				tmpStep[0].setY(coord[1]);

			}
		}

	}

	public void addT(String prev[], String name[], String[] ... cond ) throws SyntaxGrafcetException{
		int c = 0;
		if(prev == null){
			firstStep = new NodeStep(name[0]);
			collection.put(name[0], firstStep);
		}
		else{
			//Divergence OU or simple branch
			if(prev.length == 1){
				tmpTransi = new NodeTransition[name.length];
				for(String s : name){
					tmpTransi[c] = new NodeTransition(s);
					tmpStep = new NodeStep[1];
					tmpStep[0] = getNS(prev[0]);

					tmpTransi[c].setPrevStep(tmpStep);
					collection.put(s, tmpTransi[c]);
					c++;
				}
				tmpStep[0].setNextTransition(tmpTransi);
			}
			//Convergence ET
			else if(name.length == 1){
				tmpTransi = new NodeTransition[1];
				tmpTransi[0] = new NodeTransition(name[0]);
				collection.put(name[0], tmpTransi[0]);
				tmpStep = new NodeStep[prev.length];
				for(String s : prev){
					tmpStep[c] = getNS(s);
					tmpStep[c].setNextTransition(tmpTransi);
					c++;
				}
				tmpTransi[0].setPrevStep(tmpStep);
			}
			else{
				throw new SyntaxGrafcetException(0);
			}
		}

		c = 0;
		int d = 0;
		//for each transition
		for(String s[] : cond){
			//for each condition
			Bool b[] = new Bool[s.length];
			for(String ss : s){
				if(ss.contains(".x")){
					String p = ss.substring(0, ss.length()-2);
					b[d] = getNS(p).getActive();
				}
				else{
					b[d] = variable.condition.get(ss);
				}
				d++;
			}
			d = 0;
			getNT(name[c]).getTransition().setCondition(new Bool(b));
			c++;
		}
	}
	public void addT(String prev, String name, String ... cond ) throws SyntaxGrafcetException{
		if(prev == null){
			firstStep = new NodeStep(name);
			collection.put(name, firstStep);
		}
		else{
			//simple branch
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = new NodeTransition(name);
			tmpStep = new NodeStep[1];
			tmpStep[0] = getNS(prev);

			tmpTransi[0].setPrevStep(tmpStep);
			collection.put(name, tmpTransi[0]);

			tmpStep[0].setNextTransition(tmpTransi);
		}

		int d = 0;
		Bool b[] = new Bool[cond.length];
		//for each condition
		for(String ss : cond){
			if(ss.contains(".x")){
				String p = ss.substring(0, ss.length()-2);
				b[d] = getNS(p).getActive();
			}
			else{
				b[d] = variable.condition.get(ss);
			}
			d++;
		}
		getNT(name).getTransition().setCondition(new Bool(b));
	}
	public void addFT(String prev[], String name[], String[] ... cond) throws SyntaxGrafcetException{
		int c = 0;
		//simple branch
		if(prev.length == 1 && name.length == 1){
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = new NodeTransition(name[0]);
			tmpStep = new NodeStep[1];
			tmpStep[0] = getNS(prev[0]);

			tmpTransi[0].setPrevStep(tmpStep);
			collection.put(name[0], tmpTransi[c]);
			tmpStep[0].setNextTransition(tmpTransi);

			tmpStep = new NodeStep[1];
			tmpStep[0] = firstStep;
			tmpTransi[0].setNextStep(tmpStep);
			firstStep.setPrevTransition(tmpTransi);
		}
		//Convergence ET
		else if(name.length == 1){
			tmpTransi = new NodeTransition[1];
			tmpTransi[0] = new NodeTransition(name[0]);
			collection.put(name[0], tmpTransi[0]);
			for(String s : prev){
				tmpStep = new NodeStep[1];
				tmpStep[c] = getNS(s);
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
			throw new SyntaxGrafcetException(0);
		}

		c = 0;
		int d = 0;
		//for each transition
		for(String s[] : cond){
			//for each condition
			Bool b[] = new Bool[s.length];
			for(String ss : s){
				if(ss.contains(".x")){
					String p = ss.substring(0, ss.length()-2);
					b[d] = getNS(p).getActive();
				}
				else{
					b[d] = variable.condition.get(ss);
				}
				d++;
			}
			d = 0;
			getNT(name[c]).getTransition().setCondition(new Bool(b));
			c++;
		}
	}
	public void addFT(String prev, String name, String ... cond) throws SyntaxGrafcetException{
		//simple branch
		tmpTransi = new NodeTransition[1];
		tmpTransi[0] = new NodeTransition(name);
		tmpStep = new NodeStep[1];
		tmpStep[0] = getNS(prev);

		tmpTransi[0].setPrevStep(tmpStep);
		collection.put(name, tmpTransi[0]);

		tmpStep[0].setNextTransition(tmpTransi);

		tmpStep = new NodeStep[1];
		tmpStep[0] = firstStep;
		tmpTransi[0].setNextStep(tmpStep);
		tmpStep[0].setPrevTransition(tmpTransi);

		int d = 0;
		Bool b[] = new Bool[cond.length];
		//for each condition
		for(String ss : cond){
			if(ss.contains(".x")){
				String p = ss.substring(0, ss.length()-2);
				b[d] = getNS(p).getActive();
			}
			else{
				b[d] = variable.condition.get(ss);
			}
			d++;
		}
		getNT(name).getTransition().setCondition(new Bool(b));
	}


	public NodeStep getNS(String name) throws SyntaxGrafcetException{
		NodeStep tmp = (NodeStep) collection.get(name);
		if(tmp == null){
			throw new SyntaxGrafcetException(1);
		}
		return tmp;
	}
	public NodeTransition getNT(String name) throws SyntaxGrafcetException{
		NodeTransition tmp = (NodeTransition) collection.get(name);
		if(tmp == null){
			throw new SyntaxGrafcetException(2);
		}
		return tmp;
	}

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

}
