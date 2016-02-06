package alg71533682.p12;

public class Bucle5 {
	
	public static long bucle5(int n){
		
		long c = 0;
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				for(int k = 0; k < n; k++){
					for(int l = 0; l < n; l++){
						c++;
					}
				}
			}
		}
		
		return c;
	}
	
	public static void main(String arg[]) {
		long c = 0;
		long t1, t2;
		int nVeces = Integer.parseInt(arg[0]);
		
		System.out.println("Bucle 5");

		for (int n = 2048; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle5(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + c);


		} // for

	}

}
