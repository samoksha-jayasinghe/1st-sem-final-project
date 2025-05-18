module lk.ijse.bakerymanagment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;


    opens lk.ijse.bakerymanagment to javafx.fxml;
    opens lk.ijse.bakerymanagment.controller to javafx.fxml;
    exports lk.ijse.bakerymanagment;
}