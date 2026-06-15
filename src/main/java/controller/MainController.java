package controller;

import javafx.application.Platform;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import model.Paciente;
import controller.FilaService;

//Classe que interage com a interface, atualizando informações e executando comandos
public class MainController {
    //ATTRIBUTES
    //Botões
    @FXML private Button btnInserir;
    @FXML private Button btnAtendimento;
    @FXML private Button btnReiniciar;

    //Painel principal
    @FXML private Label lblNomeChamado;
    @FXML private Label lblDetalhesChamado;
    //@FXML private Label lblHorarioChamado; //Caso dê tempo de implementar

    //Listas de espera
    @FXML private ListView<String> listViewPreferencial;
    @FXML private ListView<String> listViewComum;

    //Barra de status
    @FXML private Label lblStatusDasFilas;

    //Gerenciadores
    private FilaService fila;
    private AdminController admin;

    //Contador para a barra de status
    private int totalAtendidos = 0;

    //METHODS

    //Inicializa o sistema
    @FXML
    public void inicializar() {
        this.fila = new FilaService();
        this.admin = new AdminController(this.fila);

        //iniciarRelogio(); //Caso dê tempo de implementar

        atualizarInterface();
    }

    //Lógica ao apertar o botão de inserir paciente
    @FXML
    public void handleInserirPaciente() {

    }

    //Lógica ao apertar o botão de atendimento
    @FXML
    public void handleAtendimento() {

    }

    //Lógica ao apertar o botão de reiniciar o sistema
    @FXML
    public void handleReiniciarSistema() {

    }

//    private void iniciarRelogio() { //Caso dê tempo de implementar
//
//    }


    //Sincroniza a lista com a interface a cada alteração
    private void atualizarInterface() {

    }
}
