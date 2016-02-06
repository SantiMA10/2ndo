/*
	JAVA es sensible a min�sculas y mayusculas (min�scula != may�scula)
	Es norma que una clase comience por letra may�scula.
	Los m�todos y todo tipo de variables comienzan por min�scula.
	 
	Las clases JAVA se guardan en ficheros con el mismo nombre
	al que se a�ade la extensi�n Nombre.java.
	
	Los paquetes deben estar en directorio del mismo nombre, esto es,
	el paquete alg77777777.s1 debe estar en el directorio
	alg77777777\s1. 
*/

package alg.p1 ;

import java.util.Random ; //es la clase que genera n�meros aleatorios

/** Este programa sirve para trabajar con vectores y ver c�mo se 
	ejecutan programas en JAVA   */

public class Vector1{
	
	static int []v;
	
	public static void main(String arg [] ) {
		int n = Integer.parseInt(arg[0]);  //tama�o del problema
		
	  	v = new int[n] ;
	  
	  	rellena(v);
	  	escribe(v);
	  
	  	int s = suma(v);
	  	System.out.println ("LA SUMA DE LOS ELEMENTOS ES = " + s);
	  
	  	int[] m = new int[2];
	  	maximo(v, m);
	  	System.out.println ("EL MAXIMO ESTA EN POSICION = " + m[0]);
	  	System.out.println ("EL MAXIMO ES = " + m[1]);
	
	} // fin de main
	
	
	/** Este m�todo da valores aleatorios a un vector de enteros, 
		utiliza para ello la clase Random del paquete java.util  
	**/
	public static void rellena(int[] a) {
		Random r = new Random();
	  	int n = a.length;
	  	for(int i = 0; i < n; i++) {
	  		a[i] = r.nextInt(199) - 99;//valores entre -99 y 99
	  	}
	
	}  // fin de rellena   
	
	
	/** Este m�todo saca el contenido por pantalla
	**/
	public static void escribe(int[] a) {
		int n = a.length;
	  	for(int i = 0; i < n; i++) {
	  		System.out.println ("COMPONENTE DE ORDEN " + i + " = " + a[i]);
	  	}
	  	System.out.println();
	
	}  // fin de escribe   
	
	
	/** Este m�todo suma los elementos de un vector y la devuelve
	*/
	public static int suma(int[] a) {
		int s = 0;
		int n = a.length;
		for(int i = 0; i < n; i++) {
			s = s + a[i]; 
		} 
		return s;
	
	}  // fin de suma
	
	
	/** Este m�todo calcula el m�ximo y su posici�n y los devuelve.
	*/
	public static void maximo(int[] a, int[] m) {
		int n = a.length;
		m[0] = 0; // posici�n inicial
		m[1] = a[0];
		for(int i = 1; i < n; i++) {
		   if(a[i] > m[1]) {
			   m[0] = i;
			   m[1] = a[i];
	     }
	  }
	    
	}  // fin de maximo

}  // fin de clase
