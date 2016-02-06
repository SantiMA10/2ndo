package grafos;

public class Graph<T> {
	private double[][] weights;
	private boolean[][] edges;
	private T[] nodes;
	private int size;
	
	@SuppressWarnings("unchecked")
	public Graph(int size) {
		weights = new double[size][size];
		setEdges(new boolean[size][size]);
		nodes = (T[]) new Object[size];
		this.size = 0;
	}
	
	/**
	 * Metido que añade un nodo a al grafo. Falla si el nodo ya existe en el grafo.
	 * 
	 * @param node que se desaea añadir
	 * @return ret -1 si ya estaba el nodo, 0 si no estaba
	 */
	public int addNode(T node) {
		if(existNode(node) || node == null) {
			return -1;
		}
		if(getSize() < nodes.length) {
			nodes[size] = node;
			size++;
			return 0;
		}
		return -1;
	}
	
	/**
	 * Metodo que devuelve la posicion del nodo que se pasa como parametro
	 * @param node nodo a buscar
	 * @return posicion del nodo a buscar, -1 en caso de no encontrarlo
	 */
	public int getNode(T node){
		for(int i = 0; i < size; i++){
			if(nodes[i].equals(node)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Metodo que devuelve el peso del camino entre nodos
	 * @param source nodo de origen
	 * @param target nodo objetivo
	 * @return devuelve el peso del camino entre los dos nodos, si no existe devuelve +inf.
	 */
	public double getEdge(T source, T target) {
		int positionSource = getNode(source);
		int positionTarget = getNode(target);
		if(positionSource >= 0 && positionTarget >= 0) {
			return (weights[positionSource][positionTarget]);
		}
		else {
			return Double.POSITIVE_INFINITY;
		}
	}
	
	/**
	 * Metodo elimina la conexión entre dos nodos
	 * @param source nodo de origen
	 * @param target nodo objetivo
	 * @return devuelve 1 si la operación se ha llevado acabo, -1 en caso contrario
	 */
	public int removeEdge(T source, T target) {
		int positionSource = getNode(source);
		int positionTarget = getNode(target);
		if(positionSource >= 0 && positionTarget >= 0) {
			getEdges()[positionSource][positionTarget] = false;
			weights[positionSource][positionTarget] = Double.POSITIVE_INFINITY;
			return 0;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Metodo elimina un nodos
	 * @param source nodo de origen
	 * @param target nodo objetivo
	 * @return devuelve 1 si la operación se ha llevado acabo, -1 en caso contrario
	 */
	public int removeNode(T node){
		int position = getNode(node);
		
		if(position >= 0) {
			if(position != size) {
				nodes[position] = nodes[size];
				
				for(int i = 0; i <= size; i++){
					getEdges()[i][position] = getEdges()[i][size];
					getEdges()[position][i] = getEdges()[size][i];
					
					weights[position][i] = weights[size][i];
					weights[i][position] = weights[i][size];
				}
				getEdges()[position][position] = getEdges()[size][size];
				weights[position][position] = weights[size][size];
			}
			size = size-1;
			return 0;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Metodo que devuelve si existe un nodo o no
	 * @param node nodo a buscar 
	 * @return	true si el nodo esta, false en caso contrario
	 */
	public boolean existNode(T node){
		if(node == null) {
			return false;
		}
		else {
			for(int i = 0; i < size; i++) {
				if(nodes[i].equals(node))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo que devuelve el tamaño del array de nodos.
	 * @return tamaño del array de nodos.
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * Método que implementa el Algoritmo de Floyd.
	 * @return matriz de costes minimos
	 */
	public double[][] floyd(){
		double[][] a = initializeFloydA();
		T[][] p = initializeFloydP();
		
		for(int pivot = 0; pivot < size; pivot++){
			for(int source = 0; source < size; source++){
				for(int target = 0; target < size; target++){
					if(a[source][pivot] + a[pivot][target] < a[source][target]){
						a[source][target] = a[source][pivot] + a[pivot][target];
						p[source][target] = nodes[pivot];
					}
				}
			}
		}
		return a;
	}
	
	/**
	 * Método que inicializa la matriz P del algoritmo de Floyd.
	 * @return inicializacion de la matriz P del algoritmo de Floyd
	 */
	@SuppressWarnings("unchecked")
	private T[][] initializeFloydP() {
		T[][] p = (T[][])new Object[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(edges[i][j]){
					p[i][j] = nodes[i];
				}
			}
		}
		return p;
	}

	/**
	 * Método que inicializa la matriz A del algoritmo de Floyd.
	 * @return inicialización de la matriz A del algorimo de Floyd.
	 */
	private double[][] initializeFloydA() {
		double[][] a = new double[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(edges[i][j]){
					a[i][j] = weights[i][j];
				}
				else if(i == j){
					a[i][j] = 0;
				}
				else {
					a[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		return a;
	}

	/**
	 * Metodo inicial de Dijkstra
	 * @param source nodo desde sobre el que queremos calcular Dijkstra.
	 * @return el vector D, el de costes.
	 */
	public double[] dijkstra(T source){
		if(!existNode(source)){
			return null;
		}
		boolean[] s = new boolean[size];
		s[getNode(source)] = true;
		//inicializaci�n de costes
		double[] d = initializeDijkstraD(source);
		//inicializaci�n de caminos
		T[] p = initializeDijkstraP(source);
		dijkstra(s,d,p);
		return d;
	}
	/**
	 * Metodo auxiliar que implemente el algoritmo de Dijkstra en su forma recursiva.
	 * @param s vector de booleanos quue indica al algoritmo si un nodo ha sido visitado.
	 * @param d vector de costes.
	 */
	private void dijkstra(boolean[] s, double[] d, T[] p){
		int minEdgeNode = minEdge(d,s);
		if(minEdgeNode != -1) {
			s[minEdgeNode] = true;
			for(int i = 0; i < size; i++) {
				if(getEdges()[minEdgeNode][i] 
						&& (d[minEdgeNode] + weights[minEdgeNode][i]) < d[i]) {
					d[i] = d[minEdgeNode] + weights[minEdgeNode][i];
					p[i] = nodes[minEdgeNode];
				}
			}
			dijkstra(s,d,p);
		}
	}
	
	/**
	 * Metodo auxiliar que busca la arista de coste minimo que parta desde los nodos visitados
	 * por el algoritmo hacia los vectores aun por visitar.
	 * @param d vector de pesos minimos.
	 * @param s vector que indica si los nodos ya ha sido visitados o no.
	 * @return indice del nodo sin visitar con asista minima, -1 si no quedan.
	 */
	private int minEdge(double[] d, boolean[] s){
		int minEdge = -1;
		for(int i = 0; i < size; i++){
			if(!s[i] && (minEdge == -1 || d[i] < d[minEdge])){
				minEdge = i;
			}
		}
		return minEdge;
	}
	
	/**
	 * Metodo auxiliar que genera un vector D inicializado.
	 * @param node Nodo para el que se desa inicializar el vector D.
	 * @return el vector D, el vector de costes.
	 */
	private double[] initializeDijkstraD(T node){
		double[] d = new double[size];
		int nodePosition = getNode(node);
		for(int i = 0; i < size; i++){
			if(nodePosition == i){
				d[i] = 0.0;
			}
			else if(getEdges()[nodePosition][i]){
				d[i] = new Double(weights[nodePosition][i]);
			}
			else {
				d[i] = Double.POSITIVE_INFINITY;
			}
		}
		return d;
	}
	
	/**
	 * Metodo auxiliar que genera un vector P inicializado.
	 * @param node Nodo para el que se desa inicializar el vector P.
	 * @return el vector P, el vector de caminos.
	 */
	private T[] initializeDijkstraP(T node){
		@SuppressWarnings("unchecked")
		T[] p = (T[]) new Object[size];
		int nodePosition = getNode(node);
		for(int i = 0; i < size; i++){
			if(getEdges()[nodePosition][i] || nodePosition == i) {
				p[i] = node;
			}
		}
		return p;
	}
	
	/**
	 * Metodo que añade una arista entre dos nodos al grafo
	 * @param source nodo fuente
	 * @param target nodo objetivo
	 * @param weight coste del desplazamiento
	 * @return 0 si se añade correctamente, -1 si no se añade.
	 */
	public int addEdges(T source, T target, double weight){
		if(existEdge(source, target)) {
			return -1;
		}
		else {
			int i = getNode(source);
			int j = getNode(target);
			
			getEdges()[i][j] = true;
			weights[i][j] = weight;
			
			return 0;
		}
		
	}

	/**
	 * Metodo que comprueba si existe arista entre dos nodos
	 * @param source nodo de origen
	 * @param target nodo destino
	 * @return true si existe conexion entre nodos, false en caso contrario
	 */
	public boolean existEdge(T source, T target) {
		if(!existNode(source)|| !existNode(target)){
			return false;
		}
		else {
			int i = getNode(source);
			int j = getNode(target);
			
			return getEdges()[i][j];
		}
		
	}

	/**
	 * Metodo que inicializa el algoritmo de recorrido en profundidad
	 * @param source el nodo desde el que se parte
	 * @return -1 si no se han visitado todos los nodos, 0 si se han visitado todos los nodos
	 */
	public int deepFirstSearch(T source){
		if(!existNode(source)){
			return -1;
		}
		
		boolean[] visited = new boolean[size];
		recursiveDFS(source, visited);
		return allNodesVisited(visited);
	}
	
	/**
	 * Metodo que implementa el algoritmo de recorrido en profuncidad de forma recursiva.
	 * @param source nodo del que se parte.
	 * @param visited matriz booleana con los nodos visitados.
	 */
	private void recursiveDFS(T source, boolean[] visited){
		 System.out.println(source);
		 
		 int position = getNode(source);
		 visited[position] = true;
		 
		 for(int i = 0; i < size; i++){
			 if(!visited[i] && existEdge(source, nodes[i])){
				 recursiveDFS(nodes[i], visited);
			 }
		 }
	}
	
	/**
	 * Metodo que comprueba si todos los nodos han sido visitados
	 * @param visited matriz booleana con los nodos visitados
	 * @return -1 si no se han visitado todos los nodos, 0 si se han visitado todos los nodos
	 */
	private int allNodesVisited(boolean[] visited){
		for(int i = 0; i < size; i++){
			if(!visited[i]) {
				return -1;
			}
		}
		return 0;
	}
	
	public boolean[][] getEdges() {
		return edges;
	}

	public void setEdges(boolean[][] edges) {
		this.edges = edges;
	}
	
}
