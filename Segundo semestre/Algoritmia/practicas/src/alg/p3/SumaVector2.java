package alg.p3;


/** Este programa sirve para medir tiempos incrementando autom�ticamente
	el tama�o del problema y adem�s seg�n una escala de tiempos 
	determinada por nVeces, que se mete como argumento en ejecuci�n
 */
public class SumaVector2
{
	static int []v;

	public static void main (String arg [] )
	{
		int nVeces = Integer.parseInt (arg[1]); 
		int opcion = Integer.parseInt (arg[0]);
		long t1,t2;
		int s=0;

		System.out.println("SumVec"+opcion+" nVeces: " + nVeces);

		for( int n=3; n<= 100000 ; n*=2) // n se va incrementando *2   
		{
			v = new int[n] ;
			SumaVector1.rellena (v);

			if (opcion==1)
			{

				t1=System.currentTimeMillis();

				for (int repeticion=1;repeticion<=nVeces;repeticion++)
				{  	
					s=SumaVector1.suma1 (v);
				}
				t2=System.currentTimeMillis();

				System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+s);
			} // fin de if

			else if (opcion==2)
			{

				
				t1=System.currentTimeMillis();


				for (int repeticion=1;repeticion<=nVeces;repeticion++)
				{  	
					s=SumaVector1.suma2 (v);
				}

				t2=System.currentTimeMillis();

				System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+s);
			} // fin de if

			else if (opcion==3)
			{

				
				t1=System.currentTimeMillis();

				for (int repeticion=1;repeticion<=nVeces;repeticion++)
				{  	
					s=SumaVector1.suma3(v);
				}

				t2=System.currentTimeMillis();

				System.out.println (n+ "\t\t"+(t2-t1)+"\t\t"+s);
			} // fin de if 

			else System.out.println ("OPCION INCORRECTA"); 

		} // fin de for del tama�o de n 
	} // fin de main


} // fin de la clase
