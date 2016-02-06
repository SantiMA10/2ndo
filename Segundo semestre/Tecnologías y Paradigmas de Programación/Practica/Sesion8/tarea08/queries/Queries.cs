using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPP.Laboratory.Functional.Lab08 {

    class Query {

        private Model model = new Model();

        private static void Show<T>(IEnumerable<T> collection) {
            foreach (var item in collection) {
                Console.WriteLine(item);
            }
            Console.WriteLine("Number of items in the collection: {0}.", collection.Count());
        }

        static void Main(string[] args) {
            Query query = new Query();
            query.Query1();
            query.Query2();
            query.Query3();
            query.Query4();
            query.Query5();
        }

        private void Query1() 
        {
            System.Console.WriteLine("Querry1");
            //Empleados de "Computer Science"
            IEnumerable<Employee> paso1 = model.Departments.FirstOrDefault<Department>((departamento) => departamento.Name == "Computer Science").Employees;
            //Empleados con despacho en "Faculty of Science"
            IEnumerable<Employee> paso2 = paso1.Where<Employee>((empleado) => empleado.Office.Building == "Faculty of Science");
            //Obtenemos las llamadas de cada empleado
            IEnumerable<KeyValuePair<Employee, int>> paso3 = paso2.Join<Employee, PhoneCall, String, KeyValuePair<Employee, int>>(model.PhoneCalls, (empleado) => empleado.TelephoneNumber,
                (llamada) => llamada.SourceNumber, (empleado, llamada) => new KeyValuePair<Employee, int>(empleado, llamada.Seconds));
            //Suma de llamadas de cada empleado
            Dictionary<Employee, int> paso4 = paso3.Aggregate<KeyValuePair<Employee, int>, Dictionary<Employee, int>>(new Dictionary<Employee, int>(),
                (semilla, par) => {int value; if(semilla.TryGetValue(par.Key, out value)) { semilla[par.Key] = value + par.Value; } 
                else { semilla.Add(par.Key, par.Value); } return semilla; } );
            //Filtro los que tiene mas de 60 segundos de llamadas
            IEnumerable<KeyValuePair<Employee, int>> paso5 = paso4.Where<KeyValuePair<Employee, int>>((par) => par.Value > 60);
            //Ordeno por edad
            IEnumerable<KeyValuePair<Employee, int>> paso6 = paso5.OrderBy<KeyValuePair<Employee, int>, int>((par) => par.Key.Age);
            //Imprimo la solucion
            Show(paso6);
        }

        private void Query2()
        {
            System.Console.WriteLine("Query2");
            //Saco los empleados del departamento "Computer Science"
            IEnumerable<Employee> paso1 = model.Departments.FirstOrDefault<Department>((x) => x.Name == "Computer Science").Employees;
            //Saco la lista de llamadas del departamento "Computer Science"
            IEnumerable<PhoneCall> paso2 = model.PhoneCalls.Join<PhoneCall, Employee, String, PhoneCall>(paso1, (x) => x.SourceNumber, (x) => x.TelephoneNumber, (llamada, empleado) => llamada );
            //Obtengo la suma de llamdas del departamento
            int result = paso2.Aggregate<PhoneCall, int>(0, (semilla, llamada) => semilla + llamada.Seconds);
            //Imprimo la solucion
            Console.WriteLine("Calls Computer Science: {0}", result);
        }
        private void Query3()
        {
            System.Console.WriteLine("Query3");
            //Obtengo la lista de string formado por el nombre del departamento y el total de segundos de las llamdas
            IEnumerable<String> paso1 = model.Departments.Aggregate<Department, List<String>>(new List<String>(), 
                (semilla, departamentos) => { semilla.Add("Departamento="+departamentos.Name+
                    ", Duración="+(model.PhoneCalls.Join<PhoneCall, Employee, String, PhoneCall>(departamentos.Employees, 
                    (x) => x.SourceNumber, (x) => x.TelephoneNumber, (llamada, empleado) => llamada ).Aggregate<PhoneCall, int>(0, 
                    (semilla2, llamada) => semilla2 + llamada.Seconds))); return semilla; });
            //Imprimo la solucion
            Show(paso1);
        }
        private void Query4()
        {
            System.Console.WriteLine("Query4");
            //Lista de los empleados más jovenes
            IEnumerable<Employee> paso1 = model.Employees.Aggregate<Employee, List<Employee>>(new List<Employee>(),
                (lista, empleado) => { if(lista.Count() == 0){ lista.Add(empleado); } 
                                       else{
                                        if(lista[0].Age == empleado.Age){
                                            lista.Add(empleado);
                                        }
                                        else if(lista[0].Age > empleado.Age){
                                            lista = new List<Employee>();
                                            lista.Add(empleado);
                                        }
                                       }
                                       return lista;
                });
            //Obtengo los datos pedidos y los meto en una lista de string
            List<String> paso2 = paso1.Aggregate<Employee, List<String>>(new List<String>(),
                (lista, empleado) => { lista.Add("Departamento: " + empleado.Department.Name + ", nombre: " + empleado.Name + ", edad: " + empleado.Age); return lista; });
            Show(paso2);
        }
        private void Query5()
        {
            System.Console.WriteLine("Query5");
            //Saco la suma de minutos de cada departamento
            IEnumerable<KeyValuePair<String, int>> paso1 = model.Departments.Aggregate<Department, Dictionary<String, int>>(new Dictionary<String, int>(),
                (semilla, departamentos) =>
                {
                    semilla.Add(departamentos.Name,
                        (model.PhoneCalls.Join<PhoneCall, Employee, String, PhoneCall>(departamentos.Employees,
                        (x) => x.SourceNumber, (x) => x.TelephoneNumber, (llamada, empleado) => llamada).Aggregate<PhoneCall, int>(0,
                        (semilla2, llamada) => semilla2 + llamada.Seconds))); return semilla;
                });
            //Me quedo con el mayor
            KeyValuePair<String, int> paso2 = paso1.Aggregate<KeyValuePair<String, int>>((valor1, valor2) => valor1.Value > valor2.Value ? valor1 : valor2);
            //Imprimo la solucion
            System.Console.WriteLine("Departamento=" + paso2.Key + ", Duración=" + paso2.Value);
        }
    }

}