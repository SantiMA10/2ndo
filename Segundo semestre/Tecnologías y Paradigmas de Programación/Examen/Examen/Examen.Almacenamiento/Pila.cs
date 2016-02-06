using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Diagnostics.Contracts;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.Examen
{
    public class Pila<T>
    {
  
        private Lista<T> lista;
        private uint numMaxElementos;

        public bool EstaVacia{ 
            get {
                return lista.Size() == 0; 
            } 
        }

        public bool EstaLlena { 
            get { 
                return lista.Size() == numMaxElementos; 
            } 
        }

        /// <summary>
        /// Constructor con un parametro para la clase pila.
        /// </summary>
        /// <param name="maxElementos">Número máximo de elementos que se pueden guardar en la pila.</param>
        public Pila(uint maxElementos)
        {
            this.numMaxElementos = maxElementos;
            lista = new Lista();
        }

        /// <summary>
        /// Método que introduce un valor en la pila
        /// </summary>
        /// <param name="valor">Valor que si quiere introducir en la pila</param> 
        public void Push(Object valor)
        {
            //Debug.Assert(!EstaLlena, "La pila esta llena");
            //Debug.Assert(valor != null, "El valor pasado como parametro es null");
            if (!EstaLlena && valor != null)
            {
                lista.AñadirAlPrincipio(valor);
            }
            else if (valor == null)
            {
                throw new ArgumentNullException();
            }
            else if (EstaLlena)
            {
                throw new StackOverflowException();
            }
        }

        /// <summary>
        /// Método que devuelve el último objeto metido en la pila
        /// </summary>
        /// <returns>Último objeto metido en la pila</returns>
        public Object Pop()
        {
            //Debug.Assert(!EstaVacia, "La pila esta vacia");
            Object aux = null;
            if (!EstaVacia)
            {
                aux = lista.GetElemento(0);
                lista.Borrar();
            }
            else 
            {
                throw new InvalidOperationException();
            }
            return aux;
        }
    }
}
