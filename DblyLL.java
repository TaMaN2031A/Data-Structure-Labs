import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java .util.Scanner;
interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public void sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}



public class DoublyLinkedList implements ILinkedList {
    public class node {
        public Object elm ;
        node next ;
        node prev ;

    }
    node head=null;
    node tail=null;
    static int size=0;

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


    }
    public void print(){ //*same as single*//
        node p=head;
        System.out.print("[");
        for(int i=0;i<size;i++){

            System.out.print(p.elm);
            p=p.next;
            if(i != size - 1)
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
        return ;



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
        return ;}




    public void clear(){  //*same as single*//
        node x=null;
        head=x;
        size=0;
        return ;}

    public boolean isEmpty(){if(size==0)return true; //*same as single*//
    else return false;}





    public void remove(int index) {

        if(index != 0){
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
            counter.next = counter.next.next; // It could be done with prev,
            // for clarity we chosed same approach
            // of single linked list
        }
        }
        else
            head = head.next;
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

        return ;}
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












    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String a=in.nextLine().replaceAll("\\[|\\]","");
        String[] arr =a.split(", ");
        String op=in.nextLine();

        DoublyLinkedList p=new DoublyLinkedList();

        if(arr.length == 1 && arr[0].isEmpty()){}
        else{
            for(int i=0;i<arr.length;i++){
                p.add(Integer.parseInt(arr[i]));
            }}



        if(op.equals("add")){
            Object element=in.nextInt();
            p.add(element);
            p.print();



        }

        else if (op.equals("addToIndex") ){
            int ind=in.nextInt();
            Object element=in.nextInt();
            if(ind < size && ind >= 0 || size == 0 && ind == 0) {
                p.add(ind, element);
                p.print();
            }

            else
            System.out.print("Error");
        }



        else if (op.equals("get")){
            int ind=in.nextInt();
            if(ind < size && ind >= 0) {
                Object x;
                x = p.get(ind);

                if (x == "null") {
                    System.out.println("Error");
                } else System.out.println(x);
            }
            else
                System.out.print("Error");

        }
        else if (op.equals("set")){
            int ind=in.nextInt();
            Object element=in.nextInt();
            if(ind < size && ind >= 0) {
                p.set(ind,element);
                p.print();
            }
            else
                System.out.print("Error");

        }
        else if (op.equals("clear")){

            p.clear();
            p.print();



        }
        else if (op.equals("isEmpty")){

            boolean empty;
            empty=p.isEmpty();
            if(empty){System.out.println("True");}
            else System.out.println("False");


        }
        else if (op.equals("remove")){
            int ind=in.nextInt();
            if(ind < size && ind >= 0) {
                p.remove(ind);
                p.print();
            }
            else
                System.out.print("Error");
        }
        else if (op.equals("sublist")) {
            int start_ind = in.nextInt();
            int end_ind = in.nextInt();
            if (start_ind <= end_ind) {
                if (start_ind < size && start_ind >= 0 && end_ind < size && end_ind >= 0)
                    p.sublist(start_ind, end_ind);
                else
                    System.out.print("Error");
            }
            else
                System.out.print("Error");
        }



        else if (op.equals("contains")){
            Object element=in.nextInt();

            boolean contain;
            contain=p.contains(element);
            if(contain){System.out.println("True");}
            else System.out.println("False");


        }
        else if (op.equals("size")){


            System.out.println(size);


        }





        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    }
}

