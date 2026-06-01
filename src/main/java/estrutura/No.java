package estrutura;

import model.Paciente;

public class No {
    Paciente paciente; 
    No prev; // no anterior
    No next; // proximo no 


    //inserirOrdenado(Paciente p)
    No(Paciente p, No ant, No prxm){
        paciente = p;
        prev = ant;
        next = prxm; 
    }

    No(Paciente p){
        this(p,null, null); // revisar se é a melhor maneira, pq o paciente vai entrar nulo tbm(embora que essa No só seja usado como auxiliar)
    }

    public String getPaciente(){
        return paciente.getNome();
    }
    
}

