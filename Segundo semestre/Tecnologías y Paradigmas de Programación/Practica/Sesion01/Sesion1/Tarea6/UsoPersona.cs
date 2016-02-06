using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.Practicas.OrientacionObjetos.Practica1
{
    class UsoPersona
    {
        void Main()
        {
            Persona pepe = new Persona
            {
                Nombre = "José",
                Apellido1 = "Pérez",
                Apellido2 = "Vázquez",
                // La edad no la escribimos
                Nacionalidad = "Española",
                DNI = "9345765-C",
                FechaNacimiento = new DateTime(1980, 3, 13),
                Sexo = Género.Masculino,
            };
            Persona mary = new Persona
            {
                Nombre = "Mary",
                Apellido1 = "Williams",
                // No tiene segundo apellido
                // La edad no la escribimos
                Nacionalidad = "UK",
                // No tiene DNI
                // No sabemos la fecha de nacimiento
                Sexo = Género.Femenino,
            };
        }
    }
}
