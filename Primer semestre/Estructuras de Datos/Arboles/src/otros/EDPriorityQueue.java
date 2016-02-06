package otros;

public interface EDPriorityQueue<T extends Comparable<T>> {
	
	/**
	 * Añade un elemento a la cola de prioridad 
	 * @param elemento Elemento que se quiere insertar en la cola
	 * @return true si consigue insertarlo, false en caso contrario
	 */
	public boolean add(T elemento);
	
	/**
	 * Devuelve y elemina el elemento con m�s prioridad
	 * @return El elemento con mayor prioridad, o null si no hay elementos
	 */
	public T poll();
	
	/**
	 * Borra un elemento de la cola
	 * @param elemento El elemento que se quiere eliminar de la cola
	 * @return true si estaba y lo elimina, false en caso contrario
	 */
	public boolean remove(T elemento);
	
	/**
	 * @return true si no hay ningun elemento
	 */
	public boolean isEmpty();
	
	/**
	 * Elimina todos los ementos de la cola
	 */
	public void clear();

	/**
	 * @return Un string con la cola de alguna forma visible
	 */
	public String toString();
}
