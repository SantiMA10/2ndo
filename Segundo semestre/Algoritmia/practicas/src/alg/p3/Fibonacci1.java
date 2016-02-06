package alg.p3;


public class Fibonacci1
{
	/* Vamos a resolver de 4 formas diferentes la conocida serie 
	 * de Fibonacci: 
	 * 0 1 1 2 3 5 8 13 21 34 55 89 ...
	 */

	/** Primera soluci�n iterativa con complejidad temporal O(n)
	 */
	public static int fib1 (int n) 
	{
		int n1= 0;
		int n2= 1;
		for (int i= 1; i <= n; i++) 
		{
			int s= n1+n2;
			n1= n2;
			n2= s;
		}
		return n1;
	}  

	/** Segunda soluci�n con complejidad temporal O(n) y 
	 * que utiliza un vector.
	 * Este algoritmo se va a ver m�s adelante como un caso muy 
	 * sencillo de PROGRAMACION DINAMICA
	 */
	public static int fib2 (int n, int[]v) 
	{
		v[0] =0;
		v[1]=1;
		for (int i=2; i <= n; i++) 
			v[i]=v[i-1]+v[i-2];
		return v[n];
	}

	/** Primera versi�n recursiva,   
		es T(n) = T(n-1) + O(1), luego el tiempo es O(n).
	 */
	public static int fib3 (int n) 
	{
		return aux(0, 1, n);
	}

	private static int aux (int n1, int n2, int n) 
	{
		if (n < 1) return n1;
		return aux(n2, n1+n2, n-1);
	}


	/** Segunda versi�n recursiva, 
	 * de ecuaci�n T(n) = T(n-1) + T(n-2) + O(1), 
	 * que una vez resuelta es exponencial O(1.6^n), es decir,
	 * cada vez que n aumenta de n a n+1, el tiempo se multiplica por 1.6,
	 * o lo que es lo mismo, suma de los tiempos que tarde para n es la 
	 * sumade los tiempos los dos tama�os precedentes:(n-1) y (n-2).
	 */
	public static int fib4 (int n) 
	{
		if (n<=1)
			return n;
		return fib4(n-1) + fib4(n-2);
	}

	/* METODO MAIN PARA BUEN COMPROBAR FUNCIONAMIENTO */ 
	public static void main (String[] arg) 
	{ 
		int n= Integer.parseInt (arg[0]);

		System.out.println ("El Numero Fibonacci de orden "+n+" es " + fib1(n));
		int[]v = new int[60];  // maximo n (orden) posible = 60
		System.out.println ("El Numero Fibonacci de orden "+n+" es " + fib2(n,v));
		System.out.println ("El Numero Fibonacci de orden "+n+" es " + fib3(n));
		System.out.println ("El Numero Fibonacci de orden "+n+" es " + fib4(n));

	}   // de main

}
