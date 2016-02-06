function [X,E] = jacobi2(A,b,tol)

n = length(b);
x0 = zeros(n, 1);
x1 = zeros(n, 1);
X = x0;
c = 0;

condicion = 1;

while condicion 
    for i=1:n %fila -> i
        suma = b(i);
        for j=1:n %columna -> j
            if j~=i %nos saltamos uno
                suma = (suma - (A(i,j)*x0(j)));
            end
        end
        x1(i) = (suma/A(i,i));
    end
    X = [X, x1];
    if length(X) == 2
        E = max(abs(x1-x0));
    else
        E = [E, max(abs(x1-x0))];
    end
    condicion = max(abs(x1-x0)) > tol;
    x0 = x1;
    c = c + 1;
end
