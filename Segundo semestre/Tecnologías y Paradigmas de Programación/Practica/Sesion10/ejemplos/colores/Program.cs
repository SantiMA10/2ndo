using System;
using System.Threading;

namespace TPP.Practicas.Concurrente.Practica2 {

    public class Program {

        public static readonly ConsoleColor[] colores = { 
                    ConsoleColor.DarkBlue,  ConsoleColor.DarkGreen,  ConsoleColor.DarkCyan, 
	                ConsoleColor.DarkRed, ConsoleColor.DarkMagenta,  ConsoleColor.DarkYellow,  
	                ConsoleColor.DarkGray,  ConsoleColor.Blue,  ConsoleColor.Green,  
                    ConsoleColor.Cyan,  ConsoleColor.Red, ConsoleColor.Gray, 
	                ConsoleColor.Magenta,  ConsoleColor.Yellow, ConsoleColor.White,
                    ConsoleColor.DarkBlue,  ConsoleColor.DarkGreen,  ConsoleColor.DarkCyan, 
	                ConsoleColor.DarkRed, ConsoleColor.DarkMagenta,  ConsoleColor.DarkYellow,  
	                ConsoleColor.DarkGray,  ConsoleColor.Blue,  ConsoleColor.Green,  
                    ConsoleColor.Cyan,  ConsoleColor.Red, ConsoleColor.Gray, 
	                ConsoleColor.Magenta,  ConsoleColor.Yellow, ConsoleColor.White,
                    };

        static void Main() {
            Thread[] hilos = new Thread[colores.Length];
            for (int i = 0; i < colores.Length; i++) {
                Color color = new Color(colores[i]);
                hilos[i] = new Thread(color.Mostrar);
            }
            foreach (Thread hilo in hilos)
                hilo.Start();
        }

    }
}
