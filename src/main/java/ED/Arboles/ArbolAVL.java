
package ED.Arboles;
import ED.Arboles.NodoAVL;

//ya revisado

public class ArbolAVL <J extends Comparable<? super J>> {
    
    NodoAVL raiz;

    // Constructor
    ArbolAVL(){
        raiz = null;
    }
    
    // Encontar minimo
    
    public J findMin(){
        if (isEmpty()) {
            System.out.println("Arbol vacio");
        }
        return (J) findMin(raiz).data;
        
    }
    
    private NodoAVL<J> findMin(NodoAVL<J> nd){
        
        if (nd == null && nd == raiz) {
            return null;
        }
        
        while (nd.left != null) {
            nd = nd.left;
        }
        
        return nd;
    }
    
    // Encontar maximo
    
    public J findMax(){
        if (isEmpty()) {
            System.out.println("Arbol vacio");
        }
            return (J) findMax(raiz).data;
        
    }
    
    private NodoAVL<J> findMax (NodoAVL<J> nd){
        if (nd == null && nd == raiz) {
            return null;
        }
        
        while (nd.right != null) {
            nd = nd.right;
        }
        
        return nd;
        
    }
    
    // Esta vacio?
    
    public boolean isEmpty(){
        if (raiz == null) {
            return true;
        }else{
            return false;
        }
    }
    
    // Vaciar el arbol
    
    public void makeEmpty(){
        raiz = null;
    }
    
    // Continete el elmento que busco
    
    public boolean contains(J x){
        return contains(x, raiz);
    }
    
    private boolean contains(J x, NodoAVL<J> nd ){
        
        if (nd == null) {
            return false;
        }
        
        int comp = x.compareTo(nd.data);
        
        //Busca dependiendo si el dato en el nodo es mayor o menor a x
        
        if (comp < 0) {
            return contains(x, nd.left); 
        }
        else if(comp > 0){
            return contains(x, nd.right);
        }
        else {
            return true; 
        }
    }
    
    //imprimir arbol con InOrder
    
    private void printTree_in( NodoAVL<J> t )
    {
        if( t != null )
        {
            printTree_in( t.left );
            System.out.println( t.data );
            printTree_in( t.right );
        }
    }
    
    public void printTree_in( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree_in( raiz );
    }
    
    
    //Altura
    private int altura( NodoAVL<J> nd )
    {   
        if (nd == null) {
            return -1;
        }else{
            return nd.alt;
        }
    }
    
    //Rotacion simple a la derecha
    private NodoAVL<J> rs_der( NodoAVL<J> nd1 )
    {
        NodoAVL<J> nd2 = nd1.left;
        nd1.left = nd2.right;
        nd2.right = nd1;
        nd1.alt = Math.max( altura( nd1.left ), altura( nd1.right ) ) + 1;
        nd2.alt = Math.max( altura( nd2.left ), nd1.alt ) + 1;
        return nd2;
    }
    
    // Rotacion simple a la izquierda
    private NodoAVL<J> rs_izq( NodoAVL<J> nd1 )
    {
        NodoAVL<J> nd2 = nd1.right;
        nd1.right = nd2.left;
        nd2.left = nd1;
        nd1.alt = Math.max( altura( nd1.left ), altura( nd1.right ) ) + 1;
        nd2.alt = Math.max( altura( nd2.right ), nd1.alt ) + 1;
        return nd2;
    }
    
    //rotacion doble a la derecha
    private NodoAVL<J> rd_der( NodoAVL<J> nd )
    {
        nd.left = rs_izq( nd.left );
        return rs_der( nd );
    }
    
    //rotacion doble a la izquierda
    private NodoAVL<J> rd_izq( NodoAVL<J> nd )
    {
        nd.right = rs_der( nd.right );
        return rs_izq( nd );
    }
    
    //balancear
     private NodoAVL<J> balancear( NodoAVL<J> nd )
    {
        if(nd == null)
            return nd;
        
        if(altura(nd.left) - altura(nd.right) > 1 )
            if(altura(nd.left.left) >= altura(nd.left.right))
                nd = rs_der(nd);
            else
                nd = rd_der(nd);
        else
        if(altura(nd.right) - altura(nd.left) > 1)
            if(altura(nd.right.right) >= altura(nd.right.left))
                nd = rs_izq(nd);
            else
                nd = rd_izq(nd);

        nd.alt = Math.max(altura(nd.left), altura(nd.right)) + 1;
        return nd;
    }
     
     //Insertar
    public void insert( J x )
    {
        raiz = insert( x, raiz );
    }
    
    private NodoAVL<J> insert( J x, NodoAVL<J> nd )
    {
        if( nd == null )
            return new NodoAVL<>( x, null, null );
        
        int comp = x.compareTo( nd.data );
        
        if( comp < 0 )
            nd.left = insert( x, nd.left );
        else if( comp > 0 )
            nd.right = insert( x, nd.right );
        else
            ;  // Duplicate; do nothing
        return balancear( nd );
    }
     
     //remover
    
    public void remove(J x){
        raiz = remove(x, raiz);
    }
    
    private NodoAVL<J> remove(J x, NodoAVL<J> nd){
        if (nd == null) {
            return nd;  //No hacemos nada porque  el arbol esta vacio
        }
        
        int comp = x.compareTo( nd.data );
        
        if(comp < 0){
            nd.left = remove(x, nd.left);
        }
        else if(comp > 0){
            nd.right = remove(x, nd.right);
        }
        else if(nd.left != null && nd.right != null){ // En caso de que el nodo a eliminar posea 2 hijos
           nd.data = findMin(nd.right).data;
           nd.right = remove(nd.data, nd.right);
           //En este caso debemos reemplazar el valor del nodo a elminar
           //Por el valor  mas pequeño debajo de su hijo derecho
           //El cual seria el valor proximo mayor seguiente a el
        }
        else if(nd.left != null){ 
        // Caso en que el nodo a eliminar solamente posea un hijo a la izquierda
        // Este tomara sera reemplazado por su hijo izquierda
            nd = nd.left;
        }else{
            // Encaso de que solo sea una hoja pues entonces este
            // sera igual a su hijo derecho o sea a null y ya se reemplazo
            nd = nd.right;
        }         
        return balancear (nd); // retornamos el nodo eliminado
    }
    
    
    
    //opcional
     public void checkBalance( )
    {
        checkBalance( raiz );
    }
    
    private int checkBalance( NodoAVL<J> t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( altura( t.left ) - altura( t.right ) ) > 1 ||
                    altura( t.left ) != hl || altura( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return altura( t );
    }
    
    
    
    
         // Test program
    public static void main( String [ ] args )
    {
        ArbolAVL<Integer> t = new ArbolAVL<>( );
        final int SMALL = 40;
        final int NUMS = 1000000;  // must be even
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        {
        //    System.out.println( "INSERT: " + i );
            t.insert( i );
            if( NUMS < SMALL )
                t.checkBalance( );
        }
        
        System.out.println(t.raiz.data);
        
         for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        {
        //    System.out.println( "INSERT: " + i );
            t.insert( i );
            if( NUMS < SMALL )
                t.checkBalance( );
        }
        
        
        for( int i = 1; i < NUMS; i+= 2 )
        {
         //   System.out.println( "REMOVE: " + i );
            t.remove( i );
            if( NUMS < SMALL )
                t.checkBalance( );
        }
        if( NUMS < SMALL )
            t.printTree_in( );
        
        System.out.println(t.findMax());
        System.out.println(t.findMin());
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
    }
}
