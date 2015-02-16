package cs455.overlay.transport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class TCPReceiver {

	private Socket socket;
	private DataInputStream dataInputStream;
	
	public TCPReceiver(Socket socket) throws IOException {
		this.socket = socket;
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