package exec7_9;

/**
 *
 * @author thome pereira alves neto
 * @author matheus santos almeida
 */
import edu.princeton.cs.algs4.TrieST;
import java.io.*;
import java.lang.StringBuilder;

/**
 * alfabeto: a b … y z ? 0 1 … 8 9 (e espaço em branco)
 * ascii:   97 - 122, 63, 48 - 57, 32
 */


public class Exec7_9 {
    
    public static int[] a = {131,132,133,134,142,143,160,166};
    public static int[] e = {130,136,137,138,144};
    public static int[] i = {139,140,141,161,173};
    public static int[] o = {147,148,149,153,162,167};
    public static int[] u = {129,150,151,154,163};
    public static int[] c = {128,135,155};
    public static int[] y = {152,157};
    public static int[] n = {164,165};
    
    public static boolean contido(int[] arr, int item) {
      for (int n : arr) {
         if (item == n) {
            return true;
         }
      }
      return false;
    }

    public static String mudar(String titulo){
        StringBuilder novoTitulo = new StringBuilder();
        int aux;
        while (titulo.length() > 0) {
            aux = (int) titulo.charAt(0);
            titulo = titulo.substring(1);
            if (aux == 32 || aux > 96 && aux < 123) {
                novoTitulo.append(aux);
            }
            else if (aux > 64 && aux < 91) {
                aux = aux + 32;
                novoTitulo.append(aux);
            }
            else if (contido(a, aux))
                novoTitulo.append("a");
            else if (contido(e, aux))
                novoTitulo.append("e");
            else if (contido(i, aux))
                novoTitulo.append("i");
            else if (contido(o, aux))
                novoTitulo.append("o");
            else if (contido(u, aux))
                novoTitulo.append("u");
            else if (contido(c, aux))
                novoTitulo.append("c");
            else if (contido(y, aux))
                novoTitulo.append("y");
            else if (contido(n, aux))
                novoTitulo.append("n");
            else
                novoTitulo.append("?");
                
        }
        return novoTitulo.toString();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        TrieST trie = new TrieST();
        File file = new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Exec7_9\\src\\exec7_9\\movies.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String titulo;
        String novoTitulo;
        int cont = 0;
        while ((titulo = br.readLine()) != null) {
            novoTitulo = mudar(titulo);
            trie.put(novoTitulo, cont);
            cont++;
        }
    }
    
}
