package grid;

public class Grid {

	private int numberOfElements, numberofNodes;
	private Element[] elementsArray;
	private Node[] nodesArray;
	private int nH;
	private int nB;
	private double H;
	private double B;
	private double temp;
	public Grid(double H, double B, int nH, int nB, double temp) {
		this.H = H;
		this.B = B;
		this.nB = nB;
		this.nH =nH;
		this.temp = temp;
		this.numberOfElements = (nH-1)*(nB-1);
		this.numberofNodes = nH*nB;
		elementsArray = new Element[numberOfElements];
		nodesArray = new Node[numberofNodes];
		
		int nrElem=0;
		for(int i=0;i<nH-1;i++) {
			for(int j=0;j<nB-1;j++) {
				elementsArray[nrElem]=new Element();
				elementsArray[nrElem].setID(1+i*(nB)+j,
						1+(i+1)*(nB)+j,
						(1+(i+1)*(nB))+1+j,
						(1+i*(nB))+1+j);
				nrElem++;
			}
		}
		nrElem=0;
		for(int i=0;i<nH;i++) {
			for(int j=0;j<nB;j++) {
				Boolean edge=false;
				if(i==0||i==(nH-1)||j==0||j==(nB-1)) {
					edge=true;
				}else {
					edge=false;
				}
				nodesArray[nrElem]=new Node(H/(nH-1)*i,B/(nB-1)*j,temp,edge);
				nrElem++;
			}
		}
	}
	public void showSiatka(){
		int conunter=1;
		for(int i=0;i<nH-1;i++) {
			for(int j=0;j<nB-1;j++) {
				System.out.println("Element nr: "+conunter);
				System.out.println(elementsArray[conunter-1].getID0()+"-"+elementsArray[conunter-1].getID1());
				System.out.println("|  |");
				System.out.println(elementsArray[conunter-1].getID3()+"-"+elementsArray[conunter-1].getID2());
				conunter++;
			}
			System.out.println("\nNext column");
		}
	}
	public Element[] getTabElem() {
		return elementsArray;
	}
	public Node[] getTabWez() {
		return nodesArray;
	}
}
