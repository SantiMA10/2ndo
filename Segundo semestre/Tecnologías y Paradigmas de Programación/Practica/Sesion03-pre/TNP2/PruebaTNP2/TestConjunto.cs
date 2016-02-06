using System;
using System.Text;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TPP.TNP2
{
    /// <summary>
    /// Clase de test para el Conjunto
    /// </summary>
    [TestClass]
    public class TestConjunto
    {
        private Conjunto c1;
        private Conjunto c2;

        /// <summary>
        /// Inicialización para todos los test
        /// </summary>
        [TestInitialize]
        public void InicializarTests()
        {
            this.c1 = new Conjunto();
            this.c2 = new Conjunto();
        }

        /// <summary>
        /// Método de prueba para el operador +(Añadir)
        /// </summary>
        [TestMethod]
        public void Añadir()
        {
            c1 += 2;
            Assert.AreEqual(1, c1.Size());
            c1 += 2;
            Assert.AreEqual(1, c1.Size());
        }

        /// <summary>
        /// Método de prueba para el operador -(Borrar)
        /// </summary>
        [TestMethod]
        public void Borrar()
        {
            c1 += 2;
            Assert.AreEqual(1, c1.Size());
            c1 -= 0;
            Assert.AreEqual(0, c1.Size());
        }

        /// <summary>
        /// Método de prueba para el operador [](Contiene)
        /// </summary>
        [TestMethod]
        public void Contiene()
        {
            c1 += 2;
            Assert.AreEqual(1, c1.Size());
            Assert.AreEqual(true, c1[2]);
            Assert.AreEqual(false, c1[3]);
        }

        /// <summary>
        /// Método de prueba para el operador -(Diferencia entre conjuntos)
        /// </summary>
        [TestMethod]
        public void Diferencia()
        {
            c1 += 2;
            c1 += 4;
            Assert.AreEqual(2, c1.Size());

            c2 += 2;
            c2 += 9;
            Assert.AreEqual(2, c2.Size());

            c2 = c2 - c1;
            Assert.AreEqual(1, c2.Size());
        }

        /// <summary>
        /// Método de prueba para el operador |(Unión entre conjuntos)
        /// </summary>
        [TestMethod]
        public void Union()
        {
            c1 += 2;
            c1 += 4;
            Assert.AreEqual(2, c1.Size());

            c2 += 2;
            c2 += 9;
            Assert.AreEqual(2, c2.Size());
            
            c2 = c2 | c1;
            Assert.AreEqual(3, c2.Size());
        }

        /// <summary>
        /// Método de prueba para el operador &(Intersección entre conjuntos)
        /// </summary>
        [TestMethod]
        public void Interseccion()
        {
            c1 += 2;
            c1 += 3;
            c1 += 4;
            Assert.AreEqual(3, c1.Size());

            c2 += 2;
            c2 += 9;
            c2 += 3;
            Assert.AreEqual(3, c2.Size());

            c2 = c2 & c1;
            Assert.AreEqual(2, c2.Size());
        }
    }
}
