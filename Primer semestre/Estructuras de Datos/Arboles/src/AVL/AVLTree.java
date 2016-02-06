package AVL;

public class AVLTree<T extends Comparable<T>> {
	
	private AVLNode<T> raiz; //Nodo raiz del arbol
	private boolean borrado;
	
	/**
	 * Constructor de AVLTree
	 */
	public AVLTree(){
		
	}
	
	/**
	 * Metodo que añade un nodo al arbol AVL
	 * @param x el valor del nodo que queremos añadir
	 * @return El nodo raiz
	 */
	public AVLNode<T> add(T x){
		if(raiz == null){
			raiz = new AVLNode<T>(x);
		}
		else{
			if(raiz.compareTo(x) > 0){
				if(raiz.getLeft() == null){
					raiz.setLeft(new AVLNode<T>(x));
				}
				else{
					addRecursivo(raiz.getLeft(), x);
				}
			}
			else if(raiz.compareTo(x) < 0){
				if(raiz.getRight() == null){
					raiz.setRight(new AVLNode<T>(x));
				}
				else{
					addRecursivo(raiz.getRight(), x);
				}
				
			}

		}
		raiz = updateBF(raiz);
		return raiz;
	}
	
	/**
	 * Metodo que añade un nodo al arbol AVL
	 * @param x el valor del nodo que queremos añadir
	 * @return true si se ha podido añadir el nodo, false en caso contrario.
	 */
	public boolean addB(T x){
		boolean result = false;
		if(raiz == null){
			raiz = new AVLNode<T>(x);
			result = true;
		}
		else{
			if(raiz.compareTo(x) > 0){
				if(raiz.getLeft() == null){
					raiz.setLeft(new AVLNode<T>(x));
					result = true;
				}
				else{
					result = addRecursivo(raiz.getLeft(), x);
				}
			}
			else if(raiz.compareTo(x) < 0){
				if(raiz.getRight() == null){
					raiz.setRight(new AVLNode<T>(x));
					result = true;
				}
				else{
					result = addRecursivo(raiz.getRight(), x);
				}
				
			}
			else if(raiz.compareTo(x) == 0){
				result = false;
			}
		}
		raiz = updateBF(raiz);
		return result;
	}
	
	/**
	 * Metodo de ayuda para hacerlo recursivo que añade un nodo al arbol AVL
	 * @param raiz el nodo que se debe tomar como raiz
	 * @param x el valor que se quiere añadir
	 * @return true si lo ha añadido con exito, false en caso contrario
	 */
	private boolean addRecursivo(AVLNode<T> raiz, T x) {
		boolean result = false;
		if(raiz == null){
			raiz = new AVLNode<T>(x);
			updateBF(raiz);
			result = true;
		}
		else{
			if(raiz.compareTo(x) > 0){
				if(raiz.getLeft() == null){
					raiz.setLeft(new AVLNode<T>(x));
					result = true;
				}
				else{
					result = addRecursivo(raiz.getLeft(), x);
				}
			}
			else if(raiz.compareTo(x) < 0){
				if(raiz.getRight() == null){
					raiz.setRight(new AVLNode<T>(x));
					result = true;
				}
				else{
					result = addRecursivo(raiz.getRight(), x);
				}
				
			}
			else if(raiz.compareTo(x) == 0){
				result = false;
			}
		}
		raiz = updateBF(raiz);
		return result;
	}
	
