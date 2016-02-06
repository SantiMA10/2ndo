using System;

namespace TPP.TNP1
{
    public class Lista
    {
        private Nodo raiz { get; set; }
        private int numElementos { get; set; }

        public Lista()
        {
            System.Console.WriteLine("Lista creada");
            numElementos = 0;
        }

        ~Lista()
        {
            System.Console.WriteLine("Lista destruida");
        }

        public int Size()
        {
            return numElementos;
        }

        public void Borrar()
        {
            this.raiz = raiz.siguiente;
            numElementos--;
        }

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

    }
}
