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
		
//		System.out.println("����� �������:");
//		start=System.nanoTime();
//		nuton(n,pribl);
//		start= System.nanoTime() - start;  
//	      System.out.println("�������� ���������� ������: " + start + " ����������");
//		System.out.println("_______________________");
//		System.out.println("����� ��������� �������� ���������:");
//		start=System.nanoTime();
//		nutonPlus(n,pribl);
//		start= System.nanoTime() - start;  
//	      System.out.println("�������� ���������� ������: " + start + " ����������");
//		System.out.println("_______________________");
//		System.out.println("����� ������� �������� ���������:");
//		start=System.nanoTime();
//		nutonPlusPlus(n,pribl);
//		start= System.nanoTime() - start;  
//	      System.out.println("�������� ���������� ������: " + start + " ����������");
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
	      
	    
		nuton(n, x0);
		
	    
		
		nePolniyPrognoz(n, x0);
		

		
		polniyPrognoz(n, x0);
	}
	
	
	public static void nuton(int n, double pribl) {
		Function func=new Function(n);
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
		System.out.println("�����= "+norma(func.func(xPribl)));
		System.out.println(ji);
		
	}
	
	public static void nutonPlus(int n,double pribl) {
		double beta=0.01;
		double gamma=beta*beta;
		Function func=new Function(n);                           
		double[] xPribl= new double[n];
		for(int i=0;i<n;i++) {
			xPribl[i]=pribl;                            //��������� ����������� = (0,0,...,0)
		}
		          //����� ������� � ����� �0
		int it=1; //���-�� ��������
		boolean flag=true;

		while(flag) {
			double norma = norma(func.func(xPribl));     
			it++;
			double[] deltaX=SLAUSolution(func.dF(xPribl),func.minusFunc(xPribl));      //��� 1
			xPribl=summ(xPribl,skalar(beta,deltaX));                                   //��� 2
			if(norma( func.func(xPribl)) < eps ) {
				flag = false;                                                          //��� 3
			}
			else {                                                                     //��� 4
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
		System.out.println("�����= "+norma(func.func(xPribl)));
		System.out.println(it);
		
	}
	
	
	public static void nutonPlusPlus(int n,double pribl) {
		double beta=0.01;
		double gamma=beta*beta;
		Function func=new Function(n);                           
		double[] xPribl= new double[n];
		for(int i=0;i<n;i++) {
			xPribl[i]=pribl;                            //��������� ����������� = (0,0,...,0)
		}
		
		
			double norma = norma(func.func(xPribl));   //����� ������� � ����� �0
			double normaMinus = norma;
			double[] deltaX=SLAUSolution(func.dF(xPribl),func.minusFunc(xPribl));      //��� 1
			gamma=gamma*norma(func.func(summ(xPribl,deltaX)));
			double normaPlus = norma(func.func(summ(xPribl,deltaX)));
			xPribl=summ(xPribl,skalar(beta,deltaX));                                   //��� 2
			gamma/=norma(func.func(xPribl));
			
		int it=1; //���-�� ��������
		    
		while(true) {
			norma = norma(func.func(xPribl));     
			if(norma(func.func(xPribl)) < eps ) {
				break;                                                          //��� 3
			}
			else {gamma/=norma;                                                                     //��� 4
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
			deltaX=SLAUSolution(func.dF(xPribl),func.minusFunc(xPribl));      //��� 1
			xPribl=summ(xPribl,skalar(beta,deltaX));                                   //��� 2
			normaMinus=norma;
			normaPlus = norma(func.func(summ(xPribl,deltaX)));
		}
		
		show(xPribl);
		System.out.println("�����= "+norma(func.func(xPribl)));
		System.out.println(it);
		
	}
	

	public static double[] SLAUSolution(double[][] matr,double[] xN) {       //����� ������ 
		int n=matr.length;                                                   //��� ����
		double[][] test= new double[n][n+1];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				test[i][j]=matr[i][j];
			}
			test[i][n]=xN[i];
		}
		Matrica matrix=new Matrica(test);;
		matrix.pesult();
		matrix.finish();
		return matrix.resultat();
	}
	
	public static double[] summ(double[] a, double[] b) {                   //����� ���� ��������
		int size=a.length;
		double[] result = new double[size];
		for(int i=0;i<size;i++) {
			result[i]=a[i]+b[i];
		}
		return result;
	}
	
	public static double norma(double[] vect) {                             //����� �������
		int size=vect.length;
		double result = 0;
		for(int i=0;i<size;i++) {
			result+=vect[i]*vect[i];
		}
		return Math.sqrt(result);
	}
	
	public static void show(double[] vect) {                         //������� ������ �� �������
		for(int i=0;i<vect.length;i++) {
			System.out.println("x["+(i+1)+"]"+vect[i]);
		}
	}
	
	public static double[] skalar(double skalar, double[] vect) {            //���������� ������� �� ������
		int size=vect.length;
		double[] result = vect;
		for(int i=0;i<size;i++) {
			result[i]*=skalar;
		}
		return result;
	}
	//////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	public static void nuton(int n, double[] x0) {                                                                                    //����� �������
		Nuton nuton=new Nuton(n,eps, x0);                                  ///////////////////////////////////////////////////////////////////////////////
		long start=System.nanoTime();
		int i=nuton.solve();                                               /////////////////////////////////////////////////////////////////////////
		nuton.show();                                                            ////////////////////////////////////////////////////////////////////////////
		start= System.nanoTime() - start;  
		System.out.println("�������� ���������� ������: " + start + " ����������"); 
		System.out.println(i+" ��������");                                //////////////////////////////////////////////////////////////////////////////////	
		System.out.println("___________________________________________________________________________");
	}
	
	public static void nePolniyPrognoz(int n, double[] x0) {
		NePolniyPrognoz nePolniyPrognoz=new NePolniyPrognoz(n,eps, x0);                                  ///////////////////////////////////////////////////////////////////////////////
		long start=System.nanoTime();
		int i=nePolniyPrognoz.solve();                                               /////////////////////////////////////////////////////////////////////////
		nePolniyPrognoz.show();                                                            ////////////////////////////////////////////////////////////////////////////
		start= System.nanoTime() - start;  
		System.out.println("�������� ���������� ������: " + start + " ����������"); 
		System.out.println(i+" ��������"); 
		System.out.println("___________________________________________________________________________");
	}
	
	public static void polniyPrognoz(int n, double[] x0) {
		PolniyPrognoz polniyPrognoz=new PolniyPrognoz(n,eps, x0);                                  ///////////////////////////////////////////////////////////////////////////////
		long start=System.nanoTime();
		int i=polniyPrognoz.solve();                                               /////////////////////////////////////////////////////////////////////////
		polniyPrognoz.show();                                                            ////////////////////////////////////////////////////////////////////////////
		start= System.nanoTime() - start;  
		System.out.println("�������� ���������� ������: " + start + " ����������"); 
		System.out.println(i+" ��������"); 
	}

}


/////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

