using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sesion7
{
    static public class Class1
    {
        static public IEnumerable<int> ForEach(this IEnumerable<int> v, Action<int> a)
        {
            foreach (int elemento in v)
            {
                a(elemento);
            }
            return v;
        }

    }
}
