package nEnLinea;

/**
 *
 * @author Juan Fernando Ossa
 * @author Jonathan Stiven Zapata
 */

import java.util.*;
import java.io.*;
/**
 * 
 * Esta clase nos permite hacer todas las operaciones con el tablero, entre las cuales estÃ¡ 
 * decidir quiÃ©n es el ganador, cuÃ¡ndo termina el juego, decidir las jugadas de la mÃ¡quina, etc.
 */

public class Juego {
    private static ArrayList<Jugador> players = new ArrayList<Jugador>();
    private static int [][] tablero ;
    private static int jugadores,n,maquinas;
    private static Tablero tab;
    private static int ganador;
    private static int [] uj = new int [2];
    
    public static void crearJugadores(){
        if(jugadores > 0){
            for(int i=1 ; i<=jugadores ; i++){
                players.add(new Jugador(Integer.toString(i),0));
            }
        }
        if(maquinas > 0){
            for(int i=jugadores ; i<jugadores+maquinas ; i++){
                players.add(new Jugador(Integer.toString(i+101-jugadores),1));
            }
        }
    }
    
    /**
     * MÃ©todo que permite llenar la matriz con posiciones vacÃ­as las cuales se representan con un -1.
     */
    public static void llenarTablero(){
        for(int i=0 ; i<tab.getFilas() ; i++){
            for(int j=0 ; j<tab.getColumnas() ; j++){
                tablero[i][j]=-1;
            }
        }
        ganador = -1;
    }
    
    /**
     * 
     * Este mÃ©todo pregunta si todas las casillas estÃ¡n llenas. 
     */
    public static boolean tableroCompleto(){
        for(int i=0 ; i<tab.getFilas() ; i++){
             for(int j=0 ; j<tab.getColumnas() ; j++){
                 if(tablero[i][j] == -1) return false;
             }
         }
        return true;
    }
    
    /**
     * 
     * Pregunta si hay un ganador, o si hay un empate, en este caso serÃ­a que todas las casillas estuviesen llenas. 
     */
    public static boolean terminaPartida(){
        return ganador() != -1 || tableroCompleto();
    }
    
    /**
     * 
     * Este mÃ©todo recorre todo el tablero, y segÃºn el n con el que estemos jugando, decide si hay un ganador.
     * Los recorridos que hace es de forma horizontal, vertical y diagonal.
     */
    public static int ganador(){
        
        /*--------------------------------------------------------------------*/
        
        //Recorrido horizontal.
        int cont = 0;
        int temp = 0;
        for(int i=0 ; i<tab.getFilas() ; i++){
            temp=tablero[i][0];
            for(int j=0 ; j<tab.getColumnas() ; j++){
                if(tablero[i][j] == temp){
                        cont++;
                        if(cont==n && temp != -1){
                            return temp;
                        }
                }else{
                    temp = tablero[i][j];
                    cont = 1;
                }
            }
            cont=0;
        }
        
        /*--------------------------------------------------------------------*/
        
        //Recorrido vertical.
        cont=0;
        temp = 0;
        for(int j=0 ; j<tab.getColumnas(); j++){
            for(int i=0 ; i<tab.getFilas() ; i++){
                if(tablero[i][j] == temp){
                     cont++;
                     if(cont==n-1 && temp != -1){
                         return temp;
                     }
                }else{
                    temp = tablero[i][j];
                    cont = 0;
                }
             }
            cont=0; temp=tablero[0][j];
         }
        
        /*--------------------------------------------------------------------*/
        
        //Recorrido diagonal/.
        int i = 0; int j = n-1; int inicioi = i+1; int inicioj = j+1;
        temp = tablero[i][j]; 
        cont=0;
        while(j < tab.getColumnas() && inicioi<tab.getFilas()){
            if(j<0 || i==tab.getFilas()){
                if(j<0){
                    i = 0; 
                    j=inicioj; 
                    inicioj++;
                    temp = tablero[i][j];
                }else{
                    i=inicioi;
                    j=tab.getColumnas()-1;
                    inicioi++;
                    temp = tablero[i][j];
                }
                cont = 0;
            }
            if((temp != -1)&&(temp == tablero[i][j])){
                cont++;
                if(cont==n){
                    return temp;
                }
            }else{
                temp = tablero[i][j];
                cont=1;
            }
            i++; j--;
        }
        
        /*--------------------------------------------------------------------*/
        
        //Recorrido diagonal\.
        
        i = 0; j = tab.getColumnas() - n; inicioi = i+1; inicioj = j-1;
        temp = tablero[i][j]; 
        cont=0;
        while(j >= 0 && inicioi<tab.getFilas()){
            if(j>=tab.getColumnas() || i==tab.getFilas()){
                if(j>=tab.getColumnas()){
                    i = 0; 
                    j=inicioj; 
                    inicioj--;
                    temp = tablero[i][j];
                }else{
                    i=inicioi;
                    j=0;
                    inicioi++;
                    temp = tablero[i][j];
                }
                cont = -1;
            }
            if(temp == tablero[i][j]){
                if(temp != -1){
                    cont++;
                }
                if(cont==n-1 && temp != -1){
                    return temp;
                }
            }else{
                temp = tablero[i][j];
                cont=0;
            }
            i++; j++;
        }
        return -1;
    }
    
