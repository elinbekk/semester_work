package com.example.pong.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class PongServerConnection implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private boolean keepReading = true;
    public PongServerConnection otherClient = null;

    private double paddlePositionY;
    private int playerNumber;

    public PongServerConnection(Socket socket, int playerNumber) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream());
        this.playerNumber = playerNumber;
    }

    public void run() {
        String line = null;
        try {
            line = bufferedReader.readLine();
            System.out.println(line);
            sendResponse(String.valueOf(playerNumber));
            System.out.println("sent");

            while (otherClient == null) {
                System.out.println("waiting for other client");
            }
            sendResponse("READY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (keepReading) {
            try {
                line = bufferedReader.readLine();
                System.out.println(line);
                handleRequest(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void handleRequest(String request) throws IOException {
        try {
            if (request != null) {
                String[] tokens = request.split("\\s+");
                String command = tokens[0];

                if (command.equalsIgnoreCase("DISCONNECT")) {
                    try {
                        keepReading = false;
                        bufferedReader.close();
                        printWriter.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (command.equalsIgnoreCase("POSITION")) {
                    String[] information = request.split("\\s+");
                    paddlePositionY = Double.parseDouble(information[1]);
                    System.out.println("y = " + paddlePositionY);
                    otherClient.sendResponse(Double.toString(paddlePositionY));
                } else {
                    sendError(405, "Method Not Allowed", "You cannot use the '" + command + "' command on this server.");
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(String content) throws IOException {
        printWriter.println(content);
        printWriter.flush();
        System.out.println("Sending: " + content);
    }

    private void sendError(int errorCode,
                           String errorMessage,
                           String description) throws IOException {
    }
}