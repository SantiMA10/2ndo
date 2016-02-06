using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.TNP2
{
    public class Conjunto
    {
        private int numElementos;
        private Nodo raiz;

        /// <summary>
        /// Constructor de la clase conjutno
        /// </summary>
        public Conjunto()
        {
            numElementos = 0;
        }

        /// <summary>
        /// Redefinición del operador + que ahora añade elemento al conjunto.
        /// </summary>
        /// <param name="c">Conjunto en el que se quiere añadir el valor</param>
        /// <param name="valor">Valor que se quiere añadir al conjunto</param>
        /// <returns></returns>
        public static Conjunto operator +(Conjunto c, Object valor)
        {
            if (!c[valor])
            {
                if (c.raiz != null)
                {
                    Nodo aux = c.raiz;
                    while (aux.siguiente != null)
                    {
                        aux = aux.siguiente;
                    }
                    aux.siguiente = new Nodo(valor);
                }
                else
                {
                    c.raiz = new Nodo(valor);
                }
                c.numElementos++;
            }
            return c;
        }

        /// <summary>
        /// Redefinición del operador - que ahora borra valores de un conjunto
        /// </summary>
        /// <param name="c">Conjunto del que se quiere borrar</param>
        /// <param name="posicion">Posición que se quiere borrar dentro del conjunto</param>
        /// <returns></returns>
        public static Conjunto operator -(Conjunto c, int posicion)
        {
            if (posicion == 0)
            {
                c.raiz = c.raiz.siguiente;
                c.numElementos--;
            }
            else
            {
                Nodo actual = c.raiz.siguiente;
                Nodo anterior = c.raiz;
                for (int i = 1; i < c.numElementos; i++)
                {
                    if (i == posicion)
                    {
                        anterior.siguiente = actual.siguiente;
                        c.numElementos--;
                    }
                    anterior = actual;
                    actual = actual.siguiente;
                }
            }
            return c;
        }

        /// <summary>
        /// Redefinición del operador [] que ahora comprueba si un valor esta dentro del conjunto 
        /// </summary>
        /// <param name="valor">Valor que si quiere comprobar si esta dentro del conjunto</param>
        /// <returns>True en caso de que el valor este ya en el conjunto, False en caso contrario</returns>
        public bool this[Object valor]
        {
            get
            {
                Nodo aux = this.raiz;
                while (aux != null)
                {
                    if (aux.valor.Equals(valor)) {
                        return true;
                    }
                    aux = aux.siguiente;
                }
                return false;
            }
            
        }

        /// <summary>
        /// Redefinición del método toString
        /// </summary>
        /// <returns>String con todos los valores del conjunto</returns>
        public override string ToString()
        {
            String toString = "";
            Nodo aux = this.raiz;
            while (aux != null)
            {
                toString += aux.valor;
                aux = aux.siguiente;
            }
            return toString;
        }

        /// <summary>
        /// Redefinición del operador | que ahora calcula la unión de los dos conjuntos con los que se use
        /// </summary>
        /// <param name="c1">Conjunto uno del que se quiere obtener la unión</param>
        /// <param name="c2">Conjunto dos del que se quiere obtener la unión</param>
        /// <returns>Conjunto unión de los usados como parametros</returns>
        public static Conjunto operator |(Conjunto c1, Conjunto c2)
        {
            Nodo aux = c2.raiz;
            Conjunto union = c1;
            while (aux != null)
            {
                if (!union[aux.valor])
                {
                    union += aux.valor;
                }
                aux = aux.siguiente;
            }
            return union;
        }

        /// <summary>
        /// Redefinición del operador & que ahora calcula la intersección de los conjuntos con los que se use
        /// </summary>
        /// <param name="c1">Conjunto uno del que se quiere obtener la intersección</param>
        /// <param name="c2">Conjunto uno del que se quiere obtener la intersección</param>
        /// <returns>Conjunto intersección de los usados como parametros</returns>
        public static Conjunto operator &(Conjunto c1, Conjunto c2)
        {
            Nodo aux = c1.raiz;
            Conjunto interseccion = new Conjunto();

            while (aux != null)
            {
                if (c2[aux.valor])
                {
                    interseccion += aux.valor;
                }
                aux = aux.siguiente;
            }
            return interseccion;
        }

        /// <summary>
        /// Redefinición del operador & que ahora calcula la diferencia de los conjuntos con los que se use
        /// </summary>
        /// <param name="c1">Conjunto uno del que se quiere obtener la diferencia</param>
        /// <param name="c2">Conjunto uno del que se quiere obtener la diferencia</param>
        /// <returns>Conjunto diferencia de los usados como parametros</returns>
        public static Conjunto operator -(Conjunto c1, Conjunto c2)
        {
            Conjunto diferencia = new Conjunto();
            Nodo aux = c1.raiz;

            while(aux != null)
            {
                if (!c2[aux.valor])
                {
                    diferencia += aux.valor;
                }
                aux = aux.siguiente;
            }
            return diferencia;
        }

        /// <summary>
        /// Método que devuelve el tamaño del conjunto
        /// </summary>
        /// <returns>int tamaño del conjunto</returns>
        public int Size()
        {
            return numElementos;
        }
    }
}
