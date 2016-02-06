package alg71533682.p4;

public class DevoradorMedir {

	public static void main(String[] args) {
		Devorador d = new Devorador();
		Tarea[] t;
		
		int nVeces = 100000, total=1280;
		long t1, t2;
		
		for(int size = 10; size < total; size*=2){
			t1 = System.currentTimeMillis();
			t = d.generarAleatorio(size);

			for(int i = 0; i < nVeces; i++){
				d.maxBeneficion(t.clone());
			}
			
			t2 = System.currentTimeMillis();
			System.out.println(size+"\t"+(t2-t1));
		}
	}

}
