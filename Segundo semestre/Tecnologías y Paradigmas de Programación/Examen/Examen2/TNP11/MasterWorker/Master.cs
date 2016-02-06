using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using TNP10.Biblioteca;

namespace MasterWorker
{
    class Master
    {
        public static ColaConcurrente<String> linea;

        public Master(string texto)
        {
            string[] lineass = PartirEnLineas(texto);
            Thread[] hilos = new Thread[5];
            Worker[] trabajadores = new Worker[5];

            linea = new ColaConcurrente<string>();

            Console.WriteLine("Creando trabajadores...");

            for (int i = 0; i < lineass.Length; i++)
            {
                linea.Añadir(lineass[i]);
            }

            for (int i = 0; i < 5; i++)
            {
                trabajadores[i] = new Worker();
                hilos[i] = new Thread(trabajadores[i].CalcularPalabras);
                hilos[i].Start();
                hilos[i].Join();
            }

            Dictionary<String, int> diccionario = new Dictionary<string, int>();

            Console.WriteLine("Juntando resultados...");

            foreach (Worker trabajador in trabajadores)
            {
                foreach (KeyValuePair<String, int> par in trabajador.diccionario)
                {
                    int valor;
                    if (diccionario.TryGetValue(par.Key, out valor))
                    {
                        diccionario[par.Key] = valor + par.Value;
                    }
                    else
                    {
                        diccionario.Add(par.Key, par.Value);
                    }
                }

            }

            foreach (KeyValuePair<String, int> par in diccionario)
            {
                Console.WriteLine(par);
            }
            
        }

        public static string[] PartirEnLineas(String texto)
        {
            return texto.Split(new char[] { '\r' },
                StringSplitOptions.RemoveEmptyEntries);
        }

  
    }
}
