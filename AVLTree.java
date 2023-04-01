import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**

AVLTree is a self-balancing binary search tree that implements the Iterable interface.
The elements in the tree must be Comparable.
@param <T> the type of elements in the tree, which must be Comparable.

**/

public class AVLTree<T extends Comparable<T>> implements Iterable<Node<T>> {
	Node<T> root;
	
	/**
	Constructs an empty AVLTree.
	**/

	public AVLTree() {
		root = null;

	// other methods and classes follow...

	}

	/**

	Returns an iterator over the nodes of this binary search tree in in-order traversal order.
	@return an iterator over the nodes of this binary search tree in in-order traversal order.
	
	**/

	public Iterator<Node<T>> iterator() {
		ArrayList<Node<T>> nodesHolder = new ArrayList<>();
		inOrderPrint(root, nodesHolder);
		return new Itr(nodesHolder.iterator());

	}

	/**

	Private inner class that implements the Iterator interface for the nodes of this binary search tree.
	
	**/

	private class Itr implements Iterator<Node<T>> {
		private final Iterator<Node<T>> itrIterator;


		/**

		Constructs a new Itr instance with the specified iterator.
		@param iterator the iterator to be used by this Itr instance.
		
		**/

		public Itr(Iterator<Node<T>> iterator) {
			this.itrIterator = iterator;
		}
		/**

		Returns true if there are more nodes to be iterated over.
		@return true if there are more nodes to be iterated over, false otherwise.
		
		**/

		public boolean hasNext() {
			return itrIterator.hasNext();
			}

		/**

		Returns the next node in the iteration.
		@return the next node in the iteration.
		
		**/

		public Node<T> next() {
			return itrIterator.next();
		}

		/**

		Removes the last node returned by the iterator from the underlying collection.
		
		**/

		public void remove() {
			itrIterator.remove();
		}
	}

	/**

	Performs an in-order traversal of the binary search tree rooted at the specified node and adds the nodes
	to the specified container in in-order traversal order.
	@param node the root of the binary search tree to be traversed.
	@param container the container to which the nodes should be added in in-order traversal order.

	**/

	protected void inOrderPrint(Node<T> node, ArrayList<Node<T>> container) {
		if (node != null) {
			inOrderPrint(node.getLeft(), container);
			System.out.print(node.getData() + " ");
			container.add(node);
			inOrderPrint(node.getRight(), container);
		}
	}

	/**

	Returns the maximum element in the binary search tree.
	@return the maximum element in the binary search tree, or null if the tree is empty.

	**/

	public T Maximum() {
		Node<T> local = root;
		if (local == null)
			return null;
		while (local.getRight() != null)
			local = local.getRight();
		return local.getData();
	}


	/**

	Returns the depth of the specified node in the binary search tree.
	@param node the node whose depth is to be returned.
	@return the depth of the specified node, or 0 if the node is null.
	
	**/

	private int depth(Node<T> node) {
		if (node == null)
			return 0;
		return node.getDepth();
	}

	/**

	Adds a new node with the specified data to the binary search tree.
	@param data the data of the node to be added.
	@return the root node of the updated binary search tree.
	
	**/
	public Node<T> add(T data) {
		root = add(root, data);
		switch (balanceNumber(root)) {
			case 1:
				root = rotateLeft(root);
				break;
			case -1:
				root = rotateRight(root);
				break;
			default:
				break;
		}
		return root;
	}

	/**

	Adds a new node with the specified data to the binary search tree rooted at the specified node.
	@param node the root node of the binary search tree to which the new node is to be added.
	@param data the data of the node to be added.
	@return the root node of the updated binary search tree.

	**/

	public Node<T> add(Node<T> node, T data) {
		if (node == null)
			return new Node<T>(data);
		if (node.getData().compareTo(data) >= 0) {
			node = new Node<T>(node.getData(), add(node.getLeft(), data),
					node.getRight());
		} else if (node.getData().compareTo(data) < 0) {
			node = new Node<T>(node.getData(), node.getLeft(), add(
					node.getRight(), data));
		}
		switch (balanceNumber(node)) {
			case 1:
				node = rotateLeft(node);
				break;
			case -1:
				node = rotateRight(node);
				break;
			default:
				return node;
		}
		return node;
	}

	/**

	Calculates the balance factor of the given node by computing the difference between the depths
	of its left and right subtrees.
	@param node the node to calculate the balance factor for
	@return an integer representing the balance factor of the node. If the balance factor is less than
    -1, the node is considered "right-heavy" and a value of 1 is returned. If the balance factor
    is greater than 1, the node is considered "left-heavy" and a value of -1 is returned. If the
    balance factor is within the range [-1, 1], the node is considered "balanced" and a value of
    0 is returned.

	**/
	private int balanceNumber(Node<T> node) {
		int Left = depth(node.getLeft());
		int Right = depth(node.getRight());
		if (Left - Right >= 2)
			return -1;
		else if (Left - Right <= -2)
			return 1;
		return 0;
	}

	/**

	Performs a left rotation on the given node and returns the new root node of the resulting subtree.
	@param node the node to perform the left rotation on
	@return the new root node of the subtree after the left rotation
	
	**/

