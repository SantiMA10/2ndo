%% Ejercicio 1
clear, clc, close all

x=[2,3,4,5,6];
y=[2,6,5,5,6];

V=Vandermonde(x)

a=V\y'
aa=a(end:-1:1)'

xx=linspace(min(x),max(x));
yy=polyval(aa,xx);   

plot(x,y,'.','markersize',20)       
hold on, plot(xx,yy)

%% Ejercicio 2
clear, clc, close all

x=[2,3,4,5,6];
y=[2,6,5,5,6];

a=difdiv(x,y)

xx=linspace(min(x),max(x));
yy=pol_newton(x,a,xx);
plot(x,y,'.','markersize',10)
hold on,plot(xx,yy)