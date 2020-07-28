
package ED.Heap_ColasDePrioridad;

public class Heap {
    
    int [] arr;
    int size;
    int maxSize;
    
    Heap(int capacidad){
        size = 0;
        maxSize = capacidad;
        arr = new int[maxSize];
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
        while (i > 1 && arr[parent(i)] < arr[i]){
            // swap H[Parent(i)] and H[i]
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            // actualiza
            i = parent(i);
        }
    }
    
    private void siftDown(int i){
        int maxIndex = size;
        int l = leftChild(i);
        if (l <= size && arr[l] > arr[maxIndex]) {
            maxIndex = l;
        }
        int r = rightChild(i);
        if (r<=size && arr[r]> arr[maxIndex]) {
            maxIndex = r;
        }
        if (i != maxIndex) {
            // swap H[i] and H[maxIndex]
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
            siftDown(maxIndex);
        }
    }
    
    public void insert(int p){
        System.out.println(size);
        System.out.println(maxSize);
        if (size+1 == maxSize) {
            System.out.println("error");
            int[] nuevo = new int [size*2];
            for (int i = 1; i < arr.length; i++) {
                nuevo[i] = arr[i];
            }
            this.arr = nuevo;
            this.maxSize = size*2;
            size = size + 1;
            arr[size] = p;
            siftUp(size);
        }else{
            size = size + 1;
            arr[size] = p;
            siftUp(size);
        }
    }
    
    public int extractMax(){
        int result = arr[1];
        arr[1] = arr[size];
        size = size-1;
        siftDown(1);
        return result;
    }
    
    public void remove(int i){
        arr[i] = 32767;
        siftUp(i);
        extractMax();
    }
    
    public void imp_heap(Heap a){
        for (int i = 1; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
