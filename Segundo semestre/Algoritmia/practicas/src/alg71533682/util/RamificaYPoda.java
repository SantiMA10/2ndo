// ESQUEMA GENERAL RAMIFICA Y PODA

package alg71533682.util;

import java.util.ArrayList;

public class RamificaYPoda 
{
	protected Monticulo cola; 
	//nodos para ser explorados(a?n no utilizados)

	protected Nodo mejorSolucion; 
	//para guardar el nodo final de la mejor soluci?n

	protected Nodo nodoRaiz; //nodo inicial

	protected int cotaPoda;
	
	public int nodosVisitados = 0;

	public RamificaYPoda() 
	{
		cola = new Monticulo(); //crea Monticulo vac?o
	}

	public void ramificaYPoda(Nodo nodoRaiz) 
	{ 
//		cola.crearVacia(); 
		cola.insertar(nodoRaiz); 

		cotaPoda = nodoRaiz.valorInicialPoda();

		while (!cola.vacia() && cola.estimacionMejor()<cotaPoda)
		{
			Nodo actual = cola.sacarMejorNodo();
//			System.out.println("sacarMejorNodo");
//			System.out.println(actual);

			ArrayList<Nodo> hijos = actual.expandir(); 

			for (Nodo estadoHijo : hijos)
			{
				if (estadoHijo.getHeuristico() < cotaPoda) {
					nodosVisitados++;
					if (estadoHijo.solucion())
					{
						mejorSolucion = estadoHijo;
						cotaPoda = estadoHijo.getHeuristico();
					}
					else
						cola.insertar(estadoHijo);
				}
			}
			
		} //while
		
	}


	public Nodo getNodoRaiz() 
	{
		return nodoRaiz;
	}


	public void mostrarTrazaSolucion() 
	{
		if (mejorSolucion==null) 
		{
			System.out.println("Original:");
			System.out.println(nodoRaiz);
			System.out.println("NO HAY SOLUCION");
		} 
		else
		{
			ArrayList<Nodo>resultado=cola.rutaHastaRaiz(mejorSolucion);
			//camino de los nodos desde el mejor hasta ra?z

			for (int i = 1;i<=resultado.size();i++)
			{
				if (i==1)
				{
					System.out.println("Original:");
					System.out.println(resultado.get(resultado.size()-i));
				}
				else
				{
					System.out.println("Paso " + (i-1)+ ":");
					System.out.println(resultado.get(resultado.size()-i));
				}
			}
//			System.out.println("\nSOLUCION DE "+mejorSolucion.getProfundidad()+" PASOS");	

		}
	}
}
