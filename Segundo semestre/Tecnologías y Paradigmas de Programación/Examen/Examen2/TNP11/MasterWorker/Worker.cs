using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MasterWorker
{
    class Worker
    {
        public Dictionary<String, int> diccionario;
        private string[] palabras;

        public Worker(){
            diccionario = new Dictionary<string,int>();
        }

        public string[] PartirEnPalabras(String texto)
        {
            return texto.Split(new char[] { ' ', '\r','\n', ',', '.', ';', ':', '-', '!', '¡', '¿', '?', '/', '«', 
                                            '»', '_', '(', ')', '\"', '*', '\'', 'º', '[', ']', '#' },
                StringSplitOptions.RemoveEmptyEntries);
        }

        public void CalcularPalabras()
        {
            String linea;
            while (Master.linea.Extraer(out linea))
            {
                Console.WriteLine("Trabajando");
                palabras = PartirEnPalabras(linea);
                for (int i = 0; i < palabras.Length; i++)
                {
                    int valor;
                    if (diccionario.TryGetValue(palabras[i], out valor))
                    {
                        diccionario[palabras[i]] = valor + 1;
                    }
                    else
                    {
                        diccionario.Add(palabras[i], 1);
                    }
                }
            }
        }

    }
}
