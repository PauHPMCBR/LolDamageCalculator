module com.damagecalculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.damagecalculator to javafx.fxml;
    exports com.damagecalculator;
}