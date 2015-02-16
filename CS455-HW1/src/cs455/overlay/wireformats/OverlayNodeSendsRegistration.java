package cs455.overlay.wireformats;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OverlayNodeSendsRegistration implements Event {
	byte Message_Type; // OVERLAY_NODE_SENDS_REGISTRATION;
	byte IP_address_length;
	byte[] IP_address; // from InetAddress.getAddress()
	int Port_number;

	@Override
	public byte[] getBytes() throws IOException {
		// TODO Auto-generated method stub
		byte[] marshalledBytes = null;
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(
				baOutputStream));

		// dout.writeInt(type);
		// dout.writeLong(timestamp);
		//
		// byte[] identifierBytes = identifier.getBytes();
		// int elementLength = identifierBytes.length;
		// dout.writeInt(elementLength);
		// dout.write(identifierBytes);
		//
		// dout.writeInt(tracker);

		dout.flush();
		marshalledBytes = baOutputStream.toByteArray();
		baOutputStream.close();
		dout.close();
		return marshalledBytes;
	}

	@Override
	public void WireFormatWidget(byte[] marshalledBytes) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayInputStream baInputStream = new ByteArrayInputStream(
				marshalledBytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(
				baInputStream));

		// type = din.readInt();
		// timestamp = din.readLong();
		//
		// int identifierLength = din.readInt();
		// byte[] identifierBytes = new byte[identifierLength];
		// din.readFully(identifierBytes);
		// identifier = new String(identifierBytes);
		//
		// tracker = din.readInt();

		baInputStream.close();
		din.close();
	}

	@Override
	public byte getType() {
		// TODO Auto-generated method stub
		return this.Message_Type = Protocol.OVERLAY_NODE_SENDS_REGISTRATION;
	}

	@Override
	public String toString() {
		return "IP address: " + IP_address + " Port number: " + Port_number;
	}

}
