package alg71533682.p3 ;

import java.util.Random ; 

/**	Este programa resuelve un problema de tres formas diferentes.
	El problema es: calcular la suma de los elementos de un vector
 */
public class SumaVector1
{
	static int []v;

	public static void main (String arg [] )
	{

		int n = Integer.parseInt (arg[0]);

		v=new int[n];
		rellena(v);
		escribe(v);

		System.out.println ("PRIMERA SOLUCION ="+suma1(v));

		System.out.println ("SEGUNDA SOLUCION ="+suma2(v));

		System.out.println ("TERCERA SOLUCION ="+suma3(v));
	} // fin de main

	/**	Este método da valores aleatorios a un vector de enteros, 
		utiliza para ello la clase Random del paquete java.util  
	 */
	public static void rellena(int[]a)
	{
		Random r= new Random();
		int n=a.length;
		for(int i=0;i<n;i++)
			a[i]= r.nextInt(19)-9; //valores entre -9 y 9
	}  // fin de rellena   

	/**	Este método escribe el vector
	 */
	public static void escribe(int[]a)
	{
		int n= a.length;
		for (int i=0; i<n; i++)
			System.out.print (a[i]+"//");
		System.out.println();
	}  // fin de escribe   

	/** Este metodo calcula de forma iterativa la suma del vector
		con una complejidad lineal O(n) */
	public static int suma1(int[]a)
	{
		int n= a.length;
		int s=0;
		for(int i=0;i<n;i++)
			s=s+a[i];
		return s; 
	}  // fin de suma1   

	/** Este método calcula de otra forma recursiva  la suma del
		vector con una complejidad lineal O(n)
	 */
	public static int suma2(int[]a)
	{
		return recSust (0,a);
	}  // fin de suma2   

	private static int recSust(int i, int[]a)
	{
		if (i==a.length) return 0;
		else return a[i]+recSust(i+1,a);
	}  // fin de recSust

	/** Este método calcula de forma recursiva la suma 
		con una complejidad lineal O(n) 
	 */
	public static int suma3 (int[]a)
	{
		return recDiv (0,a.length-1,a);
	}  // fin de suma3Vector

	private static int recDiv(int iz,int de,int[]a)
	{
		if (iz==de)
			return a[iz];
		else
		{ 
			int m= (iz+de)/2;
			return recDiv(iz,m,a)+ recDiv(m+1,de,a);
		}
	}  // fin de recDiv

} // fin de la clase
