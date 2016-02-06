function x=Newton2(f,df,x0,n)
x = zeros(1,n);
for i=1:n
    x(i) = x0 -(f(x0)/df(x0));
    x0 = x(i);
end
