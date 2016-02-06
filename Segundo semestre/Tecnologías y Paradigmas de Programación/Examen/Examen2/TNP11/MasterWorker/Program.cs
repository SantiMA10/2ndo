using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MasterWorker
{
    class Program
    {
        static void Main(string[] args)
        {
            String texto = LeerFicheroTexto(@"..\..\..\clarin.txt");
            Master master = new Master(texto);
        }

        public static String LeerFicheroTexto(string nombreFichero)
        {
            using (StreamReader stream = File.OpenText(nombreFichero))
            {
                StringBuilder texto = new StringBuilder();
                string linea;
                while ((linea = stream.ReadLine()) != null)
                {
                    texto.AppendLine(linea);
                }
                return texto.ToString();
            }
        }

        
    }
}
