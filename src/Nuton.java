
public class Nuton {

	private SystemFunctions f;
	private int n;
	private double eps;
	private double[] x;
	private double norm;
	
	public Nuton() {
		
	}
	
	public Nuton(int n,double eps, double[] x) {
		this.n = n;
		f = new SystemFunctions(n);
		this.eps = eps;
		this.x = x;
	}
	
	private double[] findInc() {
		return Matrica.SLAUSolution(f.dF(x),f.minusFunc(x));
		
	}
	
	private double[] increment(double[] inc) {
		return Vector.summ(x,inc);
	}
	
	public double[] solve() {
		int i=0;
		do {
			i++;
			x=increment(findInc());
		}while(Vector.norma(f.func(x)) > eps );
		return x;
	}
	
	public void show() {
		Vector.show(x);
		System.out.println("норма = "+Vector.norma(f.func(x)));
	}

}
