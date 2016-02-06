package alg.p3;

/**
	Es un m�todo recursivo POR DIVISION
	Los par�metros son: a=1; b=3; k=1
	Luego la complejidad temporal es lineal O(n)
	y la complejidad MPILA es O(log n), por lo que
	por mucho que crezca n no se desbordar�
 */
public class Division1
{

	static long cont;

	public static boolean rec1 (int n)
	{ 
		if (n<=0) 
			cont++;
		else
		{ 
			for (int i=1;i<n;i++) cont++ ;  //O(n)    
			rec1 (n/3);
		}
		return true;   
	}

	public static void main (String arg []) 
	{
		long t1,t2;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true;
		
		System.out.println("Division1 nVeces="+nVeces);
		for (int n=30000; n<=100000000; n*=2)
		{
			t1 = System.currentTimeMillis ();

			for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
			{ 
				cont=0;
				b=rec1 (n);
			} 

			t2 = System.currentTimeMillis ();

			System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+b);
		}  // for
	} // main
} //class