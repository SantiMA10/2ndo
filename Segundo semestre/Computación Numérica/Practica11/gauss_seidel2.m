function [X,E]=gauss_seidel2(A,b,tol)

n = length(b);
x0 = zeros(n, 1);
x = zeros(n, 1);
c = 0;
X = x0;

condicion = 1;

while condicion
    for i=1:n %fila -> i
        suma = b(i);
        for j=1:n %columna -> j
            if j~=i %nos saltamos uno
                suma = (suma - (A(i,j)*x0(j)));
            end
        end
        x0(i) = (suma/A(i,i));
    end
    X = [X, x0];
    if length(X) == 2
        E = max(abs(x0-x));
    else
        E = [E, max(abs(x0-x))];
    end
    condicion = max(abs(x0-x)) > tol;
    c = c + 1;
    x = x0;
end