	/**
	 * Metodo para borra el nodo con el valor pasado como parametro si se borra devuelve true,
	 * false en caso contrario
	 * @param x valor del nodo a borrar
	 * @return el nodo raiz
	 */
	public AVLNode<T> remove(T x){
		if(raiz != null){
			if(raiz.compareTo(x) == 0){
				if(raiz.getLeft() == null && raiz.getRight() == null){
					AVLNode<T> padre = getPadre(new AVLNode<T>(x));
					raiz = null;
					if(padre != null)
						updateBF(padre);
					borrado = true;
				}
				else if(raiz.getLeft() == null){
					raiz = raiz.getRight();
					borrado = true;
				}
				else if(raiz.getRight() == null){
					raiz = raiz.getLeft();
					borrado = true;
				}
				else{
					T aux = getMax(raiz.getLeft());
					remove(aux);
					if(raiz.getInfo() == x)
						raiz.setInfo(aux);
					else
						cambiar(x, aux);
					borrado = true;
				}
			}
			else if(raiz.compareTo(x) < 0){
				raiz.setRight(remove(raiz.getRight(), x));
			}
			else{
				raiz.setLeft(remove(raiz.getLeft(), x));		
			}
		}
		if(raiz != null){
			raiz = updateBF(raiz);
			if(raiz.getLeft() != null)
				raiz.setLeft(updateBF(raiz.getLeft()));
			if(raiz.getRight() != null)
				raiz.setRight(updateBF(raiz.getRight()));
		}
		return raiz;
	}
	
	/**
	 * Metodo que cambia el valor de un nodo por otro
	 * @param x el valor que se quiere cambiar por y
	 * @param y el valor por el que se quiere cambiar x
	 */
	private void cambiar(T x, T y){
		if(raiz != null){
			if(raiz.compareTo(x) == 0){
				raiz.setInfo(y);
			}
			else if(raiz.compareTo(x) < 0){
				cambiar(raiz.getRight(), x, y);
			}
			else{
				cambiar(raiz.getLeft(), x, y);		
			}
		}
	}
	
	/**
	 * Metodo auxiliar para cambia el valor de un nodo por otro
	 * @param x el valor que se quiere cambiar por y
	 * @param y el valor por el que se quiere cambiar x
	 */
	private void cambiar(AVLNode<T> raiz, T x, T y) {
		if(raiz != null){
			if(raiz.compareTo(x) == 0){
				raiz.setInfo(y);
			}
			else if(raiz.compareTo(x) < 0){
				cambiar(raiz.getRight(), x, y);
			}
			else{
				cambiar(raiz.getLeft(), x, y);		
			}
		}
		
	}

	/**
	 * Metodo que borra un nodo, en caso de borrarlo corectamente devuelve true, false en caso contrario
	 * @param x valor del nodo que se quiere borrar
	 * @return  devuelve true si lo ha borrado correctamente, false en caso contrario
	 */
	public boolean removeB(T x){
		borrado = false;
		remove(x);
		return borrado;
	}
	
	/**
	 * Metodo auxiliar para borra el nodo con el valor pasado como parametro si se borra devuelve true,
	 * false en caso contrario
	 * @param x valor del nodo a borrar, la raiz desde donde se empieza a buscar el nodo
	 * @return el nodo raiz
	 */
	private AVLNode<T> remove(AVLNode<T> raiz, T x){
		if(raiz != null){
			if(raiz.compareTo(x) == 0){
				if(raiz.getLeft() == null && raiz.getRight() == null){
					AVLNode<T> padre = getPadre(new AVLNode<T>(x));
					raiz = null;
					if(padre != null)
						updateBF(padre);
					borrado = true;
				}
				else if(raiz.getLeft() == null){
					raiz = raiz.getRight();
					borrado = true;
				}
				else if(raiz.getRight() == null){
					raiz = raiz.getLeft();
					borrado = true;
				}
				else{
					T aux = getMax(raiz.getLeft());
					remove(aux);
					if(raiz.getInfo() == x)
						raiz.setInfo(aux);
					else
						cambiar(x, aux);
					borrado = true;
				}
			}
			else if(raiz.compareTo(x) < 0){
				raiz.setRight(remove(raiz.getRight(), x));
			}
			else{
				raiz.setLeft(remove(raiz.getLeft(), x));
				
			}
			
		}
		if(raiz != null){
			raiz = updateBF(raiz);
			if(raiz.getLeft() != null)
				raiz.setLeft(updateBF(raiz.getLeft()));
			if(raiz.getRight() != null)
				raiz.setRight(updateBF(raiz.getRight()));
		}
		return raiz;
	}
	/**
	 * Devuelve el valor maximo del arbol pasado y lo extrae del arbol
	 * @param raiz del arbol del que se desea obtener el mayor
	 * @return el valor del nodo mayor
	 */
	private T getMax(AVLNode<T> raiz){
		while (raiz.getRight() != null){
			raiz = raiz.getRight();
		}
		return raiz.getInfo();
	}
	
