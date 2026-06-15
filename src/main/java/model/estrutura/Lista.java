package model.estrutura;

import model.Paciente;

// im da classe Node

public class Lista
{
    private No primNode;
    private No ultiNode;
    private No copia = null;
    private String nomeLista;
    
    
    //Construtor sem argumentos que cria a lista com um nome gen�rico
    public Lista(){
        this("Lista");   
    }
    
    //Construtor de um argumento que da um nome a lista e coloca cabeca e cauda em null
    public Lista(String n){
        nomeLista = n;
        primNode = ultiNode = null;
    }
    
    //Determina se a lista esta vazia
    public boolean estaVazia(){
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
            System.out.println(atual.paciente.getNome()); // retorna o nome dos pacientes
            atual = atual.next;
        }                  
    }

    /**
     * insere o paciente na fila de forma ordenada
     * critério 1: Maior gravidade (prioridade 5 > 4 > 3 > 2 > 1)
     * critério 2: Em caso de empate, pacientes preferenciais (idosos, PCDs, gestantes) passam na frente
     */
    public void inserirOrdenado(Paciente p) {
        No novoNo = new No(p);

        // Se a lista estiver vazia, ele é o primeiro e o último
        if (estaVazia()) {
            primNode = novoNo;
            ultiNode = novoNo;
            return;
        }

        No atual = primNode;

        // Percorre a lista para encontrar a posição correta
        while (atual != null) {
            boolean deveInserirAntes = false;

            // 1º Critério: Gravidade (5 > 4 > 3 > 2 > 1)
            if (p.getPrioridade() > atual.paciente.getPrioridade()) {
                deveInserirAntes = true;
            }
            // 2º Critério: Empate na gravidade, verifica se é preferencial
            else if (p.getPrioridade() == atual.paciente.getPrioridade()) {
                if (p.isPreferencial() && !atual.paciente.isPreferencial()) {
                    deveInserirAntes = true;
                }
            }

            // Se encontrou a posição, ajusta os ponteiros
            if (deveInserirAntes) {
                novoNo.next = atual;
                novoNo.prev = atual.prev;

                if (atual.prev != null) {
                    atual.prev.next = novoNo;
                } else {
                    // Se não tem anterior, está inserindo na cabeça da lista
                    primNode = novoNo;
                }
                atual.prev = novoNo;
                return; // Encerra após inserir
            }
            atual = atual.next;
        }

        // Se percorreu toda a lista e não inseriu antes de ninguém, vai para o final
        ultiNode.next = novoNo;
        novoNo.prev = ultiNode;
        ultiNode = novoNo;
    }

    /**
     * remove da frente (FIFO) e retorna o paciente para que o serviço possa utilizá-lo
     */
    public Paciente removeDaFrente() {
        if (estaVazia()) {
            System.out.println("Lista vazia");
            return null; // retorna null se não houver ninguém
        }

        Paciente pacienteChamado = primNode.paciente; // salva o paciente antes de remover

        primNode = primNode.next; // o segundo da fila passa a ser o primeiro

        if (primNode != null) {
            primNode.prev = null; // remove a referência ao nó antigo
        } else {
            ultiNode = null; // se a fila esvaziou, o último também é null
        }

        return pacienteChamado;
    }
}
