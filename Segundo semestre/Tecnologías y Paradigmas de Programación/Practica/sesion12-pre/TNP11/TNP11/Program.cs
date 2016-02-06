using System;
using System.Collections.Generic;
using System.Collections.Concurrent;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Diagnostics;


namespace TNP11
{
    class Program
    {
        static void Main(string[] args)
        {
            String texto = LeerFicheroTexto(@"..\..\..\clarin.txt");
            string[] lineas = PartirEnLineas(texto);
            Console.WriteLine("\nTiempos dividido por lineas:");

            Stopwatch crono = new Stopwatch();
            crono.Start();
            Dictionary<String, int> rPLinQ = SolPLinQ(lineas, false);
            crono.Stop();
            Console.WriteLine("Tiempo PLinQ:\t{0}ms", crono.ElapsedMilliseconds);
            crono.Reset();
            GC.Collect();
            GC.WaitForFullGCComplete();
            crono.Start();
            IDictionary<String, int> rTPL = SolTPL(lineas, false);
            crono.Stop();
            Console.WriteLine("Tiempo TPL:\t{0}ms", crono.ElapsedMilliseconds);
            crono.Reset();
            GC.Collect();
            GC.WaitForFullGCComplete();
            crono.Start();
            Dictionary<String, int> rMono = SolMonohilo(lineas, false);
            crono.Stop();
            Console.WriteLine("Tiempo mono:\t{0}ms", crono.ElapsedMilliseconds);

            int bien = 0, mal = 0;

            foreach (KeyValuePair<String, int> par in rPLinQ)
            {
                if (rTPL[par.Key] != par.Value || rMono[par.Key] != par.Value)
                {
                    mal++;
                    Console.WriteLine("MAL {0}", par.Key);
                }
                else
                {
                    bien++;
                }
            }
            Console.WriteLine("\nComparacion de resultados:");
            Console.WriteLine("Iguales:\t{0} de {1}\nDistintas:\t{2} de {3}", bien, rTPL.Count, mal, rTPL.Count);

            Console.WriteLine("\nTiempos dividido por palabras:");
            String[] palabras = PartirEnPalabras(texto);
            crono.Reset();
            crono.Start();
            rMono = SolMonoPalabras(palabras);
            crono.Stop();
            Console.WriteLine("Tiempo Mono: {0}ms", crono.ElapsedMilliseconds);
            crono.Reset();
            crono.Start();
            rTPL = SolTPLPalabras(palabras);
            crono.Stop();
            Console.WriteLine("Tiempo TPL: {0}ms", crono.ElapsedMilliseconds);

            bien = 0;
            mal = 0;

            foreach (KeyValuePair<String, int> par in rPLinQ)
            {
                if (rTPL[par.Key] != par.Value || rMono[par.Key] != par.Value)
                {
                    mal++;
                    Console.WriteLine("MAL {0}", par.Key);
                }
                else
                {
                    bien++;
                }
            }
            Console.WriteLine("\nComparacion de resultados:");
            Console.WriteLine("Iguales:\t{0} de {1}\nDistintas:\t{2} de {3}", bien, rTPL.Count, mal, rTPL.Count);

        }

        private static Dictionary<String, int> SolMonoPalabras(String[] palabras)
        {
            Dictionary<String, int> resultado2 = new Dictionary<string, int>();
            foreach (String palabra in palabras)
            {
                int valor;
                if (resultado2.TryGetValue(palabra, out valor))
                {
                    resultado2[palabra] = valor + 1;
                }
                else
                {
                    resultado2.Add(palabra, 1);
                }
            }
            return resultado2;
        }

        private static IDictionary<String,int> SolTPLPalabras(String[] palabras)
        {
            ConcurrentDictionary<String, int> resultado = new ConcurrentDictionary<string, int>();
            Parallel.ForEach(palabras, (palabra) =>
            {
                resultado.AddOrUpdate(palabra, 1, (actual, valor) => valor + 1);
            });
            return resultado;
        }

        private static Dictionary<String, int> SolMonohilo(string[] lineas, bool guardar)
        {
            Dictionary<String, int> resultado = new Dictionary<string, int>();
            foreach (String linea in lineas)
            {
                String[] palabras = PartirEnPalabras(linea);
                foreach (String palabra in palabras)
                {
                    int valor;
                    if (resultado.TryGetValue(palabra, out valor))
                    {
                        resultado[palabra] = valor + 1;
                    }
                    else
                    {
                        resultado.Add(palabra, 1);
                    }
                }
            }
            if(guardar) guardarResultado("monohilo", resultado);

            return resultado;
        }

        private static IDictionary<String, int> SolTPL(string[] lineas, bool guardar)
        {
            ConcurrentDictionary<String, int> resultado = new ConcurrentDictionary<string, int>();
            Parallel.ForEach(lineas, (linea) =>
            {
                string[] palabras = PartirEnPalabras(linea);
                Parallel.ForEach(palabras, (palabra) =>
                {
                   resultado.AddOrUpdate(palabra, 1, (actual, valor) => valor + 1);
                });
            });

            if (guardar) guardarResultado("TPL", resultado);

            return resultado;
        }

        private static Dictionary<String, int> SolPLinQ(string[] lineas, bool guardar)
        {
            IEnumerable<Dictionary<String, int>> paso1 = lineas.AsParallel().Select((linea) =>
            {
                Dictionary<String, int> paso = PartirEnPalabras(linea).AsParallel().Aggregate<String, Dictionary<String, int>>(new Dictionary<string, int>(),
                    (semilla, palabra) =>
                    {
                        int valor;
                        if (semilla.TryGetValue(palabra, out valor))
                        {
                            semilla[palabra] = valor + 1;
                        }
                        else
                        {
                            semilla[palabra] = 1;
                        }
                        return semilla;
                    });
                return paso;
            });

            Dictionary<String, int> resultado = paso1.Aggregate<Dictionary<String, int>, Dictionary<String, int>>(new Dictionary<string, int>(),
                (semilla, diccionario) =>
                {
                    foreach (KeyValuePair<String, int> par in diccionario)
                    {
                        int valor;
                        if (semilla.TryGetValue(par.Key, out valor))
                        {
                            semilla[par.Key] = valor + par.Value;
                        }
                        else
                        {
                            semilla[par.Key] = par.Value;
                        }
                    }
                    return semilla;
                });

            if (guardar) guardarResultado("LinQ", resultado);

            return resultado;
        }

        public static void guardarResultado(String nombreFichero, IDictionary<String, int> resultado)
        {
            String fichero = "";
            foreach (KeyValuePair<String, int> par in resultado)
            {
                fichero += par + "\n";
            }
            using (StreamWriter stream = new StreamWriter(@"..\..\..\"+nombreFichero+".txt"))
            {
                stream.WriteLine(fichero);
            }
        }

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

        public static string[] PartirEnLineas(String texto) {
            return texto.Split(new char[] {'\r'},
                StringSplitOptions.RemoveEmptyEntries);
        }

        public static string[] PartirEnPalabras(String texto)
        {
            return texto.Split(new char[] { ' ', '\r','\n', ',', '.', ';', ':', '-', '!', '¡', '¿', '?', '/', '«', 
                                            '»', '_', '(', ')', '\"', '*', '\'', 'º', '[', ']', '#' },
                StringSplitOptions.RemoveEmptyEntries);
        }

    }
}
