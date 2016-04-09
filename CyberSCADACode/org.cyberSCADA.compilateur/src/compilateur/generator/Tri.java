package compilateur.generator;

public class Tri {

	public static int[] sort(int i[]){
		
		int s[] = new int[i.length];
		int tampon = 0;
		int e = 0, t = i.length;
		int stack = 0;
		
		while(t > 0){
			while(e < i.length){
				if(i[e] > tampon){
					tampon = i[e];
					stack = e;
				}
				e++;
			}

			s[t-1] = tampon;
			t--;
			tampon = 0;
			e = 0;
			i[stack] = 0;
		}
			
		return s;
	}
	
	public static void main(String args[]){
		int test[] = new int [5];
		int i = 0;
		test[0] = 5;
		test[1] = 3;
		test[2] = 8;
		test[3] = 1;
		test[4] = 4;
	
		
		test = sort(test);
		
		for(i=0;i<5;i++){
			System.out.println(test[i]);
		}
	}
	
	
	
}
