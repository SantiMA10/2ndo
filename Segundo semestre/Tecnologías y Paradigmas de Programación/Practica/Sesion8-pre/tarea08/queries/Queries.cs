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

        private void Query1() {
            var employeesCS = model.Departments.FirstOrDefault((x) => x.Name == "Computer Science").Employees;
            var employeesFS = employeesCS.Where((x) => x.Office.Building.Equals("Faculty of Science"));
            var calls = model.PhoneCalls.Where((x) => x.Seconds >= 60);
            var result = calls.Join(employeesFS, (x) => x.SourceNumber, (x) => x.TelephoneNumber, (x, y) => x.SourceNumber == y.TelephoneNumber ? y : null);
            Console.WriteLine("Employees:");
            Show(result);
        }

        private void Query2()
        {
            var employeesCS = model.Departments.FirstOrDefault((x) => x.Name == "Computer Science").Employees;
            var phoneNumbers = model.PhoneCalls.Join(employeesCS, (x) => x.SourceNumber, (x) => x.TelephoneNumber, (x, y) => x.SourceNumber == y.TelephoneNumber ? x : null);
            var result = phoneNumbers.Aggregate<PhoneCall, int>(0, (x, y) => x+y.Seconds);
            Console.WriteLine("Calls Computer Science: {0}", result);
        }
        private void Query3()
        {
            foreach (Department departamento in model.Departments){
               var phoneNumbers = model.PhoneCalls.Join(departamento.Employees, (x) => x.SourceNumber, (x) => x.TelephoneNumber, (x, y) => x.SourceNumber == y.TelephoneNumber ? x : null);
               var result = phoneNumbers.Aggregate<PhoneCall, int>(0, (x, y) => x+y.Seconds);
               Console.WriteLine("Departamento={0}, Duración={1}", departamento.Name, result);
            }
        }
        private void Query4()
        {
            foreach (Department departamento in model.Departments)
            {
                var minAge = departamento.Employees.Min<Employee>((x) => x.Age);
                var result = departamento.Employees.Where((x) => x.Age == minAge);
                Console.WriteLine("Más joven en {0}", departamento.Name);
                Show(result);
            }
        }
        private void Query5()
        {
            int max = 0;
            Department de = null;
            foreach (Department departamento in model.Departments)
            {
                var phoneNumbers = model.PhoneCalls.Join(departamento.Employees, (x) => x.SourceNumber, (x) => x.TelephoneNumber, (x, y) => x.SourceNumber == y.TelephoneNumber ? x : null);
                var result = phoneNumbers.Aggregate<PhoneCall, int>(0, (x, y) => x + y.Seconds);
                if (max < result)
                {
                    max = result;
                    de = departamento;
                }
            }
            Console.WriteLine("Departamento={0}, Duración={1}", de.Name, max);
        }
    }

}
