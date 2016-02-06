function A=Ejercicio4(I,x0,y0)

I=imread('einstein_bw.jpg');
I=im2double(I);

m = size(I,1);
n = size(I,2);
[x,y] = meshgrid(1:n,1:m);                  

d = @(x,y)sqrt((x-x0).^2+(y-y0).^2);

p = 0.01*d(x,y).*(x-x0)+x;
q = 0.01*d(x,y).*(y-y0)+y;
          
Itrans=interp2(x,y,I,p,q);        

[fil, col] = find(~isnan(Itrans));
A=Itrans(min(fil):max(fil),min(col):max(col),:);
