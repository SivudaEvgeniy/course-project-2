import org.mariuszgromada.math.mxparser.*;
public class SystemFunctions {

private int size;
	
	
	SystemFunctions(int size){
		this.size=size;
	}
	
	
	public double[] func(double[] tochka ) {
		double[] result=new  double[size];
		result[size-1]=tochka[size-1];
		for(int i=0;i<size-1;i++) {
			result[i]=0;
			result[size-1]*=tochka[i];
			for(int j=0;j<size;j++) {
				result[i]+=tochka[j];
				if(i==j) {
					result[i]+=tochka[j];
					
				}
			}
			result[i]=result[i]-(size+1);
		}
		result[size-1]-=1;
		return result;

	}
	
	public double[] minusFunc(double[] tochka) {
		double[] result=new double[size];
		result=func(tochka);
		for(int i=0;i<size;i++) {
			result[i]*=-1;
		}
		return result;
	}
	
	public double[][] dF(double[] tochka) {
		double[][] result=new double[size][size];
		result[size-1][size-1]=1;
		for(int i=0;i<size-1;i++) {
			result[size-1][i]=1;
			for(int j=0;j<size;j++) {
				result[i][j]=1;
				if(j!=i) {
					result[size-1][i]*=tochka[j];
				}
			}
			result[i][i]=2;
			result[size-1][size-1]*=tochka[i];
		}
		
		return result;
	}

}
