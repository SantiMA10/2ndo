using System;
using System.IO;
using System.Threading;
using System.Text;
using System.Linq;
using System.Collections.Generic;

namespace TPP.Practicas.Concurrente.Practica3 {

    public static class Procesamiento {

        public static String LeerFicheroTexto(string nombreFichero) {
            using (StreamReader stream = File.OpenText(nombreFichero)) {
                StringBuilder texto = new StringBuilder();
                string linea;
                while ((linea = stream.ReadLine()) != null) {
                    texto.AppendLine(linea);
                }
                return texto.ToString();
            }
        }

        public static string[] PartirEnPalabras(String texto) {
            return texto.Split(new char[] { ' ', '\r', '\n', ',', '.', ';', ':', '-', '!', '¡', '¿', '?', '/', '«', 
                                            '»', '_', '(', ')', '\"', '*', '\'', 'º', '[', ']', '#' },
                StringSplitOptions.RemoveEmptyEntries);
        }

        public static int SignosPuntuación(string texto) {
            return texto.Count(carácter => carácter == '.' || carácter == ',' || carácter == ';' || carácter == ':'); 
        }

        public static string[] PalabrasMasLargas(string[] palabras) {
            return palabras
                .GroupBy( palabra => palabra.Length)  // emparejamos por longitud
                .OrderByDescending( grupo => grupo.Key)  // ordenamos descendentemente por longitud
                .Select( grupo => grupo.Distinct()) // eliminamos repetidos
                .First() // nos quedamos con los de mayor logitud (el primer grupo)
                .ToArray(); // convertimos a array
        }

        public static string[] PalabrasMasCortas(string[] palabras) {
            return palabras
                .GroupBy(palabra => palabra.Length) // emparejamos por longitud
                .OrderBy(grupo => grupo.Key) // ordenamos ascendentemente por longitud
                .Select(grupo => grupo.Distinct()) // eliminamos repetidos
                .First() // nos quedamos con las de menor longitud (el primer grupo)
                .ToArray(); // convertimos a array

        }

        public static string[] PalabrasConMenosApariciones(string[] palabras, out int apariciones) {
            var palabrasYApariciones = palabras
                .GroupBy(palabra => palabra.ToLower()) // emparejamos por palabra en minúsculas
                .Select(grupo => new { Palabra = grupo.Key, Apariciones = grupo.Count()}) // nos quedamos con una lista de pares {palabra, apariciones}
                .OrderBy( par => par.Apariciones); // ordenamos los pares ascendentemente por las aparariciones
            // Nos quedamos con el menor número de apariciones en el texto (el primer elemento al estar ordenadas por apariciones)
            int numeroMenorApariciones = apariciones = palabrasYApariciones.First().Apariciones;
            return palabrasYApariciones
                .Where(par => par.Apariciones==numeroMenorApariciones) // Filtramos por el número menor de apariciones
                .Select(par => par.Palabra)  // Nos quedamos con la palabra en minúscula
                .ToArray(); // Lo devolvemos como un array
        }

        public static string[] PalabrasConMasApariciones(string[] palabras, out int apariciones) {
            var palabrasYApariciones = palabras
                .GroupBy(palabra => palabra.ToLower()) // emparejamos por palabra en minúsculas
                .Select(grupo => new { Palabra = grupo.Key, Apariciones = grupo.Count() }) // nos quedamos con una lista de pares {palabra, apariciones}
                .OrderByDescending(par => par.Apariciones); // ordenamos los pares descendentemente por las aparariciones
            // Nos quedamos con el mayor número de apariciones en el texto (el primer elemento al estar ordenadas por apariciones)
            int numeroMayorApariciones = apariciones = palabrasYApariciones.First().Apariciones;
            return palabrasYApariciones
                .Where(par => par.Apariciones == numeroMayorApariciones) // Filtramos por el número menor de apariciones
                .Select(par => par.Palabra)  // Nos quedamos con la palabra en minúscula
                .ToArray(); // Lo devolvemos como un array
        }


    }

}
