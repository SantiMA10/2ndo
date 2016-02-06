using System;

namespace TPP.Practicas.OrientacionObjetos.Practica3 {
	class Persona {
		public Persona(string nombre = null, string apellido1 = null, string apellido2 = null, string dni = null ) {
			this.Nombre = nombre;
			this.DNI = dni;
            this.Apellido1 = apellido1;
            this.Apellido2 = apellido2;
		}

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

		public override string ToString() {
			return Nombre;
		}

        public override bool Equals(object obj)
        {
            return this.DNI.Equals((obj as Persona).DNI);
        }
	}


}
