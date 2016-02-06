using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TPP.TNP2
{
    [TestClass]
    public class TestLista
    {
        private Lista lista;

        /// <summary>
        /// Inicialización para todo los test
        /// </summary>
        [TestInitialize]
        public void InicializarTests()
        {
            this.lista = new Lista();
        }

        /// <summary>
        /// Método de prueba para comprobar que la lista se crea vacia
        /// </summary>
        [TestMethod]
        public void ListaVacia()
        {
            Assert.AreEqual(0, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista añadiendo int
        /// </summary>
        [TestMethod]
        public void AñadirInt()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(9);
            Assert.AreEqual(1, lista.Size());
            lista.Añadir(10);
            Assert.AreEqual(2, lista.Size());
            lista.Añadir(22);
            Assert.AreEqual(3, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista añadiendo String
        /// </summary>
        [TestMethod]
        public void AñadirString()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir("hola");
            Assert.AreEqual(1, lista.Size());
            lista.Añadir("adios");
            Assert.AreEqual(2, lista.Size());
            lista.Añadir("prueba");
            Assert.AreEqual(3, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista añadiendo double
        /// </summary>
        [TestMethod]
        public void AñadirDouble()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(1.0);
            Assert.AreEqual(1, lista.Size());
            lista.Añadir(22.4);
            Assert.AreEqual(2, lista.Size());
            lista.Añadir(1.6547);
            Assert.AreEqual(3, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista añadiendo Persona
        /// </summary>
        [TestMethod]
        public void AñadirPersona()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(1, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(2, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(3, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista borrando int
        /// </summary>
        [TestMethod]
        public void BorrarInt()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(9);
            Assert.AreEqual(1, lista.Size());
            lista.Añadir(10);
            Assert.AreEqual(2, lista.Size());
            lista.Añadir(22);
            Assert.AreEqual(3, lista.Size());
            lista.Borrar();
            Assert.AreEqual(2, lista.Size());
            lista.Borrar();
            Assert.AreEqual(1, lista.Size());
            lista.Borrar();
            Assert.AreEqual(0, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista borrando String
        /// </summary>
        [TestMethod]
        public void BorrarString()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir("hola");
            Assert.AreEqual(1, lista.Size());
            lista.Añadir("adios");
            Assert.AreEqual(2, lista.Size());
            lista.Añadir("prueba");
            Assert.AreEqual(3, lista.Size());
            lista.Borrar();
            Assert.AreEqual(2, lista.Size());
            lista.Borrar();
            Assert.AreEqual(1, lista.Size());
            lista.Borrar();
            Assert.AreEqual(0, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista borrando double
        /// </summary>
        [TestMethod]
        public void BorrarDouble()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(1.0);
            Assert.AreEqual(1, lista.Size());
            lista.Añadir(22.4);
            Assert.AreEqual(2, lista.Size());
            lista.Añadir(1.6547);
            Assert.AreEqual(3, lista.Size());
            lista.Borrar();
            Assert.AreEqual(2, lista.Size());
            lista.Borrar();
            Assert.AreEqual(1, lista.Size());
            lista.Borrar();
            Assert.AreEqual(0, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista borrando Persona
        /// </summary>
        [TestMethod]
        public void BorrarPersona()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(1, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(2, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(3, lista.Size());
            lista.Borrar();
            Assert.AreEqual(2, lista.Size());
            lista.Borrar();
            Assert.AreEqual(1, lista.Size());
            lista.Borrar();
            Assert.AreEqual(0, lista.Size());
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista buscando si contiene un int
        /// </summary>
        [TestMethod]
        public void ContieneInt()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(9);
            Assert.AreEqual(1, lista.Size());
            Assert.AreEqual(true, lista.Contiene(9));
            Assert.AreEqual(false, lista.Contiene(19));
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista buscando si contiene un String
        /// </summary>
        [TestMethod]
        public void ContieneString()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir("hola");
            Assert.AreEqual(1, lista.Size());
            Assert.AreEqual(true, lista.Contiene("hola"));
            Assert.AreEqual(false, lista.Contiene("adios"));
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista buscando si contiene un double
        /// </summary>
        [TestMethod]
        public void ContieneDouble()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(1.0);
            Assert.AreEqual(1, lista.Size());
            Assert.AreEqual(true, lista.Contiene(1.0));
            Assert.AreEqual(false, lista.Contiene(19.2312));
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista buscando si contiene un Persona
        /// </summary>
        [TestMethod]
        public void ContienePersona()
        {
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(new Persona{
                Nombre = "José",
                Apellido1 = "Pérez",
                Apellido2 = "Vázquez",
                // La edad no la escribimos
                Nacionalidad = "Española",
                DNI = "9345765-C",
                FechaNacimiento = new DateTime(1980, 3, 13),
                Sexo = Género.Masculino,
            });
            Assert.AreEqual(1, lista.Size());
            Assert.AreEqual(true, lista.Contiene(new Persona
            {
                Nombre = "José",
                Apellido1 = "Pérez",
                Apellido2 = "Vázquez",
                // La edad no la escribimos
                Nacionalidad = "Española",
                DNI = "9345765-C",
                FechaNacimiento = new DateTime(1980, 3, 13),
                Sexo = Género.Masculino,
            }));
            Assert.AreEqual(false, lista.Contiene(new Persona
            {
                Nombre = "Miguel",
                Apellido1 = "Martín",
                Apellido2 = "López",
                // La edad no la escribimos
                Nacionalidad = "Española",
                DNI = "76632133-B",
                FechaNacimiento = new DateTime(1980, 3, 13),
                Sexo = Género.Masculino,
            }));
        }
        
    }
}
