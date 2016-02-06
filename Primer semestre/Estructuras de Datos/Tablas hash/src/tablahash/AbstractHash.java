package tablahash;



public abstract class AbstractHash<T extends Comparable<T>> {

	abstract protected int fHash(T info, int intento);
	abstract public boolean add(T elemento);
	abstract public T find(T elemento);
	abstract public boolean remove(T elemento);
	
	/**
	 * Metodo que comprueba que un numero sea primo
	 * @param numero que quieres comprobar si es primo
	 * @return true si es primo, false en caso de no serlo
	 */
	protected boolean esPrimo(int numero){
		return (numero % 2 != 0);
	}
	
	/**
	 * Metodo que devuelve el siguiente numero primo
	 * @param numero el numero a partir del cual quieres calcular el numero primo
	 * @return int un numero primo
	 */
	protected int numPrimoMayor(int numero){
		do{
			numero++;
		}
		while(!esPrimo(numero));
		return numero;
	}
	
	/**
	 * Metodo que devuelve el siguiente numero primo
	 * @param numero el numero a partir del cual quieres calcular el numero primo
	 * @return int un numero primo
	 */
	protected int numPrimoMenor(int numero){
		do{
			numero++;
		}
		while(!esPrimo(numero));
		return numero;
	}
	
	/**
	 * Metodo que devuelve la clave donde insertar el elemento que se pasa como parametro
	 * @param info el valor del que se desea obtener la clave
	 * @return int clave donde insertar el elemento
	 */
	protected int obtenerClave(T info){
		if(info instanceof Integer){
			return ((Integer)info).intValue();
		}
		else if(info instanceof String){
			int result = 0;
			String infoString = (String) info;
			for (int i=0; i< infoString.length(); i++){
				result += (int) infoString.charAt(i);
			}	
			return result;
		}
		else{
			return info.hashCode();
		}
	}
	
}
