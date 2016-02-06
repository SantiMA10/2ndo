package alg.util;

public class Cancion implements Comparable<Cancion>{
	
	public String id;
	public int duracion;
	public int puntuacion;
	
	public Cancion(String id, String duracion, int puntuacion){
		this.id = id;
		this.duracion = Integer.parseInt(duracion.split(":")[0])*60 + 
				Integer.parseInt(duracion.split(":")[1]);
		this.puntuacion = puntuacion;
	}
	
	public String toString(){
		return "id: "+id + " - duracion: " + duracion + " segundos - puntuacion: " + puntuacion;
	}

	@Override
	public int compareTo(Cancion o) {
		if(puntuacion/duracion > o.puntuacion/o.duracion){
			return -1;
		}
		else{
			return 1;
		}
	}

}
