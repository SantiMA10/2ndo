using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TNP6
{
    class Program
    {
        static void Main(string[] args)
        {
            System.Console.WriteLine("Ejercicio 1");

            IEnumerable<int> p = new int[] { -1, 1, 2, -9, 3, 4 };
            Predicate<int> f = EsMayor(0);

            System.Console.Write("Antes: ");
            ImprimirVector(p);

            p = Filtrar(p, f);

            System.Console.Write("\nDespues: ");
            ImprimirVector(p);


            System.Console.WriteLine("\n\nEjercicio 2");

            const int terminoFibonacci = 40;
            IEnumerable<int> resultado;

            var cronoEager = new Stopwatch();
            cronoEager.Start();
            resultado = FibonacciEager(terminoFibonacci);
            cronoEager.Stop();
            long ticksFibonacciEager = cronoEager.ElapsedTicks;
            Console.WriteLine("Tiempo FibonacciEager: {0:N} ticks.", ticksFibonacciEager);

            var cronoLazy = new Stopwatch();
            cronoLazy.Start();
            resultado = FibonacciEager(terminoFibonacci);
            cronoLazy.Stop();
            long ticksFibonacciLazy = cronoLazy.ElapsedTicks;
            Console.WriteLine("Tiempo FibonacciLazy: {0:N} ticks.", ticksFibonacciLazy);
        }

        /// <summary>
        /// Metodo que imprime por consola el IEnumerable pasado como parametro
        /// </summary>
        /// <param name="p"></param>
        private static void ImprimirVector(IEnumerable<int> p)
        {
            for (int i = 0; i < p.Count(); i++)
            {
                System.Console.Write(p.ElementAt(i));
                if (i != (p.Count() - 1))
                {
                    System.Console.Write(", ");
                }
            }
        }

        /// <summary>
        /// Metodo auxilar para implementar el fibonacciLazy
        /// </summary>
        /// <returns></returns>
        private static IEnumerable<int> FibonacciInfinito()
        {
            int primero = 1, segundo = 1;
            while (true)
            {
                yield return primero;
                int suma = primero + segundo;
                primero = segundo;
                segundo = suma;
            }
        }

        /// <summary>
        /// Metodo que calcula Fibonacci hasta el elemento pasado como parametro con la evaluacion Lazy
        /// </summary>
        /// <param name="n"></param>
        /// <returns></returns>
        public static IEnumerable<int> FibonacciLazy(int n)
        {
            IList<int> resultado = new List<int>();

            int i = 0;
            foreach (int valor in FibonacciInfinito())
            {
                resultado.Add(valor);
                if (++i == n)
                    break;
            }
            return resultado;
        }

        /// <summary>
        /// Metodo que calcula Fibonacci hasta el elemento pasado como parametro con la evaluacion Eager
        /// </summary>
        /// <param name="n"></param>
        /// <returns></returns>
        public static IEnumerable<int> FibonacciEager(int n)
        {
            IList<int> resultado = new List<int>();

            if (n >= 0)
            {
                resultado.Add(1);
            }
            if (n >= 1)
            {
                resultado.Add(1);
            }
            for (int i = 2; i < n; i++)
            {
                resultado.Add(resultado[i - 1] + resultado[i - 2]); 
            }
            return resultado;
        }

        /// <summary>
        /// Metodo filtrar
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="a"></param>
        /// <param name="f"></param>
        /// <returns></returns>
        public static IEnumerable<T> Filtrar<T>(IEnumerable<T> a, Predicate<T> f)
        {
            List<T> resultado = new List<T>();
            foreach (T elemento in a)
            {
                if (f(elemento))
                {
                    resultado.Add(elemento);
                }
            }
            return resultado;
        }

        /// <summary>
        /// Funcion currificada para obtener el mayor
        /// </summary>
        /// <param name="a"></param>
        /// <returns></returns>
        static Predicate<int> EsMayor(int a)
        {
            return (b) => a < b;
        }

        /// <summary>
        /// Metodo map
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <typeparam name="Q"></typeparam>
        /// <param name="a"></param>
        /// <param name="f"></param>
        /// <returns></returns>
        static IEnumerable<Q> Map<T, Q>(IEnumerable<T> a, Func<T, IEnumerable<Q>> f)
        {
            IEnumerable<Q> resultado = new List<Q>();
            foreach (T elemento in a)
            {
                resultado = f(elemento);
            }
            return resultado;
        }
    }
}
