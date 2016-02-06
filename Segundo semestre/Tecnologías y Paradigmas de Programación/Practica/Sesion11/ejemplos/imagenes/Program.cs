using System;
using System.IO;
using System.Drawing;
using System.Threading;
using System.Diagnostics;
using System.Threading.Tasks;


namespace TPP.Practicas.Concurrente.Practica3 {

    class Program {

        static void Main() {
            Stopwatch crono = new Stopwatch();
            float tMono, tForEach, tFor;
            crono.Start();
            string[] ficheros = Directory.GetFiles(@"..\..\..\pics", "*.jpg");
            string nuevoDirectorio = @"..\..\..\pics\rotadas";
            Directory.CreateDirectory(nuevoDirectorio);
            crono.Start();
            VersionMonoHilo(ficheros, nuevoDirectorio);
            crono.Stop();
            tMono = crono.ElapsedMilliseconds;
            Console.WriteLine("Ejecutado monohilo en {0:N} milisegundos.\n", tMono);
            crono.Reset();
            crono.Start();
            VersionForEach(ficheros, nuevoDirectorio);
            crono.Stop();
            tForEach = crono.ElapsedMilliseconds;
            Console.WriteLine("Ejecutado ForEach en {0:N} milisegundos.\n", tForEach);
            crono.Reset();
            crono.Start();
            VersionFor(ficheros, nuevoDirectorio);
            crono.Stop();
            tFor = crono.ElapsedMilliseconds;
            Console.WriteLine("Ejecutado For en {0:N} milisegundos.\n", tFor);

            float beneficio = 100 - ((tForEach / tMono) * 100);
            Console.WriteLine("Beneficio de rendimiento Mono vs ForEach {0:N} %", beneficio);

            beneficio = 100 - ((tFor / tMono) * 100);
            Console.WriteLine("Beneficio de rendimiento Mono vs For {0:N} %\n", beneficio);

        }

        static void VersionMonoHilo(String[] ficheros, String directorio)
        {
            foreach (string fichero in ficheros)
            {
                string nombreFichero = Path.GetFileName(fichero);
                using (Bitmap bitmap = new Bitmap(fichero))
                {
                    Console.WriteLine("Procesando el fichero {0}", nombreFichero);
                    bitmap.RotateFlip(RotateFlipType.Rotate180FlipNone);
                    bitmap.Save(Path.Combine(directorio, nombreFichero));
                }
            }
        }

        static void VersionForEach(String[] ficheros, String directorio)
        {
            Parallel.ForEach(ficheros, (fichero) =>
            {
                string nombreFichero = Path.GetFileName(fichero);
                using(Bitmap imagen = new Bitmap(fichero)){
                    Console.WriteLine("Procesando imagen {0} por hilo {1}", nombreFichero, Thread.CurrentThread.ManagedThreadId);
                    imagen.RotateFlip(RotateFlipType.Rotate180FlipNone);
                    imagen.Save(Path.Combine(directorio, nombreFichero));
                }
            });
        }

        static void VersionFor(String[] ficheros, String directorio)
        {
            Parallel.For(0, ficheros.Length, (i) =>
            {
                string nombreFichero = Path.GetFileName(ficheros[i]);
                using (Bitmap imagen = new Bitmap(ficheros[i]))
                {
                    Console.WriteLine("Procesando imagen {0} por hilo {1}", nombreFichero, Thread.CurrentThread.ManagedThreadId);
                    imagen.RotateFlip(RotateFlipType.Rotate180FlipNone);
                    imagen.Save(Path.Combine(directorio, nombreFichero));
                }
            });
        }

    }

}
