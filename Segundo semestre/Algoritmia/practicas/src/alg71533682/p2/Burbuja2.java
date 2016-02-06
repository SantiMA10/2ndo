package alg71533682.p2;

/** Este programa sirve para medir tiempos del 
	algoritmo de ordenacion BURBUJA O INTERCAMBIO DIRECTO
	Lo hace en tres supuestos: 
	1) array inicialmente ordenado 
	2) array inicialmente en orden inverso
	3) array inicialmente aleatorio
 */

public class Burbuja2
{
	static int []v;

	public static void main (String arg [] )
	{ 
		String opcion=arg[0];

		long t1,t2;
		System.out.println(opcion);

		for (int n=10000;n<1280001;n*=2)
		{
			v = new int [n] ;

			if (opcion.compareTo("ordenado")==0)
				Vector.ordenDirecto(v);
			else if (opcion.compareTo("inverso")==0)
				Vector.ordenInverso(v);
			else
				Vector.aleatorio(v);

			t1 = System.currentTimeMillis();
			Burbuja1.burbuja (v);
			t2 = System.currentTimeMillis();

			System.out.println(n+"\t"+(t2-t1));
		} // de for
	} //fin de main
}
