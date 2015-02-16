package cs455.overlay.wireformats;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class EventFactory {
	private static EventFactory factory = null;

	public static EventFactory getInstance() {
		if (factory == null)
			factory = new EventFactory();
		return factory;
	}

	public static Event startEvent(byte[] bytes) throws IOException {

		ByteArrayInputStream baInputStream = new ByteArrayInputStream(bytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(
				baInputStream));
		try {
			switch (din.readInt()) { // read protocol type byte
			case Protocol.OVERLAY_NODE_SENDS_REGISTRATION:
				return new OverlayNodeSendsRegistration();
			case Protocol.REGISTRY_REPORTS_REGISTRATION_STATUS:
				return new RegistryReportsRegistrationStatus();

			default:
				return null;

			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

}
