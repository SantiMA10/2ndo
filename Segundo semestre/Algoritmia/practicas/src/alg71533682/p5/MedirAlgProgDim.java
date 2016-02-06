package alg71533682.p5;

public class MedirAlgProgDim {

	public static void main(String[] args) {
		AlgProgDim pD = new AlgProgDim();
		int[] v;
		
		/** El metodo comerPienso puede recibir un segundo parametro de tipo boolean que si es true
		 * muestra una traza del proceso.
		 * Tambien se puede utilizar el metodo imprimirTabla().
		**/
		
		System.out.println("pienso01.txt");
		v = pD.leerFichero("datos/pienso01.txt");	
		System.out.println("Listilla come: "+pD.comerPienso(v)+"\n");
		
		System.out.println("pienso02.txt");
		v = pD.leerFichero("datos/pienso02.txt");	
		System.out.println("Listilla come: "+pD.comerPienso(v)+"\n");
		
		System.out.println("pienso03.txt");
		v = pD.leerFichero("datos/pienso03.txt");
		System.out.println("Listilla come: "+pD.comerPienso(v)+"\n");
		
		System.out.println("pienso04.txt");
		v = pD.leerFichero("datos/pienso04.txt");
		System.out.println("Listilla come: "+pD.comerPienso(v)+"\n");
		
		System.out.println("pienso05.txt");
		v = pD.leerFichero("datos/pienso05.txt");
		System.out.println("Listilla come: "+pD.comerPienso(v)+"\n");
	}

}
