using System;

namespace TPP.TNP1
{
    public class Lista
    {
        private Nodo raiz;
        public int numElementos;

        public Lista()
        {
            numElementos = 0;
        }

        ~Lista()
        {
        }

        public void Borrar()
        {
            this.raiz = raiz.siguiente;
            numElementos--;
        }

        public void Añadir(int valor) 
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

        public int GetElemento(int posicion)
        {
            Nodo aux = this.raiz;
            for (int i = 0; i < numElementos; i++)
            {
                if (i == posicion)
                {
                    return aux.valor;
                }
                aux = aux.siguiente;
            }
            return -1;
        }

    }
}
