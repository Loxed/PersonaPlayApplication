package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Repo.MediaDaoImpl;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class PpSearchCarouselController {
    public Text categoryName;
    MediaDaoImpl mediaDao = new MediaDaoImpl();
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
    public HBox CategoryBox;

    //initialize carousel
    public void initialize() {

        //hide containerBox
        CategoryBox.setVisible(false);

        //wrapping width
        name1.setWrappingWidth(135);
        name2.setWrappingWidth(135);
        name3.setWrappingWidth(135);
        name4.setWrappingWidth(135);
        name5.setWrappingWidth(135);
        name6.setWrappingWidth(135);

        //set preserve ratio
        img1.setPreserveRatio(true);
        img2.setPreserveRatio(true);
        img3.setPreserveRatio(true);
        img4.setPreserveRatio(true);
        img5.setPreserveRatio(true);
        img6.setPreserveRatio(true);

        //set onHover for every image
        img1.setOnMouseEntered(event -> {
            //scaleTransition from 1 to 1.1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img1);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        img1.setOnMouseExited(event -> {
            //scaleTransition from 1.1 to 1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img1);
            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        img2.setOnMouseEntered(event -> {
            //scaleTransition from 1 to 1.1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img2);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        img2.setOnMouseExited(event -> {
            //scaleTransition from 1.1 to 1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img2);
            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        img3.setOnMouseEntered(event -> {
            //scaleTransition from 1 to 1.1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img3);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        img3.setOnMouseExited(event -> {
            //scaleTransition from 1.1 to 1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img3);
            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        img4.setOnMouseEntered(event -> {
            //scaleTransition from 1 to 1.1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img4);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        img4.setOnMouseExited(event -> {
            //scaleTransition from 1.1 to 1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img4);
            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        img5.setOnMouseEntered(event -> {
            //scaleTransition from 1 to 1.1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img5);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        img5.setOnMouseExited(event -> {
            //scaleTransition from 1.1 to 1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img5);
            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        img6.setOnMouseEntered(event -> {
            //scaleTransition from 1 to 1.1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img6);
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        img6.setOnMouseExited(event -> {
            //scaleTransition from 1.1 to 1
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img6);
            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.setText(categoryName);
    }

    public void setMedias(List<Medias> medias) {

        if(medias.size() > 0) {
            Thread thread = new Thread(() -> {
                img1.setImage(new Image(medias.get(0).posterUrl));
            }); thread.start();
            name1.setText(medias.get(0).mediaName);
            img1.setFitWidth(170);

            if(medias.get(0).available==false) {
                img1.setOpacity(0.5);
            }

            img1.setOnMouseClicked(event -> {

                //if "By Online Search" ,when clicking, we add the media to the medias db
                if(categoryName.getText().equals("By Online Search")) {

                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1);
                    img1.setEffect(colorAdjust);
                    img1.setOpacity(0.5);

                    mediaDao.save(medias.get(0));

                }


                System.out.println("clicked on " + medias.get(1).mediaName + ", location: " + medias.get(1).mediaLocation);

                if(medias.get(0).available) {

                    File file = null;

                    //load media, if it exists launch it
                    file = new File(medias.get(0).mediaLocation);

                    //try to load media, if it fails, set available to false
                    Media media;

                    try {
                        media = new Media(file.toURI().toString());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
                        try {
                            //Create a new stage
                            Stage stage = new Stage();

                            //Set the scene to the stage
                            stage.setScene(new Scene(loader.load()));

                            //get the controller
                            PpMediaWatcherController controller = loader.getController();
                            //set the media
                            controller.loadMedia(medias.get(0).mediaLocation);

                            System.out.println(controller);

                            //Set the title of the stage
                            stage.setTitle("PersonaPlayer");
                            //size 1280x720
                            stage.setMinWidth(1280);
                            stage.setMinHeight(720);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (MediaException e) {
                        System.out.println("Media location not found");
                        medias.get(0).available = false;
                        medias.get(0).mediaLocation = "";
                        //set image to grey
                        img1.setOpacity(0.5);

                        mediaDao.update(medias.get(0));
                        return;
                    }
                }
            });
        }

        if (medias.size() > 1) {
            Thread thread = new Thread(() -> {
                img2.setImage(new Image(medias.get(1).posterUrl));
            }); thread.start();
            name2.setText(medias.get(1).mediaName);
            img2.setFitWidth(170);

            if(medias.get(1).available==false) {
                img2.setOpacity(0.5);
            }

            img2.setOnMouseClicked(event -> {

                //if "By Online Search" ,when clicking, we add the media to the medias db
                if(categoryName.getText().equals("By Online Search")) {

                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1);
                    img2.setEffect(colorAdjust);
                    img2.setOpacity(0.5);

                    mediaDao.save(medias.get(0));
                }

                System.out.println("clicked on " + medias.get(1).mediaName + ", location: " + medias.get(1).mediaLocation);

                if(medias.get(1).available) {

                    File file = null;

                    //load media, if it exists launch it
                    file = new File(medias.get(1).mediaLocation);

                    //try to load media, if it fails, set available to false
                    Media media;

                    try {
                        media = new Media(file.toURI().toString());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
                        try {
                            //Create a new stage
                            Stage stage = new Stage();

                            //Set the scene to the stage
                            stage.setScene(new Scene(loader.load()));

                            //get the controller
                            PpMediaWatcherController controller = loader.getController();
                            //set the media
                            controller.loadMedia(medias.get(1).mediaLocation);

                            System.out.println(controller);

                            //Set the title of the stage
                            stage.setTitle("PersonaPlayer");
                            //size 1280x720
                            stage.setMinWidth(1280);
                            stage.setMinHeight(720);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (MediaException e) {
                        System.out.println("Media location not found");
                        medias.get(1).available = false;
                        medias.get(1).mediaLocation = "";
                        //set image to grey
                        img2.setOpacity(0.5);

                        mediaDao.update(medias.get(1));
                        return;
                    }
                }
            });
        }

        if (medias.size() > 2) {
            Thread thread = new Thread(() -> {
                img3.setImage(new Image(medias.get(2).posterUrl));
            }); thread.start();
            name3.setText(medias.get(2).mediaName);
            img3.setFitWidth(170);

            if(medias.get(2).available==false) {
                img3.setOpacity(0.5);
            }

            img3.setOnMouseClicked(event -> {

                //if "By Online Search" ,when clicking, we add the media to the medias db
                if(categoryName.getText().equals("By Online Search")) {

                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1);
                    img3.setEffect(colorAdjust);
                    img3.setOpacity(0.5);

                    mediaDao.save(medias.get(2));
                }

                System.out.println("clicked on " + medias.get(2).mediaName + ", location: " + medias.get(2).mediaLocation);

                if(medias.get(2).available) {

                    File file = null;

                    //load media, if it exists launch it
                    file = new File(medias.get(2).mediaLocation);

                    //try to load media, if it fails, set available to false
                    Media media;

                    try {
                        media = new Media(file.toURI().toString());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
                        try {
                            //Create a new stage
                            Stage stage = new Stage();

                            //Set the scene to the stage
                            stage.setScene(new Scene(loader.load()));

                            //get the controller
                            PpMediaWatcherController controller = loader.getController();
                            //set the media
                            controller.loadMedia(medias.get(2).mediaLocation);

                            System.out.println(controller);

                            //Set the title of the stage
                            stage.setTitle("PersonaPlayer");
                            //size 1280x720
                            stage.setMinWidth(1280);
                            stage.setMinHeight(720);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (MediaException e) {
                        System.out.println("Media location not found");
                        medias.get(2).available = false;
                        //clear path
                        medias.get(2).mediaLocation = "";
                        //set image to grey
                        img3.setOpacity(0.5);


                        mediaDao.update(medias.get(2));
                        return;
                    }
                }
            });
        }

        if (medias.size() > 3) {
            Thread thread = new Thread(() -> {
                img4.setImage(new Image(medias.get(3).posterUrl));
            }); thread.start();
            name4.setText(medias.get(3).mediaName);
            img4.setFitWidth(170);

            if(medias.get(3).available==false) {
                img4.setOpacity(0.5);
            }

            img4.setOnMouseClicked(event -> {

                //if "By Online Search" ,when clicking, we add the media to the medias db
                if(categoryName.getText().equals("By Online Search")) {

                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1);
                    img4.setEffect(colorAdjust);
                    img4.setOpacity(0.5);

                    mediaDao.save(medias.get(3));
                }

                System.out.println("clicked on " + medias.get(3).mediaName + ", location: " + medias.get(3).mediaLocation);

                if(medias.get(3).available) {

                    File file = null;

                    //load media, if it exists launch it
                    file = new File(medias.get(3).mediaLocation);

                    //try to load media, if it fails, set available to false
                    Media media;

                    try {
                        media = new Media(file.toURI().toString());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
                        try {
                            //Create a new stage
                            Stage stage = new Stage();

                            //Set the scene to the stage
                            stage.setScene(new Scene(loader.load()));

                            //get the controller
                            PpMediaWatcherController controller = loader.getController();
                            //set the media
                            controller.loadMedia(medias.get(3).mediaLocation);

                            System.out.println(controller);

                            //Set the title of the stage
                            stage.setTitle("PersonaPlayer");
                            //size 1280x720
                            stage.setMinWidth(1280);
                            stage.setMinHeight(720);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (MediaException e) {
                        System.out.println("Media location not found");
                        medias.get(3).available = false;
                        medias.get(3).mediaLocation = "";
                        //set image to grey
                        img4.setOpacity(0.5);

                        mediaDao.update(medias.get(3));
                        return;
                    }
                }
            });
        }

        if (medias.size() > 4) {
            Thread thread = new Thread(() -> {
                img5.setImage(new Image(medias.get(4).posterUrl));
            }); thread.start();
            name5.setText(medias.get(4).mediaName);
            img5.setFitWidth(170);

            if(medias.get(4).available==false) {
                img5.setOpacity(0.5);
            }

            img5.setOnMouseClicked(event -> {

                //if "By Online Search" ,when clicking, we add the media to the medias db
                if(categoryName.getText().equals("By Online Search")) {

                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1);
                    img5.setEffect(colorAdjust);
                    img5.setOpacity(0.5);

                    mediaDao.save(medias.get(4));
                }

                System.out.println("clicked on " + medias.get(4).mediaName + ", location: " + medias.get(4).mediaLocation);

                if(medias.get(4).available) {

                    File file = null;

                    //load media, if it exists launch it
                    file = new File(medias.get(4).mediaLocation);

                    //try to load media, if it fails, set available to false
                    Media media;

                    try {
                        media = new Media(file.toURI().toString());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
                        try {
                            //Create a new stage
                            Stage stage = new Stage();

                            //Set the scene to the stage
                            stage.setScene(new Scene(loader.load()));

                            //get the controller
                            PpMediaWatcherController controller = loader.getController();
                            //set the media
                            controller.loadMedia(medias.get(4).mediaLocation);

                            System.out.println(controller);

                            //Set the title of the stage
                            stage.setTitle("PersonaPlayer");
                            //size 1280x720
                            stage.setMinWidth(1280);
                            stage.setMinHeight(720);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (MediaException e) {
                        System.out.println("Media location not found");
                        medias.get(4).available = false;
                        medias.get(4).mediaLocation = "";
                        //set image to grey
                        img5.setOpacity(0.5);

                        mediaDao.update(medias.get(4));
                        return;
                    }
                }
            });
        }

        if (medias.size() > 5) {
            Thread thread = new Thread(() -> {
                img6.setImage(new Image(medias.get(5).posterUrl));
            }); thread.start();
            name6.setText(medias.get(5).mediaName);
            img6.setFitWidth(170);

            if(!medias.get(5).available) {
                img6.setOpacity(0.5);
            }

            img6.setOnMouseClicked(event -> {

                //if "By Online Search" ,when clicking, we add the media to the medias db
                if(categoryName.getText().equals("By Online Search")) {

                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1);
                    img6.setEffect(colorAdjust);
                    img6.setOpacity(0.5);

                    mediaDao.save(medias.get(5));
                }

                System.out.println("clicked on " + medias.get(5).mediaName + ", location: " + medias.get(5).mediaLocation);

                if(medias.get(5).available) {

                    File file = null;

                    //load media, if it exists launch it
                    file = new File(medias.get(5).mediaLocation);

                    //try to load media, if it fails, set available to false
                    Media media;

                    try {
                        media = new Media(file.toURI().toString());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
                        try {
                            //Create a new stage
                            Stage stage = new Stage();

                            //Set the scene to the stage
                            stage.setScene(new Scene(loader.load()));

                            //get the controller
                            PpMediaWatcherController controller = loader.getController();
                            //set the media
                            controller.loadMedia(medias.get(5).mediaLocation);

                            System.out.println(controller);

                            //Set the title of the stage
                            stage.setTitle("PersonaPlayer");
                            //size 1280x720
                            stage.setMinWidth(1280);
                            stage.setMinHeight(720);
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (MediaException e) {
                        System.out.println("Media location not found");
                        medias.get(5).available = false;
                        medias.get(5).mediaLocation = "";
                        //set image to grey
                        img6.setOpacity(0.5);

                        mediaDao.update(medias.get(5));
                        return;
                    }
                }
            });
        }

        //for every title between medias.size() and 6, set text to #202020
        for(int i = medias.size(); i < 6; i++) {
            switch (i) {
                case 1:
                    name2.setFill(javafx.scene.paint.Color.web("#202020"));
                    break;
                case 2:
                    name3.setFill(javafx.scene.paint.Color.web("#202020"));
                    break;
                case 3:
                    name4.setFill(javafx.scene.paint.Color.web("#202020"));
                    break;
                case 4:
                    name5.setFill(javafx.scene.paint.Color.web("#202020"));
                    break;
                case 5:
                    name6.setFill(javafx.scene.paint.Color.web("#202020"));
                    break;
            }
        }
    }

    //onHover, scaleTransition to 1.1




}
