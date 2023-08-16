module com.damagecalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires jzy3d.api;
    requires jzy3d.javafx;
    requires javafx.swing;

    opens com.damagecalculator to javafx.fxml;

    exports com.damagecalculator;
}