package modulo.util;

import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.imgscalr.Scalr;

public class ImageUtil {
	
	public static void adaptarImagen(Component componente, String ruta){
		if(componente instanceof JButton){
			try{
				JButton boton = (JButton) componente;
				BufferedImage icono = ImageIO.read(ImageUtil.class.getResource(ruta));
				boton.setIcon(new ImageIcon(Scalr.resize(icono, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC,
			               boton.getWidth(),  boton.getHeight())));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(componente instanceof JLabel){
			try{
				JLabel label = (JLabel) componente;
				BufferedImage icono = ImageIO.read(ImageUtil.class.getResource(ruta));
				label.setIcon(new ImageIcon(Scalr.resize(icono, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_HEIGHT, 
						label.getWidth(),  label.getHeight())));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static ImageIcon redimensionarImagen(String ruta, int wigth, int height){
		try{
			BufferedImage icono = ImageIO.read(ImageUtil.class.getResource(ruta));
			return new ImageIcon(Scalr.resize(icono, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC, 
					wigth,  height));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
