package otros;

public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T> {
	
	protected T[] elementos;		//Lista de elementos del monticulo
	private int numElementos;		//Numero de elementos insertados hasta el momento
	
	@SuppressWarnings("unchecked")
	public EDBinaryHeap(int n){
		elementos = (T[]) new Comparable[n];
		numElementos = 0;
	}
	/**
	 * Añade un elemento a la cola de prioridad 
	 * @param elemento Elemento que se quiere insertar en la cola
	 * @return true si consigue insertarlo, false en caso contrario
	 */
	@Override
	public boolean add(T elemento) {
		if(isEmpty()){
			elementos[0] = elemento;
			numElementos++;
			return true;
		}
		else if(elementos.length > numElementos && search(elemento)){
			elementos[numElementos] = elemento;
			int pos = numElementos;
			boolean aux = true;
			while(aux){
				if(elementos[(pos-1)/2] != null && elementos[(pos-1)/2].compareTo(elemento) > 0){
					T vAux = elementos[(pos-1)/2];
					elementos[(pos-1)/2] = elementos[pos];
					elementos[pos] = vAux;
				}
				pos = (pos-1)/2;
				if(pos == 0){
					aux = false;
				}
			}
			numElementos++;
			return true;
		}
		return false;
	}
	
	private boolean search(T info){
		for(int i = 0; i < elementos.length; i++){
			if(elementos[i] != null && elementos[i].compareTo(info) == 0){
				return false;
			}
		}
		return true;
	}

	@Override
	public T poll() {
		if(numElementos > 0){
			T aux = elementos[0];
			System.out.println(aux);
			remove(aux);
			return aux;
		}
		return null;
	}

	@Override
	public boolean remove(T elemento) {
		for(int i = 0; i < elementos.length; i++){
			if(elementos[i] != null && elementos[i].compareTo(elemento) == 0){
				elementos[i] = null;
				boolean control = true;
				while(control){
					T hijoI = null;
					T hijoD = null;
					T last = elementos[numElementos - 1];
					if((i*2)+1 < numElementos - 1){
						hijoI = elementos[(i*2)+1];
					}
					if((i*2)+2 < numElementos - 1){
						hijoD = elementos[(i*2)+2];
					}
					if(hijoI != null && hijoD != null){
						if(hijoD.compareTo(hijoI) < 0){
							if(hijoD.compareTo(last) < 0){
								elementos[i] = hijoD;
								elementos[(i*2)+2] = null;
								i = (i*2)+2;
							}
							else{
								elementos[i] = last;
								elementos[numElementos-1] = null;
								control = false;
							}
						}
						else{
							if(hijoI.compareTo(last) < 0){
								elementos[i] = hijoI;
								elementos[(i*2)+1] = null;
								i = (i*2)+1;
							}
							else{
								elementos[i] = last;
								elementos[numElementos-1] = null;
								control = false;
							}
						}
					}
					else if(hijoI == null && hijoD != null){
						if(hijoD.compareTo(last) < 0){
							elementos[i] = hijoD;
							elementos[(i*2)+2] = null;
							i = (i*2)+2;
						}
						else{
							elementos[i] = last;
							elementos[numElementos-1] = null;
							control = false;
						}
					}
					else if(hijoD == null && hijoI != null){
						if(hijoI.compareTo(last) < 0){
							elementos[i] = hijoI;
							elementos[(i*2)+1] = null;
							i = (i*2)+1;
						}
						else{
							elementos[i] = last;
							elementos[numElementos-1] = null;
							control = false;
						}
					}
					else{
						elementos[i] = last;
						elementos[numElementos-1] = null;
						control = false;
					}
				}
				numElementos--;
				return true;
			}
		}
		return false;
	}

	
	@Override
	public boolean isEmpty() {
		return numElementos == 0;
	}

	@Override
	public void clear() {
		for(int i = 0; i < numElementos; i++){
			elementos[i] = null;
		}
		numElementos = 0;
	}
	
	@Override
	public String toString() {
		String string = "";
		for(int i = 0; i < numElementos; i++){
			string += elementos[i].toString() +"\t";
		}
		return string;
	}
	
	/**
	 * 
	 */
	public boolean esAscendente(T a, T b){
		if(!search(b) && !search(a)){
			for(int i = 0; i < elementos.length; i++){
				if(elementos[i].compareTo(a) == 0){
					T hijoI = null, hijoD = null;
					if((i*2)+1 < numElementos - 1){
						hijoI = elementos[(i*2)+1];
					}
					if((i*2)+2 < numElementos - 1){
						hijoD = elementos[(i*2)+2];
					}
					if(hijoD != null && hijoD.compareTo(b) == 0){
						return true;
					}
					else if(hijoI != null && hijoI.compareTo(b) == 0){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	public boolean esDescendente(T a, T b){
		if(!search(b) && !search(a)){
			for(int i = 0; i < elementos.length; i++){
				if(elementos[i].compareTo(b) == 0){
					T hijoI = null, hijoD = null;
					if((i*2)+1 < numElementos - 1){
						hijoI = elementos[(i*2)+1];
					}
					if((i*2)+2 < numElementos - 1){
						hijoD = elementos[(i*2)+2];
					}
					if(hijoD != null && hijoD.compareTo(a) == 0){
						return true;
					}
					else if(hijoI != null && hijoI.compareTo(a) == 0){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int Tamaño(){
		return numElementos;
	}
	
	public boolean estaLleno(){
		return numElementos == elementos.length;
	}
	
	public int numAristas(T a, T b){
		int numAristas = 0;
		if(!search(b) && !search(a)){
			for(int i = 0; i < elementos.length; i++){
				if(elementos[i].compareTo(a) == 0){
					if(elementos[i].compareTo(b) != 0){
						for(int j = 0; (j+i) < elementos.length; j++){
							if(elementos[j+i].compareTo(b) == 0){
								return numAristas;
							}
							else if(j % 2 == 0){
								numAristas ++;
							}
						}
					}
				}
			}
		}
		return numAristas;
	}
	
	public T nodoMaximo(){
		T maximo = elementos[0];
		for(int i = 1; i < elementos.length; i++){
			if(elementos[i] != null && maximo.compareTo(elementos[i]) < 0){
				maximo = elementos[i];
			}
		}
		return maximo;
	}
	
	public void mostrarPorAltura(int altura){
		String string = "";
		int alturaActual = 0;
		int control = 2;
		for(int i = 0; i < elementos.length; i++){
			if(altura == alturaActual && elementos[i] != null){
				string += elementos[i];
			}
			if(i % control == 0){
				alturaActual++;
				control += (int)Math.pow(2, 2);
			}
		}
		System.out.println(string);
	}
	
	public int nodoNivel(int altura){
		int nodoNivel = 0;
		int alturaActual = 0;
		int control = 1;
		for(int i = 0; i < elementos.length; i++){
			if(altura == alturaActual && elementos[i] != null){
				nodoNivel++;
			}
			if(i % control == 0){
				alturaActual++;
				if(control == 1)
					control++;
			    else
			    	control += (int)Math.pow(2, 2);
			}
		}
		return nodoNivel;
	}
	
	public int numHojas(){
		int altura = 0;
		int numHojas = 0;
		int alturaActual = 0;
		int control = 1;
		for(int i = 0; i < elementos.length; i++){
			if(altura == alturaActual && elementos[i] != null){
				numHojas++;
			}
			if(elementos[i] == null){
				return numHojas;
			}
			if(i % control == 0){
				alturaActual++;
				altura++;
				if(control == 1)
					control++;
			    else
			    	control += (int)Math.pow(2, 2);
				numHojas = 0;
			}
		}
		return numHojas;
	}
	
	@SuppressWarnings("unchecked")
	public T[] camino(T a, T b){
		T[] camino = (T[]) new Comparable[elementos.length];
		T padre;
		int elemento = 0;
		if(!search(b) && !search(a)){
			for(int i = 0; i < elementos.length; i++){
				if(elementos[i].compareTo(b) == 0){
					i = (i-1)/2;
					do{
						padre = elementos[i];
						i = (i-1)/2;
						System.out.println("[" + padre + "]");
						if(padre.compareTo(a) != 0){
							camino[elemento] = padre;
							elemento++;
						}
					} while(elementos[i].compareTo(a) != 0 && elementos[i].compareTo(padre) != 0);
					return camino;
				}
			}	
		}
		return null;
	}
	
	public String stringCamino(T[] camino){
		String string = "";
		for(int i = 0; i < camino.length; i++){
			string  += camino[i];
		}
		return string;
	}
	
	public boolean esCompleto(){
		int altura = 0;
		int alturaActual = 0;
		int control = 1;
		for(int i = 0; i < elementos.length; i++){
			if(altura == alturaActual && elementos[i] != null){
			}
			else{
				return false;
			}
			if(i % control == 0){
				alturaActual++;
				altura++;
				if(control == 1)
					control++;
			    else
			    	control += (int)Math.pow(2, 2);
			}
		}
		return true;
	}
	
	public boolean identicoA(T [] monticulo){
		if(elementos.length == monticulo.length){
			for(int i = 0; i < elementos.length; i++){
				if(elementos[i].compareTo(monticulo[i]) != 0){
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
