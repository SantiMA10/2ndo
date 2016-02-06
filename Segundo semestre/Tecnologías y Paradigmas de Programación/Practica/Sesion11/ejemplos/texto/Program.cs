using System;
using System.IO;
using System.Threading;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using System.Diagnostics;
using System.Threading.Tasks;


namespace TPP.Practicas.Concurrente.Practica3 {

    /// <summary>
    /// Ejemplo de programa secuencial que procesa un fichero con varias tareas en paralelo.
    /// Se paralelizará con TPL para ver la mejora en los tiempos de ejecución.
    /// </summary>
    class Program {


        static void Main(string[] args) {
            String texto = Procesamiento.LeerFicheroTexto(@"..\..\..\clarin.txt");
            string[] palabras = Procesamiento.PartirEnPalabras(texto);
            float tMono, tInvoke;
            
            Stopwatch crono = new Stopwatch();
            crono.Start();
            int signosDePuntuacion = Procesamiento.SignosPuntuación(texto);
            var palabrasMasLargas = Procesamiento.PalabrasMasLargas(palabras);
            var palabrasMasCortas = Procesamiento.PalabrasMasCortas(palabras);
            int numeroMayorAparciones, numeroMenorApariciones;
            var palabrasConMasApariciones = Procesamiento.PalabrasConMasApariciones(palabras, out numeroMayorAparciones);
            var palabrasConMenosApariciones = Procesamiento.PalabrasConMenosApariciones(palabras, out numeroMenorApariciones);
            crono.Stop();

            MostrarResultados(signosDePuntuacion, palabrasMasCortas, palabrasMasLargas, palabrasConMenosApariciones, numeroMenorApariciones, 
                palabrasConMasApariciones, numeroMayorAparciones);

            tMono = crono.ElapsedMilliseconds;
            Console.WriteLine("\nProcesamiento ejecutado en {0:N} milisegundos.\n", tMono);

            crono.Reset();
            crono.Start();
            Parallel.Invoke(() => { signosDePuntuacion = Procesamiento.SignosPuntuación(texto); },
               () => { palabrasMasLargas = Procesamiento.PalabrasMasLargas(palabras); },
               () => { palabrasMasCortas = Procesamiento.PalabrasMasCortas(palabras); },
               () => { palabrasConMasApariciones = Procesamiento.PalabrasConMasApariciones(palabras, out numeroMayorAparciones); },
               () => { palabrasConMenosApariciones = Procesamiento.PalabrasConMenosApariciones(palabras, out numeroMenorApariciones); });
            crono.Stop();

            MostrarResultados(signosDePuntuacion, palabrasMasCortas, palabrasMasLargas, palabrasConMenosApariciones, numeroMenorApariciones,
                palabrasConMasApariciones, numeroMayorAparciones);

            tInvoke = crono.ElapsedMilliseconds;
            Console.WriteLine("\nProcesamiento(invoke) ejecutado en {0:N} milisegundos.\n", tInvoke);

            float beneficio = 100 - ((tInvoke / tMono) * 100);
            Console.WriteLine("Beneficio de rendimiento Mono vs Invoke {0:N} %\n", beneficio);

        }


        public static void MostrarResultados(int signosDePuntuación, string[] palabrasMasCortas, string[] palabrasMasLargas, string[] palabrasConMenosApariciones,
                                              int numeroMenorApariciones, string[] palabrasConMasApariciones, int numeroMayorApariciones) {
            const int numeroMaximoElementosAMostrar = 20;

            Console.WriteLine("> Aparecieron {0} signos de puntuación. ", signosDePuntuación);

            Console.Write("> {0} palabras más cortas: ", palabrasMasCortas.Count());
            Mostrar(palabrasMasCortas, Console.Out, numeroMaximoElementosAMostrar);
            Console.WriteLine();

            Console.Write("> {0} palabras más largas: ", palabrasMasLargas.Count());
            Mostrar(palabrasMasLargas, Console.Out, numeroMaximoElementosAMostrar);
            Console.WriteLine();

            Console.Write("> {0} palabras con un menor número de apariciones ({1}): ", palabrasConMenosApariciones.Count(), numeroMenorApariciones);
            Mostrar(palabrasConMenosApariciones, Console.Out, numeroMaximoElementosAMostrar);
            Console.WriteLine();

            Console.Write("> {0} palabras con un mayor número de apariciones ({1}): ", palabrasConMasApariciones.Count(), numeroMayorApariciones);
            Mostrar(palabrasConMasApariciones, Console.Out, numeroMaximoElementosAMostrar);
            Console.WriteLine();
        }

        private static void Mostrar<T>(IEnumerable<T> elementos, TextWriter flujo, int numeroMáximoElementos) {
            int i = 0;
            foreach (T elemento in elementos) {
                flujo.Write(elemento);
                i = i + 1;
                if (i == numeroMáximoElementos) {
                    flujo.Write("...");
                    break;
                }
                if (i < elementos.Count())
                    flujo.Write(", ");
            }
        }




    }

}
