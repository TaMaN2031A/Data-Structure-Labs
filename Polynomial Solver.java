import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;




interface IPolynomialSolver {

    void setPolynomial(char poly, int[][] terms);
    String print(char poly);
    void clearPolynomial(char poly);
    float evaluatePolynomial(char poly, float value);
    int[][] add(char poly1, char poly2);
    // int[][] subtract(char poly1, char poly2);
    int[][] multiply(char poly1, char poly2);

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
    node pointer = head;
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











public class Solution implements IPolynomialSolver{

    static int[][] terms =new int [12][50];

    static int row=0;
    static int [] lenghts=new int[6];
    //  static  PolynomialSolver po=new PolynomialSolver();
    DoublyLinkedList M=new DoublyLinkedList();
    DoublyLinkedList A=new DoublyLinkedList();
    DoublyLinkedList B=new DoublyLinkedList();
    DoublyLinkedList C=new DoublyLinkedList();
    DoublyLinkedList R=new DoublyLinkedList();


    public void setPolynomial(char poly, int[][] terms) {

        String ch;


        if(poly=='A'){


            row=0;

            for(int i=0;i<lenghts[0];i++){

                A.add(terms[row][i]);
            }




        }


        else if (poly=='B'){
            row=2;

            for(int i=0;i<lenghts[1];i++){

                B.add(terms[row][i]);
            }

        }
        else if(poly=='C'){


            row=3;

            for(int i=0;i<lenghts[0];i++){

                C.add(terms[row][i]);

            }

        }



    }

    public String print(char poly){
        StringBuilder printer = new StringBuilder();
        int row = 0;
        boolean noprinted = true;
        if(poly == 'A') row = 0;else if(poly == 'B') row = 1;else if(poly == 'C') row = 2; else if(poly == 'R') row = 3;               else if(poly == 'M') row = 4; else if(poly == 'S') row = 5;
        for(int i=0;i<lenghts[row];i++) {
            if (terms[2 * row][i] != 0)
            {
                if (terms[2 * row][i] > 0 && i != 0 && terms[2 * row][i-1] != 0)
                    printer.append("+");
                if(i == lenghts[row]-1 && terms[2 * row][i] ==1)
                    printer.append(1);
                if(terms[2 * row][i] != 1 && terms[2 * row][i] != -1)
                    printer.append(terms[2 * row][i]);
                else if(terms[2*row][i] == -1)
                    printer.append("-");

                if(terms[2*row+1][i] > 1 || terms[2*row+1][i] < 0)
                {
                    printer.append("x");
                    printer.append("^");
                    int x = lenghts[row]-i-1;
                    printer.append(x);
                }
                else if(terms[2*row+1][i] == 1)
                    printer.append("x");
            }
        }
        return printer.toString();
    }




