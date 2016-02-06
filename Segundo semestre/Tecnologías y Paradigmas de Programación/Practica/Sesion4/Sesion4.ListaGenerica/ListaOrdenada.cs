using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.TNP3
{
    //IENumerable<T> foreach Y IComparable<T> ordenada
    class ListaOrdenada<T>: IEnumerable<T> where T: IComparable<T>
    {
        private Lista<T> lista;

        public ListaOrdenada()
        {
            lista = new Lista<T>();
        }

        public void Añadir(T objecto)
        {
            if (objecto != null)
            {
                for(int i = 0; i < lista.Size(); i++)
                {
                    //AñadirEn para Lista
                }
            }
            else
            {
                throw new ArgumentNullException();
            }
        }
    }
}
