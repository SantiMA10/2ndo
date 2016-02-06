using System;

namespace TPP.Practicas.Concurrente.Practica1 {
    
    public class ProgramModuloVector {

        static void Main(string[] args) {
            short[] vector = CrearVectorAleatorio(100000, -10, 10);

            Master master = new Master(vector, 1);
            DateTime antes = DateTime.Now;
            double resultado = master.CalcularModulo();
            DateTime despues = DateTime.Now;
            Console.WriteLine("Resultado del cálculo con un hilo: {0:N2}.", resultado);
            Console.WriteLine("Tiempo transcurrido: {0:N0} ticks de reloj.",
                (despues - antes).Ticks );

            master = new Master(vector, 2);
            antes = DateTime.Now;
            resultado = master.CalcularModulo();
            despues = DateTime.Now;
            Console.WriteLine("Resultado del cálculo con cuatro hilos: {0:N2}.", resultado);
            Console.WriteLine("Tiempo transcurrido: {0:N0} ticks de reloj.",
                (despues - antes).Ticks);
        }

        public static short[] CrearVectorAleatorio(int numeroElementos, short menor, short mayor) {
            short[] vector = new short[numeroElementos];
            Random random = new Random();
            for (int i = 0; i < numeroElementos; i++)
                vector[i] = (short)random.Next(menor, mayor + 1);
            return vector;
        }

    }

}