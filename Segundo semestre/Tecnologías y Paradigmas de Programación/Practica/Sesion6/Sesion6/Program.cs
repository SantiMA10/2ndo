using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sesion6
{
    class Program
    {
        static void Main(string[] args)
        {
            // Ejemplo clausulas
            int valor = 1;
            Func<int> doble = () => valor * 2;
            System.Console.WriteLine(doble());
            valor = 7;
            System.Console.WriteLine(doble());
            // Fin ejemplo clausulas
        }
    }
}
