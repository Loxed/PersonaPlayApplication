package com.example.personaplayfront.Controller.Handler;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SessionHandler {
    private static final String SESSION_FILE_PATH = "session.bin";

    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) ((c - 'a' + 3) % 26 + 'a'); // apply shift and wrap around alphabet
                ciphertext.append(shifted);
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }
    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char unshifted = (char) ((c - 'a' - 3 + 26) % 26 + 'a'); // unshift and wrap around alphabet
                plaintext.append(unshifted);
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }
    public static String encryptSessionId(String username, String hashedPassword) {
        return encrypt(username + ":" + hashedPassword);
    }
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //decypt from session id
    public static String[] decryptSessionId(String sessionId) {
         String _temp = decrypt(sessionId);
         return _temp.split(":");
    }
    public static void saveSessionId(String sessionId) {
        try {
            FileOutputStream fileOut = new FileOutputStream(SESSION_FILE_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sessionId);
            out.close();
            fileOut.close();
            System.out.println("Session ID saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving session ID: " + e.getMessage());
        }
    }

    public static String getSessionId() {
        try {
            FileInputStream fileIn = new FileInputStream(SESSION_FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            String sessionId = (String) in.readObject();
            in.close();
            fileIn.close();
            return sessionId;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error getting session ID: " + e.getMessage());
            return null;
        }
    }



    public static void removeSessionId() {
        File file = new File(SESSION_FILE_PATH);
        try {
            if (file.delete()) {
                System.out.println("Session ID removed successfully.");
            } else {
                System.out.println("Error removing session ID.");
            }
        } catch (Exception e) {
            System.out.println("Error removing session ID: " + e.getMessage());
        }

    }

    public static void displaySessionId() {
        String sessionId = getSessionId();
        if (sessionId != null) {
            System.out.println("Session ID: " + sessionId);
        } else {
            System.out.println("No session ID found");
        }
    }
}
