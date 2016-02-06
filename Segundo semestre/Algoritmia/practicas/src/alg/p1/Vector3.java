package alg.p1 ;

/** Este programa sirve para medir tiempos incrementando autom�ticamente
el tama�o del problema
*/
public class Vector3
{
	static int []v;
	
	public static void main (String arg [] )
	{
		long t1, t2;
		
		for (int n=10; n <= 100000000; n*=5) // n se va incrementando *5  
		{
		  System.out.println("n = "+n);
		  v = new int [n] ;
		  Vector1.rellena (v);
		  //  escribe (v);
		  // Medida del tiempo de la operaci�n
		  t1=System.currentTimeMillis();
		  int s=Vector1.suma (v);
		  t2=System.currentTimeMillis();
		  System.out.println ("TAMA�O = "+n+"**"+"TIEMPO = "+(t2-t1));   
		 
		  System.out.println ("LA SUMA DE LOS ELEMENTOS ES = "+ s);
		//  int [] m = new int [2];
		//  maximo (v,m);
		//  System.out.println ("EL MAXIMO ESTA EN POSICION = "+ m[0]);
		//  System.out.println ("EL MAXIMO ES = "+ m[1]);
		
		} // fin de for de tama�o del problema 
	
	} // fin de main

} // fin de clase
