package alg71533682.p2 ;

/** Este programa sirve para ordenar n elementos
	con un algoritmo de los "malos" (cuadrático)· 
	Es la INSERCIÓN DIRECTA
 */
public class Insercion1
{
	static int []v;

	public static void main (String arg [] )
	{
		int n= Integer.parseInt (arg[0]);  //tamaño del problema  
		v = new int [n] ;

		Vector.ordenDirecto (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		insercion(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.ordenInverso (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		insercion(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.aleatorio (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		insercion(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);
	} // fin de main

	/**
	 * Ordenación por inserción directa
	 * @param a array de enteros, después de la llamada quedará ordenado
	 */
	public static void insercion(int[] a) 
	{
		int n= a.length;
		for (int i=1;i<=n-1;i++) 
		{
			int x=a[i];
			int j=i-1;
			while (j>=0 && x<a[j])
			{ 
				a[j+1]=a[j];
				j=j-1;
			}
			a[j+1]=x;
		}
	} 
} 
