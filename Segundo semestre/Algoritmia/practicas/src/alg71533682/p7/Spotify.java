package alg71533682.p7;

import java.io.BufferedReader;
import java.io.FileReader;

import alg71533682.util.Cancion;
import alg71533682.util.RamificaYPoda;

public class Spotify extends RamificaYPoda{
	
	public static long t1;
	public static int totalPuntuacion;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		Spotify problemaSpotify = new Spotify(); 
		
		t1 = System.currentTimeMillis();

		problemaSpotify.ramificaYPoda(problemaSpotify.getNodoRaiz()); 
		
		long t2 = System.currentTimeMillis();

		problemaSpotify.mostrarTrazaSolucion(); 
		
		System.out.println("Datos del arbol generado:");
		System.out.println("\t- " + (t2-t1) + " milisegundos");
		System.out.println("\t- " + ((EstadoSpotify)problemaSpotify.getNodoRaiz()).nodosGenerados + " nodos generados");
		System.out.println("\t- " + problemaSpotify.nodosVisitados + " nodos visitados");
		System.out.println("\t- " + (((EstadoSpotify)problemaSpotify.getNodoRaiz()).nodosGenerados - problemaSpotify.nodosVisitados) + " nodos podados");

	}


	public Spotify() 
	{
		Cancion[] canciones = leerFichero("datos/Lista01.txt");
		System.out.println("Datos fichero:");
		System.out.println("\t->Numero canciones: " + canciones.length);
		int duracion = 0, puntuacion = 0;
		for(int i = 0; i < canciones.length; i++){
			duracion += canciones[i].duracion;
			puntuacion += canciones[i].puntuacion;
		}
		System.out.println("\t->Duracion total: " + duracion + " segundos");
		System.out.println("\t->Puntuacion total: " + puntuacion);
		
		totalPuntuacion = puntuacion;
		
		nodoRaiz = new EstadoSpotify(5, canciones); //costes iniciales
	}
	
	@SuppressWarnings("resource")
	public Cancion[] leerFichero(String ruta){
		BufferedReader bf;
		Cancion[] canciones = null;
		try {
			bf = new BufferedReader(new FileReader(ruta));
			int i = -1;
			while(bf.ready()){
				if(i == -1){
					int n = Integer.parseInt(bf.readLine());
					canciones = new Cancion[n];
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
		return canciones;
	}

}
