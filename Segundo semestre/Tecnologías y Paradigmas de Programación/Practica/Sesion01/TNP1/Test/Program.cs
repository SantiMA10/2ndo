using System;

namespace TPP.TNP1
{
    class Program
    {
        static void Main()
        {
            Lista lista = new Lista();
            for (int i = 0; i < 11; i++)
            {
                lista.Añadir(i);
            }
            System.Console.WriteLine("NumElementos: " + lista.Size());
            for (int i = 0; i < 11; i++)
            {
                System.Console.WriteLine("Elemento " + i +": " + lista.GetElemento(i));
            }
            System.Console.WriteLine("Elemento 0: " + lista.GetElemento(0)+"\nBorrando elemento 0...");
            lista.Borrar(0);
            System.Console.WriteLine("Elemento 0: " + lista.GetElemento(0));
            for (int i = 0; i < 10; i++)
            {
                lista.Borrar();
            }
            System.Console.WriteLine("NumElementos: " + lista.Size());
            System.Console.WriteLine("Error: " + lista.GetElemento(0));
        }
    }
}
