package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import model.Paciente;

import java.net.URL;
import java.util.ResourceBundle;

import estrutura.No;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ButtonBar.ButtonData;

//Classe que interage com a interface, atualizando informações e executando comandos
public class MainController implements Initializable{
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

    //Contador para a barra de status
    private int totalAtendidos = 0;

    //METHODS

    //Inicializa o sistema
    @Override
    public void initialize(URL caminho, ResourceBundle resources) {
        this.fila = new FilaService();

        //iniciarRelogio(); //Caso dê tempo de implementar

        atualizarInterface();
    }

    //Lógica ao apertar o botão de inserir paciente
    @FXML
    private void handleInserirPaciente(ActionEvent event) {
        Dialog<Paciente> dialog = new Dialog<>();
        dialog.setTitle("Recepção - Cadastrar Novo Paciente");
        dialog.setHeaderText("Insira os dados da triagem do paciente");

        ButtonType botaoConfirmarTipo = new ButtonType("Adicionar à Fila", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(botaoConfirmarTipo, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome Completo");
        TextField txtCpf = new TextField();
        txtCpf.setPromptText("000.000.000-00");
        TextField txtIdade = new TextField();
        txtIdade.setPromptText("Ex: 25");

        ComboBox<Integer> comboGravidade = new ComboBox<>();
        comboGravidade.getItems().addAll(1, 2, 3, 4, 5);
        comboGravidade.setValue(1);

        CheckBox chkPcd = new CheckBox("PCD (Pessoa com Deficiência)");
        CheckBox chkGestante = new CheckBox("Gestante");

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(new Label("CPF:"), 0, 1);
        grid.add(txtCpf, 1, 1);
        grid.add(new Label("Idade:"), 0, 2);
        grid.add(txtIdade, 1, 2);
        grid.add(new Label("Gravidade (1 a 5):"), 0, 3);
        grid.add(comboGravidade, 1, 3);
        grid.add(chkPcd, 1, 4);
        grid.add(chkGestante, 1, 5);

        dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogBotao -> {
                if (dialogBotao == botaoConfirmarTipo) {
                    try {
                        String nome = txtNome.getText().trim();
                        String cpf = txtCpf.getText().replaceAll("[^0-9]", ""); // remove pontos e traço
                        int idade = Integer.parseInt(txtIdade.getText().trim());
                        int gravidade = comboGravidade.getValue();
                        boolean pcd = chkPcd.isSelected();
                        boolean gestante = chkGestante.isSelected();

                        // Valida o CPF ANTES de criar o paciente
                        if (!model.util.ValidaCPF.isCPF(cpf)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("CPF Inválido");
                            alert.setHeaderText("Não foi possível cadastrar o paciente");
                            alert.setContentText("O CPF informado é inválido. Verifique e tente novamente.");
                            alert.showAndWait();
                            return null; // Bloqueia a inserção
                        }

                        return new Paciente(nome, cpf, gravidade, idade, pcd, gestante);

                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Dados Inválidos");
                        alert.setHeaderText("Campo de idade inválido");
                        alert.setContentText("Por favor, insira um número válido para a idade.");
                        alert.showAndWait();
                        return null;
                    }
                }
                return null;
            });

                    dialog.showAndWait().ifPresent(paciente -> {
                        fila.inserirNaFila(paciente);
                        atualizarInterface();
                    });
                    }

    //Lógica ao apertar o botão de atendimento
    @FXML
    private void handleAtendimento(ActionEvent event) {
        Paciente chamado = this.fila.atendimento();
        if (chamado != null) {
            totalAtendidos++;
            lblNomeChamado.setText(chamado.getNome().toUpperCase() + (chamado.isPreferencial() ? " (Preferencial)" : " (Comum)"));
            lblDetalhesChamado.setText(String.format("CPF: %s | Gravidade: %d", chamado.getCpf(), chamado.getPrioridade()));
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            //lblHora.setText("⏱️ Chamado às " + LocalDateTime.now().format(dtf));
        } else {
            lblNomeChamado.setText("NENHUM PACIENTE NA FILA");
            lblDetalhesChamado.setText("As filas de espera estão zeradas.");
            //lblHora.setText("");
        }
        atualizarInterface();
    }

    //Lógica ao apertar o botão de reiniciar o sistema
    @FXML
    private void handleReiniciarSistema(ActionEvent event) {
        // Reinicia as listas criando novas instâncias vazias
        fila = new FilaService();
        totalAtendidos = 0;
        lblNomeChamado.setText("SISTEMA REINICIADO");
        lblDetalhesChamado.setText("Aguardando novos pacientes.");
        //lblHora.setText("");
        atualizarInterface();
    }

//    private void iniciarRelogio() { //Caso dê tempo de implementar
//
//    }


    //Sincroniza a lista com a interface a cada alteração
    private void atualizarInterface() {
        listViewPreferencial.getItems().clear();
        listViewComum.getItems().clear();

        No atualPref = this.fila.getFilaPreferencial().getPrimNode();
        int cont = 1;
        while (atualPref != null) {
            Paciente p = atualPref.paciente;
            listViewPreferencial.getItems().add(String.format("%d. %s (G:%d)", cont++, p.getNome(), p.getPrioridade()));
            atualPref = atualPref.next;
        }

        No atualComum = this.fila.getFilaComum().getPrimNode();
        cont = 1;
        while (atualComum != null) {
            Paciente p = atualComum.paciente;
            listViewComum.getItems().add(String.format("%d. %s (G:%d)", cont++, p.getNome(), p.getPrioridade()));
            atualComum = atualComum.next;
        }

        lblStatusDasFilas.setText(String.format("Status das Filas: Operacional | Atendimentos no Turno: %d", totalAtendidos));
    }
}
