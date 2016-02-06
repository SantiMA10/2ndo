using System;
namespace TPP.Practicas.OrientacionObjetos.Practica1
{
    public class Angulo
    {
        /// <summary>
        ///  Es la propiedad de almacenamiento de radianes
        /// </summary>
        public double radianes
        {
            get;
            set;

        }

        /// <summary>
        ///  Es la propiedad de almacenamiento de grados
        /// </summary>
        public double grados
        {
            get
            {
                return radianes * (180 / Math.PI);
            }
            set
            {
                this.radianes = value * (Math.PI / 180);
            }
        }

        ~Angulo()
        {
            Console.WriteLine("Ejecutando desconstructor");
        }

        public Angulo() { }

        public double Seno()
        {
            return Math.Sin(radianes);
        }
        
        public double Coseno()
        {
            return Math.Cos(radianes);
        }
        
        public double Tangente()
        {
            return Seno() / Coseno();
        }
    }
}