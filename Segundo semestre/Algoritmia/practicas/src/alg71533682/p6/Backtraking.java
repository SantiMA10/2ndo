package alg71533682.p6;

import java.io.BufferedReader;
import java.io.FileReader;

public class Backtraking {
	
	private int n;
	private int max;
	private Cancion[] canciones;
	
	private int[] resultadoMejor;
	private int[] bloquesMejor = new int[2];
	private int valMejor = 0;	
	
	private int[] resultado;
	private int[] bloques = new int[2];
	private int val = 0;
	private int nodos = 0;
	
	public void leerFichero(String ruta){
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(ruta));
			int i = -1;
			while(bf.ready()){
				if(i == -1){
					n = Integer.parseInt(bf.readLine());
					canciones = new Cancion[n];
					resultado = new int[n];
					resultadoMejor = new int[n];
					i++;
				}
				else{
					String[] linea = bf.readLine().split("\t");
					canciones[i] = new Cancion(linea[0], linea[1], Integer.parseInt(linea[2]));
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void calcularBloques(String fichero, int maximo){
		this.max = maximo*60;
		this.val = 0;
		this.nodos = 0;
		leerFichero(fichero);
		
		imprimirListaCanciones();

		
		backtracking(0);
		
		System.out.println();
		imprimirMejorResultado();
	}
	
	private void imprimirListaCanciones(){
		int duracion = 0, puntuacion = 0;
		for(int i = 0; i < n; i++){
			duracion += canciones[i].duracion;
			puntuacion += canciones[i].puntuacion;
		}
		System.out.println("El fichero tiene:");
		System.out.println("\t*" + n + " canciones.");
		System.out.println("\t*duracion de " + duracion + " segundos.");
		System.out.println("\t*puntuacion de " + puntuacion + ".");
	}
	
	private void imprimirMejorResultado(){
		System.out.println("La mejor solucion para bloques de " + max/60 + ":" + max%60 + " minutos("+max+" segundos):");
		
		System.out.println("\t-Bloque1 " + bloquesMejor[0]/60 + ":" + bloquesMejor[0]%60 
				+ " minutos ("+bloquesMejor[0]+"/"+max+" segundos)");
		System.out.println("\t-Canciones:");
		for(int i = 0; i < n; i++){
			
			if(resultadoMejor[i] == 1){
				
				System.out.println("\t\t"+canciones[i]);
				
			}
			
		}
		
		System.out.println("\n\t-Bloque2 " + bloquesMejor[1]/60 + ":" + bloquesMejor[1]%60 
				+ " minutos ("+bloquesMejor[1]+"/"+max+" segundos)");
		System.out.println("\t-Canciones:");
		for(int i = 0; i < n; i++){
			
			if(resultadoMejor[i] == 2){
				
				System.out.println("\t\t"+canciones[i]);
				
			}
			
		}
		
		System.out.println("\nValor de las canciones de ambos bloques: " + valMejor);
		System.out.println("Nodos totales: " + nodos);
	}
	
	public void backtracking (int actual)
	{
		if (actual == n) {
			
			if(val > valMejor){
				
				valMejor = val;

				resultadoMejor = resultado.clone();
				bloquesMejor[0] = bloques[0];
				bloquesMejor[1] = bloques[1];
				
			}
			
		}
		else{

			for(int i = 0; i <= bloques.length; i++){
				
				if(i < bloques.length){
					if(bloques[i] + canciones[actual].duracion < max){
						
						resultado[actual] = i+1;
						bloques[i] += canciones[actual].duracion;
						val += canciones[actual].puntuacion;

						nodos++;
						backtracking (actual + 1);

						val -= canciones[actual].puntuacion;
						bloques[i] -= canciones[actual].duracion;
						resultado[actual] = 0;
					}
				}
				else{
					nodos++;
					backtracking (actual + 1);
				}
				
			}
		}
	}

}
