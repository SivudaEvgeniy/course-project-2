
public class Matrica {
double[][] matrica;
	
	public Matrica() {
		
	}
	
	public Matrica(double[][] arg) {
		matrica=arg;
	}
	
	private boolean chekForNull(int line) {//проверка на ноль
		if(matrica[line][line]==0) {
			return true;
		}
		else return false;
	}
	
	private int findMaxInColumn(int column) {//пойск максимального в столбце
		int indexMax=column;
		for(int i=column+1;i<matrica.length;i++) {
			if(Math.abs(matrica[i][column])>Math.abs(matrica[indexMax][column])) {
				indexMax=i;
			}
		}
		return indexMax;
	}
	
	private void swap(int line1, int line2) {//поменять местами строки
		double[] buff=matrica[line1];
		matrica[line1]=matrica[line2];
		matrica[line2]=buff;
	}
	
	private void toUnity(int line) {//к еденице главную дивгональ
		double del=matrica[line][line];
		for(int i=0;i<matrica.length+1;i++) {
			matrica[line][i]/=del;
		}
	}
	
	public void finish() {
		for(int i=matrica.length-1;i>0;i--) {
			for(int j=i-1;j>-1;j--) {
				raznica(j,lineXConst(i,matrica[j][i]));
			}
		}
	}
	
	private void raznica(int line2, double[] line1) {// line2-line1
		for(int i=0;i<matrica.length+1;i++) {
			matrica[line2][i]-=line1[i];
		}
	}
	
	private double[] lineXConst(int line,double constanta) {//строка умноженная на константу
		double[] result=new double[matrica.length+1];
		for(int i=0;i<matrica.length+1;i++) {
			result[i]=matrica[line][i]*constanta;
		}
		return result;
	}
	
	private void zeroing(int line) {
		for(int i=line+1;i<matrica.length;i++) {
			raznica(i,lineXConst(line,matrica[i][line]));
		}
	}
	
	private double[] backCourse() {
		double[] result=new double[matrica.length];
		result[matrica.length-1]=matrica[matrica.length-1][matrica.length];
		for(int i=matrica.length-2;i>-1;i--) {
			for(int j=i+1;j<matrica.length-1;j++) {
				result[i]-=matrica[i][j]*result[j];
			}
		}
		return result;
	}
	
	public double[] pesult() {
		for(int i=0;i<matrica.length-1;i++) {

			swap(i,findMaxInColumn(i));
			toUnity(i);
			zeroing(i);

		}
		if(matrica[matrica.length-1][matrica.length-1]!=0) {
			toUnity(matrica.length-1);
			zeroing(matrica.length-1);
		}
		return backCourse();
		
	}
	
	public void showMatrix() {
		for(int i=0;i<matrica.length;i++) {
			for(int j=0;j<matrica.length+1;j++) {
				System.out.print(matrica[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public double[] resultat() {
		double[] res = new double[matrica.length];
		for(int i=0;i<matrica.length;i++) {
			res[i]=matrica[i][matrica.length];
		}
		return res;
	}
	
	public static double[] SLAUSolution(double[][] matr,double[] x) {       //метод Гаусса 
		int n=matr.length;                                                   //для СЛАУ
		double[][] test= new double[n][n+1];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				test[i][j]=matr[i][j];
			}
			test[i][n]=x[i];
		}
		Matrica matrix=new Matrica(test);;
		matrix.pesult();
		matrix.finish();
		return matrix.resultat();
	}

}
