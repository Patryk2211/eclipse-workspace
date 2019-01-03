package localSystem;

import grid.Node;

public class LocalSystem {
	public double[][] N;
	public Node nodesArray[];

	public LocalSystem (Node n1, Node n2 ) {
		N = new double[2][4];
		nodesArray = new Node[2];
		nodesArray[0] = n1;
		nodesArray[1] = n2;
	}
}
