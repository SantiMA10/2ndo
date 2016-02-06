using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.Examen
{
    class ListaOrdenadaEnumerator<T> : IEnumerator<T> where T : IComparable<T>
    {
        private ListaOrdenada<T> lista;
        private int pos = -1;

        public ListaOrdenadaEnumerator(ListaOrdenada<T> lista)
        {
            this.lista = lista;
        }

        public T Current
        {
            get { return lista.GetElemento(pos); }
        }

        public void Dispose()
        {
        }

        object System.Collections.IEnumerator.Current
        {
            get { return lista.GetElemento(pos); }
        }

        public bool MoveNext()
        {
            if (pos + 1 != lista.Size())
            {
                pos++;
                return true;
            }
            else 
            {
                return false;
            }
        }

        public void Reset()
        {
            pos = 0;
        }
    }
}
