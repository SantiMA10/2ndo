using System;
using System.IO;

namespace TPP.Practicas.Concurrente.Practica1 {

    class Program {

        static void Main(string[] args) {
            const int numeroMaximoHilos = 50;
            char[] ADN = ProgramModuloVector.CrearVectorAleatorio(1000000);
            char[] gen = ProgramModuloVector.CrearVectorAleatorio(10);
            MostrarLinea(Console.Out, "Numero de Hilos", "Ticks", "Resultado");
            using (System.IO.StreamWriter file = new System.IO.StreamWriter("./datos.csv"))
            {
                for (int numeroHilos = 1; numeroHilos <= numeroMaximoHilos; numeroHilos++)
                {
                    Master master = new Master(ADN, gen, numeroHilos);
                    DateTime antes = DateTime.Now;
                    double resultado = master.CalcularNumeroGenes();
                    DateTime despues = DateTime.Now;
                    MostrarLinea(Console.Out, numeroHilos, (despues - antes).Ticks, resultado);
                    MostrarLinea(file, numeroHilos, (despues - antes).Ticks, resultado);
                    GC.Collect();
                    GC.WaitForFullGCComplete();
                }
            }
        }

        private const string SEPARADOR_CSV = ";";

        static void MostrarLinea(TextWriter flujo, string tituloNumeroHilos, string tituloTicks, string tituloResultado) {
            flujo.WriteLine("{0}{3}{1}{3}{2}{3}", tituloNumeroHilos, tituloTicks, tituloResultado, SEPARADOR_CSV);
        }

        static void MostrarLinea(TextWriter flujo, int numeroHilos, long ticks, double resultado) {
            flujo.WriteLine("{0}{3}{1:N0}{3}{2:N2}{3}", numeroHilos, ticks, resultado, SEPARADOR_CSV);
        }
    }

}
