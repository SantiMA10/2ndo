function x=sust_re(U,b)
n = length(b);
x = zeros(n,1);
for i=n:-1:1
    suma = b(i);
    for j=n:-1:1
        if(j > i)
            suma = suma - U(i,j)*x(j);
        end
        x(i) = suma/U(i,i);
    end
end