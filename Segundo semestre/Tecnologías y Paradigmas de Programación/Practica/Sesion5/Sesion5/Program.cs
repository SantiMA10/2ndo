using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sesion5
{

    class Program
    {
        static void Main(string[] args)
        {
            IEnumerable<double> prueba = new double[] { -3, -2, -1, 0, 1, 2, 3 };
            double[] solucion = new double[] { 9,  4,  1, 0, 1, 4, 9 };
            prueba = CalcularValores(FXCuadrado, prueba);
            int i = 0;
            foreach(double valor in prueba)
            {
                Console.WriteLine(valor == solucion[i]);
                i++;
            }
        }

        /// <summary>
        /// Delegado para una función que: reciva un real y retorne un real.
        /// </summary>
        /// <param name="a">un real(double en este caso)</param>
        /// <returns>un real(double en este caso)</returns>
        public delegate double funcion(double a);

        /// <summary>
        /// Delegada de funcion
        /// </summary>
        /// <param name="a">un real(double en este caso)</param>
        /// <returns>un real(double en este caso)</returns>
        public static double FXCuadrado(double a)
        {
            return Math.Pow(a,2);
        }

        /// <summary>
        /// Funcion de orden superior
        /// </summary>
        /// <param name="f">delegado de funcion</param>
        /// <param name="d">IEnumerable</param>
        /// <returns>IEnumerable</returns>
        public static IEnumerable<double> CalcularValores(Func<double, double> f, IEnumerable<double> d)
        {
            double[] sol = new double[d.Count<double>()];
            int i = 0;
            foreach(double valor in d){
                sol[i] = f(valor);
                i++;
            }
            return sol;
        }

    }
}