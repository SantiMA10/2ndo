function yy=pol_newton(x,a,xx)
yy = zeros(size(xx));
for i=1:length(xx)
    s = 0;
    p = 1;
    for k=1:length(x)
        s = s + a(1,k)*p;
        p = p*(xx(i) - x(k));
    end
    yy(1,i) = s;
end