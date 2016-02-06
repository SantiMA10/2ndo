package alg71533682.p4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Devorador {

	public Tarea[] leeFichero(String ruta){
		Tarea[] lista = null;
		try {
			@SuppressWarnings("resource")
			BufferedReader bf = new BufferedReader(new FileReader(ruta));
			int i = 0;
			while(bf.ready()){
				String[] linea = bf.readLine().split("\t");
				if(linea.length == 1){
					lista = new Tarea[Integer.parseInt(linea[0])];
				}
				else{
					lista[i] = new Tarea(Integer.parseInt(linea[0]), Integer.parseInt(linea[1]));
					i++;
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Tarea[] generarAleatorio(int size){
		Tarea[] resultado = new Tarea[size];
		for(int i = 0; i < size; i++){
			resultado[i] = new Tarea((int)(Math.random()*size)+1, (int)(Math.random()*size)+1);
		}
		return resultado;
	}
	
	public int maxBeneficion(Tarea[] t){
		Arrays.sort(t);
		int beneficio = 0, tiempo = 1, max = 0;
		boolean aux = false;
		for(int i = 0; i < t.length; i++){
			if(t[i].tiempo == tiempo && t[i].beneficio > max){
				aux = true;
				max = t[i].beneficio;
			}
			else if(tiempo != t[i].tiempo){
				if(aux){
					aux = false;
					beneficio += max;
					max = 0;
					i--;
				}
				else if (!aux && tiempo < t[i].tiempo){
					beneficio += t[i].beneficio;
				}
				tiempo++;
			}
		}
		
		return beneficio+max;
	}
}
