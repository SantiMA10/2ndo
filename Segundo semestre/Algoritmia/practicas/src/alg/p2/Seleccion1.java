package alg.p2 ;

/** Este programa sirve para ordenar n elementos
	con un algoritmo de los "malos" (cuadr�tico)�
	es la SELECCION
 */
public class Seleccion1
{
	static int []v;

	public static void main (String arg [] )
	{
		int n= Integer.parseInt (arg[0]);  //tama�o del problema  
		v = new int [n] ;

		Vector.ordenDirecto (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		seleccion(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.ordenInverso (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		seleccion(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.aleatorio (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		seleccion (v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);
	} // fin de main

	/**
	 * Permuta los elementos de las posiciones i, j en el array v
	 */
	private static void intercambiar (int[] v, int i, int j)
	{
		int t;
		t= v[i]; v[i]= v[j]; v[j]= t;
	}

	/**
	 * Ordenaci�n por selecci�n
	 * @param v array de enteros, despu�s de la llamada quedar� ordenado
	 */
	public static void seleccion (int[] a) 
	{
		int n= a.length;
		int posmin;
		for (int i=0;i<n-1;i++) 
		{
			// Buscar la posicion del mas peque�o de los que quedan
			posmin= i;
			for (int j= i+1; j < n; j++)
				if (a[j] < a[posmin])
					posmin= j;
			// Intercambia el que toca con el m�s peque�o
			intercambiar (a,i,posmin);
		}  // for
	}  
} 
