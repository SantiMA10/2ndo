function [x,n]=biseccion2(f,a,b,Ea)
n = 0;
while (abs(b-a) > Ea)
    n = n+1;
    x = ((a+b)/2);
    if ((f(a)*f(x)) < 0)
        b = x;
    else
        a = x;
    end
end