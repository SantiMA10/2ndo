﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TPP.TNP1
{
    class Nodo
    {
        internal Object valor;
        internal Nodo siguiente;

        public Nodo(int valor){
            this.valor = valor;
            siguiente = null;
        }
    }
}
