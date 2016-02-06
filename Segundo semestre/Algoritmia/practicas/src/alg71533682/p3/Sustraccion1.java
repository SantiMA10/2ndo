package alg71533682.p3;

/** 
	Clase que modeliza T(n)= T(n-1) + O(1)
	La complejidad temporal es O(n) y 
	el gasto de pila es Mpila=O(n)
	En consecuencia la pila se desborda 
 */
public class Sustraccion1
{

	static long cont;

	public static boolean rec1 (int n)
	{
		if (n<=0) 
			cont++;
		else 
		{ 
			cont++;  // O(1)=O(n^0)
			rec1 (n-1);
		}
		return true;   
	}

	public static void main (String arg []) 
	{
		long t1, t2;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true;
		
		System.out.println("Sustraccion1 nVeces: " + nVeces);
		for (int n=300; n<=38400; n*=2)
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