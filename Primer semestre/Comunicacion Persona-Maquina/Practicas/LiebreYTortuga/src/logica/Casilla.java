package logica;

public class Casilla {
	
	private int valor = 0;
	private boolean arbol = false;
	private boolean agujero = false;
	private boolean bonus = false;
	
	public Casilla(int valor) {
		this.valor = valor;
	}
	public boolean tieneArbol() {
		return arbol;
	}
	public boolean tieneBonus() {
		return bonus;
	}
	public void ponerBonus(){
		valor *= 2;
		bonus = true;
	}
	public void plantarArbol() {
		arbol = true;
	}
	public void hacerAgujero(){
		agujero = true;
	}
	public boolean tieneAgujero(){
		return agujero;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

}
