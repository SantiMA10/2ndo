using System;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace TPP.Practicas.Funcional.Practica1
{
    [TestClass]
    public class TestLinQ
    {
        IEnumerable<Persona> listaPersonas;
        IEnumerable<Angulo> listaAngulos;

        [TestInitialize]
        public void Inicializacion()
        {
            listaPersonas = Factoría.CrearPersonas();
            listaAngulos  = Factoría.CrearAngulos();
        }
        [TestMethod]
        public void BuscarPersonas()
        {
            Persona maria = listaPersonas.FirstOrDefault<Persona>(x => x.Nombre.Equals("María") && x.Nif.Contains("A"));
            Assert.IsTrue(maria.Nombre.Equals("María") && maria.Nif.Contains("A")); 
        }
        [TestMethod]
        public void BuscarAngulos()
        {
            Angulo recto = listaAngulos.FirstOrDefault<Angulo>(x => x.Grados == 90);
            Assert.IsTrue(recto.Grados == 90);
        }
        [TestMethod]
        public void FiltrarPersonas()
        {
            IEnumerable<Persona> marias = listaPersonas.Where<Persona>(x => x.Nombre.Equals("María") && x.Nif.Contains("A"));
            Assert.AreEqual(1, marias.Count());
        }
        [TestMethod]
        public void FiltrarAngulos()
        {
            IEnumerable<Angulo> rectos = listaAngulos.Where<Angulo>(x => x.Grados == 90);
            Assert.AreEqual(1, rectos.Count());
        }
        [TestMethod]
        public void ReducirPersonas()
        {
            Dictionary<String, int> d = new Dictionary<string, int>();
            d = listaPersonas.Aggregate<Persona, Dictionary<String, int>>(d, DistribucionDeNombres);
            Dictionary<String, int> f = new Dictionary<string, int>();
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

        public Dictionary<String, int> DistribucionDeNombres(Dictionary<String, int> d, Persona p)
        {
            if (d == null)
            {
                d = new Dictionary<String, int>();
            }
            int cantidad = 1;
            if (d.ContainsKey(p.Nombre))
            {
                d.TryGetValue(p.Nombre, out cantidad);
                cantidad++;
                d[p.Nombre] = cantidad;
            }
            else
            {
                d.Add(p.Nombre, cantidad);
            }

            return d;
        }

        [TestMethod]
        public void ReducirAngulos()
        {
            float sumaTotal = 0;
            sumaTotal = listaAngulos.Aggregate<Angulo, float>(sumaTotal, (y, x) => x.Grados + y);
            Assert.AreEqual(64980, sumaTotal);

            double senoMax = 0;
            senoMax = listaAngulos.Aggregate<Angulo, double>(senoMax, (y, x) => x.Seno() > y ? x.Seno() : y);
            Assert.AreEqual(1, senoMax);
        }
        [TestMethod]
        public void MapPersonas()
        {
            IEnumerable<String> nombreApellido;
            nombreApellido = listaPersonas.Select<Persona, String>((x) => x.Apellido1+", "+x.Nombre);
            Assert.AreEqual(9, nombreApellido.Count());
        }

        [TestMethod]
        public void MapAngulos()
        {
            IEnumerable<int> cuadrantes;
            cuadrantes = listaAngulos.Select<Angulo, int>((x) => x.GetCuadrante());
            Assert.AreEqual(361, cuadrantes.Count());

        }

    }
}
