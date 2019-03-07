import java.util.Scanner;

public class Main {

	public static final double eps=0.000000000001;
	
	public static void main(String[] args) {
//		TestGUI app = new TestGUI();
//		app.setVisible(true);
		double beta=0.01;
		double gamma=beta*beta;
		double pribl=0.94;
		long start;
		Scanner sc=new Scanner(System.in);
		int n;
		do {
		    System.out.println("Please enter a positive number!");
		    while (!sc.hasNextInt()) {
		        System.out.println("That not a number!");
		        sc.next(); 
		    }
		    n = sc.nextInt();
		} while (n <= 1);
		
//		System.out.println("Метод Ньютона:");
//		start=System.nanoTime();
//		nuton(n,pribl);
//		start= System.nanoTime() - start;  
//	      System.out.println("Скорость выполнения метода: " + start + " наносекунд");
//		System.out.println("_______________________");
//		System.out.println("Метод неполного прогноза коррекции:");
//		start=System.nanoTime();
//		nutonPlus(n,pribl);
//		start= System.nanoTime() - start;  
//	      System.out.println("Скорость выполнения метода: " + start + " наносекунд");
//		System.out.println("_______________________");
//		System.out.println("Метод полного прогноза коррекции:");
//		start=System.nanoTime();
//		nutonPlusPlus(n,pribl);
//		start= System.nanoTime() - start;  
//	      System.out.println("Скорость выполнения метода: " + start + " наносекунд");
//	      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//	      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//	      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	      /////////////////////////////////////////////////////////////////////////////////////
	      /////////////////////////////////////////////////////////////////////////////////////
	      double[] x0=Vector.random(n);
	      System.out.print("x0 = [");
	      for(int i=0;i<n;i++) {
	    	  System.out.print(x0[i]+", ");
	      }
	      System.out.println("]");
	      Nuton nuton=new Nuton(n,eps, x0);
	      double[] sd=nuton.solve();
	      Vector.show(sd);
	      NePolniyPrognoz nePolniyPrognoz=new NePolniyPrognoz(n,eps, x0);
	      sd=nePolniyPrognoz.solve();
	      Vector.show(sd);
	      PolniyPrognoz зolniyPrognoz=new PolniyPrognoz(n,eps, x0);
	      sd=зolniyPrognoz.solve();
	      Vector.show(sd);

//		nuton(n, x0);
//		
//	    
//		
//		nePolniyPrognoz(n, x0);
//		
//
//		
//		polniyPrognoz(n, x0);
	}
	
	
/*	public static void nuton(int n, double pribl) {
		SystemFunctions func=new SystemFunctions(n);
		double[] xPribl= new double[n];
		for(int i=0;i<n;i++) {
			xPribl[i]=pribl;
		}
		int ji=0;
		do {
		 ji++;
		double[] deltaX=SLAUSolution(func.dF(xPribl),func.minusFunc(xPribl));
		xPribl=summ(xPribl,deltaX);
		} while(norma(func.func(xPribl)) > eps );
		show(xPribl);
		System.out.println("норма= "+norma(func.func(xPribl)));
		System.out.println(ji);
		
	}
	
	public static void nutonPlus(int n,double pribl) {
		double beta=0.01;
		double gamma=beta*beta;
		SystemFunctions func=new SystemFunctions(n);                           
		double[] xPribl= new double[n];
		for(int i=0;i<n;i++) {
			xPribl[i]=pribl;                            //начальное приближение = (0,0,...,0)
		}
		          //норма функции в точке х0
		int it=1; //кол-во итераций
		boolean flag=true;

		while(flag) {
			double norma = norma(func.func(xPribl));     
			it++;
			double[] deltaX=SLAUSolution(func.dF(xPribl),func.minusFunc(xPribl));      //шаг 1
			xPribl=summ(xPribl,skalar(beta,deltaX));                                   //шаг 2
			if(norma( func.func(xPribl)) < eps ) {
				flag = false;                                                          //шаг 3
			}
			else {                                                                     //шаг 4
				if(norma > norma(func.func(xPribl))) {                                 
					beta=1;                                                            
				}                                                                        
				else {                                                                       
					beta=(norma*gamma)/(norma(func.func(xPribl))*beta);                                                                       
					if( beta > 1 ) { beta=1; }                                                                
				}                                                                      
				gamma=(norma*gamma)/norma(func.func(xPribl));                                                                     
				norma=norma(func.func(xPribl));                                                                     
			}
		}
		
		show(xPribl);
		System.out.println("норма= "+norma(func.func(xPribl)));
		System.out.println(it);
		
	}
	
	
	public static void nutonPlusPlus(int n,double pribl) {
		double beta=0.01;
		double gamma=beta*beta;
		SystemFunctions func=new SystemFunctions(n);                           
		double[] xPribl= new double[n];
		for(int i=0;i<n;i++) {
			xPribl[i]=pribl;                            //начальное приближение = (0,0,...,0)
		}
		
		
			double norma = norma(func.func(xPribl));   //норма функции в точке х0
			double normaMinus = norma;
			double[] deltaX=SLAUSolution(func.dF(xPribl),func.minusFunc(xPribl));      //шаг 1
			gamma=gamma*norma(func.func(summ(xPribl,deltaX)));
			double normaPlus = norma(func.func(summ(xPribl,deltaX)));
			xPribl=summ(xPribl,skalar(beta,deltaX));                                   //шаг 2
			gamma/=norma(func.func(xPribl));
			
		int it=1; //кол-во итераций
		    
		while(true) {
			norma = norma(func.func(xPribl));     
			if(norma(func.func(xPribl)) < eps ) {
				break;                                                          //шаг 3
			}
			else {gamma/=norma;                                                                     //шаг 4
				if(norma > norma(func.func(xPribl))) {                                 
					beta=1;                                                            
				}                                                                        
				else {                                                                       
					beta=(normaMinus*gamma)/(normaPlus*beta);                                                                       
					if( beta > 1 ) { beta=1; }                                                                
				}                                                                      
				gamma=(normaMinus*gamma*norma(func.func(summ(xPribl,deltaX))))/(normaPlus);                                                                     
				                                                                     
			}
			
			it++;
			deltaX=SLAUSolution(func.dF(xPribl),func.minusFunc(xPribl));      //шаг 1
			xPribl=summ(xPribl,skalar(beta,deltaX));                                   //шаг 2
			normaMinus=norma;
			normaPlus = norma(func.func(summ(xPribl,deltaX)));
		}
		
		show(xPribl);
		System.out.println("норма= "+norma(func.func(xPribl)));
		System.out.println(it);
		
	}
	*/

	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
/*	public static void nuton(int n, double[] x0) {                                                                                    //метод Ньютона
		Nuton nuton=new Nuton(n,eps, x0);                                  ///////////////////////////////////////////////////////////////////////////////
		long start=System.nanoTime();
		int i=nuton.solve();                                               /////////////////////////////////////////////////////////////////////////
		nuton.show();                                                            ////////////////////////////////////////////////////////////////////////////
		start= System.nanoTime() - start;  
		System.out.println("Скорость выполнения метода: " + start + " наносекунд"); 
		System.out.println(i+" итераций");                                //////////////////////////////////////////////////////////////////////////////////	
		System.out.println("___________________________________________________________________________");
	}
	
	public static void nePolniyPrognoz(int n, double[] x0) {
		NePolniyPrognoz nePolniyPrognoz=new NePolniyPrognoz(n,eps, x0);                                  ///////////////////////////////////////////////////////////////////////////////
		long start=System.nanoTime();
		int i=nePolniyPrognoz.solve();                                               /////////////////////////////////////////////////////////////////////////
		nePolniyPrognoz.show();                                                            ////////////////////////////////////////////////////////////////////////////
		start= System.nanoTime() - start;  
		System.out.println("Скорость выполнения метода: " + start + " наносекунд"); 
		System.out.println(i+" итераций"); 
		System.out.println("___________________________________________________________________________");
	}
	
	public static void polniyPrognoz(int n, double[] x0) {
		PolniyPrognoz polniyPrognoz=new PolniyPrognoz(n,eps, x0);                                  ///////////////////////////////////////////////////////////////////////////////
		long start=System.nanoTime();
		int i=polniyPrognoz.solve();                                               /////////////////////////////////////////////////////////////////////////
		polniyPrognoz.show();                                                            ////////////////////////////////////////////////////////////////////////////
		start= System.nanoTime() - start;  
		System.out.println("Скорость выполнения метода: " + start + " наносекунд"); 
		System.out.println(i+" итераций"); 
	}
*/
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

