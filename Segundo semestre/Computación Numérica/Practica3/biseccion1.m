function x=biseccion1(f,a,b,n)
for i=1:n
    x = ((a+b)/2);
    if ((f(a)*f(x)) < 0)
        b = x;
    else
        a = x;
    end
end