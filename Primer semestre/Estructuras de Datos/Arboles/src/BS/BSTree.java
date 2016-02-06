package BS;

public class BSTree<T extends Comparable<T>> {
	private BSTNode<T> raiz; //Nodo raiz del �rbol
	
	public BSTree(){
	
	}
	
	/**
	 *  Metodo que devuelve false en caso de que el nodo a añadir ya exista dentro 
	 *  del arbol en caso contrario true.
	 * @param x valor que se desea añadir
	 * @return true si lo hace, false en caso contrario
	 */
	public boolean add(T x){
		boolean result = false;
		if(this.raiz == null){
			raiz = new BSTNode<T>(x);
			result = true;
		}
		else{
			result = add(this.raiz, x);
		}
		return result;
	}
	
	/**
	 *  Metodo auxiliar que devuelve false en caso de que el nodo a añadir ya exista dentro 
	 *  del arbol en caso contrario true.
	 * @param x valor que se desea añadir
	 * @return true si lo hace, false en caso contrario
	 */
	private boolean add(BSTNode<T> nodo, T x){
		boolean result = false;
		if(nodo == null){
			nodo = new BSTNode<T>(x);
			result = true;
		}
		if(find(x) == null){
			if(nodo.compareTo(x) > 0){
				if(nodo.getLeft() == null){
					nodo.setLeft(new BSTNode<T>(x));
					result = true;
				}
				else{
					result = add(nodo.getLeft(),x);
				}
			}
			else if(nodo.compareTo(x) < 0){
				if(nodo.getRight() == null){
					nodo.setRight(new BSTNode<T>(x));
					result = true;
				}
				else{
					result = add(nodo.getRight(),x);
				}
			}
		}
		return result;
	}
	
	/**
	 * Metodo que nos permite encontar un nodo en el arbol pasandole el valor, devuelve 
	 * el nodo con ese valor.
	 * @param x el valor del nodo a buscar
	 * @return el nodo buscado
	 */
	public BSTNode<T> find(T x){
		BSTNode<T> node = null;
		if(this.raiz != null){
			if(raiz.compareTo(x) != 0){
				if(raiz.getLeft() != null){
					node = find(raiz.getLeft(), x);
					if(node != null){
						return node;
					}
				}
				if(raiz.getRight() != null){
					node = find(raiz.getRight(), x);
					if(node != null){
						return node;
					}
				}
			}
			else{
				return raiz;
			}
		}
		return node;
	}
	/**
	 * Metodo auxiliar que nos permite encontar un nodo en el arbol pasandole el valor, devuelve 
	 * el nodo con ese valor.
	 * @param x el valor del nodo a buscar
	 * @return el nodo buscado
	 */
	public BSTNode<T> find(BSTNode<T> raiz, T x){
		BSTNode<T> node = null;
		if(this.raiz != null){
			if(raiz.compareTo(x) != 0){
				if(raiz.getLeft() != null){
					node = find(raiz.getLeft(), x);
					if(node != null){
						return node;
					}
				}
				if(raiz.getRight() != null){
					node = find(raiz.getRight(), x);
					if(node != null){
						return node;
					}
				}
			}
			else{
				return raiz;
			}
		}
		return node;
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
	private String preOrden(BSTNode<T> node){
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
	private String inOrden(BSTNode<T> node){
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
	
	/**
	 * Metodo que devuelve los valores del arbol con recorrido postorden
	 * @return string
	 */
	public String postOrden(){
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
	 * @return string
	 */
	private String postOrden(BSTNode<T> node){
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
	 * Metodo que borra el nodo con el valor pasado como parametro si se borra devuelve true,
	 * false en caso contrario
	 * @param x valor del nodo a borrar
	 * @return true si se borra, false en caso contrario
	 */
	public boolean remove(T x){
		boolean result = false;
		if(this.raiz != null){
			if(raiz.compareTo(x) == 0){
				if(this.raiz.getLeft() == null && this.raiz.getRight() == null){
					this.raiz = null;
					result = true;
				}
				else{
					if(raiz.getLeft() == null && raiz.getRight() != null){
						raiz = raiz.getRight();
						result = true;
					}
					else if(raiz.getLeft() != null && raiz.getRight() == null){
						raiz = raiz.getLeft();
						result = true;
					}
					else{
						x = getMax(raiz);
						remove(raiz.getLeft(), x);
						raiz.setInfo(x);
						result = true;
					}
				}
			}
			else if(raiz.compareTo(x) < 0){
				result = remove(raiz.getRight(), x);
			}
			else{
				result = remove(raiz.getLeft(), x);
			}
		}
		return result;
	}
	
	/**
	 * Metodo auxiliar para borra el nodo con el valor pasado como parametro si se borra devuelve true,
	 * false en caso contrario
	 * @param x valor del nodo a borrar, la raiz desde donde se empieza a buscar el nodo
	 * @return true si se borra, false en caso contrario
	 */
	private boolean remove(BSTNode<T> raiz, T x) {
		boolean result = false;
		if(raiz != null){
			if(raiz.compareTo(x) == 0){
				if(raiz.getLeft() == null && raiz.getRight() == null){
					raiz = null;
					result = true;
				}
				else{
					if(raiz.getLeft() == null && raiz.getRight() != null){
						raiz = raiz.getRight();
						result = true;
					}
					else if(raiz.getLeft() != null && raiz.getRight() == null){
						raiz = raiz.getLeft();
						result = true;
					}
					else{
						x = getMax(raiz.getLeft());
						raiz.setInfo(x);
						result = true;
					}
				}
			}
			else if(raiz.compareTo(x) < 0){
				result = remove(raiz.getRight(), x);
			}
			else{
				result = remove(raiz.getLeft(), x);
			}
		}
		return result;
	}

	/**
	 * Devuelve el valor maximo del arbol pasado y lo extrae del arbol
	 * @param raiz del arbol del que se desea obtener el mayor
	 * @return el valor del nodo mayor
	 */
	private T getMax(BSTNode<T> raiz){
		BSTNode<T> anterior = null;
		while (raiz.getRight() != null){
			anterior = raiz;
			raiz = raiz.getRight();
		}
		if(anterior != null){
			anterior.setRight(null);
		}
		return raiz.getInfo();
	}
}
