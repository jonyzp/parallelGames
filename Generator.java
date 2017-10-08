/**
 * main
 */
import java.io.*;
import java.util.*;
import java.lang.*;

public class Generator {
    static Generator g;
    public ArrayList<Integer> randomGenerator(int size,int range) {
        int randVal, sum = 0;
        
        ArrayList<Integer> sol = new ArrayList<>();
        for (int i = 0; i<size;++i){
            randVal = (int)(Math.random()*range)+1;
            //System.out.printf("printing %d", randVal);
            sum+= randVal;
            sol.add(randVal);
        }

        sol.add(sum);
        return sol;
        
    }
    
    
    public void matrixGenerator(int size, int range) {
        try{
            Writer out = new FileWriter("matrix.txt");
            ArrayList actual;
            int bVal,printVal;
            out.write(String.valueOf(size+"\n"));
            for (int i = 0; i < size; ++i) {
                actual = randomGenerator(size,range);
                bVal = 0;
                for (int j = 0; j < size; ++j){
                    if (i==j) printVal = (int)actual.get(actual.size()-1);
                    else printVal = (int)actual.get(j);
                    System.out.printf("%d ",printVal);
                    bVal += (j+1) * printVal;
                    out.write(String.valueOf(printVal+" "));
                }
                System.out.println(bVal);
                out.write(String.valueOf(bVal+"\n"));
            }
            out.close();
        }catch(FileNotFoundException fnfe) { 
            System.out.println(fnfe.getMessage());
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        g=new Generator();
        int range = 2;
        int size = 20;
        g.matrixGenerator(size,range);

    }
}
