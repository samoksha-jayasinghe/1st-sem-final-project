module lk.ijse.bakerymanagment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.bakerymanagment to javafx.fxml;
    exports lk.ijse.bakerymanagment;
}