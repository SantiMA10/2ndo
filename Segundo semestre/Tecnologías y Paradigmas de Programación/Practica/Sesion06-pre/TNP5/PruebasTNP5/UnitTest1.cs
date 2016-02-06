using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TNP5;
using TPP.Practicas.Funcional.Practica1;
using System.Collections.Generic;


namespace PruebasTNP5
{
    [TestClass]
    public class UnitTest1
    {

        private Persona[] p;
        private Angulo[] a;

        [TestInitialize]
        public void TestStart()
        {
            p = Factoría.CrearPersonas();
            a = Factoría.CrearAngulos();
        }

        [TestMethod]
        public void TestBuscar()
        {
            Assert.AreEqual("María",  Program.Buscar(p, 
                delegate(Persona per) { return per.Nombre == "María" && per.Nif.Contains("A"); }).Nombre);

            Assert.AreEqual(new Angulo(90).Grados, Program.Buscar(a, 
                delegate(Angulo an) { return an.Grados == 90; }).Grados);
        }

        [TestMethod]
        public void TestFiltrar()
        {
            Assert.AreEqual("María",Program.Filtrar(p, 
                delegate(Persona per) { return per.Nombre == "María" && per.Nif.Contains("A"); })[0].Nombre);

            Assert.AreEqual(new Angulo(90).Grados, (Program.Filtrar(a, 
                delegate(Angulo an) { return an.Grados == 90; }))[0].Grados);
        }

        [TestMethod]
        public void TestReducir()
        {
            Assert.AreEqual(64980, Program.Reducir(a, delegate(Angulo an, float r) { return r + an.Grados; }));

            Assert.AreEqual(1, Program.Reducir<Angulo, double>(a, Program.SenoMaximo));

            Dictionary<String, int> d = Program.Reducir<Persona, Dictionary<String, int>>(p, Program.DistribucionDeNombres);
            Dictionary<String, int> f = new Dictionary<string,int>();
            f.Add("María", 2);
            f.Add("Juan", 2);
            f.Add("Pepe", 1);
            f.Add("Luis", 1); 
            f.Add("Carlos", 1);
            f.Add("Miguel", 1);
            f.Add("Cristina", 1);
            int valor;
            foreach (KeyValuePair<String, int> elemento in d)
            {
                Assert.AreEqual(true, f.ContainsKey(elemento.Key));
                f.TryGetValue(elemento.Key, out valor);
                Assert.AreEqual(elemento.Value, valor);
            }

            
        }
    }
}
