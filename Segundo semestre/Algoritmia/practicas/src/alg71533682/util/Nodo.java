// CLASE PARA INSTANCIAR OBJETOS DE TIPO NODO
// DEL ?RBOL DE DESARROLLO DE ESTADOS DEL PROBLEMA.
// HABR? QUE CONCRETAR ESTA CLASE PARA CADA PROBLEMA

package alg71533682.util;

import java.util.ArrayList;

public abstract class Nodo implements Comparable<Nodo> 
{
	protected int profundidad; 
	//n?mero de movimientos hasta ese instante (es igual  
	//al n?mero de nodos desarrollados) en una rama

	protected int idPadre; 
	//ID del nodo padre para poder realizar trazabilidad

	protected int valorHeuristico; 
	//valor del heur?stico calculado

	public Nodo() 
	{ //valores por defecto
		profundidad = 0; 
		idPadre = -1; //no tiene nodo padre el nodo ra?z 
	}

//	public int getProfundidad() 
//	{
//		return profundidad;
//	}
//
	public int getIdPadre() 
	{
		return idPadre;
	}

	/**
	 * Devuelve valor del heur?stico correspondiente al estado
	 * Se utiliza internamente para comparar estados y 
	 * @return
	 */
	public int getHeuristico() 
	{
		return valorHeuristico; 
	}

	public boolean equals(Nodo n) 
	{
		return (n.toString().equals(toString()));
	}

	public int valorInicialPoda() 
	{
		return Integer.MAX_VALUE; //por defecto
	}

	/**
	 * Para implementar la interfaz Comparable que utiliza
	 * el mont?culo de Ramificaci?n y poda
	 */
	@Override
	public int compareTo(Nodo nodo2) 
	{
		if (valorHeuristico > nodo2.valorHeuristico) 
			return 1; //this tiene más prioridad
		else if (valorHeuristico == nodo2.valorHeuristico) {
			return 0;
		}
		else return -1; //this tienen menos prioridad
	}

	/**
	 * Calcula el valor del heur?stico y lo guarda
	 */
	public abstract void calcularValorHeuristico();

	/**
	 * Genera todos los hijos v?lidos del actual
	 * @return ArrayList con todos los hijos
	 */
	public abstract ArrayList<Nodo> expandir();

	/**
	 * Comprueba si el estado actual es solucion
	 * @return true- si es soluci?n
	 */
	public abstract boolean solucion();

}
