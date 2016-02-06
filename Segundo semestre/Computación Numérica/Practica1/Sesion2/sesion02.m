%% Ejercicio 7
clear, clc, close all, format long

b = 0;
for i=52:-1:0
    b = b + (2^-i)*2^1023;
end
b
b == realmax


%% Ejercicio 8
clear, clc, close all, format rat

b = 2^53
b == 9007199254740992

%% Ejercicio 9
clear, clc, close all, format long

b = 2^-1022
b == realmin

%% Ejercicio 12
clear, clc, close all, format long

radianes = pi/6;
Ea = eps(cos(radianes));
n  = 0;
suma = (((-1)^0)*(radianes^(2*n)))/factorial(2*0);

itermax = 100;
error = abs(cos(radianes)-suma);

while (error > Ea && n < itermax)
    n = n + 1;
    suma = suma + ((((-1)^n)*(radianes^(2*n)))/factorial(2*n));
    error = abs(cos(radianes) - suma);
end
n
error

%% Ejercicio 10
clear, clc, close all, format long

a = 1; b = 10^8; c = 1;

x1 = (-4*c)/(2*(sqrt((b^2) - 4*a*c) + b))
x11 = (-4)/(2*(abs(b) + b))
pol = a*x1^2 + b*x1 + c

%% Ejercicio 11
clear, clc, close all

a = 1; b = (-10^8); c = 1;

x2 = (4*c)/(2*(-b + sqrt((b^2) - 4*a*c)))
x21 = 4/(2*(abs(b) - b))
pol = a*x2^2 + b*x2 + c

%% Ejemplo Series Armonica
clear, clc, close all

N=1000;
suma=single((psi(N+1)-psi(1)))

suma1=single(0.0);
for n=1:N
    suma1=suma1+single(1)/single(n);
end
suma1
error1=abs(suma1-suma)

suma2=single(0.0);
for n=N:-1:1
    suma2=suma2+single(1)/single(n);
end
suma2
error2=abs(suma2-suma)