import java.util.*;
/*
 *  Name: Andre Zhang
 *
 * Using the setOrder method will change the traversal order of the tree
 * 1 = pre-order
 * 2 = post-order
 * 3 = level-order
 * 4 = reverse order
 * any other number = in-order
 *
 */

public class BinarySearchTree
{
    public class Node
    {
        int data;
        Node left;
        Node right;

        public Node( int v, Node l, Node r)
        {
            data = v;
            left = l;
            right = r;
        }

        public String toString()
        {
            return "" + data + " " + left + " " + right;
        }
    }

    private Node root;
    private int order;
    private String print;
    //constructor
    public BinarySearchTree() {
        print = "";
        //sets the order to -1 so it defaults to in-order
        order = -1;
    }

    //toString method: depending on what the "order" int is it will traverse the tree in a certain order
    public String toString() {
        print = "";
        if(order == 1) {
            preOrder(root);
        }
        else if(order == 2) {
            postOrder(root);
        }
        else if(order == 3) {
            levelOrder(root);
        }
        else if(order == 4) {
            revOrder(root);
        }

        else {
            inOrder(root);
        }
        return print;
    }


    //method for setting the order of tree traversal
    public void setOrder(int val) {
        order = val;
    }

    //add method
    public void add( int o )
    {
        root = add( o, root );
    }
    private Node add( int o, Node n )
    {
        if(n == null) {
            n = new Node(o, null, null);
        }
        else if(n.data > o) {
            n.left = add(o, n.left);
        }
        else if(n.data < o) {
            n.right = add(o, n.right);
        }

        return n;
    }


    //remove method
    public void remove(int o) {
        root = remove(root, o);
    }
    private Node remove(Node n, int o) {
        if(n == null) return null;
        if(o == n.data) {
            if(n.left == null && n.right == null) {
                return null;
            }
            else if(n.left == null || n.right == null) {
                if(n.left == null) {
                    return n.right;
                }
                else {
                    return n.left;
                }
            }
            else if (n.left != null && n.right != null){
                int smallestValue = smallest(n.right);
                n.data = smallestValue;
                n.right = remove(n.right, smallestValue);
                return n;
            }

        }
        else if(n.data > o) {
            n.left = remove(n.left, o);
        }
        else if(n.data < o) {
            n.right = remove(n.right, o);
        }

        return n;
    }

    //next methods are all traversals

    //in order traversal L D R

    private void inOrder(Node n) {

        if(n != null) {
            inOrder(n.left);
            print += n.data + " ";
            inOrder(n.right);
        }
        return;

    }

    //pre-order traversal D L R

    private void preOrder(Node n) {
        if(n != null) {
            print += n.data + " ";
            preOrder(n.left);
            preOrder(n.right);
        }
        return;
    }

    //post order traversal L R D

    private void postOrder(Node n) {
        if(n != null) {
            postOrder(n.left);
            postOrder(n.right);
            print += n.data + " ";
        }
        return;
    }

    //reverse order traversal R D L

    private void revOrder(Node n) {
        if(n != null) {
            revOrder(n.right);
            print += n.data + " ";
            revOrder(n.left);
        }

        return;

    }

    //level-order traversal

