package modulo.logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Sala {
	private static int NUMERO_FILAS = 4;
	private static int NUMERO_BUTACAS = 10;
	
	private String nombreSala;
	private String tituloPelicula;
	private String codigo;
	private String fecha;
	private String hora;
	private int[][] sala;
	private int entradasVendidas;
	
	public Sala(String nombreSala, String titulo, String codigo, String fecha, String hora){
		setNombreSala(nombreSala);
		setTituloPelicula(titulo);
		setCodigo(codigo);
		setFecha(fecha);
		setHora(hora);
		sala = new int[NUMERO_BUTACAS][NUMERO_FILAS];
		if(!cargarSala()){
			entradasVendidas = 0;
		}
	}

	private boolean cargarSala() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
			BufferedReader fichero = new BufferedReader(new FileReader("data/salas/"+getCodigo()+getFecha().replace("/", "")+getHora().replace(":", "")+".json"));
//			System.out.println("Cargando sala: "+getCodigo()+getFecha()+getHora());
			java.lang.reflect.Type tipoPeliculas = new TypeToken<int[][]>(){}.getType();
			sala = gson.fromJson(fichero, tipoPeliculas);
	    	fichero.close();
//	    	System.out.println("Sala: "+ getTituloPelicula() + " cargada");
		}catch(FileNotFoundException fn){
			return false;
		}catch(Exception e){
			return false;
		}
		return true;
	}

	protected void guardarSala(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
//			System.out.println("Guardando salas: "+getCodigo()+getFecha()+getHora());
			BufferedWriter fichero = new BufferedWriter(new FileWriter("data/salas/"+getCodigo()+getFecha().replace("/", "")+getHora().replace(":", "")+".json"));
			gson.toJson(sala, fichero);
	    	fichero.close();
//	    	System.out.println("Sala "+getCodigo()+getFecha()+getHora()+" guardada");
		}catch(Exception e){
		}
	}
	public Entrada cambiarTipoDeEntrada(int fila, int butaca, int tipo){
		if(sala[butaca][fila] == 0 && tipo != 0){
			entradasVendidas++;
			sala[butaca][fila] = tipo;
			return new Entrada(getCodigo(), getTituloPelicula(), getNombreSala(), getFecha(), getHora(), fila, butaca, tipo);
		}
		else{
			sala[butaca][fila] = 0;
			entradasVendidas--;
			return null;
		}
	}
	
	public boolean isSalaLlena(){
		return entradasVendidas == 30;
	}
	
	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}
	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isLibre(int fila, int butaca) {
		return sala[butaca][fila] == 0;
	}

	public boolean isVacia() {
		return entradasVendidas == 0;
	}
}
