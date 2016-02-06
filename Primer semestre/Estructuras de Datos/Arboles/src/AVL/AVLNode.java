package AVL;

public class AVLNode<T extends Comparable<T>> {
	
	private T info; 				//El contenido de un nodo de tipo generico
	private AVLNode<T> left;		//Nodo hijo izquierdo
	private AVLNode<T> right;		//Nodo hijo derecho
	private byte factorBalance;		//Factor de balance del arbol
	private int altura;				//Altura del arbol

	public AVLNode(T x){
		this.setInfo(x);
		setLeft(null);
		setRight(null);
		factorBalance = 0;
		altura = 0;
	}

	/**
	 * Metodo que devuelve el valor del nodo
	 * @return T info
	 */
	public T getInfo() {
		return info;
	}

	/**
	 * Metodo que asigna el valor al nodo
	 * @param info T 
	 */
	public void setInfo(T info) {
		this.info = info;
	}

	/**
	 * Metodo que devuelve el nodo izquierdo del arbol
	 * @return AVLNode el nodo izquierdo del arbol
	 */
	public AVLNode<T> getLeft() {
		return left;
	}

	/**
	 * Metodo que asigna el nodo izquierdo del arbol
	 * @param right AVLNode nodo izquierdo del arbol
	 */
	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	/**
	 * Metodo que devuelve el nodo derecho del arbol
	 * @return AVLNode el nodo derecho del arbol
	 */
	public AVLNode<T> getRight() {
		return right;
	}

	/**
	 * Metodo que asigna el nodo derecho del arbol
	 * @param right AVLNode nodo derecho del arbol
	 */
	public void setRight(AVLNode<T> right) {
		this.right = right;
	}

	/**
	 * Metodo que devuelve el factor de balance del arbol
	 * @return byte factor de balance del arbol
	 */
	public byte getBF() {
		return factorBalance;
	}

	/**
	 * Metodo que asigana el factor de balance del arbol
	 * @param factorBalance byte
	 */
	protected void setFactorBalance(byte factorBalance) {
		this.factorBalance = factorBalance;
	}

	/**
	 * Metodo que devuelve el valor de la altura del arbol
	 * @return la altura del arbol
	 */
	public int getAltura() {
		return altura;
	}
	
	/**
	 * Metodo que establece el factor de balance y recalcula la altura del
	 *  nodo utilizando la altura de los hijos
	 */
	public void setFactorBalanceAltura() {
		int alturaR, alturaL;
		if(this.getLeft() != null) {
			alturaL = getLeft().getAltura();
		}
		else{
			alturaL = -1;
		}
		if(this.getRight() != null) {
			alturaR = getRight().getAltura();
		}
		else{
			alturaR = -1;
		}
		setFactorBalance((byte) (alturaR - alturaL));
		if(alturaR > alturaL) {
			altura = alturaR + 1;
		}
		else {
			altura = alturaL + 1;
		}
	}
	
	/**
	 * Redefinicion del metodo toString
	 * @return String
	 */
	public String toString(){
		return info.toString();
	}
	
	/**
	 * Redefinicion del metodo toString que incluye el factor de balance
	 * @return String
	 */
	public String toStringFB(){
		return info.toString() + ":FB="+getBF();
	}
	
	/**
	 * Metodo compareTo
	 * @param x elemento a comparar
	 * @return 0 si es igual, -1 si es menor y 1 si es mayor
	 */
	public int compareTo(T x){
		return info.compareTo(x);
	}
}
