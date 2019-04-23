/* Name:    Zaynab Ghazi
 * File:    NameDLL.java
 * Desc:
 *
 * 
 *
 * This program creates a DoublyLinkedList of type Name
 * 
 * 
 */
public class NameDLL{
    //create Node object to be implemented in the linkedlist
    private static class Node{
	private Name data;
	private Node prev,next;
	public Node(Name data, Node prev, Node next){
	    this.data=data;
	    this.prev=prev;
	    this.next=next;
	}
	//setters:
	public void setNext(Node n) {next=n;}
	public void setPrev(Node n) {prev=n;}
	//getters:
	public Name getData() {return this.data; }
	public Node getPrev() {return this.prev;}
	public Node getNext() {return this.next;}
    }
    //initialize head of list
    private Node head=null;
    //initialize tail of list
    private Node tail=null;
    //initialize size
    private int size=0;
    //constructor:
    public NameDLL(){}
    //returns size of list
    public int size() { return size;}
    //checks whether lists is empty
    public boolean isEmpty() {return size==0;}
    //returns Name stored in first Node
    public Name first(){
	if (isEmpty()) {return null;}
	else {return head.getData();}
    }
    //returns Name stored in last Node
    public Name last(){
	if(isEmpty()) {return null;}
	else {return tail.getData();}
    }
    /* adds Name to the end of list
     * @param name : the Name object to be added
     * @return  void
     */
    public void addLast(Name name){
	Node newest = new Node(name,tail,null);
	if(isEmpty()) {head=tail=newest;}
	else{
	    tail.setNext(newest);
	    tail=newest;
	}
	size++;
    }
    /* adds Name to the start of list
     * @param name : the Name object to be added
     * @return  void
     */ 
    public void addFirst(Name name){
	Node newest= new Node(name,null,head);
	if(isEmpty()) {head=tail=newest;}
	else{
	    head.setPrev(newest);
	    head= newest;
	}
	size++;
    }
    /* adds Name between two nodes
     * @param name : the Name object to be added
     * @param prev : future previous node
     * @param next : future next node
     * @return  void
     */
    public void addBTW(Name name, Node prev, Node next){
	Node newest = new Node(name,prev,next);
	prev.setNext(newest);
	next.setPrev(newest);
	size++;
    }
    /* removes name in the beginning of the list
     * @return  name object removed
     */
    public Name removeFirst(){
	if(isEmpty()) {return null;}
	Node target=head;
	if(head == tail){
	    head=tail=null;
	}
	else{
	    head=head.getNext();
	    head.setPrev(null);
	}
	size--;
	return target.getData();
    }
    /* removes name from any node in the list
     * @param node : node containing Name to be removed
     * @return  name removed
     */
    public  Name remove(Node n){
	if (head == n) {removeFirst();}
	else if (tail == n) {removeLast();}
	else{
	    n.getPrev().setNext(n.getNext());
	    n.getNext().setPrev(n.getPrev());
	}
	size--;
	return n.getData();
    }
    /* removes Name from end of the list
     * @return  Name removed
     */
    public Name removeLast(){
	if(isEmpty()) {return null;}
	Node target=tail;
	if(head == tail){
	    head=tail=null;
	}
	else{
	    tail=tail.getPrev();
	    tail.setNext(null);
	}
	size--;
	return target.getData();
	
    }
    //override toString()
    public String toString(){
	String s = new String();
	for(Node n=head; n!= null; n=n.getNext()){
	    s+= n.getData();
	    if( n!= tail){ s+=", ";
	    }}
	return s;
	
    }
    /* insert Name before a certain node
     * @param name : the Name object to be inserted
     * @param node : node to be after new inserted name
     * @return  void
     */
    public void insertBefore(Name name,Node n){
	if (n == head) this.addFirst(name);
	else this.addBTW(name,n.getPrev(),n);
    }
    public void insertSorted(Name name ){
	if (this.isEmpty()) this.addFirst(name);
	else{
	    boolean placed = false;
	    for(Node n=head; n!= null; n=n.getNext()){
		if(name.getName().compareToIgnoreCase(n.getData().getName())<0){
		    this.insertBefore(name,n);
		    placed=true;
		    break;
		}
		
	    }if (!placed) this.addLast(name);
	}
    }
    /* return Name at node of number nodeNumber
     * @param nodeNumber : number of Node
     * @return  Name at node of number nodeNumber
     */
    public Name getListElement(int nodeNumber){
	int count=1;
	Node n= head;
	
	while( count != nodeNumber){
	    n=n.getNext();
	    count++;
	}
	return n.getData();
    }
    /* returns the ranking of a name in the LinkedList
     * @param name : the Name whose ranking will be determined
     * @return  ranking of the linkedList
     */
    public int getLLRank(String name){
	int count=1;
        Node n=head;
	while(!n.getData().getName().equals(name) && n!=null){
	    n=n.getNext();
	    count++;
	}
	return count;
    }
}
   
