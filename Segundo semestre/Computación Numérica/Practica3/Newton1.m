function [x,n]=Newton1(f,df,x0,Ea) 
n = 1;
x1 = x0 - (f(x0)/df(x0));
while (abs(x1-x0) > Ea)
    n = n + 1;
    x0 = x1;
    x1 = x0 -(f(x0)/df(x0));
end
x = x1;