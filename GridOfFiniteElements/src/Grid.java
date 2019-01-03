
public class Grid {
	private float H;
	private float L;
	private int nH;
	private int nL;
	private int t_0;
	private int nN;
	private int nE;
	
	private Node [] nodes_arr;
	private Element [] element_arr;
	
	public Grid(float h, float l, int nH, int nL, int t_0) {
		this.H = h;
		this.L = l;
		this.nH = nH;
		this.nL = nL;
		this.t_0 = t_0;
		this.nE = (nH - 1) * (nL - 1);
		this.nN = nH * nL;
		this.nodes_arr = new Node[this.nN];
		this.element_arr = new Element[this.nE];
		
		int elemNr = 0;

	        int i;
	        int j;
	        for(i = 0; i < nH - 1; ++i) {
	            for(j = 0; j < nL - 1; ++j) {
	                this.element_arr[elemNr] = new Element();
	                this.element_arr[elemNr].setArr(1 + i * nL + j, 1 + (i + 1) * nL + j, 1 + (i + 1) * nL + 1 + j, 1 + i * nL + 1 + j);
	                ++elemNr;
	            }
	        }

	        elemNr = 0;

	        for(i = 0; i < nH; ++i) {
	            for(j = 0; j < nL; ++j) {
	                Boolean krawedz = false;
	                if (i != 0 && i != nH - 1 && j != 0 && j != nL - 1) {
	                    krawedz = false;
	                } else {
	                    krawedz = true;
	                }

	                this.nodes_arr[elemNr] = new Node(H / (float)(nH - 1) * (float)i, L / (float)(nL - 1) * (float)j, t_0);
	                ++elemNr;
	            }
	        }
	}
	
	public void printGrid() {
        int liczba = 1;

        for(int i = 0; i < this.nH - 1; ++i) {
            for(int j = 0; j < this.nL - 1; ++j) {
                System.out.println("Element nr: " + liczba);
                System.out.println(this.element_arr[liczba - 1].getId()[0] + "-" + this.element_arr[liczba - 1].getId()[1]);
                System.out.println("|  |");
                System.out.println(this.element_arr[liczba - 1].getId()[3] + "-" + this.element_arr[liczba - 1].getId()[2]);
                ++liczba;
            }

            System.out.println("\nNext column");
        }

    }

	public Node[] getNodes_arr() {
		return nodes_arr;
	}

	public Element[] getElement_arr() {
		return element_arr;
	}
}