    private void levelOrder(Node n) {
        if(root == null) return;
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            print += temp.data + " ";
            if(temp.left != null) {
                q.add(temp.left);
            }
            if(temp.right != null) {
                q.add(temp.right);
            }

        }
    }

    //find a specified value in the tree

    public boolean find (int val) {
        return find(root, val);
    }
    private boolean find(Node n, int val) {
        if(n!= null) {
            if(n.data == val) return true;
            else if(n.data > val) {
                return find(n.left, val);
            }
            else if(n.data < val) {
                return find(n.right, val);
            }
        }

        return false;
    }

    // return the largest or smallest values in the tree
    public int largest() {
        if(root == null) {
            throw new NoSuchElementException();
        }
        return largest(root);
    }
    private int largest(Node n) {
        return n.right == null ? n.data : largest(n.right);
    }

    public int smallest() {
        if(root == null) {
            throw new NoSuchElementException();
        }
        return smallest(root);
    }
    private int smallest(Node n) {
        return n.left == null ? n.data : smallest(n.left);
    }

    //sum the nodes

    public int sumNodes() {
        if(root == null) return 0;
        return sumNodes(root);
    }
    private int sumNodes(Node n) {
        if(n == null) return 0;
        return n.data + sumNodes(n.left) + sumNodes(n.right);

    }

    // count the number of leaves in the tree

    public int countLeaves() {
        if(root == null) throw new NoSuchElementException();
        return countLeaves(root);
    }
    private int countLeaves(Node n) {
        if(n == null) return 0;
        if(n.left == null && n.right == null) return 1;
        return countLeaves(n.left) + countLeaves(n.right);
    }

    //checks to see if it's a binary tree by looking at the inorder traversal (inorder is always least -> greatest)

    public boolean isBST() {
        print = "";
        if(root == null) return false;
        inOrder(root);
        String[] words = print.split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        for(String s : words) {
            if(!s.equals("")) nums.add(Integer.parseInt(s));
        }
        for(int i = 1; i < nums.size(); i++) {
            int cache = nums.get(i - 1);
            if(cache > nums.get(i)) return false;
        }
        return true;
    }

    // return the height of the tree

    public int getHeight() {
        if(root == null) return 0;
        return getHeight(root);
    }
    private int getHeight(Node n) {
        if(n == null) return 0;
        else {
            int leftHeight = getHeight(n.left);
            int rightHeight = getHeight(n.right);
            if(leftHeight > rightHeight) {
                return leftHeight + 1;
            }
            else {
                return rightHeight + 1;
            }
        }
    }

    //get the width of the tree

    public int getWidth() {
        if(root == null) return 0;
        return getWidth(root);
    }
    private int getWidth(Node n) {
        if(n == null) return 0;
        return 1 + getHeight(n.left) + getHeight(n.right);
    }

    //checks to see if the tree is full

    public boolean isFull() {
        if(root == null) return false;
        boolean con = true;
        return isFull(root);
    }
    private boolean isFull(Node n) {
        if(root.left == null && root.right == null) return true;
        else {
            if(n.left == null && n.right == null) return true;
            if((n.left == null)^(n.right == null)) return false;
            else return isFull(n.left) && isFull(n.right);
        }


    }

    //count the number of nodes in the tree

    public int countNodes() {
        if(root == null) {
            return 0;
        }
        return countNodes(root);
    }
    private int countNodes(Node n) {
        if(n == null) return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    //checks to see if the tree is complete or not

    public boolean isComplete() {
        if(root == null) return false;
        return isComplete(root, 0, countNodes());
    }
    private boolean isComplete(Node n, int i, int nodeCnt) {
        if(n == null) return true;
        if(i >= nodeCnt) return false;
        return isComplete(n.left, 2 * i + 1, nodeCnt) && isComplete(n.right, 2 * i + 2, nodeCnt);
    }
	 /* grading scale
	    70   -  add + traversals � inOrder, preorder, postOrder, revOrder
	    
        80   -  70  + remove, smallest, largest, find
        
        90   -  80  + sumNodes, countLeaves, levelOrder traversal [ use a Q ], and isBST [ is the tree a binary search tree ]
        
        100  -  90  + getHeight, getWidth, isFull, and isComplete
        
        110  -  100 + mirror [ return a new tree ] + 
                    print a tree where it looks like a tree in a diagram (only do this if you enjoy inflicting pain upon yourself)
                    
    */

    //put test cases here to make sure your code works

     public static void main(String[] args)
     {
         int[] cases = { 90, 10, 87, 33, 24, 56, 22, 66, 221, 455, 11, -90, 3, 4, 5, 6, 7, -9, 100, 200, 300};

         BinarySearchTree lab = new BinarySearchTree();
         //for each loop through cases
         //add each value from case to the tree
         //print the tree after each add
         //System.out.println(cases.length);
         lab.setOrder(4);
         for(int num : cases) {
             lab.add(num);
             System.out.println(lab);
         }
         System.out.println("This is a binary search tree: " + lab.isBST());
         System.out.println("Sum of all values in the tree: " + lab.sumNodes());
         System.out.println("Number of \"leaves\" in the tree: " + lab.countLeaves());
         System.out.println("Height of the tree: " + lab.getHeight());
         System.out.println("Width of the tree: " + lab.getWidth());
         System.out.println("This tree is a complete tree: " + lab.isComplete());

//			lab.setOrder(3);
//			System.out.print("levelOrder traversal: "); System.out.println(lab);
//			lab.setOrder(1);
//			System.out.print("preOrder traversal: "); System.out.println(lab);

         for(int i : cases) {
             lab.remove(i);
             System.out.println(lab);
         }
         System.out.println(lab + " fin");
         //System.out.print(lab);
         //print the tree after all adds
         //use in order
         //use pre order
         //use post order
         //use reverse order

         //for each loop through cases
         //delete each value from the tree
         //print the tree after each add

     }
}