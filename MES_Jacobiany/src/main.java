
public class main {
	public static void main(String[] args) {
		Punkt[] punkty=new Punkt[4];
		punkty[0]=new Punkt(-0.5773,-0.5773);
		punkty[1]=new Punkt(0.5773,-0.5773);
		punkty[2]=new Punkt(0.5773,0.5773);
		punkty[3]=new Punkt(-0.5773,0.5773);
		double x1=-1.0;
		double x2=1.0;
		double x3=1.0;
		double x4=-1.0;
		double y1=-1.0;
		double y2=-1.0;
		double y3=1.0;
		double y4=1.0;
	
		
		for(int i=0;i<4;i++) {
			System.out.println("Obliczam dla punktu: "+i);
			double dXpodE=punkty[i].getEta()/4*(x1-x2+x3-x4)+(-x1+x2+x3-x4)/4;
			double dYpodN=punkty[i].getKsi()/4*(y1-y2+y3-y4)+(-y1-y2+y3+y4)/4;
			double dYpodE=punkty[i].getEta()/4*(y1-y2+y3-y4)+(y1+y2+y3-y4)/4;
			double dXpodN=punkty[i].getKsi()/4*(x1-x2+x3-x4)+(-x1-x2+x3+x4)/4;
			
			
			double detJ=dXpodE * dYpodN - dYpodE * dXpodN;
			
			
			double[] dNpodE= {-(punkty[i].getEta()-1)/4,(1-punkty[i].getEta())/4,(1+punkty[i].getEta())/4,-(1+punkty[i].getEta()/4)};
			double[] dNpodN= {-(punkty[i].getKsi()-1)/4,-(1+punkty[i].getKsi())/4,(1+punkty[i].getKsi())/4,(1-punkty[i].getKsi()/4)};
			
			
			
			double[] dNpodX=new double[4];
			double[] dNpodY=new double[4];
			
			for(int j=0;j<4;j++) {
				dNpodX[j]=(dYpodN*dNpodE[j]+(-dYpodE*dNpodN[j]))/detJ;
				dNpodY[j]=((-dXpodN)*dNpodE[j]+dXpodE*dNpodN[j])/detJ;
				System.out.println("Wektor koncowy dla punktu: "+i+" dla N"+j+", dNpodX ma wartosc: "+dNpodX[j]+" a dNpodY: "+dNpodY[j]);
			}
			
			double[][] macierzdNpodX=new double[4][4];
			double[][] macierzdNpodY=new double[4][4];
			
			
			for(int z=0;z<4;z++) {
				for(int j=0;j<4;j++) {
					macierzdNpodX[z][j]=dNpodX[z]*dNpodX[j];
				}
			}
			for(int z=0;z<4;z++) {
				for(int j=0;j<4;j++) {
					macierzdNpodY[z][j]=dNpodY[z]*dNpodY[j];
				}
			}			
		}
	}
}