    public void clearPolynomial(char poly){

        if (poly=='A'){A.clear();A.print();}
        if(poly=='B'){B.clear();B.print();}
        if(poly=='C'){C.clear();C.print();}
    }
    public float evaluatePolynomial(char poly, float value){

        float x=0;
        if(poly=='A'){
            for(int i =0;i<A.size;i++){

                x=x+Float.parseFloat(String.valueOf(A.get(i)))*(float)Math.pow(value,lenghts[0]-i-1);

            }

        }
        else if(poly=='B'){

            for(int i =0;i<B.size;i++){

                x=x+Float.parseFloat(String.valueOf(B.get(i)))*(float)Math.pow(value,lenghts[1]-i-1);

            }}
        else if (poly=='C'){

            for(int i =0;i<C.size;i++){

                x=x+Float.parseFloat(String.valueOf(C.get(i)))*(float)Math.pow(value,lenghts[2]-i-1);

            }
        }
        return x;
    }
    public int[][] subtract(char poly1, char poly2){
        
           int l ;
        if(poly1=='A'&& poly2=='B'||poly2=='A'&& poly1=='B'){
            if(lenghts[0]==lenghts[1]){
                for(int i=0;i<lenghts[0];i++)
                    R.add((Integer.parseInt(String.valueOf(A.get(i))))-(Integer.parseInt(String.valueOf(B.get(i)))) );
            }
            else if (lenghts[0]!=lenghts[1]){
                if(lenghts[0]>lenghts[1]){
                    l=A.size-B.size;
                    for(int i=0;i<l;i++)B.add(0,0);
                    for(int i=0;i<lenghts[0];i++)
                       R.add((Integer.parseInt(String.valueOf(A.get(i))))-(Integer.parseInt(String.valueOf(B.get(i)))));
                    for(int i=0;i<l;i++){B.remove(0);}
                }
                else if(lenghts[0] < lenghts[1]){
                    l=B.size-A.size;
                    for(int i=0;i<l;i++){A.add(0,0);}
                    for(int i=0;i<lenghts[1];i++)
                        R.add((Integer.parseInt(String.valueOf(A.get(i))))-(Integer.parseInt(String.valueOf(B.get(i)))) );
                    for(int i=0;i<l;i++){A.remove(0);}
                }
            }
        }
        else if(poly1=='A'&&poly2=='C'||poly2=='A'&&poly1=='C') {

            if (lenghts[0] == lenghts[2]) {

                for (int i = 0; i < lenghts[0]; i++) {

                    R.add((Integer.parseInt(String.valueOf(A.get(i)))) - (Integer.parseInt(String.valueOf(C.get(i)))));
                }


            } else if (lenghts[0] != lenghts[2]) {


                if (lenghts[0] > lenghts[2]) {
                    l = A.size - C.size;
                    for (int i = 0; i < l; i++) {
                        C.add(0, 0);
                    }

                    for (int i = 0; i < lenghts[0]; i++) {
     R.add((Integer.parseInt(String.valueOf(A.get(i)))) - (Integer.parseInt(String.valueOf(C.get(i)))));
                    }
                    for (int i = 0; i < l; i++) {
                        C.remove(0);
                    }


                } else if (lenghts[0] < lenghts[2]) {
                    l = C.size - A.size;
                    for (int i = 0; i < l; i++) {
                        A.add(0, 0);
                    }
                    for (int i = 0; i < lenghts[2]; i++) {
   R.add((Integer.parseInt(String.valueOf(A.get(i)))) - (Integer.parseInt(String.valueOf(C.get(i)))));
                    }
                    for (int i = 0; i < l; i++) {
                        A.remove(0);
                    }
                }
            }
        }
        else if(poly1=='B'&&poly2=='C'||poly2=='B'&&poly1=='C'){



            if(lenghts[1]==lenghts[2]){

                for(int i=0;i<lenghts[1];i++){

                    R.add((Integer.parseInt(String.valueOf(B.get(i))))-(Integer.parseInt(String.valueOf(C.get(i)))) );
                }


            }
            else if (lenghts[1]!=lenghts[2]){
                if(lenghts[2]>lenghts[1]){
                    l=C.size-B.size;
                    for(int i=0;i<l;i++){B.add(0,0);}
                    for(int i=0;i<lenghts[2];i++){
                        R.add((Integer.parseInt(String.valueOf(B.get(i))))-(Integer.parseInt(String.valueOf(C.get(i)))) );
                    }
                    for(int i=0;i<l;i++){B.remove(0);}
                }
                else if(lenghts[2] < lenghts[1]){
                    l=B.size-C.size;
                    for(int i=0;i<l;i++){C.add(0,0);}
                    for(int i=0;i<lenghts[1];i++){
                        R.add((Integer.parseInt(String.valueOf(B.get(i))))-(Integer.parseInt(String.valueOf(C.get(i)))) );
                    }
                    for(int i=0;i<l;i++){C.remove(0);}
                }
            }
        }
        else{System.out.println("Error");}
        if(poly1=='A'&& poly2=='B'||poly1=='A'&&poly2=='C'||poly1=='B'&&poly2=='C'){
            
            row=10;
        lenghts[5]=R.size;
        for(int i=0;i<lenghts[5];i++)
            terms[row][i]=(Integer.parseInt(String.valueOf(R.get(i))));row++;
        for(int i=0;i<lenghts[5];i++)
            terms[row][i]=lenghts[5]-i-1;

        row++;
        
        }
        if(poly2=='A'&& poly1=='B'||poly2=='A'&&poly1=='C'||poly2=='B'&&poly1=='C'){
            row=10;
        lenghts[5]=R.size;
        for(int i=0;i<lenghts[5];i++)
            terms[row][i]=(-1*Integer.parseInt(String.valueOf(R.get(i))));

        row++;
        for(int i=0;i<lenghts[5];i++)
            terms[row][i]=lenghts[5]-i-1;

        row++;
        }
        
        
        R.clear();
        return terms;
    }


    public int[][] multiply(char poly1, char poly2)
    {
        int row1 = 0, row2 = 0, multRow = 4;
        if(poly1=='A')row1=0;else if(poly1=='B')row1=1;else if(poly1=='C')row1=2;else System.out.println("Error"); // could print Error here
        if(poly2=='A')row2=0;else if(poly2=='B')row2=1;else if(poly2=='C')row2=2;else System.out.println("Error");
        lenghts[multRow] = lenghts[row1] + lenghts[row2] -1 ;
        for(int i = 0; i < lenghts[row1]; i ++) {
            for (int j = 0; j < lenghts[row2]; j++)
            {
                terms[multRow*2][i+j] += terms[row1*2][i]*terms[row2*2][j];
            }
        }
        for(int i=0;i<lenghts[multRow];i++)
             terms[multRow*2+1][i]=lenghts[multRow]-i-1;
        return terms;
    }


