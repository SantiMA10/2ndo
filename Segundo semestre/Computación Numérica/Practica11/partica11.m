%% Ejercicio 1
clear, clc, close all
A = [ 5 1 -1 -1; 1 4 -1 1 ; 1 1 -5 -1; 1 1 1 -4];
b = [1;1;1;1];
tol = 1e-4;
[x,c] = jacobi(A,b,tol)


%% Ejercicio 2
clear, clc, close all
A = [ 5 1 -1 -1; 1 4 -1 1 ; 1 1 -5 -1; 1 1 1 -4];
b = [1;1;1;1];
tol = 1e-4;
[x,n] = gauss_seidel(A,b,tol)

m=30;
A1=magic(m);
for i=1:m,A1(i,i)=norm(A1,inf);end
b1=A1*(m:-1:1)';
tol=1e-4;
[x,n]=gauss_seidel(A1,b1,tol);
sol=x'
iteraciones=n

%% Ejercicio 3a
clear, clc, close all

A = [5 2; 3 -2];
b = [19; 5];
tol = 1e-6;

% [Xj, Ej] = jacobi2(A,b,tol);
% [Xg, Eg] = gauss_seidel2(A,b,tol);

% semilogy(Ej,'b*-'), hold on
% semilogy(Eg,'r.-')

% Ejercicio 3b

tol = 1e-2;

[Xj, Ej] = jacobi2(A,b,tol);
[Xg, Eg] = gauss_seidel2(A,b,tol);

figure
plot(Xj(1,:), Xj(2,:), 'b*-'), hold on
plot(Xg(1,:), Xg(2,:), 'r*-')

%% Ejercicio 4
clear, clc, close all

L=[2 0 0;1 2 0;1 1 2];
b=[2 -1 0];
x=sust_pro(L,b')

%% Ejercicio 5
clear, clc, close all

U=[2 1 1;0 2 1;0 0 2];
b=[9 4 4];
x=sust_re(U,b')