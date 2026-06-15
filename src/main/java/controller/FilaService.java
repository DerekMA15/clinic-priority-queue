package controller;

import model.estrutura.Lista;
import model.Paciente;

/**
 * para implementar a regra 3:2 (3 preferenciais para 2 comuns) de uma forma mais facilitada, a melhor maneira
 * é que essa classe gerencie duas listas separadas, uma preferencial e outra comum
 * se for pra gente colocar todos numa lista só, tirar um paciente comum enquanto ainda há preferenciais na frente
 * exigiria de métodos mais complexos para "pular" nós no meio da lista encadeada
 * então preferi e achei mais simples isso ser feito com uma lista pra cada
 */

public class FilaService {

    private Lista filaPreferencial;
    private Lista filaComum;

    // contadores para controlar a regra 3:2
    private int contadorPreferencial;
    private int contadorComum;

    public FilaService() {
        this.filaPreferencial = new Lista("Fila Preferencial");
        this.filaComum = new Lista("Fila Comum");
        this.contadorPreferencial = 0;
        this.contadorComum = 0;
    }

    /**
     * insere o paciente na fila correta
     * o método inserirOrdenado (configurado na Lista) garantirá que
     * a prioridade de gravidade (5 a 1) seja respeitada dentro de cada fila
     */
    public void inserirNaFila(Paciente paciente) {
        if (paciente.isPreferencial()) {
            filaPreferencial.inserirOrdenado(paciente);
        } else {
            filaComum.inserirOrdenado(paciente);
        }
    }

    /**
     * remove e retorna o próximo paciente a ser atendido,
     * aplicando a regra de intercalação 3:2.
     */
    public Paciente atendimento() {
        // cenário 1: As duas filas estão vazias
        if (filaPreferencial.estaVazia() && filaComum.estaVazia()) {
            resetarContadores();
            return null;
        }

        // cenário 2: Só tem pacientes na Fila Comum
        if (filaPreferencial.estaVazia()) {
            resetarContadores(); // Reseta para iniciar um ciclo limpo quando chegar preferencial
            return filaComum.removeDaFrente();
        }

        // cenário 3: Só tem pacientes na Fila Preferencial
        if (filaComum.estaVazia()) {
            resetarContadores();
            return filaPreferencial.removeDaFrente();
        }

        // cenário 4: Regra 3:2 (ambas as filas têm pacientes)
        if (contadorPreferencial < 3) {
            // ainda é a vez dos preferenciais
            contadorPreferencial++;
            return filaPreferencial.removeDaFrente();

        } else {
            // vez dos preferenciais acabou, chamando os comuns
            contadorComum++;
            Paciente pacienteChamado = filaComum.removeDaFrente();

            // se completou o ciclo (já chamou 2 comuns), reseta para a próxima chamada
            if (contadorComum >= 2) {
                resetarContadores();
            }
            return pacienteChamado;
        }
    }

    // método auxiliar privado para zerar o ciclo
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