using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TPP.Practicas.OrientacionObjetos.Practica3;


namespace Sesion3
{
    class ComparacionPorNombre: IPredicadoIgualdad
    {
        public bool Comparacion(Object o1 , Object o2)
        {
            if (o1 is Persona && o2 is Persona)
            {
                return (o1 as Persona).Nombre.Equals((o2 as Persona).Nombre);
            }

            return false;
        }
    }
}
