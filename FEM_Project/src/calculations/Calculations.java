package calculations;

import data.source.InitialData;
import grid.Element;
import grid.Grid;
import grid.Node;
import localSystem.LocalSystem;

public class Calculations {
	
	private double[][] localH;
	private double[] localP;
	private double[][] globalH;
	private double[] globalP;
	private InitialData container;
	private Grid grid;
	private double[][] localMatrix;
	private double[][] localC;
	private Node[][] pomoc;
	LocalSystem[] localSystem;
	
	public Calculations(InitialData container, double[][] globalH, double[] globalP, Grid siatka) {
		this.container = container;
		localH = new double[container.getNH()][container.getNB()];
		localC = new double[container.getNH()][container.getNB()];
		localP = new double[container.getNH()];
		this.globalH=globalH;
		this.globalP=globalP;
		this.grid=siatka;
		fillMatrix();
		fillPomoc();
	}
	
	private void fillPomoc() {
		// TODO Auto-generated method stub
		localSystem = new LocalSystem[4];
		localSystem[0] = new LocalSystem( new Node( - 1.0, 1.0 / Math.sqrt( 3 ),0.1,false ), new Node( - 1.0, - 1.0 / Math.sqrt( 3.0 ),0.1,false ) );
		localSystem[1] = new LocalSystem( new Node( - 1.0 / Math.sqrt( 3 ), - 1.0,0.1,false ), new Node( 1.0 / Math.sqrt( 3.0 ), - 1.0 ,0.1,false) );
		localSystem[2] = new LocalSystem( new Node( 1.0, - 1.0 / Math.sqrt( 3 ) ,0.1,false), new Node( 1.0, 1.0 / Math.sqrt( 3.0  ),0.1,false) );
		localSystem[3] = new LocalSystem( new Node( 1.0 / Math.sqrt( 3 ), 1.0 ,0.1,false), new Node( - 1.0 / Math.sqrt( 3.0 ), 1.0 ,0.1,false) );

		for ( int i = 0; i < 4; i++ ) {
			for ( int j = 0; j < 2; j++ ) {
				localSystem[i].N[j][0] = N1( localSystem[i].nodesArray[j].getX(), localSystem[i].nodesArray[j].getY() );
				localSystem[i].N[j][1] = N2( localSystem[i].nodesArray[j].getX(), localSystem[i].nodesArray[j].getY() );
				localSystem[i].N[j][2] = N3( localSystem[i].nodesArray[j].getX(), localSystem[i].nodesArray[j].getY() );
				localSystem[i].N[j][3] = N4( localSystem[i].nodesArray[j].getX(), localSystem[i].nodesArray[j].getY() );
			}
		}
	}

	private void fillMatrix() {
		// TODO Auto-generated method stub
		localMatrix = new double[4][2];
		localMatrix[0][0]=-1/Math.sqrt(3);
		localMatrix[0][1]=-1/Math.sqrt(3);
		localMatrix[1][0]=1/Math.sqrt(3);
		localMatrix[1][1]=-1/Math.sqrt(3);
		localMatrix[2][0]=-1/Math.sqrt(3);
		localMatrix[2][1]=1/Math.sqrt(3);
		localMatrix[3][0]=1/Math.sqrt(3);
		localMatrix[3][1]=1/Math.sqrt(3);
	}

