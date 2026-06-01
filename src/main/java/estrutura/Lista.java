package estrutura;

// im da classe Node

public class Lista
{
    private No primNode;
    private No ultiNode;
    private No copia = null;
    private String nomeLista;
    
    //Construtor sem argumentos que cria a lista com um nome gen�rico
    public Lista()
    {
        this("Lista");   
    }
    
    //Construtor de um argumento que d� um nome a lista e coloca cabe�a e cauda em null
    public Lista(String n)
    {
        nomeLista = n;
        primNode = ultiNode = null;
    }
    
    //Determina se a lista est� vazia
    public boolean estaVazia()
    {
        if (primNode == null) {
            return true;
        }
            else{
                return false;
        }
    }

    public No getPrimNode(){
        return primNode;
    }
    
    public No getUltNode(){
        return ultiNode;
    }
    
    public void mostra(){
        if (estaVazia())
        {
            System.out.println("Lista "+nomeLista+" esta vazia!");
            return;
        }
        
        System.out.println("A lista "+nomeLista+":"); 
        No atual = primNode;
        while (atual != null)
        {
            System.out.println(atual.nome);
            atual = atual.next;
        }                  
    }

    public void insereNaFrente(String n){
        No aux = new No(n,null,null, primNode); //Cria um no tendo como proximo quem era primeiro
        if(primNode != null)
            primNode.prev = aux; //Faz o anterior de quem era o primeiro ser o no que foi criado
        primNode = aux; //Faz o primeiro ser quem acabou de ser criado                  
        if(primNode.next == null) 
            ultiNode = aux;
    }

    public void removeDaFrente(){
        if (!estaVazia()){
            primNode = primNode.next;
            if(primNode != null)
                primNode.prev = null;
            else
                ultiNode = null;}  
            else
                System.out.println("Lista vazia");
    }
}
