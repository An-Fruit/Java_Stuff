public class LinkList_Lab
{
    private Node front;

    //add a constructor
    public LinkList_Lab(Node n) {
        front = n;
    }

    public LinkList_Lab() {
        front = null;
    }

    //add method
    //add a new node containing val to the front of the list

    public void add( int val ) {
        if(front == null) {
            front = new Node(val, null);
            return;
        }
        Node current = front;
        front = new Node(val, current);

    }

    //add last method
    //make an addLast method to add to the end of the list

    public void addLast( int val ){
        if(front == null) {
            front = new Node(val, null);
            return;
        }
        Node current = front;
        while(current.next != null) {
            current = current.next;
        }
        current.next = new Node(val, null);

    }

    //delete method
    //deletes first occurence of val

    public boolean delete( int val ) {

        Node current = front;
        Node previous;
        if(front.value == val) {
            front = current.next;
            return true;
        }
        while(current.next != null) {
            previous = current;
            current = current.next;
            if(current.value == val) {
                previous.next = current.next;
                return true;
            }
        }


        return false;
    }

    //find method
    //return the index of a specified value in the list 
    //return -1 if not found

    public int find( int val ) {
        boolean found = false;
        int index = 0;
        Node current = front;
        while(current.next != null) {
            current = current.next;
            if(current.value == val) {
                found = true;
                return index;
            }
            index++;
        }
        return -1;
    }

    //doubleFirst
    //add a new node to the front of the list with the same value of the first node
    //[ 3, 4, 6]  would become  [3, 3, 4, 6]

    public void doubleFirst(){
        Node n = new Node(front.value, front);
        front = n;
    }

    //doubleLast
    //same as doubleFirst except you are doubling the last node
    //[ 3, 4, 6]  would become  [3, 4, 6, 6]
    public void doubleLast() {
        Node current = front;
        while(current.next != null) {
            current = current.next;
        }
        Node last = current;
        this.addLast(last.value);
    }

    //print / toString method
    //print / return all values in the list
    //void print()

    public String toString() {
        Node current = front;
        String s = "";
        while(current != null) {
            s += " " + current.value;
            current = current.next;
        }
        return s;
    }

    //put some test cases here and test your code

    public static void main(String[] args)
    {
        int[] cases = { 90, 10, 87, 33, 24, 56, 22, 66, 221, 455, 11, -90, 3, 4, 5, 6, 7, -9, 100, 200, 300};

        //make a new LinkList_Lab
        LinkList_Lab lab = new LinkList_Lab();

        //for each loop through cases
        for( int item : cases )
        {
            lab.addLast(item);
            System.out.println(lab);

        }
        System.out.println(lab);

        //print the list after all add
        //call doubleFirst
//		lab.doubleFirst();
////		//print the list
//		System.out.println(lab);
////		//call doubleLast
//		lab.doubleLast();
////		//print the list
//		System.out.println(lab);
//
//		//call find to search for a particular value
        System.out.println(lab.find(56));
//		//call find again to search for a value that is not in the list
        System.out.println(lab.find(429));
//		//for each loop through cases
        System.out.println(lab);

        for(int num: cases) {
            lab.delete(num);
            System.out.println(lab);
        }
        //System.out.println(lab);

        //delete each value from the list
        //print the list after each add
    }

    //A "node" that consists of a reference to another node and some integer value.

    class Node
    {
        int value;
        Node next;

        public Node( int v, Node n)
        {
            value = v;
            next = n;
        }

        public String toString()
        {
            return "" + value + " " + next;
        }
    }

}