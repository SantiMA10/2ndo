using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TPP.TNP3
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
        /// Prueba de que el método Borrar controla cuando llamamos a añadir el parametro sea correcto(distinto de null)
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void TestAñadirNull()
        {
            lista.Añadir(null);
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
        /// Prueba de que el método Borrar controla cuando llamamos a borrar el parametro sea correcto
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void TestBorrarConListaVacia()
        {
            lista.Borrar();
        }

        /// <summary>
        /// Prueba de que el método Borrar controla cuando llamamos a borrar el parametro sea correcto
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void TestGetElementoFueraDeRango()
        {
            lista.GetElemento(1);
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
    }
}
