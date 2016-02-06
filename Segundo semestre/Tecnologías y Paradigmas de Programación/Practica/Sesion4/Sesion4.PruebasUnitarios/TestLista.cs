using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TPP.TNP3
{
    [TestClass]
    public class TestLista
    {
        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista añadiendo int
        /// </summary>
        [TestMethod]
        public void AñadirInt()
        {
            Lista<int> lista = new Lista<int>();
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
            Lista<String> lista = new Lista<String>();
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
            Lista<double> lista = new Lista<double>();
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
            Lista<Persona> lista = new Lista<Persona>();
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(1, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(2, lista.Size());
            lista.Añadir(new Persona());
            Assert.AreEqual(3, lista.Size());
        }

        /// <summary>
        /// Prueba de que el método Borrar controla cuando llamamos a añadir el parametro sea correcto(distinto de null)
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void TestAñadirNull()
        {
            Lista<int?> lista = new Lista<int?>();
            lista.Añadir(null);
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista borrando int
        /// </summary>
        [TestMethod]
        public void BorrarInt()
        {
            Lista<int> lista = new Lista<int>();
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
            Lista<String> lista = new Lista<String>();
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
            Lista<double> lista = new Lista<double>();
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
            Lista<Persona> lista = new Lista<Persona>();
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
        /// Prueba de que el método Borrar controla cuando llamamos a borrar el parametro sea correcto
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void TestBorrarConListaVacia()
        {
            Lista<int> lista = new Lista<int>();
            lista.Borrar();
        }

        /// <summary>
        /// Prueba de que el método Borrar controla cuando llamamos a borrar el parametro sea correcto
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void TestGetElementoFueraDeRango()
        {
            Lista<int> lista = new Lista<int>();
            lista.GetElemento(1);
        }

        /// <summary>
        /// Método de prueba para probar el correcto comportamiento de la lista buscando si contiene un int
        /// </summary>
        [TestMethod]
        public void ContieneInt()
        {
            Lista<int> lista = new Lista<int>();
            Assert.AreEqual(0, lista.Size());
            lista.Añadir(9);
            Assert.AreEqual(1, lista.Size());
            Assert.AreEqual(true, lista.Contiene(9));
            Assert.AreEqual(false, lista.Contiene(19));
        }
    }
}
