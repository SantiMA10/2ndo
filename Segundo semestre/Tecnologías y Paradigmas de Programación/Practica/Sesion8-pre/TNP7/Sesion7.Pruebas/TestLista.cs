using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TPP.TNP3;
using System.Linq;
using System.Collections.Generic;

namespace Sesion7.Pruebas
{
    [TestClass]
    public class TestLista
    {
        private ListaOrdenada<int> lista;

        [TestInitialize]
        public void Inicializacion()
        {
            lista = new ListaOrdenada<int>();
            lista.Añadir(1);
            lista.Añadir(3);
            lista.Añadir(2);
            lista.Añadir(6);
            lista.Añadir(5);
            lista.Añadir(4);
        }

        [TestMethod]
        public void TestBuscar()
        {
            for (int i = 1; i < 7; i++)
            {
                Assert.AreEqual(i, lista.Buscar(x => x == i));
            }
        }
        [TestMethod]
        public void TestMap()
        {
            IEnumerable<int> resultado = lista.Map<int, int>(x => x + 5);
            for (int i = 0; i < 5; i++)
            {
                Assert.AreEqual(i + 6, resultado.ElementAt(i));
            }
        }
        [TestMethod]
        public void TestFiltrar()
        {
            IEnumerable<int> resultado = lista.Filtrar(x => x < 5);
            int i = 1;
            foreach (int elemento in resultado)
            {
                Assert.AreEqual(i, elemento);
                i++;
            }
        }
        [TestMethod]
        public void TestReducir()
        {
            Assert.AreEqual(21, lista.Reducir(0, (x,y) => x+y));
        }
        [TestMethod]
        public void TestInvertir()
        {
            Assert.AreEqual(21, lista.Invertir(0, (x,y) => x+y));
        }
        [TestMethod]
        public void TestForEach()
        {
            for (int i = 0; i < 6; i++)
            {
                int[] enteros = new int[lista.Size()];
                lista.ForEach(a => enteros[a-1] = a);
                Assert.AreEqual(i+1, enteros[i]);
                
            }
        }
    }
}
