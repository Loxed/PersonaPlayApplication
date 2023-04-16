package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.MediaDaoImpl;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PpAdminStatsController {
    public PieChart genrePieChart;
    public VBox scrollcontent;
    public Pane pane;
    MediaDaoImpl mediaDao = new MediaDaoImpl();

    UsersDaoImpl userDao = new UsersDaoImpl();

    Map<String, Integer> genreCounts = new HashMap<>();

    public LineChart creationLineChart;

    @FXML
    CategoryAxis xAxis;
    @FXML
    NumberAxis yAxis;

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    public void initialize() throws IOException {

        series.setName("User Creation Count");

        initPieChart();

        initMediaTable();

        initUserTable();

        //set y axis to start at 0, end at maxcount, ints only
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(10);

        //count highest value in map creationLineChart
        int max = 0;

        for (XYChart.Data<String, Number> entry : series.getData()) {
            if (entry.getYValue().intValue() > max) {
                max = entry.getYValue().intValue();
            }
        }

        System.out.println(max);

        //if highest count for every 6hr increment is less than 10, set upper bound to 10
        if(max > 10) {
            yAxis.setUpperBound(max);
        }

        yAxis.setTickUnit(1);

        //set line color to purple
        series.getNode().setStyle("-fx-stroke: #8a2be2;");

        //dot color
        for (XYChart.Data<String, Number> entry : series.getData()) {
            //make it slightly vary based on count
            int count = entry.getYValue().intValue();
            int red = 138 - (count * 10);
            int green = 43 - (count * 10);
            int blue = 226 - (count * 10);
            String color = "rgb(" + red + "," + green + "," + blue + ")";
            entry.getNode().setStyle("-fx-background-color: " + color + ","+ color + ";");
        }

    }

    private void initUserTable() {

        //get users
        List<Users> users = userDao.findAll();

        Map<LocalDateTime, Integer> DtMapCount = new HashMap<>();

        //count users by date, and 6hr increments
        for (Users user : users) {

            String creationDate = user.getCreationDate();
            String creationTime = creationDate.split(" ")[1];

            //convert creationTime to 6hr increments
            String[] timeSplit = creationTime.split(":");

            int hour = Integer.parseInt(timeSplit[0]);

            int newHour = hour - (hour % 6);

            String newHourString;

            if(newHour <10) {
                newHourString = "0" + newHour;
            } else {
                newHourString = "" + newHour;
            }
            String newTime = newHourString + ":00:00";

            //create new date time
            LocalDateTime dateTime = LocalDateTime.parse(creationDate.split(" ")[0] + " " + newTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            //add to map
            if (DtMapCount.containsKey(dateTime)) {
                DtMapCount.put(dateTime, DtMapCount.get(dateTime) + 1);
            } else {
                DtMapCount.put(dateTime, 1);
            }

        }

        //go from the current date to 3 days ago, in increments of 6h. If there is no entry for that date, add 0
        //get current date
        LocalDateTime latestDate = LocalDateTime.now();

        //get latest hour %6
        int latestHour = latestDate.getHour() - (latestDate.getHour() % 6);

        latestDate = LocalDateTime.of(latestDate.getYear(), latestDate.getMonth(), latestDate.getDayOfMonth(), latestHour, 0, 0);

        //parse the latest date string to LocalDateTime and subtract 3 days
        LocalDateTime minusXDays = latestDate.minusDays(3);

        while (!minusXDays.isAfter(latestDate)) {
            if(!DtMapCount.containsKey(minusXDays)) {
                DtMapCount.put(minusXDays, 0);
            }
            minusXDays = minusXDays.plusHours(6);
        }

        //sort the map by key
        Collection<LocalDateTime> keys = DtMapCount.keySet();

        List<LocalDateTime> keyList = new ArrayList<>(keys);

        Collections.sort(keyList);

        Map<LocalDateTime, Integer> sortedMap = new LinkedHashMap<>();

        for (LocalDateTime key : keyList) {
            sortedMap.put(key, DtMapCount.get(key));
        }

        //add data to line chart
        for (LocalDateTime dateTime : sortedMap.keySet()) {
            String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int count = DtMapCount.get(dateTime);

            System.out.println(dateTimeString + " " + count);

            series.getData().add(new XYChart.Data<>(dateTimeString, count));

        }

        creationLineChart.getData().clear();

        creationLineChart.getData().add(series);

        //hide legend
        creationLineChart.setLegendVisible(false);

    }

    private void initMediaTable() throws IOException {
        List<Medias> medias = mediaDao.findAll();

        if(medias.size()>30) {
            medias = medias.subList(0, 30);
        }

        scrollcontent.getChildren().clear();

        int i =0;

        for (Medias media : medias) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_admin_stats_media_obj.fxml"));
            Parent root = loader.load();
            scrollcontent.getChildren().add(root);
            PpAdminStatsMediaObjController controller = loader.getController();

            controller.title.setText(media.mediaName);
            controller.year.setText(media.year);
            controller.genre.setText(media.genres);

            if(i%2==0) {
                scrollcontent.getChildren().get(i).setStyle("-fx-background-color: #d5c4a1");
            } i++;
        }
    }

    public void initPieChart() {
        ArrayList<String> movieGenres = new ArrayList<String>();

        //todo: search medias by ratings

        List<Medias> medias = mediaDao.findAll();

        for (Medias media : medias) {
            movieGenres.add(media.genres);
        }

        for (String movie : movieGenres) {
            String[] genres = movie.split(", ");
            for (String genre : genres) {
                if (genreCounts.containsKey(genre)) {
                    genreCounts.put(genre, genreCounts.get(genre) + 1);
                } else {
                    genreCounts.put(genre, 1);
                }
            }
        }

        int i =0;
        for (Map.Entry<String, Integer> entry : genreCounts.entrySet()) {

            //display a max of 10 genres in the pie chart
            if(i>=10){
                break;
            }i++;

            genrePieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));

        }

        //on hover, display genre and count in label in pane
        for (final PieChart.Data data : genrePieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> {

                            //convert pievalue to int
                            int pieValue = (int) data.getPieValue();

                            //make sure set name doesnt already have count, we will display the count with XXX (count) (001, 010, 999,...)
                            if(data.getName().contains(" (")) {
                            } else {
                                data.setName(data.getName() + " (" + pieValue + ")");
                            }

                    });

            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    e -> {
                        //split from ( to get the name
                        String[] split = data.getName().split(" \\(");
                        data.setName(split[0]);
                    });
        }

        //hide legend
        genrePieChart.setLegendVisible(false);
    }
}
