using System;

namespace TPP.TNP2
{
    public class Lista
    {
        private Nodo raiz { get; set; }
        private int numElementos { get; set; }

        /// <summary>
        /// Constructor de la clase lista
        /// </summary>
        public Lista()
        {
            System.Console.WriteLine("Lista creada");
            numElementos = 0;
        }

        /// <summary>
        /// Desconstructor de la clase lista
        /// </summary>
        ~Lista()
        {
            System.Console.WriteLine("Lista destruida");
        }

        /// <summary>
        /// Método que devuelve el tamaño de la lista
        /// </summary>
        /// <returns>int el tamaño de la lista</returns>
        public int Size()
        {
            return numElementos;
        }

        /// <summary>
        /// Método que borra el primer elemento de la lista
        /// </summary>
        public void Borrar()
        {
            if (this.raiz != null)
            {
                this.raiz = raiz.siguiente;
                numElementos--;
            }
        }

        /// <summary>
        /// Método que borrar el elemento de la posición que se pasa por parametro
        /// </summary>
        /// <param name="posicion">int posicion del elemento que se desea borrar</param>
        public void Borrar(int posicion)
        {
            if (posicion == 0)
            {
                this.Borrar();
            }
            else
            {
                Nodo actual = this.raiz.siguiente;
                Nodo anterior = this.raiz;
                for (int i = 1; i < numElementos; i++)
                {
                    if (i == posicion)
                    {
                        anterior.siguiente = actual.siguiente;
                        numElementos--;
                    }
                    anterior = actual;
                    actual = actual.siguiente;
                }
            }

        }

        /// <summary>
        /// Método que añade al final de la lista el objeto que se pase como parametro
        /// </summary>
        /// <param name="valor">Object que se quiere añadir a la lista</param>
        public void Añadir(Object valor) 
        {
            if (this.raiz != null)
            {

                Nodo aux = this.raiz;
                while (aux.siguiente != null)
                {
                    aux = aux.siguiente;
                }
                aux.siguiente = new Nodo(valor);
            }
            else
            {
                this.raiz = new Nodo(valor);
            }
            numElementos++;
        }

        /// <summary>
        /// Método que devuelve el valor del Object de la lista en la posición que se pasa como parametro
        /// </summary>
        /// <param name="posicion">int posición del elemento que se quiere obtener</param>
        /// <returns>Object valor de la posición deseada</returns>
        public Object GetElemento(int posicion)
        {
            Nodo aux = this.raiz;
            for (int i = 0; i < numElementos; i++)
            {
                if (i == posicion)
                {
                    return aux.valor;
                }
                if (aux != null)
                {
                    aux = aux.siguiente;
                }
                else
                {
                    return null;
                }
            }
            return null;
        }

        /// <summary>
        /// Redeficinición del metodo toString
        /// </summary>
        /// <returns>String con todos los valores de la lista</returns>
        public override string ToString()
        {
            String toString = "";
            for (int i = 0; i < this.Size(); i++)
            {
                toString += this.GetElemento(i);
            }
            return toString;
        }

        /// <summary>
        /// Método que comprueba si la lista contiene el valor que se pasa como parametro
        /// </summary>
        /// <param name="valor">Object valor que se desea comprobar si esta en la lista</param>
        /// <returns>bool true si esta en la lista, false en caso contrario.</returns>
        public bool Contiene(Object valor)
        {
            for (int i = 0; i < this.Size(); i++)
            {
                if (this.GetElemento(i).Equals(valor))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
