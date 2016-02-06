using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TPP.Practicas.OrientacionObjetos.Practica3;


namespace Sesion3
{
    class ComparacionPorCuadrante: IPredicadoIgualdad
    {
        public bool Comparacion(Object o1, Object o2)
        {
            if (o1 is Angulo && o2 is Angulo)
            {
                return (o1 as Angulo).GetCuadrante().Equals((o2 as Angulo).GetCuadrante());
            }

            return false;
        }
    }
}
