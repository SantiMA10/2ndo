package alg.p3;


/*
	Es un m�todo recursivo POR SUSTRACCION
	T(n)= 2 T(n-1) + O(1)
	Los par�metros son: a=2; b=1; k=0
	Luego la complejidad temporal es exponencial O(2^n)
	y la complejidad MPILA es O(n), pero no se desborda
	porque mucho antes el tiempo de ejecuci�n se hace 
	intratable
 */
public class Sustraccion3
{

	static long cont;

	public static boolean rec3 (int n)
	{
		if (n<=0) 
			cont++;
		else
		{
			cont++;   // O(1)    
			rec3 (n-1);
			rec3 (n-1);
		}
		return true;   
	}

	public static void main (String arg []) 
	{
		long t1,t2;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true; 
		
		System.out.println("Sustraccion3 nVeces: " + nVeces);
		for (int n=1; n<=100; n++)
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