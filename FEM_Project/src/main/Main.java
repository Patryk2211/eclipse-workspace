package main;


import java.io.IOException;
import java.util.Arrays;

import calculations.Calculations;
import data.source.InitialData;
import grid.Grid;


public class Main {

    private static Grid grid;
    private static InitialData initialData;
	public static Calculations calculations;
	public static double[][] globalH;
	public static double[] globalP;
	public static double[] t;

    public static void main(String []argv) throws IOException {

    	initialData = new InitialData();
    	globalH = new double[initialData.getNH()*initialData.getNB()][initialData.getNH()*initialData.getNB()];
		globalP = new double[initialData.getNH()*initialData.getNB()];
        createGrid(initialData);
        
        calculations = new Calculations(initialData, globalH, globalP, grid);
        int k=0, numberOfSteps = 20;
        
        System.out.println("Temperatury min i max dla " + numberOfSteps + " kroków:");
        System.out.println("------------------------------------");
        
        for(int c = 0; c < numberOfSteps; c++) {
			makeDataZero();
			calculations.calculate();
			t=Calculations.calculateTemperatureInNode(initialData.getNH()*initialData.getNB(),globalH,globalP);
			
			//aktualizowanie temperatur
			for(int i = 0;i<initialData.getNH()*initialData.getNB();i++) 
				grid.getTabWez()[i].setTemp(t[i]);

			System.out.print( (k+1)*initialData.getTimeStamp() + "\t" );
			System.out.printf( "%.3f\t\t", getMin( t ) );
			System.out.printf( "%.3f", getMax( t ) );
			System.out.println();
			k++;
        }
        
      //Ostateczne temperatury
      		System.out.println("\nKońcowe temperatury w wezłach:");
      		System.out.println("------------------------------");
      				int iterator = 0;
      				for ( int i = 0; i < initialData.getNB(); i++ ) {
      					for ( int j = 0; j < initialData.getNH(); j++ )
      						System.out.printf( "%.3f\t\t", grid.getTabWez()[iterator++].getTemperature() );
      					System.out.println();
      				}
    }


    private static void createGrid(InitialData initialData) {
    	double H = initialData.getH();
    	double B = initialData.getB();
    	int nH = initialData.getNH();
    	int nB = initialData.getNB();
    	double temp = initialData.getInitialTemp();
        grid = new Grid(H, B, nH, nB, temp);
        //grid.printGrid();
    }
    
    private static void makeDataZero() {
		for(int i=0;i<initialData.getNH()*initialData.getNB();i++) {
			for(int j=0;j<initialData.getNH()*initialData.getNB();j++) {
				globalH[i][j]=0;
			}
			globalP[i]=0;
		}
	}
    
    public static double getMin ( double[] t ){
		return Arrays.stream( t ).min().getAsDouble();
	}

	public static double getMax ( double[] t ){
		return Arrays.stream( t ).max().getAsDouble();
	}
}