    /**
     * Imprimir el tablero.
     */
    public static void printT(){
        for(int i=0 ; i<tab.getFilas() ; i++){
            for(int j=0 ; j<tab.getColumnas() ; j++){
                if(tablero[i][j]==-1){
                    System.out.print("[   ]");
                }else{
                    if(tablero[i][j]<100){
                        System.out.print("[ "+tablero[i][j]+" ]");
                    }else{
                        System.out.print("["+tablero[i][j]+"]");
                    }
                }
            }
            System.out.println();
        }
        if(n==3) System.out.println("------------------------");
        if(n==4) System.out.println("----------------------------------------");
        if(n==5) System.out.println("------------------------------------------------");
        if(n >5) System.out.println("-------------------------------------------------------");
        for(int j=0 ; j<tab.getColumnas() ; j++){
            System.out.print("  "+j+"  ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * 
     * MÃ©todo que verifica si es posible hacer una jugada, y pone una ficha en la columna.
     * No es necesqario pasarle la fila al mÃ©todo, Ã©ste establece en quÃ© fila debe ir la ficha.
     * 
     */
    public static boolean jugada(Jugador jugador, int columna){
        int i;
        if(columna<tab.getColumnas() && columna>=0){
            i = tab.getFilas()-1;
            while(i>=0 && tablero[i][columna] != -1){
                i--;
            }
        }else{
            return false;
        }
        if(i<tab.getFilas() && i>=0){
            tablero[i][columna] = jugador.getFicha();
            return true;
        }
        return false;
        
    }
    
    /**
     * 
     * Este mÃ©todo es usado por la mÃ¡quina para cuando encuentre la columna mÃ¡s adecuada poder obtener la fila en la que puede jugar.
     * 
     */
    public static int obtenerFila(int columna){
        int i;
        i = tab.getFilas()-1;
        while(i>=0 && tablero[i][columna] != -1){
           i--;
        }
        return i;
    }
    
    
    // JUGADAS DE LA MAQUINA
    
    /**
     * 
     * Este mÃ©todo retorna un arreglo con las columnas en donde se puede jugar. 
     */
    public static ArrayList <Integer> posibleJugada(){
        ArrayList <Integer> posibleJugada = new ArrayList();
        int i=tab.getFilas()-1;
        int j=0;
        while(i>=0 && j<tab.getColumnas()){
            if(tablero[i][j]==-1){
                posibleJugada.add(j);
                j++;
                i=tab.getFilas()-1;
            }else{
                i--;
                if(i==-1){
                    j++;
                    i=tab.getFilas()-1;
                }
            }
        }
        return posibleJugada;
    }
    
    /**
     * Este mÃ©todo decide en quÃ© posiciÃ³n poner la ficha, despuÃ©s de llamar al mÃ©todo min y max.
     */
    public static void jugadaMaquina(Jugador maquina1){
        if(!terminaPartida()){
            if(maquina1.getTipo()==1){  //Sobra porque ya lo validamos
                int mejorJugada = max(maquina1);
                jugada(maquina1, mejorJugada);
                System.out.println("La mÃ¡quina " + maquina1.getFicha() + " ha jugado en la columna: " + mejorJugada);
            }      
        }
    }
    
    /**
     * 
     * Este mÃ©todo permite establecer una posiciÃ³n a la ofensiva para la mÃ¡quina. (Ganar al jugador) 
     * 
     */
    public static int max(Jugador maquina1) {
        ArrayList<Integer> pj = posibleJugada();
        int c = -1;  //Nombrarla como columna
        for (int i = 0; i < pj.size(); i++) {
            int fila = obtenerFila(pj.get(i));
            int col = pj.get(i);
            jugada(maquina1, col);
            if (terminaPartida()) {
                if (ganador() == maquina1.getFicha()) {
                    c = col;
                }
            }
            tablero[fila][col] = -1;
        }
        if(c!=-1){
            return c;
        }else{
            c=min(maquina1);
            if(c != -1){
                return c;
            }else{
                int v = 0;  
                boolean malaJugada = false;
                int cont=0;
                while(!malaJugada && cont <100){
                    v = (int)(Math.random() * pj.size());
                    v = pj.get(v);
                    int f = obtenerFila(v);
                    jugada(maquina1, v);
                    int mj = min(maquina1);
                    if(mj!=v){
                        malaJugada=true;
                    }
                    tablero[f][v] = -1;
                    cont++;
                }
                return v;
            }
        }
    }
    
    /**
     * 
     * Este mÃ©todo permite establecer una posiciÃ³n a la defensiva para la mÃ¡quina. (Bloquear al jugador) 
     */
    public static int min(Jugador maquina1){
        ArrayList <Integer> pj = posibleJugada();
        int c = -1;
        int nextPlayer = maquina1.getFicha()+1;
        if(nextPlayer == players.size()+100){
            nextPlayer = 0;
        }
        for(int n=0 ; n<players.size() ; n++){
            if(maquina1.getFicha()!=players.get(n).getFicha()){
                for (int i = 0; i < pj.size(); i++) {
                    int fila = obtenerFila(pj.get(i));
                    int col = pj.get(i);
                    jugada(players.get(n), col);
                    if (terminaPartida()) {
                        if (ganador() == nextPlayer) {
                            return col;
                        }else{
                            c = col;
                        }
                    }
                    tablero[fila][col] = -1;
                }
            }
        }
        return c;
    }
    
    /**
     * 
     * @param args 
     * 
     *  MAIN:
     * - Inicializa todas las varibales que usamos en el juego 
     * - Crea el tablero de juego.
     * - Crea los jugadores.
     * - Maneja los turnos de los jugadores.
     * - Controla cuÃ¡ndo puede jugar la mÃ¡quina.
     * - Verifica que la columna ingresada sea correcta.
     * - Decide cuÃ¡ndo imprimir el tablero.
     * - Muestra el mensaje del ganador
     * 
     */
    public static void main(String args[]){
        long TInicio; 
        long TFin;
        double time;
        
        System.out.println("Este es n en lÃ­nea.");
        Scanner console = new Scanner(System.in);
        System.out.print("NÃºmero de jugadores: ");
        jugadores = Integer.parseInt(console.next());
        while(jugadores<0){
            System.out.print("Ingrese de nuevo el nÃºmero de jugadores: ");
            jugadores = Integer.parseInt(console.next());
        }
        System.out.print("NÃºmero de mÃ¡quinas: ");
        maquinas = Integer.parseInt(console.next());
        while(maquinas<0){
            System.out.print("Ingrese de nuevo el nÃºmero de mÃ¡quinas: ");
            maquinas = Integer.parseInt(console.next());
        }
        System.out.print("Ingrese el n en lÃ­nea que desea: ");
        n = Integer.parseInt(console.next());
        while((n<3) || (n>10)){
            System.out.print("Ingrese de nuevo n: ");
            n = Integer.parseInt(console.next());
        }
        System.out.println("----------------------------------------------------");
        tab = new Tablero((2*n-2),(2*n-1));
        tablero=new int [tab.getFilas()][tab.getColumnas()];
        tablero = tab.getTablero();
        llenarTablero();
        printT();
        crearJugadores();
        int c=0;
        for(int i=0 ; i<players.size() ; i++){
            if(!terminaPartida()){
                if(players.get(i).getTipo()==0){
                    System.out.print("Turno: Jugador " + (i + 1) + ", ingrese columna: ");
                    TInicio = System.currentTimeMillis();
                    boolean bool = false;
                    String str2 = "";
                    char[] str = console.next().toCharArray();
                    while (!bool) {
                        for (int n = 0; n < str.length; n++) {
                            if (!Character.isDigit(str[n])) {
                                bool = true;
                            }
                            str2 += str[n];
                        }
                        if (bool) {
                            System.out.print("Ingrese la columna de nuevo: ");
                            str = console.next().toCharArray();
                            bool = false;
                            str2 = "";
                        } else {
                            bool = true;
                        }
                    }
                    c = Integer.parseInt(str2);
                    while (!jugada(players.get(i), c)) {
                        printT();
                        System.out.print("Ingrese la columna de nuevo, " + "jugador " + (i + 1) + ": ");
                        c = Integer.parseInt(console.next());
                    }
                    TFin = System.currentTimeMillis();
                    time = TFin - TInicio;
                    System.out.println("El tiempo que se demorÃ³ en decidir fue: " + time + " milisegundos");
                    printT();
                }else{
                        TInicio = System.currentTimeMillis(); 
                        jugadaMaquina(players.get(i));
                        TFin = System.currentTimeMillis();
                        time = TFin - TInicio; 
                        System.out.println("El tiempo que se demorÃ³ la mÃ¡quina fue: " + time + " milisegundos");
                        printT();
                }
                ganador = ganador();
                if(ganador != -1){
                    if(ganador>100) {
                        System.out.println("Â¡Â¡Â¡Â¡La mÃ¡quina " + ganador + " ha ganado !!!!");
                    }else{
                        System.out.println("Â¡Â¡Â¡Â¡Jugador " + ganador + " eres el ganador!!!!");
                    }
                    return;
                }
                if (i == players.size() - 1) {
                    i = -1;
                }
            }else{
                System.out.println("Fin de la partida, empate!!");
                return;
            }
        }
    }
}
