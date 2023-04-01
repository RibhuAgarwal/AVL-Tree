/**

The Node class represents a single node in a binary search tree. It stores the node's data,
left and right child nodes, level, and depth in the tree.
@param <T> the type of data stored in the node

**/

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

	/** The data stored in the node **/
	private T data;
	/** The left child node **/
	private Node<T> left;
	/** The right child node **/
	private Node<T> right;
	/** The level of the node in the tree **/
	public int level;
	/** The depth of the node in the tree **/
	private int depth;

	/**

	Constructs a new Node with the given data and no child nodes.
	@param data the data to store in the node

	**/

	public Node(T data) {
		this(data, null, null);
	}

	/**

	Constructs a new Node with the given data and child nodes.
	The depth of the node is set based on the depths of its children.
	@param data the data to store in the node
	@param left the left child node
	@param right the right child node
	
	**/

	public Node(T data, Node<T> left, Node<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		if (left == null && right == null)
			setDepth(1);
		else if (left == null)
			setDepth(right.getDepth() + 1);
		else if (right == null)
			setDepth(left.getDepth() + 1);
		else
			setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
	}

	/**

	Returns the data stored in the node.
	@return the data stored in the node
	
	**/
	public T getData() {
		return data;
	}
	
	/**

	Sets the data stored in the node to the given value.
	@param data the new data to store in the node
	
	**/
	public void setData(T data) {
		this.data = data;
	}
	/**

	Returns the left child node.
	@return the left child node
	
	**/
	
	public Node<T> getLeft() {
		return left;
	}
	/**

	Sets the left child node to the given node.
	@param left the new left child node
	
	**/
	public void setLeft(Node<T> left) {
		this.left = left;
	}

	/**

	Returns the right child node.
	@return the right child node
	
	**/

	public Node<T> getRight() {
		return right;
	}

	/**

	Sets the right child node to the given node.
	@param right the new right child node
	
	**/

	public void setRight(Node<T> right) {
		this.right = right;
	}

	/**

	Returns the depth of the node in the tree.
	@return the depth of the node in the tree

	**/


	public int getDepth() {
		return depth;
	}

	/**

	Sets the depth of the node in the tree to the given value.
	@param depth the new depth of the node in the tree

	**/
	
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
   Compares this node with the specified node for order based on their data values.
   Returns a negative integer, zero, or a positive integer as this node is less than,
   equal to, or greater than the specified node.
   @param o the node to be compared
   @return a negative integer, zero, or a positive integer as this node is less than,
   equal to, or greater than the specified node.
 	**/

	@Override
	public int compareTo(Node<T> o) {
		return this.data.compareTo(o.data);
	}

	
	/**
 	
	Returns a string representation of this node, including its data value and level in the tree.
	@return a string representation of this node
 	
	**/

	@Override
	public String toString() {
		return "Level " + level + ": " + data;
	}


	

}