	public void calculate() {
		double[] dNpodX=new double[4];
		double[] dNpodY=new double[4];
		double[] X;
		double[] Y;
		double[] temps;
		int[] id;
		double intTemp;
		Element el;
		double[][] dNpodE= new double[4][4];
		double[][] dNpodN= new double[4][4];
		double[][] N = new double[4][4];
		double[][] NPow = new double[2][4];
		
		
		
		
		for ( int i = 0; i < 4; i++ ) {
			dNpodE[i][0] = N1_ksi( localMatrix[i][0] );
			dNpodE[i][1] = N2_ksi( localMatrix[i][0] );
			dNpodE[i][2] = N3_ksi( localMatrix[i][0] );
			dNpodE[i][3] = N4_ksi( localMatrix[i][0] );

			dNpodN[i][0] = N1_eta( localMatrix[i][1] );
			dNpodN[i][1] = N2_eta( localMatrix[i][1] );
			dNpodN[i][2] = N3_eta( localMatrix[i][1] );
			dNpodN[i][3] = N4_eta( localMatrix[i][1] );

			N[i][0] = N1( localMatrix[i][0], localMatrix[i][1] );
			N[i][1] = N2( localMatrix[i][0], localMatrix[i][1] );
			N[i][2] = N3( localMatrix[i][0], localMatrix[i][1] );
			N[i][3] = N4( localMatrix[i][0], localMatrix[i][1] );
		}
		
		
		
		

		
		//P�tla po elementach
		int liczbaElem = grid.getTabElem().length;
		double help=0;
		for(int i=0;i<liczbaElem;i++) {	
			localH = new double[container.getNH()][container.getNB()];
			localP = new double[container.getNH()];
			X=new double[container.getNH()];
			Y=new double[container.getNB()];
			temps=new double[container.getNB()];
			el=grid.getTabElem()[i];
			id = el.getTabID();
			
			//Zerowanie macierzy lokalnej
			for(int j=0;j<4;j++) {
				for(int n=0;n<4;n++) {
					localH[j][n]=0;
					localC[j][n]=0;
				}
				localP[j]=0;
			}
			
			//Dane elementu i jego w�z��w
			for(int j=0;j<4;j++) {
				X[j]=grid.getTabWez()[id[j]-1].getX();
				Y[j]=grid.getTabWez()[id[j]-1].getY();
				temps[j]=grid.getTabWez()[id[j]-1].getTemperature();
			}
			
			for(int punktCalkowania = 0; punktCalkowania<4; punktCalkowania++) {
								//////////
								//dXpodE//
								//////////
								double dXpodE=localMatrix[punktCalkowania][1]/4*(X[0] - X[1] +X[2] - X[3])
										+(-X[0]	+ X[1] + X[2] - X[3])/4;
								//////////
								//dYpodN//
								//////////						
								double dYpodN=localMatrix[punktCalkowania][0]/4*(Y[0] - Y[1] + Y[2] - Y[3])
										+(-Y[0] - Y[1] + Y[2] + Y[3])/4;
								//////////
								//dYpodE//
								//////////						
								double dYpodE=localMatrix[punktCalkowania][1]/4*(Y[0] - Y[1] + Y[2] - Y[3])
										+(-Y[0] + Y[1] + Y[2] - Y[3])/4;
								//////////
								//dXpodN//
								//////////
								double dXpodN=localMatrix[punktCalkowania][0]/4*(X[0] - X[1] + X[2] - X[3])
										+(-X[0] - X[1] + X[2] + X[3])/4;
							
								
								double detJ=dXpodE * dYpodN - dYpodE * dXpodN;
								intTemp = 0;
								//
								
								

								
								
								//P�tla po w�z�ach
								for(int k=0;k<4;k++) {
									dNpodX[k]=(dYpodN*dNpodE[punktCalkowania][k]+(-dXpodN)*dNpodN[punktCalkowania][k])/detJ;
									dNpodY[k]=((-dYpodE)*dNpodE[punktCalkowania][k]+dXpodE*dNpodN[punktCalkowania][k])/detJ;
									intTemp += temps[k]*N[punktCalkowania][k];
								}
								
								detJ=Math.abs(detJ); //Z ksi��ki prof. Milenina
								
								//Obliczanie macierzy lokalnej H i C
								for(int n=0;n<4;n++) {
									for(int o=0;o<4;o++) {
										help = container.getSpecificHeat()*container.getDensity()*N[punktCalkowania][n]*N[punktCalkowania][o]*detJ;
										localH[n][o] += container.getConductivity()*(dNpodX[n]*dNpodX[o]+dNpodY[n]*dNpodY[o])*detJ+help/container.getTimeStamp();
										localP[n] += help/container.getTimeStamp() * intTemp;
									}
								}
			}
			//Warunki brzegowe
			for(int k=0;k<4;k++) {
				int a=(k+1)%4;
				if(grid.getTabWez()[id[k%4]-1].getStatus()==true && grid.getTabWez()[id[(k+1)%4]-1].getStatus()==true) {
					double detj = Math.sqrt( Math.pow( grid.getTabWez()[id[k%4]-1].getX() - grid.getTabWez()[id[(k+1)%4]-1].getX(), 2 ) + Math.pow( grid.getTabWez()[id[k%4]-1].getY() - grid.getTabWez()[id[(k+1)%4]-1].getY(), 2 ) ) / 2.0;					
					
					if(i == 0) {
						if(a==0) {
							a=1;
						}else if(a == 1) {
							a=0;
						}
					}else if(i==2) {
						if(a==0) {
							a=3;
						}else if(a == 3) {
							a=0;
						}
					}
					
					//Po dw�ch punktach 
					for ( int p = 0; p < 2; p++ ) {

						for ( int n = 0; n < 4; n++ ) {
							for ( int l = 0; l < 4; l++ )
								localH[n][l] += container.getAlfa() * localSystem[(k+1)%4].N[p][n] * localSystem[(k+1)%4].N[p][l] * detj; //dodajemy warunek brzegowy na powierzchni
							localP[n] += container.getAlfa() * container.getAmbientTemp() * localSystem[a].N[p][n] * detj;
						}
					}
				}
			}
			
			//Agregacja
			for(int n=0;n<4;n++) {
				for(int o=0;o<4;o++) {
					//System.out.println("Wrzucam do "+(id[n]-1)+", "+(id[o]-1));
					globalH[id[n]-1][id[o]-1]+=localH[n][o];
				}
				globalP[id[n]-1]+=localP[n];
			}	
		}
		/*for(int j=0;j<16;j++) {
			for(int k=0;k<16;k++) {
				System.out.print(globalH[j][k]+", ");
			}
			System.out.println("");
		}
		
		System.out.println("");*/
	}
	public Grid getSiatka() {
		return grid;
	}
	