	/**
	 * Metodo que obtiene el padre de un nodo
	 * @param node del que se quiere obtener el padre
	 * @return el padre del nodo que se pasa como paramentro
	 */
	private AVLNode<T> getPadre(AVLNode<T> node){
		if(raiz == null || node == null){
			return null;
		}
		else{
			if(raiz.compareTo(node.getInfo()) > 0){
				if(raiz.getLeft() == null){
					return null;
				}
				else if (raiz.getLeft().compareTo(node.getInfo()) == 0){
					return raiz;
				}
				else{
					return getPadre(raiz.getLeft(), node);
				}
			}
			else if(raiz.compareTo(node.getInfo()) < 0){
				if(raiz.getRight() == null){
					return null;
				}
				else if (raiz.getRight().compareTo(node.getInfo()) == 0){
					return raiz;
				}
				else{
					return getPadre(raiz.getRight(), node);
				}
			}
		}
		return null;
	}
	
	/**
	 * Metodo que ayuda a obtiener el padre de un nodo
	 * @param raiz el nodo desde el que hay que empezar a buscar
	 * @param node del que se quiere obtener el padre
	 * @return el padre del nodo que se pasa como paramentro
	 */
	private AVLNode<T> getPadre(AVLNode<T> raiz, AVLNode<T> node) {
		if(raiz == null || node == null){
			return null;
		}
		else{
			if(raiz.compareTo(node.getInfo()) > 0){
				if(raiz.getLeft() == null){
					return null;
				}
				else if (raiz.getLeft().compareTo(node.getInfo()) == 0){
					return raiz;
				}
				else{
					return getPadre(raiz.getLeft(), node);
				}
			}
			else if(raiz.compareTo(node.getInfo()) < 0){
				if(raiz.getRight() == null){
					return null;
				}
				else if (raiz.getRight().compareTo(node.getInfo()) == 0){
					return raiz;
				}
				else{
					return getPadre(raiz.getRight(), node);
				}
			}
		}
		return null;
	}

	/**
	 * Metodo que actualiza los factores de balance y altura para cada nodo
	 * @param nodo del que se desea actualizar el factor de balance y altura
	 * @return el nodo del que se ha actualizado el factor de balance y altura
	 */
	private AVLNode<T> updateBF(AVLNode<T> raiz){
		raiz.setFactorBalanceAltura();
		if(raiz.getBF() == -2){
			if(raiz.getLeft().getBF() == -1)
				raiz = singleLeftRotation(raiz);
			else{
				raiz = doubleLeftRotation(raiz);
			}
		}
		else if(raiz.getBF() == 2){
			if(raiz.getRight().getBF() == 1)
				raiz = singleRightRotation(raiz);
			else{
				raiz = doubleRightRotation(raiz);
			}
		}
		return raiz;
	}
	
