package logica;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pedido {
	
	private List<Articulo> articulosPedido = null;
	
	public Pedido(){
		articulosPedido = new ArrayList<Articulo>();
	}
	
	public void add(Articulo articulo, int unidades){
		Articulo articuloEnPedido = null;
		Iterator<Articulo> iterador = articulosPedido.iterator();
		
		while (iterador.hasNext()){
			Articulo a = iterador.next();
			if (a.getCodigo().equals(articulo.getCodigo())){
					articuloEnPedido = a;
			}
		}
		if (articuloEnPedido == null){
			articulo.setUnidades(unidades);
			articulosPedido.add(articulo);
		}
		else{
			int totalUnidades = articuloEnPedido.getUnidades() + unidades;
			articuloEnPedido.setUnidades(totalUnidades);
		}
	}
	
	public float calcularTotalSinIva(){
		Articulo articulo = null;
		float total = 0.0f;
				     
		for (int i=0; i<articulosPedido.size();i++){
				articulo = articulosPedido.get(i);
				if (articulo !=null){
					if(articulo.getUnidades() >= 10) {
						total += (articulo.getPrecio()* articulo.getUnidades()) - 
								(articulo.getPrecio()* articulo.getUnidades())*0.15;
					}
					else {
						total += articulo.getPrecio()* articulo.getUnidades();
					}
				}
			}
		return total;
	}
	
	public void grabarPedido(String nombreFichero){
		try {
		        BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + nombreFichero + ".dat"));
		        String linea = articulosPedido.toString();
		        fichero.write(linea);
		        fichero.close();
			}

		catch (FileNotFoundException fnfe) {
		      System.out.println("El archivo no se ha podido guardar");
		    }
		catch (IOException ioe) {
		      new RuntimeException("Error de entrada/salida.");
		}
	  }

	public void inicializar(){
		articulosPedido.clear();
	}
}

