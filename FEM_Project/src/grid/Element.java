package grid;

public class Element {

	private int IDArray[];
	private int area[];
	public Element() {
		IDArray= new int[4];
		area = new int[4];
	}
	public void setID(int ID0,int ID1,int ID2,int ID3) {
		IDArray[0]=ID0;
		IDArray[1]=ID1;
		IDArray[2]=ID2;
		IDArray[3]=ID3;
	}
	public void setArea(int p0,int p1, int p2, int p3) {
		area[0]=p0;
		area[1]=p1;
		area[2]=p2;
		area[3]=p3;
	}
	public int getID0() {
		return IDArray[0];
	}
	public int getID1() {
		return IDArray[1];
	}
	public int getID2() {
		return IDArray[2];
	}
	public int getID3() {
		return IDArray[3];
	}
	public int[] getTabID() {
		return IDArray;
	}
}
