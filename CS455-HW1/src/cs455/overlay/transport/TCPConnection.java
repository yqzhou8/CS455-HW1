package cs455.overlay.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cs455.overlay.node.Node;

public class TCPConnection {
	Node node;
	ServerSocket serverSocket = null;
	// represents the connection between two nodes

	// Encapsulates the corresponding TCPReceiverThread and TCPSender
	// TCPSender and TCPReceiver
	TCPSender sender;
	TCPReceiverThread receiver;

	public TCPConnection(Node node, int port) {
		this.node = node;
		try {
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {

		// start accepting connections
		try {
			while (true) {
				Socket socket = serverSocket.accept();

				// ServerThread.java
				while (socket.isConnected()) {
					TCPServerThread tcpconnection = new TCPServerThread(socket, node);
					tcpconnection.start();
				}
				serverSocket.close();
			}

		} catch (Exception e) {
			// System.out.println(e.getMessage());
			System.out.println("server is closed");
		}
	}

	// store and reuse the connection
	// ConnectionCache
	// hashtable is candidate data structure
	// key - a unique identifier for the destination node value - the connection

}