	/**
	 * Aplica la rotacion simple por la izquierda.
	 * @param nodo desde el que se hace la rotacion
	 * @return el nodo raiz del subarbol recolocado
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> nodo){
		//System.out.println("Single Left: " + nodo);
		if(nodo != null){
			AVLNode<T> aux = nodo.getLeft();
			nodo.setLeft(aux.getRight());
			aux.setRight(nodo);
			AVLNode<T> padre = getPadre(nodo);
			if(padre != null){
				if(padre.compareTo(aux.getInfo()) > 0){
					padre.setLeft(aux);
				}
				else{
					padre.setRight(aux);
				}
				padre.setFactorBalanceAltura();
			}
			else{
				this.raiz = aux;
			}
			nodo.setFactorBalanceAltura();
			aux.setFactorBalanceAltura();
			nodo = aux;
		}
		return nodo;
	}
	
	/**
	 * Aplica la rotacion simple por la derecha.
	 * @param nodo desde el que se hace la rotacion
	 * @return el nodo raiz del subarbol recolocado
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> nodo){
		//System.out.println("Single Right: " + nodo);
		if(nodo != null){
			AVLNode<T> aux = nodo.getRight();
			nodo.setRight(aux.getLeft());
			aux.setLeft(nodo);
			AVLNode<T> padre = getPadre(nodo);
			if(padre != null){
				if(padre.compareTo(aux.getInfo()) > 0){
					padre.setLeft(aux);
				}
				else{
					padre.setRight(aux);
				}
				padre.setFactorBalanceAltura();
			}
			else{
				this.raiz = aux;
			}
			nodo.setFactorBalanceAltura();
			aux.setFactorBalanceAltura();
			nodo = aux;
		}
		return nodo;
	}
	
	/**
	 * Aplica la rotacion doble por la izquierda.
	 * @param nodo desde el que se hace la rotacion
	 * @return el nodo raiz del subarbol recolocado
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T> nodo){
		//System.out.println("Double Left: " + nodo);
		if(nodo != null){
			AVLNode<T> aux1 = nodo.getLeft();
			AVLNode<T> aux2 = aux1.getRight();
			nodo.setLeft(aux2.getRight());
			aux1.setRight(aux2.getLeft());
			aux2.setRight(nodo);
			aux2.setLeft(aux1);
			AVLNode<T> padre = getPadre(nodo);
			if(padre != null){
				if(padre.compareTo(aux2.getInfo()) > 0){
					padre.setLeft(aux2);
				}
				else{
					padre.setRight(aux2);
				}
			}
			else{
				this.raiz = aux2;
			}
			nodo.setFactorBalanceAltura();
			aux1.setFactorBalanceAltura();
			aux2.setFactorBalanceAltura();
			nodo = aux2;
		}
		return nodo;
	}
	
	/**
	 * Aplica la rotacion doble por la derecha.
	 * @param nodo desde el que se hace la rotacion
	 * @return el nodo raiz del subarbol recolocado
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> nodo) {
		//System.out.println("Double Right: " + nodo);
		if(nodo != null){
			AVLNode<T> aux1 = nodo.getRight();
			AVLNode<T> aux2 = aux1.getLeft();
			nodo.setRight(aux2.getLeft());
			aux1.setLeft(aux2.getRight());
			aux2.setLeft(nodo);
			aux2.setRight(aux1);
			AVLNode <T> padre = getPadre(nodo);
			if(padre != null){
				if(padre.compareTo(aux2.getInfo()) > 0){
					padre.setLeft(aux2);
				}
				else{
					padre.setRight(aux2);
				}
			}
			else{
				this.raiz = aux2;
			}
			nodo.setFactorBalanceAltura();
			aux1.setFactorBalanceAltura();
			aux2.setFactorBalanceAltura();
			nodo = aux2;
		}
		return nodo;
	}
	
	/**
	 * Metodo que devuelve los valores del arbol con recorrido postorden
	 * @return string con el arbol en recorrido posorden
	 */
	public String postOrder(){
		String postOrden = "";
		if(this.raiz != null){
			if(raiz.getLeft() != null){
				postOrden += postOrden(raiz.getLeft());
			}
			if(raiz.getRight() != null){
				postOrden += postOrden(raiz.getRight());
			}
			postOrden += raiz.toString()+"\t";
		}
		return postOrden;
	}
	/**
	 * Metodo auxiliar que devuelve los valores del arbol con recorrido postorden
	 * @return string con el arbol en recorrido posorden
	 */
	private String postOrden(AVLNode<T> node){
		String postOrden = "";
		if(node != null){
			if(node.getLeft() != null){
				postOrden += postOrden(node.getLeft());
			}
			if(node.getRight() != null){
				postOrden += postOrden(node.getRight());
			}
			postOrden += node.toString()+"\t";
		}
		return postOrden;
	}

