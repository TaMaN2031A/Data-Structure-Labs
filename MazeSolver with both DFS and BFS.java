package com.company;
import java.awt.*;
import java.util.*;
import java.math.*;
import java.io.*;

import java.util.Arrays;
import java.util.Locale;

interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item);
    /*** Removes the object at the queue rear and returnsit.*/
    public Object dequeue();
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
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




    public void print(){ //*same as single*//
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
interface IStack {

    /*** Removes the element at the top of stack and returnsthat element.
     * @return top of stack element, or through exception if empty
     */

    public Object pop();

    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */

    public Object peek();

    /*** Pushes an item onto the top of this stack.
     * @return object to insert*
     */

    public void push(Object element);

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();

    public int size();
}

class MyStack implements IStack {

    class Node {
        Object data;
        Node next;
    }

    Node head = null;

    public void add(Object element) {
        Node current = new Node();
        current.data = element;
        current.next = null;
        Node temp = head;
        if (head == null)
            head = current;
        else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = current;
        }
    }

    public void display() {
        Node current = head;
        System.out.print("[");
        for (int i = 0; current != null; ++i) {
            System.out.print(current.data);
            if (current.next != null)
                System.out.print(", ");
            current = current.next;
        }
        System.out.print("]");
    }


    public void push(Object element) {
        Node current = new Node();
        current.data = element;
        current.next = head;
        head = current;
    }


    public Object pop() {
        if (head == null) {
            System.out.println("Error");
            System.exit(0);
        }
        Object obj = head.data;
        head = head.next;
        return obj;
    }

    public Object peek() {
        if (head == null) {
            System.out.println("Error");
            System.exit(0);
        }
        return head.data;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    public int size() {
        Node current = new Node();
        int size = 0;
        if (head == null)
            return size;

        current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

}

interface IMazeSolver {
    /**
     * Read the maze file, and solve it using Breadth First Search
     * @param maze maze file
     * @return the coordinates of the found path from point ’S’
     *
    to point ’E’ inclusive, or null if no path is found.
     */
    public int[][] solveBFS(java.io.File maze) throws FileNotFoundException;
    /**
     * Read the maze file, and solve it using Depth First Search
     * @param maze maze file
     * @return the coordinates of the found path from point ’S’
     *
    to point ’E’ inclusive, or null if no path is found.
     */
    public int[][] solveDFS(java.io.File maze) throws FileNotFoundException;
}
class LinkedListQueue implements IQueue {
    static DoublyLinkedList dbl = new DoublyLinkedList();
    static LinkedListQueue Q = new LinkedListQueue();

    public void enqueue(Object item) {
        dbl.add(item);
    }

    public Object dequeue() {
        dbl.pointer = dbl.head;
        dbl.remove(0);
        return dbl.pointer.elm;
    }

    public boolean isEmpty() {
        return dbl.isEmpty();
    }

    public int size() {
        return dbl.size();
    }

}
public class MazeSolver implements IMazeSolver{
    LinkedListQueue Q = new LinkedListQueue();
    public int[][] solveBFS(java.io.File maze) throws FileNotFoundException {
        int[][] error = new int[2][1];
        error[0][0] = -1; error[1][0] = -1;
        Scanner in = new Scanner(maze);
        String[] s = in.nextLine().split(" ");
        int M = Integer.parseInt(s[0]), N = Integer.parseInt(s[1]);
        char[][] arr = new char[M][N];
        for (int i = 0; in.hasNextLine(); i++) {
            arr[i] = in.nextLine().toCharArray();
        }
        String root = "haha";
        String hold = "hehe"; String[] held;
        int[][] answer= new int[100][2];
        String[][] parents = new String[M][N];
        Boolean[][] visited = new Boolean[M][N];
        for(int i = 0; i < M; i++){
            for (int j = 0; j < N; j++)
                visited[i][j] = false;
        }
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(arr[i][j]=='S') {
                    root = i + "," + j;
                    parents[i][j] = -1+","+-1;
                    break;
                }
            }
        }
        if(root.equals("haha")) return error;
        else {
            Q.enqueue(root);
            String tank;
            boolean found = false; // for the E
            int i, j, k, m = -1, n = -1, x = -1, y = -1;
            while (!Q.isEmpty()&&!found) {
                hold = String.valueOf(Q.dequeue());
                held = hold.split(",");
                //visited part
                i = Integer.parseInt(held[0]);
                j = Integer.parseInt(held[1]);
                visited[i][j] = true;
                for (k = 0; k < 4; k++) {
                    if (k == 0) {
                        m = i;
                        n = j - 1;
                    }//check left
                    else if (k == 1) {
                        m = i;
                        n = j + 1;
                    }//check right
                    else if (k == 2) {
                        m = i - 1;
                        n = j;
                    }//check up
                    else if (k == 3) {
                        m = i + 1;
                        n = j;
                    }

                    if (n < 0 || m < 0 || n >= N || m >= M || arr[i][j] == '#' || visited[m][n]) {
                    }else if (arr[m][n] == '.') {
                        tank = m + "," + n;
                        Q.enqueue(tank);
                        parents[m][n] = i + "," + j;
                    } else if (arr[m][n] == 'E') {
                        x = m;
                        y = n;
                        parents[m][n] = i + "," + j;
                        answer[0][0]=m;answer[0][1]=n;
                        //System.out.println("{"+m+","+n+"}");
                        while (!Q.isEmpty())
                            Q.dequeue();
                        found = true;
                    }
                }
            }
            if (x == -7 || y == -7)
                return error;
            else{
                String[] tenk = new String[2];
                tank = parents[x][y]; int r = 1;
                tenk = tank.split(",");
                x = Integer.parseInt(tenk[0]);
                y = Integer.parseInt(tenk[1]);
                while (!tank.equals("-1,-1")) {
                    answer[r][0] = x; answer[r][1] = y; r++;
                    //System.out.println("{" + tank + "}");
                    tank = parents[x][y];
                    tenk = tank.split(",");
                    x = Integer.parseInt(tenk[0]);
                    y = Integer.parseInt(tenk[1]);
                }
                int[][] bfs = new int[r][2];
                for(int w = 0; w < r; w++){
                    bfs[r-w-1][0] = answer[w][0];
                    bfs[r-w-1][1] = answer[w][1];
                }
                return(bfs);
            }
        }
    }


    public int[][] solveDFS(java.io.File maze) throws FileNotFoundException {

        // handle the input
        Scanner in = new Scanner(maze);
        String[] s = in.nextLine().split(" ");
        int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
        char[][] grid = new char[n][m];
        for (int i = 0; in.hasNextLine(); i++)
            grid[i] = in.nextLine().toCharArray();

        // get the start point which we will start from it
        int x=0,y=0;
        boolean found=false; //this variable found made to break when we found the start point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j]=='S'){
                    x=i;
                    y=j;
                    found=true;
                    break;
                }
            }
            if(found)break;
        }
        Point p=new Point(x,y);  //create the start point
        MyStack stack =new MyStack();
        stack.push(p);
        while (true){                  /* we get the point and change this position in the grid to #
                                          as not to return to this point again
                                        */
            x=((Point)stack.peek()).x;
            y=((Point)stack.peek()).y;
            grid[x][y]='#';

            //check down
            if(x+1<n) {
                if(grid[x + 1][y] == 'E'){
                    stack.push(new Point(x+1,y));
                    break;
                }
                else if (grid[x + 1][y] == '.') {
                    stack.push(new Point(x+1,y));
                    continue;
                }
            }
            //check left
            if(y-1>=0) {
                if(grid[x][y-1] == 'E'){
                    stack.push(new Point(x,y-1));
                    break;
                }

                else if (grid[x][y - 1] == '.') {
                    stack.push(new Point(x,y-1));
                    continue;
                }
            }
            //check up

            if(x-1>=0) {
                if(grid[x - 1][y] == 'E'){
                    stack.push(new Point(x-1,y));
                    break;
                }
                else if (grid[x - 1][y] == '.') {
                    stack.push(new Point(x-1,y));
                    continue;
                }
            }
            //check right
            if(y+1<m) {
                if(grid[x][y+1] == 'E'){
                    stack.push(new Point(x,y+1));
                    break;
                }
                if (grid[x][y + 1] == '.') {
                    stack.push(new Point(x,y+1));
                    continue;
                }
            }
            stack.pop(); /*if the stack is empty and we didn't yet reach to the end ,
                         this mean that there is no path */
            if(stack.isEmpty()){
                System.out.println("there is no path");
                System.exit(0);
            }

        }
        int [][]dfs = new int[stack.size()][2];
        for (int i = stack.size()-1; i >=0; i--) {
            dfs[i][0]=((Point)stack.peek()).x;
            dfs[i][1]=((Point)stack.pop()).y;
        }
        return dfs;
    }
    public void print(int [][]arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(Locale.ENGLISH,"{%d, %d}",arr[i][0],arr[i][1]);
            if(i< arr.length-1)
                System.out.print(", ");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File maze = new File("C:\\Users\\LeNoVo\\Desktop\\Maze Solver.txt");
        Scanner scan=new Scanner(System.in);
        try {
            MazeSolver m = new MazeSolver();
            int[][] dfs;
            int[][] bfs;
            bfs = m.solveBFS(maze);
            if(bfs[0][0] == -1 && bfs[1][0]==-1)
                System.out.println("Path not found");
            else {
                System.out.print("BFS: ");
                m.print(bfs);
            }
            dfs = m.solveDFS(maze);
            System.out.print("\nDFS: ");
            m.print(dfs);
        }
        catch (Exception e){
            System.out.println( "Error : "+e.getMessage());
        }
    }
}
