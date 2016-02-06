function [A,b]=aP(g,gr)
A=zeros(gr+1,gr+1);            % matriz de coeficientes
b=zeros(gr+1,1);               % término independiente
for i=1:gr+1
    for j=1:gr+1
        exponente=i+j-2;
        f=@(x)x.^exponente;
        A(i,j)=quad(f,-1,1);   % integramos numéricamente
    end
    g=@(x)(x.^(i-1)).*(1.0./sqrt(x.^2+1.0)-log(x+2.0).*exp(x.^2));
    b(i,1)=quad(g,-1,1);
end