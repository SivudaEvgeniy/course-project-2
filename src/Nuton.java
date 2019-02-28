
public class Nuton {

	private Function f;
	private int n;
	private double eps;
	private double[] x;
	private double norm;
	
	public Nuton() {
		
	}
	
	public Nuton(int n,double eps, double[] x) {
		this.n = n;
		f = new Function(n);
		this.eps = eps;
		this.x = x;
	}
	
	private double[] findInc() {
		return Matrica.SLAUSolution(f.dF(x),f.minusFunc(x));
		
	}
	
	private double[] increment(double[] inc) {
		return Vector.summ(x,inc);
	}
	
	public int solve() {
		int i=0;
		do {
			i++;
			x=increment(findInc());
		}while(Vector.norma(f.func(x)) > eps );
		return i;
	}
	
	public void show() {
		Vector.show(x);
		System.out.println("норма = "+Vector.norma(f.func(x)));
	}

}
