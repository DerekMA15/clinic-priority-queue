package view.util;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ComponentFactory {
    public static Button criarBotao(String texto, String corHex) {
        Button btn = new Button(texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMinHeight(50);
        btn.setStyle("-fx-background-color: " + corHex + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 25;" +
                "-fx-cursor: hand;");
        return btn;
    }

    public static VBox criarFilaBox(String titulo) {
        VBox box = new VBox(8);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #F8FCFD; -fx-border-color: #CBE6F3; -fx-border-radius: 5; -fx-background-radius: 5;");
        HBox.setHgrow(box, Priority.ALWAYS);

        Label lblTitulo = new Label(titulo);
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #4A4A4A;");
        box.getChildren().add(lblTitulo);

        return box;
    }
}
