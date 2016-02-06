%% Ejercicio 1
clear, clc, close all

I=imread('cameraman.tif');

u0=im2double(I);
u0=u0+0.05*randn(size(u0));
u0=(u0-min(min(u0)))/(max(max(u0))-min(min(u0)));

tau=0.1;
lambda=2;
for tau=0:0.025:0.1
    u=u0;
    for k=1:20
        [ux,uy]=gradient(u);
        u=u-tau*(u-u0-lambda*divergence(ux,uy));
        u=(u-min(min(u)))/(max(max(u))-min(min(u)));
    end
    dif = norm(u-u0)/norm(u0);
    
    figure, imshow(u)
    title(['diferencia= ',num2str(dif),' para \tau= ',num2str(tau)])
end

%% Ejercicio 2

clear, clc, close all

I=imread('cameraman.tif');

u0=im2double(I);
u0=u0+0.05*randn(size(u0));
u0=(u0-min(min(u0)))/(max(max(u0))-min(min(u0)));

tau=0.025;
lambda=2;
ua = u0;
tol = 0.01;
error = 1;

while error 
    u = u0;
    for k=1:20
        [ux,uy]=gradient(u);
        u=u-tau*(u-u0-lambda*divergence(ux,uy));
        u=(u-min(min(u)))/(max(max(u))-min(min(u)));
    end
    error = norm(u-ua)/norm(u) > tol;
    er =  norm(u-ua)/norm(u)
    ua = u;
    tau = tau + 0.025;
end

figure, imshow(u)
title('Resultado')

%% Ejercicio 3
clear, clc, close all

I=imread('cameraman.tif');

u0=im2double(I);
u0=u0+0.05*randn(size(u0));
u0=(u0-min(min(u0)))/(max(max(u0))-min(min(u0)));

tau=0.1;
lambda=0.1;
u=u0;
e = 0.001;
for k=1:20
    [ux,uy]=gradient(u);
    for i=1:length(u)
        for j=1:length(u)
            Ux(i,j) = ux(i,j)/sqrt(e^2+ux(i,j)^2+uy(i,j)^2);
            Uy(i,j) = uy(i,j)/sqrt(e^2+ux(i,j)^2+uy(i,j)^2);
        end
    end
    u=u-tau*(u-u0-lambda*divergence(Ux,Uy));
    u=(u-min(min(u)))/(max(max(u))-min(min(u)));
end
figure,imshow(u0)
figure,imshow(u)
for k=1:20
    [ux,uy]=gradient(u);
    Ux = ux./sqrt(e^2+ux.^2+uy.^2);
    Uy = uy./sqrt(e^2+ux.^2+uy.^2);
    u=u-tau*(u-u0-lambda*divergence(Ux,Uy));
    u=(u-min(min(u)))/(max(max(u))-min(min(u)));
end
figure,imshow(u)
