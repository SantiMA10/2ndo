using System;

namespace TPP.Practicas.Concurrente.Practica1 {
    
    public class ProgramModuloVector {

        static void Main(string[] args) {
            char[] ADN = CrearVectorAleatorio(1000000);
            char[] gen = CrearVectorAleatorio(10);

            Master master = new Master(ADN, gen, 1);
            DateTime antes = DateTime.Now;
            double resultado = master.CalcularNumeroGenes();
            DateTime despues = DateTime.Now;
            Console.WriteLine("Resultado del cálculo con un hilo: {0:N2}.", resultado);
            Console.WriteLine("Tiempo transcurrido: {0:N0} ticks de reloj.",
                (despues - antes).Ticks );

            master = new Master(ADN, gen, 4);
            antes = DateTime.Now;
            resultado = master.CalcularNumeroGenes();
            despues = DateTime.Now;
            Console.WriteLine("Resultado del cálculo con cuatro hilos: {0:N2}.", resultado);
            Console.WriteLine("Tiempo transcurrido: {0:N0} ticks de reloj.",
                (despues - antes).Ticks);
        }

        public static char[] CrearVectorAleatorio(int numeroElementos) {
            char[] vector = new char[numeroElementos];
            Random random = new Random();
            for (int i = 0; i < numeroElementos; i++)
            {
                int selector = random.Next(0, 4);
                if (selector == 0)
                {
                    vector[i] = 'A';
                }
                else if (selector == 1)
                {
                    vector[i] = 'C';
                }
                else if (selector == 1)
                {
                    vector[i] = 'T';
                }
                else
                {
                    vector[i] = 'G';
                }
            }
                
            return vector;
        }

    }

}