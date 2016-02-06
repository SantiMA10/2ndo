package alg71533682.p6;

public class Cancion {
	
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

}
