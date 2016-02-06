using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace TPP.Practicas.Concurrente.Practica2 {

    class Program {

        private static void Transferir(Cuenta cuentaA, Cuenta cuentaB, decimal importe) {
            Console.WriteLine("Transfiriendo {0:N}€ de cuenta {1} a cuenta {2}...",
                importe, cuentaA.NumeroCuenta, cuentaB.NumeroCuenta);
            if (cuentaA.Transferir(cuentaB, importe))
                Console.WriteLine("\tResultado satisfactorio, hilo {0}.", Thread.CurrentThread.Name);
            else
                Console.WriteLine("\tError en la transacción, hilo {0}.", Thread.CurrentThread.Name);
        }

        public static void Main() {
            decimal importeInicial = 30000;
            Cuenta cuentaA = new Cuenta("A", importeInicial), cuentaB = new Cuenta("B", importeInicial);

            Random random = new Random();
            int iteraciones = 1000;
            Thread[] hilos = new Thread[iteraciones];
            for (int i = 0; i < iteraciones; i++) {
                decimal importe = (decimal)random.NextDouble() * random.Next(1, 600);
                if (i % 2 == 0)
                    hilos[i] = new Thread(() => Transferir(cuentaA, cuentaB, importe));
                else
                    hilos[i] = new Thread(() => Transferir(cuentaB, cuentaA, importe));
                hilos[i].Name = "Transferencia número " + i;
            }

            foreach (Thread hilo in hilos)
                hilo.Start();

        }

    }
}
