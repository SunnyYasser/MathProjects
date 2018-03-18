/* 
 * Disjoint-set data structure - Simple library (Java)
 * 
 
 */


/* 
 * Represents a set of disjoint sets. Also known as the union-find data structure.
 * Main operations are querying if two elements are in the same set, and merging two sets together.
 * Useful for testing graph connectivity, and is used in Kruskal's algorithm.
 */
public final class SimpleDisjointSet {
	
	/*---- Fields ----*/
	
	private Node[] nodes;
	
	
	
	/*---- Constructors ----*/
	
	// Constructs a new set containing the given number of singleton sets.
	// For example, new SimpleDisjointSet(3) --> {{0}, {1}, {2}}.
	public SimpleDisjointSet(int numElems) {
		if (numElems < 0)
			throw new IllegalArgumentException("Number of elements must be non-negative");
		nodes = new Node[numElems];
		for (int i = 0; i < numElems; i++) {
			Node node = new Node();
			node.parent = node;
			node.rank = 0;
			nodes[i] = node;
		}
	}
	
	
	
	/*---- Methods ----*/
	
	// Returns the representative node for the set containing the given element. Also performs path compression on nodes.
	private Node find(int elemIndex) {
		if (elemIndex < 0 || elemIndex >= nodes.length)
			throw new IndexOutOfBoundsException();
		return find(nodes[elemIndex]);
	}
	
	
	// Returns the representative node for the set containing the given element. Also performs path compression on nodes.
	private static Node find(Node node) {
		if (node.parent != node)
			node.parent = find(node.parent);  // Full path compression
		return node.parent;
	}
	
	
	// Tests whether the given two elements are members of the same set.
	public boolean inSameSet(int elemIndex0, int elemIndex1) {
		return find(elemIndex0) == find(elemIndex1);
	}
	
	
	// Merges together the sets that the given two elements belong to.
	// Returns true if and only if the two elements belonged to different sets at the start of the operation.
	public boolean union(int elemIndex0, int elemIndex1) {
		// Get representative nodes
		Node repr0 = find(elemIndex0);
		Node repr1 = find(elemIndex1);
		if (repr0 == repr1)
			return false;
		
		// Graph repr1's subtree onto node repr0 or vice versa, depending on ranks
		if (repr0.rank > repr1.rank)
			repr1.parent = repr0;
		else if (repr1.rank > repr0.rank)
			repr0.parent = repr1;
		else {  // repr0.rank == repr1.rank
			repr1.parent = repr0;
			repr0.rank++;
		}
		return true;
	}
	
	
	
	/*---- Helper class: Simple node ----*/
	
	private static final class Node {
		
		public Node parent;  // Not null
		public int rank;  // At least 0
		
	}
	
}