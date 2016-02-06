using System;
using System.Collections.Generic;
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

            String palabraBuscar = "visitar";

            ContarPalabraTPL(lineas, palabraBuscar);

            IEnumerable<Dictionary<String, int>> paso1 = lineas.AsParallel().Select((linea) => { 
                Dictionary<String, int> paso = PartirEnPalabras(linea).AsParallel().Aggregate<String, Dictionary<String, int>>(new Dictionary<string,int>(), 
                    (semilla, palabra) => {
                        int valor;
                        if(semilla.TryGetValue(palabra, out valor)){
                            semilla[palabra] = valor + 1;
                        }
                        else{
                            semilla[palabra] = 1;
                        }
                        return semilla;
                    });
                return paso;
            });

            Dictionary<String, int> resultado = paso1.Aggregate<Dictionary<String, int>, Dictionary<String, int>>(new Dictionary<string,int>(),
                (semilla, diccionario) =>
                {
                    foreach(KeyValuePair<String, int> par in diccionario){
                        int valor;
                        if(semilla.TryGetValue(par.Key, out valor)){
                            semilla[par.Key] = valor + par.Value;
                        }
                        else{
                            semilla[par.Key] = par.Value;
                        }
                        return semilla;
                    }
                    return semilla;
                });
            int mal = 0, bien = 0;
            foreach (KeyValuePair<String, int> par in resultado) {
                if (ContarPalabraTPL(lineas, par.Key) == par.Value)
                    bien++;
                else
                    mal++;
                Console.WriteLine("Bien: {0}, Mal: {1}", bien, mal);

            }

            Console.WriteLine("Fin\nBien: {0}, Mal: {1}", bien, mal);

        }

        public static int ContarPalabraTPL(String[] lineas, String palabraBuscar)
        {
            int nVeces = 0;
            Stopwatch crono = new Stopwatch();
            crono.Start();
            Parallel.ForEach(lineas, (linea) =>
            {
                String[] palabras = PartirEnPalabras(linea);
                Parallel.ForEach(palabras, (palabra) =>
                {
                    if (palabra.Equals(palabraBuscar))
                    {
                        Interlocked.Increment(ref nVeces);
                    }
                });
            });
            crono.Stop();

            //Console.WriteLine("'{0}' encontrada {1} veces en {2} milis", palabraBuscar, nVeces, crono.ElapsedMilliseconds);

            return nVeces;
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
