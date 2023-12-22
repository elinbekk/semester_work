package com.example.pong.server;

import java.io.*;
import java.net.*;

public class PongServer {
	private ServerSocket serverSocket;
	private int port;

	public PongServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		this.port = port;
	}

	public void handleRequests() throws IOException {
		System.out.println("port: " + port);
		while (true) {
			Socket clientSocket = serverSocket.accept();
			PongServerConnection handler = new PongServerConnection(clientSocket,1);
			Thread handlerThread = new Thread(handler);
			handlerThread.start();
			Socket clientSocket2 = serverSocket.accept();
			PongServerConnection handler2 = new PongServerConnection(clientSocket2,2);
			Thread handlerThread2= new Thread(handler2);
			handlerThread2.start();
			handler.otherClient = handler2;
			handler2.otherClient = handler;
		}
	}


	public static void main(String[] args) {
		int port = 1234;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		try {
			PongServer server = new PongServer(port);
			server.handleRequests();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}