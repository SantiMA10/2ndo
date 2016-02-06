using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.Practicas.OrientacionObjetos.Practica3
{
    public class Programa
    {
          static Persona[] CrearPersonas() {
            string[] nombres = { "María", "Juan", "Pepe", "Luis", "Carlos", "Miguel", "Cristina" };
            string[] apellidos = { "Díaz", "Pérez", "Hevia", "García", "Rodríguez", "Pérez", "Sánchez" };
            int numeroPersonas = 100;

            Persona[] listado = new Persona[numeroPersonas];
            Random random = new Random();
            for (int i = 0; i < numeroPersonas; i++)
                listado[i] = new Persona(
                    nombres[random.Next(0, nombres.Length)],
                    apellidos[random.Next(0, apellidos.Length)],
                    apellidos[random.Next(0, apellidos.Length)],
                    random.Next(9000000, 90000000) + "-" + (char)random.Next('A', 'Z')
                    );
            return listado;
        }

        /// <summary>
        /// Muestra una colección de personas por consola
        /// </summary>
        static void Mostrar(Persona[] personas) {
            foreach (Persona persona in personas) {
                Console.WriteLine(persona);
            }
            Console.WriteLine();
        }

      
        /// <summary>
        /// Complete el siguiente código
        /// </summary>
        static void Main() {
            Persona[] personas = CrearPersonas();
            Mostrar(personas);
        }
    }

      
}
