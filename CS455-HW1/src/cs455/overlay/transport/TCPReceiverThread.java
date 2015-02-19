package cs455.overlay.transport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import cs455.overlay.node.Node;

public class TCPReceiverThread {

	private Socket socket;
	private Node node;
	private DataInputStream dataInputStream;
	
	public TCPReceiverThread(Socket socket, Node node) throws IOException {
		this.socket = socket;
		this.node = node;
		this.dataInputStream = new DataInputStream(socket.getInputStream());
	}
	
	public byte[] receive() throws IOException{
		int dataLength;
		byte[] data = null;
		while (socket != null) {
			try {
				dataLength = dataInputStream.readInt();
				data = new byte[dataLength];
				dataInputStream.readFully(data, 0, dataLength);
				return data;
			} catch (SocketException se) {
				System.out.println(se.getMessage());
				break;
			}
		}
		return data;
	}
}