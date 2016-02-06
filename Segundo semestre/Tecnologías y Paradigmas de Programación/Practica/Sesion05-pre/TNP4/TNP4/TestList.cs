using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace TNP4
{
    [TestClass]
    public class TestList
    {
        private IList<int> lista;

        /// <summary>
        /// Inicialización para todo los test
        /// </summary>
        [TestInitialize]
        public void InicializarTests()
        {
            this.lista = new List<int>();
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de añadir y contar el número de elementos
        /// de la clase List<T>
        /// </summary>
        [TestMethod]
        public void TestAñadirYNumeroElementos()
        {
            lista.Add(4);
            Assert.AreEqual(1, lista.Count);
            lista.Add(7);
            Assert.AreEqual(2, lista.Count);
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de obtener y reescribir elementos
        /// de la clase List<T>
        /// </summary>
        [TestMethod]
        public void TestReescribirYObtener()
        {
            lista.Add(4);
            Assert.AreEqual(4, lista[0]);
            lista.Insert(0, 5);
            Assert.AreEqual(5, lista[0]);

        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de para comprobar si un elemento esta en el diccionario
        /// de la clase List<T>
        /// </summary>
        [TestMethod]
        public void TestEnLaLista()
        {
            lista.Add(4);
            Assert.AreEqual(true, lista.Contains(4));
            Assert.AreEqual(false, lista.Contains(6));

        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función para obtener el indice de un elemento de la lista
        /// </summary>
        [TestMethod]
        public void TestPrimerIndice()
        {
            lista.Add(4);
            lista.Add(7);
            lista.Add(99);
            lista.Add(3);
            lista.Add(4);
            Assert.AreEqual(0, lista.IndexOf(4));
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de borrar
        /// de la clase List<T>
        /// </summary>
        [TestMethod]
        public void TestBorrar()
        {
            lista.Add(4);
            lista.Add(7);
            lista.Add(99);
            lista.Add(3);
            lista.Add(4);
            Assert.AreEqual(true, lista.Remove(4));
            Assert.AreEqual(4, lista.Count);
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento del foreach de la clase List<T>
        /// </summary>
        [TestMethod]
        public void TestFor()
        {
            lista.Add(1);
            lista.Add(2);
            lista.Add(3);
            lista.Add(4);
            lista.Add(5);
            int ele = 1;
            foreach (int elemento in lista)
            {
                Assert.AreEqual(ele, elemento);
                ele++;
            }
        }

    }
}
