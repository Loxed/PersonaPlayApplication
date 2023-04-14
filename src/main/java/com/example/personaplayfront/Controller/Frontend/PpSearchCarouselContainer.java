package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.List;

public class PpSearchCarouselContainer {

    public ImageView img1;

    public ImageView img2;

    public ImageView img3;

    public ImageView img4;

    public ImageView img5;

    public ImageView img6;
    public Text name1;

    public Text name2;

    public Text name3;

    public Text name4;

    public Text name5;

    public Text name6;

    //initialize carousel
    public void initialize() {

    }

    public void setMedias(List<Medias> medias) {

        if(medias.size() > 0) {
            img1.setImage(new Image(medias.get(0).posterUrl));
            name1.setText(medias.get(0).mediaName);
        }
    }



}
