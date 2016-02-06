package modulo.logica;

import java.io.BufferedReader;
import java.io.FileReader;

public class Cine {
	private Cartelera cartelera;
	private String nombre;
	private double precioEntrada;
	private double descuentoJubilado;
	private double incrementoCumple;
	private double incremento3D;
	
	public Cine(){
		cargarCine();
		setCartelera(new Cartelera());
	}
	public void cargarCine(){
		String linea = "";
	    try {
	    	BufferedReader fichero = new BufferedReader(new FileReader("data/precios.dat"));
	    	while (fichero.ready()) {
	    			linea = fichero.readLine();
		    	if(!linea.isEmpty()){
		    		String[] datos = linea.split(";");
		    		setNombre(datos[0]);
		    		setPrecioEntrada(Double.parseDouble(datos[1]));
		    		setIncremento3D(Double.parseDouble(datos[2]));
		    		setIncrementoCumple(Double.parseDouble(datos[3]));
		    		setDescuentoJubilado(Double.parseDouble(datos[4]));
	    		}
	    	}
	    	fichero.close();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    }
	}
	public Cartelera getCartelera() {
		return cartelera;
	}
	private void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}
	public String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioEntrada() {
		return precioEntrada;
	}
	private void setPrecioEntrada(double precioEntrada) {
		this.precioEntrada = precioEntrada;
	}
	public double getDescuentoJubilado() {
		return descuentoJubilado;
	}
	private void setDescuentoJubilado(double descuentoJubilado) {
		this.descuentoJubilado = descuentoJubilado;
	}
	public double getIncrementoCumple() {
		return incrementoCumple;
	}
	private void setIncrementoCumple(double incrementoCumple) {
		this.incrementoCumple = incrementoCumple;
	}
	public double getIncremento3D() {
		return incremento3D;
	}
	private void setIncremento3D(double incremento3d) {
		incremento3D = incremento3d;
	}
	public double getPrecio(int tipo, boolean is3D){
		double precio = precioEntrada;
		if(tipo == Entrada.JUBILADO){
			precio += getDescuentoJubilado();
		}
		else if(tipo == Entrada.JUBILADO_Y_PACK_CUMPLE){
			precio += getDescuentoJubilado()+getIncrementoCumple();
		}
		else if(tipo == Entrada.PACK_CUMPLE){
			precio += getIncrementoCumple();
		}
		if(is3D){
			precio += getIncremento3D();
		}
		return precio;
		
	}
}
