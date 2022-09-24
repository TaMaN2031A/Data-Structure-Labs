import java.util.Scanner;

interface IQueue {
    /**
     * Inserts an item at the queue front.
     */
    public void enqueue(Object item);
    /**
     * Removes the object at the queue rear and returns it.
     */
    public Object dequeue();
    /**
     * Tests if this queue is empty.
     */
    public boolean isEmpty();
    /**
     * Returns the number of elements in the queue
     */
    public int size();
}
interface ILinkedList {

    public void add(int index, Object element);
    public void add(Object element);
    public Object get(int index);
    public void set(int index, Object element);
    public void clear();
    public boolean isEmpty();
    public void remove(int index);
    public int size();
    public void sublist(int fromIndex, int toIndex);
    public boolean contains(Object o);
}





class DoublyLinkedList implements ILinkedList{
    class node {
        public Object elm ;
        node next ;
        node prev ;

    }
    int size=0;




    node head=null;
    node tail=null;
    //  public static int size=0;
    node pointer;
    public void add(Object element){ //*same as single*//
        if(size==0){
            node no =new node();
            no.elm=element;
            no.next=null;
            no.prev=null;
            head=no;
            tail=no;
            size++;
        }
        else{
            node f=new node();
            f.elm=element;
            tail.next=f;
            f.prev=tail;
            tail=f;
            tail.next=null;
            size++;
        }
        pointer = head;
    }




    public void print(String operation){ //*same as single*//
        node p=tail;
        System.out.print("[");
        for(int i=size()-1;i>=0;i--){
            System.out.print(p.elm);
            p=p.prev;
            if(i != 0)
                System.out.print(", ");
        }
        System.out.print("]");
    }






    public void add(int index, Object element)
    {
        node added = new node();
        added.elm = element;
        if(size == 0)
        {
            added.next = null;
            added.prev = null;
            head = added;
            tail = added;
        }
        else {
            node counter;
            if(index <= size/2) {
                counter = head;
                for (int i = 0; i < index - 1; i++)
                    counter = counter.next; // stopped right before the required index
            }
            else
            {
                counter = tail;
                // stopped right before the req index to make it similar the manupilation uphere
                for(int i = 0; i < size - index; i++)
                    counter = counter.prev;
            }

            if(index!=0) {
                added.next = counter.next;
                added.prev = counter;
                counter.next.prev = added;
                counter.next = added;
            }
            else
            {
                added.next = head;
                head = added;
                head.prev = null;
            }
        }
        size++;
    }

    public Object get(int index){
        node count = head;
        if(index<=size){
            for(int j=0;j<size;j++){
                if(j==index){
                    return count.elm;
                }
                count=count.next;
            }
        }
        return ("null");
    }

    public void set(int index, Object element){
        node counter;
        if(index <= size/2)
        {
            counter = head;
            for (int i = 0; i < index; i++)
                counter = counter.next;
            counter.elm = element;
        }
        else
        {
            counter = tail;
            for (int i = 0; i < size - index - 1; i++)
                counter = counter.prev;
            counter.elm = element;
        }
        return ;
    }

    public void clear(){  //*same as single*//
        node x=null;
        head=x;
        tail=x;
        size=0;
        return ;}

    public boolean isEmpty(){if(size==0)return true; //*same as single*//
    else return false;
    }

    public void remove(int index) {

        if(index != 0 && index != size-1){
            node counter;

            if (index <= size / 2) {
                counter = head;
                for (int i = 0; i < index - 1; i++)
                    counter = counter.next; // To stop just after the removed node
                // Erga3 mrten w 5alli elly 2odamak elle m3ana
                counter.next = counter.next.next;


            } else {
                counter = tail;
                for (int i = 0; i < size - index ; i++)
                    counter = counter.prev; // To stop just before the removed node
                counter.next = counter.next.next;
                // It could be done with prev,
                // for clarity we chosed same approach
                // of single linked list
            }
        }
        else if(index == 0)
            head = head.next; // Feha 7aga me4 tmam
        else if(index == size-1)
            tail = tail.prev;
        if(size == 1)
        {
            head = tail = null;
        }
        size--;
        return;
    }

    public int size(){return size;} //*same as single*//

    public void sublist(int fromIndex, int toIndex){
        node counter = head;
        System.out.print("[");
        for(int i = 0; i < size; i++)
        {
            if(i >= fromIndex && i <= toIndex)
            {
                System.out.print(counter.elm);
                if(i != toIndex)
                    System.out.print(", ");
            }
            counter = counter.next;
        }
        System.out.print("]");

        return ;
    }
    public boolean contains(Object o){
        if(head != null) {
            node contain = head;
            for (int j = 0; j < size; j++) {
                if (contain.elm == o) {
                    return true;
                }
                contain = contain.next;
            }
        }
        return false;
    }
}

public class ArrayQueue implements IQueue{
    static int n = 1000;
    static int f=0,r=0;
    static int[] arr = new int[n];
    static ArrayQueue obj = new ArrayQueue();
    public void enqueue(Object item){
        r=(r+1)%n;
        int element = Integer.valueOf(String.valueOf(item));
        arr[r]=element;
    }
    public Object dequeue(){
        int temp = arr[f];//as required
        arr[f] = 0;
        f=(f+1)%n;
        return temp;
    }
    public int size(){
        return (n-f+r)%n;
    }
    public boolean isEmpty(){
        return f==r;
    }

    public void print(){
        System.out.print("[");
        for(int i=r;i>f;i--){

            System.out.print(arr[i]);
            if(i !=  f+1)
                System.out.print(", ");
        }
        System.out.print("]");
    }


    public static void main(String[] args) {
        DoublyLinkedList dbl = new DoublyLinkedList();
        int x=0;int j;
        Scanner in = new Scanner(System.in);
        String tank = new String();
        try {
            while (true) {
                tank = in.nextLine();
                dbl.add(tank); // Checked
            }
        } catch(Exception e) {
            if (dbl.size != 0) {
                    int i=0;
                    String sin = String.valueOf(dbl.get(0)).replaceAll("\\[|\\]", "");
                    String[] s;
                    dbl.remove(0);
                    if (sin.length() > 0) {
                        s = sin.split(", ");
                        for(; i < s.length ; i++)
                            arr[s.length-i] = Integer.valueOf(s[i]);
                    }
                    r = i;
                    tank = String.valueOf(dbl.get(0));
                    dbl.remove(0);
                    if(tank.equals("enqueue")){
                        if(obj.size()!=n-1){
                        obj.enqueue(dbl.get(0));
                        dbl.remove(0);
                        obj.print();
                        }
                        else
                            System.out.println("Error");
                    }
                    else if(tank.equals("dequeue")) {
                        if (obj.isEmpty()) System.out.println("Error");
                        else {
                            obj.dequeue();
                            obj.print();
                        }
                    }
                    else if(tank.equals("size")){
                        System.out.println(String.valueOf(obj.size()));
                    }
                    else if(tank.equals("isEmpty")){
                        if(obj.isEmpty())
                            System.out.println("True");
                        else
                            System.out.println("False");
                    }
                }
            else
                System.out.println("Error");
            }
    }
}

