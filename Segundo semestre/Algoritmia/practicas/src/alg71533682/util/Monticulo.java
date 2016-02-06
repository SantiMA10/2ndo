// ESTRUCTURA DE DATOS DE TIPO MONT?CULO
// OFRECE UN COMPORTAMIENTO LOGAR?TMICO
// PARA METER NODOS Y SACAR EL MEJOR

package alg71533682.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Monticulo 
{
	private PriorityQueue<Nodo> nodos;
	// n?mero nodos del mont?culo

	private HashMap<Integer,Nodo> nodosUsados; 
	// nodos para componer la soluci?n y tambi?n
	// para saber qu? nodos han sido tratados


	public Monticulo() 
	{
		nodos = new PriorityQueue<Nodo>();
		nodosUsados = new HashMap<Integer,Nodo>();
	}

	public void crearVacia() 
	{
		nodos.clear();
	}

	public void insertar(Nodo nodo) 
	{
		if (!nodoRepetido(nodo)) 
			// para no repetir nodos utilizados y evitar as? bucles
			// infinitos (por ejemplo en el problema del puzzle)
		{
			nodos.add(nodo);
		}

	}

	private boolean nodoRepetido(Nodo nodo) 
	{
//		for (Nodo n:nodosUsados.values())
//		{
//			if (nodo.equals(n))
//				return true;
//		}
		return false;
	}

	public boolean vacia() 
	{
		return nodos.isEmpty();
	}

	public int estimacionMejor() 
	{
		return nodos.peek().getHeuristico();
	}

	public Nodo sacarMejorNodo() 
	{
		Nodo nodo=nodos.poll();
		nodosUsados.put(nodo.hashCode(),nodo);
		// lo guardamos porque puede ser parte de la 
		// soluci?n. Adem?s, de este modo podemos 
		// saber si un nodo ya ha sido tratado
		return nodo;
	}

	public ArrayList<Nodo> rutaHastaRaiz(Nodo nodo) 
	/* calcula la ruta hasta el nodo raiz */
	{
		ArrayList<Nodo>resultado=new ArrayList<Nodo>();

		resultado.add(nodo); //a?adimos el ?ltimo nodo tratado
		int idPadre = nodo.getIdPadre(); //buscamos su padre

		while (idPadre != -1) 
			//mientras no s llegue al nodo raiz
		{
			nodo = nodosUsados.get(idPadre);
			resultado.add(nodo);
			idPadre = nodo.getIdPadre();
		}

		return resultado;
	}
}
