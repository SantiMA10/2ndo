%% Ejemplo
clear, clc, close all
a = imread('lena_gray_512.tif');
ad = double(a);
ad_im = im2double(a);
figure,imshow(ad);
lena_eye=a(252:284,318:350);
figure,imagesc(lena_eye)
colormap(gray)
axis image

%% Ejercicio 1
clear, clc, close all

c = imread('cameraman.tif');
ch=c(75:165, 180:270);
figure,imagesc(ch)
colormap(gray)
axis image
title('Cabeza cameraman')
double_ch = im2double(ch);

%% Ejercicio 2
clear, clc, close all

a = imread('lena_gray_512.tif');
ad = im2double(a);
[m, n] = size(a);
filtro = zeros(m, n);
cy = m/2;
cx = n/2;
for i=1:n
    for j=1:m
        if(((i - cx)^2 + (j - cy)^2) < 150^2)
            filtro(i, j) = 1;
        end 
    end
end
final = filtro.*ad;
figure,imagesc(final)
colormap(gray)
title('Lena con filtro1')
axis image

%% Ejercicio 3
clear, clc, close all

a = imread('lena_gray_512.tif');
ad = im2double(a);
[m, n] = size(a);
aux = linspace(0,1,m);
filtro = repmat(aux', 1, n);
final = filtro.*ad;
figure,imagesc(final)
colormap(gray)
title('Lena con filtro2')
axis image

%% Ejercicio 4
clear, clc, close all
n = 500;
m = 500;
filtro = zeros(n, m);
cx = 0:50:500;
cy = 0:50:500;
for i=1:n
    for j=1:m
        for k=1:11
            for l=1:11
                if(((i - cx(k))^2 + (j - cy(l))^2) < 10^2)
                    filtro(i, j) = 1;
                end
            end
        end 
    end
end
figure,imagesc(filtro)
colormap(gray)
title('500x500 R=10')
axis image
imwrite(filtro, 'lunares.png')

%% Ejercicio 5
clear, clc, close all
n = 450;
m = 450;
cNegro  = double(zeros(n/9, m/9));
cBlanco = double(ones(n/9, m/9));
cGris   = double(ones(n/9, m/9))*(0.5);

NegroGris = [cNegro, cGris];
GrisBlanco = [cGris, cBlanco];

filaNegroGris = [repmat(NegroGris, 1 , 4), cNegro];
filaGrisBlanco = [repmat(GrisBlanco, 1 , 4), cGris];

patron = [filaNegroGris; filaGrisBlanco];

final = [repmat(patron, 4, 1); filaNegroGris];
figure,imshow(final)
title('450x450')
axis image
imwrite(NegroGris, 'cuadraditos.png')

%% Ejercicio 6
clear, clc, close all

a = imread('lena_gray_512.tif');
ad = im2double(a);
[m, n] = size(a);
filtro = zeros(m, n);
cy = m/2;
cx = n/2;
degradado = linspace(0,1,m/2);

for i=1:n
    for j=1:m
        if(((i - cx)^2 + (j - cy)^2) < ((n/2)-15)^2)       
            filtro(i, j) = degradado();
        end
    end
end
final = filtro.*ad;
figure,imagesc(filtro)
colormap(gray)
title('Lena con filtro3')
axis image