	/**
	 * Metodo que devuelve los valores del arbol con recorrido postorden añadiendo el FB
	 * @return string con el arbol en recorrido posorden con el FB de cada nodo
	 */
	public String postOrderFB(){
		String postOrden = "";
		if(this.raiz != null){
			if(raiz.getLeft() != null){
				postOrden += postOrdenFB(raiz.getLeft());
			}
			if(raiz.getRight() != null){
				postOrden += postOrdenFB(raiz.getRight());
			}
			postOrden += raiz.toStringFB()+"\t";
		}
		return postOrden;
	}
	/**
	 * Metodo auxiliar que devuelve los valores del arbol con recorrido postorden añadiendo el FB
	 * @return string con el arbol en recorrido posorden con el FB de cada nodo
	 */
	private String postOrdenFB(AVLNode<T> node){
		String postOrden = "";
		if(node != null){
			if(node.getLeft() != null){
				postOrden += postOrdenFB(node.getLeft());
			}
			if(node.getRight() != null){
				postOrden += postOrdenFB(node.getRight());
			}
			postOrden += node.toStringFB()+"\t";
		}
		return postOrden;
	}
	
	/**
	 * Metodo que devuelve los valores del arbol con recorrido preOrden
	 * @return string
	 */
	public String preOrden(){
		String preOrden = "";
		if(this.raiz != null){
			preOrden += raiz.toString()+"\t";
			if(raiz.getLeft() != null){
				preOrden += preOrden(raiz.getLeft());
			}
			if(raiz.getRight() != null){
				preOrden += preOrden(raiz.getRight());
			}
		}
		return preOrden;
	}
	/**
	 * Metodo que devuelve los valores del arbol con recorrido preOrden
	 * @return string
	 */
	private String preOrden(AVLNode<T> node){
		String preOrden = "";
		if(node != null){
			preOrden += node.toString()+"\t";
			if(node.getLeft() != null){
				preOrden += preOrden(node.getLeft());
			}
			if(node.getRight() != null){
				preOrden += preOrden(node.getRight());
			}
		}
		return preOrden;
	}
	
	/**
	 * Metodo que devuelve los valores del arbol con recorrido inOrden
	 * @return string
	 */
	public String inOrden(){
		String inOrden = "";
		if(this.raiz != null){
			if(raiz.getLeft() != null){
				inOrden += inOrden(raiz.getLeft());
			}
			inOrden += raiz.toString()+"\t";
			if(raiz.getRight() != null){
				inOrden += inOrden(raiz.getRight());
			}
		}
		return inOrden;
	}
	/**
	 * Metodo auxiliar que devuelve los valores del arbol con recorrido inOrden
	 * @return string
	 */
	private String inOrden(AVLNode<T> node){
		String inOrden = "";
		if(node != null){
			if(node.getLeft() != null){
				inOrden += inOrden(node.getLeft());
			}
			inOrden += node.toString()+"\t";
			if(node.getRight() != null){
				inOrden += inOrden(node.getRight());
			}
		}
		return inOrden;
	}
	
	public boolean esAscendente(T a, T b){
		return getPadre(new AVLNode<T>(b)).getInfo().equals(a);
	}
	
	public boolean esDescendente(T a, T b){
		return getPadre(new AVLNode<T>(a)).getInfo().equals(b);
	}
	
