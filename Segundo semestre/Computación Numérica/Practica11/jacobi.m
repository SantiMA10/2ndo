function [x,c] = jacobi(A,b,tol)

n = length(b);
x0 = zeros(n, 1);
x1 = zeros(n, 1);
c= 1;

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
    x = x1;
    condicion = abs(x1-x0) > tol;
    x0 = x1;
    c = c + 1;
end