    public int[][] add(char poly1, char poly2){
        int l ;
        if(poly1=='A'&& poly2=='B'||poly2=='A'&& poly1=='B'){
            if(lenghts[0]==lenghts[1]){
                for(int i=0;i<lenghts[0];i++)
                    R.add((Integer.parseInt(String.valueOf(A.get(i))))+(Integer.parseInt(String.valueOf(B.get(i)))) );
            }
            else if (lenghts[0]!=lenghts[1]){
                if(lenghts[0]>lenghts[1]){
                    l=A.size-B.size;
                    for(int i=0;i<l;i++)B.add(0,0);
                    for(int i=0;i<lenghts[0];i++)
                        R.add((Integer.parseInt(String.valueOf(A.get(i))))+(Integer.parseInt(String.valueOf(B.get(i)))) );
                    for(int i=0;i<l;i++){B.remove(0);}
                }
                else if(lenghts[0] < lenghts[1]){
                    l=B.size-A.size;
                    for(int i=0;i<l;i++){A.add(0,0);}
                    for(int i=0;i<lenghts[1];i++)
                        R.add((Integer.parseInt(String.valueOf(A.get(i))))+(Integer.parseInt(String.valueOf(B.get(i)))) );
                    for(int i=0;i<l;i++){A.remove(0);}
                }
            }
        }
        else if(poly1=='A'&&poly2=='C'||poly2=='A'&&poly1=='C') {

            if (lenghts[0] == lenghts[2]) {

                for (int i = 0; i < lenghts[0]; i++) {

                    R.add((Integer.parseInt(String.valueOf(A.get(i)))) + (Integer.parseInt(String.valueOf(C.get(i)))));
                }


            } else if (lenghts[0] != lenghts[2]) {


                if (lenghts[0] > lenghts[2]) {
                    l = A.size - C.size;
                    for (int i = 0; i < l; i++) {
                        C.add(0, 0);
                    }

                    for (int i = 0; i < lenghts[0]; i++) {
                        R.add((Integer.parseInt(String.valueOf(A.get(i)))) + (Integer.parseInt(String.valueOf(B.get(i)))));
                    }
                    for (int i = 0; i < l; i++) {
                        C.remove(0);
                    }


                } else if (lenghts[0] < lenghts[2]) {
                    l = C.size - A.size;
                    for (int i = 0; i < l; i++) {
                        A.add(0, 0);
                    }
                    for (int i = 0; i < lenghts[2]; i++) {
                        R.add((Integer.parseInt(String.valueOf(A.get(i)))) + (Integer.parseInt(String.valueOf(B.get(i)))));
                    }
                    for (int i = 0; i < l; i++) {
                        A.remove(0);
                    }
                }
            }
        }
        else if(poly1=='B'&&poly2=='C'||poly2=='B'&&poly1=='C'){



            if(lenghts[1]==lenghts[2]){

                for(int i=0;i<lenghts[1];i++){

                    R.add((Integer.parseInt(String.valueOf(B.get(i))))+(Integer.parseInt(String.valueOf(C.get(i)))) );
                }


            }
            else if (lenghts[1]!=lenghts[2]){
                if(lenghts[2]>lenghts[1]){
                    l=C.size-B.size;
                    for(int i=0;i<l;i++){B.add(0,0);}
                    for(int i=0;i<lenghts[2];i++){
                        R.add((Integer.parseInt(String.valueOf(A.get(i))))+(Integer.parseInt(String.valueOf(B.get(i)))) );
                    }
                    for(int i=0;i<l;i++){B.remove(0);}
                }
                else if(lenghts[2] < lenghts[1]){
                    l=B.size-C.size;
                    for(int i=0;i<l;i++){C.add(0,0);}
                    for(int i=0;i<lenghts[1];i++){
                        R.add((Integer.parseInt(String.valueOf(A.get(i))))+(Integer.parseInt(String.valueOf(B.get(i)))) );
                    }
                    for(int i=0;i<l;i++){C.remove(0);}
                }
            }
        }
        row=6;
        lenghts[3]=R.size;
        for(int i=0;i<lenghts[3];i++)
            terms[row][i]=(Integer.parseInt(String.valueOf(R.get(i))));

        row++;
        for(int i=0;i<lenghts[3];i++)
            terms[row][i]=lenghts[3]-i-1;

        row++;
        R.clear();
        return terms;
    }







    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Object test;
        String scan,ch2,ch,ch1;
        char poly;

        DoublyLinkedList a= new DoublyLinkedList();
        Solution b= new Solution();


