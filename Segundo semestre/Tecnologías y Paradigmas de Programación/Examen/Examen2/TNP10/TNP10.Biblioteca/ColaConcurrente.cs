using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TNP10.Biblioteca
{
    public class ColaConcurrente<T>
    {
        private Lista<T> lista = new Lista<T>();

        public int NumeroElementos {
            get {
                lock (lista) {
                    return lista.Size();
                }
            } 
        }

        public bool EstaVacia {
            get {
                lock (lista) {
                    return lista.Size() == 0;
                }
            }
        }

        public void Añadir(T elemento)
        {
            lock (lista)
            {
                lista.AñadirAlFinal(elemento);
            }
        }

        public bool Extraer(out T valor)
        {
            lock (lista)
            {
                bool resultado = false;
                try{
                    valor = lista.GetElemento(0);
                    lista.Borrar(0);
                    resultado = true;
                }catch(ArgumentException e){
                    valor = default(T);
                }
                return resultado;
            }
        }

        public bool PrimerElemento(out T valor)
        {
            lock (lista)
            {
                bool resultado = false;
                try
                {
                    valor = lista.GetElemento(0);
                    resultado = true;
                }
                catch (ArgumentException e)
                {
                    valor = default(T);
                }
                return resultado;
            }
        }
    }
}
