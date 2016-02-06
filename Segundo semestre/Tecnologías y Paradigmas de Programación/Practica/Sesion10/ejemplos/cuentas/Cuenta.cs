using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace TPP.Practicas.Concurrente.Practica2 {

    public class Cuenta {
        private decimal saldo;
        private string numeroCuenta;

        public Cuenta(string numeroCuenta, decimal saldo = 0) {
            this.saldo = saldo;
            this.numeroCuenta = numeroCuenta;
        }

        public string NumeroCuenta { get { return this.numeroCuenta; } }

        public bool Retirar(decimal cantidad) {
            if (this.saldo < cantidad)
                return false;
            saldo -= cantidad;
            return true;
        }

        public void Ingresar(decimal cantidad) {
            saldo += cantidad;
        }

        public bool Transferir(Cuenta cuentaDestino, decimal cantidad) {
            lock (this) {
                lock (cuentaDestino) {
                    Thread.Sleep(100); // Simula procesamiento...
                    if (this.Retirar(cantidad)) {
                        cuentaDestino.Ingresar(cantidad);
                        return true;
                    }
                    else
                        return false;
                }
            }
        }
    }
}
