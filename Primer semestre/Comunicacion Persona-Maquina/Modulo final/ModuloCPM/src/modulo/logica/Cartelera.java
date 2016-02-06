package modulo.logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Cartelera {
	private ArrayList<Pelicula> peliculas;
	private Pelicula[] recomendadas;
	
	public Cartelera(){
		peliculas = new ArrayList<Pelicula>();
		recomendadas = new Pelicula[2];
		cargarCartelera();
		generarRecomendadas();
		cargarTrailers();
	}
	protected Pelicula[] getRecomendadas(){
		return recomendadas;
	}
	private void generarRecomendadas(){
		recomendadas[0] = peliculas.get((int) (Math.random()*peliculas.size()));
		do{
			recomendadas[1] = peliculas.get((int) (Math.random()*peliculas.size()));
		}while(recomendadas[1].getCodigo().equals(recomendadas[0].getCodigo()));
	}
	
	public ArrayList<Pelicula> getCartelera(){
		return peliculas;
	}
	public void cargarCartelera(){
		String linea = "";
	    try {
	    	BufferedReader fichero = new BufferedReader(new FileReader("data/cartelera.dat"));
	    	while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		if(!linea.isEmpty()){
		    		String[] pelicula = linea.split(";");
		    		peliculas.add(new Pelicula(pelicula[0].trim(), pelicula[1].trim(), pelicula[2].trim(), pelicula[3].trim(), Integer.parseInt(pelicula[4]), 
		    				pelicula[5].trim(), pelicula[6].trim(), pelicula[7].trim(), pelicula[8].trim(), pelicula[9].trim()));
	    		}
	    	}
	    	fichero.close();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	public void cargarTrailers(){
		String linea = "";
	    try {
	    	BufferedReader fichero = new BufferedReader(new FileReader("data/trailers.dat"));
	    	while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		if(!linea.isEmpty()){
		    		String[] trailers = linea.split(";");
		    		for(int i = 0; i < peliculas.size(); i++){
		    			if(peliculas.get(i).getCodigo().equals(trailers[0])){
		    				peliculas.get(i).setTrailerID(trailers[1]);
		    			}
		    		}
	    		}
	    	}
	    	fichero.close();
	    	}catch (Exception e) {
	    		System.err.println("Archivo de trailers no encontrado");
	    	}
	}
	public Pelicula hayEn3D(Pelicula pelicula){
		for(int i = 0; i < peliculas.size(); i++){
			if(peliculas.get(i).getTitulo().equals(pelicula.getTitulo()) && peliculas.get(i).isIs3D()){
				return peliculas.get(i);
			}
		}
		return null;
	}
	
	public Pelicula hayEn2D(Pelicula pelicula){
		for(int i = 0; i < peliculas.size(); i++){
			if(peliculas.get(i).getTitulo().equals(pelicula.getTitulo()) && !peliculas.get(i).isIs3D()){
				return peliculas.get(i);
			}
		}
		return null;
	}
}
