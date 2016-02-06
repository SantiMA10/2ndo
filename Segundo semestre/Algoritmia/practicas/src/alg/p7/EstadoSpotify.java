package alg.p7;

import java.util.ArrayList;
import java.util.Arrays;

import alg.util.Cancion;
import alg.util.Nodo;

public class EstadoSpotify extends Nodo{
	
	public static int mejorPuntuacion;
	public static Cancion[] canciones;
	public static int tiempoMaximo;
	public static int nodosGenerados;
	
	public int[] resultado;
	public int[] bloques = new int[2];
	public int puntuacion = 0;
	
	public EstadoSpotify(EstadoSpotify padre, int i){
		super();
		
		bloques = Arrays.copyOf(padre.bloques, bloques.length);
		resultado = Arrays.copyOf(padre.resultado, canciones.length);
		puntuacion = padre.puntuacion;
		profundidad = padre.profundidad;
		idPadre= padre.hashCode();

				
		if(i < bloques.length) { //&& profundidad < canciones.length){
//			if(bloques[i] + canciones[profundidad].duracion <= max){
				
				bloques[i] += canciones[profundidad].duracion;
				resultado[profundidad] = i+1;
				puntuacion += canciones[profundidad].puntuacion;
				
//			}
		}
		
		profundidad++;

		if(puntuacion > mejorPuntuacion){
			mejorPuntuacion = puntuacion;
		}
		
		calcularValorHeuristico();
//		System.out.println(this);
	}
	
	@SuppressWarnings("static-access")
	public EstadoSpotify(int max, Cancion[] canciones){
		super();
		nodosGenerados = 0;
		this.tiempoMaximo = (max*60);
		this.canciones = canciones;
		this.resultado = new int[canciones.length];
		Arrays.sort(this.canciones);
		
//		Imprime la lista de canciones
		for(int i = 0; i < canciones.length; i++){
			System.out.println(this.canciones[i]);
		}
		
		System.out.println("\nCalculando bloques de " + max + " minutos:\n");
		
	}

	@Override
	public void calcularValorHeuristico() {
		
// Heuristico devorador
//		int puntuacion = this.puntuacion;
//		int[] bloques = this.bloques.clone();
//		for(int i = profundidad; i < canciones.length; i++){
//			if(bloques[0] + canciones[i].duracion <= max){
//				bloques[0] += canciones[i].duracion;
//				puntuacion += canciones[i].puntuacion;
//			}
//			else if(bloques[1]+ canciones[i].duracion <= max){
//				bloques[1] += canciones[i].duracion;
//				puntuacion += canciones[i].puntuacion;
//			}
//		}
//		valorHeuristico = Spotify.totalPuntuacion - puntuacion;
		
		
		if(profundidad != canciones.length){
			valorHeuristico = - (puntuacion + ((canciones[profundidad].puntuacion/canciones[profundidad].duracion)*((tiempoMaximo-bloques[0])+(tiempoMaximo-bloques[1]))));
		}
		else{
			valorHeuristico = - puntuacion;
		}
		
	}

	@Override
	public ArrayList<Nodo> expandir() {
		ArrayList<Nodo> hijos = new ArrayList<Nodo>();
		for(int i = 0; i <= bloques.length; i++){
//			System.out.println("----Nivel: " + profundidad + " - Hijo: "+ i);
			if(i == bloques.length || bloques[i] + canciones[profundidad].duracion <= tiempoMaximo){
				nodosGenerados++;
				Nodo estadoHijo= new EstadoSpotify(this,i); 
				hijos.add(estadoHijo);
			}
		}
		return hijos;
	}
	
	public String toString(){
		String toString = "";
		
		for(int i = 0; i < bloques.length; i++){
			if(i!=0){
				toString += "\n";
			}
			toString += "Bloque "+(i+1)+": " + bloques[i] + "s\n";
			int puntuacion = 0;
			for(int j = 0; j < resultado.length; j++){
				if(resultado[j] == 1){
					toString += "\t"+canciones[j]+"\n";
					puntuacion += canciones[j].puntuacion;
				}
			}
			toString += "\tPuntuacion: "+ puntuacion;
		}
		
		toString += "\nPuntuacion total: " + this.puntuacion + " Profundidad: " + profundidad;
		
//		Muestra las canciones que quedan por probar
//		if(profundidad < canciones.length){
//			toString += "\nCanciones sin probar:\n";
//			for(int i = profundidad; i < resultado.length; i++){
//				if(resultado[i] == 0){
//					toString += "\t"+canciones[i]+"\n";
//				}
//			}
//		}
//		else{
//			toString += "\n";
//		}
		
		return toString;
	}

	@Override
	public boolean solucion() {
		boolean resultado = false;
		if(profundidad == canciones.length){
			resultado = true;
		}
		return resultado;
	}

}
