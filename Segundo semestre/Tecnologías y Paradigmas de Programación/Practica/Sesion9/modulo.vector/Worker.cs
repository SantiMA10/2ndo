
namespace TPP.Practicas.Concurrente.Practica1 {

    internal class Worker {

        private short[] vector;

        private int índiceDesde, índiceHasta;

        private long resultado;

        internal long Resultado {
            get { return this.resultado; }
        }

        internal Worker(ref short[] vector, int índiceDesde, int índiceHasta) {
            this.vector = vector;
            this.índiceDesde = índiceDesde;
            this.índiceHasta = índiceHasta;
        }

        internal void Calcular() {
            this.resultado = 0;
            for(int i= this.índiceDesde; i<=this.índiceHasta; i++)
                this.resultado += this.vector[i] * this.vector[i];
        }

    }

}
