package com.example.personaplayfront.Application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class YoutubePlayer extends Application {
    private WebView webView;
    private WebEngine webEngine;

    @Override
    public void start(Stage stage) {
        webView = new WebView();
        webEngine = webView.getEngine();

        // Load the HTML content that embeds the YouTube player
        webEngine.loadContent(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "  <head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <title>YouTube Player</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <div id=\"player\"></div>\n" +
                        "    <script src=\"https://www.youtube.com/iframe_api\"></script>\n" +
                        "    <script>\n" +
                        "      var player;\n" +
                        "      function onYouTubeIframeAPIReady() {\n" +
                        "        player = new YT.Player('player', {\n" +
                        "          videoId: 'L0fw0WzFaBM',\n" +
                        "          playerVars: {\n" +
                        "            'autoplay': 1,\n" +
                        "            'controls': 0,\n" +
                        "            'rel': 0,\n" +
                        "            'loop': 1,\n" +
                        "            'mute': 1,\n" +
                        "            'fs': 0,\n" +
                        "            'iv_load_policy': 3,\n" +
                        "            'enablejsapi': 1\n" +
                        "          },\n" +
                        "          events: {\n" +
                        "            'onReady': onPlayerReady\n" +
                        "          }\n" +
                        "        });\n" +
                        "      }\n" +
                        "      function onPlayerReady(event) {\n" +
                        "        // Hide the video title and other information\n" +
                        "        event.target.setOption('playerVars', 'showinfo=0');\n" +
                        "      }\n" +
                        "    </script>\n" +
                        "  </body>\n" +
                        "</html>"
        );

        // Get the JavaScript window object
        JSObject window = (JSObject) webEngine.executeScript("window");

        // Expose a Java object to the JavaScript environment
        window.setMember("javaApp", this);

        Scene scene = new Scene(new StackPane(webView), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    // A method that can be called from JavaScript
    public void stopPlayer() {
        webEngine.executeScript("player.stopVideo();");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
