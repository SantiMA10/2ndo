package alg.p3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class InversionesDV {
	
	private BufferedReader bf;

	public long calcularInversionesDV(Integer[] ranking){
		return calcularInversionesDV1(ranking, 0, ranking.length - 1);
	}
	
	public long calcularInversionesDV1(Integer[] r, int iz, int de){
		long num = 0, izNum = 0, deNum = 0;
		int m = (iz + de) / 2;
		if(de > iz){
			izNum = calcularInversionesDV1(r, iz, m);
			deNum = calcularInversionesDV1(r, m + 1, de);
			num = combinarResultados(r, iz, m, m + 1, de);
		}
		return (num + izNum + deNum);
	}
	
	private long combinarResultados(Integer[] v, int iz, int mIz, int mDe, int de) {
		long num = 0;
		
		int t1 = mIz - iz + 1, t2 = de - mDe + 1;
		int[]vIz = new int[t1];
		int[]vDe = new int[t2];
		
		for (int i=0; i<t1; i++) {
			vIz[i] = v[iz + i];
		}
		for (int i=0; i<t2; i++) {
			vDe[i] = v[mDe + i];
		}
		
		int l1 = 0, l2 = 0, c1=0, c2=0;
		for (int i=0; i<t1+t2; i++)
		{ 
			if (l1 < t1){
				c1 = vIz[l1];
			}
			else{
				c1 = Integer.MAX_VALUE;
			}
			if (l2 < t2){
				c2 = vDe[l2];
			}
			else{
				c2 = Integer.MAX_VALUE;
			}
			if (c1 <= c2)
			{
				v[iz + i] = c1;
				l1 = l1 + 1;
			}
			else
			{
				if(c2 != v[iz + i]){
					num += t1 - (l1+1) + 1;
				}
				v[iz + i] = c2;
				l2 = l2 + 1;
			}
			

		}  
		return num;
	}

	public Integer[] leerFichero(String nombre){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		try {
			bf = new BufferedReader(new FileReader(nombre));
			while(bf.ready()){
				lista.add(Integer.parseInt(bf.readLine()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer[] ranking = new Integer[lista.size()];
		lista.toArray(ranking);
		return ranking;
	}
}
