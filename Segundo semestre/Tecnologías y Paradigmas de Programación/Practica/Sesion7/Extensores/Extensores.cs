using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Extensores
{
    static public class Extensores
    {
        public static T Buscar<T>(this IEnumerable<T> a, Predicate<T> f)
        {
            foreach (T elemento in a)
            {
                if (f(elemento))
                {
                    return elemento;
                }
            }
            return default(T);
        }

        public static IEnumerable<Q> Map<T, Q>(this IEnumerable<T> a, Func<T, IEnumerable<Q>> f)
        {
       
            IEnumerable<Q> resultado = new List<Q>();
            foreach (T elemento in a)
            {
                resultado = f(elemento);
            }
            return resultado;
        }

        public static IEnumerable<T> Filtrar<T>(this IEnumerable<T> a, Predicate<T> f)
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

        public static D Reducir<P, D>(this IEnumerable<P> a, D semilla, Func<P, D, D> f)
        {
            foreach (P elemento in a)
            {
                semilla = f(elemento, semilla);
            }
            return semilla;
        }

        public static D Invertir<P, D>(this IEnumerable<P> a, D semilla, Func<P, D, D> f)
        {
            a = a.Reverse();
            foreach (P elemento in a)
            {
                semilla = f(elemento, semilla);
            }
            return semilla;
        }

        static public void ForEach(this IEnumerable<int> v, Action<int> a)
        {
            foreach (int elemento in v)
            {
                a(elemento);
            }
        }
    }
}
