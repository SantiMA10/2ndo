package alg71533682.p3;

public class Sustraccion4 {

	/*
	Es un m?todo recursivo POR SUSTRACCION
	T(n)= 2 T(n-1) + O(1)
	Los par?metros son: a=3; b=2; k=2
	Luego la complejidad temporal es exponencial O(3^(n/2))
 */
	
	static long cont;

	public static boolean rec4 (int n)
	{
		if (n<=0) 
			cont++;
		else
		{
			for (int i=1;i<n;i++){
				for(int j = 1; j < n; j++){
					cont++ ;  //O(n^2)  
				}
			}    
			rec4 (n-2);
			rec4 (n-2);
			rec4 (n-2);
		}
		return true;   
	}

	public static void main (String arg []) 
	{
		long t1,t2;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true; 
		
		System.out.println("Sustraccion4 nVeces: " + nVeces);
		for (int n=1; n<=100; n++)
		{
			t1 = System.currentTimeMillis ();

			for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
			{ 
				cont=0;
				b=rec4 (n);
			} 

			t2 = System.currentTimeMillis ();

			System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+b);
		}  // for
	} // main
}
