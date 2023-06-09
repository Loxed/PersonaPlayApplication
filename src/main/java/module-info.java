//frontend
module com.example.personaplayfront {

    //javafx
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    //api call
    requires okhttp;

    //send mail
    requires javax.mail.api;

    //serde
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    //personal
    requires javafx.web;
    requires jdk.jsobject;
    requires java.sql;

    //javax naming
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;


    opens com.example.personaplayfront.Controller.Frontend to javafx.fxml;
    exports com.example.personaplayfront.Controller.Frontend;

    opens com.example.personaplayfront.Model to javafx.fxml, org.hibernate.orm.core;
    exports com.example.personaplayfront.Model;

    opens com.example.personaplayfront.Application to javafx.fxml;
    exports com.example.personaplayfront.Application;

    exports com.example.personaplayfront.Controller.Handler;
    opens com.example.personaplayfront.Controller.Handler to javafx.fxml;

    //model composite keys
    opens com.example.personaplayfront.Model.CompositeKeys to org.hibernate.orm.core;
    exports com.example.personaplayfront.Model.CompositeKeys;
}
