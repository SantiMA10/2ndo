package modulo.logica;

import java.util.ArrayList;
import java.util.ResourceBundle;



public class Pelicula {
	private String codigo;
	private String titulo;
	private String sinopsis;
	private String genero;
	private int duracion;
	private String edadRecomendada;
	private String[] fechas;
	private String[] horas;
	private String nombreSala;
	private ArrayList<Sala> salas;
	private boolean is3D;
	private String trailerID;
	
	public Pelicula(String codigo, String titulo, String sinopsis, String genero, int duracion, 
			String edadRecomendada, String fechas, String horas, String nombreSala, String is3D){
		setCodigo(codigo);
		setTitulo(titulo);
		setSinopsis(sinopsis);
		setGenero(genero);
		setDuracion(duracion);
		setEdadRecomendada(edadRecomendada);
		procesarFechas(fechas);
		procesarHoras(horas);
		setNombreSala(nombreSala);
		procesarFormato(is3D);
		crearSalas();
	}
	
	public String getRutaImagen(){
		return "/modulo/img/"+getCodigo()+".jpg";
	}

	private void crearSalas() {
		salas = new ArrayList<Sala>();
		for(int i = 0; i < fechas.length; i++){
			for(int j = 0; j < horas.length; j++){
				salas.add(new Sala(getNombreSala(), getTitulo(),getCodigo(), fechas[i], horas[j]));
			}
		}
		
	}
	
	public void guardarSalas(){
		for(int i = 0; i < salas.size(); i++){
			if(!salas.get(i).isVacia()){
				salas.get(i).guardarSala();
			}
		}
	}

	public Sala getSala(String fecha, String hora){
		for(int i = 0; i < salas.size(); i++){
			if(salas.get(i).getFecha().equals(fecha) && salas.get(i).getHora().equals(hora)){
				return salas.get(i);
			}
		}
		return null;
	}
	
	private void procesarHoras(String horas) {
		this.horas = horas.split("%");	
	}
	
	private void procesarFechas(String fechas) {
		this.fechas = fechas.split("%");
	}

	private void procesarFormato(String is3D) {
		this.is3D = (is3D.equals("S") || is3D.equals("s"));	
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getEdadRecomendada() {
		return edadRecomendada;
	}
	public void setEdadRecomendada(String edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}
	public String[] getFechas() {
		return fechas;
	}
	public void setFechas(String[] fechas) {
		this.fechas = fechas;
	}
	public String[] getHoras() {
		return horas;
	}
	public void setHoras(String[] horas) {
		this.horas = horas;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	public ArrayList<Sala> getSalas() {
		return salas;
	}
	public void setSalas(ArrayList<Sala> salas) {
		this.salas = salas;
	}
	public boolean isIs3D() {
		return is3D;
	}
	public String getInformacion(ResourceBundle traduccion){
		String informacion = "";
		informacion += traduccion.getString("genero")+": "+getGenero()+"\n";
		informacion += traduccion.getString("duracion")+": "+getDuracion()+" min.\n";
		informacion += traduccion.getString("edadRecomendada")+": "+getEdadRecomendada();
		return informacion;
	}
	public String toString(){
		if(isIs3D()){
			return getTitulo() + " 3D";
		}
		return getTitulo();
	}

	public String getTrailerID() {
		return trailerID;
	}

	public void setTrailerID(String trailerID) {
		this.trailerID = trailerID;
	}
	
}
