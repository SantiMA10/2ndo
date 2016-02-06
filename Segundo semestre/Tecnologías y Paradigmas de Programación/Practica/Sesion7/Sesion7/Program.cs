using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TPP.TNP3;

namespace Sesion7
{
    class Program
    {
        static void Main(string[] args)
        {
            IEnumerable<int> enteros = new int[]{ 1, 2, 3, 4, 5, 6};
            System.Console.WriteLine("Con Función");
            enteros.ForEach(Imprimir);
            System.Console.WriteLine("Con Expresión lambda");
            enteros.ForEach(a => System.Console.WriteLine(a));

            ListaOrdenada<int> lista = new ListaOrdenada<int>();
            lista.Añadir(1);
            lista.Añadir(3);
            lista.Añadir(2);
            lista.Añadir(6);
            lista.Añadir(7);

            IEnumerable<int> resultado = lista.Filtrar((x) => x > 5);
            resultado.ForEach(Imprimir);

            //FirstOrDefault == Buscar
            //Select == Map
            //Where == Filtrar
            //Aggregate == Reducir
        }

        static void Imprimir(int a)
        {
            System.Console.WriteLine(a);
        }
        
    }
}
