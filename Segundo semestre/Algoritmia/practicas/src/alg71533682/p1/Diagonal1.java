package alg71533682.p1 ;

import java.util.Random ;

/** Este programa sirve resolver un problema de tres formas diferentes.
	El problema es calcular la suma de los elementos de la 
	diagonal principal de una matriz cuadrada de orden n
**/

public class Diagonal1
{
	static int [][]a;

	public static void main (String arg [] )
	{

		int n = Integer.parseInt (arg[0]);

		a = new int[n][n] ;

		rellena (a);

		escribe (a);

		System.out.println ("PRIMERA SOLUCION ="+suma1Diagonal(a));

		System.out.println ("SEGUNDA SOLUCION ="+suma2Diagonal(a));

	} // fin de main


	/**	Este método da valores aleatorios a un vector de enteros, 
		utiliza para ello la clase Random del paquete java.util  
		Tiene una complejidad cuadrática
	 **/
	public static void  rellena (int[][]a)
	{
		Random r= new Random();
		int n=a.length;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				a[i][j]= r.nextInt(199)-99; //valores entre -99 y 99

	}  // fin de rellena   


	/**	Este método escribe la matriz cuadrada a
		Tiene una complejidad cuadrática
	 */
	public static void escribe (int[][]a)
	{
		int n= a.length;
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
				System.out.print (a[i][j]+"//");
			System.out.println ();
		}

	}  // fin de escribe   


	/** Este metodo calcula de forma iterativa la suma de la diagonal
		Tiene una complejidad cuadrática 
	 */
	public static int suma1Diagonal (int[][]a)
	{
		int n= a.length;
		int s=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if (i==j) s=s+a[i][j];
		return s; 
	}  // fin de suma1Diagonal   


	/**  Este método calcula de otra forma iterativa  la suma de
		la diagonal. 
		Tiene una complejidad lineal 
	 */
	public static int suma2Diagonal (int[][]a)
	{
		int n= a.length;
		int s=0;
		for(int i=0;i<n;i++)
			s=s+a[i][i];
		return s; 
	}  // fin de suma2Diagonal   

} // fin de la clase
