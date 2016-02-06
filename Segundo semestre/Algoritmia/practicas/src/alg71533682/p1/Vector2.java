package alg71533682.p1 ;


/** Este programa sirve para medir tiempos de la operacion de suma
**/
public class Vector2
{
	static int []v;
	
	public static void main(String arg [] ){
		
		int n = Integer.parseInt (arg[0]);  //tamaño del problema  
		v = new int [n] ;
		Vector1.rellena (v);
		//  escribe (v);
		  
		// Declara variables de tipo long para recoger el datos de milisegundos
		long t1, t2;
		  
		t1 = System.currentTimeMillis();	// milisegundos actuales antes del código a medir	
		System.out.println("Milisegundo actual: " + t1);
		int s = Vector1.suma (v);
		t2 = System.currentTimeMillis();	// milisegundos actuales después del código a medir
		System.out.println ("TAMAÑO = " + n + " ** " + "TIEMPO = " + (t2 - t1));
	 
		System.out.println ("LA SUMA DE LOS ELEMENTOS ES = " + s);
	  
		//  int [] m = new int [2];
		//  maximo (v,m);
		//  System.out.println ("EL MAXIMO ESTA EN POSICION = "+ m[0]);
		//  System.out.println ("EL MAXIMO ES = "+ m[1]);
	 
	} // fin de main

} // fin de clase
