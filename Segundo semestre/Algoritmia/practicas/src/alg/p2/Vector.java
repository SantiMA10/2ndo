
package alg.p2 ;

import java.util.Random ;

/**
	Clase de utilidad, que genera para un vector los 3 �rdenes 
	iniciales posibles: ordenado, inverso y aleatorio.
	Tambi�n escribe el contenido de un vector
 */
public class Vector
{

	/**	Este m�todo da valores ordenados
	 */
	public static void ordenDirecto (int[]a)
	{
		int n= a.length;
		for(int i=0;i<n;i++) a[i]=i;
	}

	/** 	Este m�todo da valores ordenados
	 */
	public static void ordenInverso (int[]a)
	{
		int n= a.length;
		for(int i=0;i<n;i++) a[i]=n-i-1;
	}     

	/**
	 * Este m�todo da valores aleatorios a un vector de enteros, 
	 * utiliza para ello la clase Random del paquete java.util
	 * @param a - array de enteros que rellena con los aleatorios
	 */
	public static void aleatorio (int[] a)
	{
		aleatorio(a,1000000);
	}
	
	/**
	 * Este m�todo da valores aleatorios a un vector de enteros, 
	 * utiliza para ello la clase Random del paquete java.util
	 * @param a - array de enetros que rellena con aleatorios
	 * @param maxAlea - m�ximo n�mero aleatorio para rellenar
	 */
	public static void aleatorio (int[]a, int maxAlea)
	{
		Random r= new Random ();
		int n= a.length;
		for(int i=0;i<n;i++)
			a[i]=r.nextInt (maxAlea);//valores entre 0 y maxAlea
	}     

	/**	Este m�todo escribe los componentes del vector
	 */
	public static void escribe (int[]a)
	{
		int n= a.length;
		System.out.print("(");
		for (int i=0; i<n; i++ )
			System.out.print(a[i]+", ");
		System.out.println(")");
	}    

}
