
namespace TPP.Practicas.Concurrente.Practica1 {

    internal class Worker {

        private char[] ADN;
        private char[] gen;

        private int índiceDesde, índiceHasta;

        private int resultado;

        internal int Resultado {
            get { return this.resultado; }
        }

        internal Worker(ref char[] ADN, ref char[] gen, int índiceDesde, int índiceHasta) {
            this.ADN = ADN;
            this.gen = gen;
            this.índiceDesde = índiceDesde;
            this.índiceHasta = índiceHasta;
        }

        internal void Calcular() {
            this.resultado = 0;
            bool encontrado = false;
            for (int i = this.índiceDesde; i <= this.índiceHasta; i++)
            {
                encontrado = true;
                if (i + gen.Length <= ADN.Length)
                {
                    for (int j = 0; j < gen.Length; j++)
                    {
                        encontrado = encontrado && ADN[i + j] == gen[j];
                    }
                    if (encontrado)
                    {
                        resultado++;
                    }
                }
            }
                
        }

    }

}
