using System;

namespace TPP.Practicas.OrientacionObjetos.Practica1 {
    enum Género {
            Masculino, Femenino
    }

	struct Persona {

		public string Nombre { get; set; }
		public string Apellido1 { get; set; }
        public string Apellido2 { get; set; }

		public int Edad {
			get { return DateTime.Now.Year - AñoNacimiento; }
			set { AñoNacimiento = DateTime.Now.Year - value; }
		}
		public string Nacionalidad { get; set; }
		public string DNI { get; set; }
		public int AñoNacimiento { get; set; }
        public DateTime FechaNacimiento { get; set {  } }
        public Género Sexo;

        public bool Equals(Object o) 
        {
            int hashPropio = GetHashCode(this);
            int hashParametro = GetHashCode(o);

            return hashPropio == hashParametro;
        }

        public int GetHashCode(Object o)
        {
            return Convert.ToInt32(DNI.Split('-')[0]);
        }



		public override string ToString() {
			return Nombre;
		}
	}
}
