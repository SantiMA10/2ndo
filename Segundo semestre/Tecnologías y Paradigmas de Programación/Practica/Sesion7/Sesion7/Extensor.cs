using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sesion7
{
    static public class Extensor
    {
        public static T Buscar<T>(this IEnumerable<T> coleccion, Predicate<T> funcion)
        {
            foreach (T elemento in coleccion)
            {
                if (funcion(elemento))
                {
                    return elemento;
                }
            }
            return default(T);
        }

        public static IEnumerable<Q> Map<T, Q>(this IEnumerable<T> coleccion, Func<T, Q> funcion)
        {
            foreach(T elem in coleccion)
            {
                yield return funcion(elem);
            }
        }

        public static IEnumerable<T> Filtrar<T>(this IEnumerable<T> coleccion, Predicate<T> funcion)
        {
            foreach (T elemento in coleccion)
            {
                if (funcion(elemento))
                {
                    yield return elemento;
                }
            }
        }

        public static D Reducir<P, D>(this IEnumerable<P> coleccion, D semilla, Func<P, D, D> funcion)
        {
            foreach (P elemento in coleccion)
            {
                semilla = funcion(elemento, semilla);
            }
            return semilla;
        }

        public static D Invertir<P, D>(this IEnumerable<P> coleccion, D semilla, Func<P, D, D> funcion)
        {
            coleccion = coleccion.Reverse();
            foreach (P elemento in coleccion)
            {
                semilla = funcion(elemento, semilla);
            }
            return semilla;
        }

        static public void ForEach(this IEnumerable<int> coleccion, Action<int> accion)
        {
            foreach (int elemento in coleccion)
            {
                accion(elemento);
            }
        }

    }
}
