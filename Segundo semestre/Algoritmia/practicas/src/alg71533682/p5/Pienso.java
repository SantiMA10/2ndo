package alg71533682.p5;

import java.util.ArrayList;

public class Pienso {
	public int valor;
	public ArrayList<Integer> cubos;
	
	@SuppressWarnings("unchecked")
	public Pienso(int valor, ArrayList<Integer> cubos, int cubo){
		this.valor = valor;
		this.cubos = ((ArrayList<Integer>) cubos.clone());
		this.cubos.add(cubo);
	}

	public Pienso(int valor) {
		cubos = new ArrayList<Integer>();
		cubos.add(valor);
		this.valor = valor;
	}
	
	public String toString(){
		return valor + "";
	}
}
