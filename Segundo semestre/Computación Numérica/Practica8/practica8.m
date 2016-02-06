%% Ejercicio 1
clear, clc, close all

x=[-1,-0.5,0,0.5,1];
y=cos(x);
V=Vandermonde2(x,4)

a=V'*V\V'*y'
aa=a(end:-1:1)'

xx=linspace(min(x),max(x));
yy=polyval(aa,xx);
plot(x,y,'o',xx,yy,'r-')
legend('puntos','funci�n aproximada','location','south')

%% Ejercicio 2
clear, clc, close all

gr=4;                          % grado del polinomio
A=zeros(gr+1,gr+1);            % matriz de coeficientes
b=zeros(gr+1,1);               % t�rmino independiente
for i=1:gr+1
    for j=1:gr+1
        exponente=i+j-2;
        f=@(x)x.^exponente;
        A(i,j)=quad(f,-1,1);   % integramos num�ricamente
    end
    g=@(x)(x.^(i-1)).*(1.0./sqrt(x.^2+1.0)-log(x+2.0).*exp(x.^2));
    b(i,1)=quad(g,-1,1);
end
sol=A\b;
sol=sol(end:-1:1)'
xx=linspace(-1,1);
funcion = (1.0./sqrt(xx.^2+1.0)-log(xx+2.0).*exp(xx.^2));
yy=polyval(sol,xx);
figure
plot(xx,funcion,xx,yy,'r-')
legend('funci�n exacta','funci�n aproximada','location','south') 


gr=4;                          % grado del polinomio
A=zeros(gr+1,gr+1);            % matriz de coeficientes
b=zeros(gr+1,1);               % t�rmino independiente
for i=1:gr+1
    for j=1:gr+1
        exponente=i+j-2;
        f=@(x)x.^exponente;
        A(i,j)=quad(f,-1,1);   % integramos num�ricamente
    end
    g=@(x)(x.^(i-1)).*(-x.*1.0./(x.^2+1.0).^(3.0./2.0)-exp(x.^2)./(x+2.0)-x.*log(x+2.0).*exp(x.^2).*2.0);
    b(i,1)=quad(g,-1,1);
end
sol=A\b;
sol=sol(end:-1:1)'
xx=linspace(-1,1);
funcion = (-xx.*1.0./(xx.^2+1.0).^(3.0./2.0)-exp(xx.^2)./(xx+2.0)-xx.*log(xx+2.0).*exp(xx.^2).*2.0);
yy=polyval(sol,xx);
figure
plot(xx,funcion,xx,yy,'r-')
legend('derivada exacta','derivada aproximada','location','south') 

%% Ejercicio 3
clear, clc, close all

%0
d=@(x)(1/2);
n=@(x)(1.0./sqrt(x.^2+1.0)-log(x+2.0).*exp(x.^2));
sol(1)=quad(n,-1,1)/2;
%1
d=@(x)(x.^2);
n=@(x)x.*(1.0./sqrt(x.^2+1.0)-log(x+2.0).*exp(x.^2));
sol(2)=quad(n,-1,1)/quad(d,-1,1);
%2
d=@(x)1/4*(1-3*x.^2).^2;
n=@(x)1/2*(1-3*x.^2).*(1.0./sqrt(x.^2+1.0)-log(x+2.0).*exp(x.^2));
sol(3)=quad(n,-1,1)/quad(d,-1,1);
%3
d=@(x)1/4*(5*x.^3-3*x).^2;
n=@(x)1/2*(5*x.^3-3*x).*(1.0./sqrt(x.^2+1.0)-log(x+2.0).*exp(x.^2));
sol(4)=quad(n,-1,1)/quad(d,-1,1);
%4
d=@(x)1/64*(35*x.^4-30*x.^2+3).^2;
n=@(x)1/8*(35*x.^4-30*x.^2+3).*(1.0./sqrt(x.^2+1.0)-log(x+2.0).*exp(x.^2));
sol(5)=quad(n,-1,1)/quad(d,-1,1);
a=sol
%
pol=@(x)sol(1)+sol(2)*x+sol(3)*1/2*(1-3*x.^2)+sol(4)*1/2*(5*x.^3-3*x)+sol(5)*1/8*(35*x.^4-30*x.^2+3);
%
xx=linspace(-1,1);
funcion = (1.0./sqrt(xx.^2+1.0)-log(xx+2.0).*exp(xx.^2));
%
figure
plot(xx,funcion,xx,pol(xx),'r-')
legend('funci�n exacta','funci�n aproximada','location','south')


