/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author thome pereira alves neto
 */
public class ExecUm {

    private ExecUm() { }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SeparateChainingHashST<String, String> SC = new SeparateChainingHashST<>();
        LinearProbingHashST<String, String> LP = new LinearProbingHashST<>();
        
        In wordListSC = new In(args[0]);
        In wordListLP = new In(args[0]);
        In taleSC = new In(args[1]);
        In taleLP = new In(args[1]);
        
        //BlackFilter com SeparateChaining
        Stopwatch watchSC = new Stopwatch();
        
        while (!wordListSC.isEmpty()) {
            String word = wordListSC.readString();
            SC.put(word, word);
        }
        
        while (!taleSC.isEmpty()) {
            String word = taleSC.readString();
            if (!SC.contains(word))
                StdOut.println(word);
        }
        
        double timeSC = watchSC.elapsedTime();
        
        //BlackFilter com LinearProbing
        Stopwatch watchLP = new Stopwatch();
        
        while (!wordListLP.isEmpty()) {
            String word = wordListLP.readString();
            LP.put(word, word);
        }
        
        while (!taleLP.isEmpty()) {
            String word = taleLP.readString();
            if (!LP.contains(word))
                StdOut.println(word);
        }
        
        double timeLP = watchLP.elapsedTime();
        
        //Comparação
        if (timeSC < timeLP)
            System.out.printf("\nSeparateChaining foi melhor, com %2f segundos!", timeSC);
        else if (timeLP < timeSC)
            System.out.printf("\nLinearProbing foi melhor, com %2f segundos!", timeLP);
        else
            System.out.printf("\nOs dois foram iguais, com %2f segundos!", timeSC);
    }
    
}
