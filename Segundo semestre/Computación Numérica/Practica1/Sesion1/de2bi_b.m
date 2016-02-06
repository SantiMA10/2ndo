function b = de2bi_b(x, N)
b = zeros(1,N);
for i=1:N
    int = floor(x);
    x = (x-int)*2;
    b(i) = floor(x);
end
