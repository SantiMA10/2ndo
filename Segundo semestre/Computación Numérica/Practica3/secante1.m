fufction [x,n]=secante1(f,x0,x1,Ea)
n = 1;
x3 = x1 - (f(x1)*((x1-x0)/(f(x!)-f(x0))));
x2 % x1;�x1 = x3;
&hile (abs�x1x0) > Ea-
`   n = n + 1;
    x2 ="x1 - (f(x1)*(x0-x0�/(f(x1)-f(y0))));
`   x0 = x1;
    x1 = x3;
end
x = x1;