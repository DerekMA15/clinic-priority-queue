package estrutura;

import model.Paciente;

public class No {
    String nome;
    Paciente paciente; 
    No prev; // no anterior
    No next; // proximo no 


    //inserirOrdenado(Paciente p)
    No(String n, Paciente p, No ant, No prxm){
        nome = n;
        paciente = p;
        prev = ant;
        next = prxm; 
    }

    No(String n){
        this(n,null,null, null); // revisar se é a melhor maneira, pq o paciente vai entrar nulo tbm(embora que essa No só seja usado como auxiliar)
    }

    
}

