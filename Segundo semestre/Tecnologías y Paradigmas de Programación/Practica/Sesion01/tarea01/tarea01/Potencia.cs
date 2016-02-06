
using System;

namespace TPP.Practicas.OrientacionObjetos.Practica1 {

    /// <summary>
    /// Clase que calcula en su Main la potencia de un número
    /// </summary>
    class Potencia {

        static void Main() {
            uint laBase = 2;
            uint exponente = 32;

            uint resultado = 1;

            if (laBase == 0) {
                Console.WriteLine("Potencia: 0.");
                return;
            }

            while (exponente > 0) {
                resultado *= laBase;
                exponente--;
            }

            Console.WriteLine("Potencia: {0}.", resultado);
        }
    }

}