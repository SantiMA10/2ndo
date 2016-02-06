package alg71533682.p2 ;

/** Este programa sirve para ordenar n elementos
	con el algoritmo mejor. Es el QUICKSORT
 */
public class Rapido1
{
	static int []v;

	public static void main (String arg [] )
	{
		int n= Integer.parseInt (arg[0]);  //tamaño del problema  
		v = new int [n];

		Vector.ordenDirecto (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		rapido(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.ordenInverso (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		rapido(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.aleatorio (v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe (v);	
		rapido(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

	} // fin de main

	/**
	 * Intercambia los elementos de las posiciones i, j en el array a
	 * es O(1)
	 */
	private static void intercambiar (int[] v, int i, int j)
	{
		int t;
		t=v[i];v[i]=v[j];v[j]=t;
	}

	/** Deja el	pivote en una posicion tal que a su izquierda no 
		hay ningún mayor, ni a la derecha ningun menor.
		Es un proceso lineal O(n).  
	 */
	private static int particion(int[]v,int iz,int de) 
	{
		int i, pivote;
		intercambiar (v, (iz+de)/2,iz);
		//el pivote es el de centro y se cambia con el primero
		pivote= v[iz]; 
		i= iz;
		for (int s= iz+1; s <= de; s++) 
			if (v[s] <= pivote) 
			{
				i++;
				intercambiar(v,i,s);
			}
		intercambiar(v,iz,i);//se restituye el pivote donde debe estar
		return i; // retorna la posicion en que queda el pivote 
	}

	/**
	 * Ordenación por el método rápido (quicksort)
	 * Método divide y vencerás de complejidad estudiada en clase
	 */  
	private static void rapirec (int[] v, int iz, int de) 
	{
		int m;
		if (de>iz) 
		{
			m=particion(v,iz,de);
			rapirec(v,iz,m-1);
			rapirec(v,m+1,de);
		}
	}

	public static void rapido (int[] v) 
	{
		rapirec(v,0,v.length-1);
	}
} 
