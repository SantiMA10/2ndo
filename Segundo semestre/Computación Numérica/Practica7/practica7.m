%% Ejercicio 1
clc
[g,gs]=wavread('flauta_noise.wav');

N=length(g);

G=fft(g)/sqrt(N);
Q=abs(G).^2;

% En la transformada, eliminamos los elementos con módulo al cuadrado menores que 10

GT=G.*(Q > 100);

% Obtenemos la antitrasformada del espectro de potencias "limpiado"

gT=ifft(GT)*sqrt(N);

wavwrite(gT,gs,'santiago.wav')

%% Ejercicio 2
format long
clc, clear, close all

f=imread('cameraman.tif');
f=double(f);
[M,N]=size(f);
r = 1/200;

figure,imagesc(f),colormap(gray),axis image
title('Original')

F=fft2(f)/sqrt(M*N);

minF = min(min(F))
maxF = max(max(F))

cF=fftshift(F);
aF=abs(cF);

h=@(T) (nnz(aF >=T)-r*N*M);

h_t=[];
for T=minF:200
    h_t=[h_t h(T)];
end
figure, plot(minF:200,h_t,[minF,200],[0,0]), ylabel('Función h'), xlabel('T'), axis xy
title('Número de elementos no nulos en la matriz aF para un umbral T')

aux = 0;
for T=minF:1:maxF
    if(h(T) < 0)
        aux = T;
        break;
    end
end
auxb = real(biseccion2(h, minF, maxF, 0.1))
compresion = (N*M)/nnz(abs(F)>=T)

c=F.*(abs(F)>=T);
fM=real(ifft2(c))*sqrt(M*N);
figure, imagesc(fM),colormap(gray),axis image
title('Comprimida')
f_im=fM*255/max(max(fM));                % Reescalamos los valores a [0 255]
f_sal=uint8(f_im);                       % Transformamos en enteros sin signo de 8 bits
imwrite(f_sal,'cameraman_comprimida.tif') % Guardamos como jpg