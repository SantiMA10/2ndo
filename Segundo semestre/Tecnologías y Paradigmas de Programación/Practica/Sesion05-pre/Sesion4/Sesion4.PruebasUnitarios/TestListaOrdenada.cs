using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TPP.TNP3
{
    [TestClass]
    public class TestListaOrdenada
    {

        private ListaOrdenada<int> lista;

        /// <summary>
        /// Test para comprobar que funciona correctamente el añadir de la clase ListaOrdenada
        /// </summary>
        [TestMethod]
        public void TestAñadir()
        {
            this.lista = new ListaOrdenada<int>();
            lista.Añadir(7);
            lista.Añadir(6);
            lista.Añadir(9);
            lista.Añadir(8);
            lista.Añadir(5);
            lista.Añadir(1);
            lista.Añadir(2);
            lista.Añadir(3);
            lista.Añadir(4);
            Assert.AreEqual(1, lista.GetElemento(0));
            Assert.AreEqual(2, lista.GetElemento(1));
            Assert.AreEqual(3, lista.GetElemento(2));
            Assert.AreEqual(4, lista.GetElemento(3));
            Assert.AreEqual(5, lista.GetElemento(4));
            Assert.AreEqual(6, lista.GetElemento(5));
            Assert.AreEqual(7, lista.GetElemento(6));
            Assert.AreEqual(8, lista.GetElemento(7));
            Assert.AreEqual(9, lista.GetElemento(8));
        }

        /// <summary>
        /// Test para comprobar que la lista ordenada se puede recorrer perfectamente con un foreach
        /// </summary>
        [TestMethod]
        public void TestFor()
        {
            this.lista = new ListaOrdenada<int>();
            lista.Añadir(7);
            lista.Añadir(6);
            lista.Añadir(9);
            lista.Añadir(8);
            lista.Añadir(5);
            lista.Añadir(1);
            lista.Añadir(2);
            lista.Añadir(3);
            lista.Añadir(4);

            int ele = 1;
            foreach (int elemento in lista)
            {
                Assert.AreEqual(ele, elemento);
                ele++;
            }
        }
    }
}
