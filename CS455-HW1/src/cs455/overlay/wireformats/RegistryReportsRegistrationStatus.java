package cs455.overlay.wireformats;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RegistryReportsRegistrationStatus implements Event{
	// TODO: set it to private and getter and setter
	public byte Message_type; //(REGISTRY_REPORTS_REGISTRATION_STATUS)
	public int Success_status; //Assigned ID if successful, -1 in case of a failure
	public byte Information_string_length;
	public byte[] Information_string; //ASCII charset

	@Override
	public byte getType() {
		
		return this.Message_type = Protocol.REGISTRY_REPORTS_REGISTRATION_STATUS;
	}

	@Override
	public byte[] getBytes() throws IOException {
		byte[] marshalledBytes = null;
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(
				baOutputStream));

		dout.writeByte(Message_type);
		dout.writeInt(Success_status);
		dout.writeByte(Information_string_length);
		dout.write(Information_string);
		
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
		
		this.Message_type = din.readByte();
		this.Success_status = din.readInt();
		this.Information_string_length = din.readByte();
		byte[] info = new byte[this.Information_string_length];
		din.readFully(info);
		this.Information_string = info;

		baInputStream.close();
		din.close();
		
	}
	
	@Override
	public String toString() {
		return "Message Type: " + this.Message_type + " Success status: " + this.Success_status;
	}

}
