package alg71533682.p1 ;


/** Este programa sirve para medir tiempos incrementando autom�ticamente
el tama�o del problema y adem�s seg�n una escala de tiempos determinada 
por nVeces, que se mete como argumento en ejecuci�n arg[1]
Tambi�n se mete como argumento de ejecuci�n arg[0] la operaci�n sobre la que
queremos centrar la medici�n de tiempos (opciones 0,1,2 respectivamente)
 */

public class Diagonal2
{
	static int [][]a;

	public static void main (String arg [] )
	{

		int nVeces = Integer.parseInt (arg[1]); 
		int opcion = Integer.parseInt (arg[0]);
		long t1,t2;


		for( int n=3; n<= 100000 ; n*=2) // n se va incrementando *2   
		{
			a = new int[n][n] ;
			if (opcion==0)
			{
				t1=System.currentTimeMillis();

				for (int repeticion=1;repeticion<=nVeces;repeticion++)
				{  	
					Diagonal1.rellena(a);
				}
				t2=System.currentTimeMillis();

				  System.out.println(n+"\t"+(t2-t1)+"\t");
			} // fin de if




			else if (opcion==1)
			{
				Diagonal1.rellena (a);
				t1=System.currentTimeMillis();
				int s = 0;
				for (int repeticion=1;repeticion<=nVeces;repeticion++)
				{  	
					s = Diagonal1.suma1Diagonal (a);
				}
				t2=System.currentTimeMillis();

				System.out.println(n+"\t"+(t2-t1)+"\t"+s);
   
			} // fin de else if


			else if (opcion==2)
			{
				Diagonal1.rellena (a);
				t1=System.currentTimeMillis();
				int s = 0;
				for (int repeticion=1;repeticion<=nVeces;repeticion++)
				{  	
					 s=Diagonal1.suma2Diagonal (a);
				}
				t2=System.currentTimeMillis();

				  System.out.println(n+"\t"+(t2-t1)+"\t"+s);
			} // fin de else if


			else System.out.println ("OPCION INCORRECTA"); 

		} // fin de for del tama�o de n 

	} // fin de main

} // fin de la clase
