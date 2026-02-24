module vikingexpress.recruitment {
    exports se.lu.ics;
    exports se.lu.ics.controllers;


    opens se.lu.ics.controllers to javafx.fxml;
    opens se.lu.ics.models to javafx.base;


    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
}
