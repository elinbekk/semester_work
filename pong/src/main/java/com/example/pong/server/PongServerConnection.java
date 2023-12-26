package com.example.pong.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class PongServerConnection extends Thread {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private boolean continueReading = true;
    public PongServerConnection opponent = null;

    private double paddlePositionY;
    private int playerNumber;

    public PongServerConnection(Socket socket, int playerNumber) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream());
        this.playerNumber = playerNumber;
    }

    public void run() {
        String line;
        try {
            line = bufferedReader.readLine();
            System.out.println(line);
            sendResponse(String.valueOf(playerNumber));
            //System.out.println("send");
            while (opponent == null) {
                System.out.println("waiting for other client");
            }
            sendResponse("READY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (continueReading) {
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
                String[] requestInfo = request.split("\\s+");
                String command = requestInfo[0];
                if (command.equalsIgnoreCase("DISCONNECT")) {
                    try {
                        continueReading = false;
                        bufferedReader.close();
                        printWriter.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (command.equalsIgnoreCase("POSITION")) {
                    paddlePositionY = Double.parseDouble(requestInfo[1]);
                    opponent.sendResponse(Double.toString(paddlePositionY));
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
        System.out.println("Sending player № " + content);
    }

    private void sendError(int errorCode,
                           String errorMessage,
                           String description) throws IOException {
    }
}