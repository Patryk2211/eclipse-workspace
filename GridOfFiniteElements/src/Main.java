import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	private static LinkedList<Integer> data = new LinkedList<Integer>();
	private static String path = "/home/patryk/eclipse-workspace/GridOfFiniteElements/src/data.txt";
	private static Grid grid;
	
	public static void main(String[] args) {
		System.out.println("Welcome, now your data is loaded from the file");
		try {
			wczytajZPliku(data, path);
		} catch (FileNotFoundException e) {
			System.out.println("Read from file error!");
			e.printStackTrace();
		}
		System.out.println("Your data: " + data);
		
		create(data);
	}
	
	public static void wczytajZPliku(LinkedList<Integer> data, String path) throws FileNotFoundException {
		Scanner read = new Scanner(new File(path));
		while(read.hasNextLine()) {
			String line = read.nextLine();
			data.add(Integer.parseInt(line));
		}
	}
	
	private static void create(LinkedList<Integer> data) {
        grid = new Grid((float)data.get(0), (float)data.get(1), data.get(2), data.get(3), data.get(4));
        grid.printGrid();
    }
}