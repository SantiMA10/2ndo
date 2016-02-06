%% Ejemplo 1
% x columna y filas
clear, clc, close all

I=imread('cameraman.tif');
I=im2double(I);

s=[2,3];
tform1 = maketform('affine',[s(1) 0 0; 0 s(2) 0; 0 0 1]);       % Escalado
I1 = imtransform(I,tform1);

sh=[0.5 0.2];
tform2 = maketform('affine',[1 sh(1) 0; sh(2) 1 0; 0 0 1]);     % Cizallado
I2 = imtransform(I,tform2);

theta=pi/6;                                                   % Rotaci�n
A=[cos(theta) sin(theta) 0; -sin(theta) cos(theta) 0; 0 0 1];
tform3 = maketform('affine',A);
I3 = imtransform(I,tform3);

figure
imagesc(I),axis image
title('Original','FontSize',18)
colormap(gray)
figure
imagesc(I1),axis image
title('Escalado','FontSize',18)
colormap(gray)
figure
imagesc(I2),axis image
title('Cizallado','FontSize',18)
colormap(gray)
figure
imagesc(I3),axis image
title('Girado','FontSize',18)
colormap(gray)

%% Ejemplo 1
clear, clc, close all

I=imread('tower_bw.jpg');
I=im2double(I);

theta=-pi/24;                                                   % Rotaci�n
A=[cos(theta) sin(theta) 0; -sin(theta) cos(theta) 0; 0 0 1];
tform3 = maketform('affine',A);
I = imtransform(I,tform3);
I2 = I;
I2(:, 375:378) = 1;
I = I(41:437,173:420);

figure
imagesc(I),axis image
title('Enderezado y recortado','FontSize',18)
colormap(gray)

figure
imagesc(I2),axis image
title('Enderezado','FontSize',18)
colormap(gray)

%% Ejercicio 2
clear, clc, close all

I=imread('water.jpg');
I=im2double(I);

m = size(I,1);
n = size(I,2);
[x,y] = meshgrid(1:n,1:m);                  

x0 = fix(n/2);
y0 = fix(m/2);
d = @(x,y)sqrt((x-x0).^2+(y-y0).^2);

theta = 10*exp(-0.1*d(x,y));
p=(x-x0).*cos(theta)+(y-y0).*sin(theta)+x0;   
q=-(x-x0).*sin(theta)+(y-y0).*cos(theta)+y0;

Itrans=zeros(size(I));                
for k=1:3                             
    Ic=I(:,:,k); 
    Ic=interp2(x,y,Ic,p,q);        
    Itrans(:,:,k)=Ic;                 
end

Ifinal=Itrans(9:178,5:270,:);

% figure
% imagesc(I),axis image
% title('Original','FontSize',18)
% 
figure
imagesc(Itrans),axis image
title('Transformada','FontSize',18)

figure
imagesc(Ifinal),axis image
title('Transformada y recortada','FontSize',18)


%% Ejercicio 3
clear, clc, close all

I=imread('einstein_bw.jpg');
I=im2double(I);

m = size(I,1);
n = size(I,2);
[x,y] = meshgrid(1:n,1:m);                  

x0 = fix(n/2);
y0 = fix(m/2);
d = @(x,y)sqrt((x-x0).^2+(y-y0).^2);

theta = 10*exp(-0.1*d(x,y));
p = 0.01*d(x,y).*(x-x0)+x;
q = 0.01*d(x,y).*(y-y0)+y;
          
Itrans=interp2(x,y,I,p,q);        

[fil, col] = find(~isnan(Itrans));
Ifinal=Itrans(min(fil):max(fil),min(col):max(col),:);


figure
imagesc(Itrans),axis image
title('Transformada','FontSize',18)
colormap(gray)

figure
imagesc(Ifinal),axis image
title('Transformada','FontSize',18)
colormap(gray)

%% Ejercicio 4
I=imread('einstein_bw.jpg');
A=Ejercicio4(I,361,593);

figure
imagesc(A),axis image
title('Transformada','FontSize',18)
colormap(gray)

%% Ejercicio 5
clear, clc, close all

I=imread('tower_bw.jpg');
I=im2double(I);

m = size(I,1);
n = size(I,2);
[x,y] = meshgrid(1:n,1:m);                  

x0 = fix(n/2);
y0 = fix(m/2);
d = @(x,y)sqrt((x-x0).^2+(y-y0).^2);

theta = 10*exp(-0.1*d(x,y));
p = x+10*sin(((2*pi)/4)*y);
q = y;
          
Itrans=interp2(x,y,I,p,q);        

% figure
% imagesc(I),axis image
% title('Original','FontSize',18)
% colormap(gray)

figure
imagesc(Itrans),axis image
title('Transformada','FontSize',18)
colormap(gray)