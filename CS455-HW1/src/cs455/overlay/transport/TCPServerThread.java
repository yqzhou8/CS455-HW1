package cs455.overlay.transport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import cs455.overlay.node.Node;
import cs455.overlay.wireformats.Event;
import cs455.overlay.wireformats.EventFactory;

public class TCPServerThread extends Thread {

	// TCPSender and TCPReceiver
	TCPSender sender;
	// TCPReceiverThread receiver;
	Node node;
	Socket socket;

	public TCPServerThread(Socket socket, Node node) {
		this.node = node;
		this.socket = socket;
		try {
			sender = new TCPSender(socket);
			// receiver = new TCPReceiverThread(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public TCPSender getSender() {

		return this.sender;
	}

	public void run() {
		while (socket != null) {
			try {
				DataInputStream dataInputStream = new DataInputStream(
						socket.getInputStream());
				int dataLength = dataInputStream.readInt();
				byte[] data = new byte[dataLength];
				dataInputStream.readFully(data, 0, dataLength);
				Event event = EventFactory.getInstance().startEvent(data);
				Event response = node.onEvent(event);
				if (response != null) {
					this.sender.sendData(response.getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
