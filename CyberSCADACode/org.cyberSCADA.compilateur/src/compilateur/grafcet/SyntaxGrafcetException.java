package compilateur.grafcet;

public class SyntaxGrafcetException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SyntaxGrafcetException(int code){
		switch(code){
		case 0:
			System.out.println("<< Wrong number of step or transition parameters >>\n<< Doesn't respect Grafcet syntax >>");
			break;
		case 1:
			System.out.println("<< Unknow step >>\n<< No step with this name >>");
			break;
		case 2:
			System.out.println("<< Unknow transition >>\n<< No transition with this name >>");
			break;
		}
	}


}
