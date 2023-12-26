package com.example.pong.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class PongServer {
	private ServerSocket serverSocket;
	private Map<UUID, List<PongServerConnection>> roomList;
	private int port;

	public PongServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		this.roomList = new HashMap<>();
		this.port = port;
	}

	public void handleRequests() throws IOException {
		System.out.println("port: " + port);
		while (true) {
			UUID uuid = UUID.randomUUID();
			List<PongServerConnection> room = createRoom(new PongServerConnection(serverSocket.accept(),1),
					new PongServerConnection(serverSocket.accept(),2));
            for (PongServerConnection pongServerConnection : room) {
                pongServerConnection.start();
            }
			this.roomList.put(uuid, room);
		}
	}

	private List<PongServerConnection> createRoom(PongServerConnection psc1, PongServerConnection psc2) {
		psc1.opponent = psc2;
		psc2.opponent = psc1;
		return new ArrayList<>(Arrays.asList(psc1, psc2));
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