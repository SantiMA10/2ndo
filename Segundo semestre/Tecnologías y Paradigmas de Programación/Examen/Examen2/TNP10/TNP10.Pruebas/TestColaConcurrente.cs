using System;
using System.Threading;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TNP10.Biblioteca;

namespace TNP10.Pruebas
{
    [TestClass]
    public class TestColaConcurrente
    {
        private ColaConcurrente<int> cola;

        [TestInitialize]
        public void Inicializacion()
        {
            cola = new ColaConcurrente<int>();
        }

        [TestMethod]
        public void TestEnMonoHilo()
        {
            int valor;

            Assert.AreEqual(true, cola.EstaVacia);
            Assert.AreEqual(0, cola.NumeroElementos);
            Assert.AreEqual(false, cola.Extraer(out valor));

            cola.Añadir(1);

            Assert.AreEqual(false, cola.EstaVacia);
            Assert.AreEqual(1, cola.NumeroElementos);
            Assert.AreEqual(true, cola.PrimerElemento(out valor));
            Assert.AreEqual(1, valor);

            cola.Añadir(2);
            cola.Añadir(3);
            cola.Añadir(4);

            Assert.AreEqual(false, cola.EstaVacia);
            Assert.AreEqual(4, cola.NumeroElementos);

            Assert.AreEqual(true, cola.Extraer(out valor));
            Assert.AreEqual(1, valor);
            Assert.AreEqual(true, cola.PrimerElemento(out valor));
            Assert.AreEqual(3, cola.NumeroElementos);
            Assert.AreEqual(2, valor);
            Assert.AreEqual(true, cola.Extraer(out valor));
            Assert.AreEqual(2, valor);
            Assert.AreEqual(true, cola.Extraer(out valor));
            Assert.AreEqual(3, valor);
            Assert.AreEqual(true, cola.Extraer(out valor));
            Assert.AreEqual(4, valor);
            Assert.AreEqual(false, cola.Extraer(out valor));

        }

        [TestMethod]
        public void testMultihilo()
        {
            Thread[] hilos = new Thread[100];

            Assert.AreEqual(true, cola.EstaVacia);
            Assert.AreEqual(0, cola.NumeroElementos);

            for (int i = 0; i < hilos.Length; i++)
            {
                hilos[i] = new Thread((x) => {
                    cola.Añadir((int)x);
                });
                hilos[i].Start(i);
            }

            for (int i = 0; i < hilos.Length; i++)
            {
                hilos[i].Join();
            }

            Assert.AreEqual(false, cola.EstaVacia);
            Assert.AreEqual(100, cola.NumeroElementos);

            for (int i = 0; i < hilos.Length; i++)
            {
                hilos[i] = new Thread(() => {
                    int valor;
                    Assert.AreEqual(true, cola.PrimerElemento(out valor));
                });
                hilos[i].Start();
            }

            for (int i = 0; i < hilos.Length; i++)
            {
                hilos[i].Join();
            }

            Assert.AreEqual(false, cola.EstaVacia);
            Assert.AreEqual(100, cola.NumeroElementos);

            for (int i = 0; i < hilos.Length; i++)
            {
                hilos[i] = new Thread(() =>{
                    int valor;
                    Assert.AreEqual(true, cola.Extraer(out valor));
                });
                hilos[i].Start();
            }

            for (int i = 0; i < hilos.Length; i++)
            {
                hilos[i].Join();
            }

            Assert.AreEqual(true, cola.EstaVacia);
            Assert.AreEqual(0, cola.NumeroElementos);

        }
    }
}