	public int numAristas(T a, T b){
		int numAristas = 0;
		if(!a.equals(b)){
			AVLNode<T> padre = new AVLNode<T>(b);
			
			do{
				padre = getPadre(padre);
				numAristas++;
			}while(!padre.getInfo().equals(a));
			
		}
		return numAristas;
	}
	
	public int numNodos(T a, T b){
		return numAristas(a,b) + 1;
	}
	
	public int tamaño(){
		int tamaño = 0;
		if(this.raiz != null){
			if(raiz.getLeft() != null){
				tamaño += tamaño(raiz.getLeft());
			}
			if(raiz.getRight() != null){
				tamaño += tamaño(raiz.getRight());
			}
			tamaño ++;
		}
		return tamaño;
		
	}

	private int tamaño(AVLNode<T> raiz) {
		int tamaño = 0;
		if(raiz != null){
			if(raiz.getLeft() != null){
				tamaño += tamaño(raiz.getLeft());
			}
			if(raiz.getRight() != null){
				tamaño += tamaño(raiz.getRight());
			}
			tamaño ++;
		}
		return tamaño;
	}
	
	public int maxNodosAltura(int altura){
		return (int)Math.pow(2, altura-1);
	}
	
	public boolean estaLleno(){
		int altura = -1;
		if(raiz != null){
			altura = raiz.getAltura()+1;
		}
		return tamaño() == (int)Math.pow(2, altura) - 1;
	}
	
	public boolean esCompleto(){
		if(estaLleno()){
			return true;
		}
		else{
			int alturaMax = raiz.getAltura();
			return (maxNodosAltura(alturaMax) == nodoNivel(alturaMax-1)) && comprobarUltimoNivel(alturaMax-1);
		}
	}
	
