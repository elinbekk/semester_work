package com.example.pong.client;

import java.io.*;
import java.net.*;

import javafx.scene.text.Text;

public class PongClient {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String hostname;
    private int port;

    private Text connected = null;

    public int playerNumber;
    public boolean ready = false;

    public PongClient(String host, int port, Text connected) {
        this.hostname = host;
        this.port = port;
        this.connected = connected;
    }

    public void setConnectedText(Text connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        boolean connected = false;
        if (socket != null) {
            connected = !socket.isClosed();
        }
        return connected;
    }

    public int getPlayerNumber() {
        try {
            String line = null;
            //System.out.println("reading in");
            line = bufferedReader.readLine();
            System.out.println(line);
            playerNumber = Integer.parseInt(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerNumber;
    }

    public double readPosition() {
        double result = 0;
        try {
            String request = null;
            //System.out.println("Reading y");
            request = bufferedReader.readLine();
            if (request != null) {
                String[] tokens = request.split("\\s+");
                String paddlePositionY = tokens[0];
                result = Double.parseDouble(paddlePositionY);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void connectToServer() {
        try {
            System.out.println("Connecting to server");
            socket = new Socket(hostname, port);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());

            sendRequest("Connected");
            System.out.println("Connected");
            getPlayerNumber();
            String line = null;
            line = bufferedReader.readLine();
            System.out.println(line);
            ready = true;
            if (connected != null) {
                connected.setText("Connected to Server!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(String command) {
        printWriter.print(command + "\r\n");
        printWriter.flush();
    }

    public void disconnectFromServer() {
        try {
            sendRequest("DISCONNECT");
            bufferedReader.close();
            printWriter.close();
            socket.close();

            if (connected != null) {
                connected.setText("Disconnected from Server!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}