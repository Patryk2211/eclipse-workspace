package data.source;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;


public class InitialData {
	
	private double initialTemp;
	private double simulationTime;
	private double simulationStepTime;
	private double ambientTemp;
	private double alfa;
	private double H;
	private double B;
	private int nH;
	private int nB;
	private double specificHeat;
	private double conductivity;
	private double density;
	
	public InitialData() {
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("/home/patryk/eclipse-workspace/FEM_Project/src/data/resource/initialDataFile.json"));
			JSONObject jsonObject =  (JSONObject) obj;
			
		    initialTemp = Double.parseDouble((String)jsonObject.get("initial temperature"));
		    simulationTime = Double.parseDouble((String)jsonObject.get("simulation time"));
		    simulationStepTime = Double.parseDouble((String)jsonObject.get("simulation step time"));
		    ambientTemp = Double.parseDouble((String)jsonObject.get("ambient temperature"));
		    alfa = Double.parseDouble((String)jsonObject.get("alfa"));
		    H = Double.parseDouble((String)jsonObject.get("H"));
		    B = Double.parseDouble((String)jsonObject.get("B"));
		    nH = Integer.parseInt((String)jsonObject.get("NH"));
		    nB = Integer.parseInt((String)jsonObject.get("NB"));
		    specificHeat = Double.parseDouble((String)jsonObject.get("specific heat"));
		    conductivity = Double.parseDouble((String)jsonObject.get("conductivity"));
		    density = Double.parseDouble((String)jsonObject.get("density"));
		    
		} 
		
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		
		catch(Exception e ) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public int getNH() {
		return nH;
	}
	public int getNB() {
		return nB;
	}
	public double getB() {
		return B;
	}
	public double getH() {
		return H;
	}
	public double getDensity() {
		return density;
	}
	public double getSimTime() {
		return simulationTime;
	}
	public double getTimeStamp() {
		return simulationStepTime;
	}
	public double getSpecificHeat() {
		return specificHeat;
	}
	public double getConductivity() {
		return conductivity;
	}
	public double getInitialTemp() {
		return initialTemp;
	}
	public double getAmbientTemp() {
		return ambientTemp;
	}
	public double getAlfa() {
		return alfa;
	}
}
