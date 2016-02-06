package alg.p12;

public class Bucle2 {

	public static long bucle2(int n)
	/*
	 * algoritmo cuadr�tico O(n^2)
	 */
	{
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				cont++;
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
		int nVeces = Integer.parseInt(arg[0]);
		
		System.out.println("Bucle 2");

		for (int n = 8; n <= 100000; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle2(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + c);
			
		} // for

	} // main
} // class