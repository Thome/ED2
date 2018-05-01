#include <iostream>
#include <stdlib.h>
#include <fstream>
using namespace std;

ifstream entrada;

//Função de dispersão
//h(k, m)=k mod m
int dispersao(int valor, int tamanho)
{
   return valor % tamanho;
}

//Define-se um nó como um par
//Par: (Valor, Próximo)
typedef struct STnode *Node;
struct STnode
{
   int valor;
   Node       next;
};

//Tab irá apontar para as M listas
Node* tab;

//Inicializa a tabela com tamanho M
void STinit(int tamanho)
{
   int h;
   tab = (STnode**) malloc(tamanho * sizeof (Node));
   for (h = 0; h < tamanho; h++)
      tab[h] = NULL;
}

//Insere o número v na tabela hash
//Retorna 1 se bem-sucedido, ou 0 caso contrário
//O número de comparações c é computado
int ADD(int v, int tamanho, int* c)
{
    Node t;
    int h;
    h = dispersao(v, tamanho);
    t = tab[h];
    while (t != NULL)
    {
        (*c)++;
        if (t->valor == v) return 0;
        t = t->next;
    }

    Node novo = (STnode*) malloc(sizeof (Node));
    novo->valor = v;
    novo->next = NULL;
    if(tab[h] == NULL)
        tab[h] = novo;
    else
    {
        Node aux = tab[h];
        tab[h] = novo;
        novo->next = aux;
    }

    return 1;
}

int newADD(int v, int tamanho)
{
    Node t;
    int h;
    h = dispersao(v, tamanho);
    t = tab[h];
    while (t != NULL)
    {

        if (t->valor == v) return 0;
        t = t->next;
    }

    Node novo = (STnode*) malloc(sizeof (Node));
    novo->valor = v;
    novo->next = NULL;
    if(tab[h] == NULL)
        tab[h] = novo;
    else
    {
        Node aux = tab[h];
        tab[h] = novo;
        novo->next = aux;
    }

    return 1;
}

//Procura-se o número v na tabela hash
//Retorna 1 se bem-sucedido, ou 0 caso contrário
//O número de comparações c é computado
int HAS(int v, int tamanho, int* c)
{
    Node t;
    int h;
    h = dispersao(v, tamanho);
    for (t = tab[h]; t != NULL; t = t->next){
        (*c)++;
        if (t->valor == v) break;
    }
    if (t != NULL) return 1;
    return 0;
}

//Remove-se, caso possível, o número v da tabela hash
//Retorna 1 se bem-sucedido, ou 0 caso contrário
//O número de comparações c é computado
int DEL(int v, int tamanho, int* c)
{
    int h;
    h = dispersao(v, tamanho);

    Node aux, ant;
    aux = tab[h];
    ant = aux;
    while (aux != NULL)
    {
        (*c)++;
        if (aux->valor == v) {
            ant->next = aux->next;
            if (*c == 1) {
                ant = ant->next;
                tab[h] = ant;
            }
            return 1;
        }
        ant = aux;
        aux = aux->next;
    }
}

//Dado o tamanho da Hash table
//Retorna o comprimento da maior lista da tabela
int PRT(int tamanho)
{
    int l;
    int temp;
    Node t;
    for (int i = 0; i < tamanho; i++)
    {
        temp = 0;
        t = tab[i];
        while (t != NULL)
        {
            temp++;
            t = t->next;
        }
        if (l < temp) l = temp;
    }
    return l;
}

void printHash(int tamanho)
{
    cout << "HASHTABLE" << endl;
    Node t;
    for (int i = 0; i < tamanho; i++)
    {
        t = tab[i];
        cout << i;
        while (t != NULL)
        {
            cout << " -> " << t->valor;
            t = t->next;
        }
        cout << " -> |" << endl;
    }
    cout << endl;
}

//Dado o tamanho da Hash Table
//Todos os elementos da tabela são redistribuídos
//Em uma nova Hash Table
/*
void REHASH(int tamanho)
{
    int novoTamanho = 2*tamanho - 1;
    Node *newHashTable = new Node[tamanho]();

    Node t;
    newHashTable = tab;
    tab = (Node*) malloc(novoTamanho * sizeof(Node));

    int newHash;
    for (int i = 0; i < tamanho; i++)
    {
        t = newHashTable[i];
        while (t != NULL)
        {
            newADD(t->valor, novoTamanho);
            t = t->next;
        }
    }
}*/

/*
void REHASH(int tamanho)
{
    int novoTamanho = 2*tamanho - 1;
    Node *newHashTable = (STnode**) malloc(novoTamanho * sizeof (Node));
    for (int i = 0; i < novoTamanho; i++)
      newHashTable[i] = NULL;

    Node t;
    int newHash;
    for (int i = 0; i < tamanho; i++)
    {
        t = tab[i];
        while (t != NULL)
        {
            newHash = dispersao(t->valor, novoTamanho);
            Node novo = (Node) malloc(sizeof(Node));
            novo->valor = t->valor;
            novo->next = NULL;
            novo->next = newHashTable[newHash];
            newHashTable[newHash] = novo;
        }
    }

    //delete [] tab;
    tab = newHashTable;
}*/

int main(){

    entrada.open("entrada.txt");
    string action;
    int element;
    int i = 0, l = 0; //numero da operacao, comprimento da maior lista da tabela
    int r, c = 0;                //resultado da operação, número de comparações feitas
    int m = 7, n = 0;               //tamanho da tabela

    Node *tab;
    STinit(m);

    while(entrada >> action){
        c = 0;
        if (action == "ADD"){
            entrada >> element;
            r = ADD(element, m, &c);
            cout << i << " " << r << " " << c << endl;
            if (r){ //se o elemento foi adicionado
                n++;
            }
            if (((double) n)/(double) m >= 0.75){
                REHASH(m);
                m = 2*m - 1;
            }
            //cout << "Adicionando Numero: " << element << ", Hash: " << dispersao(element, m) << endl;
            //printHash(m);
        }
        if (action == "DEL"){
            entrada >> element;
            r = DEL(element, m, &c);
            cout << i << " " << r << " " << c << endl;
            if (r){
                n--;
            }
            //cout << "Deletando Numero: " << element << endl;
            //printHash(m);
        }
        if (action == "HAS"){
            entrada >> element;
            r = HAS(element, m, &c);
            cout << i << " " << r << " " << c << endl;
        }
        if (action == "PRT"){
            l = PRT(m);
            cout << i << " " << m << " " << n << " " << l << endl;
        }

        i++;
    }

    return 0;
}
