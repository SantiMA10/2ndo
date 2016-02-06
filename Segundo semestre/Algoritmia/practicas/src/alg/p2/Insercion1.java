package alg.p2 ;

/** Este programa sirve para ordenar n elementos
	con un algoritmo de los "malos" (cuadr�tico)� 
	Es la INSERCI�N DIRECTA
 */
public class Insercion1
{
	static int []v;

	public static void main (String arg [] )
	{
		int n= Integer.parseInt (arg[0]);  //tama�o del problema  
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
	 * Ordenaci�n por inserci�n directa
	 * @param a array de enteros, despu�s de la llamada quedar� ordenado
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
