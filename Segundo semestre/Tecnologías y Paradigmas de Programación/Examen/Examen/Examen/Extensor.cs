using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.Examen
{
    static public class Extensor
    {
        /// <summary>
        /// Busca el primer elemento que cumpla la condicion de la función pasada como parametro.
        /// FirstOrDefault en LinQ
        /// </summary>
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

        /// <summary>
        /// Aplica la funcion pasada como parametro a todos los elementos de la coleccion pasada como parametro.
        /// No tiene porque devolver una coleccion del mismo tipo que la entrada.
        /// Select en LinQ
        /// </summary>
        public static IEnumerable<Q> Map<T, Q>(this IEnumerable<T> coleccion, Func<T, Q> funcion)
        {
            foreach(T elem in coleccion)
            {
                yield return funcion(elem);
            }
        }

        /// <summary>
        /// Devuelve un conjunto de elementos de la coleccion que cumple la condicion explicada en la funcion.
        /// Where en LinQ
        /// </summary>
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

        /// <summary>
        /// Transforma los elementos de la conleccion segun la funcion pasada como parametro, tiene una semilla opcional.
        /// Aggregate en LinQ
        /// </summary>
        public static D Reducir<P, D>(this IEnumerable<P> coleccion, Func<P, D, D> funcion, D semilla = default(D))
        {
            foreach (P elemento in coleccion)
            {
                semilla = funcion(elemento, semilla);
            }
            return semilla;
        }

        /// <summary>
        /// Transforma los elementos de la conleccion segun la funcion pasada como parametro, tiene una semilla opcional.
        /// Recorre la colección de forma inversa.
        /// </summary>
        public static D Invertir<P, D>(this IEnumerable<P> coleccion, Func<P, D, D> funcion, D semilla = default(D))
        {
            coleccion = coleccion.Reverse();
            foreach (P elemento in coleccion)
            {
                semilla = funcion(elemento, semilla);
            }
            return semilla;
        }

        /// <summary>
        /// Aplica una accion a cada elemento de una coleccion.
        /// </summary>
        static public void ForEach(this IEnumerable<int> coleccion, Action<int> accion)
        {
            foreach (int elemento in coleccion)
            {
                accion(elemento);
            }
        }

    }
}
