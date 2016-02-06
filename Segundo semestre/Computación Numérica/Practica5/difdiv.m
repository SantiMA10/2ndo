function a=difdiv(x,y)
n = length(x);
a = [y'];
for j=2:n
    aux = zeros(1,n);
    for i=1:(n+1-j)
        aux(1,i) = (a(i,j-1)-a(i+1, j-1))/(x(i)-x(i+j-1));
    end
    a = [a, aux'];
end