
package Parcial_2;

public class H2p {
    
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
