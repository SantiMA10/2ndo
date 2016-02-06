using System;

namespace TNP10.Biblioteca
{
    public class Lista<T>
    {
        private Nodo<T> raiz { get; set; }
        private int numElementos { get; set; }

        /// <summary>
        /// Clase nodo
        /// </summary>
        class Nodo<R>
        {
            internal R valor;
            internal Nodo<R> siguiente;

            /// <summary>
            /// Contructor de la clase Nodo
            /// </summary>
            /// <param name="valor">Valor que va a almacenar el nodo</param>
            public Nodo(R valor)
            {
                this.valor = valor;
                siguiente = null;
            }

            public Nodo(R valor, Nodo<R> nodo)
            {
                // TODO: Complete member initialization
                this.valor = valor;
                this.siguiente = nodo;
            }

        }

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
            else
            {
                throw new ArgumentException();
            }
        }

        /// <summary>
        /// Método que borrar el elemento de la posición que se pasa por parametro
        /// </summary>
        /// <param name="posicion">int posicion del elemento que se desea borrar</param>
        public void Borrar(int posicion)
        {
            bool borrado = false;
            if (posicion == 0)
            {
                this.Borrar();
                borrado = true;
            }
            else
            {
                Nodo<T> actual = this.raiz.siguiente;
                Nodo<T> anterior = this.raiz;
                for (int i = 1; i < numElementos; i++)
                {
                    if (i == posicion)
                    {
                        anterior.siguiente = actual.siguiente;
                        numElementos--;
                        borrado = true;
                    }
                    anterior = actual;
                    actual = actual.siguiente;
                }
            }
            if (!borrado)
            {
                throw new ArgumentException();
            }

        }

        /// <summary>
        /// Método que añade al final de la lista el objeto que se pase como parametro
        /// </summary>
        /// <param name="valor">Object que se quiere añadir a la lista</param>
        public void AñadirAlFinal(T valor) 
        {
            if (valor != null)
            {
                if (this.raiz != null)
                {
                    Nodo<T> aux = this.raiz;
                    while (aux.siguiente != null)
                    {
                        aux = aux.siguiente;
                    }
                    aux.siguiente = new Nodo<T>(valor);
                }
                else
                {
                    this.raiz = new Nodo<T>(valor);
                }
                numElementos++;
            }
            else
            {
                throw new ArgumentNullException();
            }
        }

        /// <summary>
        /// Método que añade en la posicion elegida de la lista el objeto que se pase como parametro
        /// </summary>
        /// <param name="valor">Object que se quiere añadir a la lista</param>
        public void AñadirEn(T valor, int posicion)
        {
            if (valor != null && posicion > 0 && posicion < Size())
            {
                Nodo<T> aux = this.raiz;
                int con = 0;
                while (aux.siguiente != null)
                {
                    con++;
                    if (con == posicion)
                    {
                        aux.siguiente = new Nodo<T>(valor, aux.siguiente);
                    }
                    aux = aux.siguiente;
                }

                numElementos++; 
            }
            else if (posicion == 0)
            {
                AñadirAlPrincipio(valor);

            }
            else if (posicion == Size())
            {
                AñadirAlFinal(valor);
            }
            else if (valor == null)
            {
                throw new ArgumentNullException();
            }
            else if (posicion < 0 || posicion > Size())
            {
                throw new ArgumentException();
            }
        }

        /// <summary>
        /// Método que añade al principio de la lista el objeto que se pase como parametro
        /// </summary>
        /// <param name="valor">Object que se quiere añadir a la lista</param>
        public void AñadirAlPrincipio(T valor)
        {
            if (valor != null)
            {
                if (this.raiz != null)
                {
                    this.raiz = new Nodo<T>(valor, this.raiz);
                }
                else
                {
                    this.raiz = new Nodo<T>(valor);
                }
                numElementos++;
            }
            else
            {
                throw new ArgumentNullException();
            }
        }

        /// <summary>
        /// Método que devuelve el valor del Object de la lista en la posición que se pasa como parametro
        /// </summary>
        /// <param name="posicion">int posición del elemento que se quiere obtener</param>
        /// <returns>Object valor de la posición deseada</returns>
        public T GetElemento(int posicion)
        {
            Nodo<T> aux = this.raiz;
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
                    throw new ArgumentException();
                }
            }
            throw new ArgumentException();
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
        public bool Contiene(T valor)
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
