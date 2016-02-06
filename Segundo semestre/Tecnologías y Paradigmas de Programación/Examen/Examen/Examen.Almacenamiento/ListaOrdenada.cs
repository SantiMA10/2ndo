using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.Examen
{
    //IENumerable<T> foreach Y IComparable<T> ordenada
    public class ListaOrdenada<T>: IEnumerable<T> where T: IComparable<T>
    {
        private Lista<T> lista;

        /// <summary>
        /// Constructor de la clase
        /// </summary>
        public ListaOrdenada()
        {
            lista = new Lista<T>();
        }

        /// <summary>
        /// Metodo que permite añadir un elemento a la lista ordenada
        /// </summary>
        /// <param name="objecto">El objeto que se quiere añadir a la lista</param>
        public void Añadir(T objecto)
        {
            if (objecto != null)
            {
                bool añadido = false;
                if (Size() > 0)
                {

                    for (int i = 0; i < lista.Size(); i++)
                    {
                        if (lista.GetElemento(i).CompareTo(objecto) > 0)
                        {
                            lista.AñadirEn(objecto, i);
                            añadido = true;
                            break;
                        }

                    }
                }
                else
                {
                    lista.AñadirEn(objecto, 0);
                    añadido = true;
                }
                if (!añadido)
                {
                    lista.AñadirEn(objecto, Size());
                }
            }
            else
            {
                throw new ArgumentNullException();
            }
        }

        /// <summary>
        /// Metodo para borrar un elemento de la lista ordenada pasando el indice
        /// </summary>
        /// <param name="indice">Indice del valor que se quiere borrar</param>
        public void Borrar(int indice)
        {
            lista.Borrar(indice);
        }

        /// <summary>
        /// Metodo para obtener el elemento de la posicion que se pasa como parametro
        /// </summary>
        /// <param name="posicion">Indice del valor que se quiere obtener</param>
        /// <returns>Valor correspondiente al indice pasado como parametro</returns>
        public T GetElemento(int posicion)
        {
            return lista.GetElemento(posicion);
        }

        /// <summary>
        /// Metodo para que funcione el foreach
        /// </summary>
        /// <returns></returns>
        public IEnumerator<T> GetEnumerator()
        {
            return new ListaOrdenadaEnumerator<T>(this);
        }

        /// <summary>
        /// Metodo que devuelve el tamaño de la lista;
        /// </summary>
        /// <returns>Tamaño de la lista</returns>
        public int Size()
        {
            return lista.Size();
        }

        /// <summary>
        ///  Metodo para que funcione el foreach
        /// </summary>
        /// <returns></returns>
        System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator()
        {
            return new ListaOrdenadaEnumerator<T>(this);
        }
    }
}
