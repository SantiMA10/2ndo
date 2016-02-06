package BS;
/**
 * 
 * @author UO237040
 *
 * @param <T>
 */
public class BSTNode<T extends Comparable<T>> {
	
	private T info; //El contenido de un nodo de tipo gen�rico:
	private BSTNode<T> left; //Nodo hijo izquierdo.
	private BSTNode<T> right; //Nodo hijo derecho.
	
	/**
	 * Constructor de la clase BSTNode, se le pasa como parametro info.
	 * @param info un objeto comparable
	 */
	public BSTNode(T x){
		this.info = x;
		setLeft(null);
		setRight(null);
	}
	
	/**
	 * Metodo que asigna a info el valor que se le pasa
	 * @param info
	 */
	protected void setInfo(T info){
		this.info = info;
	}
	
	/**
	 * Metodo que devuelve el valor de info
	 * @return info
	 */
	public T getInfo(){
		return info;
	}
	
	/**
	 * Metodo que asigna al hijo izquierdo el valor de x
	 * @param x nodo que sera el hijo izquierdo
	 */
	public void setLeft(BSTNode<T> x){
		left = x;
	}
	
	/**
	 * Metodo que asigna al hijo derecho el valor de x
	 * @param x nodo que sera el hijo derecho
	 */
	public void setRight(BSTNode<T> x){
		right = x;
	}
	
	/**
	 * Metodo que devuelve el valor de hijo izquierdo
	 * @return  el valor del hijo iquierdo
	 */
	public BSTNode<T> getLeft(){
		return left;
	}
	
	/**
	 * Metodo que devuelve el valor del hijo derecho
	 * @return el valor del hijo derecho
	 */
	public BSTNode<T> getRight(){
		return right;
	}
	
	/**
	 * Metodo toString
	 */
	public String toString(){
		return info.toString();
	}
	
	/**
	 * Metodo compareTo
	 */
	public int compareTo(T x){
		return info.compareTo(x);
	}
}
