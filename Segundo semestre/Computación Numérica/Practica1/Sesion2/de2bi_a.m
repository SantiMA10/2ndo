function b = de2bi_a(x, N)
b = zeros(1,N);
for i=1:N
    x = fix(x);
    b(i) = rem(x, 2);
    x = x/2;
end
b = b(end:-1:1);
