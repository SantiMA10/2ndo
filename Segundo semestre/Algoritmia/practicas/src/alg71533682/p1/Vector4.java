package alg71533682.p1 ;

/** Este programa sirve para medir tiempos de la operaci�n suma.
Va incrementando autom�ticamente el tama�o del problema y adem�s 
seg�n una escala de tiempos determinada 
por nVeces, que se mete como argumento en ejecuci�n
*/

public class Vector4
{
	static int []v;
	
	public static void main (String arg [] )
	{
		int nVeces = Integer.parseInt (arg[1]); 
		int opcion = Integer.parseInt (arg[0]);
		
		long t1,t2;
		
		if(opcion == 0){
			for ( int n=10; n<= 100000000 ; n*=3){
				System.out.println("Rellena");

				v = new int [n] ;
				Vector1.rellena (v);
			  			 
				t1=System.currentTimeMillis();
				int [] m = new int[2];

				for (int repeticion=1; repeticion <= nVeces; repeticion++){  	
					Vector1.rellena(v);
				}
			  
				t2=System.currentTimeMillis();
				System.out.println(n+"\t"+(t2-t1)+"\t"+m);
			}
		}
		else if(opcion == 1){
			for ( int n=10; n<= 100000000 ; n*=3){
				System.out.println("Suma");

				v = new int [n] ;
				Vector1.rellena (v);
				  				 
				t1=System.currentTimeMillis();
				int [] m = new int[2];
				int s = 0;

				for (int repeticion=1; repeticion <= nVeces; repeticion++){  	
					s = Vector1.suma(v);
				}
				 
				t2=System.currentTimeMillis();
				System.out.println(n+"\t"+(t2-t1)+"\t"+m+"\t"+s);
			}
		}
		else if(opcion == 2){
			for ( int n=10; n<= 100000000 ; n*=3){
				System.out.println("Maximo");
				
				v = new int [n] ;
				Vector1.rellena (v);
			  			 
				t1=System.currentTimeMillis();
				int [] m = new int[2];

				for (int repeticion=1; repeticion <= nVeces; repeticion++){  	
					Vector1.maximo(v, m);
				}
			  
				t2=System.currentTimeMillis();
				System.out.println(n+"\t"+(t2-t1)+"\t"+m);
		}
	}
		
		} 
	
	} 

