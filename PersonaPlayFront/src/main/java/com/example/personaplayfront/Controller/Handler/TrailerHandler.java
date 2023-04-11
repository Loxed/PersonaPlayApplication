package com.example.personaplayfront.Controller.Handler;

import java.io.File;
import java.io.IOException;

public class TrailerHandler {
    public static void main(String[] args) throws IOException, InterruptedException {
        String videoUrl = "https://www.youtube.com/watch?v=L0fw0WzFaBM";
        String outputFilename = "trailer.gif";
        int startTimeSeconds = 90; // 1 minute and 30 seconds
        int durationSeconds = 5;

        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-ss", String.valueOf(startTimeSeconds),
                "-t", String.valueOf(durationSeconds),
                "-i", videoUrl,
                "-vf", "scale=480:-1,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse",
                "-loop", "0",
                outputFilename
        );

        // Set the working directory to the directory where the output file should be saved
        pb.directory(new File(System.getProperty("user.dir")));

        Process process = pb.start();

        // Wait for the process to finish
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            System.out.println("Trailer extracted successfully!");
        } else {
            System.err.println("Failed to extract trailer.");
        }
    }
}
