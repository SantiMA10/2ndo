package tablahash;

import java.lang.reflect.Array;

public class HashTable<T extends Comparable<T>> extends AbstractHash<T>{
	private int numElementos;		//Numero de elementos de la tabla hash hasta el momento
	private HashNode<T> tabla[];	//Tabla Hash
	
	//Tamañado por defecto de la tabla
	public static final int DEFAULT_TABLE_SIZE	=	1;
	
	//Limites de factor de carga
	public static final double MAXIMUN_LF		=	0.5;
	public static final double MINIMUN_LF		=	0.16;
	
	//Tipo de dispersion que lleva acabo la tabla
	 //1 lineal, 2 cuadratica, 3 dispersion doble
	public static short TIPO_EXPLORACION = 1;
	
	/**
	 * Constructor que crea una Tabla Hash con el tama�o por defecto
	 */
	@SuppressWarnings("unchecked")
	public HashTable(){
		tabla = (HashNode<T>[]) Array.newInstance(HashNode.class,DEFAULT_TABLE_SIZE);
		this.numElementos = 0;
	}
	
	/**
	 * Contructor que crea una Tabla Hash con el tama�o que se pasa como parametro
	 * @param tam int tamaño de la tabla, debe de ser primo
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int tam){
		if(esPrimo(tam)){
			tabla = (HashNode<T>[]) Array.newInstance(HashNode.class,tam);
			this.numElementos = 0;
		}
		else{
			tabla = (HashNode<T>[]) Array.newInstance(HashNode.class,numPrimoMayor(tam));
			this.numElementos = 0;
		}
	}
	
	/**
	 * Metodo que devuelve el numero de elementos insertados en la tabla
	 * @return int numero de elementos insertados
	 */
	public int getNumElementos(){
		return numElementos;
	}
	
	/**
	 * Metodo que devuelve el tama�o de la tabla
	 * @return int el tama�o de la tabla
	 */
	public int getB(){
		return tabla.length;
	}
	
	/**
	 * Metodo que devuelve la tabla hash
	 * @return HashNode<T>[] la tabla hast
	 */
	public HashNode<T>[] getTable(){
		return tabla;
	}
	
	/**
	 * Metodo que devuelve el factor de carga
	 * @return double factor de carga
	 */
	private double loadFactor(){
		return (double)numElementos/getB();
	}

	
	/**
	 * Metodo que calcula la posicion que tiene que ocupar el elemento en la tabla
	 * @param info elemento a añadir
	 * @return int posicion que ocupa el elemento en la tabla
	 */
	protected int fHash(T info, int intento){
		if(TIPO_EXPLORACION == 1)
			return Math.abs((obtenerClave(info) + intento) % getB());
		else if(TIPO_EXPLORACION == 2)
			return (int) Math.abs((obtenerClave(info) + Math.pow(intento,2)) % getB());
		else if(TIPO_EXPLORACION == 3){
			int R = numPrimoMenor(getB());
			return (int) Math.abs((obtenerClave(info) + intento*(R-obtenerClave(info)%R)) % getB());
		}
		return 0;
	}
	
	/**
	 * Metodo que a�ade un elemento a la tabla hash, devuelve true si lo logra
	 * false en caso contrario
	 * @param elemento T a a�adir a la tabla
	 * @return devuelve true si lo logra
	 * 					false en caso contrario
	 */
	public boolean add(T elemento){
		boolean control = false;
		if(find(elemento) != null || numElementos == getB()){
			return false;
		}
		int intento = 0;
		int pos = fHash(elemento, intento);
		while(!control){
			if(tabla[pos] == null || tabla[pos].getEstado() != 1 ){
				tabla[pos] = new HashNode<T>(elemento);
				numElementos++;
				redispersion();
				control = true;
			}
			intento++;
			pos = fHash(elemento, intento);
		}
		return control;
	}
	
	@SuppressWarnings("unchecked")
	public void redispersion(){
		if(loadFactor() > MAXIMUN_LF){
			 HashNode<T> aux[] = getTable();
			 int tam = getB()*2;
			 if(!esPrimo(tam)){
				 tam = numPrimoMayor(tam);
			 }
			 tabla = (HashNode<T>[]) Array.newInstance(HashNode.class,tam);
			 numElementos = 0;
			 for(int i = 0; i < aux.length; i++){
				 if(aux[i] != null && aux[i].getEstado() == 1 ){
					 add(aux[i].getInfo());
				 }
			 }
		}
	}
	
	@SuppressWarnings("unchecked")
	public void redispersionInversa(){
		if(loadFactor() < MINIMUN_LF){
			HashNode<T> aux[] = getTable();
			 int tam = getB()/2;
			 if(!esPrimo(tam)){
				 tam = numPrimoMenor(tam);
			 }
			 tabla = (HashNode<T>[]) Array.newInstance(HashNode.class,tam);
			 numElementos = 0;
			 for(int i = 0; i < aux.length; i++){
				 if(aux[i] != null && aux[i].getEstado() == 1 ){
					 add(aux[i].getInfo());
				 }
			 }
		}
	}
	
	/**
	 * Metodo que busca un elemento dentro de la tabla Hash
	 * @param elemento T a buscar dentro de la tabla
	 * @return T el valor del elemento buscado
	 */
	public T find(T elemento){
		for(int i = 0; i < getB(); i++){
			if(tabla[i] != null){
				if(tabla[i].getInfo().compareTo(elemento) == 0){
					return elemento;
				}
			}
			
		}
		return null;
	}
	
	/**
	 * Metodo que borra el elemento pasado como parametro, devuelve true si lo logra
	 * false en caso contrario
	 * @param elemento T a borrar de la tabla
	 * @return boolean true si lo logra
	 * 				   false en caso contrario
	 */
	public boolean remove(T elemento){
		for(int i = 0;i<tabla.length; i++){
			if(tabla[fHash(elemento, i)] != null && tabla[fHash(elemento, i)].getInfo().compareTo(elemento) == 0){
				tabla[fHash(elemento, i)].borrar();
				numElementos--;
				redispersionInversa();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo toString de la clase HashTable
	 */
	public String toString(){
		String toString = "";
		for(int i = 0; i < tabla.length; i++){
			if(i != 0){
				toString += "-";
			}
			if(tabla[i] == null || tabla[i].getEstado() == 0){
				toString += "[_E_]";
			}
			else if(tabla[i].getEstado() == 1){
				toString += "["+tabla[i]+"]";
			}
			else if(tabla[i] == null || tabla[i].getEstado() == -1){
				toString += "[_D_]";
			}
		}
		return toString +" [Tamaño: "+getB()+" Num.Elem.: " + numElementos + "]";
	}
}
