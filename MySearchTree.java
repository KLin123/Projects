import java.util.Scanner;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

public class MySearchTree<T extends Comparable<T>> // Generic Class
{
	Node<T> root; // Root of the tree

	class Node<T extends Comparable<T>> // Generic Class
	{
        	T element;
        	Node left, right;
 
        	private Node(T item) // Constructor
        	{
        		element = item;
            		left = null;
			right = null;
        	}
	}
	
	public Node<T> add(T newNode, Node<T> node)
	{
		if(root == null) // If the tree is empty
		{
			root = new Node<T>(newNode);
		}

		if(node == null)
		{
			node = new Node<T>(newNode);
			return node;
		}

		int compare = newNode.compareTo(node.element);
			
        	if(compare < 0) // if the new element is less than the current one, add it to the left side
		{
            		node.left = add(newNode, node.left);
		}
        	else if(compare > 0) // If the new element is greater than the current one, add it to the right side
		{
			node.right = add(newNode, node.right);
		}
	        return node;
	}

	public boolean find(T goal, Node<T> node)
	{
		if(node == null) // If the tree is empty
		{
			return false;
		}

		int compare = goal.compareTo(node.element);

                if(compare < 0) // if the new element is less than the current one, add it to the left
                {
                        return find(goal, node.left);
                }
                else if(compare > 0) // If the new element is greater than the current one, add it to the right
                {
                        return find(goal, node.right);
                }
		else // If the node exists (compare = 0)
		{
			return true;
		}

	}

	public int leafCount(Node<T> node)
	{
		if(node == null) // If the tree is empty
		{
			return 0;
		}

		if((node.left == null) && (node.right == null)) // If there is only the root
		{
			return 1;
		}
		else
		{
			return leafCount(node.left)+leafCount(node.right);
		}
	}

	public int parentCount(Node<T> node) // Call using parentCount(tree.root)
	{
		if(node == null) // Tree is empty
		{
			return 0;
		}

		if((node.left == null) && (node.right == null)) // If the node is a leaf
                {
                        return 0;
                }
                else
                {
                        return parentCount(node.left)+parentCount(node.right)+1;
                }
	}

	public int height(Node<T> node)
	{
		if(node == null) // Tree is empty
                {
                        return -1;
                }

		// Determine the height of the largest subtree and that is the height
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);

		if(leftHeight > rightHeight)
		{
			return (leftHeight + 1);
		}
		else
		{
			return (rightHeight + 1);
		}
	}

	public int findADepth(Node<T> node) // This is used for the isPerfect method
	{
		int d = 0;
		while (node != null)
		{
			d++;
    			node = node.left;
		}
		return d;
	}

	public boolean isPerfect(Node<T> node, int level, int depth)
	{
		// Need to check if left and right subtrees are the same height
		// Need to check if all internal nodes have 2 children

		if(node == null) // Tree is empty
                {
                        return true;
                }

		// If the node is a leaf node, it has to be at the height of the tree
		if((node.left == null) && (node.right == null))
		{
			return (depth == level+1);
		}

		// These are internal nodes because already checked for leaves before
		// If one child is empty
		if((node.left == null) || (node.right == null))
		{
			return false;
		}

		return (isPerfect(node.left, level+1, depth) && (isPerfect(node.right, level+1, depth)));
	}

	public boolean ancestors(Node<T> node, T target)
	{
		ArrayList<T> arr = new ArrayList<>(); // Holds the ancestors

		if(node == null)
		{
			return false;
		}

		if(node.element == target)
		{
			return true;
		}

		// If the target is either in the left or right subtree of the current node
		if(ancestors(node.left, target) || ancestors(node.right, target))
		{
			arr.add(node.element);
			System.out.println("The ancestors are: " + node.element + " ");
			return true;
		}
		
		return false;
	}

	public void inOrderPrint(Node<T> node)
	{
		if(node != null)
		{
			inOrderPrint(node.left);
			System.out.println(node.element);
			inOrderPrint(node.right);
		}
	}

	public void preOrderPrint(Node<T> node)
	{
		if(node != null)
		{
			System.out.println(node.element);
			preOrderPrint(node.left);
			preOrderPrint(node.right);
		}
	}

	public static void main(String args[])
	{
		MySearchTree<Integer> tree = new MySearchTree<Integer>();
		
		int input = 0;
		Scanner in = new Scanner(System.in);
		
		System.out.println("==================================================================================== \n");
		System.out.println("PLEASE NOTE THAT THE BINARY TREE IS OF TYPE INTEGER, BUT THE CLASS IS A GENERIC TYPE \n");
		System.out.println("==================================================================================== \n");

                while(input != 10)
                {
                        System.out.println("What would you like to do with the Integer binary search tree? (Please enter a number)");
                        System.out.println("1. Add a New Node");
                        System.out.println("2. Find a Node");
                        System.out.println("3. Count the Leaves");
                        System.out.println("4. Count the Parents");
                        System.out.println("5. Return the Height");
                        System.out.println("6. Know If the Tree is Perfect");
			System.out.println("7. Return the Ancestor");
			System.out.println("8. Print Inorder Traversal");
			System.out.println("9. Print Preorder Traversal");
			System.out.println("10. EXIT");

                        input = in.nextInt();
                        in.nextLine();

                        if(input == 1) // Add new node
                        {
				int n;

                                System.out.println("Please enter the Integer node you want to add");
				n = in.nextInt();

				tree.add(n, tree.root);
				System.out.println("Successfully added " + n + "\n");
			}

			if(input == 2)
			{
				int n;

                                System.out.println("Please enter the node you want to find");
                                n = in.nextInt();

                                if(tree.find(n, tree.root))
				{
					System.out.println("The node was found \n");
				}
				else
				{
					System.out.println("The node was NOT found \n");
				}
			}
			
			if(input == 3)
                        {
				System.out.println("The number of leaves in the tree is: " + tree.leafCount(tree.root) + "\n");
                        }

			if(input == 4)
                        {
				System.out.println("The number of parents in the tree is: " + tree.parentCount(tree.root) + "\n");
                        }

			if(input == 5)
                        {
				System.out.println("The height of the tree is: " + tree.height(tree.root) + "\n");
                        }

			if(input == 6)
                        {
				int d = tree.findADepth(tree.root);

				if(tree.isPerfect(tree.root, 0, d))
				{
					System.out.println("The tree is perfect \n");
				}
				else
				{
					System.out.println("The tree is NOT perfect \n");
				}
                        }

			if(input == 7)
                        {
				int n;

                                System.out.println("Please enter the node you want to find ancestors for");
                                n = in.nextInt();

                                tree.ancestors(tree.root, n);	
                        }

			if(input == 8)
                        {
				System.out.println("Inorder Traversal:"); 
				tree.inOrderPrint(tree.root);
                        }

			if(input == 9)
                        {
				System.out.println("Preorder Traversal:");
				tree.preOrderPrint(tree.root);
                        }

			if(input == 10)
                        {
				return;
                        }
		}
	}
}
