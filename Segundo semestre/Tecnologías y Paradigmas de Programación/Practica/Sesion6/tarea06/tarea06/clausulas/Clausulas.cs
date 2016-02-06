using System;
using System.Collections.Generic;

namespace TPP.Practicas.Funcional.Practica2 {

    /// <summary>
    /// Trate de saber qué muestra este programa antes de ejecutarlo
    /// </summary>
    class Clausulas {

        static Func<int> Contador() {
            int contador = 0;
            return () => ++contador;
        }

        static void Contador1(out Func<int> f1, out Func<int> f2, out Func<int, int> f3)
        {
            int contador = 0;
            f1 = () => ++contador;
            f2 = () => --contador;
            f3 = (cont) => contador = cont; 
        }

        static void Main() {
            /**
             * 
            Console.WriteLine("<Contador>");
            Func<int> contador = Contador();
            Console.WriteLine(contador());
            Console.WriteLine(contador());
            
            Func<int> otroContador = Contador();
            Console.WriteLine("<OtroContador>");
            Console.WriteLine(otroContador());
            Console.WriteLine(otroContador());
            Console.WriteLine(otroContador());

            Console.WriteLine("<Contador>");
            Console.WriteLine(contador());
            Console.WriteLine(contador());

            

            Func<int> contadorInc, contadorDec;
            Func<int, int> contadorAs;
            Contador1(out contadorInc, out contadorDec, out contadorAs);

            Console.WriteLine("<ContadorInc>");
            Console.WriteLine(contadorInc());
            Console.WriteLine(contadorInc());

            Console.WriteLine("<ContadorDec>");
            Console.WriteLine(contadorDec());
            Console.WriteLine(contadorDec());
            Console.WriteLine(contadorDec());

            Console.WriteLine("<ContadorAsi>");
            Console.WriteLine(contadorAs(21));
            Console.WriteLine("<ContadorInc>");
            Console.WriteLine(contadorInc());
            Console.WriteLine("<ContadorDec>");
            Console.WriteLine(contadorDec());
            Console.WriteLine(contadorDec());
            **/

            int[] prueba = new int[] { 1, 2, 3, 4 };
            Func<int, int> f = SumaC(5);
            prueba = Map(prueba, f);
            for (int i = 0; i < prueba.Length; i++)
            {
                System.Console.WriteLine(prueba[i]);
            }
        }
        //Currificación de la suma
        static Q[] Map<T, Q>(T[] a, Func<T, Q> f)
        {
            Q[] resultado = new Q[a.Length];
            for (int i = 0; i < a.Length; i++)
                resultado[i] = f(a[i]);
            return resultado;
        }

        static IEnumerable<Q> MapIEnumerable<T, Q>(IEnumerable<T> a, Func<T, IEnumerable<Q>> f)
        {
            IEnumerable<Q> resultado = new List<Q>();
            int i = 0;
            foreach (T elemento in a)
            {
                resultado = f(elemento);
            }
            return resultado;
        }

        static Func<int, int> SumaC(int a)
        {
            return b => a + b;
        }

    }

}
