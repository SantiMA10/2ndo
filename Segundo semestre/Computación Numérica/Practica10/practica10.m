%% Ejercicio 1
clear, clc, close all
f=@(x) exp(-x.^2);
Lf=@(x)(4*x.^2-2).*exp(-x.^2);

h=0.05;
x=-1:h:1;

K = (1/h^2)*[1 -2 1];
F = f(x);
FA = [ 0 F 0 ];

Lz=conv(FA, K, 'valid');
figure
plot(x,Lz,'g','linewidth',2), hold on
title('(1) Laplaciano')
plot(x,Lf(x),'r')

Lz2 = Lz;
Lz2(1) = (1/h^2)*(2*f(-1)-5*f(-1+h)+4*f(-1+2*h)-f(-1+3*h));
Lz2(length(Lz)) = (1/h^2)*(2*f(1)-5*f(1-h)+4*f(1-2*h)-f(1-3*h));

figure
plot(x,Lz2,'g','linewidth',2), hold on
title('(2) Laplaciano')
plot(x,Lf(x),'r')

Lz3=4*del2(F,h);

figure
plot(x,Lz3,'g','linewidth',2), hold on
title('(3) Laplaciano')
plot(x,Lf(x),'r')

%% Ejercicio 2
clear, clc, close all

% Imagen
I0=imread('coins.png');
I0=im2double(I0);
% figure
% imagesc(I0),axis image,colormap('gray')
% title('Imagen','FontSize',14)

% Imagen con ruido
ruido=0.5;
I=I0+ruido*randn(size(I0));
% figure
% imagesc(I),axis image,colormap('gray')
% title('Imagen con ruido','FontSize',14)

% Desenfoque
n=3;
kS=ones(n,n)/n^2; % kernel de desenfoque de orden n
I1=I;
for k=1:75
    I2=conv2(I1,kS,'same');
    I1=I2;
end
% figure
% imagesc(I2),axis image,colormap('gray')
% title('Imagen desenfocada','FontSize',14)

I2=I2.*(I2>0.50);

kx=0.5.*[0 0 0; -1 0 1; 0 0 0]
DxI=conv2(I2,kx,'same');

ky=0.5.*[0 -1 0; 0 0 0; 0 1 0]
DyI=conv2(I2,ky,'same');

DI = sqrt((DxI.^2)+(DyI.^2));

umbral=0.0001;
ind=find(abs(DI)<umbral);
Z=ones(size(I2));
Z(ind)=0;

dibuja_contornos(I,Z,0)

