package com.example.personaplayfront.Controller.Frontend;

import javafx.scene.text.Text;

public class PpAdminStatsMediaObjController {


    public Text title;


    public Text rating;
    public Text genre;
    public Text year;
    public Text nbRating;

    public void initialize() {
        title.setText("test");
        rating.setText("test");
        genre.setText("test");
        year.setText("test");
        nbRating.setText("test");

        //set wrapping to 200
        title.wrappingWidthProperty().setValue(200);
        rating.wrappingWidthProperty().setValue(200);
        genre.wrappingWidthProperty().setValue(200);
        year.wrappingWidthProperty().setValue(200);
        nbRating.wrappingWidthProperty().setValue(200);
    }
}
