using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tarea5.Vector
{
    public class Vector
    {

        public int Tamaño { get; set; }
        private int[] vector;

        public Vector()
        {
            vector = new int[1];
        }

        public void Insertar(int primerValor)
        {
            if (Tamaño + 1 > vector.Length)
            {
                Array.Resize(ref vector, Tamaño + 1);
            }
            vector[Tamaño] = primerValor;
            Tamaño += 1;
        }

        public object GetElemento(int p)
        {
            if (p < Tamaño)
            {
                return vector[p];
            }
            else
            {
                throw new ArgumentException();
            }
        }

        public void SetElemento(int p, int primerValor)
        {
            if (p < Tamaño)
            {
                vector[p] = primerValor;
            }
            else
            {
                throw new ArgumentException();
            }
        }
    }
}
