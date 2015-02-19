package cs455.overlay.wireformats;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OverlayNodeSendsRegistration implements Event {
	// TODO: set it to private and getter and setter
	public byte Message_Type; // OVERLAY_NODE_SENDS_REGISTRATION;
	public byte IP_address_length;
	public byte[] IP_address; // from InetAddress.getAddress()
	public int Port_number;
	
	

	@Override
	public byte[] getBytes() throws IOException {
		
		byte[] marshalledBytes = null;
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(
				baOutputStream));

		dout.writeByte(Message_Type);
		dout.writeByte(IP_address_length);
		dout.write(IP_address);
		dout.writeInt(Port_number);

		dout.flush();
		marshalledBytes = baOutputStream.toByteArray();
		baOutputStream.close();
		dout.close();
		return marshalledBytes;
	}

	@Override
	public void WireFormatWidget(byte[] marshalledBytes) throws IOException {
		
		ByteArrayInputStream baInputStream = new ByteArrayInputStream(
				marshalledBytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(
				baInputStream));
		
		this.Message_Type = din.readByte();
		this.IP_address_length = din.readByte();
		byte[] id = new byte[this.IP_address_length];
		din.readFully(id);
		this.IP_address = id;
		this.Port_number = din.readInt();

		baInputStream.close();
		din.close();
	}

	@Override
	public byte getType() {
		
		return this.Message_Type = Protocol.OVERLAY_NODE_SENDS_REGISTRATION;
	}

	@Override
	public String toString() {
		return "IP address: " + IP_address + " Port number: " + Port_number;
	}

	public void setMessIP(byte[] address) {
		// TODO Auto-generated method stub
		
	}

	public void setPort(int messaging_port) {
		// TODO Auto-generated method stub
		
	}

}
