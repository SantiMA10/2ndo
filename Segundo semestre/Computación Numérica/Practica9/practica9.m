%% Ejercicio 1
clear, clc, close all, format compact

h1 = 0.01;
h2 = 0.001;

f=@(x)exp(x);
dp=@(x,h)((f(x+h)-f(x))/h);
dr=@(x,h)((f(x)-f(x-h))/h);
dc=@(x,h)((f(x+h)-f(x-h))/(2*h));
x = [];
for i=0:h1:1-h1
   x = [ x i ];
end

figure %Grafica con derivadas h1
plot(x,f(x),'b','linewidth',2), hold on
plot(x,dp(x,h1),'m')
plot(x,dr(x,h1),'c')
plot(x,dc(x,h1),'g')

figure %Error en cada putno(absoluto) con h1
plot(x,abs(dp(x,h1)-f(x)),'m'), hold on
plot(x,abs(dr(x,h1)-f(x)),'c')
plot(x,abs(dc(x,h1)-f(x)),'g')

%Error global

ErrorDP = norm(f(x)-dp(x,h1))/norm(f(x))
ErrorDR = norm(f(x)-dr(x,h1))/norm(f(x))
ErrorDC = norm(f(x)-dc(x,h1))/norm(f(x))

%% Ejercicio 2
clear, clc, close all

h1 = 0.01;

f=@(x)(1/x);
df=@(x)(-1./x.^2);
dp=@(x,h)((f(x+h)-f(x))/h);
dr=@(x,h)((f(x)-f(x-h))/h);
dc=@(x,h)((f(x+h)-f(x-h))/(2*h));

x = 0.2:h1:1.2;
n = length(x);

def = [dp(0.2,h1)];
for i=2:n-1
    def = [def dc(x(i), h1)];
end
def = [def dc(1.2, h1)];

ErroGlobal1 = norm(df(x)-def)/norm(df(x))

dp2=@(x,h)(1/(2*h))*(-3*f(x)+4*f(x+h)-f(x+2*h));
dr2=@(x,h)(1/(2*h))*(f(x-2*h)-4*f(x-h)+3*f(x));

def2 = def;

def2(1) = dp2(0.2,h1);

def2(length(def)) = dr2(1.2,h1);

ErroGlobal2 = norm(df(x)-def2)/norm(df(x))


%% Ejercicio 4
clear, clc, close all

hx = 0.1;
hy = 0.1;

f=@(x,y)x.*exp(-x.^2-y.^2);
fxe=@(x,y)(1-2*x.^2).*exp(-x.^2-y.^2);
fye=@(x,y)-2*x.*y.*exp(-x.^2-y.^2);

[x,y]=meshgrid(-2:hx:3,-2:hy:2);
z = f(x,y);

figure
subplot(1,2,1), contour(x,y,z)
subplot(1,2,2), surf(x,y,z)

[fx,fy] = gradient(z,hx,hy);

g = [fx, fy];
ge = [fxe(x,y), fye(x,y)];

error = norm(g-ge)/norm(ge)

figure
contour(x,y,z), hold on
quiver(x,y,fx,fy)

%% Ejercicio 5
clear, clc, close all, format long
hx = 0.1;
hy = 0.1;

f1=@(x,y)x.^2+y.^2;
f2=@(x,y)x.^2-y.^2;
divf=@(x,y)2*x-2*y;

%1
[x,y]=meshgrid(-2:hx:2,-2:hy:2);

div = divergence(x,y,f1(x,y),f2(x,y));

figure
quiver(x,y,f1(x,y),f2(x,y)), hold on
contour(x,y,div)
error1 = norm(divf(x,y)-div)/norm(divf(x,y))
%2
[x2,y2]=meshgrid(-2+hx:hx:2-hx,-2+hy:hy:2-hy);

div2 = divergence(x2,y2,f1(x2,y2),f2(x2,y2));

figure
quiver(x2,y2,f1(x2,y2),f2(x2,y2)), hold on
contour(x2,y2,div2)

%Error
error1 = norm(divf(x,y)-div)/norm(divf(x,y))
error2 = norm(divf(x2,y2)-div2)/norm(divf(x2,y2))





