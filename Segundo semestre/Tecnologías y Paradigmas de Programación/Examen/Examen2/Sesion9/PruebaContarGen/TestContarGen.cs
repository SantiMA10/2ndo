using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TPP.Practicas.Concurrente.Practica1;

namespace PruebaContarGen
{
    [TestClass]
    public class TestContarGen
    {
        [TestMethod]
        public void TestContarGenAleatorio()
        {
            for (int i = 100; i < 1000000; i *= 2)
            {
                char[] ADN = ProgramModuloVector.CrearVectorAleatorio(i);
                char[] gen = ProgramModuloVector.CrearVectorAleatorio(10);

                Master monohilo = new Master(ADN, gen, 1);
                int resultadoMonohilo = monohilo.CalcularNumeroGenes();

                for (int numeroHilos = 1; numeroHilos <= 10; numeroHilos++)
                {
                    Master master = new Master(ADN, gen, numeroHilos);
                    int resultado = master.CalcularNumeroGenes();
                    Assert.AreEqual(resultado, resultadoMonohilo);
                }
            }
        }

        [TestMethod]
        public void TestContarGen()
        {

            char[] ADN = new char[] { 'A', 'C', 'T', 'G', 'A', 'A', 'C', 'C', 'T', 'G', 'G', 'A' };
            char[] gen = new char[] { 'A', 'C' };

            Master monohilo = new Master(ADN, gen, 1);
            int resultadoMonohilo = monohilo.CalcularNumeroGenes();

            for (int numeroHilos = 1; numeroHilos <= 10; numeroHilos++)
            {
                Master master = new Master(ADN, gen, numeroHilos);
                int resultado = master.CalcularNumeroGenes();
                Assert.AreEqual(resultado, resultadoMonohilo);
            }

        }
    }
}