	private double N1_ksi ( double eta ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 - eta ) );
	}

	private double N1_eta ( double ksi ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 - ksi ) );
	}

	//
	private double N2_ksi ( double eta ) {
		return ( ( 1.0 / 4.0 ) * ( 1 - eta ) );
	}

	private double N2_eta ( double ksi ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 + ksi ) );
	}

	//
	private double N3_ksi ( double eta ) {
		return ( ( 1.0 / 4.0 ) * ( 1 + eta ) );
	}

	private double N3_eta ( double ksi ) {
		return ( ( 1.0 / 4.0 ) * ( 1 + ksi ) );
	}

	//
	private double N4_ksi ( double eta ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 + eta ) );
	}

	private double N4_eta ( double ksi ) {
		return ( ( 1.0 / 4.0 ) * ( 1 - ksi ) );
	}


	//
	private double N1 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 - ksi ) * ( 1.0 - eta );
	}

	private double N2 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 + ksi ) * ( 1.0 - eta );
	}

	private double N3 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 + ksi ) * ( 1.0 + eta );
	}

	private double N4 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 - ksi ) * ( 1.0 + eta );
	}
	
	public static double[] calculateTemperatureInNode ( int n, double[][] gik, double[] rok ) {

		double m, s, e;
		e = Math.pow( 10, - 12 );
		double[] tabResult = new double[n];

		double[][] tabAB = new double[n][n + 1];
		for ( int i = 0; i < n; i++ )
			for ( int j = 0; j < n; j++ )
				tabAB[j][i] = gik[j][i];

		for ( int i = 0; i < n; i++ )
			tabAB[i][n] = rok[i];

		for ( int i = 0; i < n - 1; i++ ) {
			for ( int j = i + 1; j < n; j++ ) {
				if ( Math.abs( tabAB[i][i] ) < e ) {
					System.err.println( "dzielnik rowny 0" );
					break;
				}

				m = - tabAB[j][i] / tabAB[i][i];
				for ( int k = 0; k < n + 1; k++ )
					tabAB[j][k] += m * tabAB[i][k];
			}
		}

		for ( int i = n - 1; i >= 0; i-- ) {
			s = tabAB[i][n];
			for ( int j = n - 1; j >= 0; j-- )
				s -= tabAB[i][j] * tabResult[j];

			if ( Math.abs( tabAB[i][i] ) < e ) {
				System.err.println( "dzielnik rowny 0" );
				break;
			}

			tabResult[i] = s / tabAB[i][i];
		}
		return tabResult;
	}
}
