package alg71533682.p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class InversionesCudratico {
	
	private BufferedReader bf;

	/**
	 * Calcula el numero de inversiones en el ranking
	 * @return numero de inversiones
	 */
	public long calcularInversiones(Integer[] ranking){
		long numInver = 0;
		for(int i = 0; i < ranking.length; i++){
			for(int j = i+1; j < ranking.length; j++){
				if(j > i && ranking[i] > ranking[j]){
					numInver++;
				}
			}
		}
		
		return numInver;
	} 
	
	public Integer[] leerFichero(String nombre){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		try {
			bf = new BufferedReader(new FileReader(nombre));
			while(bf.ready()){
				lista.add(Integer.parseInt(bf.readLine()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer[] ranking = new Integer[lista.size()];
		lista.toArray(ranking);
		return ranking;
	}

}
