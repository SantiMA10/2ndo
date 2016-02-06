using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.TNP2
{
    class Nodo
    {
        internal Object valor;
        internal Nodo siguiente;

        /// <summary>
        /// Contructor de la clase Nodo
        /// </summary>
        /// <param name="valor">Valor que va a almacenar el nodo</param>
        public Nodo(Object valor){
            this.valor = valor;
            siguiente = null;
        }
    }
}
