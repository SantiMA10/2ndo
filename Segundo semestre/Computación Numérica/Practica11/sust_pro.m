function x=sust_pro(L, b)
n = length(b);
x = zeros(n,1);
for i=1:n
    suma = b(i);
    for j=1:n
        if(j < i)
            suma = suma - L(i,j)*x(j);
        end
        x(i) = suma/L(i,i);
    end
end