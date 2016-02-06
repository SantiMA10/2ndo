package alg71533682.p4;

public class Tarea implements Comparable<Tarea>{
	public int tiempo;
	public int beneficio;
	
	public Tarea(int beneficio, int tiempo){
		this.beneficio = beneficio;
		this.tiempo = tiempo;
	}
	
	
	@Override
	public int compareTo(Tarea t) {
		if(this.tiempo > t.tiempo){
			return 1;
		}
		else if(this.tiempo < t.tiempo){
			return -1;
		}
		else{
			if(this.beneficio < t.beneficio){
				return 1;
			}
			else{
				return -1;
			}
		}
	}

	public String toString(){
		return "Tiempo: "+tiempo+" Beneficio: "+beneficio;
	}
}
