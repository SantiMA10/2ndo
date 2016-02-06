package alg71533682.p2;

/** Este programa sirve para medir tiempos del algoritmo 
	de ordenacion INSERCI�N DIRECTA
	Lo hace en tres supuestos: 
	1) array inicialmente ordenado 
	2) array inicialmente en orden inverso
	3) array inicialmente aleatorio
 */
public class Insercion2
{
	static int []v;

	public static void main (String arg [] )
	{ 
		String opcion=arg[0];
		long t1,t2;

		for (int n=1280000;n<1000000000;n*=2)
		{
			v = new int [n] ;
			System.out.println(opcion);
			if (opcion.compareTo("ordenado")==0)
			{
				Vector.ordenDirecto (v);
				t1 = System.currentTimeMillis();
				for (int nVeces=1;nVeces<=1000;nVeces++)
					Insercion1.insercion(v);
				t2 = System.currentTimeMillis();
				System.out.println(n+"\t"+(t2-t1));
				//				System.out.println ("n="+n+"**TIEMPO="+(t2-t1)+ " MICROSEGUNDOS");
			}  
			else if (opcion.compareTo("inverso")==0)
			{
				Vector.ordenInverso (v);
				t1 = System.currentTimeMillis();
				Insercion1.insercion(v);
				t2 = System.currentTimeMillis();
				System.out.println(n+"\t"+(t2-t1));
//				System.out.println ("n="+n+"**TIEMPO="+(t2-t1));
			}
			else 
			{
				Vector.aleatorio (v);
				t1 = System.currentTimeMillis();
				Insercion1.insercion(v);
				t2 = System.currentTimeMillis();
				System.out.println(n+"\t"+(t2-t1));
//				System.out.println ("n="+n+"**TIEMPO="+(t2-t1));
			}
		} // de for
	} //fin de main
}