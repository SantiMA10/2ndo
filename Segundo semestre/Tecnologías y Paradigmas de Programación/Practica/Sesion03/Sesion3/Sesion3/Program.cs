using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TPP.Practicas.OrientacionObjetos.Practica3;

namespace Sesion3
{
    class Program
    {
        static void Main(string[] args)
        {
            Object[] i = new Object[] { 1, 2, new Angulo(Math.PI), 
                new Persona("Pepe", "Gonzalez", "Gonzalez", "32546789-Z"), new Angulo(33), 
                new Persona("Santiago", "Martin", "Agra", "71533682-W") };

            System.Console.WriteLine(i[(int)indiceDe(i, 1)]);
            System.Console.WriteLine(i[(int)indiceDe(i, 2)]);

            System.Console.WriteLine(i[(int)indiceDe(i, new Angulo(Math.PI))]);
            System.Console.WriteLine(i[(int)indiceDe(i, new Angulo(33))]);

            System.Console.WriteLine(i[(int)indiceDe(i, new Persona("Pepe", "Gonzalez", "Gonzalez", "32546789-Z"))]);
            System.Console.WriteLine(i[(int)indiceDe(i, new Persona("Santiago", "Martin", "Agra", "71533682-W"))]);

            System.Console.WriteLine(i[(int)indiceDe(i, new Persona("Santiago", "Lopez", "Gonzalez", "85412365-U"), 
                new ComparacionPorNombre())]);

            System.Console.WriteLine(i[(int)indiceDe(i, new Angulo(10),
                new ComparacionPorCuadrante())]);
        }

        public static int? indiceDe(Object[] array, Object objeto, IPredicadoIgualdad predicado = null)
        {
            if (array != null && objeto != null)
            {
                for (int i = 0; i < array.Length; i++)
                {
                    if ((predicado == null && array[i].Equals(objeto)) || 
                        (predicado != null && predicado.Comparacion(array[i], objeto)))
                    {
                        return i;
                    }
                }
            }

            return null;
        }
    }
}
