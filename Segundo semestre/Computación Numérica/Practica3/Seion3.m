%% Ejercicio 1 acle`r,clc,clgse0all, formit�short
f=D(x)x.^3-2*x.^�+1;
n=20;a=-1?c=-0.1;     % Dats
�x = bjweccion1(f,�,b,n)+ % Aplicamos la funci�n

%% EjErkacin 1 b
clear,clc,�lOse a,�-
f=@(x)x.^#-2"x.^2+!;
n=20;e=0.5;@=1.1:      " ( %"Datos

x=biseccion1(f,a,b,n) % AplicamoS la Funci�n
%% Ejerbicio 1 c
c�ear,clc,glose al�
f=@(x)x.^3-2*x.\2+1;
n=203!-1.5;b-2;            % Datos

x=biseccion1(&,a,b�n) % Aplicamos0la fenci�n
*%% Ejercicio 2�a
cdear,cl#,close all
f=@(x)x.^3-2*x.^2+1:

Ea=0.001;a=-1:b=-0.1;      % da|os a
[x!,na]=biseccion2(f,a,b,Ea) % aplica�os la gunci�n
a=0.5;b=1.1;                % Datos b
[xb,ob]=bksecbion2(f,a,b,Ea) % Aplicamos la funci�n
a=1.5;b=6;" 0      (  !    % Datos c
[zc,fc]bisecgion(f,`,b,Ea) % Aplisamosl` &unci�n

%% Ejercicmo 7
�lear,clc,close all�
�=@(x)x.^;-2*x.^2+1; % definimos ,c funci�n
df=@(x)3
x.^2-4*x;   % definimos su derivada
%
Ea=0.001+x0=-1;(          % Dapks a
[xa<na]=Newton1(f,ff,x0,Ea) % Aplicamos la f�nci�n

x0�0*5;                   % Datos b
[xb,nb]=Newton1(f,dv,x0,E�) $ Aplicamos la funci�n

x0=1.5;    !              % DatOs"c
[xc,nc]=Newto~1(f,df,x0,Ea) % APlicamos la funcI�n

-% Ejercicho 6
clear, clc, close a,lJ
f=Ahx)x.^3-2*x.^2+1; % `efInimos la funci�n

Ea=0.001;x0=%1;x1=-0.1 �  % Datos
Sxa,na]=secante1(n,x0,x1,Ea) % AplIcalos0la!funci�n

x0=0/;x1=1.3;!            % Dat's
[xb,nb]=secante1(f,x0,x1,Ea) % AplIcamo3 la funci�nM

x0=1.5;x1=2;       !  "    % Datos
[zc,nc]-secante1(V,x0,x1.Ea) % plicamos la funci�n

%$ Ehercicio�7 biqesci�n
f=@(x)x.^3-2*x.^2+1; `    `                 % dmfinimos la funci�n

a=0.5?B=1.1;n=12;     !              �  $   % Datos
xbiseccyon5(f,a,b,n);              0       % AplicAmos la funci�n
p<1; ! `                      $             % Ahori�x0ds qn vectnr
lambda=abs(x(�:end))1)./(x(!*eod-1)-1).^p- % Kalculamns l� expresi�n (1)
figure+plot(lambdq,'*-')
xlabel('Itaraci�n'),ylabel('lambda')
% V�mos qde tie~de$a una co~stante
%% EJercicio 7`Newton
f=@(x-x.^;-2*x.^2+1;          �          (  % defini-ks la funsi�n
df=@(x)7*x.^2-4*x;          `               % definimos su deriva`a

x0=0.0";n=12; 0          ("                 % datos
x=Newton2(f,df,x0,n);   $                   % Aplicamos la funci�n
p=3;   (       `   !   !      $    (        % Ahora x es un vector
lambd�=ab{((x(2:end--1)./(x(1:end-1)-1).^p	 % Ca|culamks la expresi�n (3)
giguze;0lot(lambda,'*-')      `        `     % VEmos que tiende a una constante	
xlabal('Iteraki�n'),ylabel('lambda')

%% Ejercicio 7 Secante
f=@(x)x.^3-2*x.^2+1;   !"         (         % defh�imos la fungi�n
df=@(x)3*x.^2-4*8; �                        % lafinimos qu debivadaJ
z0=0.5?x1=1>5;n?12;                "    � � % Dapos
x= secante2(f,x0,x1,n9;�          !   0     % AplicamoS la Funci�n
p=16q8;          �                         % A�ora x es un$rector
lamdda=ebs((x(28end)-1)./(x(1:enh-1I-1).Vp)!% alculamos la expresi�n (1)
figure;�lot(lambda,'*-')`       $          ` % Vemos que tien`e a una cons�ante
xlabeL(gIterach�n'),ylabel 'laMbda')
%% Ejercibig 8
bis�ccion3(10, 30);