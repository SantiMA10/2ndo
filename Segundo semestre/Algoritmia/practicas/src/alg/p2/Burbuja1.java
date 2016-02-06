package alg.p2 ;

/** Este programa sirve para ordenar n elementos 
	con un algoritmo de los "malos" (cuadr�tico)� 
	Es la BURBUJA O INTERCAMBIO DIRECTO */
public class Burbuja1
{
	static int []v;

	public static void main (String arg [] )
	{
		int n=Integer.parseInt(arg[0]);  //tama�o del problema  
		v = new int[n] ;

		Vector.ordenDirecto(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		burbuja(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.ordenInverso(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		burbuja(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe(v);

		Vector.aleatorio(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		burbuja(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe(v);

	} // fin de main

	/**
	 * Intercambia los elementos de las posiciones i, j en el array a
	 */
	private static void intercambiar (int[] a, int i, int j)
	{
		int t;
		t= a[i]; a[i]= a[j]; a[j]= t;
	}

	/**
	 * Ordenaci�n por el m�todo de Burbuja
	 * @param a array de enteros, despu�s de la llamada quedar� ordenado
	 */
	public static void burbuja (int[] a) 
	{
		int n= a.length;
		for (int i=0;i<=n-2;i++) 
			for (int j=n-1;j>i;j--)
				if (a[j-1]>a[j])
					intercambiar (a,j-1,j);
	}  
} 

