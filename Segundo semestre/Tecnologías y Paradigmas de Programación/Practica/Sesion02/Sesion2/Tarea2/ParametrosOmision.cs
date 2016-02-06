
using System;
namespace TPP.Practicas.OrientacionObjetos.Practica2 {

    /// <summary>
    /// Ejercicio de utilización de parámetros por omisión
    /// </summary>
    class ParametrosOmision {


        /// <summary>
        /// Este método debería filtrar todas las personas que:
        /// - Con un nombre determinado
        /// - Con unos apellidos determinados
        /// - Con un nif que contenga una cadena determinada
        /// La comparación NO será sensible mayúsculas/minúsculas
        /// Devolviendo sólo aquellos que cumplan ese criterio.
        /// </summary>
        /// <param name="personas">La colección original de personas</param>
        /// <param name="nombre">El nombre que tiene que tener la persona para no ser filtrada</param>
        /// <param name="apellido1">El apellido1 que tiene que tener la persona para no ser filtrada</param>
        /// <param name="apellido2">El apellido2 que tiene que tener la persona para no ser filtrada</param>
        /// <param name="nifContiene">Caracteres que tiene que contener el nif</param>
        /// <returns>Una colección con aquellas personas que cumplan esos criterios</returns>
        static Persona[] Filtrar(Persona[] personas, string nombre = null, string apellido1 = null, string apellido2 = null, string nifContiene = null)
        {
            // * Array.Resize cambia el tamaño de un array
            Persona[] listaFiltrada = new Persona[personas.GetLength(0)];
            int pos = 0;
            for(int i = 0; i < personas.GetLength(0); i++){
                if ((nombre != null && personas[i].Nombre.Equals(nombre)) && (apellido1 != null && personas[i].Apellido1.Equals(apellido1) &&
                    (apellido2 != null && personas[i].Apellido2.Equals(apellido2) && (nifContiene != null && nifContiene.Contains(personas[i].Nif)))))
                {
                    listaFiltrada[pos] = personas[i];
                    pos++;
                }
            }
            Array.Resize(ref listaFiltrada, pos);
            return listaFiltrada;
        }


        /// <summary>
        /// Crea una lista de personas aleatoriamente
        /// </summary>
        /// <returns></returns>
        static Persona[] CrearPersonas()
        {
            string[] nombres = { "María", "Juan", "Pepe", "Luis", "Carlos", "Miguel", "Cristina" };
            string[] apellidos = { "Díaz", "Pérez", "Hevia", "García", "Rodríguez", "Pérez", "Sánchez" };
            int numeroPersonas = 10;

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
            Console.WriteLine("Personas con nombre María:");
            // * ¿Cómo se haría?
            Mostrar(Filtrar(personas, nombre:"María"));
            Console.WriteLine("Personas con los dos apellidos Pérez:");
            // * ¿Cómo se haría?
            Filtrar(personas, apellido1: "Pérez", apellido2: "Pérez");
            Console.WriteLine("Personas cuyo NIF contiene la A");
            // * ¿Cómo se haría?
            Filtrar(personas, nifContiene: "A");
        }
    }

}
