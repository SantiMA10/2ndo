using System;

namespace TPP.Practicas.OrientacionObjetos.Practica1 {
	class Persona {
		public Persona(string nombre, string dni) {
			this.Nombre = nombre;
			this.DNI = dni;
		}

		public string Nombre { get; set; }
		public string Apellidos { get; set; }
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
	}

	class Ejemplo {
		public static void Main() {
			Persona raul = new Persona("Raul", "123456789");
			Console.WriteLine(raul.DNI);
			Console.WriteLine(raul);
		}
	}
}
