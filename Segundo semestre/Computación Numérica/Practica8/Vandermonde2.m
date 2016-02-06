function V=Vandermonde2(x, g)
n = length(x);
for i=1:n
    for j=0:g
        if j == 0
            aux = [x(i)^j];
        else
            aux = [aux, x(i)^j];
        end
    end
    if i == 1
        V = [aux];
    else
        V = [V; aux];
    end
end