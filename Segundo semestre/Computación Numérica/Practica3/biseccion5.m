function x=biseccion5(f,a,b,n)
x = zeros(1,n);
for i=1:n
    x(i) = ((a+b)/2);
    if ((f(a)*f(x(i))) < 0)
        b = x(i);
    else
        a = x(i);
    end
end