%0
d=@(x)(1/2);
n=@(x)((-x.*1.0./(x.^2+1.0).^(3.0./2.0)-exp(x.^2)./(x+2.0)-x.*log(x+2.0).*exp(x.^2).*2.0));
sol(1)=quad(n,-1,1)/2;
%1
d=@(x)(x.^2);
n=@(x)x.*((-x.*1.0./(x.^2+1.0).^(3.0./2.0)-exp(x.^2)./(x+2.0)-x.*log(x+2.0).*exp(x.^2).*2.0));
sol(2)=quad(n,-1,1)/quad(d,-1,1);
%2
d=@(x)1/4*(1-3*x.^2).^2;
n=@(x)1/2*(1-3*x.^2).*((-x.*1.0./(x.^2+1.0).^(3.0./2.0)-exp(x.^2)./(x+2.0)-x.*log(x+2.0).*exp(x.^2).*2.0));
sol(3)=quad(n,-1,1)/quad(d,-1,1);
%3
d=@(x)1/4*(5*x.^3-3*x).^2;
n=@(x)1/2*(5*x.^3-3*x).*((-x.*1.0./(x.^2+1.0).^(3.0./2.0)-exp(x.^2)./(x+2.0)-x.*log(x+2.0).*exp(x.^2).*2.0));
sol(4)=quad(n,-1,1)/quad(d,-1,1);
%4
d=@(x)1/64*(35*x.^4-30*x.^2+3).^2;
n=@(x)1/8*(35*x.^4-30*x.^2+3).*((-x.*1.0./(x.^2+1.0).^(3.0./2.0)-exp(x.^2)./(x+2.0)-x.*log(x+2.0).*exp(x.^2).*2.0));
sol(5)=quad(n,-1,1)/quad(d,-1,1);
a=sol
%
pol=@(x)sol(1)+sol(2)*x+sol(3)*1/2*(1-3*x.^2)+sol(4)*1/2*(5*x.^3-3*x)+sol(5)*1/8*(35*x.^4-30*x.^2+3);
%
xx=linspace(-1,1);
funcion =@(x)(-x.*1.0./(x.^2+1.0).^(3.0./2.0)-exp(x.^2)./(x+2.0)-x.*log(x+2.0).*exp(x.^2).*2.0);
%
figure
plot(xx,funcion(xx),xx,pol(xx),'r-')
legend('derivada exacta','derivada aproximada','location','south')

%% Ejercicio 4
clear, clc, close all

f=@(x)((x-pi).^2);         % funcion
aa=0;bb=2*pi;       % intervalo
n=6;            % numero de iteraciones

T=bb-aa;        % periodo
f1=@(x)f(x+T);   % definimos la funci�n f en el periodo anterior
f2=@(x)f(x-T);   % definimos la funci�n f en el periodo siguiente
%
a=zeros(1,n);
b=zeros(1,n);
xx=linspace(aa,bb);
xx1=linspace(aa-T,bb-T);
xx2=linspace(aa+T,bb+T);
xxt=linspace(aa-T,bb+T,300);
a0=quad(f,aa,bb)/T;
s=a0*ones(1,length(xxt));
for k=1:n
    figure(k)
    hold on
    % calculamos ak y bk
    fun1=@(x)cos(2*pi*k*x/T).*f(x);
    fun2=@(x)sin(2*pi*k*x/T).*f(x);
    a(k)=2*quad(fun1,aa,bb)/T;
    b(k)=2*quad(fun2,aa,bb)/T;
    % calculamos el t�rmino y lo sumamos a la serie
    s1=a(k)*cos(2*pi*k*xxt/T)+b(k)*sin(2*pi*k*xxt/T);
    s=s+s1;
    % dibujamos la funci�n
    plot(xx,f(xx))
    % dibujamos los t�rminos de la serie de Fourier
    plot(xxt,s,'r')
    legend('f(x)','Serie')
    % dibujamos otros dos periodos de la funci�n
    plot(xx1,f1(xx-T))
    plot(xx2,f2(xx+T))
    title(['f(x)=x en [0,3] k=',num2str(k)])
    box on
end
