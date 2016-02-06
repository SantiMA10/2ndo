using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sesion2
{
    class Program
    {
        static void Main(string[] args)
        {
            int x = 5;
            int y = 10;

            System.Console.WriteLine("X: {0} Y: {1}\nCambiando...",x , y);

            intercambiar(ref x, ref y);

            System.Console.WriteLine("X: {0} Y: {1}", x, y);

            System.Console.WriteLine(pedirDatos());
        }

        static void intercambiar(ref int x, ref int y)
        {
            int temp = x;
            x = y;
            y = temp;
        }

        static String pedirDatos()
        {
            String nombre, apellidos, NIF;

            System.Console.Write("Introduce tu nombre: ");
            nombre = Console.In.ReadLine();
            System.Console.Write("Introduce tus apellidos: ");
            apellidos = Console.In.ReadLine();
            System.Console.Write("Introduce tu NIF: ");
            NIF = Console.In.ReadLine();

            return String.Format("Nombre: {0} ,Apellidos: {1} ,NIF: {2}", nombre, apellidos, NIF);
        }
    }
}
