package cs455.overlay.transport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPSender {

	private Socket socket;
	private DataOutputStream dataOutputStream;
	
	public TCPSender(Socket socket) throws IOException {
		this.socket = socket;
		this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void sendData(byte[] data) throws IOException {
		int dataLength = data.length;
		
			dataOutputStream.writeInt(dataLength);
			dataOutputStream.write(data, 0, dataLength);
			dataOutputStream.flush();
	}


}