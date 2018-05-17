package execum;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author thome pereira alves neto
 */
public class ExecUm {

    //Construtor
    private ExecUm() { }
    
    //Recebe como entrada uma tabela hash "SC" de Separate Chaining,
    //uma "wordList" (filtro) de palavras, e a lista "tale" a ser filtrada
    //Imprime as palavras na lista ausentes no filtro
    //Retorna a duração da filtragem
    public static double BlackFilter(SeparateChainingHashST<String, String> SC, 
                                     In wordList, In tale){
        Stopwatch watch = new Stopwatch();
        
        while (!wordList.isEmpty()) {
            String word = wordList.readString();
            SC.put(word, word);
        }
        
        while (!tale.isEmpty()) {
            String word = tale.readString();
            if (!SC.contains(word))
                StdOut.println(word);
        }
        
        double time = watch.elapsedTime();
        return time;    
    }
    
    //Recebe como entrada uma tabela hash "LP" de Linear Probing,
    //uma "wordList" (filtro) de palavras, e a lista "tale" a ser filtrada
    //Imprime as palavras na lista ausentes no filtro
    //Retorna a duração da filtragem
    public static double BlackFilter(LinearProbingHashST<String, String> LP, 
                                     In wordList, In tale){
        Stopwatch watch = new Stopwatch();
        
        while (!wordList.isEmpty()) {
            String word = wordList.readString();
            LP.put(word, word);
        }
        
        while (!tale.isEmpty()) {
            String word = tale.readString();
            if (!LP.contains(word))
                StdOut.println(word);
        }
        
        double time = watch.elapsedTime();
        return time;    
    }
    
    //Recebe dois arquivos como entrada
    //O livro tale.txt
    //O vocabulário words.utf-8.txt
    //Compara o desempenho das implementações BlackFilter
    //BF SeparateChaining e BF LinearProbing
    public static void main(String[] args) {
        SeparateChainingHashST<String, String> SC = new SeparateChainingHashST<>();
        LinearProbingHashST<String, String> LP = new LinearProbingHashST<>();
        
        In wordList = new In(args[0]);
        In tale = new In(args[1]);
        
        //Duração de BlackFilter com SeparateChaining
        double timeSC = BlackFilter(SC, wordList, tale);
        
        //Duração de BlackFilter com LinearProbing
        double timeLP = BlackFilter(LP, wordList, tale);
        
        if (timeSC < timeLP)
            System.out.printf("\nSeparateChaining foi melhor, com %2f segundos!", timeSC);
        else if (timeLP < timeSC)
            System.out.printf("\nLinearProbing foi melhor, com %2f segundos!", timeLP);
        else
            System.out.printf("\nOs dois foram iguais, com %2f segundos!", timeSC);
    }
    
}
