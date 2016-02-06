package alg71533682.p2 ;

/** Este programa sirve para ordenar n elementos con un algoritmo QUICKSORT
	Pero como selecciona como pivote el elemento primero de la partición, si se
	le mete el array ordenado tiene un comportamiento fatal (cuadrático). 
 */
public class RapidoFatal
{
	static int []v;

	public static void main (String arg [] )
	{
		System.out.println ("VAMOS A PROBAR QUE FUNCIONA");
		int n= Integer.parseInt(arg[0]);  //tamaño del problema  
		v = new int [n] ;

//		Vector.ordenDirecto (v);
//		System.out.println ("VECTOR A ORDENAR ES");
//		Vector.escribe (v);	
//		rapido2(v);
//		System.out.println ("VECTOR ORDENADO ES");
//		Vector.escribe (v);
//
//		Vector.ordenInverso (v);
//		System.out.println ("VECTOR A ORDENAR ES");
//		Vector.escribe (v);	
//		rapido2(v);
//		System.out.println ("VECTOR ORDENADO ES");
//		Vector.escribe (v);
//
//		Vector.aleatorio (v);
//		System.out.println ("VECTOR A ORDENAR ES");
//		Vector.escribe (v);	
//		rapido2(v);
//		System.out.println ("VECTOR ORDENADO ES");
//		Vector.escribe (v);

		// VAMOS AHORA  PROBAR QUE ES CUADRATICO (FATAL) 
		// EN EL CASO ORDENADO SELECIONANDO COMO PIVOTE
		// EL PRIMERO EN CADA PARTICION
		System.out.println ("TIEMPOS CUADRATICOS PARA EL CASO FATAL COMENTADO");	
		long t1,t2;

		for (n=10000;n<1000000000;n*=2)
		{
			v = new int [n] ;

			Vector.ordenInverso (v);

			t1 = System.currentTimeMillis();

			for (int nVeces=1;nVeces<=1000;nVeces++) rapido2(v); // microsegundos

			t2 = System.currentTimeMillis();

			System.out.println (n+"\t"+(t2-t1));

		} // de for

	} //fin de main


	private static void intercambiar(int[]v,int i,int j)
	//es O(1)
	{
		int t;
		t= v[i];v[i]=v[j];v[j]=t;
	}

	/** Deja el	pivote en una posición tal que a su izquierda no hay 
		ningún mayor, ni a la derecha ningún menor.
		es un proceso lineal O(n). 
	 */
	private static int particion2 (int[] v, int iz, int de) 
	{
		int i, pivote;
		pivote= v[iz]; //el pivote es el primero
		i= iz;
		for (int s= iz+1; s <= de; s++) 
			if (v[s] <= pivote) 
			{
				i++;
				intercambiar(v,i,s);
			}
		intercambiar(v,iz,i);
		//se restituye el pivote donde debe estar
		return i; // retorna la posicion en que queda el pivote 
	}

	private static void rapirec2 (int[] v, int iz, int de) 
	{
		int m;
		if (de>iz) 
		{
			m=particion2(v,iz,de);
			rapirec2(v,iz,m-1);
			rapirec2(v,m+1,de);
		}
	}

	public static void rapido2 (int[] v) 
	{
		rapirec2(v,0,v.length-1);
	}
} 

