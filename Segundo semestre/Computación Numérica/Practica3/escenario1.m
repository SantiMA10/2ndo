function [error,tirax,tiray,tirat] = escenario1(v,alfa,pinta)

if nargin<3, pinta = true; end

inicio = [0 0];
papelera = [20 0];



% Escenario 1
Vx = @(t,x,y) 50*cos(2*pi*t.*y.^1/2); Vy = @(t,x,y) y.*0;

k = 0.5;
g = 9.8;

F = @(t,x,y) [x(2) -k*x(2)+Vx(t,x(1),y(1)); y(2) -g-k*y(2)+Vy(t,x(1),y(1))];

X = 0; Y = 0;
dX = v*cos(alfa*pi/180);
dY = v*sin(alfa*pi/180);

X = [0 dX; 0 dY];

tirax = 0;
tiray = 0;
tirat = 0;

h = 0.02;

t = 0;
while Y(1)>=0
    nt = t+h;
    NX = X+h*F(nt,X(1,:),X(2,:));
    if NX(2,1)<0, if X(2,1)<1e-5; break; else, h = h/2; continue; end; end
    X = NX;
    t = nt;
    tirat = [tirat t];
    tirax = [tirax, X(1,1)];
    tiray = [tiray, X(2,1)];
end



sol = [tirax(end) tiray(end)];
error = sol(1)-papelera(1);

if pinta, dibuja(tirax,tiray,tirat,papelera,inicio,Vx,Vy); end
end

function dibuja(tirax,tiray,tirat,papelera,inicio,Vx,Vy)

figure(1), clf

xm = min(tirax); xM = max(tirax);
ym = 0; yM = max(tiray);
xm = 5*round(xm/5-.5); xM = max(5*round(xM/5+.5),papelera(1)+5);
ym = 0; yM = 5*round(yM/5+.5);
longitud = 50;
Long = (tirax(2:end)-tirax(1:end-1)).^2+(tiray(2:end)-tiray(1:end-1)).^2;
Long = [0 sqrt(Long)]; Long = cumsum(Long);

hold on
ptl = plot(inicio(1),inicio(2),'c-');%,'linewidth',1);
ptestela = plot(inicio(1),inicio(2),'-');%,'linewidth',2);

pt = plot(inicio(1),inicio(2),'g.','markersize',25);
plot(papelera(1),papelera(2),'ro');

[mx,my] = meshgrid(linspace(xm,xM,25),linspace(ym,yM,25));
qv = quiver(mx,my,Vx(0,mx,my),Vy(0,mx,my),1/3);
set(qv,'color',[1 .8 0]);
axis([xm xM ym yM])

t = 0; tic;
while t<max(tirat)
    t = toc;
    L = sum(tirat<=t);
    ttx = tirax(1:L); tty = tiray(1:L);
    set(pt,'xdata',tirax(L),'ydata',tiray(L));
    u = find(Long(L)-Long(1:L)<=longitud);
    set(ptestela,'xdata',ttx(u),'ydata',tty(u));
    set(ptl,'xdata',tirax(1:L),'ydata',tiray(1:L));
    set(qv,'udata',Vx(t,mx,my),'vdata',Vy(t,mx,my));
    drawnow
end
end