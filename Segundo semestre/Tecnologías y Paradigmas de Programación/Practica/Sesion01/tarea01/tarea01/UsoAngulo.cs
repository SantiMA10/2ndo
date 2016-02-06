using System;

namespace TPP.Practicas.OrientacionObjetos.Practica1
{
    class UsoAngulo
    {
        static void Main()
        {
            Angulo angulo = new Angulo(Math.PI);
            Angulo angulo = new Angulo(Math.PI);
            Console.WriteLine("Angulo (radianes): {0,10:F6}", angulo.GetRadianes());
            Console.WriteLine("Angulo (grados):   {0,10:F2}", angulo.GetGrados());
            Console.WriteLine("Seno:              {0,10:F}", angulo.Seno());
            Console.WriteLine("Coseno:            {0,10:F}", angulo.Coseno());
            Console.WriteLine("Tangente:          {0,10:F}", angulo.Tangente());
        }
    }
}