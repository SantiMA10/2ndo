using System;

namespace TPP.Practicas.OrientacionObjetos.Practica1
{
    class UsoAngulo
    {

        static void Main()
        {
            Angulo angulo = new Angulo();
            angulo.radianes = Math.PI;
            Console.WriteLine("Angulo (radianes): {0,10:F6}", angulo.radianes);
            Console.WriteLine("Angulo (grados):   {0,10:F2}", angulo.grados);
            Console.WriteLine("Seno:              {0,10:F}", angulo.Seno());
            Console.WriteLine("Coseno:            {0,10:F}", angulo.Coseno());
            Console.WriteLine("Tangente:          {0,10:F}", angulo.Tangente());
        }
    }
}