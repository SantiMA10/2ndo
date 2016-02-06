package alg71533682.p3;

/** Permite medir tiempos de la 4 formas vistas de resolver Fibonacci
 * Hay que meter dos parametros: el algoritmo a ejecutar (1..4)
 * y la escala de tiempos nVeces
 */
public class Fibonacci2 
{

	public static void main (String[] arg) 

	{ 
		int opcion= Integer.parseInt (arg[0]);
		int nVeces= Integer.parseInt (arg[1]);

		long t1,t2;
		int solucion=0;
		int[]v = new int [60];  // hace falta para el segundo metodo   

		System.out.println("Fibo"+opcion+" nVeces: " + nVeces);

		
		for (int n=10; n<60; n++)  // n posibles
		{    
			if (opcion==1)
			{    

				t1= System.currentTimeMillis();

				for (int repeticiones=1; repeticiones<=nVeces; repeticiones++) 
				{solucion=Fibonacci1.fib1(n);}  

				t2= System.currentTimeMillis();
				System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+solucion);
			}    

			else if (opcion==2)
			{ 

				t1= System.currentTimeMillis();

				for (int repeticiones=1; repeticiones<=nVeces; repeticiones++) 
				{solucion=Fibonacci1.fib2(n,v);}  

				t2= System.currentTimeMillis();
				System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+solucion);
			}

			else if (opcion==3)
			{ 
				
				t1= System.currentTimeMillis();

				for (int repeticiones=1; repeticiones<=nVeces; repeticiones++) 
				{solucion=Fibonacci1.fib3(n);}  

				t2= System.currentTimeMillis();
				System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+solucion);
			}    

			else if (opcion==4)
			{
				
				t1= System.currentTimeMillis();

				for (int repeticiones=1; repeticiones<=nVeces; repeticiones++) 
				{solucion=Fibonacci1.fib4(n);}  

				t2= System.currentTimeMillis();
				System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+solucion);
			}

			else System.out.println ("REVISE OPCION 1..4");

		} // de for

	}   // de main
} // de clase
