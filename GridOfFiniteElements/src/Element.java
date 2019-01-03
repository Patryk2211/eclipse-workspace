
public class Element {
	private int [] id = new int[4];
	
	public Element() {}

	public Element(int[] id) {
		super();
		this.id = id;
	}

	public int[] getId() {
		return id;
	}

	public void setId(int[] id) {
		this.id = id;
	}
	
	public void setArr(int a, int b, int c, int d) {
		this.id[0] = a;
		this.id[1] = b;
		this.id[2] = c;
		this.id[3] = d;
 	}
}
