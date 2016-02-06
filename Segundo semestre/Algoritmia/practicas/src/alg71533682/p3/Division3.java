package alg71533682.p3;

/**
	Es un método recursivo POR DIVISION
	Los parámetros son: a=2; b=2; k=0
	Luego la complejidad temporal es lineal O(n)
	y la complejidad MPILA es O(log n), por lo que
	por mucho que crezca n no se desbordará
 */
public class Division3
{

	static long cont;

	public static boolean rec3 (int n)
	{
		if (n<=0) 
			cont++;
		else
		{ 
			cont++ ; // O(1)    
			rec3 (n/2);
			rec3 (n/2);
		}
		return true;   
	}

	public static void main (String arg []) 
	{
		long t1,t2;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true;

		System.out.println("Division3 nVeces="+nVeces);
		for (int n=30000; n<=100000000; n*=2)
		{
			t1 = System.currentTimeMillis ();

			for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
			{ 
				cont=0;
				b=rec3 (n);
			} 

			t2 = System.currentTimeMillis ();

			System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+b);
		}  // for

	} // main
} //class