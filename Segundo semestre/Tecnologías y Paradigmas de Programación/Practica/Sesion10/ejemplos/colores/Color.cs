using System;

namespace TPP.Practicas.Concurrente.Practica2 {

    public class Color {

        static Object misemaforo = new Object();
        private ConsoleColor color;

        public Color(ConsoleColor color) {
            this.color = color;
        }

        virtual public void Mostrar(){
            ConsoleColor colorAnterior;

            lock (misemaforo) 
            {

                colorAnterior = Console.ForegroundColor;
                Console.ForegroundColor = this.color;
                Console.Write("{0}\t", this.color);
                Console.ForegroundColor = colorAnterior;

            }

        }

    }
}
