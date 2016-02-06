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
        public int GetCuadrante()
        {
            if (GetGrados() < 90 && GetGrados() >= 0)
            {
                return 1;
            }
            else if (GetGrados() >= 90 && GetGrados() < 180)
            {
                return 2;
            }
            else if (GetGrados() >= 180 && GetGrados() < 270)
            {
                return 3;
            }
            return 4;
                
        }
        public override bool Equals(object obj)
        {
            if (obj is Angulo)
            {
                return (obj as Angulo).GetRadianes() == this.GetRadianes();
            }
            else
            {
                return false;
            }
        }
        public override string ToString()
        {
            return GetRadianes()+"";
        }
    }
}