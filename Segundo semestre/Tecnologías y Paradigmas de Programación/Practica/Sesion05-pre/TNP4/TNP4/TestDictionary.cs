using System;
using System.Text;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace TNP4
{
    /// <summary>
    /// Descripción resumida de TestDiccionario
    /// </summary>
    [TestClass]
    public class TestDictionary
    {
        private IDictionary<int, double> diccionario;

        /// <summary>
        /// Inicialización para todo los test
        /// </summary>
        [TestInitialize]
        public void InicializarTests()
        {
            this.diccionario = new Dictionary<int, double>();
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de añadir y contar el número de elementos
        /// de la clase Dictionary<T,R>
        /// </summary>
        [TestMethod]
        public void TestAñadirYNumeroPares()
        {
            diccionario.Add(1, 10.0);
            Assert.AreEqual(1, diccionario.Count);
            diccionario.Add(4, 11.0);
            Assert.AreEqual(2, diccionario.Count);
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de obtener y reescribir elementos
        /// de la clase Dictionary<T,R>
        /// </summary>
        [TestMethod]
        public void TestObtenerYReescribir()
        {
            diccionario.Add(1, 10.0);
            diccionario.Add(4, 11.0);
            Assert.AreEqual(10.0, diccionario[1]);
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de para comprobar si un elemento esta en el diccionario
        /// de la clase Dictionary<T,R>
        /// </summary>
        [TestMethod]
        public void TestEnElDiccionario()
        {
            diccionario.Add(1, 10.0);
            diccionario.Add(4, 11.0);
            double aux;
            Assert.AreEqual(true, diccionario.TryGetValue(1, out aux));
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento de la función de borrar
        /// de la clase Dictionary<T,R>
        /// </summary>
        [TestMethod]
        public void TestBorrar()
        {
            diccionario.Add(1, 10.0);
            diccionario.Add(4, 11.0);
            Assert.AreEqual(true, diccionario.Remove(1));
        }

        /// <summary>
        /// Test para comprobar el correcto funcionamiento del foreach de la clase Dictionary<T,R>
        /// </summary>
        [TestMethod]
        public void TestFor()
        {
            diccionario.Add(1, 10.0);
            diccionario.Add(2, 11.0);
            diccionario.Add(3, 12.0);
            diccionario.Add(4, 13.0);
            diccionario.Add(5, 14.0);
            diccionario.Add(6, 15.0);
            int keycount = 1;
            double value = 10.0;
            foreach (KeyValuePair<int, double> par in diccionario)
            {
                Assert.AreEqual(keycount, par.Key);
                Assert.AreEqual(value, par.Value);
                keycount++;
                value++;
            }
           
        }
    }
}
