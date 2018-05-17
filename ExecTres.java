package execum;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.io.File;

/**
 *
 * @author thome pereira alves neto
 */
public class ExecTres {
    
    //Construtor
    public ExecTres() { }
    
    /*
    Situação Nº1: Aplicação Java externa, exemplo: Dicionario.java
    Dicionario quer realizar consultas chamando métodos da classe ExecTres
    Para tanto utiliza dois métodos de ExecTres: BuildLookupTable e Lookup
    BuildLookupTable, que constrói e retorna a tabela de símbolos pronta
    E LookUp, que responde a consultas pedidas pela aplicação
    O número de consultas a serem realizadas fica por conta do Dicionario
    */
    
    //BuildLookupTable(File file, int keyField, int valField)
    //Recebe como entrada de Dicionario:
    //Um arquivo CSV "file",
    //o número "keyField" do campo que será a chave,
    //e o número "valField" do campo que será o valor associado. 
    //Retorna a Dicionario uma tabela de símbolos pronta para uso
    public ST BuildLookupTable(File file, int keyField, int valField){
        ST<String,String> st = new ST<>();
        In in = new In(file);
        while (in.hasNextLine()) {
           String line = in.readLine();
           String[] tokens = line.split(",");
           String key = tokens[keyField];
           String val = tokens[valField];
           st.put(key, val);
        }
        return st;
    }
    
    //Lookup(String query, ST st)
    //Recebe como entrada uma query, e a tabela de símbolos a ser consultada
    public void Lookup(String query, ST st){
           if (st.contains(query))
              StdOut.println(st.get(query));        
    }
    
    /*
    Situação Nº2: Aplicação Java externa, exemplo: ListaTelefonica.java
    ListaTelefonica quer consultas realizadas através de um arquivo
    Usa apenas o método Lookup
    O número de consultas equivale ao número de queries do arquivo
    */
    
    //Lookup(File file, File lookUpFile, int keyField, int valField)
    //Recebe como entrada de ListaTelefonica:
    //Um arquivo CSV "file",
    //um arquivo de queries "lookupFile",
    //o número "keyField" do campo que será a chave,
    //e o número "valField" do campo que será o valor associado.
    //Depois, responde a consultas pedidas pelo arquivo lookupFile
    public void Lookup(File file, File lookupFile, int keyField, int valField){
        ST<String,String> st = new ST<>();
        In in = new In(file);
        In lookUp = new In(lookupFile);
        while (in.hasNextLine()) {
           String line = in.readLine();
           String[] tokens = line.split(",");
           String key = tokens[keyField];
           String val = tokens[valField];
           st.put(key, val);
        }
        while (lookUp.hasNextLine()) {
           String query = lookUp.readString();
           if (st.contains(query))
              StdOut.println(st.get(query));
        }
    }
    
    //main
    //Idêntico ao LookupCSV
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        ST<String,String> st = new ST<>();

        while (in.hasNextLine()) {
           String line = in.readLine();
           String[] tokens = line.split(",");
           String key = tokens[keyField];
           String val = tokens[valField];
           st.put(key, val);
        }
        while (!StdIn.isEmpty()) {
           String query = StdIn.readString();
           if (st.contains(query))
              StdOut.println(st.get(query));
        }
    }
}
