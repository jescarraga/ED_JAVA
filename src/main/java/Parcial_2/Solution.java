
package Parcial_2;






import java.util.Scanner;

public class Solution {

    public static class H2p {
    
    String [] arr;
    int size;
    int maxSize;
    
    public H2p(int capacidad){
        this.size = 0;
        this.maxSize = capacidad;
        this.arr = new String[capacidad];
    }
    
    //Heap implementado con arrelgos desde la pos 1
    private int parent(int i){
        return i/2;
    }
    
    private int leftChild(int i){
        return 2*i;
    }
    
    private int rightChild(int i){
        return (2*i)+1;
    }
    
    private void siftUp(int i){
        //while (i > 1 && arr[parent(i)] < arr[i]){
        while (i >1 && compTo(arr[parent(i)],arr[i]) == -1){
            /* swap H[Parent(i)] and H[i]
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;*/
            String temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            // actualiza
            i = parent(i);
        }
    }
    
    private void siftDown(int i){
        int maxIndex = i;
        int l = leftChild(i);
        //if (l <= size && arr[l] > arr[maxIndex]) {
        if (l <= size && compTo(arr[l],arr[maxIndex]) == 1) {
            maxIndex = l;
        }
        int r = rightChild(i);
        //if (r<=size && arr[r]> arr[maxIndex]) {
        if (r <= size && compTo(arr[r],arr[maxIndex]) == 1) {
            maxIndex = r;
        }
        if (i != maxIndex) {
            /* swap H[i] and H[maxIndex]
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
            siftDown(maxIndex);*/
            String temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
            siftDown(maxIndex);
        }
    }
    
    public void insert(String p){
        if (size+1 == maxSize) {
            String[] nuevo = new String[(size+1)*2];
            for (int i = 1; i < arr.length; i++) {
                nuevo[i] = arr[i];
            }
            this.arr = nuevo;
            this.maxSize = (size+1)*2;
            size = size + 1;
            arr[size] = p;
            siftUp(size);
        }else{
            size = size + 1;
            arr[size] = p;
            siftUp(size);
        }
    }
    
    public String extractMax(){
        if (size != 0) {
            String result = arr[1];
            arr[1] = arr[size];
            size = size-1;
            siftDown(1);
            return result;
        }else{
            return "Heap vacio";
        }
        
    }
    
    public void remove(int i){
        arr[i] = "discapacitado";
        siftUp(i);
        extractMax();
    }
    
    public void imp_h2p(){
        for (int i = 1; i < arr.length ; i++) {
            System.out.print(this.arr[i] + " ");
        }
    }
    
    public void imp_h2p_pri(){
        int clientes = size;
        if (clientes != 0) {
            for (int j = 1; j <= clientes; j++) {
                System.out.println(this.extractMax());
            }
        }   
    }
    
    public int compTo(String a,String b){
        int copA = 0;
        int copB = 0;
        int res = 0;
        
        // obtenemos el nivel de prioridad de a
            switch(a) 
        { 
            case "discapacitado": 
                copA = 5;
                break; 
            case "conBebe": 
                copA = 4; 
                break; 
            case "embarazada": 
                copA = 3;
                break; 
            case "mayor": 
                copA = 2;
                break;
            default: // caso de otros
                copA = 1;
                break; 
        } 
        
        // obtenemos el nivel de prioridad de b
        switch(b)
        {
            case "discapacitado": 
                copB = 5;
                break; 
            case "conBebe": 
                copB = 4; 
                break; 
            case "embarazada": 
                copB = 3;
                break; 
            case "mayor": 
                copB = 2;
                break;
            default: // caso de otros
                copB = 1;
                break; 
        } 
        
        //Comparamos cual elemento posee mayor prioridad
        if (copA == copB) {
            return 0;
        }else if(copA > copB){
            return 1;
        }if (copA < copB) {
            return -1;
        }
        return -5;
    }
}
    
    public static class Node <J>{
    private Node next;
    private J data;

    public Node() {
        this.next = null;
        this.data = null;
    }
    
    public Node(Node sig, J info) {
        this.next = sig;
        this.data = info;
    }
    
     public Node(Node sig) {
        this.next = sig;
        this.data = null;
    }
     
     public Node(J valor) {
        this.next = null;
        this.data = valor;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public J getData() {
        return data;
    }

    public void setData(J data) {
        this.data = data;
    }

   
}
    
    public static class Queue <J>{
    private Node head;
    private Node tail;
    private int size;

    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }
    
    
    public Boolean isEmpty(){
        return size ==0;
    }
    
    public Node getHead(){
        return head;
    }
    
    public Node getTail(){
        return tail;
    }
    
    public void push(J valor){
        Node nuevo = new Node();
        nuevo.setData(valor);
        if((head == null)){
            head = nuevo;
            tail = nuevo;
            size = 1;
        }else{
            tail.setNext(nuevo);
            tail = nuevo;
            size++;
        }   
    }
    
    public J peekHead(){
        if(head==null){
            return (J) "No existe";
        }else{
            return (J) head.getData();
        } 
    }
    
    public J peekTail(){
        if(tail==null){
            return (J) "No existe";
        }else{
            return (J) tail.getData();
        } 
    }
    
    public Node pop(){
        Node mostrar = new Node();
        mostrar = head;   
        head = head.getNext();
        size--;
        return mostrar;
    }
    
    public int getSize(){
        return size;
    }
    
}

    public static boolean es_prioridad(String a){
        int copA = 0;
        
        // obtenemos el nivel de prioridad de a
            switch(a) 
        { 
            case "discapacitado": 
                copA = 5;
                break; 
            case "conBebe": 
                copA = 4; 
                break; 
            case "embarazada": 
                copA = 3;
                break; 
            case "mayor": 
                copA = 2;
                break;
            default: // caso de otros
                copA = 1;
                break; 
        } 
        
        if (copA == 1) {
            return false;
        }else{
            return true;
        }
    }
    
    public static void imp_cola_nor(Queue<String> a){
        int b = a.getSize();
        for (int i = 0; i < b; i++) {
            System.out.println(a.pop().getData());
        }
    }
    
    private static boolean num(String s){
	try {
		Integer.parseInt(s);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}
    
    public static void main(String[] args) {
        /*
        // Creamos el Scanner
        Scanner leer = new Scanner(System.in);
        //Obtenemos el numero de veces en que tenemos que relaizar un nuevo heap
        int heaps = leer.nextInt();
        for (int i = 0; i < heaps; i++) {
            // reemplazamos el heap anterior por uno nuevo vacio
            H2p cli = new H2p(1);
            Queue cli_n = new Queue();
            // Obtenemos el número de acciones realizadas por el banco
            int acciones = leer.nextInt();
            for (int j = 0; j < acciones; j++) {
                //String a = leer.nextLine();
                String a = leer.next();
                    if (a.equals("entra")) {
                        String b = leer.next();
                        if (es_prioridad(b)) {
                            cli.insert(b);
                        }else{
                            cli_n.push(b);
                        }   
                    }else if (a.equals("atiende")) {
                        cli.imp_h2p_pri();
                        imp_cola_nor(cli_n);
                    }      
            }
        }
*/
        System.out.println("Hola mundo");
    }
    
        
}