	private boolean comprobarUltimoNivel(int altura){
		boolean resultado = true;
		boolean check = true;
		if(raiz != null){
			if(numAristas(this.raiz.getInfo(), raiz.getInfo()) == altura){
				if(raiz.getLeft() != null & check){
					if(raiz.getRight() == null){
						check = false;
					}
				}
				else if(raiz.getLeft() == null){
					if(raiz.getRight() != null){
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
			}
			if(raiz.getLeft() != null){
				resultado = resultado && comprobarUltimoNivel(altura,raiz.getLeft(), check);
			}
			if(raiz.getRight() != null){
				resultado = resultado && comprobarUltimoNivel(altura,raiz.getRight(), check);
			}
			
		}
		return resultado;
	}
	
	private boolean comprobarUltimoNivel(int altura, AVLNode<T> raiz,
			boolean check) {
		boolean resultado = true;
		if(raiz != null){
			if(numAristas(this.raiz.getInfo(), raiz.getInfo()) == altura){
				if(raiz.getLeft() != null & check){
					if(raiz.getRight() == null){
						check = false;
					}
				}
				else if(raiz.getLeft() == null){
					if(raiz.getRight() != null){
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
			}
			if(raiz.getLeft() != null){
				resultado = resultado && comprobarUltimoNivel(altura, raiz.getLeft(), check);
			}
			if(raiz.getRight() != null){
				resultado = resultado && comprobarUltimoNivel(altura, raiz.getRight(), check);
			}
			
		}
		return resultado;
	}

	public int numHojas(){
		int numHojas = 0;
		if(this.raiz != null){
			if(raiz.getLeft() != null){
				numHojas += numHojas(raiz.getLeft());
			}
			if(raiz.getRight() != null){
				numHojas += numHojas(raiz.getRight());
			}
			else if(raiz.getLeft() == null && raiz.getRight() == null){
				numHojas++;
			}
		}
		return numHojas;
	}

	private int numHojas(AVLNode<T> raiz) {
		int numHojas = 0;
		if(raiz != null){
			if(raiz.getLeft() != null){
				numHojas += numHojas(raiz.getLeft());
			}
			if(raiz.getRight() != null){
				numHojas += numHojas(raiz.getRight());
			}
			else if(raiz.getLeft() == null && raiz.getRight() == null){
				numHojas++;
			}
		}
		return numHojas;
	}
	
	public T[] camino(T a, T b){
		@SuppressWarnings("unchecked")
		T[] camino = (T[]) new Comparable[raiz.getAltura()];
		AVLNode<T> padre = new AVLNode<T>(b);
		int pos = 0;
		while(!(padre.compareTo(a) == 0)){
			padre = getPadre(padre);
			if(padre.compareTo(a) != 0){
				camino[pos] = padre.getInfo();
				System.out.println(padre.getInfo());
			}
			pos++;
		}
		return camino;
		
	}
	
	public T nodoMaximo(){
		return getMax(raiz);
	}
	
	public void mostrarPorAltura(int altura){
		String nodos = "";
		if(this.raiz != null){
			if(raiz.getLeft() != null){
				nodos += mostrarPorAltura(altura,raiz.getLeft());
			}
			if(raiz.getRight() != null){
				nodos += mostrarPorAltura(altura,raiz.getRight());
			}
			if(numAristas(this.raiz.getInfo(), raiz.getInfo()) == altura){
				nodos += "["+raiz+"]";
			}
		}
		System.out.println(nodos);
	}

	private String mostrarPorAltura(int altura, AVLNode<T> raiz) {
		String nodos = "";
		if(raiz != null){
			if(raiz.getLeft() != null){
				nodos += mostrarPorAltura(altura,raiz.getLeft());
			}
			if(raiz.getRight() != null){
				nodos += mostrarPorAltura(altura,raiz.getRight());
			}
			if(altura == numAristas(this.raiz.getInfo(), raiz.getInfo())){
				nodos += "["+raiz+"]";
			}
		}
		return nodos;
	}
	
	public int nodoNivel(int altura){
		int nodos = 0;
		if(this.raiz != null){
			if(raiz.getLeft() != null){
				nodos += nodoNivel(altura,raiz.getLeft());
			}
			if(raiz.getRight() != null){
				nodos += nodoNivel(altura,raiz.getRight());
			}
			if(numAristas(this.raiz.getInfo(), raiz.getInfo()) == altura){
				nodos ++;
			}
		}
		return nodos;
	}

	private int nodoNivel(int altura, AVLNode<T> raiz) {
		int nodos = 0;
		if(raiz != null){
			if(raiz.getLeft() != null){
				nodos += nodoNivel(altura,raiz.getLeft());
			}
			if(raiz.getRight() != null){
				nodos += nodoNivel(altura,raiz.getRight());
			}
			if(numAristas(this.raiz.getInfo(), raiz.getInfo()) == altura){
				nodos++;
			}
		}
		return nodos;
	}
	
	public boolean identicoA(AVLNode<T> node){
		boolean resultado = false;
		if(raiz != null){
			resultado = true;
			resultado = resultado && raiz.compareTo(node.getInfo()) == 0;
			if(raiz.getLeft() != null){
				resultado = resultado && identicoA(node.getLeft(),raiz.getLeft());
			}
			if(raiz.getRight() != null){
				resultado = resultado && identicoA(node.getRight(),raiz.getRight());
			}
		}
		return resultado;
	}

	private boolean identicoA(AVLNode<T> node, AVLNode<T> raiz) {
		boolean resultado = false;
		if(raiz != null){
			resultado = true;
			resultado = resultado && raiz.compareTo(node.getInfo()) == 0;
			if(raiz.getLeft() != null){
				resultado = resultado && identicoA(node.getLeft(),raiz.getLeft());
			}
			if(raiz.getRight() != null){
				resultado = resultado && identicoA(node.getRight(),raiz.getRight());
			}
		}
		if(node == null && raiz == null){
			resultado = true;
		}
		return resultado;
	}
	
	public AVLNode<T> getRaiz(){
		return raiz;
	}
	
	
}
