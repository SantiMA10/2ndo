package alg.p3;


import java.util.concurrent.RecursiveAction;

public class InversionesParalelo extends RecursiveAction{

	private static final long serialVersionUID = 1L;
	private Integer[] v;
	private int iz, de;
	public long numInv;
	
	public InversionesParalelo(Integer[] v, int iz, int de){
		this.v = v;
		this.iz = iz;
		this.de = de;
		numInv = 0;
	}
	
	@Override
	protected void compute() {
		long num = 0, izNum = 0, deNum = 0;
		int m = (iz + de) / 2;
		if(de > iz){
			InversionesParalelo iP1 = new InversionesParalelo(v, iz, m);
			InversionesParalelo iP2 = new InversionesParalelo(v, m + 1, de);
			invokeAll(iP1, iP2);
			izNum = iP1.numInv;
			deNum = iP1.numInv;
			num = combinarResultados(v, iz, m, m + 1, de);
		}
		numInv = (num + izNum + deNum);
		
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
	

}