	private Node<T> rotateLeft(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getRight();
		Node<T> c = q.getLeft();
		Node<T> a = p.getLeft();
		Node<T> b = p.getRight();
		q = new Node<T>(q.getData(), c, a);
		p = new Node<T>(p.getData(), q, b);
		return p;
	}

	/**

	Performs a right rotation on the given node and returns the new root node of the resulting subtree.
	@param node the node to perform the right rotation on
	@return the new root node of the subtree after the right rotation

	**/
	private Node<T> rotateRight(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getLeft();
		Node<T> c = q.getRight();
		Node<T> a = p.getLeft();
		Node<T> b = p.getRight();
		q = new Node<T>(q.getData(), b, c);
		p = new Node<T>(p.getData(), a, q);
		return p;
	}

	/**

	Checks if the binary search tree contains a node with the given data.
	@param data the data to search for
	@return true if the binary search tree contains a node with the given data, false otherwise

	**/

	public boolean contain(T data) {
		Node<T> local = root;
		while (local != null) {
			if (local.getData().compareTo(data) == 0)
				return true;
			else if (local.getData().compareTo(data) > 0)
				local = local.getLeft();
			else
				local = local.getRight();
		}
		return false;
	}



	/**

	Calculates the total number of nodes in the subtree rooted at the given node.
	@param root the root node of the subtree to count the number of nodes for
	@return the total number of nodes in the subtree rooted at the given node

	**/

	public int numberofnode (Node<T> root){
        if (root==null){
            return 0;
        }
        
        int leftNode = numberofnode(root.getLeft());
        int rightNode = numberofnode(root.getRight());
        return leftNode+rightNode+1;
    }


	/**

	Prints the binary search tree to the console in a level-by-level order using breadth-first search.
	The root node is printed first, followed by its children, then their children, and so on.
	
	**/

	public void PrintTree() {
		root.level = 0;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<T> node = queue.poll();
			System.out.println(node);
			int level = node.level;
			Node<T> left = node.getLeft();
			Node<T> right = node.getRight();
			if (left != null) {
				left.level = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.level = level + 1;
				queue.add(right);
			}
		}
	}

	/**

	Removes the node with the given element from the binary search tree. Throws a NoSuchElementException
	if the element is not found in the tree.
	@param element the element to remove from the tree
	@throws NoSuchElementException if the element is not found in the tree
	
	**/

	public void remove(T element) {
		if (!contain(element)) {
			throw new NoSuchElementException("Element not found in tree.");
		}
		this.root = remove(this.root, element);
	}

	/**

	Finds and returns the node with the minimum value in the subtree rooted at the given node.
	@param node the root node of the subtree to find the minimum value for
	@return the node with the minimum value in the subtree rooted at the given node
	
	**/


	private Node<T> findMin(Node<T> node) {
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}

	/**

	Removes the node containing the specified element from the tree.
	If the tree does not contain the element, nothing is changed.
	@param node the root of the subtree to search for the element to remove
	@param element the element to remove from the tree
	@return the root of the subtree after removing the specified element

	**/

	private Node<T> remove(Node<T> node, T element) {
		if (node == null) {
			return null;
		}
		int comparision = element.compareTo(node.getData());
		// Check if left subtree has to be searched for the element to remove
		if (comparision < 0) {
			node.setLeft(remove(node.getLeft(), element));
		} 

		// Check if right subtree has to be searched for the element to remove

		else if (comparision > 0) {
			node.setRight(remove(node.getRight(), element));
		} 

		// If the node to be removed has only one child or no child
		
		else {
			if (node.getLeft() == null || node.getRight() == null) {
				node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
			} 
			
			// If the node to be removed has two children

			else {
				Node<T> temp = findMin(node.getRight());
				node.setData(temp.getData());
				node.setRight(remove(node.getRight(), temp.getData()));
			}
		}
		if (node == null) {
			return null;
		}

		// Re-calculate depth and balance of each node after removal

		node.setDepth(1 + Math.max(depth(node.getLeft()), depth(node.getRight())));
		int balance = balanceNumber(node);

		// Perform balancing operations if necessary

		if (balance > 1 && balanceNumber(node.getLeft()) >= 0) {
			return rotateRight(node);
		}
		if (balance > 1 && balanceNumber(node.getLeft()) < 0) {
			node.setLeft(rotateLeft(node.getLeft()));
			return rotateRight(node);
		}
		if (balance < -1 && balanceNumber(node.getRight()) <= 0) {
			return rotateLeft(node);
		}
		
		if (balance < -1 && balanceNumber(node.getRight()) > 0) {
			node.setRight(rotateRight(node.getRight()));
			return rotateLeft(node);
		}
		return node;
	}


	/**

	Removes all occurrences of a given element from the binary search tree.
	@param element the element to be removed

	**/

	public void removeAll(T element) {
		while (contain(element)) {
			root = remove(root, element);
		}
	}



	/**

	Returns a boolean indicating whether the binary search tree is empty or not.
	@return true if the binary search tree is empty, false otherwise

	**/ 

	public boolean isEmpty(){
		return root==null;		
	}


}