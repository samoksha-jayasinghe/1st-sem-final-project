module lk.ijse.bakerymanagment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;
    requires com.google.protobuf;


    //opens lk.ijse.bakerymanagment to javafx.fxml;
    opens lk.ijse.bakerymanagment.controller to javafx.fxml;
    opens lk.ijse.bakerymanagment.dto.tm to javafx.base;
    opens lk.ijse.bakerymanagment.dto to javafx.base;

    exports lk.ijse.bakerymanagment;
}