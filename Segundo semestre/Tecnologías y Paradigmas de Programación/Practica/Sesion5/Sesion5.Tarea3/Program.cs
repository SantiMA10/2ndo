using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sesion5.Tarea3
{
    class Program
    {
        static void Main(string[] args)
        {
            IEnumerable<String> entrada = new String[]{"hola","","adios"};
            int[] salida = new int[] { 4, 0, 5 }, resultado;
            resultado = CalcularValores<String, int>(tamañoCadena, entrada);
        }

        public static int tamañoCadena(String cadena)
        {
            return cadena.Length;
        }

        public static IEnumerable<R> CalcularValores<P, R>(Func<P, R> f, IEnumerable<P> parametro)
        {
            IEnumerable<R> sol = new R[parametro.Count<P>()];
            for (int i = 0; i < parametro.Count<P>(); i++)
            {
                sol[i] = f(parametro.ElementAt<P>(i));
            }
            return sol;
        }
    }
}
