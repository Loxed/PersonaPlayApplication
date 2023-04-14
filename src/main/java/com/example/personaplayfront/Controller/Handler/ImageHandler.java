package com.example.personaplayfront.Controller.Handler;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ImageHandler {
    private static ArrayList<ArrayList<Image>> imageGrid;

    //ctor to init
    public ImageHandler() {
        initImages();
    }

    public static void getImageAsync(int id1, int id2, Consumer<Image> callback) {
        Task<Image> task = new Task<>() {
            @Override
            protected Image call() throws Exception {
                //check image progress and wait until it is done
                while (imageGrid.get(id1).get(id2).getProgress() < 1.0) {
                    Thread.sleep(100);
                    System.out.println("image ("+id1+","+id2+") is :"+imageGrid.get(id1).get(id2).getProgress());
                }
                return getImage(id1, id2);
            }
        };
        //when the task is done, call the callback function
        task.setOnSucceeded(event -> callback.accept(task.getValue()));
        new Thread(task).start();
    }

//    public static void
    private void initImages() {

        imageGrid = new ArrayList<>();

        //initialize the arraylist of arraylist of imageview
        for (int i = 0; i < 10; i++) {
            //add first arraylist of imageview
            imageGrid.add(new ArrayList<>());

            for(int j=0; j < 4 ; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(256);
                imageView.setFitHeight(256);

                imageView.setPreserveRatio(true);

                String IMAGE_URL_FORMAT = "https://lox.rombaut.org//resources/projects/PersonaPlay/images/Face_Fantasy1_0%02dB_%d.png";
                String imageUrl = String.format(IMAGE_URL_FORMAT, i+1, j+1); // looks like this: https://lox.rombaut.org//resources/projects/PersonaPlay/images/Face_Fantasy1_01B_1.png

                //print url path
                //System.out.println(imageUrl);
                Image image = new Image(imageUrl, true);

                imageGrid.get(i).add(image);
            }
            //System.out.println(i+": "+imageGrid.get(i));
        }
    }

    //get image (image, variant)
    private static Image getImage(int image, int variant) {
        return imageGrid.get(image).get(variant);
    }
}
