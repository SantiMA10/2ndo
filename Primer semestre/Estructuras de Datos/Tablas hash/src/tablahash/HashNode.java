package tablahash;


public class HashNode<T extends Comparable<T>> {

	private T info;		//El contenido de un elementos de una tabla hash de tipo generico
	private byte estado; //Estado del elemento: borrado(-1), lleno(1) o vacio(0)
	
	private static final byte BORRADO = -1;
	private static final byte VACIO = 0;
	private static final byte LLENO = 1;
	
	/**
	 * Constructor de HashNode que genera un nodo vacio
	 */
	public HashNode(){
		setInfo(null);
		estado = VACIO;
	}
	
	/**
	 * Contructor de HashNode que genera un nodo con el dato pasado como parametro
	 * @param elemento
	 */
	public HashNode(T elemento){
		setInfo(elemento);
		estado = LLENO;
	}
	
	/**
	 * Metodo que devuelve el valor de la propiedad info del nodo
	 * @return T valor de la propiedad info
	 */
	public T getInfo(){
		return info;
	}
	
	/**
	 * Metodo que asigna un valor a la propiedad info del HashNode
	 * @param elemento
	 */
	public void setInfo(T elemento){
		this.info = elemento;
	}
	
	/**
	 * Metodo que hace que el estado del nodo sea borrado
	 */
	public void borrar(){
		estado = BORRADO;
	}
	
	/**
	 * Metodo que devuelve el estado del nodo
	 * @return byte estado borrado(-1), lleno(1) o vacio(0)
	 */
	public byte getEstado(){
		return estado;
	}
	
	/**
	 * Metodo toString de la clase HashNode
	 */
	public String toString(){
		return "" + info;
	}
}
