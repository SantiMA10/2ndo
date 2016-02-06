function biseccion3(a,b)
Ea = escenario1(a,45);
while (Ea < 0.001)
    x = ((a+b)/2);
    if ((escenario1(a, 45)*escenario1(x, 45)) < 0)
        b = x;
    else
        a = x;
    end
end