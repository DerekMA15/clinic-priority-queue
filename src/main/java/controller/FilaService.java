package controller;

import estrutura.Lista;
import model.Paciente;

public class FilaService {

    private Lista filaPreferencial;
    private Lista filaComum;

    // Contadores para controlar a regra 3:2
    private int contadorPreferencial;
    private int contadorComum;

    public FilaService() {
        this.filaPreferencial = new Lista("Fila Preferencial");
        this.filaComum = new Lista("Fila Comum");
        this.contadorPreferencial = 0;
        this.contadorComum = 0;
    }

    /**
     * Insere o paciente na fila correta.
     * O método inserirOrdenado (configurado na Lista) garantirá que
     * a prioridade de gravidade (5 a 1) seja respeitada dentro de cada fila.
     */
    public void inserirNaFila(Paciente paciente) {
        if (paciente.isPreferencial()) {
            filaPreferencial.inserirOrdenado(paciente);
        } else {
            filaComum.inserirOrdenado(paciente);
        }
    }

    /**
     * Remove e retorna o próximo paciente a ser atendido.
     * Lógica Mista: Gravidade Absoluta + Desempate Proporcional (3:2).
     */
    public Paciente atendimento() {
        // Cenário 1: As duas filas estão vazias
        if (filaPreferencial.estaVazia() && filaComum.estaVazia()) {
            resetarContadores();
            return null;
        }

        // Cenário 2: Só tem pacientes na Fila Comum
        if (filaPreferencial.estaVazia()) {
            resetarContadores();
            return filaComum.removeDaFrente();
        }

        // Cenário 3: Só tem pacientes na Fila Preferencial
        if (filaComum.estaVazia()) {
            resetarContadores();
            return filaPreferencial.removeDaFrente();
        }

        // Cenário 4: Ambas as filas têm pacientes aguardando
        // Vamos "espiar" as cabeças das listas sem remover ninguém ainda
        Paciente primeiroPref = filaPreferencial.getPrimNode().paciente;
        Paciente primeiroComum = filaComum.getPrimNode().paciente;

        // REGRA A: Gravidade Absoluta (Emergências atropelam o ciclo)
        if (primeiroPref.getPrioridade() > primeiroComum.getPrioridade()) {
            resetarContadores(); // Reseta porque uma emergência quebrou a contagem normal
            return filaPreferencial.removeDaFrente();
        }
        else if (primeiroComum.getPrioridade() > primeiroPref.getPrioridade()) {
            resetarContadores(); // Reseta pelo mesmo motivo
            return filaComum.removeDaFrente();
        }

        // REGRA B: Gravidades Iguais (Aplica a regra de intercalação 3:2)
        else {
            if (contadorPreferencial < 3) {
                // Ainda é a vez da fila preferencial no ciclo
                contadorPreferencial++;
                return filaPreferencial.removeDaFrente();
            } else {
                // A vez da preferencial acabou, chamando os comuns
                contadorComum++;
                Paciente pacienteChamado = filaComum.removeDaFrente();

                // Se completou o ciclo inteiro (já chamou 2 comuns), reseta para o próximo
                if (contadorComum >= 2) {
                    resetarContadores();
                }
                return pacienteChamado;
            }
        }
    }

    // Método auxiliar privado para zerar o ciclo
    private void resetarContadores() {
        this.contadorPreferencial = 0;
        this.contadorComum = 0;
    }

    public Lista getFilaPreferencial() {
        return this.filaPreferencial;
    }

    public Lista getFilaComum() {
        return this.filaComum;
    }

    public void mostrarEstadoDasFilas() {
        System.out.println("\n--- ESTADO ATUAL DAS FILAS ---");
        filaPreferencial.mostra();
        filaComum.mostra();
        System.out.println("Contadores do Ciclo -> Preferencial: " + contadorPreferencial + " | Comum: " + contadorComum);
        System.out.println("-------------------------------\n");
    }

    public void reiniciarFilas() {
        this.filaPreferencial = new Lista("Fila Preferencial");
        this.filaComum = new Lista("Fila Comum");
        resetarContadores();
        System.out.println("Sistema reiniciado com sucesso.");
    }
}