        try{

            while(true){
                scan=in.nextLine().replaceAll("\\[|\\]","");

                a.add(scan);


            }

        }
        catch(Exception e ){

            while(a.size!=0){test=String.valueOf(a.get(0));
                if(test.equals("set")){
                    ch=String.valueOf(a.get(1));
                    poly=ch.charAt(0);
                    String ch3;
                    if(poly=='A') {
                        row = 0;
                        ch3 = String.valueOf(a.get(2));
                        String[] arr = ch3.split(",");
                        lenghts[0] = arr.length;
                        if(lenghts[0]==0)
                        {
                            System.out.println("Error");
                            java.lang.System.exit(0); 
                        }
                        else{
                        for (int i = 0; i < lenghts[0]; i++)
                            terms[row][i] = (Integer.parseInt(arr[i]));
                        row++;
                        for (int i = 0; i < lenghts[0]; i++)
                            terms[row][i] = arr.length - i - 1;
                        row++;
                        for (int i = 0; i < 3; i++)
                            a.remove(0);
                          b.setPolynomial(poly,terms);
                        }
                    }
                    else if (poly=='B'){
                        row=2;
                        ch3=String.valueOf(a.get(2));
                        String[] arr=ch3.split(",");
                        lenghts[1]=arr.length;
                        if(lenghts[1]==0)
                        {
                            System.out.println("Error");
                            java.lang.System.exit(0); 
                        }
                        else{
                        for(int i=0;i<lenghts[1];i++)
                            terms[row][i]=(Integer.parseInt(arr[i]));
                        row++;
                        for(int i=0;i<lenghts[1];i++)
                            terms[row][i]=arr.length-i-1;
                        row++;
                        for(int i=0;i<3;i++)
                            a.remove(0);
                       b.setPolynomial(poly,terms);
                        }
                    }
                    else if(poly=='C'){
                        row=4;
                        ch3=String.valueOf(a.get(2));
                        String[] arr=ch3.split(",");
                        lenghts[2]=arr.length;
                        if(lenghts[2]==0)
                        {
                            System.out.println("Error");
                            java.lang.System.exit(0); 
                        }
                        else{
                        for(int i=0;i<lenghts[2];i++)
                            terms[row][i]=(Integer.parseInt(arr[i]));
                        row++;
                        for(int i=0;i<lenghts[2];i++)
                            terms[row][i]=arr.length-i-1;
                        row++;
                        for(int i=0;i<3;i++)
                            a.remove(0);
                         b.setPolynomial(poly,terms);
                    }
                    }
                    else {
                        System.out.println("Error");
                        java.lang.System.exit(0);
                    }
                }


                else if(test.equals("print")){
                    String ans;
                    char c;
                    ch1=String.valueOf(a.get(1));
                    c=ch1.charAt(0);
                    String printer = b.print(c);
                    System.out.println(printer);
                    for(int i=0;i<2;i++){
                        a.remove(0);
                    }
                }






                else if(test.equals("clear")){
                    ch=String.valueOf(a.get(1));
                    poly=ch.charAt(0);
                    b.clearPolynomial(poly);
                    for(int i=0;i<2;i++){a.remove(0);}
                }


                else if (test.equals("eval")){
                    float ev,num;
                    ch1=String.valueOf(a.get(1));
                    poly=ch1.charAt(0);
                    ev=Float.parseFloat(String.valueOf(a.get(2)));
                    num=b.evaluatePolynomial(poly,ev);
                    for(int i=0;i<3;i++){a.remove(0);}
                    System.out.println((int)num);
                }

                else if (test.equals("add")){
                    String c;
                    char poly2;
                    ch1=String.valueOf(a.get(1));
                    poly=ch1.charAt(0);
                    c=String.valueOf(a.get(2));
                    poly2=c.charAt(0);
                    terms= b.add(poly,poly2);
                    System.out.println(b.print('R'));
                    for(int i=0;i<3;i++){a.remove(0);}
                }
                else if (test.equals("mult"))
                {
                    char poly2;
                    poly = String.valueOf(a.get(1)).charAt(0);
                    poly2 = String.valueOf(a.get(2)).charAt(0);
                    terms = b.multiply(poly,poly2); 
                    System.out.println(b.print('M'));
                    for(int i=0;i<3;i++){a.remove(0);}
                }
                 else if (test.equals("sub")){
                    char poly2;
                    poly = String.valueOf(a.get(1)).charAt(0);
                    poly2 = String.valueOf(a.get(2)).charAt(0);
                    terms= b.subtract(poly,poly2);
                    System.out.println(b.print('S'));
                    for(int i=0;i<3;i++){a.remove(0);}
                }

            }
        }           
    }
}
