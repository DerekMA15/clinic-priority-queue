package controller;

//Classe de controle administrativo dos dados
public class AdminController {
    //ATTRIBUTES
    private FilaService fila;

    //CONSTRUCTOR
    public AdminController(FilaService fila) {
        this.fila = fila;
    }

    //METHODS

    //Exibe o estado das filas. Usado para monitorar a recepção dos pacientes.
    public void visualizarEstadoDasFilas() {
        System.out.println("\n==============================================");
        System.out.println("======   PAINEL DO ADMINISTRADOR: STATUS  ======");
        System.out.println("==============================================");
        fila.mostrarEstadoDasFilas();
    }

    //Reseta o sistema de filas e contadores de atendimento. ATENÇÃO: USAR ESTE
    //MÉTODO REQUER AUTENTICAÇÃO NA INTERFACE (resetar? -> autenticação -> execução)
    public void reiniciarSistema() {
        System.out.println("\n[ADMIN] Solicitando reinicialização do sistema...");
        fila.reiniciarFilas();
        System.out.println("[ADMIN] Sistema de atendimento limpo.");
    }

    //Mostra um relatório de atendimento
    public void emitirRelatorioTurno() {
        System.out.println("\n==============================================");
        System.out.println("======      RELATÓRIO DE ATENDIMENTO    ======");
        System.out.println("==============================================");
        System.out.println("Status atual:");
        if (fila.getFilaPreferencial().estaVazia() && fila.getFilaComum().estaVazia()) {
            System.out.println("-> Todas as filas foram totalmente esvaziadas com sucesso.");
        } else {
            System.out.println("-> Ainda existem pacientes aguardando atendimento.");
        }
        System.out.println("==============================================\n");
    }
}
