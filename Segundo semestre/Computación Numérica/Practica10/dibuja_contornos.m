% Muestra los contornos de una función u sobre la imagen I
function dibuja_contornos(I,u,level)
    figure
    imagesc(I),axis image
    if size(I,3)==1
        colormap('gray')
    end
    hold on;
    contour(u, [level level], 'r','LineWidth',1);
    hold off; 
    title('Imagen con contornos','FontSize',14);
    drawnow;
end