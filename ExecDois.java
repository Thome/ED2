package execum;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Random;

/**
 *
 * @author thome pereira alves neto
 */

public class ExecDois {
    
    //Construtor
    private ExecDois() { }
    
    //Dedup(int N, int M)
    //Recebe como entrada a quantidade N de números aleatórios a serem gerados
    //E recebe o limite superior M para geração dos N números
    //Dedup armazena números novos e imprime repetidos
    public static void Dedup(int N, int M){ 
        SET set = new SET();
        Random rand = new Random();
        int num;
        while (N > 0) {
            num = rand.nextInt(M);
            if (!set.contains(num))
                set.add(num);
            else
                StdOut.println(num);
            N--;
        }
    }
    
    //AleatoriosRepetidos()
    //Recebe T, N e M por linha de comando
    //T: Número de experimentos
    //N: Quantidade de inteiros aleatórios
    //M: Limitante superior pra geração de inteiros
    //Não usa Stopwatch
    public static void AleatoriosRepetidos(){
        int T, N, M;
        System.out.print("Digite T, N e M, respectivamente.");
        T = StdIn.readInt();
        N = StdIn.readInt();
        M = StdIn.readInt();
        while (T > 0) { 
            Dedup(N, M);
            T--;
        }    
    }
    
    //AleatoriosRepetidos(int T, int N, int M)
    //Recebe T, N e M como entrada
    //T: Número de experimentos
    //N: Quantidade de inteiros aleatórios
    //M: Limitante superior pra geração de inteiros
    //Cronometra sua duração com stopwatch
    public static double AleatoriosRepetidos(int T, int N, int M){
        Stopwatch watch = new Stopwatch();
        while (T > 0) { 
            Dedup(N, M);
            T--;
        }
        double time = watch.elapsedTime();
        return time;
    }
    
    //main
    //Usa AleatoriosRepetidos(T, N, M) com T igual a 10 e N igual a 10^7, 10^8, e 10^9, e com M igual a N/2, N e 2N.
    //Cronometra a duração desses experimentos com stopwatch
    public static void main(String[] args) {
        double time;
        int DezaSete = (int) Math.pow(10, 7);
        int DezaOito = (int) Math.pow(10, 8);
        int DezaNove = (int) Math.pow(10, 9);
        time = AleatoriosRepetidos(10, DezaSete, DezaSete/2);
        System.out.printf("Tempo com T=10, N=10^7, M=(10^7)/2: %2f", time);
        time = AleatoriosRepetidos(10, DezaOito, DezaOito);
        System.out.printf("Tempo com T=10, N=10^8, M=10^8: %2f", time);
        time = AleatoriosRepetidos(10, DezaNove, DezaNove*2);
        System.out.printf("Tempo com T=10, N=10^9, M=(10^9)*2: %2f", time);
    }
    
}
