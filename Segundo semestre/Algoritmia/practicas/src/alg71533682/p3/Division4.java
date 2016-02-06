package alg71533682.p3;

public class Division4 {

	/**
	Es un m�todo recursivo POR DIVISION
	Los par�metros son: a=2; b=2; k=1
	Luego la complejidad temporal es O(nlogn)
	y la complejidad MPILA es O(log n), por lo que
	por mucho que crezca n no se desbordar�
    */
	
	static long cont;

	public static boolean rec4 (int n)
	{
		if (n<=0) cont++;
		else
		{ 
			for (int i=1;i<n;i++){
				for(int j = 1; j < i; j++){
					for(int k= 1; k < k; k++){
						for(int l = 1; l < k; l++){
							cont++ ;  //O(n^4)  
						}
					}
				}
			}
			rec4 (n/2);
			rec4 (n/2);
			rec4 (n/2);
			rec4 (n/2);
		}   
		return true;
	}
	
	public static void main (String arg []) 
	{
		long t1,t2;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true;
		
		System.out.println("Division4 nVeces="+nVeces);
		for (int n=600; n<=100000000; n*=2)
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
