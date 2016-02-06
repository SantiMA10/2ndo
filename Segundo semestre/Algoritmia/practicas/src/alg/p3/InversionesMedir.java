package alg.p3;


import java.util.concurrent.ForkJoinPool;

public class InversionesMedir {

	private static InversionesCudratico iC = new InversionesCudratico();
	private static InversionesDV iDV = new InversionesDV();
	private static InversionesParalelo iP;
			
	public static void main(String[] args) {
		
		long t1, t2, b = 0;
		int nVeces= Integer.parseInt (args [1]);
		String op = args [0];
		if(op.equals("C")){
			for(int v = 1; v <=7; v++){
				System.out.println("Inversion, fichero: " + v);

				
				t1 = System.currentTimeMillis ();

				for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
				{ 
					b = iC.calcularInversiones(iC.leerFichero("datos/ranking"+v+"v2.txt"));
				} 

				t2 = System.currentTimeMillis ();

				System.out.println (nVeces+ "\t\t"+(t2-t1)+"\t\t"+b);
			}
		}
		else if(op.equals("DV")){
			
			for(int v = 1; v <=7; v++){
				
				System.out.println("InversionDV, fichero: " + v);
				
				Integer[] vector = iDV.leerFichero("src/alg71533682/datos/ranking"+v+"v2.txt");
				
				t1 = System.currentTimeMillis ();

				for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
				{ 
					b = iDV.calcularInversionesDV(vector.clone());
				} 

				t2 = System.currentTimeMillis ();

				System.out.println (nVeces+ "\t\t"+(t2-t1)+"\t\t"+b);
			}
		}
		else if(op.equals("P")){
			for(int v = 1; v <=7; v++){
				
				System.out.println("InversionParalelo, fichero: " + v);
				
				Integer[] vector = iDV.leerFichero("src/alg71533682/datos/ranking"+v+"v2.txt");
				iP = new InversionesParalelo(vector, 0, vector.length-1);
				ForkJoinPool pool = new ForkJoinPool();
				
				t1 = System.currentTimeMillis ();

				for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
				{ 	
					pool.invoke(iP);
					b = iP.numInv;
				} 

				t2 = System.currentTimeMillis ();

				System.out.println (nVeces+ "\t\t"+(t2-t1)+"\t\t"+b);
			}
		}
		else if(op.equals("DV-n")){
							
				System.out.println("InversionDV con vector aleatorio");
				Integer[] vector;
				
				int inicio = 7500;
				int tMax = 3840000;

				for (int tVectores = inicio; tVectores<=tMax; tVectores*=2)
				{ 
					vector = new Integer[tVectores];
					for(int i = 0; i < vector.length; i++){
						vector[i] = (int) (Math.random()*500000);
					}
					t1 = System.currentTimeMillis ();
					
					for (int repeticiones=1; repeticiones<=nVeces;repeticiones++){
						b = iDV.calcularInversionesDV(vector.clone());
					}
					
					t2 = System.currentTimeMillis ();
					System.out.println (tVectores+ "\t\t"+(t2-t1)+"\t\t"+b);
				} 

		}
		else if(op.equals("P-n")){
				
			System.out.println("InversionParalelo con vector aleatorio");
			Integer[] vector;
			ForkJoinPool pool = new ForkJoinPool(4);
			
			int inicio = 3840000;
			int tMax = 3840000;

			for (int tVectores = inicio; tVectores<=tMax; tVectores*=2)
			{ 	
				vector = new Integer[tVectores];
				for(int i = 0; i < vector.length; i++){
					vector[i] = (int) (Math.random()*500000);
				}
				t1 = System.currentTimeMillis ();
				for (int repeticiones=1; repeticiones<=nVeces;repeticiones++){
					iP = new InversionesParalelo(vector.clone(), 0, vector.length-1);
					pool.invoke(iP);
				}
				t2 = System.currentTimeMillis ();
				b = iP.numInv;
				System.out.println (tVectores+ "\t\t"+(t2-t1)+"\t\t"+b);
			} 
			
		}
		
	}

}
