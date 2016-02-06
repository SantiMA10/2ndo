using System;
namespace TPP.Practicas.OrientacionObjetos.Practica3
{
    public class Angulo
    {
        private double radianes;
        public double GetRadianes()
        {
            return this.radianes;
        }
        public double GetGrados()
        {
            return this.radianes / Math.PI * 180;
        }
        public Angulo(double radianes)
        {
            this.radianes = radianes;
        }
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

        ///Falta ... public override string ToString() {
    }
}