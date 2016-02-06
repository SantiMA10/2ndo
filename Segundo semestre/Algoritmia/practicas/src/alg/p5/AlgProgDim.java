package alg.p5;

import java.io.BufferedReader;
import java.io.FileReader;
public class AlgProgDim {
	public Pienso[][] tabla;
	
	@SuppressWarnings("resource")
	public int[] leerFichero(String ruta){
		int[] resultado = null;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(ruta));
			int i = 0;
			while(bf.ready()){
				String linea = bf.readLine();
				if(i == 0){
					resultado = new int[Integer.parseInt(linea)];
				}
				else{
					resultado[i-1] = Integer.parseInt(linea);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public void imprimirTabla(){
		String sTabla = "";
		for(int i = 0; i < tabla.length; i++){
			for(int j = 0; j < tabla[i].length; j++){
				if(tabla[i][j] != null){
					sTabla += tabla[i][j];
				}
				else{
					sTabla += 0;
				}
				if(j < tabla[i].length-1){
					sTabla += ",\t";
				}
			}
			System.out.println(sTabla);
			sTabla = "";
		}
	}
	
	public int comerPienso(int [] vector){
		return comerPienso(vector, false);
	}
	
	public int comerPienso(int[] vector, boolean debug){
		int n = vector.length;
		tabla = new Pienso[n][n];
		for(int i = 0; i < n-1; i++){
			tabla[i][i+1] = new Pienso(Math.max(vector[i], vector[i+1]));
		}
		int j;
		for(int d = 3 ; d < n; d += 2){
			for(int i = 0; i < n - d; i++){
				j = i + d;
				if (debug){ 
					System.out.println("");
					System.out.println("Pi+1="+vector[i+1]+", Pj="+vector[j]);
					System.out.println("Pj-1="+vector[j-1]+", Pi="+vector[i]);
					imprimirTabla(); 
				}
				
				int valor = Math.max(
						vector[i+1] > vector[j] ? 
								vector[i] + tabla[i+2][j].valor : vector[i] + tabla[i+1][j-1].valor,
						vector[j-1] > vector[i] ? 
								vector[j] + tabla[i][j-2].valor : vector[j] + tabla[i+1][j-1].valor);
				
				if(vector[i+1] > vector[j] 
						&& vector[i] + tabla[i+2][j].valor == valor){
					tabla[i][j] = new Pienso(vector[i] + tabla[i+2][j].valor,
							tabla[i+2][j].cubos, vector[i]);
				}
				else if(vector[i] + tabla[i+1][j-1].valor == valor){
					tabla[i][j] = new Pienso(vector[i] + tabla[i+1][j-1].valor,
							tabla[i+1][j-1].cubos, vector[i]);
				}
				else if(vector[j-1] > vector[i] 
						&& vector[j] + tabla[i][j-2].valor == valor){
					tabla[i][j] = new Pienso(vector[j] + tabla[i][j-2].valor,
							tabla[i][j-2].cubos, vector[j]);
				}
				else{
					tabla[i][j] = new Pienso(vector[j] + tabla[i+1][j-1].valor,
							tabla[i+1][j-1].cubos, vector[j]);
				}
				
				if (debug){ 
					System.out.println("Despues: ");
					imprimirTabla(); 
				}
			}
		}
		
		System.out.print("Listilla debe comer los cubos: ");
		for(int i = 0; i < tabla[0][n-1].cubos.size(); i++){
			System.out.print("["+tabla[0][n-1].cubos.get(i)+"]");
		}
		System.out.println();
		return tabla[0][n-1].valor;
	}
}
