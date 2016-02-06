using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TPP.Practicas.Funcional.Practica1;

namespace TNP5
{
    public class Program
    {
        static void Main(string[] args)
        {
            Persona[] p = Factoría.CrearPersonas(), p1;
            Angulo[] a = Factoría.CrearAngulos(), a1;

            System.Console.WriteLine("Buscar: ");

            System.Console.WriteLine(Buscar(p, delegate(Persona per) { return per.Nombre == "María" && per.Nif.Contains("A"); }));
            System.Console.WriteLine(Buscar(a, delegate(Angulo an) { return an.Grados == 90 ; }));

            System.Console.WriteLine("Filtrar: ");

            p1 = Filtrar(p, delegate(Persona per) { return per.Nombre == "María"; });
            a1 = Filtrar(a, delegate(Angulo an) { return an.Grados == 90; });

            System.Console.WriteLine("Reducir: ");

            System.Console.WriteLine("Suma de angulos: "+Reducir(a, delegate(Angulo an, float r) { return r + an.Grados; }));
            Dictionary<String, int> d;
            d = Reducir<Persona, Dictionary<String, int>>(p, DistribucionDeNombres);
            foreach (KeyValuePair<String, int> elemento in d)
            {
                System.Console.WriteLine(elemento.Key + ", " + elemento.Value);
            }
        }

        /// <summary>
        /// Método para reducir los elementos de una colección según el parametro f
        /// </summary>
        /// <typeparam name="P">Tipo de la coleccion</typeparam>
        /// <typeparam name="D">Tipo resultado de reducir la coleccion</typeparam>
        /// <param name="a">Colección a reducir</param>
        /// <param name="f">Funcion que reduce la colección</param>
        /// <returns>Un valor</returns>
        public static D Reducir<P, D>(P[] a, Func<P, D, D> f)
        {
            D resultado = default(D);
            for (int i = 0; i < a.Length; i++)
            {
                resultado = f(a[i], resultado);
            }
            return resultado;
        }

        /// <summary>
        /// Función pasada como parametro a reducir para obtener el maximo seno
        /// </summary>
        /// <param name="a"></param>
        /// <param name="seno"></param>
        /// <returns></returns>
        public static double SenoMaximo(Angulo a, double seno)
        {
            if (seno < a.Seno())
            {
                return a.Seno();
            }
            return seno;
        }

        /// <summary>
        /// Funcion pasada como parametro a reducir para obtener el numero de veces que sale cada nombre en una coleccion
        /// </summary>
        /// <param name="p"></param>
        /// <param name="d"></param>
        /// <returns></returns>
        public static Dictionary<String, int> DistribucionDeNombres(Persona p, Dictionary<String, int> d)
        {
            if (d == null)
            {
                d = new Dictionary<String, int>();
            }
            int cantidad = 1;
            if (d.ContainsKey(p.Nombre))
            {
                d.TryGetValue(p.Nombre, out cantidad);
                cantidad++;
                d[p.Nombre] = cantidad;
            }
            else
            {
                d.Add(p.Nombre, cantidad);
            }

            return d;
        }

        /// <summary>
        /// Método que devuelve el primer elemento de una coleccion que cumple la condición.
        /// </summary>
        /// <typeparam name="T">Tipo del vector que elementos</typeparam>
        /// <param name="a">Vector de elementos</param>
        /// <param name="f">Función con la condicion a cumplir</param>
        /// <returns>El primer elemento que cumple la condición</returns>
        public static T Buscar<T>(T[] a, Predicate<T> f){
            for (int i = 0; i < a.Length; i++)
            {
                if (f(a[i]))
                {
                    return a[i];
                }
            }
            return default(T);
        }

        /// <summary>
        /// Método que a partir de una colección de elementos, nos devuelve todos aquellos que 
        /// cumplan un criterio dado, pasado como parametro.
        /// </summary>
        /// <typeparam name="T">Tipo del vector que elementos</typeparam>
        /// <param name="a">Vector de elementos</param>
        /// <param name="f">Función con la condicion a cumplir</param>
        /// <returns>El conjunto de elementos que cumple la condición</returns>
        public static T[] Filtrar<T>(T[] a, Predicate<T> f)
        {
            List<T> resultado = new List<T>();
            for (int i = 0; i < a.Length; i++)
            {
                if (f(a[i]))
                {
                    resultado.Add(a[i]);
                }
            }
            return resultado.ToArray();
        }
    }
}
