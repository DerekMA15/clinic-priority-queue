module hospital.atendimento {
    requires javafx.controls;
    requires javafx.fxml;

    opens main to javafx.graphics, javafx.fxml;
    opens controller to javafx.fxml;

    opens model to javafx.base;
    opens view to javafx.fxml, javafx.graphics;
}