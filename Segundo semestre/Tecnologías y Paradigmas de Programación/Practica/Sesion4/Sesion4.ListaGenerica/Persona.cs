using System;

namespace TPP.TNP3 {

    public enum Género {
            Masculino, Femenino
    }

	public struct Persona {

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
        public DateTime FechaNacimiento { set { AñoNacimiento = value.Year;  } }
        public Género Sexo;

        public override bool Equals(Object o) 
        {
            int hashPropio = GetHashCode();
            int hashParametro = o.GetHashCode();

            return hashPropio == hashParametro;
        }

        public override int GetHashCode()
        {
            return Convert.ToInt32(DNI.Split('-')[0]);
        }

		public override string ToString() {
			return Nombre;
		}
	}
}
