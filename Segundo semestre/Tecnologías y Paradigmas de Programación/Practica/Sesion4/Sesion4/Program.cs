using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sesion4
{
    class Program
    {
        static void Main(string[] args)
        {
            int x = 0, y = 1;
            Intercambio<int>(ref x, ref y);
            Console.WriteLine("x: {0} y: {1}", x, y);
            String s1 = "Hola", s2 = "Adios";
            Intercambio<String>(ref s1, ref s2);
            Console.WriteLine("s1: {0} s2: {1}", s1, s2);
            Console.WriteLine("{0}", Maximo<int>(x,y));

        }

        static void Intercambio<T>(ref T p1, ref T p2)
        {
            T temp = p1;
            p1 = p2;
            p2 = temp;
        }

        static IComparable Maximo(IComparable p1, IComparable p2)
        {
            if (p1.CompareTo(p2) > 0)
            {
                return p1;
            }
            else
            {
                return p2;
            }
        }

        /*
        // Solución poco elegante
        static IComparable<T> Maximo<T>(IComparable<T> p1, IComparable<T> p2)
        {
            if (p1.CompareTo(((T)p2)) > 0)
            {
                return (p1);
            }
            else
            {
                return (p2);
            }
        }*/

        // Solución elegante
        static T Maximo<T>(T p1, T p2) where T: IComparable<T>
        {
            return p1.CompareTo(p2) > 0 ? p1 : p2;
        }
    }
}
