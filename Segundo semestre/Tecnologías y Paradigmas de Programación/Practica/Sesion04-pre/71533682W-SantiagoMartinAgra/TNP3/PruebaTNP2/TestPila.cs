using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TPP.TNP3
{
    [TestClass]
    public class TestPila
    {
        private Pila pila;

        /// <summary>
        /// Inicialización para todo los test
        /// </summary>
        [TestInitialize]
        public void InicializarTests()
        {
            this.pila = new Pila(5);
        }

        /// <summary>
        /// Método de prueba para comprobar el correcto funcionamiento del constuctor de la pila
        /// </summary>
        [TestMethod]
        public void TestConstructor()
        {
            Assert.AreEqual(false, pila.EstaLlena);
            Assert.AreEqual(true, pila.EstaVacia);
        }

        /// <summary>
        /// Método de prueba para comprobar el correcto funcionamiento del metodo Push de la pila
        /// </summary>
        [TestMethod]
        public void TestPush()
        {
            Assert.AreEqual(true, pila.EstaVacia);
            pila.Push(5);
            Assert.AreEqual(false, pila.EstaVacia);
            pila.Push(7);
            pila.Push(8);
            pila.Push(9);
            pila.Push(11);
            Assert.AreEqual(true, pila.EstaLlena);
        }

        /// <summary>
        /// Prueba de que el método Push controla que lo llamemos con la pila llena.
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(StackOverflowException))]
        public void TestPushConPilaLlena()
        {
            pila.Push(5);
            pila.Push(7);
            pila.Push(8);
            pila.Push(9);
            pila.Push(11);
            pila.Push(77);
        }

        /// <summary>
        /// Prueba de que el método Push controla que lo llamemos pasandole null.
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void TestPushPasandoNull()
        {
            pila.Push(null);
        }

        /// <summary>
        /// Método de prueba para comprobar el correcto funcionamiento del metodo Pop de la pila
        /// </summary>
        [TestMethod]
        public void TestPop()
        {
            pila.Push(5);
            pila.Push(7);
            pila.Push(8);
            pila.Push(9);
            pila.Push(11);
            Assert.AreEqual(11, pila.Pop());
            Assert.AreEqual(false, pila.EstaVacia);
            Assert.AreEqual(false, pila.EstaLlena);
            Assert.AreEqual(9, pila.Pop());
            Assert.AreEqual(8, pila.Pop());
            Assert.AreEqual(7, pila.Pop());
            Assert.AreEqual(5, pila.Pop());
            Assert.AreEqual(false, pila.EstaLlena);
            Assert.AreEqual(true, pila.EstaVacia);
        }

        /// <summary>
        /// Prueba de que el método Pop controla que lo llamemos con la pila vacia.
        /// </summary>
        [TestMethod]
        [ExpectedException(typeof(InvalidOperationException))]
        public void TestPopConPilaVacia()
        {
            pila.Pop();
        }
    }
}
