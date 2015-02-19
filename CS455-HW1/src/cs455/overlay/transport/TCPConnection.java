package cs455.overlay.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cs455.overlay.node.Node;

public class TCPConnection {
	Node node;
	ServerSocket serverSocket = null;
	int port;
	byte[] address;
	// represents the connection between two nodes

	// Encapsulates the corresponding TCPReceiverThread and TCPSender
	// TCPSender and TCPReceiver
	TCPSender sender;
	TCPReceiverThread receiver;
	
	public TCPConnection(Node node, Socket socket) throws IOException {
		this.receiver = new TCPReceiverThread(socket, node);
		this.sender = new TCPSender(socket);
		this.node = node;
		try {
			serverSocket = new ServerSocket();
			port = serverSocket.getLocalPort();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.address = serverSocket.getInetAddress().toString().getBytes();
	}
	
	public TCPConnection(Node node, int port){
		this.node = node;
		this.port = port;
		try {
			serverSocket = new ServerSocket();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.address = serverSocket.getInetAddress().toString().getBytes();
	}
	
	public int getPort(){
		return this.port;
	}
	
	public byte[] getAddress(){
		return this.address;
	}

	//send data
	public void start() {

		// start accepting connections
		try {
			while (true) {
				Socket socket = serverSocket.accept();
//				TCPConnection conn = new TCPConnection(node,socket);
				TCPServerThread serverThread = new TCPServerThread(socket, node);
				serverThread.start();
				//TODO cache the connection
				
				// ServerThread.java
//				while (socket.isConnected()) {
//					TCPServerThread tcpconnection = new TCPServerThread(socket, node);
//					tcpconnection.start();
//				}
//				serverSocket.close();
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
